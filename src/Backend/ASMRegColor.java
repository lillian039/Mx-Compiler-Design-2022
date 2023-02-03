package Backend;

import Assembly.ASMBlock;
import Assembly.ASMFn;
import Assembly.Instruction.*;
import Assembly.Operand.*;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;

public class ASMRegColor {
    ASMFn asmFn;

    ASMFunc currentFunc;

    int K = 27;

    int regCnt = 0;

    //liveness analysis 活跃性计算 记录下每一个基本快的 use def in out
    HashMap<ASMBlock, HashSet<ASMReg>> setDef = new HashMap<>();
    HashMap<ASMBlock, HashSet<ASMReg>> setUse = new HashMap<>();
    HashMap<ASMBlock, HashSet<ASMReg>> setIn = new HashMap<>();
    HashMap<ASMBlock, HashSet<ASMReg>> setOut = new HashMap<>();
    HashSet<ASMReg> initial = new HashSet<>();

    //build 静态活跃分析构造冲突图
    HashSet<Pair<ASMReg, ASMReg>> adjSet = new HashSet<>(); //图的冲突边集合
    HashSet<ASMReg> precolored = new HashSet<>(); //机器寄存器集合
    HashMap<ASMReg, Integer> degree = new HashMap<>();
    HashMap<ASMReg, HashSet<ASMReg>> adjList = new HashMap<>(); //图的领接表表示
    HashMap<ASMReg, HashSet<ASMMoveInst>> moveList = new HashMap<>(); //一个节点到与该节点相关的move表的映射

    HashSet<ASMMoveInst> worklistMoves = new HashSet<>();//有可能合并的move指令合集
    HashSet<ASMMoveInst> activeMoves = new HashSet<>();//还未做好合并准备的move指令集合(合并失败的move)
    HashSet<ASMMoveInst> frozenMoves = new HashSet<>();//不再考虑合并的move指令集合
    HashSet<ASMMoveInst> constrainedMoves = new HashSet<>();// rd rs1冲突的move
    HashSet<ASMMoveInst> coalescedMoves = new HashSet<>();//已经合并的 move指令集合


    //make worklist 使之包含所有move指令
    HashSet<ASMReg> simplifyWorkList = new HashSet<>(); //低度数move无关结点表
    HashSet<ASMReg> freezeWorkList = new HashSet<>(); //低度数move有关结点表
    HashSet<ASMReg> spillWorkList = new HashSet<>(); //高度数结点表

    HashSet<ASMReg> coalescedNodes = new HashSet<>(); // 已合并的寄存器合集
    HashSet<ASMReg> coloredNodes = new HashSet<>();//已找色的结点合集
    HashSet<ASMReg> spilledNodes = new HashSet<>();//本轮要溢出的结点
    Stack<ASMReg> selectStack = new Stack<>();//包含从图中删除的临时变量的栈

    HashMap<ASMReg, ASMReg> alias = new HashMap<>(); //u,v... 合并后的集合

    //assign color
    HashMap<ASMReg, Integer> color = new HashMap<>();//为结点选的颜色

    HashSet<ASMReg> newTmp = new HashSet<>();

    ASMPhyReg s0, sp, ra;

    int offset = -12;

    public ASMRegColor() {

    }

    ASMPhyReg getPhyReg(int idx) {
        return asmFn.getReg(asmFn.phyRegName.get(idx));
    }

    public void visit(ASMFunc asmFunc) {
        while (true) {
            initialize();
            livenessAnalysis();
            build();
            makeWorkList();
            while (true) {
                if (!simplifyWorkList.isEmpty()) simplify();
                else if (!worklistMoves.isEmpty()) coalesce();
                else if (!freezeWorkList.isEmpty()) freeze();
                else if (!spillWorkList.isEmpty()) selectSpill();
                else break;
            }
            assignColors();
            if (spilledNodes.isEmpty()) break;
           // System.out.println(spilledNodes.size());
            rewriteProgram();
        }


        for (var block : asmFunc.asmBlocks) {
            LinkedList<ASMInst> newInst = new LinkedList<>();
            for (var stmt : block.insts) {
                if (stmt.rs1 != null && color.containsKey(stmt.rs1)) stmt.rs1 = getPhyReg(color.get(stmt.rs1));
                if (stmt.rs2 != null && color.containsKey(stmt.rs2)) stmt.rs2 = getPhyReg(color.get(stmt.rs2));
                if (stmt.rd != null && color.containsKey(stmt.rd)) stmt.rd = getPhyReg(color.get(stmt.rd));
                if (!(stmt instanceof ASMMoveInst) || stmt.rs1 != stmt.rd) newInst.add(stmt);
            }
            block.insts = newInst;
        }

        ASMBlock entry = currentFunc.getHead();
        offset -= currentFunc.maxFuncParaOffset;
        ASMBinaryArith addi = new ASMBinaryArith(s0, sp, null, new ASMImm(-offset), "addi");
        entry.add_front(addi);
        ASMMemoryInst sws0 = new ASMMemoryInst(s0, null, sp, new ASMImm(-offset - 8), "sw");
        entry.add_front(sws0);
        ASMMemoryInst swra = new ASMMemoryInst(ra, null, sp, new ASMImm(-offset - 4), "sw");
        entry.add_front(swra);
        ASMBinaryArith addiEntry = new ASMBinaryArith(sp, sp, null, new ASMImm(offset), "addi");
        entry.add_front(addiEntry);

        ASMBlock tail = currentFunc.getTail();
        ASMMemoryInst lws0 = new ASMMemoryInst(s0, null, sp, new ASMImm(-offset - 8), "lw");
        tail.push_back(lws0);
        ASMMemoryInst lwra = new ASMMemoryInst(ra, null, sp, new ASMImm(-offset - 4), "lw");
        tail.push_back(lwra);
        ASMBinaryArith addiOut = new ASMBinaryArith(sp, sp, null, new ASMImm(-offset), "addi");
        tail.push_back(addiOut);
        ASMRetInst ret = new ASMRetInst();
        tail.push_back(ret);
    }

    public void visit(ASMFn asmFn) {
        this.asmFn = asmFn;
        s0 = this.asmFn.getReg("s0");
        ra = this.asmFn.getReg("ra");
        sp = this.asmFn.getReg("sp");
        for (var func : asmFn.asmFuncs) {
            currentFunc = func;
            offset = -12;
            visit(func);
        }

    }

    void setPrecolored() {
        for (int i = 0; i < 32; i++) {
            ASMReg phyReg = asmFn.getReg(asmFn.phyRegName.get(i));
            precolored.add(phyReg);
            degree.put(phyReg, 0x3fffffff);
            adjList.put(phyReg, new HashSet<>());
            alias.put(phyReg, phyReg);
            moveList.put(phyReg, new HashSet<>());
            color.put(phyReg, i);
        }
    }

    void getUseDef() {
        for (var block : currentFunc.asmBlocks) {
            block.def = new HashSet<>();
            block.use = new HashSet<>();
            for (var stmt : block.insts) {
                stmt.use = new HashSet<>();
                stmt.def = new HashSet<>();
                if (stmt.rs1 != null) stmt.use.add(stmt.rs1);
                if (stmt.rs2 != null) stmt.use.add(stmt.rs2);
                if (stmt.rd != null) stmt.def.add(stmt.rd);
                if (stmt instanceof ASMCallInst) stmt.def.addAll(asmFn.caller);
                for (var use : stmt.use) {
                    if (!block.def.contains(use)) block.use.add(use);
                }
                block.def.addAll(stmt.def);
            }
            setDef.put(block, block.def);
            setUse.put(block, block.use);
            setIn.put(block, block.use);
            setOut.put(block, new HashSet<>());
        }
    }

    void initialize() {
        setDef = new HashMap<>();
        setUse = new HashMap<>();
        setIn = new HashMap<>();
        setOut = new HashMap<>();
        initial = new HashSet<>();

        adjSet = new HashSet<>();
        precolored = new HashSet<>();
        degree = new HashMap<>();

        adjList = new HashMap<>();
        moveList = new HashMap<>();

        worklistMoves = new HashSet<>();
        activeMoves = new HashSet<>();
        frozenMoves = new HashSet<>();
        constrainedMoves = new HashSet<>();
        coalescedMoves = new HashSet<>();

        simplifyWorkList = new HashSet<>();
        freezeWorkList = new HashSet<>();
        spillWorkList = new HashSet<>();

        coalescedNodes = new HashSet<>();
        coloredNodes = new HashSet<>();
        spilledNodes = new HashSet<>();

        selectStack = new Stack<>();
        alias = new HashMap<>();
        color = new HashMap<>();
        newTmp = new HashSet<>();

        setPrecolored();
        getUseDef();
        for (var sets : setUse.values()) initial.addAll(sets);
        for (var sets : setDef.values()) initial.addAll(sets);
        initial.removeAll(asmFn.phyRegs.values());
        for (var reg : initial) {
            degree.put(reg, 0);
            adjList.put(reg, new HashSet<>());
            moveList.put(reg, new HashSet<>());
            alias.put(reg, reg);
            color.put(reg, null);
        }
    }

    void livenessAnalysis() {
        int i = 1;
        while (true) {
            boolean flag = true;
            for (var block : currentFunc.asmBlocks) {
                HashSet<ASMReg> blockIn = setIn.get(block);
                HashSet<ASMReg> blockOut = setOut.get(block);
                int originInSize = blockIn.size(),originOutSize = blockOut.size();
                blockOut.removeAll(setDef.get(block));
                blockIn.addAll(blockOut);
                for (var succ : block.succ) {
                    blockOut.addAll(setIn.get(succ));
                }
                if (originInSize != blockIn.size() || originOutSize != blockOut.size()) flag = false;
            }
            if (flag) break;
        }

    }

    void addEdge(ASMReg u, ASMReg v) {
        if (!adjSet.contains(new Pair<>(u, v)) && u != v) {
            adjSet.add(new Pair<>(u, v));
            adjSet.add(new Pair<>(v, u));
            if (!precolored.contains(u)) {
                adjList.get(u).add(v);
                degree.replace(u, degree.get(u) + 1);
            }
            if (!precolored.contains(v)) {
                adjList.get(v).add(u);
                degree.replace(v, degree.get(v) + 1);
            }
        }
    }

    void build() {
        for (var block : currentFunc.asmBlocks) {
            HashSet<ASMReg> live = new HashSet<>(setOut.get(block));
            ListIterator<ASMInst> curInst = block.insts.listIterator(block.insts.size());
            while (curInst.hasPrevious()) {
                ASMInst I = curInst.previous();
                if (I instanceof ASMMoveInst) {
                    live.removeAll(I.use);
                    for (var def : I.def) moveList.get(def).add((ASMMoveInst) I);
                    for (var use : I.use) moveList.get(use).add((ASMMoveInst) I);
                    worklistMoves.add((ASMMoveInst) I);
                }
                //call 指令要将 caller 都加入 I.def
                live.addAll(I.def);
                for (var d : I.def)
                    for (var l : live) addEdge(l, d);
                live.removeAll(I.def);
                live.addAll(I.use);
            }
        }
    }

    void makeWorkList() {
        for (var reg : initial) {
            if (degree.get(reg) >= K) spillWorkList.add(reg);
            else if (moveRelated(reg)) freezeWorkList.add(reg);
            else simplifyWorkList.add(reg);
        }
    }

    boolean moveRelated(ASMReg n) {
        return !nodeMoves(n).isEmpty();
    }

    //此时与n所有move指令
    HashSet<ASMMoveInst> nodeMoves(ASMReg n) {
        HashSet<ASMMoveInst> newMove = new HashSet<>(activeMoves);
        newMove.addAll(worklistMoves);
        newMove.retainAll(moveList.get(n));
        return newMove;
    }

    //此时与n相邻的所有点 去掉已经合并 && 溢出的相邻点
    HashSet<ASMReg> adjacent(ASMReg n) {
        HashSet<ASMReg> newList = new HashSet<>(adjList.get(n));
        selectStack.forEach(newList::remove);
        newList.removeAll(coalescedNodes);
        return newList;
    }

    //删去低度数结点
    void simplify() {
        ASMReg n = simplifyWorkList.iterator().next();
        simplifyWorkList.remove(n);
        selectStack.push(n);
        for (var m : adjacent(n)) decrementDegree(m);

    }

    //将某结点n的degree -1
    void decrementDegree(ASMReg m) {
        int d = degree.get(m);
        degree.replace(m, d - 1);
        if (d == K) {
            HashSet<ASMReg> regs = new HashSet<>(adjacent(m));
            regs.add(m);
            enableMoves(regs);
            spillWorkList.remove(m);
            if (moveRelated(m)) freezeWorkList.add(m);
            else simplifyWorkList.add(m);
        }
    }

    //合并阶段只考虑worklistmoves中的传送指令
    void enableMoves(HashSet<ASMReg> nodes) {
        for (var n : nodes) {
            for (var m : nodeMoves(n))
                if (activeMoves.contains(m)) {
                    activeMoves.remove(m);
                    worklistMoves.add(m);
                }
        }
    }

    void coalesce() {
        ASMMoveInst m = worklistMoves.iterator().next();
        ASMReg x = getAlias(m.rd);
        ASMReg y = getAlias(m.rs1);
        ASMReg u, v;
        //如果u不是 precolored 的话v也一定不是
        if (precolored.contains(y)) {
            u = y;
            v = x;
        } else {
            u = x;
            v = y;
        }
        worklistMoves.remove(m);
        if (u == v) {
            coalescedMoves.add(m);
            addWorkList(u);
        }
        //冲突的rd rs1
        else if (precolored.contains(v) || adjSet.contains(new Pair<>(u, v))) {
            constrainedMoves.add(m);
            addWorkList(u);
            addWorkList(v);
        } else {
            HashSet<ASMReg> nodes = new HashSet<>();
            nodes.addAll(adjacent(u));
            nodes.addAll(adjacent(v));
            if (precolored.contains(u) && forAllT(u, v) || !precolored.contains(u) && conservative(nodes)) {
                coalescedMoves.add(m);
                combine(u, v);
                addWorkList(u);
            } else activeMoves.add(m); //else 合并失败
        }
    }


    //获取别名 并查集
    ASMReg getAlias(ASMReg n) {
        if (coalescedNodes.contains(n)) return getAlias(alias.get(n));
        else return n;
    }

    //可以被从图上暂时删掉的节点
    void addWorkList(ASMReg u) {
        if (!precolored.contains(u) && !moveRelated(u) && degree.get(u) < K) {
            freezeWorkList.remove(u);
            simplifyWorkList.add(u);
        }
    }

    boolean forAllT(ASMReg u, ASMReg v) {
        for (var t : adjacent(v)) {
            if (!OK(t, u)) return false;
        }
        return true;
    }

    boolean OK(ASMReg t, ASMReg r) {
        return degree.get(t) < K || precolored.contains(t) || adjSet.contains(new Pair<>(t, r));
    }


    //合并后的度数 判断保守合并 Briggs规则
    boolean conservative(HashSet<ASMReg> nodes) {
        int k = 0;
        for (var n : nodes) if (degree.get(n) >= K) k++;
        return k < K;
    }

    //将v合并到u
    void combine(ASMReg u, ASMReg v) {
        if (freezeWorkList.contains(v)) freezeWorkList.remove(v);
        else spillWorkList.remove(v);
        coalescedNodes.add(v);
        alias.replace(v, u);
        HashSet<ASMMoveInst> moveCombine = new HashSet<>(moveList.get(u));
        moveCombine.addAll(moveList.get(v));
        moveList.replace(u, moveCombine);

        HashSet<ASMReg> vSet = new HashSet<>();
        vSet.add(v);
        enableMoves(vSet);

        for (var t : adjacent(v)) {
            addEdge(t, u);
            decrementDegree(t);
        }
        if (degree.get(u) >= K && freezeWorkList.contains(u)) {
            freezeWorkList.remove(u);
            spillWorkList.add(u);
        }
    }

    void freeze() {
        ASMReg u = freezeWorkList.iterator().next();
        freezeWorkList.remove(u);
        simplifyWorkList.add(u);
        freezeMoves(u);
    }

    void freezeMoves(ASMReg u) {
        for (var m : nodeMoves(u)) {
            ASMReg v;
            //rd rs1 中一定有一个是和 u 合并的(move 与 u 有关)
            if (getAlias(m.rs1) == getAlias(u)) v = getAlias(m.rd);
            else v = getAlias(m.rs1);
            activeMoves.remove(m);
            frozenMoves.add(m);
            if (nodeMoves(v).isEmpty() && degree.get(v) < K) {
                freezeWorkList.remove(v);
                simplifyWorkList.add(v);
            }
        }
    }

    void selectSpill() {
        int maxDegree = -1;
        ASMReg m = null;
        for (var reg : spillWorkList) {
            if (degree.get(reg) > maxDegree) {
                maxDegree = degree.get(reg);
                m = reg;
            }
        }
        spillWorkList.remove(m);
        simplifyWorkList.add(m);
        freezeMoves(m);
    }

    void assignColors() {
        while (!selectStack.isEmpty()) {
            ASMReg n = selectStack.pop();
            HashSet<Integer> okColor = new HashSet<>();
            for (int i = 5; i < 32; i++) okColor.add(i);
            HashSet<ASMReg> colored = new HashSet<>(coloredNodes);
            colored.addAll(precolored);

            for (var w : adjList.get(n)) {
                if (colored.contains(getAlias(w))) {
                    okColor.remove(color.get(getAlias(w)));
                }
            }
            if (okColor.isEmpty()) spilledNodes.add(n);
            else {
                coloredNodes.add(n);
                color.replace(n, okColor.iterator().next());
            }
        }
        for (var n : coalescedNodes) {
            color.replace(n, color.get(getAlias(n)));
        }
    }

    LinkedList<ASMInst> newstmts = new LinkedList<>();

    ASMReg addLoad(ASMReg rs) {
        if (!rs.assignStack) {
            ((ASMVirReg) rs).offset = offset;
            offset -= 4;
            rs.assignStack = true;
        }
        ASMReg newRs = new ASMVirReg(regCnt++, 0);
        ASMMemoryInst lw = new ASMMemoryInst(newRs, null, s0, new ASMImm(((ASMVirReg) rs).offset), "lw");
        newstmts.add(lw);
        return newRs;
    }

    ASMReg addStore(ASMReg rs) {
        if (!rs.assignStack) {
            ((ASMVirReg) rs).offset = offset;
            offset -= 4;
            rs.assignStack = true;
        }
        ASMReg newRs = new ASMVirReg(regCnt++, 0);
        ASMMemoryInst sw = new ASMMemoryInst(null, newRs, s0, new ASMImm(((ASMVirReg) rs).offset), "sw");
        newstmts.add(sw);
        return newRs;
    }

    void rewriteProgram() {
      //  System.out.println("rewrite!");
        for (var reg : spilledNodes) reg.assignStack = false;

        for (var block : currentFunc.asmBlocks) {
            offset = -12;//true offset
            newstmts = new LinkedList<>();
            for (var stmt : block.insts) {
                if (stmt.rs1 != null && spilledNodes.contains(stmt.rs1)) stmt.rs1 = addLoad(stmt.rs1);
                if (stmt.rs2 != null && spilledNodes.contains(stmt.rs2)) stmt.rs2 = addLoad(stmt.rs2);
                newstmts.add(stmt);
                if (stmt.rd != null && spilledNodes.contains(stmt.rd)) stmt.rd = addStore(stmt.rd);
            }
            block.insts = newstmts;
        }
    }
}

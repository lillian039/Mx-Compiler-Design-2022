package Backend;

import Assembly.ASMBlock;
import Assembly.ASMFn;
import Assembly.ASMVisitor;
import Assembly.Instruction.*;
import Assembly.Operand.ASMFunc;
import Assembly.Operand.ASMReg;
import LLVMIR.Value.Register;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Stack;

public class ASMRegColor {
    ASMFn asmFn;

    int K = 27;

    //liveness analysis 活跃性计算 记录下每一个基本快的 use def in out
    HashMap<ASMBlock, HashSet<ASMReg>> setDef = new HashMap<>();
    HashMap<ASMBlock, HashSet<ASMReg>> setUse = new HashMap<>();
    HashMap<ASMBlock, HashSet<ASMReg>> setIn = new HashMap<>();
    HashMap<ASMBlock, HashSet<ASMReg>> setOut = new HashMap<>();
    HashSet<ASMReg> regCollect = new HashSet<>();

    //build 静态活跃分析构造冲突图
    HashSet<Pair<ASMReg, ASMReg>> adjSet = new HashSet<>(); //图的冲突边集合
    HashSet<ASMReg> precolored = new HashSet<>();
    HashMap<ASMReg, Integer> degree = new HashMap<>();
    HashMap<ASMReg, HashSet<ASMReg>> adjList = new HashMap<>(); //图的领接表表示
    HashMap<ASMReg, HashSet<ASMMoveInst>> moveList = new HashMap<>(); //一个节点到与该节点相关的move表的映射

    HashSet<ASMMoveInst> worklistMoves = new HashSet<>();//有可能合并的move指令合集
    HashSet<ASMMoveInst> activeMoves = new HashSet<>();//还未做好合并准备的move指令集合
    HashSet<ASMMoveInst> frozenMoves = new HashSet<>();//不再考虑合并的move指令集合


    //make worklist 使之包含所有move指令
    HashSet<ASMReg> simplifyWorkList = new HashSet<>(); //低度数move无关结点表
    HashSet<ASMReg> freezeWorkList = new HashSet<>(); //低度数move有关结点表
    HashSet<ASMReg> spillWorkList = new HashSet<>(); //高度数结点表

    HashSet<ASMReg> coalescedNodes = new HashSet<>(); // 已合并的寄存器合集
    Stack<ASMReg> selectStack = new Stack<>();//包含从图中删除的临时变量的栈

    HashMap<ASMReg, ASMReg> alias = new HashMap<>(); //u,v... 合并后的集合


    public ASMRegColor() {

    }

    public void visit(ASMFn asmFn) {
        this.asmFn = asmFn;
        setPrecolored();
        getUseDef();
        initialize();
        livenessAnalysis();
        build();
        makeWorkList();
        while (true) {

            if (simplifyWorkList.isEmpty() && worklistMoves.isEmpty() && freezeWorkList.isEmpty() && spillWorkList.isEmpty())
                break;
        }

    }

    void setPrecolored() {
        for (int i = 0; i < 32; i++) {
            ASMReg phyReg = asmFn.phyRegs.get(i);
            precolored.add(phyReg);
            degree.put(phyReg, 1 << 30);
            adjList.put(phyReg, new HashSet<>());
        }
    }

    void getUseDef() {
        for (var func : asmFn.asmFuncs) {
            for (var block : func.asmBlocks) {
                for (var stmt : block.insts) {
                    if (stmt.rs1 != null) stmt.use.add(stmt.rs1);
                    if (stmt.rs2 != null) stmt.use.add(stmt.rs2);
                    if (stmt.rd != null) stmt.def.add(stmt.rd);
                    block.def.addAll(stmt.def);
                    block.use.addAll(stmt.use);
                }
                setDef.put(block, block.def);
                setUse.put(block, block.use);
                setIn.put(block, block.use);
                setOut.put(block, new HashSet<>());
            }
        }
    }

    void initialize() {
        for (var sets : setUse.values()) regCollect.addAll(sets);
        for (var sets : setDef.values()) regCollect.addAll(sets);
        for (var reg : regCollect) {
            degree.put(reg, 0);
            adjList.put(reg, new HashSet<>());
            moveList.put(reg, new HashSet<>());
        }
    }

    void livenessAnalysis() {
        while (true) {
            boolean flag = true;
            for (var func : asmFn.asmFuncs) {
                for (var block : func.asmBlocks) {
                    HashSet<ASMReg> blockIn = setIn.get(block);
                    HashSet<ASMReg> blockOut = setOut.get(block);
                    int originInSize = blockIn.size();
                    int originOutSize = blockOut.size();
                    blockOut.removeAll(setDef.get(block));
                    blockIn.addAll(blockOut);
                    for (var succ : block.succ) {
                        blockOut.addAll(setIn.get(succ));
                    }
                    if (originInSize != blockIn.size() || originOutSize != blockOut.size()) flag = false;
                }
            }
            if (flag) break;
        }
    }

    void addEdge(ASMReg u, ASMReg v) {
        if (!adjSet.contains(new Pair<>(u, v)) && u != v) {
            adjSet.add(new Pair<>(u, v));
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
        for (var func : asmFn.asmFuncs) {
            for (var block : func.asmBlocks) {
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
                    live.addAll(I.def);
                    for (var d : I.def)
                        for (var l : live) addEdge(l, d);
                    live.removeAll(I.def);
                    live.addAll(I.use);
                }
            }
        }
    }

    void makeWorkList() {
        for (var reg : regCollect) {
            if (degree.get(reg) >= K) spillWorkList.add(reg);
            else if (moveRelated(reg)) freezeWorkList.add(reg);
            else simplifyWorkList.add(reg);
        }
    }

    boolean moveRelated(ASMReg n) {
        return !nodeMoves(n).isEmpty();
    }

    HashSet<ASMMoveInst> nodeMoves(ASMReg n) {
        HashSet<ASMMoveInst> newMove = new HashSet<>(activeMoves);
        newMove.addAll(worklistMoves);
        newMove.retainAll(moveList.get(n));
        return newMove;
    }

    HashSet<ASMReg> adjacent(ASMReg n) {//去掉已经合并|溢出的相邻点
        HashSet<ASMReg> newList = new HashSet<>(adjList.get(n));
        newList.removeAll(selectStack);
        newList.removeAll(coalescedNodes);
        return newList;
    }

    //删去某些节点
    void simplify() {
        for (var n : simplifyWorkList) {
            simplifyWorkList.remove(n);
            selectStack.push(n);
            for (var m : adjacent(n)) decrementDegree(m);
        }
    }

    //减少去掉的这个节点的degree
    void decrementDegree(ASMReg m) {
        int d = degree.get(m);
        degree.replace(m, d - 1);
        if (d == K) {
            HashSet<ASMReg> regs = new HashSet<>(adjacent(m));
            regs.add(m);
            enableMove(regs);
            spillWorkList.remove(m);
            if (moveRelated(m)) freezeWorkList.add(m);
            else simplifyWorkList.add(m);
        }
    }

    //合并阶段只考虑worklistmoves中的传送指令
    void enableMove(HashSet<ASMReg> nodes) {
        for (var n : nodes) {
            for (var m : nodeMoves(n))
                if (activeMoves.contains(m)) {
                    activeMoves.remove(m);
                    worklistMoves.add(m);
                }
        }
    }

    void Coalesce() {
        for (var m : worklistMoves) {
            ASMReg x = getAlias(m.rd);
            ASMReg y = getAlias(m.rs1);
            ASMReg u, v;
            if (precolored.contains(y)) {
                u = y;
                v = x;
            }
            else {
                u = x;
                v = y;
            }
        }
    }

    //获取别名
    ASMReg getAlias(ASMReg n) {
        if (coalescedNodes.contains(n)) getAlias(alias.get(n));
        else return n;
    }


}

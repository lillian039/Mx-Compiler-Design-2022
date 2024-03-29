package Backend;

import Assembly.ASMBlock;
import Assembly.ASMFn;
import Assembly.ASMGlobalVarDef;
import Assembly.Instruction.*;
import Assembly.Operand.*;
import LLVMIR.BasicBlock;
import LLVMIR.Expression.*;
import LLVMIR.GlobalDefine.ClassDef;
import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.GlobalDefine.VarDef;
import LLVMIR.IRType.*;
import LLVMIR.IRVisitor;
import LLVMIR.RootIR;
import LLVMIR.Terminal.Branch;
import LLVMIR.Terminal.Jump;
import LLVMIR.Terminal.Ret;
import LLVMIR.Value.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static java.lang.Math.max;

public class InstructionSelector implements IRVisitor {
    /*
    sp -4 ra
    sp -8 s0
    sp -12 para 4 (0-base)
    sp - xxx .... continue
     */
    ASMFn asmFn;

    ASMPhyReg s0;
    ASMPhyReg a0;
    ASMPhyReg sp;
    ASMPhyReg ra;

    ArrayList<ASMPhyReg> regFunc = new ArrayList<>();//function argument
    int offset = -12;

    int maxForFunc = 0;

    int cntVirReg = 0;
    ASMBlock currentASMBlock;

    ASMFunc currentFunc;

    ArrayList<ASMVirReg> calleeVirReg = new ArrayList<>();

    String currentFuncName;

    int PARA_REG_SIZE = 7;

    HashMap<String, ASMBlock> existBlock = new HashMap<>();

    HashMap<String, ASMGlobalVarDef> aSMGlobalVarDefCollect = new HashMap<>();

    ASMBlock getBlock(String blockName) {
        String trueName = blockName.equals("") ? currentFuncName : currentFuncName + "_" + blockName;
        if (existBlock.containsKey(trueName)) return existBlock.get(trueName);

        ASMBlock newBlock = new ASMBlock(trueName);
        existBlock.put(trueName, newBlock);
        return newBlock;
    }

    ASMVirReg getReg(IRValue irValue) {
        if (irValue instanceof Null) {
            return getReg(new ConstInt(0));
        } else if (irValue instanceof ConstVal) {
            ASMVirReg virReg = new ASMVirReg(cntVirReg++, offset);
            offset -= 4;
            ASMLiInst li = new ASMLiInst(virReg, new ASMImm(((ConstVal) irValue).value));
            currentASMBlock.push_back(li);
            irValue.virReg = virReg;
            return virReg;
        } else if (irValue instanceof Register && ((Register) irValue).isGlobe) {
            ASMVirReg virReg = new ASMVirReg(cntVirReg++, offset);
            offset -= 4;
            ASMLaInst la = new ASMLaInst(virReg, ((Register) irValue).name);
            currentASMBlock.push_back(la);
            irValue.virReg = virReg;
            return virReg;

        } else if (irValue instanceof ConstString) {
            ASMVirReg virReg = new ASMVirReg(cntVirReg++, offset);
            offset -= 4;
            ASMLaInst la = new ASMLaInst(virReg, ((ConstString) irValue).strName);
            currentASMBlock.push_back(la);
            irValue.virReg = virReg;
            return virReg;

        } else {
            if (irValue.virReg != null) return irValue.virReg;
            ASMVirReg virReg = new ASMVirReg(cntVirReg++, offset);
            offset -= 4;
            irValue.virReg = virReg;
            return virReg;
        }

    }


    public InstructionSelector(ASMFn asmFn) {
        this.asmFn = asmFn;
        a0 = asmFn.getReg("a0");
        s0 = asmFn.getReg("s0");
        sp = asmFn.getReg("sp");
        ra = asmFn.getReg("ra");
        regFunc.add(a0);
        regFunc.add(asmFn.getReg("a1"));
        regFunc.add(asmFn.getReg("a2"));
        regFunc.add(asmFn.getReg("a3"));
        regFunc.add(asmFn.getReg("a4"));
        regFunc.add(asmFn.getReg("a5"));
        regFunc.add(asmFn.getReg("a6"));
        regFunc.add(asmFn.getReg("a7"));
    }


    @Override
    public void visit(RootIR it) {
        it.varDefs.accept(this);
        for (var classDef : it.classDefs) {
            classDef.accept(this);
        }
        for (var funcDef : it.funcDefs) {
            if (!funcDef.isInner) funcDef.accept(this);
        }
    }

    public void visit(Alloca it) {
        ASMVirReg newVirReg = new ASMVirReg(cntVirReg++, offset);
        offset -= 4;
        ASMReg rd = getReg(it.reg);
        ASMBinaryArith addi = new ASMBinaryArith(rd, s0, null, new ASMImm(newVirReg.offset), "addi");
        currentASMBlock.push_back(addi);
    }

    public void visit(Binary it) {

        String op = null;
        switch (it.op) {
            case "add" -> op = "add";
            case "sub nsw" -> op = "sub";
            case "mul" -> op = "mul";
            case "sdiv" -> op = "div";
            case "srem" -> op = "rem";
            case "shl nsw" -> op = "sll";
            case "ashr" -> op = "srl";
            case "and" -> op = "and";
            case "or" -> op = "or";
            case "xor" -> op = "xor";
        }
        ASMBinaryArith binaryArith;
        if (it.rs2 instanceof ConstInt && !op.equals("mul") && !op.equals("div") && !op.equals("rem") && !op.equals("sub")) {
            binaryArith = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), null, new ASMImm(((ConstInt) it.rs2).value), op + "i");
        } else if (it.rs2 instanceof ConstInt && op.equals("sub")) {
            binaryArith = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), null, new ASMImm(-((ConstInt) it.rs2).value), "addi");
        } else binaryArith = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), getReg(it.rs2), null, op);
        currentASMBlock.push_back(binaryArith);
    }

    public void visit(Bitcast it) {
        ASMMoveInst move = new ASMMoveInst(getReg(it.storeReg), getReg(it.value));
        currentASMBlock.push_back(move);
    }

    public void visit(Call it) {
        int cnt = 0;
        int nowOff = 0;
        for (var para : it.parameterList) {
            if (cnt < PARA_REG_SIZE) {
                ASMMoveInst mv = new ASMMoveInst(regFunc.get(cnt), getReg(para));
                currentASMBlock.push_back(mv);
                mv.unoptimizable = true;
                cnt++;
            } else {
                nowOff += 4;
                ASMMemoryInst sw = new ASMMemoryInst(null, getReg(para), sp, new ASMImm(nowOff), "sw");
                sw.unoptimizable = true;
                currentASMBlock.push_back(sw);
            }
        }
        maxForFunc = max(maxForFunc, nowOff);
        ASMCallInst call = new ASMCallInst(it.name);
        currentASMBlock.push_back(call);
        if (it.caller != null && !(it.caller.IRType instanceof VoidType)) {
            ASMMoveInst mv = new ASMMoveInst(getReg(it.caller), a0);
            currentASMBlock.push_back(mv);
        }
    }

    public void visit(GetElementPtr it) {
        ASMVirReg startPointer = getReg(it.startPointer);
        ASMVirReg tmpStore = getReg(it.tmpStore);
        IRBaseType type = ((PtrType) it.startPointer.IRType).type;

        ASMVirReg offsetReg;
        if (it.isStruct) {
            int value = 0;
            for (int i = 1; i <= ((ConstInt) it.elementNum).value; i++) {
                value += ((ClassType) type).members.get(i - 1).size() / 8;
            }
            offsetReg = getReg(new ConstInt(value));
        } else {
            if (it.elementNum instanceof ConstInt) {
                offsetReg = getReg(new ConstInt(((ConstInt) it.elementNum).value * type.size() / 8));
            } else {
                offsetReg = new ASMVirReg(cntVirReg++, offset);
                offset -= 4;
                ConstInt size = new ConstInt(type.size() / 8);

                ASMBinaryArith mul = new ASMBinaryArith(offsetReg, getReg(it.elementNum), getReg(size), null, "mul");
                currentASMBlock.push_back(mul);
            }
        }

        ASMBinaryArith add = new ASMBinaryArith(tmpStore, offsetReg, startPointer, null, "add");
        currentASMBlock.push_back(add);
    }

    public void visit(Icmp it) {
        it.swapInst();
        if (it.op.equals("slt")) {
            ASMBinaryArith slt = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), getReg(it.rs2), null, "slt");
            currentASMBlock.push_back(slt);
        } else if (it.op.equals("sge")) {
            ASMBinaryArith sge = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), getReg(it.rs2), null, "slt");
            currentASMBlock.push_back(sge);
            ASMBinaryArith xori = new ASMBinaryArith(getReg(it.ls), getReg(it.ls), null, new ASMImm(1), "xori");
            currentASMBlock.push_back(xori);

        } else if (it.op.equals("eq")) {
            ASMBinaryArith xor = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), getReg(it.rs2), null, "xor");
            currentASMBlock.push_back(xor);
            ASMCmpInst seqz = new ASMCmpInst(getReg(it.ls), getReg(it.ls), null, "seqz");
            currentASMBlock.push_back(seqz);

        } else if (it.op.equals("ne")) {
            ASMBinaryArith xor = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), getReg(it.rs2), null, "xor");
            currentASMBlock.push_back(xor);
            ASMCmpInst snez = new ASMCmpInst(getReg(it.ls), getReg(it.ls), null, "snez");
            currentASMBlock.push_back(snez);
        }

    }

    public void visit(Load it) {
        if (!it.ptr.IRType.isSameType(it.desReg.IRType)) {
            ASMMemoryInst load = new ASMMemoryInst(getReg(it.desReg), null, getReg(it.ptr), new ASMImm(0), "lw");
            currentASMBlock.push_back(load);
          //  load.unoptimizable = true;
        } else {
            ASMMoveInst mv = new ASMMoveInst(getReg(it.desReg), getReg(it.ptr));
            currentASMBlock.push_back(mv);
        }
    }

    public void visit(Malloc it) {
        ASMMoveInst mv = new ASMMoveInst(a0, getReg(it.size));
        currentASMBlock.push_back(mv);
        mv.unoptimizable = true;

        ASMCallInst call = new ASMCallInst("__malloc");
        currentASMBlock.push_back(call);

        ASMMoveInst mvBack = new ASMMoveInst(getReg(it.ptrStart), a0);
        currentASMBlock.push_back(mvBack);

    }

    public void visit(Store it) {
        if (!it.storeAddr.IRType.isSameType(it.value.IRType)) {
            ASMMemoryInst store = new ASMMemoryInst(null, getReg(it.value), getReg(it.storeAddr), new ASMImm(0), "sw");
            currentASMBlock.push_back(store);
            store.unoptimizable = true;
        } else {
            ASMMoveInst moveInst = new ASMMoveInst(getReg(it.storeAddr), getReg(it.value));
            currentASMBlock.push_back(moveInst);
        }

    }

    public void visit(Trunc it) {
        ASMMoveInst move = new ASMMoveInst(getReg(it.storeReg), getReg(it.value));
        currentASMBlock.push_back(move);
    }

    public void visit(Zext it) {
        ASMMoveInst move = new ASMMoveInst(getReg(it.storeReg), getReg(it.value));
        currentASMBlock.push_back(move);
    }

    public void visit(Branch it) {
        ASMBranchInst branch = new ASMBranchInst(getReg(it.op), currentFuncName + "_" + it.trueBranch.labelName, "bnez");//branch neq zero
        currentASMBlock.add_succ(getBlock(it.trueBranch.labelName));
        getBlock(it.trueBranch.labelName).add_pred(currentASMBlock);
        currentASMBlock.push_back(branch);

        ASMJumpInst jump = new ASMJumpInst(currentFuncName + "_" + it.falseBranch.labelName);
        currentASMBlock.add_succ(getBlock(it.falseBranch.labelName));
        getBlock(it.falseBranch.labelName).add_pred(currentASMBlock);
        currentASMBlock.push_back(jump);
    }

    public void visit(Jump it) {
        ASMJumpInst jump = new ASMJumpInst(currentFuncName + "_" + it.target.labelName);
        currentASMBlock.add_succ(getBlock(it.target.labelName));
        getBlock(it.target.labelName).add_pred(currentASMBlock);
        currentASMBlock.push_back(jump);
    }

    public void visit(Ret it) {
        for (int i = 0; i < calleeVirReg.size(); i++) {
            ASMReg callee = asmFn.callee.get(i);
            ASMMoveInst mv = new ASMMoveInst(callee, calleeVirReg.get(i));
            currentASMBlock.push_back(mv);
            mv.unoptimizable = true;
        }
        if (it.returnReg != null && !(it.returnReg.IRType instanceof VoidType)) {
            ASMMoveInst move = new ASMMoveInst(a0, getReg(it.returnReg));
            currentASMBlock.push_back(move);
            move.unoptimizable = true;
        }
    }

    public void visit(VarDef it) {
        for (var strings : it.GlobalString) {
            ASMGlobalVarDef varDef = new ASMGlobalVarDef(strings.reg);
            aSMGlobalVarDefCollect.put(varDef.varName, varDef);
            asmFn.addGlobalVar(varDef);
        }
        for (var vars : it.GlobalVar.stmts) {
            Alloca allocVar = (Alloca) vars;
            ASMGlobalVarDef varDef = new ASMGlobalVarDef(allocVar.reg);
            aSMGlobalVarDefCollect.put(varDef.varName, varDef);
            asmFn.addGlobalVar(varDef);
        }
    }

    public void visit(FuncDef it) {
        currentFunc = new ASMFunc(it.name);
        asmFn.addFunc(currentFunc);
        currentFuncName = it.name;
        offset = -12;
        maxForFunc = 0;
        currentASMBlock = getBlock("");
        currentFunc.addBlock(currentASMBlock);

        calleeVirReg = new ArrayList<>();
        for (var callee : asmFn.callee) {
            ASMVirReg newReg = new ASMVirReg(cntVirReg++, offset);
            offset -= 4;
            ASMMoveInst mv = new ASMMoveInst(newReg, callee);
            mv.unoptimizable = true;
            calleeVirReg.add(newReg);
            currentASMBlock.push_back(mv);
        }

        int cnt = 0;
        int offsetPara = 4;

        for (var paraReg : it.parameterList) {
            if (cnt < PARA_REG_SIZE) {
                ASMMoveInst mv = new ASMMoveInst(getReg(paraReg), regFunc.get(cnt));
                currentASMBlock.push_back(mv);
                cnt++;
            } else {
                ASMMemoryInst lw = new ASMMemoryInst(getReg(paraReg), null, s0, new ASMImm(offsetPara), "lw");
                currentASMBlock.push_back(lw);
                offsetPara += 4;
            }
        }
        //it.allocate.accept(this);
        it.Entry.accept(this);
        for (var block : it.basicBlocks) {
            currentASMBlock = getBlock(block.labelName);
            currentFunc.addBlock(currentASMBlock);
            block.accept(this);
        }
        if (it.returnBlock != null) {
            currentASMBlock = getBlock(it.returnBlock.labelName);
            it.returnBlock.accept(this);
            currentFunc.addBlock(currentASMBlock);
        }

        currentFunc.maxFuncParaOffset = maxForFunc;
    }

    public void visit(ClassDef it) {

    }

    public void visit(BasicBlock it) {
        for (var stmt : it.stmts) {
            stmt.accept(this);
        }
        if (it.tailStmt != null) it.tailStmt.accept(this);
    }

}

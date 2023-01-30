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

public class InstructionSelector implements IRVisitor {
    /*
    sp -4 ra
    sp -8 s0
    sp -12 para 4 (0-base)
    sp - xxx .... continue
     */
    ASMFn asmFn;

    ASMPhyReg s0 = new ASMPhyReg("s0");
    ASMPhyReg a0 = new ASMPhyReg("a0");
    ASMPhyReg sp = new ASMPhyReg("sp");
    ASMPhyReg ra = new ASMPhyReg("ra");

    ArrayList<ASMPhyReg> regFunc = new ArrayList<>();//function argument
    int offset = -12;

    int cntVirReg = 0;
    ASMBlock currentASMBlock;

    ASMFunc currentFunc;

    String currentFuncName;


    HashMap<String, ASMGlobalVarDef> aSMGlobalVarDefCollect = new HashMap<>();

    ASMBlock getBlock(String blockName) {
        String trueName = blockName.equals("") ? currentFuncName : currentFuncName + "_" + blockName;
        ASMBlock newBlock = new ASMBlock(trueName);
        currentFunc.addBlock(newBlock);
        return newBlock;
    }

    ASMVirReg getReg(IRValue irValue) {
        if (irValue instanceof Null) {
            return getReg(new ConstInt(0));
        } else if (irValue instanceof ConstVal) {
            ASMVirReg virReg = new ASMVirReg(cntVirReg++, offset);
            offset -= (irValue.IRType.size() + 7) / 8;
            ASMLiInst li = new ASMLiInst(virReg, new ASMImm(((ConstVal) irValue).value));
            currentASMBlock.push_back(li);
            irValue.virReg = virReg;
            return virReg;
        } else if (irValue instanceof Register && ((Register) irValue).isGlobe) {
            ASMVirReg virReg = new ASMVirReg(cntVirReg++, offset);
            offset -= (irValue.IRType.size() + 7) / 8;
            ASMLaInst la = new ASMLaInst(virReg, ((Register) irValue).name);
            currentASMBlock.push_back(la);
            irValue.virReg = virReg;
            return virReg;

        } else if (irValue instanceof ConstString) {
            ASMVirReg virReg = new ASMVirReg(cntVirReg++, offset);
            offset -= (irValue.IRType.size() + 7) / 8;
            ASMLaInst la = new ASMLaInst(virReg, ((ConstString) irValue).strName);
            currentASMBlock.push_back(la);
            irValue.virReg = virReg;
            return virReg;

        } else {
            if (irValue.virReg != null) return irValue.virReg;
            ASMVirReg virReg = new ASMVirReg(cntVirReg++, offset);
            offset -= (irValue.IRType.size() + 7) / 8;
            irValue.virReg = virReg;
            return virReg;
        }

    }


    public InstructionSelector(ASMFn asmFn) {
        this.asmFn = asmFn;
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
        ASMBinaryArith binaryArith = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), getReg(it.rs2), null, op);
        currentASMBlock.push_back(binaryArith);
    }

    public void visit(Bitcast it) {
        ASMMoveInst move = new ASMMoveInst(getReg(it.storeReg), getReg(it.value));
        currentASMBlock.push_back(move);
    }

    public void visit(Call it) {
        int cnt = 0;
        int offsetNow = -8;
        for (var para : it.parameterList) {
            if (cnt < 8) {
                ASMMoveInst mv = new ASMMoveInst(regFunc.get(cnt), getReg(para));
                currentASMBlock.push_back(mv);
                cnt++;
            } else {
                ASMMemoryInst sw = new ASMMemoryInst(getReg(para), null, sp, new ASMImm(offsetNow), "sw");
                currentASMBlock.push_back(sw);
                offsetNow -= para.IRType.size() / 8;
            }
        }
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
            for (int i = 1; i <= ((ConstInt) it.elementNum).value; i++)
                value += ((ClassType) type).members.get(i - 1).size() / 8;
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
        ASMMemoryInst load = new ASMMemoryInst(getReg(it.desReg), null, getReg(it.ptr), new ASMImm(0), "lw");
        currentASMBlock.push_back(load);
    }

    public void visit(Malloc it) {
        ASMMoveInst mv = new ASMMoveInst(a0, getReg(it.size));
        currentASMBlock.push_back(mv);

        ASMCallInst call = new ASMCallInst("__malloc");
        currentASMBlock.push_back(call);

        ASMMoveInst mvBack = new ASMMoveInst(getReg(it.ptrStart), a0);
        currentASMBlock.push_back(mvBack);

    }

    public void visit(Store it) {
        ASMMemoryInst store = new ASMMemoryInst(getReg(it.value), null, getReg(it.storeAddr), new ASMImm(0), "sw");
        currentASMBlock.push_back(store);
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
        currentASMBlock.push_back(branch);
        ASMJumpInst jump = new ASMJumpInst(currentFuncName + "_" + it.falseBranch.labelName);
        currentASMBlock.push_back(jump);
    }

    public void visit(Jump it) {
        ASMJumpInst jump = new ASMJumpInst(currentFuncName + "_" + it.target.labelName);
        currentASMBlock.push_back(jump);
    }

    public void visit(Ret it) {
        if (it.returnReg != null && !(it.returnReg.IRType instanceof VoidType)) {
            ASMMoveInst move = new ASMMoveInst(a0, getReg(it.returnReg));
            currentASMBlock.push_back(move);
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
        currentASMBlock = getBlock("");
        int cnt = 0;

        int offsetPara = -12;
        //get offset
        for (var paraReg : it.parameterList) {
            if (cnt < 4) {
                cnt++;
            } else offset -= paraReg.IRType.size() / 8;
        }

        cnt = 0;
        for (var paraReg : it.parameterList) {
            if (cnt < 8) {
                ASMMoveInst mv = new ASMMoveInst(getReg(paraReg), regFunc.get(cnt));
                currentASMBlock.push_back(mv);
                cnt++;
            } else {
                ASMMemoryInst lw = new ASMMemoryInst(getReg(paraReg), null, sp, new ASMImm(offsetPara), "lw");
                currentASMBlock.push_back(lw);
                offsetPara -= paraReg.IRType.size() / 8;
            }
        }
        it.allocate.accept(this);
        it.Entry.accept(this);
        for (var block : it.basicBlocks) {
            currentASMBlock = getBlock(block.labelName);
            block.accept(this);
        }
        if (it.returnBlock != null) {
            currentASMBlock = getBlock(it.returnBlock.labelName);
            it.returnBlock.accept(this);
        }
        ASMBlock entry = currentFunc.getHead();
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

    public void visit(ClassDef it) {

    }

    public void visit(BasicBlock it) {
        for (var stmt : it.stmts) {
            stmt.accept(this);
        }
        if (it.tailStmt != null) it.tailStmt.accept(this);
    }
}

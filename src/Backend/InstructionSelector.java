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
import LLVMIR.IRType.IntType;
import LLVMIR.IRVisitor;
import LLVMIR.RootIR;
import LLVMIR.Terminal.Branch;
import LLVMIR.Terminal.Jump;
import LLVMIR.Terminal.Ret;
import LLVMIR.Value.ConstInt;
import LLVMIR.Value.ConstVal;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

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
        if (irValue.virReg != null) return irValue.virReg;
        ASMVirReg virReg = new ASMVirReg(cntVirReg++, offset);
        if (irValue instanceof ConstVal) {
            ASMLiInst li = new ASMLiInst(virReg, new ASMImm(((ConstVal) irValue).value));
            currentASMBlock.push_back(li);
        }

        offset -= irValue.IRType.size() / 8;
        irValue.virReg = virReg;
        return virReg;
    }

    ASMAddress getAddress(IRValue addrAddress) {
        if (addrAddress instanceof Register && ((Register) addrAddress).isGlobe) {
            ASMVirReg virReg = new ASMVirReg(cntVirReg++, offset);
            offset -= 4;
            ASMLaInst la = new ASMLaInst(virReg, ((Register) addrAddress).name);
            currentASMBlock.push_back(la);
            return new ASMAddress(new ASMImm(0), virReg);
        }
        ASMVirReg virReg = getReg(addrAddress);
        return new ASMAddress(new ASMImm(virReg.offset), s0);
    }


    public InstructionSelector(ASMFn asmFn) {
        this.asmFn = asmFn;
        regFunc.add(a0);
        regFunc.add(asmFn.getReg("a1"));
        regFunc.add(asmFn.getReg("a2"));
        regFunc.add(asmFn.getReg("a3"));
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
        getReg(it.reg);
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
        ASMBinaryArith binaryArith = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), getReg(it.rs2), op);
        currentASMBlock.push_back(binaryArith);
    }

    public void visit(Bitcast it) {
        ASMMoveInst move = new ASMMoveInst(getReg(it.value), getReg(it.storeReg));
        currentASMBlock.push_back(move);
    }

    public void visit(Call it) {
        int cnt = 0;
        int offsetNow = -8;
        for (var para : it.parameterList) {
            if (cnt < 4) {
                ASMMemoryInst lw = new ASMMemoryInst(regFunc.get(cnt), getAddress(para), "lw");
                currentASMBlock.push_back(lw);
                cnt++;
            } else {
                ASMMemoryInst sw = new ASMMemoryInst(getReg(para), new ASMAddress(new ASMImm(offsetNow), sp), "sw");
                currentASMBlock.push_back(sw);
                offsetNow -= para.IRType.size() / 8;
            }
        }
        ASMCallInst call = new ASMCallInst(it.name);
        currentASMBlock.push_back(call);
        if (it.caller != null) {
            ASMMemoryInst sw = new ASMMemoryInst(a0, getAddress(it.caller), "sw");
            currentASMBlock.push_back(sw);
        }
    }

    public void visit(GetElementPtr it) {
        ASMVirReg startPointer = getReg(it.startPointer);
        ASMVirReg tmpStore = getReg(it.tmpStore);
        ASMMemoryInst lw;
        if (it.elementNum instanceof ConstInt) {
            int value = ((ConstInt) it.elementNum).value;
            lw = new ASMMemoryInst(tmpStore, new ASMAddress(new ASMImm(value), startPointer), "lw");
        } else {
            ASMVirReg offsetReg = new ASMVirReg(cntVirReg++, offset);
            offset -= 4;
            lw = new ASMMemoryInst(tmpStore, new ASMAddress(offsetReg, startPointer), "lw");
        }
        currentASMBlock.push_back(lw);
    }

    public void visit(Icmp it) {
        it.swapInst();
        if (it.op.equals("slt")) {
            ASMBinaryArith slt = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), getReg(it.rs2), "slt");
            currentASMBlock.push_back(slt);
        } else if (it.op.equals("sge")) {
            ASMBinaryArith sge = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), getReg(it.rs2), "slt");
            currentASMBlock.push_back(sge);
            ASMBinaryArith xori = new ASMBinaryArith(getReg(it.ls), getReg(it.ls), new ASMImm(1), "xori");
            currentASMBlock.push_back(xori);

        } else if (it.op.equals("eq")) {
            ASMBinaryArith xor = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), getReg(it.rs2), "xor");
            currentASMBlock.push_back(xor);
            ASMPseudoInst seqz = new ASMPseudoInst(getReg(it.ls), getReg(it.ls), "seqz");
            currentASMBlock.push_back(seqz);

        } else if (it.op.equals("ne")) {
            ASMBinaryArith xor = new ASMBinaryArith(getReg(it.ls), getReg(it.rs1), getReg(it.rs2), "xor");
            currentASMBlock.push_back(xor);
            ASMPseudoInst snez = new ASMPseudoInst(getReg(it.ls), getReg(it.ls), "snez");
            currentASMBlock.push_back(snez);
        }

    }

    public void visit(Load it) {
        String op = it.loadType.isSameType(new IntType(8, "bool")) ? "lbu" : "lw";
        ASMMemoryInst load = new ASMMemoryInst(getReg(it.desReg), getAddress(it.ptr), op);
        currentASMBlock.push_back(load);
    }

    public void visit(Malloc it) {
        ASMCallInst call = new ASMCallInst("__malloc");
        currentASMBlock.push_back(call);
    }

    public void visit(Store it) {
        String op = it.value.IRType.isSameType(new IntType(8, "bool")) ? "sb" : "sw";
        ASMMemoryInst store = new ASMMemoryInst(getReg(it.value), getAddress(it.storeAddr), op);
        currentASMBlock.push_back(store);
    }

    public void visit(Trunc it) {
        ASMMoveInst move = new ASMMoveInst(getReg(it.value), getReg(it.storeReg));
        currentASMBlock.push_back(move);
    }

    public void visit(Zext it) {
        ASMMoveInst move = new ASMMoveInst(getReg(it.value), getReg(it.storeReg));
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
        if (it.returnReg != null) {
            ASMMemoryInst lw = new ASMMemoryInst(a0, getAddress(it.returnReg), "lw");
            currentASMBlock.push_back(lw);
        }
        ASMRetInst ret = new ASMRetInst();
        currentASMBlock.push_back(ret);
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

        for (var paraReg : it.parameterList) {
            if (cnt < 4) {
                ASMMemoryInst sw = new ASMMemoryInst(regFunc.get(cnt), getAddress(paraReg), "sw");
                currentASMBlock.push_back(sw);
                cnt++;
            } else {
                ASMMemoryInst lw = new ASMMemoryInst(getReg(paraReg), new ASMAddress(new ASMImm(offsetPara), sp), "lw");
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
        ASMMemoryInst sws0 = new ASMMemoryInst(s0, new ASMAddress(new ASMImm(-offset + 4), sp), "sw");
        entry.add_front(sws0);
        ASMMemoryInst swra = new ASMMemoryInst(ra, new ASMAddress(new ASMImm(-offset + 8), sp), "sw");
        entry.add_front(swra);
        ASMBinaryArith addiEntry = new ASMBinaryArith(sp, sp, new ASMImm(offset), "addi");
        entry.add_front(addiEntry);

        ASMBlock tail = currentFunc.getTail();
        ASMMemoryInst lws0 = new ASMMemoryInst(s0, new ASMAddress(new ASMImm(-offset + 8), sp), "lw");
        tail.push_back(lws0);
        ASMMemoryInst lwra = new ASMMemoryInst(ra, new ASMAddress(new ASMImm(-offset + 4), sp), "lw");
        tail.push_back(lwra);
        ASMBinaryArith addiOut = new ASMBinaryArith(sp, sp, new ASMImm(-offset), "addi");
        tail.push_back(addiOut);
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

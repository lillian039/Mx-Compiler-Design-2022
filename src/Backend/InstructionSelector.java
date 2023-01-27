package Backend;

import Assembly.ASMBlock;
import Assembly.ASMFn;
import Assembly.ASMGlobalVarDef;
import Assembly.Instruction.*;
import Assembly.Operand.ASMAddress;
import Assembly.Operand.ASMImm;
import Assembly.Operand.ASMReg;
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
import LLVMIR.Value.ConstVal;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

import java.util.HashMap;

public class InstructionSelector implements IRVisitor {
    ASMFn asmFn;

    ASMBlock currentASMBlock;

    String currentFuncName;


    HashMap<String, ASMBlock> ASMBlockCollect = new HashMap<>();

    HashMap<String, ASMGlobalVarDef> ASMGlobalVarDefCollect = new HashMap<>();

    ASMBlock getBlock(String blockName) {
        if (ASMBlockCollect.containsKey(blockName)) return ASMBlockCollect.get(blockName);

        ASMBlock newBlock = new ASMBlock(blockName);
        ASMBlockCollect.put(blockName, newBlock);
        return newBlock;
    }

    ASMReg getReg(IRValue irValue) {
        return null;
    }

    ASMAddress getAddress(Register ptrReg) {
        return null;
    }

    ASMImm getOffSet(String name) {
        return null;
    }

    public InstructionSelector(ASMFn asmFn) {
        this.asmFn = asmFn;
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
        ASMCallInst call = new ASMCallInst(it.name);
        currentASMBlock.push_back(call);
    }

    public void visit(GetElementPtr it) {

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
        ASMBranchInst branch = new ASMBranchInst(getReg(it.op), getBlock(it.trueBranch.labelName), "bnez");//branch neq zero
        currentASMBlock.push_back(branch);
        ASMJumpInst jump = new ASMJumpInst(getBlock(it.falseBranch.labelName));
        currentASMBlock.push_back(jump);
    }

    public void visit(Jump it) {
        ASMJumpInst jump = new ASMJumpInst(getBlock(it.target.labelName));
        currentASMBlock.push_back(jump);
    }

    public void visit(Ret it) {
        if (it.returnReg != null) {

        }
        ASMRetInst ret = new ASMRetInst();
        currentASMBlock.push_back(ret);
    }

    public void visit(VarDef it) {

    }

    public void visit(FuncDef it) {
        currentASMBlock = getBlock(it.name);
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

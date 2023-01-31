package MiddleEnd;

import LLVMIR.BasicBlock;
import LLVMIR.Expression.*;
import LLVMIR.GlobalDefine.ClassDef;
import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.GlobalDefine.VarDef;
import LLVMIR.IRType.PtrType;
import LLVMIR.IRVisitor;
import LLVMIR.RootIR;
import LLVMIR.Terminal.Branch;
import LLVMIR.Terminal.Jump;
import LLVMIR.Terminal.Ret;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

import java.util.ArrayList;
import java.util.HashMap;

public class IRMem2Reg implements IRVisitor {

    RootIR rootIR;

    int cnt;

    HashMap<String, Register> memToReg = new HashMap<>();

    public IRMem2Reg(RootIR rootIR) {
        this.rootIR = rootIR;
    }

    IRValue getReg(IRValue irValue) {
        if (!(irValue instanceof Register)) return irValue;
        String name = ((Register) irValue).name;
        if (name == null || !memToReg.containsKey(name)) return irValue;
        return memToReg.get(((Register) irValue).name);
    }

    @Override
    public void visit(RootIR it) {
        for (var func : it.funcDefs) {
            if (!func.isInner) func.accept(this);
        }
    }

    @Override
    public void visit(BasicBlock it) {
        for (var stmt : it.stmts) {
            stmt.accept(this);
        }
        if (it.tailStmt != null) it.tailStmt.accept(this);
    }

    @Override
    public void visit(Alloca it) {
        memToReg.put(it.reg.name, new Register(((PtrType) it.reg.IRType).type, "__reg_" + cnt++));
    }

    @Override
    public void visit(Binary it) {
        it.rs1 = getReg(it.rs1);
        it.rs2 = getReg(it.rs2);
    }

    @Override
    public void visit(Bitcast it) {
        it.value = getReg(it.value);
        it.storeReg = (Register) getReg(it.storeReg);
    }

    @Override
    public void visit(Call it) {
        ArrayList<IRValue> newList = new ArrayList<>();
        for(var para:it.parameterList){
            newList.add(getReg(para));
        }
        it.parameterList = newList;
    }

    @Override
    public void visit(GetElementPtr it) {
        it.startPointer = (Register) getReg(it.startPointer);
        it.tmpStore = (Register) getReg(it.tmpStore);
        it.elementNum = getReg(it.elementNum);
    }

    @Override
    public void visit(Icmp it) {
        it.rs1 = getReg(it.rs1);
        it.rs2 = getReg(it.rs2);
    }

    @Override
    public void visit(Load it) {
        it.ptr = (Register) getReg(it.ptr);
        it.desReg = (Register) getReg(it.desReg);
    }

    @Override
    public void visit(Malloc it) {

    }

    @Override
    public void visit(Store it) {
        it.value = getReg(it.value);
        it.storeAddr = (Register) getReg(it.storeAddr);
    }

    @Override
    public void visit(Trunc it) {
        it.value = getReg(it.value);
        it.storeReg = (Register) getReg(it.storeReg);
    }

    @Override
    public void visit(Zext it) {
        it.value = getReg(it.value);
        it.storeReg = (Register) getReg(it.storeReg);
    }

    @Override
    public void visit(Branch it) {
        it.op = getReg(it.op);
    }

    @Override
    public void visit(Jump it) {

    }

    @Override
    public void visit(Ret it) {
        it.returnReg = (Register) getReg(it.returnReg);
    }

    @Override
    public void visit(FuncDef it) {
        it.allocate.accept(this);
        it.Entry.accept(this);
        for (var block : it.basicBlocks) {
            block.accept(this);
        }
        if (it.returnBlock != null) it.returnBlock.accept(this);
    }

    @Override
    public void visit(ClassDef it) {

    }

    @Override
    public void visit(VarDef it) {

    }

}

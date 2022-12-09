package LLVMIR.Expression;

import AST.Atom.TypeNode;
import LLVMIR.IRInstruction;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Malloc extends IRInstruction {
    Register ptrStart;
    IRValue size;

    IRBaseType type;

    public Malloc(IRBaseType irType, Register ptrStart, IRValue size) {
        this.type = irType;
        this.ptrStart = ptrStart;
        this.size = size;
    }

    @Override
    public void printInstruct() {

    }
}

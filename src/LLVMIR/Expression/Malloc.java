package LLVMIR.Expression;

import AST.Atom.TypeNode;
import LLVMIR.IRInstruction;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Malloc extends IRInstruction {
    Register ptrStart;
    IRValue size;


    public Malloc(Register ptrStart, IRValue size) {
        this.ptrStart = ptrStart;
        this.size = size;
    }

    @Override
    public void printInstruct() {
        System.out.println(ptrStart.valueToString() + " = @__malloc(" + size.valueToString() + ")");
    }
}

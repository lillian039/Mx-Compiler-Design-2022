package LLVMIR.Terminal;

import LLVMIR.IRType.VoidType;
import LLVMIR.IRVisitor;
import LLVMIR.Value.Register;

public class Ret extends TerminalStmt {
    public Register returnReg;

    public Ret(Register reg) {
        returnReg = reg;
    }

    @Override
    public void printInstruct() {
        System.out.print("ret " + returnReg.IRType.typeToString());
        if (returnReg.IRType instanceof VoidType) System.out.println();
        else System.out.println(" " + returnReg.valueToString());
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

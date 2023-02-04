package LLVMIR.Value;

import AST.Atom.TypeNode;
import Assembly.Operand.ASMVirReg;
import LLVMIR.IRType.IRBaseType;

public abstract class IRValue {
    public IRBaseType IRType;

    public int value;

    public boolean isConst = false;
    public ASMVirReg virReg;

    public abstract String valueToString();
}

package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.IRType.IntType;

public class ConstBool extends IRValue {
    public boolean value;

    public ConstBool(boolean value) {
        this.value = value;
        this.IRType = new IntType(8);

    }
}

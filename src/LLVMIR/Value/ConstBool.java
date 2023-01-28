package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.IRType.IntType;

public class ConstBool extends ConstVal {

    public ConstBool(boolean value) {
        this.value = value ? 1 : 0;
        this.IRType = new IntType(8, "bool");
    }

    @Override
    public String valueToString() {
        return value + "";
    }
}

package LLVMIR.Value;

import AST.Atom.TypeNode;

public class ConstBool extends IRValue {
    public boolean value;

    public ConstBool(boolean value) {
        this.value = value;
        this.typeNode = new TypeNode("bool");

    }
}

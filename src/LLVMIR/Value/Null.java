package LLVMIR.Value;

import AST.Atom.TypeNode;

public class Null extends IRValue {
    public Null() {
        this.typeNode = new TypeNode("null");
    }
}

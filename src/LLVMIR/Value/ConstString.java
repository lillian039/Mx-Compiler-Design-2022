package LLVMIR.Value;

import AST.Atom.TypeNode;

public class ConstString extends IRValue {
    String string;

    public ConstString(String string) {
        this.string = string;
        this.typeNode=new TypeNode("string");
    }
}

package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.IRType.NullType;

public class Null extends IRValue {
    public Null() {
        this.IRType=new NullType();
    }

    @Override
    public String valueToString() {
        return "null";
    }

}

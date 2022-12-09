package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRType.IntType;
import LLVMIR.IRType.PtrType;

public class ConstString extends IRValue {
    PtrType pointer;
    String string;
    int number;
    int size;

    public ConstString(String string, int number) {
        this.string = string;
        pointer=new PtrType(1, new IntType(8,"char"));
        //.....
    }

    @Override
    public String valueToString() {
        return string;
    }
}

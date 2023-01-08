package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRType.IntType;
import LLVMIR.IRType.PtrType;

public class ConstString extends IRValue {
    PtrType pointer;
    String string;
    public int number;
    int size;

    public ConstString(String string, int number) {
        this.string = string;
        this.number = number;
        this.size=string.length();
        pointer = new PtrType( new IntType(8, "char"));
    }

    @Override
    public String valueToString() {
        String name;
        if (number != 0) name = ".str." + number;
        else name = ".str";
        return name;
    }

    public String toAllocate() {
        int num = string.length() + 1;
        return "private unnamed_addr constant [" + num + " x i8] c\"" + string + "\\00\"" + ", align " + (num + 7) / 8;
    }
}

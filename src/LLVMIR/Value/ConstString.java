package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.IRType.IRBaseType;

public class ConstString extends IRValue {
    String string;
    int number;
    int size;

    public ConstString(String string, int number, IRBaseType type) {
        this.string = string;
        this.number = number;
        this.size = string.length();
        this.IRType = type;
    }
}

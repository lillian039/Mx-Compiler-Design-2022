package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.Expression.Call;
import LLVMIR.Expression.GetElementPtr;
import LLVMIR.IRType.IRBaseType;

public class Register extends IRValue {
    public int index;
    public IRValue value = null;
    public Call funcVal = null;
    public GetElementPtr getElementPtr = null;
    public String name;

    public Register(int idx, IRBaseType irBaseType) {
        this.index = idx;
        this.IRType=irBaseType;
    }

    public Register(int idx, IRBaseType irBaseType, String name) {
        this.index = idx;
        this.IRType=irBaseType;
        this.name = name;
    }

    public Register(int idx, IRBaseType irBaseType, IRValue value) {
        this.index = idx;
        this.IRType = irBaseType;
        this.value = value;
    }

}

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
    public String name = null;

    public String rename = null;//防止重名

    public boolean isGlobe = false;

    public Register(int idx, IRBaseType irBaseType) {
        this.index = idx;
        this.IRType = irBaseType;
    }

    public Register(IRBaseType irBaseType, String name) {
        this.IRType = irBaseType;
        this.name = name;
    }

    public Register(int idx, IRBaseType irBaseType, IRValue value) {
        this.index = idx;
        this.IRType = irBaseType;
        this.value = value;
    }

    @Override
    public String valueToString() {
        if (isGlobe) return "@" + name;
        else if (rename != null) return "%" + rename;
        else if (name != null) return "%" + name;
        return "%" + index;
    }
}

package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.Expression.CallFunction;
import LLVMIR.Expression.GetElementPtr;

public class Register extends IRValue {
    public int index;
    public IRValue value = null;
    public CallFunction funcVal = null;
    public GetElementPtr getElementPtr = null;
    public String name;

    public Register(int idx, TypeNode typeNode) {
        this.index = idx;
        this.typeNode = typeNode;
    }

    public Register(int idx, TypeNode typeNode, String name) {
        this.index = idx;
        this.typeNode = typeNode;
        this.name = name;
    }

    public Register(int idx, TypeNode typeNode, IRValue value) {
        this.index = idx;
        this.typeNode = typeNode;
        this.value = value;
    }

}

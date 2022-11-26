package LLVMIR.Value;

import AST.Atom.TypeNode;

public class Register extends Entity {
    public int index;
    public TypeNode typeNode;
    public Entity value = null;
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

    public Register(int idx, TypeNode typeNode, Entity value) {
        this.index = idx;
        this.typeNode = typeNode;
        this.value = value;
    }

}

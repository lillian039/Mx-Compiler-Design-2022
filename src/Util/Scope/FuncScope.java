package Util.Scope;

import AST.Atom.TypeNode;

public class FuncScope extends Scope {
    public TypeNode returnType;

    public FuncScope(Scope parentScope,TypeNode returnType) {
        super(parentScope);
        this.returnType=returnType;
    }

}

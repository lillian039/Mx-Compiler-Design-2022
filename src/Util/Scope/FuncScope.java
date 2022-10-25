package Util.Scope;

import AST.Atom.TypeNode;

public class FuncScope extends Scope {
    public TypeNode returnType;

    public FuncScope(Scope parentScope) {
        super(parentScope);
    }

}

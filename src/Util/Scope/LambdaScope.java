package Util.Scope;

import AST.Atom.SingleVarDefNode;
import AST.Atom.TypeNode;

public class LambdaScope extends Scope{
    private boolean isGlobe;

    public TypeNode returnType=null;

    public LambdaScope(Scope parentScope){
        super(parentScope);
    }

    public LambdaScope(Scope parentScope,boolean isGlobe){
        super(parentScope);
        this.isGlobe=isGlobe;
    }

    @Override
    public SingleVarDefNode getVar(String name) {
        if(isGlobe) return super.getVar(name);
        if(super.variableMembers.containsKey(name))return variableMembers.get(name);
        return null;
    }
}

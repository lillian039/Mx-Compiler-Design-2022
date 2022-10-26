package Util.Scope;

import AST.Atom.FunctionParameterListNode;
import AST.Atom.SingleVarDefNode;
import AST.Atom.TypeNode;

public class FuncScope extends Scope {
    public TypeNode returnType;
    public FunctionParameterListNode parameter;

    public boolean isConstruction=false;
    public FuncScope(Scope parentScope,TypeNode returnType,FunctionParameterListNode parameter) {
        super(parentScope);
        this.parameter=parameter;
        this.returnType=returnType;
        if(this.parameter!=null){
            for(SingleVarDefNode var:this.parameter.parameterList){
                super.addVarDefine(var.name,var);
            }
        }
    }

}

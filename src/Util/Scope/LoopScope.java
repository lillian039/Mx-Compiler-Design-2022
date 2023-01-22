package Util.Scope;

import AST.ExprNode;
import LLVMIR.BasicBlock;

public class LoopScope extends Scope{
    public BasicBlock breakLoop;
    public BasicBlock continueLoop;

    public ExprNode stepNode=null;
    public LoopScope(Scope parentScope){
        super(parentScope);
    }

}

package Util.Scope;

import LLVMIR.BasicBlock;

public class LoopScope extends Scope{
    public BasicBlock breakLoop;
    public BasicBlock continueLoop;
    public LoopScope(Scope parentScope){
        super(parentScope);
    }

}

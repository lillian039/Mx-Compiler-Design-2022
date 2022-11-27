package LLVMIR.Terminal;

import LLVMIR.BasicBlock;

public class Jump extends TerminalStmt{

    public BasicBlock target;

    public Jump(BasicBlock target){
        this.target=target;
    }
}

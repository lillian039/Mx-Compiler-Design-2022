package LLVMIR.Terminal;

import LLVMIR.BasicBlock;

public class Jump extends TerminalStmt {

    public BasicBlock target;

    public Jump(BasicBlock target) {
        this.target = target;
    }

    @Override
    public void printInstruct() {
        System.out.println("br label %" + target.label);
    }
}

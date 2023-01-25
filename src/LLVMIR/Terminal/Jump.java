package LLVMIR.Terminal;

import LLVMIR.BasicBlock;
import LLVMIR.IRVisitor;

public class Jump extends TerminalStmt {

    public BasicBlock target;

    public Jump(BasicBlock target) {
        this.target = target;
    }

    @Override
    public void printInstruct() {
        System.out.println("br label %" + target.labelName);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

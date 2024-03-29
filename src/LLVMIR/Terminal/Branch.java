package LLVMIR.Terminal;

import LLVMIR.BasicBlock;
import LLVMIR.IRVisitor;
import LLVMIR.Value.IRValue;

public class Branch extends TerminalStmt {
    public IRValue op;
    public BasicBlock trueBranch, falseBranch;//if branch else branch loop true false

    public Branch(IRValue op, BasicBlock trueBranch, BasicBlock falseBranch) {
        super();
        this.op = op;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    @Override
    public void printInstruct() {
        System.out.println("br " + op.IRType.typeToString() + " " + op.valueToString() +
                ", label %" + trueBranch.labelName + ", label %" + falseBranch.labelName);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

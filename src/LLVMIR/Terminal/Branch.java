package LLVMIR.Terminal;

import LLVMIR.BasicBlock;
import Util.Entity;

public class Branch extends TerminalStmt {
    public Entity op;

    public BasicBlock trueBranch, falseBranch;//if branch else branch loop true false

    public Branch(Entity op, BasicBlock trueBranch, BasicBlock falseBranch) {
        super();
        this.op = op;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }
}

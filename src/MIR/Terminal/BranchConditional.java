package MIR.Terminal;

import MIR.BasicBlock;
import Util.Entity;

public class BranchConditional extends TerminalStmt {
    public Entity op;
    public BasicBlock trueBranch, falseBranch;//if branch else branch loop true false

    public BranchConditional(Entity op, BasicBlock trueBranch, BasicBlock falseBranch) {
        super();
        this.op = op;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }
}

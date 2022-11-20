package MIR.Terminal;

import MIR.BasicBlock;
import Util.Entity;

public class BranchUnconditional extends TerminalStmt{
    public Entity op;
    public BasicBlock target;
}

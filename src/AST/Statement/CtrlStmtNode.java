package AST.Statement;

import AST.ASTVisitor;
import AST.StmtNode;
import Util.Position;

public class CtrlStmtNode extends StmtNode {
    public boolean isBreak = false;
    public boolean isContinue = false;

    public CtrlStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

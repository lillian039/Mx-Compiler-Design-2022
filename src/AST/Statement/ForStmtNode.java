package AST.Statement;

import AST.ASTVisitor;
import AST.StmtNode;
import Util.Position;

public class ForStmtNode extends StmtNode {
    public ForStmtNode(Position pos){super(pos); }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

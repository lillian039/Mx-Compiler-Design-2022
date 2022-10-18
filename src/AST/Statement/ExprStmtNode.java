package AST.Statement;

import AST.ASTVisitor;
import AST.StmtNode;
import Util.Position;

public class ExprStmtNode extends StmtNode {
    public ExprStmtNode(Position pos){super(pos); }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

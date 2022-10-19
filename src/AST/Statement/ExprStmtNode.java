package AST.Statement;

import AST.ASTVisitor;
import AST.StmtNode;
import AST.ExprNode;
import Util.Position;

public class ExprStmtNode extends StmtNode {

    public ExprNode expression = null;

    public ExprStmtNode(Position pos) {
        super(pos);
    }

    public ExprStmtNode(ExprNode expression,Position pos) {
        super(pos);
        this.expression=expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

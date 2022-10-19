package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class DotExprNode extends ExprNode {
    public ExprNode lhs;
    public ExprNode rhs;

    public DotExprNode(Position pos) {
        super(pos);
    }

    public DotExprNode(ExprNode lhs, ExprNode rhs, Position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class AssignExprNode extends ExprNode {

    public ExprNode lhs, rhs;

    public AssignExprNode(Position pos) {
        super(pos);
    }

    public AssignExprNode(ExprNode ls, ExprNode rs, Position pos) {
        super(pos);
        this.lhs = ls;
        this.rhs = rs;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

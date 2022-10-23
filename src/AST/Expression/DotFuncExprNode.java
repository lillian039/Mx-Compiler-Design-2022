package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class DotFuncExprNode extends ExprNode {
    public ExprNode lhs;
    public FuncExprNode rhs;

    public DotFuncExprNode(Position pos) {
        super(pos);
    }

    public DotFuncExprNode(ExprNode lhs, FuncExprNode rhs, Position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

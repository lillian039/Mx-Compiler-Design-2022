package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class BinaryExprNode extends ExprNode {

    public ExprNode ls, rs;

    public String option;

    public BinaryExprNode(Position pos) {
        super(pos);
    }

    public BinaryExprNode(ExprNode ls, ExprNode rs, String symbol, Position pos) {
        super(pos);
        this.ls = ls;
        this.rs = rs;
        this.option = symbol;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

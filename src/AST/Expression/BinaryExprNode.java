package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class BinaryExprNode extends ExprNode {

    public ExprNode ls,rs;

    public BinaryExprNode(Position pos) {
        super(pos);
    }

    public BinaryExprNode(ExprNode ls,ExprNode rs,Position pos) {
        super(pos);
        this.ls=ls;
        this.rs=rs;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class DelayCellExpr extends ExprNode {
    public ExprNode expression;
    public String option;
    public DelayCellExpr(Position pos){
        super(pos);
        isAssignable=true;
    }

    public DelayCellExpr(ExprNode expression,String option,Position pos){
        super(pos);
        this.expression=expression;
        this.option=option;
        super.isAssignable=false;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

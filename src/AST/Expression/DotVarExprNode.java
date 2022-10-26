package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class DotVarExprNode extends ExprNode {
    public ExprNode lhs;
    public ExprNode rhs;

    public DotVarExprNode(Position pos){
        super(pos);
        super.isAssignable=true;
    }

    public DotVarExprNode(ExprNode ls,ExprNode var,Position pos){
        super(pos);
        this.lhs=ls;
        this.rhs=var;
        isAssignable=true;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }


}

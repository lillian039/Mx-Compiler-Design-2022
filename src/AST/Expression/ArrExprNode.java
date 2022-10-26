package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

import java.util.ArrayList;

public class ArrExprNode extends ExprNode {
    public ExprNode ls;
    public ArrayList<ExprNode> arrDimension=new ArrayList<>();
    public ArrExprNode(Position pos){
        super(pos);
    }

/*    public ArrExprNode(String name, Position pos){
        super(pos);
        this.name=name;
    }*/

    @Override
    public boolean isAssignable() {
        return true;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

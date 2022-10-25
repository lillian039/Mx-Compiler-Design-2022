package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

import java.util.ArrayList;

public class ArrVarExprNode extends ExprNode {
    public String name;

    public ArrayList<ExprNode> arrDimension=new ArrayList<>();
    public ArrVarExprNode(Position pos){
        super(pos);
    }

    public ArrVarExprNode(String name,Position pos){
        super(pos);
        this.name=name;
    }

    @Override
    public boolean isAssignable() {
        return true;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

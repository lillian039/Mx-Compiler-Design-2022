package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.*;

public class ConstExprNode extends ExprNode {

    public Entity value;

    public ConstExprNode(Position pos) {
        super(pos);
    }

    public ConstExprNode(Type type,Position pos){
        super(pos);
        this.type=type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

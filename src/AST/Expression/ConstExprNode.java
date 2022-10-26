package AST.Expression;

import AST.ASTVisitor;
import AST.Atom.TypeNode;
import AST.ExprNode;
import Util.*;

public class ConstExprNode extends ExprNode {

    public Entity value;

    public ConstExprNode(Position pos) {
        super(pos);
    }

    public ConstExprNode(Type type,Entity value,Position pos){
        super(pos);
        this.type= new TypeNode(pos,type,false,false);
        this.value=value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

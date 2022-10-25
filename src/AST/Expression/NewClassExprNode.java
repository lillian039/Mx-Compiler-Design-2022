package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;
import Util.Type;

public class NewClassExprNode extends ExprNode {
    public NewClassExprNode(Position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

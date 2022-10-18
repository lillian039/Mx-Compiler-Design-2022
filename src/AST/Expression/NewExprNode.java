package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class NewExprNode extends ExprNode {
    public NewExprNode(Position pos){super(pos); }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

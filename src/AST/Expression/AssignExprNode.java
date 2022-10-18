package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class AssignExprNode extends ExprNode {
    public AssignExprNode(Position pos){super(pos); }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

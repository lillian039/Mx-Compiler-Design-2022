package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class VarExprNode extends ExprNode {
    String name;

    public VarExprNode(Position pos) {
        super(pos);
    }

    public VarExprNode(String name, Position pos) {
        super(pos);
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

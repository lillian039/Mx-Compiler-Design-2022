package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class CellExprNode extends ExprNode {

    public ExprNode expression;

    public String symbol;

    CellExprNode(Position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

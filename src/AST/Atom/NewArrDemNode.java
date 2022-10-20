package AST.Atom;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class NewArrDemNode extends ASTNode {
    public ExprNode arrDimension;
    public NewArrDemNode(Position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

package AST.Atom;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;
import Util.Type;

public class SingleVarDefNode extends ASTNode {
    public String name;
    public Type type;
    public TypeNode typeNode;

    public ExprNode expression=null;

    public SingleVarDefNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

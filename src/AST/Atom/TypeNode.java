package AST.Atom;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;
import Util.Type;

public class TypeNode extends ASTNode {
    public Type type;

    public TypeNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

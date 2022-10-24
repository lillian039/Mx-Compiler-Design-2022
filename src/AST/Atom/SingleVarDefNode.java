package AST.Atom;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;
import Util.Type;

public class SingleVarDefNode extends ASTNode {
    public String name;

    public TypeNode typeNode;

    public ExprNode expression=null;

    public SingleVarDefNode(Position pos) {
        super(pos);
        this.typeNode=new TypeNode(pos);
    }

    public SingleVarDefNode(Position pos,String name,TypeNode typeNode)
    {
        super(pos);
        this.name=name;
        this.typeNode=typeNode;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

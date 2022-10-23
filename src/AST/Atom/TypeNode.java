package AST.Atom;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;
import Util.Type;

import java.util.Vector;

public class TypeNode extends ASTNode {
    public Type type;

    public boolean isArr=false;

    public int layer=1;

    public Vector<ExprNode> arrLayerSize=null;

    public TypeNode(Position pos) {
        super(pos);
    }

    public TypeNode(Position pos,Type type,boolean isArr){
        super(pos);
        this.type=type;
        this.isArr=isArr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

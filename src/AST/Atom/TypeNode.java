package AST.Atom;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.ExprNode;
import LLVMIR.IRType.IRBaseType;
import Util.Position;
import Util.Type;

import java.util.Vector;

public class TypeNode extends ASTNode {
    public Type type;


    public boolean isArr = false;
    public int layer = 0;
    public int originLayer = 0;

    public TypeNode(Position pos) {
        super(pos);
        type = new Type();
    }

    public TypeNode(Position pos, Type type, boolean isArr) {
        super(pos);
        this.type = type;
        this.isArr = isArr;
    }

    public TypeNode(Position pos, TypeNode other) {
        super(pos);
        layer = originLayer = other.layer;
        isArr = other.isArr;
        type = other.type;
    }

    public TypeNode(String name) {
        super(new Position(0, 0));
        layer = 0;
        isArr = false;
        type = new Type(name);
    }

    public boolean sameType(TypeNode other) {
        return other.layer == this.layer && other.type == this.type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public boolean NotClass() {
        return type.name.equals("int") || type.name.equals("bool") || type.name.equals("string")
                || type.name.equals("void") || type.name.equals("null");
    }

}

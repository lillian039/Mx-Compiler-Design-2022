package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;
import Util.Type;

import java.util.ArrayList;
import java.util.Vector;

public class FuncExprNode extends ExprNode {
    public String name;
    public Type returnType;
    public ArrayList<Type> parameters;

    public FuncExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

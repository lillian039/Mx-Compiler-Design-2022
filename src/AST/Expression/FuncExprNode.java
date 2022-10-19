package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;
import Util.Type;

import java.util.ArrayList;

public class FuncExprNode extends ExprNode {
    public String name;
    public ArrayList<ExprNode> parameters=new ArrayList<>();

    public FuncExprNode(Position pos) {
        super(pos);
    }

    public FuncExprNode(String name,Type returnType,Position pos) {
        super(pos);
        this.name=name;
        this.type=returnType;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

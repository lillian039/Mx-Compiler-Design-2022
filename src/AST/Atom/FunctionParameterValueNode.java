package AST.Atom;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

import java.util.ArrayList;

public class FunctionParameterValueNode extends ASTNode {
    public ArrayList<ExprNode> parameters=new ArrayList<>();

    public FunctionParameterValueNode(Position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

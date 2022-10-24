package AST.Atom;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

import java.util.ArrayList;

public class FunctionParameterListNode extends ASTNode {
    public ArrayList<SingleVarDefNode> parameterList=new ArrayList<>();

    public FunctionParameterListNode(Position pos){
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public void add(SingleVarDefNode func){
        parameterList.add(func);
    }

    public boolean isEmpty(){
        return parameterList.isEmpty();
    }
}

package AST.Expression;

import AST.ASTVisitor;
import AST.Atom.NewArrDemNode;
import AST.Atom.TypeNode;
import AST.ExprNode;
import Util.Position;
import Util.Type;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;

public class NewArrNode extends ExprNode {
    public ArrayList<NewArrDemNode> arrList=new ArrayList<>();
    public NewArrNode(Position pos) {
        super(pos);
    }

    public NewArrNode(String type, Position pos) {
        super(pos);
        this.type=new TypeNode(pos,new Type(type),true);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

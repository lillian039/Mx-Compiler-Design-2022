package AST.Expression;

import AST.ASTVisitor;
import AST.Atom.NewArrDemNode;
import AST.Atom.TypeNode;
import AST.ExprNode;
import Util.Position;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;

public class NewExprNode extends ExprNode {
    public boolean newArr = false;
    public boolean newClass = false;

    public ArrayList<NewArrDemNode> arrList=new ArrayList<>();
    public TypeNode typeNode;

    public String typeName;

    public ArrayList<Pair<Integer,ExprNode>> layerSize=new ArrayList<>();

    public NewExprNode(Position pos) {
        super(pos);
    }

    public NewExprNode(String type, Position pos) {
        super(pos);
        this.typeName=type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

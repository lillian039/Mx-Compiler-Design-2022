package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;
import Util.Type;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;

public class NewExprNode extends ExprNode {
    public boolean newArr = false;
    public boolean newClass = false;

    public ArrayList<Pair<Integer,ExprNode>> layerSize=new ArrayList<>();

    public ArrayList<ExprNode> funcParaList;

    public NewExprNode(Position pos) {
        super(pos);
    }

    public NewExprNode(Type type, Position pos) {
        super(pos);
        this.type = type;
        if(type.isArr)newArr=true;
        else newClass=true;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

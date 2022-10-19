package AST.Statement;

import AST.ASTVisitor;
import AST.ExprNode;
import AST.StmtNode;
import Util.Position;
import Util.Type;
import org.antlr.v4.runtime.misc.Pair;

import java.util.Vector;


public class FunDefStmtNode extends StmtNode {
    public String name;

    public Vector<Pair<Type, ExprNode>> funcParameterList;

    public BlockStmtNode funcBody;
    public FunDefStmtNode(Position pos){super(pos); }

    public FunDefStmtNode(String name,Vector<Pair<Type, ExprNode>> funcParameterList,BlockStmtNode funcBody,Position pos){
        super(pos);
        this.name=name;
        this.funcParameterList=funcParameterList;
        this.funcBody=funcBody;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

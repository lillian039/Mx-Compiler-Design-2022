package AST.Statement;

import AST.ASTVisitor;
import AST.Atom.SingleVarDefNode;
import AST.Atom.TypeNode;
import AST.ExprNode;
import AST.StmtNode;
import Util.Position;
import Util.Type;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.Vector;


public class FunDefStmtNode extends StmtNode {
    public String name;

    public Type returnType;
    public TypeNode returnTypeNode;

    public ArrayList<SingleVarDefNode> parameterList;
    public ArrayList<Pair<Type, String>> funcParameterList;

    public BlockStmtNode funcBody;

    public FunDefStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

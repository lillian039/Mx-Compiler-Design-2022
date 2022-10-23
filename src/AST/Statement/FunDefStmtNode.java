package AST.Statement;

import AST.ASTVisitor;
import AST.Atom.SingleVarDefNode;
import AST.Atom.TypeNode;
import AST.StmtNode;
import Util.Position;

import java.util.ArrayList;


public class FunDefStmtNode extends StmtNode {
    public String name;
    public TypeNode returnTypeNode;
    public ArrayList<SingleVarDefNode> parameterList;

    public BlockStmtNode funcBody;

    public FunDefStmtNode(Position pos) {
        super(pos);
    }

    public FunDefStmtNode(Position pos,String name,TypeNode returnTypeNode) {
        super(pos);
        this.name=name;
        this.returnTypeNode=returnTypeNode;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

package AST.Statement;

import AST.ASTVisitor;
import AST.Atom.SingleVarDefNode;
import AST.Atom.TypeNode;
import AST.ExprNode;
import AST.StmtNode;
import Util.Position;
import Util.Type;

import java.util.ArrayList;

public class VarDefStmtNode extends StmtNode {
    public Type type;
    public TypeNode typeNode;
    public ArrayList<SingleVarDefNode> varDef=new ArrayList<>();
    public VarDefStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

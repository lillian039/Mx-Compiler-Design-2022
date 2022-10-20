package AST.Statement;

import AST.ASTVisitor;
import AST.StmtNode;
import Util.Position;

import java.util.ArrayList;

public class ClassDefStmtNode extends StmtNode {
    public String name;
    // public BlockStmtNode classBody;
    public ArrayList<VarDefStmtNode> memberDef = new ArrayList<>();
    public ArrayList<FunDefStmtNode> funcDef = new ArrayList<>();

    public BlockStmtNode classBody;

    public ClassDefStmtNode(Position pos) {
        super(pos);
    }

    public ClassDefStmtNode(String name, Position pos) {
        super(pos);
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

package AST;

import AST.Atom.SingleVarDefNode;
import AST.Statement.ClassDefStmtNode;
import AST.Statement.FunDefStmtNode;
import AST.Statement.VarDefStmtNode;
import Util.*;

import java.util.ArrayList;

public class RootNode extends ASTNode {

    public FunDefStmtNode mainNode;
    public ArrayList<FunDefStmtNode> funcDef = new ArrayList<>();
    public ArrayList<VarDefStmtNode> varDef = new ArrayList<>();
    public ArrayList<ClassDefStmtNode> classDef = new ArrayList<>();

    public RootNode( Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

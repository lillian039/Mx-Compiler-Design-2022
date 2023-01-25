package AST;

import AST.Atom.SingleVarDefNode;
import AST.Statement.ClassDefStmtNode;
import AST.Statement.FunDefStmtNode;
import AST.Statement.VarDefStmtNode;
import LLVMIR.GlobalDefine.ClassDef;
import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.GlobalDefine.VarDef;
import LLVMIR.IRType.ClassType;
import Util.*;

import java.util.ArrayList;
import java.util.HashMap;

public class RootNode extends ASTNode {

    public FunDefStmtNode mainNode;
    public ArrayList<FunDefStmtNode> funcDef = new ArrayList<>();
    public ArrayList<VarDefStmtNode> varDef = new ArrayList<>();
    public ArrayList<ClassDefStmtNode> classDef = new ArrayList<>();

    public ArrayList<StmtNode> statements = new ArrayList<>();



    public RootNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}

package AST;

import AST.Atom.SingleVarDefNode;
import AST.Statement.ClassDefStmtNode;
import AST.Statement.FunDefStmtNode;
import AST.Statement.VarDefStmtNode;
import LLVMIR.GlobalDefine.ClassDef;
import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.GlobalDefine.VarDef;
import Util.*;

import java.util.ArrayList;
import java.util.HashMap;

public class RootNode extends ASTNode {

    public FunDefStmtNode mainNode;
    public ArrayList<FunDefStmtNode> funcDef = new ArrayList<>();
    public ArrayList<VarDefStmtNode> varDef = new ArrayList<>();
    public ArrayList<ClassDefStmtNode> classDef = new ArrayList<>();

    ArrayList<FuncDef> funcDefs = new ArrayList<>();
    ArrayList<VarDef> varDefs = new ArrayList<>();
    ArrayList<ClassDef> classDefs = new ArrayList<>();

    HashMap<String, FuncDef> funcMap = new HashMap<>();
    HashMap<String, VarDef> varMap = new HashMap<>();
    HashMap<String, ClassDef> classMap = new HashMap<>();

    public ArrayList<StmtNode> statements = new ArrayList<>();

    public RootNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public void addFunc(FuncDef funcDef) {
        funcDefs.add(funcDef);
        funcMap.put(funcDef.name, funcDef);
    }

    public void addClass(ClassDef classDef) {
        classDefs.add(classDef);
        classMap.put(classDef.name, classDef);
    }

    public void addVar(VarDef varDef) {
        varDefs.add(varDef);
        varMap.put(varDef.name, varDef);
    }

    public FuncDef getFunc(String name) {
        return funcMap.get(name);
    }

    public ClassDef getClass(String name) {
        return classMap.get(name);
    }

    public VarDef getVar(String name) {
        return varMap.get(name);
    }
}

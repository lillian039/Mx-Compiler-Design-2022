package Util;

import AST.Atom.SingleVarDefNode;
import AST.Statement.FunDefStmtNode;
import AST.Statement.VarDefStmtNode;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;

public class Scope {
    private HashMap<String, SingleVarDefNode> variableMembers;//<name,type>
    private HashMap<String, FunDefStmtNode> funcMembers;//<name,type,layer>

    public boolean isClass;
    public boolean isFunc;
    public String name;
    public Type scopeType;

    public Scope parentScope;

    public Scope(Scope parentScope_) {
        funcMembers = new HashMap<>();
        variableMembers = new HashMap<>();
        parentScope = parentScope_;
    }

    public void makeClassScope(String scopeName) {
        name = scopeName;
        isClass = true;
        isFunc = false;
    }

    public void addVarDefine(String varName, SingleVarDefNode var) {
        if (variableMembers.containsKey(varName)) {
            System.out.println("err!");
        } else variableMembers.put(varName, var);
    }

    public void addFunDefine(String varName, FunDefStmtNode func) {
        if (funcMembers.containsKey(varName)) {
            System.out.println("err");
        } else funcMembers.put(varName, func);
    }

    public boolean funcNameValid(String name) {
        if (funcMembers.containsKey(name)) return false;
        else return true;
    }

    public SingleVarDefNode getVar(String name) {
        Scope now = this;
        while (now != null) {
            if (now.variableMembers.containsKey(name)) return now.variableMembers.get(name);
            now = now.parentScope;
        }
        return null;
    }

    public FunDefStmtNode getFun(String name) {
        Scope now = this;
        while (now != null) {
            if (now.funcMembers.containsKey(name)) return now.funcMembers.get(name);
            now = now.parentScope;
        }
        return null;
    }

    public boolean varNameValid(String name) {
        if (variableMembers.containsKey(name)) return false;
        else return true;
    }


}

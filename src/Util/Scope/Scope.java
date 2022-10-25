package Util.Scope;

import AST.Atom.SingleVarDefNode;
import AST.Statement.FunDefStmtNode;
import Util.Err.SemanticError;
import Util.Err.SyntaxError;
import Util.Type;

import java.util.HashMap;

abstract public class Scope {
    private HashMap<String, SingleVarDefNode> variableMembers;//<name,type>
    private HashMap<String, FunDefStmtNode> funcMembers;//<name,type,layer>

    public Scope parentScope;

    public Scope(Scope parentScope_) {
        funcMembers = new HashMap<>();
        variableMembers = new HashMap<>();
        parentScope = parentScope_;
    }

    public void addVarDefine(String varName, SingleVarDefNode var) {
        if (variableMembers.containsKey(varName))throw new SemanticError("variable name exist",var.pos);
        else variableMembers.put(varName, var);
    }

    public void addFunDefine(String varName, FunDefStmtNode func) {
        if (funcMembers.containsKey(varName)) {
            throw new SyntaxError("Func name already exist", func.pos);
        } else funcMembers.put(varName, func);
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

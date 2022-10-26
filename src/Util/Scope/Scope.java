package Util.Scope;

import AST.Atom.SingleVarDefNode;
import AST.Atom.TypeNode;
import AST.Statement.FunDefStmtNode;
import Util.Err.SemanticError;
import Util.Err.SyntaxError;
import Util.Type;

import java.util.HashMap;

abstract public class Scope {
    protected HashMap<String, SingleVarDefNode> variableMembers;//<name,type>
    protected HashMap<String, FunDefStmtNode> funcMembers;//<name,type,layer>

    public Scope parentScope;

    public Scope(Scope parentScope_) {
        funcMembers = new HashMap<>();
        variableMembers = new HashMap<>();
        parentScope = parentScope_;
    }

    public void addVarDefine(String varName, SingleVarDefNode var) {
        if (variableMembers.containsKey(varName)||funcMembers.containsKey(varName))throw new SemanticError("variable name exist",var.pos);
        else variableMembers.put(varName, var);
    }

    public void addFunDefine(String varName, FunDefStmtNode func) {
        if (variableMembers.containsKey(varName)||funcMembers.containsKey(varName)) {
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

    public FunDefStmtNode getFunc(String name) {
        Scope now = this;
        while (now != null) {
            if (now.funcMembers.containsKey(name)) return now.funcMembers.get(name);
            now = now.parentScope;
        }
        return null;
    }

    public TypeNode getFuncTypeNode(){
        Scope now=this;
        while(now !=null){
            if(now instanceof FuncScope)return ((FuncScope)now).returnType;
            now=now.parentScope;
        }
        return null;
    }

    public boolean inLoop(){
        Scope now=this;
        while (now!=null){
            if(now instanceof LoopScope)return true;
            now=now.parentScope;
        }
        return false;
    }

    public Scope GetCurrentFuncScope(){
        Scope now=this;
        while (now!=null){
            if(now instanceof FuncScope||now instanceof LambdaScope)return now;
            now=now.parentScope;
        }
        return null;
    }

}

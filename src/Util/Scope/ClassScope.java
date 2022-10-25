package Util.Scope;

import AST.Atom.SingleVarDefNode;
import AST.Statement.ClassDefStmtNode;
import AST.Statement.FunDefStmtNode;
import Util.Type;

public class ClassScope extends Scope {

    public Type ClassType;

    public ClassScope(Scope parentScope,Type classType) {
        super(parentScope);
        this.ClassType=classType;
        for (FunDefStmtNode func : ClassType.classDef.funcDef.values()) {
            this.addFunDefine(func.name, func);
        }
        for (SingleVarDefNode var : ClassType.classDef.memberDef.values()) {
            this.addVarDefine(var.name,var);
        }
    }
}

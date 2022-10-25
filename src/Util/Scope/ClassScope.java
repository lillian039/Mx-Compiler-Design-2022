package Util.Scope;

import AST.Statement.ClassDefStmtNode;
import Util.Type;

public class ClassScope extends Scope {

    public Type ClassType;

    public ClassScope(Scope parentScope) {
        super(parentScope);
    }
}

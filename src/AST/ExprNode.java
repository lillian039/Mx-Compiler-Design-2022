package AST;

import Util.Type;

public abstract class ExprNode extends ASTNode{
    private String type;

    public ExprNode(){ }

    public boolean isAssignable(){ return false; }

}

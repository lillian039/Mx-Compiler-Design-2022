package AST;

import Util.*;

import java.util.ArrayList;

public abstract class ExprNode extends ASTNode{
    public Type type;

    public ExprNode(Position pos){super(pos); }

    public boolean isAssignable(){ return false; }

}

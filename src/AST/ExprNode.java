package AST;

import AST.Atom.TypeNode;
import LLVMIR.Value.IRValue;
import Util.*;

import java.util.ArrayList;

public abstract class ExprNode extends ASTNode{

    public TypeNode type;

    public Entity value;

    public IRValue irValue;

    public boolean isAssignable=false;

    public ExprNode(Position pos){super(pos); }

    public boolean isAssignable(){ return isAssignable; }

}

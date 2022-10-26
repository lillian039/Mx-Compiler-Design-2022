package AST;

import AST.Atom.TypeNode;
import Util.*;

import java.util.ArrayList;

public abstract class ExprNode extends ASTNode{

    public TypeNode type;

    public Entity value;

    public ExprNode(Position pos){super(pos); }


}

package AST;
import Util.*;

abstract public class ASTNode {
    public Position pos;

    public Entity value;

    public ASTNode(Position pos){this.pos=pos;}
    abstract public void accept(ASTVisitor visitor);

}
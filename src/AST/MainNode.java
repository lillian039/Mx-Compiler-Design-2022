package AST;
import Util.*;
public class MainNode extends ASTNode{
    MainNode (Position pos){
        super(pos);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

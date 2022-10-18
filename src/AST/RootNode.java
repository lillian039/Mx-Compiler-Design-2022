package AST;
import Util.*;
public class RootNode extends ASTNode {
    RootNode (Position pos){
        super(pos);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

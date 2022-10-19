package AST;
import Util.*;

import java.util.ArrayList;

public class RootNode extends ASTNode {

    public MainNode mainNode;

    public ArrayList<StmtNode> definitions;
    RootNode (MainNode mainNode,Position pos){
        super(pos);
        this.mainNode=mainNode;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

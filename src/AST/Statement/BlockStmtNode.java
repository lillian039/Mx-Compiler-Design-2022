package AST.Statement;

import AST.ASTVisitor;
import AST.StmtNode;
import Util.*;

import java.util.ArrayList;

public class BlockStmtNode extends StmtNode {
    public ArrayList<StmtNode> statements=new ArrayList<>();
    public BlockStmtNode(Position pos){ super(pos); }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

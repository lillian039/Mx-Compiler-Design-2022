package AST.Statement;

import AST.ASTVisitor;
import AST.StmtNode;
import Util.Position;

public class FunDefStmtNode extends StmtNode {
    public FunDefStmtNode(Position pos){super(pos); }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

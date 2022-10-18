package AST.Statement;

import AST.ASTVisitor;
import AST.ExprNode;
import AST.StmtNode;
import Util.Position;

public class WhileStmtNode extends StmtNode {
    public ExprNode condition;
    public StmtNode body;
    public WhileStmtNode(Position pos){super(pos); }

    public WhileStmtNode(ExprNode condition,StmtNode body,Position pos){
        super(pos);
        this.condition=condition;
        this.body=body;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

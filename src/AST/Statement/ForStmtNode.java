package AST.Statement;

import AST.ASTVisitor;
import AST.ExprNode;
import AST.StmtNode;
import Util.Position;

public class ForStmtNode extends StmtNode {

    public StmtNode initializeStmt = null;

    public ExprNode conditionNode = null, stepNode = null;

    public StmtNode body = null;

    public ForStmtNode(Position pos) {
        super(pos);
    }

    public ForStmtNode(StmtNode initializeStmt, ExprNode conditionNode, ExprNode stepNode, StmtNode body, Position pos) {
        super(pos);
        this.initializeStmt=initializeStmt;
        this.conditionNode=conditionNode;
        this.stepNode=stepNode;
        this.body=body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

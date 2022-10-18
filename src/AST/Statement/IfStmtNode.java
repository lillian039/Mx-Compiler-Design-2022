package AST.Statement;

import AST.*;
import Util.Position;

public class IfStmtNode extends StmtNode {
    public ExprNode condition=null;
    public StmtNode thenStmt=null;
    public StmtNode elseStmt=null;
    public IfStmtNode(Position pos){super(pos); }

    public IfStmtNode(Position pos,ExprNode condition,StmtNode thenStmt){
        super(pos);
        this.condition=condition;
        this.thenStmt=thenStmt;
    }

    public IfStmtNode(Position pos,ExprNode condition,StmtNode thenStmt,StmtNode elseStmt){
        super(pos);
        this.condition=condition;
        this.thenStmt=thenStmt;
        this.elseStmt=elseStmt;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

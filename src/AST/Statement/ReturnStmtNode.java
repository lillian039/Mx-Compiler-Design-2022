package AST.Statement;

import AST.*;
import Util.*;

public class ReturnStmtNode extends StmtNode {
    public ExprNode returnExpr;
    public Type returnType;
    public ReturnStmtNode(Position pos) {
        super(pos);
    }

    public ReturnStmtNode(ExprNode returnExpr,Position pos) {
        super(pos);
        this.returnExpr=returnExpr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
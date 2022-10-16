package AST;

public interface ASTVisitor {

    void visit(binaryBoolExprNode node);

    void visit(binaryCmpExprNode node);

    void visit(binaryIntExprNode node);
    void visit(StmtNode node);


}

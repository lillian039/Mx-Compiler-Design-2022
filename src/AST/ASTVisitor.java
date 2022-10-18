package AST;

public interface ASTVisitor {
    void visit(binaryBoolExprNode node);
    void visit(binaryCmpExprNode node);
    void visit(newExprNode node);
    void visit(binaryIntExprNode node);
    void visit(varDefStmtNode node);


}

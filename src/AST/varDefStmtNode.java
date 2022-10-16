package AST;

public class varDefStmtNode extends StmtNode{

    @Override
    public void selfVisit(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

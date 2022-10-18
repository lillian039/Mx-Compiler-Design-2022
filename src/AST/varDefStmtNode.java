package AST;

public class varDefStmtNode extends StmtNode{

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

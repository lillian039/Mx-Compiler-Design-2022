package AST;

public class binaryIntExprNode extends ExprNode{
    public int value;
    public binaryIntExprNode ls,rs;
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

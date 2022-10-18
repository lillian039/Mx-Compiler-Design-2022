package AST;

abstract public class ASTNode {
    abstract public void accept(ASTVisitor visitor);

}
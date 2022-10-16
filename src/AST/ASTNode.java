package AST;

abstract public class ASTNode {
    abstract public void selfVisit(ASTVisitor visitor);

}
package Frontend;
import AST.*;
import Util.GlobalScope;
import Util.Scope;

public class SemanticChecker implements ASTVisitor{

    private Scope currentScope;

    private GlobalScope gScope;

    public SemanticChecker(GlobalScope gScope_){currentScope=gScope=gScope_; }
    @Override
    public void visit(newExprNode node) {

    }

    @Override
    public void visit(varDefStmtNode node) {

    }

    @Override
    public void visit(binaryCmpExprNode node) {

    }

    @Override
    public void visit(binaryIntExprNode node) {

    }

    @Override
    public void visit(binaryBoolExprNode node) {

    }
}

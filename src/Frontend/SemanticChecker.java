package Frontend;
import AST.*;

import AST.Expression.*;
import AST.Statement.*;
import Util.GlobalScope;
import Util.Scope;

public class SemanticChecker implements ASTVisitor{

    private Scope currentScope;

    private GlobalScope gScope;

    public SemanticChecker(GlobalScope gScope_){currentScope=gScope=gScope_; }

    @Override
    public void visit(RootNode node) {

    }

    @Override
    public void visit(MainNode node) {

    }

    @Override
    public void visit(AssignExprNode node) {

    }

    @Override
    public void visit(NewExprNode node) {

    }

    @Override
    public void visit(DelayCellExpr node) {

    }

    @Override
    public void visit(CellExprNode node) {

    }

    @Override
    public void visit(BinaryExprNode node) {

    }

    @Override
    public void visit(ConstExprNode node) {

    }

    @Override
    public void visit(FuncExprNode node) {

    }

    @Override
    public void visit(DotExprNode node) {

    }

    @Override
    public void visit(BlockStmtNode node) {

    }

    @Override
    public void visit(VarDefStmtNode node) {

    }

    @Override
    public void visit(FunDefStmtNode node) {

    }

    @Override
    public void visit(ClassDefStmtNode node) {

    }

    @Override
    public void visit(IfStmtNode node) {

    }

    @Override
    public void visit(ForStmtNode node) {

    }

    @Override
    public void visit(WhileStmtNode node) {

    }

    @Override
    public void visit(CtrlStmtNode node) {

    }

    @Override
    public void visit(ExprStmtNode node) {

    }

    @Override
    public void visit(ReturnStmtNode node) {

    }


}

package AST;
import AST.Expression.*;
import AST.Statement.*;
public interface ASTVisitor {
    void visit(RootNode node);

    void visit(MainNode node);

    void visit(NewExprNode node);

    void visit(BinaryExprNode node);

    void visit(CellExprNode node);

    void visit(ConstExprNode node);

    void visit(FuncExprNode node);

    void visit(DotExprNode node);

    void visit(AssignExprNode node);

    void visit(BlockStmtNode node);

    void visit(ClassDefStmtNode node);

    void visit (CtrlStmtNode node);

    void visit(ExprStmtNode node);

    void visit(ForStmtNode node);

    void visit(FunDefStmtNode node);

    void visit(IfStmtNode node);

    void visit(ReturnStmtNode node);

    void visit(VarDefStmtNode node);

    void visit(WhileStmtNode node);

}

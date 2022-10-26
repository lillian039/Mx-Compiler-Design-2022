package AST;
import AST.Atom.*;
import AST.Expression.*;
import AST.Statement.*;
public interface ASTVisitor {
    void visit(RootNode node);

    void visit(NewArrNode node);

    void visit(BinaryExprNode node);

    void visit(CellExprNode node);

    void visit(ConstExprNode node);

    void visit(FuncExprNode node);

    void visit(DotFuncExprNode node);

    void visit(AssignExprNode node);

    void visit(VarExprNode node);

    void visit(DelayCellExpr node);

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

    void visit(SingleVarDefNode node);

    void visit(TypeNode node);

    void visit(NewArrDemNode node);

    void visit(DotVarExprNode node);
    void visit(LambdaExprNode node);

    void visit(FunctionParameterListNode node);

    void visit(FunctionParameterValueNode node);

    void visit(NewClassExprNode node);

    void visit(ArrExprNode node);

}

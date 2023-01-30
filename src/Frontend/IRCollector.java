package Frontend;

import AST.ASTVisitor;
import AST.Atom.*;
import AST.Expression.*;
import AST.RootNode;
import AST.Statement.*;
import LLVMIR.IRType.IntType;
import LLVMIR.IRType.NullType;
import LLVMIR.IRType.VoidType;
import Util.Scope.GlobalScope;

public class IRCollector implements ASTVisitor {
    private GlobalScope gScope;

    public IRCollector(GlobalScope gScope) {
        this.gScope = gScope;
    }

    @Override
    public void visit(RootNode node) {
        node.mainNode.accept(this);
        for (var classDef : node.classDef) {
            classDef.accept(this);
        }
        for (var varDef : node.varDef) {
            varDef.accept(this);
        }

        for (var funcDef : node.funcDef) {
            funcDef.accept(this);
        }
    }

    @Override
    public void visit(NewArrNode node) {
        for (var dem : node.arrList) {
            dem.accept(this);
        }
    }

    @Override
    public void visit(BinaryExprNode node) {
        node.ls.accept(this);
        node.rs.accept(this);
    }

    @Override
    public void visit(CellExprNode node) {
        node.expression.accept(this);
    }

    @Override
    public void visit(ConstExprNode node) {
        if (node.value.isStringVal) {
            gScope.addString(node.value.stringVal);
        }
    }

    @Override
    public void visit(FuncExprNode node) {
        if (node.parameterValueNode != null) node.parameterValueNode.accept(this);
    }

    @Override
    public void visit(DotFuncExprNode node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
    }

    @Override
    public void visit(AssignExprNode node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
    }

    @Override
    public void visit(VarExprNode node) {

    }

    @Override
    public void visit(DelayCellExpr node) {

    }

    @Override
    public void visit(BlockStmtNode node) {
        if (node.statements != null) {
            for (var stmt : node.statements) {
                stmt.accept(this);
            }
        }
    }

    @Override
    public void visit(ClassDefStmtNode node) {
        for (var funcDef : node.funcDef.values()) {
            funcDef.accept(this);
        }
        for (var varDef : node.memberDef.values()) {
            varDef.accept(this);
        }

        if (node.constructor != null) node.constructor.accept(this);
    }

    @Override
    public void visit(CtrlStmtNode node) {

    }

    @Override
    public void visit(ExprStmtNode node) {
        node.expression.accept(this);
    }

    @Override
    public void visit(ForStmtNode node) {
        if (node.initializeStmt != null) node.initializeStmt.accept(this);
        if (node.conditionNode != null) node.conditionNode.accept(this);
        if (node.stepNode != null) node.stepNode.accept(this);
        if (node.body != null) node.body.accept(this);
    }

    @Override
    public void visit(FunDefStmtNode node) {
        if (node.parameterList != null) node.parameterList.accept(this);
        node.funcBody.accept(this);
    }

    @Override
    public void visit(IfStmtNode node) {
        node.condition.accept(this);
        if (node.thenStmt != null) node.thenStmt.accept(this);
        if (node.elseStmt != null) node.elseStmt.accept(this);
    }

    @Override
    public void visit(ReturnStmtNode node) {
        if (node.returnExpr != null) node.returnExpr.accept(this);
    }

    @Override
    public void visit(VarDefStmtNode node) {
        for (var varDef : node.varDef) {
            varDef.accept(this);
        }

    }

    @Override
    public void visit(WhileStmtNode node) {
        node.condition.accept(this);
        if (node.body != null) node.body.accept(this);
    }

    @Override
    public void visit(SingleVarDefNode node) {
        if (node.expression != null) node.expression.accept(this);
    }

    @Override
    public void visit(TypeNode node) {

    }

    @Override
    public void visit(NewArrDemNode node) {
        if (node.arrDimension != null) node.arrDimension.accept(this);
    }

    @Override
    public void visit(DotVarExprNode node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
    }

    @Override
    public void visit(LambdaExprNode node) {

    }

    @Override
    public void visit(FunctionParameterListNode node) {
        for (var varDef : node.parameterList) {
            varDef.accept(this);
        }
    }

    @Override
    public void visit(FunctionParameterValueNode node) {
        for (var para : node.parameters) {
            para.accept(this);
        }

    }

    @Override
    public void visit(NewClassExprNode node) {

    }

    @Override
    public void visit(ArrExprNode node) {
        for (var expr : node.arrDimension) {
            expr.accept(this);
        }
    }


}

package Frontend;

import AST.*;
import AST.Expression.*;
import Util.GlobalScope;
import Util.Position;
import Util.Type;
import Util.Entity;
import gen.MxBaseVisitor;
import gen.MxParser;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
    private GlobalScope gScope;

    public Type intType,boolType,stringType,voidType,nullType;


    public ASTBuilder(GlobalScope gScope_){gScope=gScope_;}

    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        RootNode rootNode=new RootNode((MainNode)visit(ctx.mainfunc()),new Position(ctx));
        ctx.definition().forEach(cd->rootNode.definitions.add((StmtNode)visit(cd)));
        return rootNode;
    }

    @Override
    public ASTNode visitMainfunc(MxParser.MainfuncContext ctx) {
        MainNode mainNode=new MainNode(new Position(ctx));
        intType=mainNode.intType;
        boolType=mainNode.boolType;
        voidType=mainNode.voidType;
        stringType=mainNode.stringType;
        nullType.isNull=true;
        ctx.suite().statement().forEach(cd->mainNode.stmts.add((StmtNode)visit(cd)));
        return mainNode;
    }

    @Override
    public ASTNode visitAtomExpr(MxParser.AtomExprContext ctx) {
        return visit(ctx.primary());
    }

    @Override
    public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
        Position pos=new Position(ctx);
        ExprNode ls=(ExprNode)visit(ctx.expression(0));
        ExprNode rs=(ExprNode)visit(ctx.expression(1));
        String op=ctx.op.toString();
        //System.out.println(op);
        return new BinaryExprNode(ls,rs,op,pos);
    }

    @Override
    public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
        Position pos=new Position(ctx);
        ExprNode ls=(ExprNode)visit(ctx.expression(0));
        ExprNode rs=(ExprNode)visit(ctx.expression(1));
        return new AssignExprNode(ls,rs,pos);
    }

    @Override
    public ASTNode visitDotExpr(MxParser.DotExprContext ctx) {
        Position pos=new Position(ctx);
        ExprNode ls=(ExprNode)visit(ctx.expression(0));
        ExprNode rs=(ExprNode)visit(ctx.expression(1));
        return new DotExprNode(ls,rs,pos);
    }

    @Override
    public ASTNode visitBracketExpr(MxParser.BracketExprContext ctx) {
        return visit(ctx.expression());
    }


    @Override
    public ASTNode visitCellExpr(MxParser.CellExprContext ctx) {
        ExprNode exprNode=(ExprNode)visit(ctx.expression());
        String op=ctx.op.toString();
        return new CellExprNode(exprNode,op,new Position(ctx));
    }

    @Override
    public ASTNode visitDelayCellExpr(MxParser.DelayCellExprContext ctx) {
        ExprNode exprNode=(ExprNode)visit(ctx.expression());
        String op=ctx.op.toString();
        return new DelayCellExpr(exprNode,op,new Position(ctx));
    }

    @Override
    public ASTNode visitPrimary(MxParser.PrimaryContext ctx) {
        if(ctx.constant()!=null)return visit(ctx.constant());
        else if(ctx.variable()!=null)return visit(ctx.variable());
        else return visit(ctx.funVal());
    }

    //one of primary
    @Override
    public ASTNode visitConstant(MxParser.ConstantContext ctx) {
        Position pos=new Position(ctx);
        if(ctx.FALSE()!=null)return new ConstExprNode(boolType,new Entity(false),pos);
        else if(ctx.TRUE()!=null)return new ConstExprNode(boolType,new Entity(true),pos);
        else if(ctx.INTEGER()!=null)return new ConstExprNode(intType,new Entity(Integer.parseInt(ctx.INTEGER().toString())),pos);
        else if(ctx.STRING()!=null)return new ConstExprNode(stringType,new Entity(ctx.STRING().toString()),pos);
        else return new ConstExprNode(nullType,new Entity(),pos);
    }

    @Override
    public ASTNode visitFunVal(MxParser.FunValContext ctx) {
        return super.visitFunVal(ctx);
    }

    @Override
    public ASTNode visitCallfunction(MxParser.CallfunctionContext ctx) {
        return super.visitCallfunction(ctx);
    }

    @Override
    public ASTNode visitLamdaExpr(MxParser.LamdaExprContext ctx) {
        return super.visitLamdaExpr(ctx);
    }

    @Override
    public ASTNode visitVariable(MxParser.VariableContext ctx) {
        return super.visitVariable(ctx);
    }


    @Override
    public ASTNode visitNewExpr(MxParser.NewExprContext ctx) {
        return super.visitNewExpr(ctx);
    }

    @Override
    public ASTNode visitNewArrExpr(MxParser.NewArrExprContext ctx) {
        return super.visitNewArrExpr(ctx);
    }

    @Override
    public ASTNode visitNewClassExpr(MxParser.NewClassExprContext ctx) {
        return super.visitNewClassExpr(ctx);
    }

    @Override
    public ASTNode visitArrayelement(MxParser.ArrayelementContext ctx) {
        return super.visitArrayelement(ctx);
    }

    @Override
    public ASTNode visitVardefineStmt(MxParser.VardefineStmtContext ctx) {
        return super.visitVardefineStmt(ctx);
    }

    @Override//branch of vardefineStmt
    public ASTNode visitArrayvar(MxParser.ArrayvarContext ctx) {
        return super.visitArrayvar(ctx);
    }

    @Override//another branch of vardefineStmt
    public ASTNode visitSignalvar(MxParser.SignalvarContext ctx) {
        return super.visitSignalvar(ctx);
    }

    @Override
    public ASTNode visitClassdefineStmt(MxParser.ClassdefineStmtContext ctx) {
        return super.visitClassdefineStmt(ctx);
    }

    @Override
    public ASTNode visitCtrlStmt(MxParser.CtrlStmtContext ctx) {
        return super.visitCtrlStmt(ctx);
    }

    @Override
    public ASTNode visitExprStmt(MxParser.ExprStmtContext ctx) {
        return super.visitExprStmt(ctx);
    }

    @Override
    public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
        return super.visitEmptyStmt(ctx);
    }

    @Override
    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        return super.visitForStmt(ctx);
    }

    @Override
    public ASTNode visitFuncdefineStmt(MxParser.FuncdefineStmtContext ctx) {
        return super.visitFuncdefineStmt(ctx);
    }

    @Override
    public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        return super.visitIfStmt(ctx);
    }

    @Override
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        return super.visitWhileStmt(ctx);
    }

    @Override
    public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        return super.visitReturnStmt(ctx);
    }

    @Override
    public ASTNode visitBlock(MxParser.BlockContext ctx) {
        return super.visitBlock(ctx);
    }

    @Override
    public ASTNode visitSuite(MxParser.SuiteContext ctx) {
        return super.visitSuite(ctx);
    }

    @Override
    public ASTNode visitFunctionParameterList(MxParser.FunctionParameterListContext ctx) {
        return super.visitFunctionParameterList(ctx);
    }

    @Override
    public ASTNode visitFunctionParameterValue(MxParser.FunctionParameterValueContext ctx) {
        return super.visitFunctionParameterValue(ctx);
    }

    @Override
    public ASTNode visitClassdefine(MxParser.ClassdefineContext ctx) {
        return super.visitClassdefine(ctx);
    }

    @Override
    public ASTNode visitFuncdefine(MxParser.FuncdefineContext ctx) {
        return super.visitFuncdefine(ctx);
    }

    @Override
    public ASTNode visitReturnType(MxParser.ReturnTypeContext ctx) {
        return super.visitReturnType(ctx);
    }

    @Override
    public ASTNode visitType(MxParser.TypeContext ctx) {
        return super.visitType(ctx);
    }
}

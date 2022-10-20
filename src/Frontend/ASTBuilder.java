package Frontend;

import AST.*;
import AST.Atom.SingleVarDefNode;
import AST.Atom.TypeNode;
import AST.Expression.*;
import AST.Statement.*;
import Util.GlobalScope;
import Util.Position;
import Util.Type;
import Util.Entity;
import gen.MxBaseVisitor;
import gen.MxParser;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
    private GlobalScope gScope;

    public Type intType, boolType, stringType, voidType, nullType;


    public ASTBuilder(GlobalScope gScope_) {
        gScope = gScope_;
    }

    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        RootNode rootNode = new RootNode((MainNode) visit(ctx.mainfunc()), new Position(ctx));
        for (var def : ctx.definition()) {
            if (def.children instanceof MxParser.ClassdefineContext) {
                rootNode.definitions.add((ClassDefStmtNode) visit(def));
            } else if (def.children instanceof MxParser.VardefContext) {
                rootNode.definitions.add((VarDefStmtNode) visit(def));
            } else if (def.children instanceof MxParser.FuncdefineContext) {
                rootNode.definitions.add((FunDefStmtNode) visit(def));
            }
        }
        //  ctx.definition().forEach(cd->rootNode.definitions.add((StmtNode)visit(cd)));
        return rootNode;
    }

    @Override
    public ASTNode visitMainfunc(MxParser.MainfuncContext ctx) {
        MainNode mainNode = new MainNode(new Position(ctx));
        intType = mainNode.intType;
        boolType = mainNode.boolType;
        voidType = mainNode.voidType;
        stringType = mainNode.stringType;
        nullType.isNull = true;
        ctx.suite().statement().forEach(cd -> mainNode.stmts.add((StmtNode) visit(cd)));
        return mainNode;
    }

    @Override
    public ASTNode visitAtomExpr(MxParser.AtomExprContext ctx) {
        return visit(ctx.primary());
    }

    @Override
    public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
        Position pos = new Position(ctx);
        ExprNode ls = (ExprNode) visit(ctx.expression(0));
        ExprNode rs = (ExprNode) visit(ctx.expression(1));
        String op = ctx.op.toString();
        //System.out.println(op);
        return new BinaryExprNode(ls, rs, op, pos);
    }

    @Override
    public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
        Position pos = new Position(ctx);
        ExprNode ls = (ExprNode) visit(ctx.expression(0));
        ExprNode rs = (ExprNode) visit(ctx.expression(1));
        return new AssignExprNode(ls, rs, pos);
    }

    @Override
    public ASTNode visitDotExpr(MxParser.DotExprContext ctx) {
        Position pos = new Position(ctx);
        ExprNode ls = (ExprNode) visit(ctx.expression(0));
        ExprNode rs = (ExprNode) visit(ctx.expression(1));
        return new DotExprNode(ls, rs, pos);
    }

    @Override
    public ASTNode visitBracketExpr(MxParser.BracketExprContext ctx) {
        return visit(ctx.expression());
    }


    @Override
    public ASTNode visitCellExpr(MxParser.CellExprContext ctx) {
        ExprNode exprNode = (ExprNode) visit(ctx.expression());
        String op = ctx.op.toString();
        return new CellExprNode(exprNode, op, new Position(ctx));
    }

    @Override
    public ASTNode visitDelayCellExpr(MxParser.DelayCellExprContext ctx) {
        ExprNode exprNode = (ExprNode) visit(ctx.expression());
        String op = ctx.op.toString();
        return new DelayCellExpr(exprNode, op, new Position(ctx));
    }

    @Override
    public ASTNode visitPrimary(MxParser.PrimaryContext ctx) {
        if (ctx.constant() != null) return visit(ctx.constant());
        else if (ctx.variable() != null) return visit(ctx.variable());
        else return visit(ctx.funVal());
    }

    //one of primary
    @Override
    public ASTNode visitConstant(MxParser.ConstantContext ctx) {
        Position pos = new Position(ctx);
        if (ctx.FALSE() != null) return new ConstExprNode(boolType, new Entity(false), pos);
        else if (ctx.TRUE() != null) return new ConstExprNode(boolType, new Entity(true), pos);
        else if (ctx.INTEGER() != null)
            return new ConstExprNode(intType, new Entity(Integer.parseInt(ctx.INTEGER().toString())), pos);
        else if (ctx.STRING() != null) return new ConstExprNode(stringType, new Entity(ctx.STRING().toString()), pos);
        else return new ConstExprNode(nullType, new Entity(), pos);
    }

    @Override
    public ASTNode visitFunVal(MxParser.FunValContext ctx) {
        if (ctx.callfunction() != null) return visit(ctx.callfunction());
        else return visit(ctx.lamdaExpr());

    }

    @Override
    public ASTNode visitCallfunction(MxParser.CallfunctionContext ctx) {
        Position pos = new Position(ctx);
        FuncExprNode funct = new FuncExprNode(ctx.IDENTIFIER().toString(), pos);
        for (var exp : ctx.functionParameterValue().children) {
            if (exp instanceof MxParser.ExpressionContext) {
                funct.parameters.add((ExprNode) visit(exp));
            }
        }
        return funct;
    }

    @Override
    public ASTNode visitLamdaExpr(MxParser.LamdaExprContext ctx) {
        Position pos = new Position(ctx);
        FuncExprNode lambda = new FuncExprNode(pos);
        lambda.isLambda = true;
        if (ctx.And() != null) lambda.isGlobe = true;
        for (var def : ctx.functionParameterList().children) {
            if (def instanceof MxParser.ParalistVarDefContext) {
                lambda.funcParameterList.add((SingleVarDefNode) visit(def));
            }
        }
        for (var exp : ctx.functionParameterValue().children) {
            if (exp instanceof MxParser.ExpressionContext) {
                lambda.parameters.add((ExprNode) visit(exp));
            }
        }
        lambda.funcBody = (BlockStmtNode) visit(ctx.suite());
        return lambda;
    }

    @Override
    public ASTNode visitVariable(MxParser.VariableContext ctx) {
        return new VarExprNode(ctx.IDENTIFIER().toString(), new Position(ctx));
    }


    @Override
    public ASTNode visitNewExpr(MxParser.NewExprContext ctx) {
        if (ctx.newArrExpr() != null) return visit(ctx.newArrExpr());
        else return visit(ctx.newClassExpr());
    }

    @Override
    public ASTNode visitNewArrExpr(MxParser.NewArrExprContext ctx) {
        String typename;
        if (ctx.IDENTIFIER() != null) typename = ctx.IDENTIFIER().toString();
        else if (ctx.INT() != null) typename = ctx.INT().toString();
        else if (ctx.BOOL() != null) typename = ctx.BOOL().toString();
        else typename = ctx.STR().toString();
        NewExprNode newExprNode = new NewExprNode(typename, new Position(ctx));
        newExprNode.newArr = true;
        ////TODO
        return super.visitNewArrExpr(ctx);
    }

    @Override
    public ASTNode visitNewClassExpr(MxParser.NewClassExprContext ctx) {
        return super.visitNewClassExpr(ctx);
    }

    @Override
    public ASTNode visitArrayelement(MxParser.ArrayelementContext ctx) {
        int num = ctx.expression().size();
        return super.visitArrayelement(ctx);
    }

    @Override
    public ASTNode visitVardefineStmt(MxParser.VardefineStmtContext ctx) {
        return visit(ctx.vardef());
    }

    @Override
    public ASTNode visitVardef(MxParser.VardefContext ctx) {
        VarDefStmtNode varDefStmtNode = new VarDefStmtNode(new Position(ctx));
        varDefStmtNode.typeNode = (TypeNode) visit(ctx.type());
        for (var def : ctx.singleVarDef()) {
            varDefStmtNode.varDef.add((SingleVarDefNode) visit(def));
        }
        return varDefStmtNode;
    }

    @Override
    public ASTNode visitSingleVarDef(MxParser.SingleVarDefContext ctx) {
        SingleVarDefNode singleVarDefNode = new SingleVarDefNode(new Position(ctx));
        singleVarDefNode.name = ctx.IDENTIFIER().toString();
        if (ctx.expression() != null) singleVarDefNode.expression = (ExprNode) visit(ctx.expression());

        return singleVarDefNode;
    }

    @Override
    public ASTNode visitClassdefineStmt(MxParser.ClassdefineStmtContext ctx) {
        return visit(ctx.classdefine());
    }

    @Override
    public ASTNode visitCtrlStmt(MxParser.CtrlStmtContext ctx) {
        CtrlStmtNode ctrlStmtNode = new CtrlStmtNode(new Position(ctx));
        if (ctx.BREAK() != null) ctrlStmtNode.isBreak = true;
        else ctrlStmtNode.isContinue = true;
        return ctrlStmtNode;
    }

    @Override
    public ASTNode visitExprStmt(MxParser.ExprStmtContext ctx) {
        ExprStmtNode exprStmtNode = new ExprStmtNode((ExprNode) visit(ctx.expression()), new Position(ctx));
        return super.visitExprStmt(ctx);
    }

    @Override
    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        return new ForStmtNode((StmtNode) visit(ctx.statement(0)),
                (ExprNode) visit(ctx.expression(0)),
                (ExprNode) visit(ctx.expression(1)),
                (StmtNode) visit(ctx.statement(1)),
                new Position(ctx));
    }

    @Override
    public ASTNode visitFuncdefineStmt(MxParser.FuncdefineStmtContext ctx) {
        return super.visitFuncdefineStmt(ctx);
    }

    @Override
    public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        IfStmtNode ifStmtNode = new IfStmtNode(new Position(ctx), (ExprNode) visit(ctx.expression()), (StmtNode) visit(ctx.trueStmt));
        if (ctx.falseStmt != null) ifStmtNode.elseStmt = (StmtNode) visit(ctx.falseStmt);
        return ifStmtNode;
    }

    @Override
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        return new WhileStmtNode((ExprNode) visit(ctx.expression()), (StmtNode) visit(ctx.statement()), new Position(ctx));
    }

    @Override
    public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        return new ReturnStmtNode((ExprNode) visit(ctx.expression()), new Position(ctx));
    }

    @Override
    public ASTNode visitBlock(MxParser.BlockContext ctx) {
        return visit(ctx.suite());
    }

    @Override
    public ASTNode visitSuite(MxParser.SuiteContext ctx) {
        BlockStmtNode blockStmtNode = new BlockStmtNode(new Position(ctx));
        for (var stmt : ctx.statement()) {
            blockStmtNode.statements.add((StmtNode) visit(stmt));
        }
        return super.visitSuite(ctx);
    }

    @Override
    public ASTNode visitClassdefine(MxParser.ClassdefineContext ctx) {
        ClassDefStmtNode classDefStmtNode = new ClassDefStmtNode(ctx.IDENTIFIER().toString(), new Position(ctx));
        classDefStmtNode.classBody = (BlockStmtNode) visit(ctx.suite());
        return super.visitClassdefine(ctx);
    }

    @Override
    public ASTNode visitFuncdefine(MxParser.FuncdefineContext ctx) {
        FunDefStmtNode funDefStmtNode = new FunDefStmtNode(new Position(ctx));
        funDefStmtNode.name = ctx.IDENTIFIER().toString();
        funDefStmtNode.returnTypeNode = (TypeNode) visit(ctx.returnType());
        for (var vardef : ctx.functionParameterList().children) {
            if (vardef instanceof MxParser.ParalistVarDefContext) {
                funDefStmtNode.parameterList.add((SingleVarDefNode) visit(vardef));
            }
            funDefStmtNode.funcBody = (BlockStmtNode) visit(ctx.suite());
        }
        return super.visitFuncdefine(ctx);
    }

    @Override
    public ASTNode visitReturnType(MxParser.ReturnTypeContext ctx) {
        TypeNode typeNode = new TypeNode(new Position(ctx));
        if (ctx.type() != null) return visit(ctx.type());
        else typeNode.type = voidType;
        return typeNode;
    }

    @Override
    public ASTNode visitType(MxParser.TypeContext ctx) {
        TypeNode typeNode = new TypeNode(new Position(ctx));
        Type type = new Type();
        if (ctx.BOOL() != null) type = boolType;
        else if (ctx.INT() != null) type = intType;
        else if (ctx.STR() != null) type = stringType;
        else type.name = ctx.IDENTIFIER().toString();
        typeNode.type = type;
        return typeNode;
    }
}

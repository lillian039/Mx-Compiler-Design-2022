package Frontend;

import AST.*;
import AST.Atom.*;
import AST.Expression.*;
import AST.Statement.*;
import Util.Scope.GlobalScope;
import Util.Position;
import Util.Type;
import Util.Entity;
import gen.MxBaseVisitor;
import gen.MxParser;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
    private GlobalScope gScope;

    public Type intType, boolType, stringType, voidType, nullType;


    public ASTBuilder(GlobalScope gScope_) {
        gScope = gScope_;
    }

    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        intType=gScope.getType("int");
        boolType=gScope.getType("bool");
        stringType=gScope.getType("string");
        voidType=gScope.getType("void");
        nullType=gScope.getType("null");
        RootNode rootNode = new RootNode(new Position(ctx));
        for(var Stmt : ctx.children){
            if(!(Stmt instanceof MxParser.ClassdefineContext)
                    &&!(Stmt instanceof MxParser.FuncdefineContext)
                    &&!(Stmt instanceof MxParser.VardefContext))break;
            StmtNode stmtNode=(StmtNode) visit(Stmt);
            rootNode.statements.add(stmtNode);
            if(Stmt instanceof MxParser.ClassdefineContext)rootNode.classDef.add((ClassDefStmtNode) stmtNode);
            else if(Stmt instanceof MxParser.FuncdefineContext)rootNode.funcDef.add((FunDefStmtNode)stmtNode);
            else if(Stmt instanceof MxParser.VardefContext)rootNode.varDef.add((VarDefStmtNode) stmtNode);
        }
        return rootNode;
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
        String op = ctx.op.getText().toString();
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
    public ASTNode visitDotVarExpr(MxParser.DotVarExprContext ctx) {
        Position pos = new Position(ctx);
        ExprNode ls = (ExprNode) visit(ctx.expression());
        ExprNode rs = (ExprNode) visit(ctx.variable());
        return new DotVarExprNode(ls, rs, pos);
    }

    @Override
    public ASTNode visitDotFuncExpr(MxParser.DotFuncExprContext ctx) {
        Position pos = new Position(ctx);
        ExprNode ls = (ExprNode) visit(ctx.expression());
        FuncExprNode rs = (FuncExprNode) visit(ctx.callfunction());
        return new DotFuncExprNode(ls, rs, pos);
    }

    @Override
    public ASTNode visitBracketExpr(MxParser.BracketExprContext ctx) {
        return visit(ctx.expression());
    }


    @Override
    public ASTNode visitCellExpr(MxParser.CellExprContext ctx) {
        ExprNode exprNode = (ExprNode) visit(ctx.expression());
        String op = ctx.op.getText().toString();
        return new CellExprNode(exprNode, op, new Position(ctx));
    }

    @Override
    public ASTNode visitDelayCellExpr(MxParser.DelayCellExprContext ctx) {
        ExprNode exprNode = (ExprNode) visit(ctx.expression());
        String op = ctx.op.getText().toString();
        return new DelayCellExpr(exprNode, op, new Position(ctx));
    }

    @Override
    public ASTNode visitPrimary(MxParser.PrimaryContext ctx) {
        if (ctx.constant()!=null) return visit(ctx.constant());
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
        if(ctx.functionParameterValue()!=null)funct.parameterValueNode=(FunctionParameterValueNode) visit(ctx.functionParameterValue());
        return funct;
    }

    @Override
    public ASTNode visitFunctionParameterValue(MxParser.FunctionParameterValueContext ctx) {
        Position pos = new Position(ctx);
        FunctionParameterValueNode functionParameterValueNode=new FunctionParameterValueNode(pos);
        ctx.expression().forEach(cur->functionParameterValueNode.parameters.add((ExprNode)visit(cur)));
        return functionParameterValueNode;
    }

    @Override
    public ASTNode visitLamdaExpr(MxParser.LamdaExprContext ctx) {
        Position pos = new Position(ctx);
        LambdaExprNode lambda = new LambdaExprNode(pos);
        if (ctx.And() != null) lambda.isGlobe = true;
        if(ctx.functionParameterList()!=null)lambda.functionParameterList=(FunctionParameterListNode)visit(ctx.functionParameterList());
        if(ctx.functionParameterValue()!=null)lambda.functionParameterValue=(FunctionParameterValueNode)visit(ctx.functionParameterValue());
        if(ctx.suite()!=null)lambda.funcBody = (BlockStmtNode) visit(ctx.suite());
        return lambda;
    }

    @Override
    public ASTNode visitVariable(MxParser.VariableContext ctx) {
        if(ctx.IDENTIFIER()!=null)return new VarExprNode(ctx.IDENTIFIER().toString(), new Position(ctx));
        else return new VarExprNode("this", new Position(ctx));
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
        NewArrNode newExprNode = new NewArrNode(typename, new Position(ctx));
        for (var arr : ctx.newArrExprAtom()) {
            newExprNode.arrList.add((NewArrDemNode) visit(arr));
            newExprNode.type.layer++;
        }
        return newExprNode;
    }


    @Override
    public ASTNode visitNewArrExprAtom(MxParser.NewArrExprAtomContext ctx) {
        NewArrDemNode newArrDemNode = new NewArrDemNode(new Position(ctx));
        if (ctx.expression() != null) newArrDemNode.arrDimension = (ExprNode) visit(ctx.expression());
        else newArrDemNode.arrDimension = null;
        return newArrDemNode;
    }

    @Override
    public ASTNode visitNewClassExpr(MxParser.NewClassExprContext ctx) {
        NewClassExprNode newExprNode = new NewClassExprNode(new Position(ctx));
        newExprNode.type= new TypeNode(new Position(ctx),new Type(ctx.IDENTIFIER().toString()),false);
        return newExprNode;
    }

    @Override
    public ASTNode visitArrayExpr(MxParser.ArrayExprContext ctx) {
        ArrExprNode arr=new ArrExprNode(new Position(ctx));
        arr.ls=(ExprNode) visit(ctx.expression(0));
        for(int i=1;i<ctx.expression().size();i++){
            arr.arrDimension.add((ExprNode) visit(ctx.expression(i)));
        }
        return arr;
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
    public ASTNode visitCtrlStmt(MxParser.CtrlStmtContext ctx) {
        CtrlStmtNode ctrlStmtNode = new CtrlStmtNode(new Position(ctx));
        if (ctx.BREAK() != null) ctrlStmtNode.isBreak = true;
        else ctrlStmtNode.isContinue = true;
        return ctrlStmtNode;
    }

    @Override
    public ASTNode visitExprStmt(MxParser.ExprStmtContext ctx) {
        return new ExprStmtNode((ExprNode) visit(ctx.expression()), new Position(ctx));
    }

    @Override
    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        ForStmtNode forStmtNode=new ForStmtNode(new Position(ctx));
        if(ctx.initStmt!=null)forStmtNode.initializeStmt=(StmtNode)visit(ctx.initStmt);
        if(ctx.condition!=null)forStmtNode.conditionNode=(ExprNode)visit(ctx.condition);
        if(ctx.step!=null)forStmtNode.stepNode=(ExprNode)visit(ctx.step);
        if(ctx.body!=null)forStmtNode.body=(StmtNode) visit(ctx.body);
        return forStmtNode;
    }

    @Override
    public ASTNode visitFuncdefineStmt(MxParser.FuncdefineStmtContext ctx) {
        return visit(ctx.funcdefine());
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
        if(ctx.expression()!=null)return new ReturnStmtNode((ExprNode) visit(ctx.expression()), new Position(ctx));
        else return new ReturnStmtNode(new Position(ctx));
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
        return blockStmtNode;
    }

    @Override
    public ASTNode visitClassdefine(MxParser.ClassdefineContext ctx) {
        ClassDefStmtNode classDefStmtNode = new ClassDefStmtNode(ctx.IDENTIFIER().toString(), new Position(ctx));
        classDefStmtNode.classBody = (BlockStmtNode) visit(ctx.suite());
        return classDefStmtNode;
    }

    @Override
    public ASTNode visitFuncdefine(MxParser.FuncdefineContext ctx) {
        FunDefStmtNode funDefStmtNode = new FunDefStmtNode(new Position(ctx));
        funDefStmtNode.name = ctx.IDENTIFIER().toString();
        if(ctx.returnType()!=null)funDefStmtNode.returnTypeNode = (TypeNode) visit(ctx.returnType());
        if(ctx.functionParameterList()!=null)funDefStmtNode.parameterList=(FunctionParameterListNode) visit(ctx.functionParameterList());
        funDefStmtNode.funcBody = (BlockStmtNode) visit(ctx.suite());
        return funDefStmtNode;
    }

    @Override
    public ASTNode visitFunctionParameterList(MxParser.FunctionParameterListContext ctx) {
        Position pos=new Position(ctx);
        FunctionParameterListNode parameteListNode=new FunctionParameterListNode(pos);
        ctx.paralistVarDef().forEach(cur->parameteListNode.add((SingleVarDefNode) visit(cur)));
        return parameteListNode;
    }

    @Override
    public ASTNode visitParalistVarDef(MxParser.ParalistVarDefContext ctx) {
        TypeNode typeNode=(TypeNode) visit(ctx.type());
        SingleVarDefNode singleVarDefNode=new SingleVarDefNode(new Position(ctx),ctx.IDENTIFIER().toString(),typeNode);
        return singleVarDefNode;
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
        String typename;
        if (ctx.BOOL() != null) typename="bool";
        else if (ctx.INT() != null) typename="int";
        else if (ctx.STR() != null) typename="string";
        else typename = ctx.IDENTIFIER().toString();
        typeNode.layer+=ctx.RightBracket().size();
        if(typeNode.layer>1)typeNode.isArr=true;
        typeNode.type.name=typename;
        return typeNode;
    }
}

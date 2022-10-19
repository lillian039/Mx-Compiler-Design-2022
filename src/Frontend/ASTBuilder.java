package Frontend;

import AST.*;
import Util.GlobalScope;
import gen.MxBaseVisitor;
import gen.MxParser;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
    private GlobalScope gScope;

    public ASTBuilder(GlobalScope gScope_){gScope=gScope_;}

    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }

    @Override
    public ASTNode visitMainfunc(MxParser.MainfuncContext ctx) {
        return super.visitMainfunc(ctx);
    }

    @Override
    public ASTNode visitAtomExpr(MxParser.AtomExprContext ctx) {
        return super.visitAtomExpr(ctx);
    }

    @Override
    public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
        return super.visitBinaryExpr(ctx);
    }

    @Override
    public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
        return super.visitAssignExpr(ctx);
    }

    @Override
    public ASTNode visitDotExpr(MxParser.DotExprContext ctx) {
        return super.visitDotExpr(ctx);
    }

    @Override
    public ASTNode visitCellExpr(MxParser.CellExprContext ctx) {
        return super.visitCellExpr(ctx);
    }


}

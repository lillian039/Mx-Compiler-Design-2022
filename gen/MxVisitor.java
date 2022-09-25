// Generated from D:/a_homework_of_c++/2-1_semester/Mx-Compiler-Design-2022\Mx.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS(MxParser.SContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#stringExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpr(MxParser.StringExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#intExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpr(MxParser.IntExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#boolExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(MxParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#vardefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardefine(MxParser.VardefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#newvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewvalue(MxParser.NewvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcdefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdefine(MxParser.FuncdefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#mainfuncdefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainfuncdefine(MxParser.MainfuncdefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classdefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdefine(MxParser.ClassdefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MxParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#vardefinestate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardefinestate(MxParser.VardefinestateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varassignstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarassignstate(MxParser.VarassignstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#conditionstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionstate(MxParser.ConditionstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#whilestate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhilestate(MxParser.WhilestateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#expressstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressstate(MxParser.ExpressstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#forstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForstate(MxParser.ForstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#breakstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakstate(MxParser.BreakstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#continuestate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinuestate(MxParser.ContinuestateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#innerfuncstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInnerfuncstatement(MxParser.InnerfuncstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#callfuncstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallfuncstate(MxParser.CallfuncstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#printStr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStr(MxParser.PrintStrContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#printlnStr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintlnStr(MxParser.PrintlnStrContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#printInt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintInt(MxParser.PrintIntContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#printlnInt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintlnInt(MxParser.PrintlnIntContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#getString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetString(MxParser.GetStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#getInt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetInt(MxParser.GetIntContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#toString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToString(MxParser.ToStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#intLength}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLength(MxParser.IntLengthContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#parseInt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseInt(MxParser.ParseIntContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#intOrd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntOrd(MxParser.IntOrdContext ctx);
}
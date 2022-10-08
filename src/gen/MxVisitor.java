// Generated from D:/a_homework_of_c++/2-1_semester/Mx-Compiler-Design-2022/src\Mx.g4 by ANTLR 4.10.1
package gen;
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
	 * Visit a parse tree produced by {@link MxParser#unknownExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownExpr(MxParser.UnknownExprContext ctx);
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
	 * Visit a parse tree produced by {@link MxParser#classExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassExpr(MxParser.ClassExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MxParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#arrayelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayelement(MxParser.ArrayelementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#intvardefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntvardefine(MxParser.IntvardefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#boolvardefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolvardefine(MxParser.BoolvardefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#stringvardefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringvardefine(MxParser.StringvardefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classvardefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassvardefine(MxParser.ClassvardefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#intarraydefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntarraydefine(MxParser.IntarraydefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#boolarraydefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolarraydefine(MxParser.BoolarraydefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#stringarraydefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringarraydefine(MxParser.StringarraydefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#vardefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardefine(MxParser.VardefineContext ctx);
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
	 * Visit a parse tree produced by {@link MxParser#arrayassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayassign(MxParser.ArrayassignContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarassign(MxParser.VarassignContext ctx);
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
	 * Visit a parse tree produced by {@link MxParser#callfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallfunction(MxParser.CallfunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#callclassfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallclassfunction(MxParser.CallclassfunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#callfuncstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallfuncstate(MxParser.CallfuncstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#returnstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnstate(MxParser.ReturnstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#lamdaglobe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLamdaglobe(MxParser.LamdaglobeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#lamdainner}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLamdainner(MxParser.LamdainnerContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#lamdaExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLamdaExpr(MxParser.LamdaExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#intreturnstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntreturnstate(MxParser.IntreturnstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#boolreturnstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolreturnstate(MxParser.BoolreturnstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#stringreturnstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringreturnstate(MxParser.StringreturnstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#voidreturnstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidreturnstate(MxParser.VoidreturnstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classreturnstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassreturnstate(MxParser.ClassreturnstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassmember(MxParser.ClassmemberContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#innermember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInnermember(MxParser.InnermemberContext ctx);
}
// Generated from D:/a_homework_of_c++/2-1_semester/Mx-Compiler-Design-2022/src\Mx.g4 by ANTLR 4.10.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#s}.
	 * @param ctx the parse tree
	 */
	void enterS(MxParser.SContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#s}.
	 * @param ctx the parse tree
	 */
	void exitS(MxParser.SContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#stringExpr}.
	 * @param ctx the parse tree
	 */
	void enterStringExpr(MxParser.StringExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#stringExpr}.
	 * @param ctx the parse tree
	 */
	void exitStringExpr(MxParser.StringExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#unknownExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnknownExpr(MxParser.UnknownExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#unknownExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnknownExpr(MxParser.UnknownExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#intExpr}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(MxParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#intExpr}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(MxParser.IntExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(MxParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(MxParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classExpr}.
	 * @param ctx the parse tree
	 */
	void enterClassExpr(MxParser.ClassExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classExpr}.
	 * @param ctx the parse tree
	 */
	void exitClassExpr(MxParser.ClassExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MxParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MxParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#arrayelement}.
	 * @param ctx the parse tree
	 */
	void enterArrayelement(MxParser.ArrayelementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#arrayelement}.
	 * @param ctx the parse tree
	 */
	void exitArrayelement(MxParser.ArrayelementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#intvardefine}.
	 * @param ctx the parse tree
	 */
	void enterIntvardefine(MxParser.IntvardefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#intvardefine}.
	 * @param ctx the parse tree
	 */
	void exitIntvardefine(MxParser.IntvardefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#boolvardefine}.
	 * @param ctx the parse tree
	 */
	void enterBoolvardefine(MxParser.BoolvardefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#boolvardefine}.
	 * @param ctx the parse tree
	 */
	void exitBoolvardefine(MxParser.BoolvardefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#stringvardefine}.
	 * @param ctx the parse tree
	 */
	void enterStringvardefine(MxParser.StringvardefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#stringvardefine}.
	 * @param ctx the parse tree
	 */
	void exitStringvardefine(MxParser.StringvardefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classvardefine}.
	 * @param ctx the parse tree
	 */
	void enterClassvardefine(MxParser.ClassvardefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classvardefine}.
	 * @param ctx the parse tree
	 */
	void exitClassvardefine(MxParser.ClassvardefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#intarraydefine}.
	 * @param ctx the parse tree
	 */
	void enterIntarraydefine(MxParser.IntarraydefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#intarraydefine}.
	 * @param ctx the parse tree
	 */
	void exitIntarraydefine(MxParser.IntarraydefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#boolarraydefine}.
	 * @param ctx the parse tree
	 */
	void enterBoolarraydefine(MxParser.BoolarraydefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#boolarraydefine}.
	 * @param ctx the parse tree
	 */
	void exitBoolarraydefine(MxParser.BoolarraydefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#stringarraydefine}.
	 * @param ctx the parse tree
	 */
	void enterStringarraydefine(MxParser.StringarraydefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#stringarraydefine}.
	 * @param ctx the parse tree
	 */
	void exitStringarraydefine(MxParser.StringarraydefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#vardefine}.
	 * @param ctx the parse tree
	 */
	void enterVardefine(MxParser.VardefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#vardefine}.
	 * @param ctx the parse tree
	 */
	void exitVardefine(MxParser.VardefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcdefine}.
	 * @param ctx the parse tree
	 */
	void enterFuncdefine(MxParser.FuncdefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcdefine}.
	 * @param ctx the parse tree
	 */
	void exitFuncdefine(MxParser.FuncdefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#mainfuncdefine}.
	 * @param ctx the parse tree
	 */
	void enterMainfuncdefine(MxParser.MainfuncdefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#mainfuncdefine}.
	 * @param ctx the parse tree
	 */
	void exitMainfuncdefine(MxParser.MainfuncdefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classdefine}.
	 * @param ctx the parse tree
	 */
	void enterClassdefine(MxParser.ClassdefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classdefine}.
	 * @param ctx the parse tree
	 */
	void exitClassdefine(MxParser.ClassdefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MxParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MxParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#vardefinestate}.
	 * @param ctx the parse tree
	 */
	void enterVardefinestate(MxParser.VardefinestateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#vardefinestate}.
	 * @param ctx the parse tree
	 */
	void exitVardefinestate(MxParser.VardefinestateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#arrayassign}.
	 * @param ctx the parse tree
	 */
	void enterArrayassign(MxParser.ArrayassignContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#arrayassign}.
	 * @param ctx the parse tree
	 */
	void exitArrayassign(MxParser.ArrayassignContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varassign}.
	 * @param ctx the parse tree
	 */
	void enterVarassign(MxParser.VarassignContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varassign}.
	 * @param ctx the parse tree
	 */
	void exitVarassign(MxParser.VarassignContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varassignstate}.
	 * @param ctx the parse tree
	 */
	void enterVarassignstate(MxParser.VarassignstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varassignstate}.
	 * @param ctx the parse tree
	 */
	void exitVarassignstate(MxParser.VarassignstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#conditionstate}.
	 * @param ctx the parse tree
	 */
	void enterConditionstate(MxParser.ConditionstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#conditionstate}.
	 * @param ctx the parse tree
	 */
	void exitConditionstate(MxParser.ConditionstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#whilestate}.
	 * @param ctx the parse tree
	 */
	void enterWhilestate(MxParser.WhilestateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#whilestate}.
	 * @param ctx the parse tree
	 */
	void exitWhilestate(MxParser.WhilestateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expressstate}.
	 * @param ctx the parse tree
	 */
	void enterExpressstate(MxParser.ExpressstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expressstate}.
	 * @param ctx the parse tree
	 */
	void exitExpressstate(MxParser.ExpressstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#forstate}.
	 * @param ctx the parse tree
	 */
	void enterForstate(MxParser.ForstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#forstate}.
	 * @param ctx the parse tree
	 */
	void exitForstate(MxParser.ForstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#breakstate}.
	 * @param ctx the parse tree
	 */
	void enterBreakstate(MxParser.BreakstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#breakstate}.
	 * @param ctx the parse tree
	 */
	void exitBreakstate(MxParser.BreakstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#continuestate}.
	 * @param ctx the parse tree
	 */
	void enterContinuestate(MxParser.ContinuestateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#continuestate}.
	 * @param ctx the parse tree
	 */
	void exitContinuestate(MxParser.ContinuestateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#callfunction}.
	 * @param ctx the parse tree
	 */
	void enterCallfunction(MxParser.CallfunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#callfunction}.
	 * @param ctx the parse tree
	 */
	void exitCallfunction(MxParser.CallfunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#callclassfunction}.
	 * @param ctx the parse tree
	 */
	void enterCallclassfunction(MxParser.CallclassfunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#callclassfunction}.
	 * @param ctx the parse tree
	 */
	void exitCallclassfunction(MxParser.CallclassfunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#callfuncstate}.
	 * @param ctx the parse tree
	 */
	void enterCallfuncstate(MxParser.CallfuncstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#callfuncstate}.
	 * @param ctx the parse tree
	 */
	void exitCallfuncstate(MxParser.CallfuncstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#returnstate}.
	 * @param ctx the parse tree
	 */
	void enterReturnstate(MxParser.ReturnstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#returnstate}.
	 * @param ctx the parse tree
	 */
	void exitReturnstate(MxParser.ReturnstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#lamdaglobe}.
	 * @param ctx the parse tree
	 */
	void enterLamdaglobe(MxParser.LamdaglobeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#lamdaglobe}.
	 * @param ctx the parse tree
	 */
	void exitLamdaglobe(MxParser.LamdaglobeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#lamdainner}.
	 * @param ctx the parse tree
	 */
	void enterLamdainner(MxParser.LamdainnerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#lamdainner}.
	 * @param ctx the parse tree
	 */
	void exitLamdainner(MxParser.LamdainnerContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#lamdaExpr}.
	 * @param ctx the parse tree
	 */
	void enterLamdaExpr(MxParser.LamdaExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#lamdaExpr}.
	 * @param ctx the parse tree
	 */
	void exitLamdaExpr(MxParser.LamdaExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#intreturnstate}.
	 * @param ctx the parse tree
	 */
	void enterIntreturnstate(MxParser.IntreturnstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#intreturnstate}.
	 * @param ctx the parse tree
	 */
	void exitIntreturnstate(MxParser.IntreturnstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#boolreturnstate}.
	 * @param ctx the parse tree
	 */
	void enterBoolreturnstate(MxParser.BoolreturnstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#boolreturnstate}.
	 * @param ctx the parse tree
	 */
	void exitBoolreturnstate(MxParser.BoolreturnstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#stringreturnstate}.
	 * @param ctx the parse tree
	 */
	void enterStringreturnstate(MxParser.StringreturnstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#stringreturnstate}.
	 * @param ctx the parse tree
	 */
	void exitStringreturnstate(MxParser.StringreturnstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#voidreturnstate}.
	 * @param ctx the parse tree
	 */
	void enterVoidreturnstate(MxParser.VoidreturnstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#voidreturnstate}.
	 * @param ctx the parse tree
	 */
	void exitVoidreturnstate(MxParser.VoidreturnstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classreturnstate}.
	 * @param ctx the parse tree
	 */
	void enterClassreturnstate(MxParser.ClassreturnstateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classreturnstate}.
	 * @param ctx the parse tree
	 */
	void exitClassreturnstate(MxParser.ClassreturnstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 */
	void enterClassmember(MxParser.ClassmemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 */
	void exitClassmember(MxParser.ClassmemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#innermember}.
	 * @param ctx the parse tree
	 */
	void enterInnermember(MxParser.InnermemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#innermember}.
	 * @param ctx the parse tree
	 */
	void exitInnermember(MxParser.InnermemberContext ctx);
}
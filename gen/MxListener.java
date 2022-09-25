// Generated from D:/a_homework_of_c++/2-1_semester/Mx-Compiler-Design-2022\Mx.g4 by ANTLR 4.10.1
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
	 * Enter a parse tree produced by {@link MxParser#newvalue}.
	 * @param ctx the parse tree
	 */
	void enterNewvalue(MxParser.NewvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#newvalue}.
	 * @param ctx the parse tree
	 */
	void exitNewvalue(MxParser.NewvalueContext ctx);
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
	 * Enter a parse tree produced by {@link MxParser#innerfuncstatement}.
	 * @param ctx the parse tree
	 */
	void enterInnerfuncstatement(MxParser.InnerfuncstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#innerfuncstatement}.
	 * @param ctx the parse tree
	 */
	void exitInnerfuncstatement(MxParser.InnerfuncstatementContext ctx);
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
	 * Enter a parse tree produced by {@link MxParser#printStr}.
	 * @param ctx the parse tree
	 */
	void enterPrintStr(MxParser.PrintStrContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#printStr}.
	 * @param ctx the parse tree
	 */
	void exitPrintStr(MxParser.PrintStrContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#printlnStr}.
	 * @param ctx the parse tree
	 */
	void enterPrintlnStr(MxParser.PrintlnStrContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#printlnStr}.
	 * @param ctx the parse tree
	 */
	void exitPrintlnStr(MxParser.PrintlnStrContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#printInt}.
	 * @param ctx the parse tree
	 */
	void enterPrintInt(MxParser.PrintIntContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#printInt}.
	 * @param ctx the parse tree
	 */
	void exitPrintInt(MxParser.PrintIntContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#printlnInt}.
	 * @param ctx the parse tree
	 */
	void enterPrintlnInt(MxParser.PrintlnIntContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#printlnInt}.
	 * @param ctx the parse tree
	 */
	void exitPrintlnInt(MxParser.PrintlnIntContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#getString}.
	 * @param ctx the parse tree
	 */
	void enterGetString(MxParser.GetStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#getString}.
	 * @param ctx the parse tree
	 */
	void exitGetString(MxParser.GetStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#getInt}.
	 * @param ctx the parse tree
	 */
	void enterGetInt(MxParser.GetIntContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#getInt}.
	 * @param ctx the parse tree
	 */
	void exitGetInt(MxParser.GetIntContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#toString}.
	 * @param ctx the parse tree
	 */
	void enterToString(MxParser.ToStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#toString}.
	 * @param ctx the parse tree
	 */
	void exitToString(MxParser.ToStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#intLength}.
	 * @param ctx the parse tree
	 */
	void enterIntLength(MxParser.IntLengthContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#intLength}.
	 * @param ctx the parse tree
	 */
	void exitIntLength(MxParser.IntLengthContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#parseInt}.
	 * @param ctx the parse tree
	 */
	void enterParseInt(MxParser.ParseIntContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#parseInt}.
	 * @param ctx the parse tree
	 */
	void exitParseInt(MxParser.ParseIntContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#intOrd}.
	 * @param ctx the parse tree
	 */
	void enterIntOrd(MxParser.IntOrdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#intOrd}.
	 * @param ctx the parse tree
	 */
	void exitIntOrd(MxParser.IntOrdContext ctx);
}
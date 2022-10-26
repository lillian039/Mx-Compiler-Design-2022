// Generated from D:/a_homework_of_c++/2-1_semester/Mx-Compiler-Design-2022/src\Mx.g4 by ANTLR 4.10.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(MxParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(MxParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpr(MxParser.ArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpr(MxParser.ArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code delayCellExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDelayCellExpr(MxParser.DelayCellExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code delayCellExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDelayCellExpr(MxParser.DelayCellExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bracketExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBracketExpr(MxParser.BracketExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bracketExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBracketExpr(MxParser.BracketExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dotFuncExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDotFuncExpr(MxParser.DotFuncExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dotFuncExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDotFuncExpr(MxParser.DotFuncExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(MxParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(MxParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MxParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MxParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dotVarExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDotVarExpr(MxParser.DotVarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dotVarExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDotVarExpr(MxParser.DotVarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(MxParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(MxParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cellExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCellExpr(MxParser.CellExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cellExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCellExpr(MxParser.CellExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(MxParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(MxParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(MxParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(MxParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funVal}.
	 * @param ctx the parse tree
	 */
	void enterFunVal(MxParser.FunValContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funVal}.
	 * @param ctx the parse tree
	 */
	void exitFunVal(MxParser.FunValContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#newArrExprAtom}.
	 * @param ctx the parse tree
	 */
	void enterNewArrExprAtom(MxParser.NewArrExprAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#newArrExprAtom}.
	 * @param ctx the parse tree
	 */
	void exitNewArrExprAtom(MxParser.NewArrExprAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#newArrExpr}.
	 * @param ctx the parse tree
	 */
	void enterNewArrExpr(MxParser.NewArrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#newArrExpr}.
	 * @param ctx the parse tree
	 */
	void exitNewArrExpr(MxParser.NewArrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#newClassExpr}.
	 * @param ctx the parse tree
	 */
	void enterNewClassExpr(MxParser.NewClassExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#newClassExpr}.
	 * @param ctx the parse tree
	 */
	void exitNewClassExpr(MxParser.NewClassExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MxParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MxParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#vardef}.
	 * @param ctx the parse tree
	 */
	void enterVardef(MxParser.VardefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#vardef}.
	 * @param ctx the parse tree
	 */
	void exitVardef(MxParser.VardefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(MxParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(MxParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#paralistVarDef}.
	 * @param ctx the parse tree
	 */
	void enterParalistVarDef(MxParser.ParalistVarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#paralistVarDef}.
	 * @param ctx the parse tree
	 */
	void exitParalistVarDef(MxParser.ParalistVarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#singleVarDef}.
	 * @param ctx the parse tree
	 */
	void enterSingleVarDef(MxParser.SingleVarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#singleVarDef}.
	 * @param ctx the parse tree
	 */
	void exitSingleVarDef(MxParser.SingleVarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#functionParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFunctionParameterList(MxParser.FunctionParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#functionParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFunctionParameterList(MxParser.FunctionParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#functionParameterValue}.
	 * @param ctx the parse tree
	 */
	void enterFunctionParameterValue(MxParser.FunctionParameterValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#functionParameterValue}.
	 * @param ctx the parse tree
	 */
	void exitFunctionParameterValue(MxParser.FunctionParameterValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code block}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code block}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code vardefineStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVardefineStmt(MxParser.VardefineStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code vardefineStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVardefineStmt(MxParser.VardefineStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(MxParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(MxParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcdefineStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterFuncdefineStmt(MxParser.FuncdefineStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcdefineStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitFuncdefineStmt(MxParser.FuncdefineStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ctrlStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCtrlStmt(MxParser.CtrlStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ctrlStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCtrlStmt(MxParser.CtrlStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStmt(MxParser.EmptyStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStmt(MxParser.EmptyStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(MxParser.ReturnTypeContext ctx);
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
	 * Enter a parse tree produced by {@link MxParser#lamdaExpr}.
	 * @param ctx the parse tree
	 */
	void enterLamdaExpr(MxParser.LamdaExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#lamdaExpr}.
	 * @param ctx the parse tree
	 */
	void exitLamdaExpr(MxParser.LamdaExprContext ctx);
}
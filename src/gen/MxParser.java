// Generated from D:/a_homework_of_c++/2-1_semester/Mx-Compiler-Design-2022/src\Mx.g4 by ANTLR 4.10.1
package gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, LeftParen=6, RightParen=7, LeftBracket=8, 
		RightBracket=9, LeftBrace=10, RightBrace=11, ASSIGN=12, Add=13, Sub=14, 
		Mul=15, Div=16, Mod=17, Or=18, Xor=19, And=20, Not=21, LeftShift=22, RightShift=23, 
		Invert=24, AddSelf=25, SubSelf=26, Less=27, Greater=28, Equals=29, GreaterEqual=30, 
		LessEqual=31, NotEqual=32, AndAnd=33, Oror=34, Return=35, For=36, If=37, 
		While=38, Semi=39, Comma=40, Dot=41, INT=42, BOOL=43, STR=44, VOID=45, 
		NEW=46, TRUE=47, FALSE=48, INTEGER=49, STRING=50, THIS=51, IDENTIFIER=52, 
		WS=53, LINE_COMMENT=54, COMMENT=55, BREAK=56, CONTINUE=57;
	public static final int
		RULE_program = 0, RULE_mainfunc = 1, RULE_expression = 2, RULE_primary = 3, 
		RULE_constant = 4, RULE_variable = 5, RULE_funVal = 6, RULE_newArrExpr = 7, 
		RULE_newClassExpr = 8, RULE_type = 9, RULE_vardef = 10, RULE_suite = 11, 
		RULE_arrayelement = 12, RULE_functionParameterList = 13, RULE_functionParameterValue = 14, 
		RULE_statement = 15, RULE_returnType = 16, RULE_funcdefine = 17, RULE_classdefine = 18, 
		RULE_callfunction = 19, RULE_lamdaExpr = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "mainfunc", "expression", "primary", "constant", "variable", 
			"funVal", "newArrExpr", "newClassExpr", "type", "vardef", "suite", "arrayelement", 
			"functionParameterList", "functionParameterValue", "statement", "returnType", 
			"funcdefine", "classdefine", "callfunction", "lamdaExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'main'", "'null'", "'else'", "'class'", "'->'", "'('", "')'", 
			"'['", "']'", "'{'", "'}'", "'='", "'+'", "'-'", "'*'", "'/'", "'%'", 
			"'|'", "'^'", "'&'", "'!'", "'<<'", "'>>'", "'~'", "'++'", "'--'", "'<'", 
			"'>'", "'=='", "'>='", "'<='", "'!='", "'&&'", "'||'", "'return'", "'for'", 
			"'if'", "'while'", "';'", "','", "'.'", "'int'", "'bool'", "'string'", 
			"'void'", "'new'", "'true'", "'false'", null, null, "'this'", null, null, 
			null, null, "'break'", "'continue'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "LeftParen", "RightParen", "LeftBracket", 
			"RightBracket", "LeftBrace", "RightBrace", "ASSIGN", "Add", "Sub", "Mul", 
			"Div", "Mod", "Or", "Xor", "And", "Not", "LeftShift", "RightShift", "Invert", 
			"AddSelf", "SubSelf", "Less", "Greater", "Equals", "GreaterEqual", "LessEqual", 
			"NotEqual", "AndAnd", "Oror", "Return", "For", "If", "While", "Semi", 
			"Comma", "Dot", "INT", "BOOL", "STR", "VOID", "NEW", "TRUE", "FALSE", 
			"INTEGER", "STRING", "THIS", "IDENTIFIER", "WS", "LINE_COMMENT", "COMMENT", 
			"BREAK", "CONTINUE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public MainfuncContext mainfunc() {
			return getRuleContext(MainfuncContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MxParser.EOF, 0); }
		public List<VardefContext> vardef() {
			return getRuleContexts(VardefContext.class);
		}
		public VardefContext vardef(int i) {
			return getRuleContext(VardefContext.class,i);
		}
		public List<FuncdefineContext> funcdefine() {
			return getRuleContexts(FuncdefineContext.class);
		}
		public FuncdefineContext funcdefine(int i) {
			return getRuleContext(FuncdefineContext.class,i);
		}
		public List<ClassdefineContext> classdefine() {
			return getRuleContexts(ClassdefineContext.class);
		}
		public ClassdefineContext classdefine(int i) {
			return getRuleContext(ClassdefineContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(45);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(42);
						vardef();
						}
						break;
					case 2:
						{
						setState(43);
						funcdefine();
						}
						break;
					case 3:
						{
						setState(44);
						classdefine();
						}
						break;
					}
					} 
				}
				setState(49);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(50);
			mainfunc();
			setState(51);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainfuncContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MxParser.INT, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public MainfuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainfunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMainfunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMainfunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitMainfunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainfuncContext mainfunc() throws RecognitionException {
		MainfuncContext _localctx = new MainfuncContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainfunc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(INT);
			setState(54);
			match(T__0);
			setState(55);
			match(LeftParen);
			setState(56);
			match(RightParen);
			setState(57);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExpressionContext {
		public NewArrExprContext newArrExpr() {
			return getRuleContext(NewArrExprContext.class,0);
		}
		public NewClassExprContext newClassExpr() {
			return getRuleContext(NewClassExprContext.class,0);
		}
		public NewExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DotExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Dot() { return getToken(MxParser.Dot, 0); }
		public DotExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterDotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitDotExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitDotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomExprContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public AtomExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAtomExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Mul() { return getToken(MxParser.Mul, 0); }
		public TerminalNode Div() { return getToken(MxParser.Div, 0); }
		public TerminalNode Mod() { return getToken(MxParser.Mod, 0); }
		public TerminalNode Add() { return getToken(MxParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public TerminalNode LeftShift() { return getToken(MxParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(MxParser.RightShift, 0); }
		public TerminalNode And() { return getToken(MxParser.And, 0); }
		public TerminalNode Xor() { return getToken(MxParser.Xor, 0); }
		public TerminalNode Or() { return getToken(MxParser.Or, 0); }
		public TerminalNode Greater() { return getToken(MxParser.Greater, 0); }
		public TerminalNode Less() { return getToken(MxParser.Less, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxParser.GreaterEqual, 0); }
		public TerminalNode LessEqual() { return getToken(MxParser.LessEqual, 0); }
		public TerminalNode Equals() { return getToken(MxParser.Equals, 0); }
		public TerminalNode NotEqual() { return getToken(MxParser.NotEqual, 0); }
		public TerminalNode AndAnd() { return getToken(MxParser.AndAnd, 0); }
		public TerminalNode Oror() { return getToken(MxParser.Oror, 0); }
		public BinaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBinaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBinaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(MxParser.ASSIGN, 0); }
		public AssignExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAssignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CellExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Not() { return getToken(MxParser.Not, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public TerminalNode Invert() { return getToken(MxParser.Invert, 0); }
		public TerminalNode AddSelf() { return getToken(MxParser.AddSelf, 0); }
		public TerminalNode SubSelf() { return getToken(MxParser.SubSelf, 0); }
		public CellExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCellExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCellExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCellExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftParen:
			case LeftBracket:
			case TRUE:
			case FALSE:
			case INTEGER:
			case STRING:
			case THIS:
			case IDENTIFIER:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(60);
				primary();
				}
				break;
			case Sub:
			case Not:
				{
				_localctx = new CellExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(61);
				((CellExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Sub || _la==Not) ) {
					((CellExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(62);
				expression(15);
				}
				break;
			case Invert:
			case AddSelf:
			case SubSelf:
				{
				_localctx = new CellExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(63);
				((CellExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Invert) | (1L << AddSelf) | (1L << SubSelf))) != 0)) ) {
					((CellExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(64);
				expression(13);
				}
				break;
			case T__1:
			case NEW:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(67);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(65);
					newArrExpr();
					}
					break;
				case 2:
					{
					setState(66);
					newClassExpr();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(111);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(109);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new DotExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(71);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(72);
						((DotExprContext)_localctx).op = match(Dot);
						setState(73);
						expression(17);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(74);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(75);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(76);
						expression(13);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(77);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(78);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Sub) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(79);
						expression(12);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(80);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(81);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LeftShift || _la==RightShift) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(82);
						expression(11);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(83);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(84);
						((BinaryExprContext)_localctx).op = match(And);
						setState(85);
						expression(10);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(86);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(87);
						((BinaryExprContext)_localctx).op = match(Xor);
						setState(88);
						expression(9);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(89);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(90);
						((BinaryExprContext)_localctx).op = match(Or);
						setState(91);
						expression(8);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(92);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(93);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << Greater) | (1L << GreaterEqual) | (1L << LessEqual))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(94);
						expression(7);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(95);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(96);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equals || _la==NotEqual) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(97);
						expression(6);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(98);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(99);
						((BinaryExprContext)_localctx).op = match(AndAnd);
						setState(100);
						expression(5);
						}
						break;
					case 11:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(101);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(102);
						((BinaryExprContext)_localctx).op = match(Oror);
						setState(103);
						expression(4);
						}
						break;
					case 12:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(104);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(105);
						match(ASSIGN);
						setState(106);
						expression(2);
						}
						break;
					case 13:
						{
						_localctx = new CellExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(107);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(108);
						((CellExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==AddSelf || _la==SubSelf) ) {
							((CellExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(113);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public FunValContext funVal() {
			return getRuleContext(FunValContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_primary);
		try {
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				match(LeftParen);
				setState(115);
				expression(0);
				setState(116);
				match(RightParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				variable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
				constant();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(120);
				funVal();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(MxParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(MxParser.STRING, 0); }
		public TerminalNode TRUE() { return getToken(MxParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(MxParser.FALSE, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constant);
		int _la;
		try {
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				match(INTEGER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				match(STRING);
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(125);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public TerminalNode THIS() { return getToken(MxParser.THIS, 0); }
		public ArrayelementContext arrayelement() {
			return getRuleContext(ArrayelementContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variable);
		try {
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				match(THIS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(130);
				arrayelement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunValContext extends ParserRuleContext {
		public CallfunctionContext callfunction() {
			return getRuleContext(CallfunctionContext.class,0);
		}
		public LamdaExprContext lamdaExpr() {
			return getRuleContext(LamdaExprContext.class,0);
		}
		public FunValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funVal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFunVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunValContext funVal() throws RecognitionException {
		FunValContext _localctx = new FunValContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_funVal);
		try {
			setState(135);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				callfunction();
				}
				break;
			case LeftBracket:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				lamdaExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewArrExprContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(MxParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NewArrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newArrExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewArrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewArrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewArrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewArrExprContext newArrExpr() throws RecognitionException {
		NewArrExprContext _localctx = new NewArrExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_newArrExpr);
		int _la;
		try {
			int _alt;
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NEW:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(137);
				match(NEW);
				setState(138);
				type();
				setState(144); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(139);
						match(LeftBracket);
						setState(141);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Sub) | (1L << Not) | (1L << Invert) | (1L << AddSelf) | (1L << SubSelf) | (1L << NEW) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << THIS) | (1L << IDENTIFIER))) != 0)) {
							{
							setState(140);
							expression(0);
							}
						}

						setState(143);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(146); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewClassExprContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(MxParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public FunctionParameterValueContext functionParameterValue() {
			return getRuleContext(FunctionParameterValueContext.class,0);
		}
		public NewClassExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newClassExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewClassExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewClassExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewClassExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewClassExprContext newClassExpr() throws RecognitionException {
		NewClassExprContext _localctx = new NewClassExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_newClassExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(NEW);
			setState(152);
			type();
			setState(153);
			match(LeftParen);
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Sub) | (1L << Not) | (1L << Invert) | (1L << AddSelf) | (1L << SubSelf) | (1L << NEW) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << THIS) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(154);
				functionParameterValue();
				}
			}

			setState(157);
			match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MxParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(MxParser.BOOL, 0); }
		public TerminalNode STR() { return getToken(MxParser.STR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STR) | (1L << IDENTIFIER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VardefContext extends ParserRuleContext {
		public VardefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vardef; }
	 
		public VardefContext() { }
		public void copyFrom(VardefContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SignalvarContext extends VardefContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(MxParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MxParser.IDENTIFIER, i);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public List<TerminalNode> ASSIGN() { return getTokens(MxParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(MxParser.ASSIGN, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public SignalvarContext(VardefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSignalvar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSignalvar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitSignalvar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayvarContext extends VardefContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(MxParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MxParser.IDENTIFIER, i);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(MxParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(MxParser.ASSIGN, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ArrayvarContext(VardefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArrayvar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArrayvar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArrayvar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VardefContext vardef() throws RecognitionException {
		VardefContext _localctx = new VardefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_vardef);
		int _la;
		try {
			setState(217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new SignalvarContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				type();
				setState(162);
				match(IDENTIFIER);
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ASSIGN) {
					{
					{
					setState(163);
					match(ASSIGN);
					setState(164);
					expression(0);
					}
					}
					setState(169);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(170);
					match(Comma);
					setState(171);
					match(IDENTIFIER);
					setState(176);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==ASSIGN) {
						{
						{
						setState(172);
						match(ASSIGN);
						setState(173);
						expression(0);
						}
						}
						setState(178);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					setState(183);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(184);
				match(Semi);
				}
				break;
			case 2:
				_localctx = new ArrayvarContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				type();
				setState(189); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(187);
					match(LeftBracket);
					setState(188);
					match(RightBracket);
					}
					}
					setState(191); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LeftBracket );
				setState(193);
				match(IDENTIFIER);
				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ASSIGN) {
					{
					{
					setState(194);
					match(ASSIGN);
					setState(195);
					expression(0);
					}
					}
					setState(200);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(201);
					match(Comma);
					setState(202);
					match(IDENTIFIER);
					setState(207);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==ASSIGN) {
						{
						{
						setState(203);
						match(ASSIGN);
						setState(204);
						expression(0);
						}
						}
						setState(209);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					setState(214);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(215);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(LeftBrace);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << LeftParen) | (1L << LeftBracket) | (1L << LeftBrace) | (1L << Sub) | (1L << Not) | (1L << Invert) | (1L << AddSelf) | (1L << SubSelf) | (1L << Return) | (1L << For) | (1L << If) | (1L << While) | (1L << Semi) | (1L << INT) | (1L << BOOL) | (1L << STR) | (1L << VOID) | (1L << NEW) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << THIS) | (1L << IDENTIFIER) | (1L << BREAK) | (1L << CONTINUE))) != 0)) {
				{
				{
				setState(220);
				statement();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayelementContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public ArrayelementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayelement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArrayelement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArrayelement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArrayelement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayelementContext arrayelement() throws RecognitionException {
		ArrayelementContext _localctx = new ArrayelementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_arrayelement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(IDENTIFIER);
			setState(233); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(229);
					match(LeftBracket);
					setState(230);
					expression(0);
					setState(231);
					match(RightBracket);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(235); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionParameterListContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public FunctionParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctionParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctionParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFunctionParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionParameterListContext functionParameterList() throws RecognitionException {
		FunctionParameterListContext _localctx = new FunctionParameterListContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_functionParameterList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			type();
			setState(242);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(238);
					match(LeftBracket);
					setState(239);
					match(RightBracket);
					}
					} 
				}
				setState(244);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(245);
			expression(0);
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(246);
				match(Comma);
				setState(247);
				type();
				setState(252);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(248);
						match(LeftBracket);
						setState(249);
						match(RightBracket);
						}
						} 
					}
					setState(254);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				setState(255);
				expression(0);
				}
				}
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionParameterValueContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public FunctionParameterValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionParameterValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctionParameterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctionParameterValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFunctionParameterValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionParameterValueContext functionParameterValue() throws RecognitionException {
		FunctionParameterValueContext _localctx = new FunctionParameterValueContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_functionParameterValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(262);
			expression(0);
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(263);
				match(Comma);
				setState(264);
				expression(0);
				}
				}
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForStmtContext extends StatementContext {
		public TerminalNode For() { return getToken(MxParser.For, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprStmtContext extends StatementContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStmtContext extends StatementContext {
		public TerminalNode While() { return getToken(MxParser.While, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VardefineStmtContext extends StatementContext {
		public VardefContext vardef() {
			return getRuleContext(VardefContext.class,0);
		}
		public VardefineStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVardefineStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVardefineStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVardefineStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStmtContext extends StatementContext {
		public StatementContext trueStmt;
		public StatementContext falseStmt;
		public TerminalNode If() { return getToken(MxParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassdefineStmtContext extends StatementContext {
		public ClassdefineContext classdefine() {
			return getRuleContext(ClassdefineContext.class,0);
		}
		public ClassdefineStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassdefineStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassdefineStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassdefineStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyStmtContext extends StatementContext {
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterEmptyStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitEmptyStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitEmptyStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockContext extends StatementContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public BlockContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CtrlStmtContext extends StatementContext {
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public TerminalNode BREAK() { return getToken(MxParser.BREAK, 0); }
		public TerminalNode CONTINUE() { return getToken(MxParser.CONTINUE, 0); }
		public CtrlStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCtrlStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCtrlStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCtrlStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStmtContext extends StatementContext {
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncdefineStmtContext extends StatementContext {
		public FuncdefineContext funcdefine() {
			return getRuleContext(FuncdefineContext.class,0);
		}
		public FuncdefineStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncdefineStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncdefineStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncdefineStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statement);
		int _la;
		try {
			setState(321);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				_localctx = new BlockContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				suite();
				}
				break;
			case 2:
				_localctx = new ClassdefineStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				classdefine();
				}
				break;
			case 3:
				_localctx = new VardefineStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(272);
				vardef();
				}
				break;
			case 4:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(273);
				match(While);
				setState(274);
				match(LeftParen);
				setState(275);
				expression(0);
				setState(276);
				match(RightParen);
				setState(277);
				statement();
				}
				break;
			case 5:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(279);
				match(If);
				setState(280);
				match(LeftParen);
				setState(281);
				expression(0);
				setState(282);
				match(RightParen);
				setState(283);
				((IfStmtContext)_localctx).trueStmt = statement();
				setState(286);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(284);
					match(T__2);
					setState(285);
					((IfStmtContext)_localctx).falseStmt = statement();
					}
					break;
				}
				}
				break;
			case 6:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(288);
				match(For);
				setState(289);
				match(LeftParen);
				setState(291);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(290);
					statement();
					}
					break;
				}
				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Sub) | (1L << Not) | (1L << Invert) | (1L << AddSelf) | (1L << SubSelf) | (1L << NEW) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << THIS) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(293);
					expression(0);
					}
				}

				setState(296);
				match(Semi);
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Sub) | (1L << Not) | (1L << Invert) | (1L << AddSelf) | (1L << SubSelf) | (1L << NEW) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << THIS) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(297);
					expression(0);
					}
				}

				setState(300);
				match(RightParen);
				setState(301);
				statement();
				}
				break;
			case 7:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(302);
				match(Return);
				setState(304);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Sub) | (1L << Not) | (1L << Invert) | (1L << AddSelf) | (1L << SubSelf) | (1L << NEW) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << THIS) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(303);
					expression(0);
					}
				}

				setState(306);
				match(Semi);
				}
				break;
			case 8:
				_localctx = new ExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(307);
				expression(0);
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(308);
					match(Comma);
					setState(309);
					expression(0);
					}
					}
					setState(314);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(315);
				match(Semi);
				}
				break;
			case 9:
				_localctx = new FuncdefineStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(317);
				funcdefine();
				}
				break;
			case 10:
				_localctx = new CtrlStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(318);
				_la = _input.LA(1);
				if ( !(_la==BREAK || _la==CONTINUE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(319);
				match(Semi);
				}
				break;
			case 11:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(320);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypeContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(MxParser.VOID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_returnType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case STR:
			case IDENTIFIER:
				{
				{
				setState(323);
				type();
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LeftBracket) {
					{
					{
					setState(324);
					match(LeftBracket);
					setState(325);
					match(RightBracket);
					}
					}
					setState(330);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case VOID:
				{
				setState(331);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncdefineContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public FunctionParameterListContext functionParameterList() {
			return getRuleContext(FunctionParameterListContext.class,0);
		}
		public FuncdefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcdefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncdefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncdefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncdefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncdefineContext funcdefine() throws RecognitionException {
		FuncdefineContext _localctx = new FuncdefineContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_funcdefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(334);
				returnType();
				}
				break;
			}
			setState(337);
			match(IDENTIFIER);
			setState(338);
			match(LeftParen);
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STR) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(339);
				functionParameterList();
				}
			}

			setState(342);
			match(RightParen);
			setState(343);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassdefineContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ClassdefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classdefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassdefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassdefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassdefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassdefineContext classdefine() throws RecognitionException {
		ClassdefineContext _localctx = new ClassdefineContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_classdefine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			match(T__3);
			setState(346);
			match(IDENTIFIER);
			setState(347);
			suite();
			setState(348);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallfunctionContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public FunctionParameterValueContext functionParameterValue() {
			return getRuleContext(FunctionParameterValueContext.class,0);
		}
		public CallfunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callfunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCallfunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCallfunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCallfunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallfunctionContext callfunction() throws RecognitionException {
		CallfunctionContext _localctx = new CallfunctionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_callfunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(350);
			match(IDENTIFIER);
			setState(351);
			match(LeftParen);
			setState(353);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Sub) | (1L << Not) | (1L << Invert) | (1L << AddSelf) | (1L << SubSelf) | (1L << NEW) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << THIS) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(352);
				functionParameterValue();
				}
			}

			setState(355);
			match(RightParen);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LamdaExprContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(MxParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxParser.RightBracket, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public List<TerminalNode> LeftParen() { return getTokens(MxParser.LeftParen); }
		public TerminalNode LeftParen(int i) {
			return getToken(MxParser.LeftParen, i);
		}
		public List<TerminalNode> RightParen() { return getTokens(MxParser.RightParen); }
		public TerminalNode RightParen(int i) {
			return getToken(MxParser.RightParen, i);
		}
		public TerminalNode And() { return getToken(MxParser.And, 0); }
		public FunctionParameterValueContext functionParameterValue() {
			return getRuleContext(FunctionParameterValueContext.class,0);
		}
		public FunctionParameterListContext functionParameterList() {
			return getRuleContext(FunctionParameterListContext.class,0);
		}
		public LamdaExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lamdaExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLamdaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLamdaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLamdaExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LamdaExprContext lamdaExpr() throws RecognitionException {
		LamdaExprContext _localctx = new LamdaExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_lamdaExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			match(LeftBracket);
			setState(359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==And) {
				{
				setState(358);
				match(And);
				}
			}

			setState(361);
			match(RightBracket);
			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LeftParen) {
				{
				setState(362);
				match(LeftParen);
				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STR) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(363);
					functionParameterList();
					}
				}

				setState(366);
				match(RightParen);
				}
			}

			setState(369);
			match(T__4);
			setState(370);
			suite();
			setState(371);
			match(LeftParen);
			setState(373);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Sub) | (1L << Not) | (1L << Invert) | (1L << AddSelf) | (1L << SubSelf) | (1L << NEW) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << THIS) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(372);
				functionParameterValue();
				}
			}

			setState(375);
			match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 16);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 7);
		case 7:
			return precpred(_ctx, 6);
		case 8:
			return precpred(_ctx, 5);
		case 9:
			return precpred(_ctx, 4);
		case 10:
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		case 12:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00019\u017a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u0000.\b\u0000\n\u0000\f\u00001\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002D\b\u0002"+
		"\u0003\u0002F\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002n\b\u0002"+
		"\n\u0002\f\u0002q\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003z\b\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004\u007f\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005\u0084\b\u0005\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\u0088\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u008e\b\u0007\u0001\u0007\u0004\u0007\u0091\b\u0007\u000b\u0007\f\u0007"+
		"\u0092\u0001\u0007\u0003\u0007\u0096\b\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u009c\b\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0005\n\u00a6\b\n\n\n\f\n\u00a9\t\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0005\n\u00af\b\n\n\n\f\n\u00b2\t\n\u0005\n\u00b4\b\n\n\n\f\n"+
		"\u00b7\t\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0004\n\u00be\b\n\u000b"+
		"\n\f\n\u00bf\u0001\n\u0001\n\u0001\n\u0005\n\u00c5\b\n\n\n\f\n\u00c8\t"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u00ce\b\n\n\n\f\n\u00d1\t\n"+
		"\u0005\n\u00d3\b\n\n\n\f\n\u00d6\t\n\u0001\n\u0001\n\u0003\n\u00da\b\n"+
		"\u0001\u000b\u0001\u000b\u0005\u000b\u00de\b\u000b\n\u000b\f\u000b\u00e1"+
		"\t\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0004\f\u00ea\b\f\u000b\f\f\f\u00eb\u0001\r\u0001\r\u0001\r\u0005\r"+
		"\u00f1\b\r\n\r\f\r\u00f4\t\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0005"+
		"\r\u00fb\b\r\n\r\f\r\u00fe\t\r\u0001\r\u0001\r\u0005\r\u0102\b\r\n\r\f"+
		"\r\u0105\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u010a\b\u000e"+
		"\n\u000e\f\u000e\u010d\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u011f\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u0124\b\u000f\u0001\u000f\u0003\u000f\u0127\b\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u012b\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u0131\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0005\u000f\u0137\b\u000f\n\u000f\f\u000f\u013a\t\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u0142\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0147"+
		"\b\u0010\n\u0010\f\u0010\u014a\t\u0010\u0001\u0010\u0003\u0010\u014d\b"+
		"\u0010\u0001\u0011\u0003\u0011\u0150\b\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0003\u0011\u0155\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u0162\b\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0003\u0014\u0168\b\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0003\u0014\u016d\b\u0014\u0001\u0014\u0003\u0014\u0170\b\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0176\b\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0000\u0001\u0004\u0015\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(\u0000\u000b\u0002\u0000\u000e\u000e\u0015\u0015\u0001\u0000\u0018"+
		"\u001a\u0001\u0000\u000f\u0011\u0001\u0000\r\u000e\u0001\u0000\u0016\u0017"+
		"\u0002\u0000\u001b\u001c\u001e\u001f\u0002\u0000\u001d\u001d  \u0001\u0000"+
		"\u0019\u001a\u0001\u0000/0\u0002\u0000*,44\u0001\u000089\u01ab\u0000/"+
		"\u0001\u0000\u0000\u0000\u00025\u0001\u0000\u0000\u0000\u0004E\u0001\u0000"+
		"\u0000\u0000\u0006y\u0001\u0000\u0000\u0000\b~\u0001\u0000\u0000\u0000"+
		"\n\u0083\u0001\u0000\u0000\u0000\f\u0087\u0001\u0000\u0000\u0000\u000e"+
		"\u0095\u0001\u0000\u0000\u0000\u0010\u0097\u0001\u0000\u0000\u0000\u0012"+
		"\u009f\u0001\u0000\u0000\u0000\u0014\u00d9\u0001\u0000\u0000\u0000\u0016"+
		"\u00db\u0001\u0000\u0000\u0000\u0018\u00e4\u0001\u0000\u0000\u0000\u001a"+
		"\u00ed\u0001\u0000\u0000\u0000\u001c\u0106\u0001\u0000\u0000\u0000\u001e"+
		"\u0141\u0001\u0000\u0000\u0000 \u014c\u0001\u0000\u0000\u0000\"\u014f"+
		"\u0001\u0000\u0000\u0000$\u0159\u0001\u0000\u0000\u0000&\u015e\u0001\u0000"+
		"\u0000\u0000(\u0165\u0001\u0000\u0000\u0000*.\u0003\u0014\n\u0000+.\u0003"+
		"\"\u0011\u0000,.\u0003$\u0012\u0000-*\u0001\u0000\u0000\u0000-+\u0001"+
		"\u0000\u0000\u0000-,\u0001\u0000\u0000\u0000.1\u0001\u0000\u0000\u0000"+
		"/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u000002\u0001\u0000\u0000"+
		"\u00001/\u0001\u0000\u0000\u000023\u0003\u0002\u0001\u000034\u0005\u0000"+
		"\u0000\u00014\u0001\u0001\u0000\u0000\u000056\u0005*\u0000\u000067\u0005"+
		"\u0001\u0000\u000078\u0005\u0006\u0000\u000089\u0005\u0007\u0000\u0000"+
		"9:\u0003\u0016\u000b\u0000:\u0003\u0001\u0000\u0000\u0000;<\u0006\u0002"+
		"\uffff\uffff\u0000<F\u0003\u0006\u0003\u0000=>\u0007\u0000\u0000\u0000"+
		">F\u0003\u0004\u0002\u000f?@\u0007\u0001\u0000\u0000@F\u0003\u0004\u0002"+
		"\rAD\u0003\u000e\u0007\u0000BD\u0003\u0010\b\u0000CA\u0001\u0000\u0000"+
		"\u0000CB\u0001\u0000\u0000\u0000DF\u0001\u0000\u0000\u0000E;\u0001\u0000"+
		"\u0000\u0000E=\u0001\u0000\u0000\u0000E?\u0001\u0000\u0000\u0000EC\u0001"+
		"\u0000\u0000\u0000Fo\u0001\u0000\u0000\u0000GH\n\u0010\u0000\u0000HI\u0005"+
		")\u0000\u0000In\u0003\u0004\u0002\u0011JK\n\f\u0000\u0000KL\u0007\u0002"+
		"\u0000\u0000Ln\u0003\u0004\u0002\rMN\n\u000b\u0000\u0000NO\u0007\u0003"+
		"\u0000\u0000On\u0003\u0004\u0002\fPQ\n\n\u0000\u0000QR\u0007\u0004\u0000"+
		"\u0000Rn\u0003\u0004\u0002\u000bST\n\t\u0000\u0000TU\u0005\u0014\u0000"+
		"\u0000Un\u0003\u0004\u0002\nVW\n\b\u0000\u0000WX\u0005\u0013\u0000\u0000"+
		"Xn\u0003\u0004\u0002\tYZ\n\u0007\u0000\u0000Z[\u0005\u0012\u0000\u0000"+
		"[n\u0003\u0004\u0002\b\\]\n\u0006\u0000\u0000]^\u0007\u0005\u0000\u0000"+
		"^n\u0003\u0004\u0002\u0007_`\n\u0005\u0000\u0000`a\u0007\u0006\u0000\u0000"+
		"an\u0003\u0004\u0002\u0006bc\n\u0004\u0000\u0000cd\u0005!\u0000\u0000"+
		"dn\u0003\u0004\u0002\u0005ef\n\u0003\u0000\u0000fg\u0005\"\u0000\u0000"+
		"gn\u0003\u0004\u0002\u0004hi\n\u0002\u0000\u0000ij\u0005\f\u0000\u0000"+
		"jn\u0003\u0004\u0002\u0002kl\n\u000e\u0000\u0000ln\u0007\u0007\u0000\u0000"+
		"mG\u0001\u0000\u0000\u0000mJ\u0001\u0000\u0000\u0000mM\u0001\u0000\u0000"+
		"\u0000mP\u0001\u0000\u0000\u0000mS\u0001\u0000\u0000\u0000mV\u0001\u0000"+
		"\u0000\u0000mY\u0001\u0000\u0000\u0000m\\\u0001\u0000\u0000\u0000m_\u0001"+
		"\u0000\u0000\u0000mb\u0001\u0000\u0000\u0000me\u0001\u0000\u0000\u0000"+
		"mh\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000nq\u0001\u0000\u0000"+
		"\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000p\u0005\u0001"+
		"\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000rs\u0005\u0006\u0000\u0000"+
		"st\u0003\u0004\u0002\u0000tu\u0005\u0007\u0000\u0000uz\u0001\u0000\u0000"+
		"\u0000vz\u0003\n\u0005\u0000wz\u0003\b\u0004\u0000xz\u0003\f\u0006\u0000"+
		"yr\u0001\u0000\u0000\u0000yv\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000"+
		"\u0000yx\u0001\u0000\u0000\u0000z\u0007\u0001\u0000\u0000\u0000{\u007f"+
		"\u00051\u0000\u0000|\u007f\u00052\u0000\u0000}\u007f\u0007\b\u0000\u0000"+
		"~{\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~}\u0001\u0000\u0000"+
		"\u0000\u007f\t\u0001\u0000\u0000\u0000\u0080\u0084\u00054\u0000\u0000"+
		"\u0081\u0084\u00053\u0000\u0000\u0082\u0084\u0003\u0018\f\u0000\u0083"+
		"\u0080\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0083"+
		"\u0082\u0001\u0000\u0000\u0000\u0084\u000b\u0001\u0000\u0000\u0000\u0085"+
		"\u0088\u0003&\u0013\u0000\u0086\u0088\u0003(\u0014\u0000\u0087\u0085\u0001"+
		"\u0000\u0000\u0000\u0087\u0086\u0001\u0000\u0000\u0000\u0088\r\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0005.\u0000\u0000\u008a\u0090\u0003\u0012\t"+
		"\u0000\u008b\u008d\u0005\b\u0000\u0000\u008c\u008e\u0003\u0004\u0002\u0000"+
		"\u008d\u008c\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000"+
		"\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0091\u0005\t\u0000\u0000\u0090"+
		"\u008b\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092"+
		"\u0090\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093"+
		"\u0096\u0001\u0000\u0000\u0000\u0094\u0096\u0005\u0002\u0000\u0000\u0095"+
		"\u0089\u0001\u0000\u0000\u0000\u0095\u0094\u0001\u0000\u0000\u0000\u0096"+
		"\u000f\u0001\u0000\u0000\u0000\u0097\u0098\u0005.\u0000\u0000\u0098\u0099"+
		"\u0003\u0012\t\u0000\u0099\u009b\u0005\u0006\u0000\u0000\u009a\u009c\u0003"+
		"\u001c\u000e\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009b\u009c\u0001"+
		"\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009e\u0005"+
		"\u0007\u0000\u0000\u009e\u0011\u0001\u0000\u0000\u0000\u009f\u00a0\u0007"+
		"\t\u0000\u0000\u00a0\u0013\u0001\u0000\u0000\u0000\u00a1\u00a2\u0003\u0012"+
		"\t\u0000\u00a2\u00a7\u00054\u0000\u0000\u00a3\u00a4\u0005\f\u0000\u0000"+
		"\u00a4\u00a6\u0003\u0004\u0002\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a9\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00b5\u0001\u0000\u0000\u0000"+
		"\u00a9\u00a7\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005(\u0000\u0000\u00ab"+
		"\u00b0\u00054\u0000\u0000\u00ac\u00ad\u0005\f\u0000\u0000\u00ad\u00af"+
		"\u0003\u0004\u0002\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00af\u00b2"+
		"\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b4\u0001\u0000\u0000\u0000\u00b2\u00b0"+
		"\u0001\u0000\u0000\u0000\u00b3\u00aa\u0001\u0000\u0000\u0000\u00b4\u00b7"+
		"\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b5\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b8\u0001\u0000\u0000\u0000\u00b7\u00b5"+
		"\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005\'\u0000\u0000\u00b9\u00da\u0001"+
		"\u0000\u0000\u0000\u00ba\u00bd\u0003\u0012\t\u0000\u00bb\u00bc\u0005\b"+
		"\u0000\u0000\u00bc\u00be\u0005\t\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000"+
		"\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000"+
		"\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000"+
		"\u0000\u00c1\u00c6\u00054\u0000\u0000\u00c2\u00c3\u0005\f\u0000\u0000"+
		"\u00c3\u00c5\u0003\u0004\u0002\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00d4\u0001\u0000\u0000\u0000"+
		"\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005(\u0000\u0000\u00ca"+
		"\u00cf\u00054\u0000\u0000\u00cb\u00cc\u0005\f\u0000\u0000\u00cc\u00ce"+
		"\u0003\u0004\u0002\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000\u00ce\u00d1"+
		"\u0001\u0000\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00cf\u00d0"+
		"\u0001\u0000\u0000\u0000\u00d0\u00d3\u0001\u0000\u0000\u0000\u00d1\u00cf"+
		"\u0001\u0000\u0000\u0000\u00d2\u00c9\u0001\u0000\u0000\u0000\u00d3\u00d6"+
		"\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5"+
		"\u0001\u0000\u0000\u0000\u00d5\u00d7\u0001\u0000\u0000\u0000\u00d6\u00d4"+
		"\u0001\u0000\u0000\u0000\u00d7\u00d8\u0005\'\u0000\u0000\u00d8\u00da\u0001"+
		"\u0000\u0000\u0000\u00d9\u00a1\u0001\u0000\u0000\u0000\u00d9\u00ba\u0001"+
		"\u0000\u0000\u0000\u00da\u0015\u0001\u0000\u0000\u0000\u00db\u00df\u0005"+
		"\n\u0000\u0000\u00dc\u00de\u0003\u001e\u000f\u0000\u00dd\u00dc\u0001\u0000"+
		"\u0000\u0000\u00de\u00e1\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000"+
		"\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u00e2\u0001\u0000"+
		"\u0000\u0000\u00e1\u00df\u0001\u0000\u0000\u0000\u00e2\u00e3\u0005\u000b"+
		"\u0000\u0000\u00e3\u0017\u0001\u0000\u0000\u0000\u00e4\u00e9\u00054\u0000"+
		"\u0000\u00e5\u00e6\u0005\b\u0000\u0000\u00e6\u00e7\u0003\u0004\u0002\u0000"+
		"\u00e7\u00e8\u0005\t\u0000\u0000\u00e8\u00ea\u0001\u0000\u0000\u0000\u00e9"+
		"\u00e5\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001\u0000\u0000\u0000\u00eb"+
		"\u00e9\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec"+
		"\u0019\u0001\u0000\u0000\u0000\u00ed\u00f2\u0003\u0012\t\u0000\u00ee\u00ef"+
		"\u0005\b\u0000\u0000\u00ef\u00f1\u0005\t\u0000\u0000\u00f0\u00ee\u0001"+
		"\u0000\u0000\u0000\u00f1\u00f4\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f3\u00f5\u0001"+
		"\u0000\u0000\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f5\u0103\u0003"+
		"\u0004\u0002\u0000\u00f6\u00f7\u0005(\u0000\u0000\u00f7\u00fc\u0003\u0012"+
		"\t\u0000\u00f8\u00f9\u0005\b\u0000\u0000\u00f9\u00fb\u0005\t\u0000\u0000"+
		"\u00fa\u00f8\u0001\u0000\u0000\u0000\u00fb\u00fe\u0001\u0000\u0000\u0000"+
		"\u00fc\u00fa\u0001\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000"+
		"\u00fd\u00ff\u0001\u0000\u0000\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000"+
		"\u00ff\u0100\u0003\u0004\u0002\u0000\u0100\u0102\u0001\u0000\u0000\u0000"+
		"\u0101\u00f6\u0001\u0000\u0000\u0000\u0102\u0105\u0001\u0000\u0000\u0000"+
		"\u0103\u0101\u0001\u0000\u0000\u0000\u0103\u0104\u0001\u0000\u0000\u0000"+
		"\u0104\u001b\u0001\u0000\u0000\u0000\u0105\u0103\u0001\u0000\u0000\u0000"+
		"\u0106\u010b\u0003\u0004\u0002\u0000\u0107\u0108\u0005(\u0000\u0000\u0108"+
		"\u010a\u0003\u0004\u0002\u0000\u0109\u0107\u0001\u0000\u0000\u0000\u010a"+
		"\u010d\u0001\u0000\u0000\u0000\u010b\u0109\u0001\u0000\u0000\u0000\u010b"+
		"\u010c\u0001\u0000\u0000\u0000\u010c\u001d\u0001\u0000\u0000\u0000\u010d"+
		"\u010b\u0001\u0000\u0000\u0000\u010e\u0142\u0003\u0016\u000b\u0000\u010f"+
		"\u0142\u0003$\u0012\u0000\u0110\u0142\u0003\u0014\n\u0000\u0111\u0112"+
		"\u0005&\u0000\u0000\u0112\u0113\u0005\u0006\u0000\u0000\u0113\u0114\u0003"+
		"\u0004\u0002\u0000\u0114\u0115\u0005\u0007\u0000\u0000\u0115\u0116\u0003"+
		"\u001e\u000f\u0000\u0116\u0142\u0001\u0000\u0000\u0000\u0117\u0118\u0005"+
		"%\u0000\u0000\u0118\u0119\u0005\u0006\u0000\u0000\u0119\u011a\u0003\u0004"+
		"\u0002\u0000\u011a\u011b\u0005\u0007\u0000\u0000\u011b\u011e\u0003\u001e"+
		"\u000f\u0000\u011c\u011d\u0005\u0003\u0000\u0000\u011d\u011f\u0003\u001e"+
		"\u000f\u0000\u011e\u011c\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000"+
		"\u0000\u0000\u011f\u0142\u0001\u0000\u0000\u0000\u0120\u0121\u0005$\u0000"+
		"\u0000\u0121\u0123\u0005\u0006\u0000\u0000\u0122\u0124\u0003\u001e\u000f"+
		"\u0000\u0123\u0122\u0001\u0000\u0000\u0000\u0123\u0124\u0001\u0000\u0000"+
		"\u0000\u0124\u0126\u0001\u0000\u0000\u0000\u0125\u0127\u0003\u0004\u0002"+
		"\u0000\u0126\u0125\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000"+
		"\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u012a\u0005\'\u0000\u0000"+
		"\u0129\u012b\u0003\u0004\u0002\u0000\u012a\u0129\u0001\u0000\u0000\u0000"+
		"\u012a\u012b\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000\u0000"+
		"\u012c\u012d\u0005\u0007\u0000\u0000\u012d\u0142\u0003\u001e\u000f\u0000"+
		"\u012e\u0130\u0005#\u0000\u0000\u012f\u0131\u0003\u0004\u0002\u0000\u0130"+
		"\u012f\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000\u0131"+
		"\u0132\u0001\u0000\u0000\u0000\u0132\u0142\u0005\'\u0000\u0000\u0133\u0138"+
		"\u0003\u0004\u0002\u0000\u0134\u0135\u0005(\u0000\u0000\u0135\u0137\u0003"+
		"\u0004\u0002\u0000\u0136\u0134\u0001\u0000\u0000\u0000\u0137\u013a\u0001"+
		"\u0000\u0000\u0000\u0138\u0136\u0001\u0000\u0000\u0000\u0138\u0139\u0001"+
		"\u0000\u0000\u0000\u0139\u013b\u0001\u0000\u0000\u0000\u013a\u0138\u0001"+
		"\u0000\u0000\u0000\u013b\u013c\u0005\'\u0000\u0000\u013c\u0142\u0001\u0000"+
		"\u0000\u0000\u013d\u0142\u0003\"\u0011\u0000\u013e\u013f\u0007\n\u0000"+
		"\u0000\u013f\u0142\u0005\'\u0000\u0000\u0140\u0142\u0005\'\u0000\u0000"+
		"\u0141\u010e\u0001\u0000\u0000\u0000\u0141\u010f\u0001\u0000\u0000\u0000"+
		"\u0141\u0110\u0001\u0000\u0000\u0000\u0141\u0111\u0001\u0000\u0000\u0000"+
		"\u0141\u0117\u0001\u0000\u0000\u0000\u0141\u0120\u0001\u0000\u0000\u0000"+
		"\u0141\u012e\u0001\u0000\u0000\u0000\u0141\u0133\u0001\u0000\u0000\u0000"+
		"\u0141\u013d\u0001\u0000\u0000\u0000\u0141\u013e\u0001\u0000\u0000\u0000"+
		"\u0141\u0140\u0001\u0000\u0000\u0000\u0142\u001f\u0001\u0000\u0000\u0000"+
		"\u0143\u0148\u0003\u0012\t\u0000\u0144\u0145\u0005\b\u0000\u0000\u0145"+
		"\u0147\u0005\t\u0000\u0000\u0146\u0144\u0001\u0000\u0000\u0000\u0147\u014a"+
		"\u0001\u0000\u0000\u0000\u0148\u0146\u0001\u0000\u0000\u0000\u0148\u0149"+
		"\u0001\u0000\u0000\u0000\u0149\u014d\u0001\u0000\u0000\u0000\u014a\u0148"+
		"\u0001\u0000\u0000\u0000\u014b\u014d\u0005-\u0000\u0000\u014c\u0143\u0001"+
		"\u0000\u0000\u0000\u014c\u014b\u0001\u0000\u0000\u0000\u014d!\u0001\u0000"+
		"\u0000\u0000\u014e\u0150\u0003 \u0010\u0000\u014f\u014e\u0001\u0000\u0000"+
		"\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0151\u0001\u0000\u0000"+
		"\u0000\u0151\u0152\u00054\u0000\u0000\u0152\u0154\u0005\u0006\u0000\u0000"+
		"\u0153\u0155\u0003\u001a\r\u0000\u0154\u0153\u0001\u0000\u0000\u0000\u0154"+
		"\u0155\u0001\u0000\u0000\u0000\u0155\u0156\u0001\u0000\u0000\u0000\u0156"+
		"\u0157\u0005\u0007\u0000\u0000\u0157\u0158\u0003\u0016\u000b\u0000\u0158"+
		"#\u0001\u0000\u0000\u0000\u0159\u015a\u0005\u0004\u0000\u0000\u015a\u015b"+
		"\u00054\u0000\u0000\u015b\u015c\u0003\u0016\u000b\u0000\u015c\u015d\u0005"+
		"\'\u0000\u0000\u015d%\u0001\u0000\u0000\u0000\u015e\u015f\u00054\u0000"+
		"\u0000\u015f\u0161\u0005\u0006\u0000\u0000\u0160\u0162\u0003\u001c\u000e"+
		"\u0000\u0161\u0160\u0001\u0000\u0000\u0000\u0161\u0162\u0001\u0000\u0000"+
		"\u0000\u0162\u0163\u0001\u0000\u0000\u0000\u0163\u0164\u0005\u0007\u0000"+
		"\u0000\u0164\'\u0001\u0000\u0000\u0000\u0165\u0167\u0005\b\u0000\u0000"+
		"\u0166\u0168\u0005\u0014\u0000\u0000\u0167\u0166\u0001\u0000\u0000\u0000"+
		"\u0167\u0168\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000\u0000\u0000"+
		"\u0169\u016f\u0005\t\u0000\u0000\u016a\u016c\u0005\u0006\u0000\u0000\u016b"+
		"\u016d\u0003\u001a\r\u0000\u016c\u016b\u0001\u0000\u0000\u0000\u016c\u016d"+
		"\u0001\u0000\u0000\u0000\u016d\u016e\u0001\u0000\u0000\u0000\u016e\u0170"+
		"\u0005\u0007\u0000\u0000\u016f\u016a\u0001\u0000\u0000\u0000\u016f\u0170"+
		"\u0001\u0000\u0000\u0000\u0170\u0171\u0001\u0000\u0000\u0000\u0171\u0172"+
		"\u0005\u0005\u0000\u0000\u0172\u0173\u0003\u0016\u000b\u0000\u0173\u0175"+
		"\u0005\u0006\u0000\u0000\u0174\u0176\u0003\u001c\u000e\u0000\u0175\u0174"+
		"\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176\u0177"+
		"\u0001\u0000\u0000\u0000\u0177\u0178\u0005\u0007\u0000\u0000\u0178)\u0001"+
		"\u0000\u0000\u0000,-/CEmoy~\u0083\u0087\u008d\u0092\u0095\u009b\u00a7"+
		"\u00b0\u00b5\u00bf\u00c6\u00cf\u00d4\u00d9\u00df\u00eb\u00f2\u00fc\u0103"+
		"\u010b\u011e\u0123\u0126\u012a\u0130\u0138\u0141\u0148\u014c\u014f\u0154"+
		"\u0161\u0167\u016c\u016f\u0175";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
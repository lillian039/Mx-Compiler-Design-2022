grammar Mx;   //名称需要和文件名一致
import MxLexerRules;
program : (vardef|funcdefine|classdefine)* EOF;   //解决问题: no viable alternative at input '<EOF>'

expression
    : primary                                          #atomExpr
    | '(' expression ')'                               #bracketExpr
    | expression op='.' callfunction                   #dotFuncExpr
    | expression op='.' variable                       #dotVarExpr
    | expression ('[' expression ']')+                 #arrayExpr
    | op=('!'|'-')  expression                         #cellExpr
    | expression op=('++'|'--')                        #delayCellExpr
    | op=('~'|'++'|'--') expression                    #cellExpr
    | expression op=('*'|'/'|'%') expression           #binaryExpr
    | expression op=('+'|'-') expression               #binaryExpr
    | expression op=('<<'|'>>') expression             #binaryExpr
    | expression op='&' expression                     #binaryExpr
    | expression op='^' expression                     #binaryExpr
    | expression op='|' expression                     #binaryExpr
    | expression op=('>'|'<'|'>='|'<=') expression     #binaryExpr
    | expression op=('=='|'!=') expression             #binaryExpr
    | expression op= '&&' expression                   #binaryExpr
    | expression op= '||' expression                   #binaryExpr
    | <assoc=right> expression '=' expression          #assignExpr
    | (newArrExpr|newClassExpr)                        #newExpr
    ;

primary
    : variable
    | constant
    | funVal
    ;

constant
    : INTEGER
    | STRING
    | (TRUE | FALSE)
    | NULL
    ;

variable
    : IDENTIFIER
    | THIS
    ;

funVal
    : callfunction
    | lamdaExpr
    ;

newArrExprAtom:'['(expression)?']';

newArrExpr : ('new' (INT | BOOL | STR | IDENTIFIER) (newArrExprAtom)+);

newClassExpr: 'new' IDENTIFIER ('('')')? ;

type:(INT | BOOL | STR | IDENTIFIER)('['']')*;

vardef: type singleVarDef(','singleVarDef)*';';

suite: '{' statement* '}';
//=======definitions=======


paralistVarDef: type IDENTIFIER;
singleVarDef:IDENTIFIER ('=' expression)?;
functionParameterList: paralistVarDef(','paralistVarDef)*;
functionParameterValue:(expression(','expression)*);


//=======statements=======
statement
    : suite                                                                 #block
    | vardef                                                                #vardefineStmt
    | 'while' '('expression ')' statement                                   #whileStmt
    |  If  '('  expression  ')'trueStmt=statement
                      ( Else falseStmt=statement)?                          #ifStmt
    | 'for' '(' initStmt=statement?
      condition=expression? ';' step=expression? ')' body=statement        #forStmt
    | 'return' expression? ';'                                              #returnStmt
    | expression';'                                                         #exprStmt
    | funcdefine                                                            #funcdefineStmt
    | (BREAK|CONTINUE) ';'                                                  #ctrlStmt
    | ';'                                                                   #emptyStmt
    ;

//======function=======

returnType:(type|'void');

funcdefine: returnType? (IDENTIFIER) '(' functionParameterList?')'  suite;

classdefine : 'class' IDENTIFIER suite ';';

callfunction: (IDENTIFIER '('functionParameterValue? ')');//?

lamdaExpr:'['('&')?']'('(' functionParameterList?')')?'->' suite '('functionParameterValue?')';
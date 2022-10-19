grammar Mx;   //名称需要和文件名一致
import MxLexerRules;
program : (vardef|funcdefine|classdefine)*mainfunc EOF;   //解决问题: no viable alternative at input '<EOF>'

//=======expressions=======

mainfunc :INT 'main' '(' ')'  suite ;

expression
    : primary                                          #atomExpr
    | expression op='.' expression                     #dotExpr
    | op=('!'|'-')  expression                         #cellExpr
    | expression op=('++'|'--')                        #cellExpr
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
    : '(' expression ')'
    | variable
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
    | arrayelement
    ;

funVal
    : callfunction
    | lamdaExpr
    ;

newArrExpr : ('new' type ('['(expression)?']')+)|'null';

newClassExpr: 'new' type '('functionParameterValue?')' ;

type:INT | BOOL | STR | IDENTIFIER;

vardef
    :type  IDENTIFIER ('=' expression)*(','IDENTIFIER ('=' expression)*)*';'          #signalvar
    |type ('['']')+ IDENTIFIER ('=' expression)*(','IDENTIFIER ('=' expression)*)*';' #arrayvar//why <assoc=right>???
    ;

suite: '{' statement* '}';
//=======definitions=======
arrayelement: IDENTIFIER ('[' expression ']')+;

functionParameterList: type ('['']')* expression(','type ('['']')* expression)*;
functionParameterValue:(expression(','expression)*);

//=======statements=======
statement
    : suite                                                                 #block
    | classdefine                                                           #classdefineStmt
    | vardef                                                                #vardefineStmt
    | 'while' '('expression ')' statement                                   #whileStmt
    |  'if'  '('  expression  ')'trueStmt=statement
                      ( 'else' falseStmt=statement)?                        #ifStmt
    | 'for' '(' (statement)?
      expression? ';' (expression)? ')' statement                           #forStmt
    | 'return' expression? ';'                                              #returnStmt
    | expression(','expression)* ';'                                        #exprStmt
    | funcdefine                                                            #funcdefineStmt
    | (BREAK|CONTINUE) ';'                                                  #ctrlStmt
    | ';'                                                                   #emptyStmt
    ;

//======function=======

returnType:((type('['']')*)|'void');

funcdefine: returnType? IDENTIFIER '(' functionParameterList?')'  suite;

classdefine : 'class' IDENTIFIER suite ';';

callfunction: (IDENTIFIER '('functionParameterValue? ')');//?

lamdaExpr:'['('&')?']'('(' functionParameterList?')')?'->' suite '('functionParameterValue?')';
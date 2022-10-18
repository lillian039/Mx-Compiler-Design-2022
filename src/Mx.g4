grammar Mx;   //名称需要和文件名一致
import MxLexerRules;
program : (vardef|funcdefine|classdefine)*mainfunc EOF;   //解决问题: no viable alternative at input '<EOF>'

//=======expressions=======

mainfunc :INT 'main' '(' ')'  suite ;

expression
    : primary                                          #atomExpr
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
    ;

variable
    : IDENTIFIER
    | innermember
    | classmember
    | THIS
    | arrayelement
    ;

funVal
    : callfunction
    | lamdaExpr
    | callclassfunction
    ;

newArrExpr : ('new' type ('['(expression)?']')+)|'null';

newClassExpr: 'new' IDENTIFIER '('')' ;

type:INT | BOOL | STR | IDENTIFIER;

vardef
    :<assoc=right> type  IDENTIFIER ('=' expression)*(','IDENTIFIER ('=' expression)*)*';'          #signalvar
    |<assoc=right> type ('['']')+ IDENTIFIER ('=' expression)*(','IDENTIFIER ('=' expression)*)*';' #arrayvar
    ;

suite: '{' statement* '}';
//=======definitions=======
arrayelement: IDENTIFIER ('[' expression ']')+;

functionParameterList: type ('['']')* expression(','type ('['']')* expression)*;

//=======statements=======
statement
    : suite                                                                 #block
    | classdefine                                                           #classdefineStmt
    | vardef                                                                #vardefineStmt
    | 'while' '('expression ')' statement                                   #whileStmt
    |  'if'  '('  expression  ')'trueStmt=statement
                      ( 'else' falseStmt=statement)?                        #ifStmt
    | 'for' '(' (vardef|expression)?';'
      expression? ';' (expression)? ')' statement                           #forStmt
    | 'return' expression? ';'                                              #returnStmt
    | expression(','expression)* ';'                                        #exprStmt
    | funcdefine                                                            #funcdefineStmt
    | BREAK ';'                                                             #breakStmt
    | CONTINUE ';'                                                          #continueStmt
    | ';'                                                                   #emptyStmt
    ;

//======function=======

funcdefine: ((type('['']')*)|'void')? IDENTIFIER '(' functionParameterList?')'  suite;

classdefine : 'class' IDENTIFIER suite ';';

callfunction: (IDENTIFIER '('expression?(','(expression))* ')');

callclassfunction:IDENTIFIER'.'callfunction;

lamdaglobe:'[''&'']'('(' functionParameterList?')')?'->' suite '('(expression(','expression)*)?')';

lamdainner:'['']'('(' functionParameterList?')')?'->' suite'('(expression(','expression)*)?')';

lamdaExpr:lamdaglobe|lamdainner;

classmember:(arrayelement|IDENTIFIER|callfunction)('.'(arrayelement|IDENTIFIER|callfunction))+;

innermember:THIS('.'(arrayelement|IDENTIFIER|callfunction))+;






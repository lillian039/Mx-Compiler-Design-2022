grammar Mx;   //名称需要和文件名一致
import MxLexerRules;
s : (vardef|funcdefine|classdefine)*mainfunc EOF;   //解决问题: no viable alternative at input '<EOF>'

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
    | expression op=('>'|'<'|'>='|'<=') expression     #binaryCmp
    | expression op=('=='|'!=') expression             #binaryCmp
    | expression op= '&&' expression                   #binaryBool
    | expression op= '||' expression                   #binaryBool
    | <assoc=right> expression '=' expression          #assignExpr
    | (('new' type ('['(expression)?']')+)|'null')     #newExpr
    ;

vardef
    :<assoc=right> type  IDENTIFIER ('=' expression)*(','IDENTIFIER ('=' expression)*)*';'          #signalvar
    |<assoc=right> type ('['']')+ IDENTIFIER ('=' expression)*(','IDENTIFIER ('=' expression)*)*';' #arrayvar
    ;

/*assigndef
    :<assoc=right> (IDENTIFIER|arrayelement) ('=' expression)*(','IDENTIFIER ('=' expression)*)*
    ;*/

primary
    : '(' expression ')'
    | IDENTIFIER
    | INTEGER
    | STRING
    | THIS
    | (TRUE | FALSE)
    | innermember
    | classmember
    | arrayelement
    | callfunction
    | lamdaExpr
    | callclassfunction
    | newclass
    ;

type:INT | BOOL | STR | IDENTIFIER;

suite: '{' statement* '}';
//=======definitions=======
arrayelement: IDENTIFIER ('[' expression ']')+;

functionParameterList: type ('['']')* expression(','type ('['']')* expression)*;

//=======statements=======
statement
    : suite                                                                 #block
    | classdefine                                                           #classdefineStmt
    | vardef                                                                #vardefineStmt
   // | assigndef ';'                                                         #assignStmt
    | 'while' '('expression ')' statement                                   #whileStmt
    |  'if'  '('  expression  ')'trueStmt=statement
                      ( 'else' falseStmt=statement)?                        #ifStmt
    | 'for' '(' (vardef|expression)?';'
      expression? ';' (expression)? ')' statement                 #forStmt
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

newclass:'new' IDENTIFIER '('')';

lamdaglobe:'[''&'']'('(' functionParameterList?')')?'->' suite '('(expression(','expression)*)?')';

lamdainner:'['']'('(' functionParameterList?')')?'->' suite'('(expression(','expression)*)?')';

lamdaExpr:lamdaglobe|lamdainner;

classmember:(arrayelement|IDENTIFIER|callfunction)('.'(arrayelement|IDENTIFIER|callfunction))+;

innermember:THIS('.'(arrayelement|IDENTIFIER|callfunction))+;






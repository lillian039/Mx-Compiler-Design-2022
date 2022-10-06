grammar Mx;   //名称需要和文件名一致

s : (classdefine|funcdefine)*mainfuncdefine EOF;   //解决问题: no viable alternative at input '<EOF>'

//=======expressions=======
stringExpr
    : stringExpr  '+'  stringExpr
    | STRING
    | IDENTIFIER
    | CLASSMEMBER
    | toString
    | getString
    | subString
    | callclassfunction
    | callfunction
;

intExpr
    : intExpr ('++'|'--')// -a 与 1-a如何区分？
    | ('~'|'++'|'--') intExpr
    | intExpr ('*'|'/'|'%') intExpr
    | intExpr ('+'|'-') intExpr
    | intExpr ('<<'|'>>') intExpr
    | intExpr '&' intExpr
    | intExpr '^' intExpr
    | intExpr '|' intExpr
    | '('  intExpr ')'
    | INTEGER
    | IDENTIFIER
    | CLASSMEMBER
    | getInt
    | (intLength | parseInt | intOrd)
    | callclassfunction
    | callfunction
;

boolExpr
    :'(' boolExpr ')'
    |'!'boolExpr
    | intExpr ('>'|'<'|'>='|'<=') intExpr
    | stringExpr ('>'|'<'|'>='|'<=') stringExpr
    | intExpr ('=='|'!=') intExpr
    | stringExpr ('=='|'!=') stringExpr
    | boolExpr ('&&') boolExpr
    | boolExpr ('||')boolExpr
    | BOOLEN
    | IDENTIFIER
    | callclassfunction
    | callfunction
;

classExpr
    : IDENTIFIER
    | callclassfunction
    | callfunction
    ;

expression:
    boolExpr|
    intExpr|
    stringExpr;
//=======definitions=======
intvardefine: 'int' IDENTIFIER ('='intExpr)?;
boolvardefine:'bool' IDENTIFIER ('='boolExpr)?;
stringvardefine:'string' IDENTIFIER ('=' stringExpr)?;
classvardefine:IDENTIFIER IDENTIFIER;

intarraydefine: 'int' ( '[' ']')+ IDENTIFIER ('='(('new'('['(INTEGER)?']')+)|'null'))?;
boolarraydefine: 'bool' ( '[' ']')+ IDENTIFIER ('='(('new'('['(INTEGER)?']')+)|'null'))?;
stringarraydefine: 'string' ( '[' ']')+ IDENTIFIER ('='(('new'('['(INTEGER)?']')+)|'null'))?;

vardefine :  intvardefine|
             boolvardefine|
             stringvardefine|
             intarraydefine|
             boolarraydefine|
             stringarraydefine|
             classvardefine;

funcdefine: 'int' IDENTIFIER '(' (vardefine(','vardefine)*)?  ')'  '{' (statement|intreturnstate)* intreturnstate '}'
            |'bool' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement|boolreturnstate)* boolreturnstate '}'
            |'string' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement|stringreturnstate)* stringreturnstate '}'
            |'void' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement|voidreturnstate)* voidreturnstate '}'
            | IDENTIFIER IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* classreturnstate'}';//class??

mainfuncdefine :'int' 'main' '(' (vardefine(','vardefine)*)?  ')'  '{' (statement)* 'return' intExpr ';' '}' ;

classdefine: 'class' IDENTIFIER '{'
    vardefinestate*
    (IDENTIFIER '(' ')' '{' statement*'}')?
    funcdefine*
'}' ';';

//====class=======
CLASSMEMBER:IDENTIFIER'.'IDENTIFIER;
callclassfunction:IDENTIFIER'.'callfuncstate;
//=======statements=======
statement:vardefinestate|varassignstate|whilestate|conditionstate|expressstate|forstate|innerfuncstatement|callfuncstate;


vardefinestate :(intvardefine|intarraydefine)(',' (intvardefine|intarraydefine))* ';' |
                (boolvardefine|boolarraydefine)(','(boolvardefine|boolarraydefine))*';'|
                (stringvardefine|stringarraydefine)(','(stringvardefine|stringarraydefine))*';';

arrayassign: IDENTIFIER ('['(INTEGER)']')+'='(expression|('new'('int'|'bool'|'string')('['(INTEGER)?']')+));
varassign: IDENTIFIER '='(expression);
varassignstate : (arrayassign|varassign)(',' (varassign|arrayassign))* ';' ;

conditionstate: 'if'  '('  boolExpr  ')'(statement | '{' statement* '}') ( 'else' (statement | '{' statement* '}'))?;

whilestate: 'while' '(' boolExpr ')'((statement|breakstate|continuestate) | '{' (statement|breakstate|continuestate)* '}') ;

expressstate : expression';';

forstate : 'for''('('int' IDENTIFIER ('='(intExpr))?(',' IDENTIFIER ?('='(intExpr))?)*)? ';'(boolExpr)?';' (intExpr)?')'
            ((statement|breakstate|continuestate) | '{' (statement|breakstate|continuestate)* '}');

breakstate: BREAK ';';

continuestate: CONTINUE ';';

innerfuncstatement: (printInt|printlnInt|printStr|printlnStr|getString|getInt|toString)';';

callfunction:(innerfuncstatement | (IDENTIFIER '('(boolExpr|stringExpr|intExpr)?(','(boolExpr|stringExpr|intExpr))* ')'));

callfuncstate :(callfunction|innerfuncstatement)';';

//=====return======
intreturnstate : 'return' intExpr ';';
boolreturnstate : 'return' boolExpr ';';
stringreturnstate : 'return' stringExpr ';';
voidreturnstate: 'return'  ';';
classreturnstate: 'return' classExpr ';';

//=======innerfunctions=======
printStr : 'print' '(' stringExpr ')';
printlnStr : 'println' '(' stringExpr ')';
printInt : 'printInt' '(' intExpr ')';
printlnInt : 'printlnInt' '(' intExpr ')';
getString : 'getString' '(' ')';
getInt : 'getInt' '(' ')';
toString : 'toString' '(' intExpr')';
intLength : stringExpr'.''length''('')';
subString : (STRING|'('stringExpr')')'.''substring' '(' intExpr',' intExpr')';
parseInt : stringExpr '.' 'parseInt' '(' ')';
intOrd : stringExpr '.''ord''('intExpr ')';

INTEGER : [1-9][0-9]* | '0' ;//定义整数
BOOLEN :('true'|'false');//定义bool值
STRING:'"' (ESC | .)*? '"';//定义string值
fragment
ESC : '\\"'| '\\\\' | '\\n';

IDENTIFIER : ('_'|[a-zA-Z])([0-9a-zA-Z]|'_')*;//定义变量名

WS : ([ \r\n\t]+ ) -> skip;     //跳过空白类字符
LINE_COMMENT:'//' .*? '\r'? '\n' ->skip ;//跳过'//'
COMMENT : '/*' .*? '*/'->skip;//跳过'/* */'

BREAK : 'break';
CONTINUE : 'continue';


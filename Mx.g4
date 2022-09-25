grammar Mx;   //名称需要和文件名一致

s : (classdefine|funcdefine)*mainfuncdefine EOF;   //解决问题: no viable alternative at input '<EOF>'

//=======expressions=======
stringExpr
    : stringExpr  '+'  stringExpr
    | STRING
    | IDENTIFIER
    | toString
    | getString
    | subString
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
    | getInt
    | (intLength | parseInt | intOrd)
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
;

//=======definitions=======
vardefine : 'int' IDENTIFIER ('='intExpr)? |
            'bool' IDENTIFIER ('='boolExpr)? |
            'string' IDENTIFIER ('=' stringExpr)? ;

newvalue : 'new' ('int'|'bool'|'string');//未完成


funcdefine: 'int' IDENTIFIER '(' (vardefine(','vardefine)*)?  ')'  '{' (statement)* 'return' intExpr ';' '}'
            |'bool' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* 'return' boolExpr ';' '}'
            |'string' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* 'return' stringExpr ';' '}'
            |'void' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* 'return' ';' '}'
            | IDENTIFIER IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* ('return' IDENTIFIER ';') '}';//class??

mainfuncdefine :'int' 'main' '(' (vardefine(','vardefine)*)?  ')'  '{' (statement)* 'return' intExpr ';' '}' ;

classdefine: 'class' IDENTIFIER '{'
    vardefinestate*
    (IDENTIFIER '(' ')' '{' statement*'}')?
    funcdefine*
'}' ';';

//=======statements=======
statement:vardefinestate|varassignstate|whilestate|conditionstate|expressstate|forstate|innerfuncstatement|callfuncstate;

vardefinestate :'int' ('[' ']')* IDENTIFIER ('='(intExpr))?(',' IDENTIFIER ?('='(intExpr))?)* ';' |
                'bool'('[' ']')* IDENTIFIER ('='boolExpr)?(','IDENTIFIER ('='boolExpr)?)*';'|
                'string'('[' ']')*  IDENTIFIER ('=' stringExpr)?(','IDENTIFIER ('=' stringExpr)?)*';';

varassignstate : IDENTIFIER '='(boolExpr|stringExpr|intExpr)(',' IDENTIFIER ?('='(boolExpr|stringExpr|intExpr))?)* ';' ;

//arraydefinestate : arraydefine';';

conditionstate: 'if'  '('  boolExpr  ')'(statement | '{' statement* '}') ( 'else' (statement | '{' statement* '}'))?;

whilestate: 'while' '(' boolExpr ')'((statement|breakstate|continuestate) | '{' (statement|breakstate|continuestate)* '}') ;

expressstate : (boolExpr|stringExpr|intExpr)';';

forstate : 'for''('('int' IDENTIFIER ('='(intExpr))?(',' IDENTIFIER ?('='(intExpr))?)*)? ';'(boolExpr)?';' (intExpr)?')'
            ((statement|breakstate|continuestate) | '{' (statement|breakstate|continuestate)* '}');

breakstate: BREAK ';';

continuestate: CONTINUE ';';

innerfuncstatement: (printInt|printlnInt|printStr|printlnStr|getString|getInt|toString)';';

callfuncstate :(innerfuncstatement | (IDENTIFIER '('(boolExpr|stringExpr|intExpr)?(','(boolExpr|stringExpr|intExpr))* ')'))';';

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


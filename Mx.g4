grammar Mx;   //名称需要和文件名一致

s : funcdefine* EOF;   //解决问题: no viable alternative at input '<EOF>'

stringExpr
    : stringExpr  '+' stringExpr
    | STRING
    | IDENTIFIER
;

intExpr
    : intExpr ('++'|'--')
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

statement:vardefinestate|varassignstate|whilestate|conditionstate|expressstate|forstate;

vardefinestate :'int' IDENTIFIER ('='(intExpr))?(',' IDENTIFIER ?('='(intExpr))?)* ';' |
                'bool' IDENTIFIER ('='boolExpr)?(','IDENTIFIER ('='boolExpr)?)*';'|
                'string' IDENTIFIER ('=' stringExpr)?(','IDENTIFIER ('=' stringExpr)?)*';';

vardefine : 'int' IDENTIFIER ('='intExpr)? |
            'bool' IDENTIFIER ('='boolExpr)? |
            'string' IDENTIFIER ('=' stringExpr)? ;

varassignstate : IDENTIFIER '='(boolExpr|stringExpr|intExpr)(',' IDENTIFIER ?('='(boolExpr|stringExpr|intExpr))?)* ';' ;

funcdefine: 'int' IDENTIFIER '(' (vardefine(','vardefine)*)?  ')'  '{' (statement)* 'return' intExpr ';' '}'
            |'bool' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* 'return' boolExpr ';' '}'
            |'string' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* 'return' stringExpr ';' '}'
            |'void' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* ('return' ';')? '}';

conditionstate: 'if'  '('  boolExpr  ')'(statement | '{' statement* '}') ( 'else' (statement | '{' statement* '}'))?;

whilestate: 'while' '(' boolExpr ')'(statement | '{' statement* '}') ;

expressstate : (boolExpr|stringExpr|intExpr)';';

forstate : 'for''('('int' IDENTIFIER ('='(intExpr))?(',' IDENTIFIER ?('='(intExpr))?)*)? ';'(boolExpr)?';' (intExpr)?')'(statement | '{' statement* '}');

INTEGER : '-'?[1-9][0-9]* | '0' ;//定义整数
BOOLEN :('true'|'false');//定义bool值
STRING:'"' (ESC | .)*? '"';//定义string值
fragment
ESC : '\\"'| '\\\\' | '\\n';

IDENTIFIER : ('_'|[a-zA-Z])([0-9a-zA-Z]|'_')*;//定义变量名

WS : ([ \r\n\t]+ ) -> skip;     //跳过空白类字符
LINE_COMMENT:'//' .*? '\r'? '\n' ->skip ;//跳过'//'
COMMENT : '/*' .*? '*/'->skip;//跳过'/* */'
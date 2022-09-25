grammar Mx;   //名称需要和文件名一致

s : funcdefine* EOF;   //解决问题: no viable alternative at input '<EOF>'

stringExpr
    : stringExpr  '+' stringExpr
    | STRING
    | IDENTIFIER
;

intExpr
    : intExpr ('*'|'/') intExpr
    | intExpr ('+'|'-') intExpr
    | '('  intExpr ')'
    | INTEGER
    | IDENTIFIER

;

boolExpr
    :boolExpr LOGOPERA boolExpr
    | '(' boolExpr ')'
    | stringExpr RELOPERA stringExpr
    | intExpr RELOPERA intExpr
    | BOOLEN
    | IDENTIFIER
;

statement:vardefinestate;

vardefinestate :'int' IDENTIFIER ('='(intExpr))?(',' IDENTIFIER ?('='(intExpr))?)* ';' |
                'bool' IDENTIFIER ('='boolExpr)?(','IDENTIFIER ('='boolExpr)?)*';'|
                'string' IDENTIFIER ('=' stringExpr)?(','IDENTIFIER ('=' stringExpr)?)*';';

vardefine : 'int' IDENTIFIER ('='intExpr)? |
            'bool' IDENTIFIER ('='boolExpr)? |
            'string' IDENTIFIER ('=' stringExpr)? ;

varassign : IDENTIFIER '='(boolExpr|stringExpr|intExpr)(',' IDENTIFIER ?('='(boolExpr|stringExpr|intExpr))?)* ';' ;

funcdefine: 'int' IDENTIFIER '(' (vardefine(','vardefine)*)?  ')'  '{' (vardefinestate| conditionstate)* 'return' intExpr ';' '}'|
            'bool' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (vardefinestate| conditionstate)* 'return' boolExpr ';' '}'

;

conditionstate: 'if'  '('  boolExpr  ')'(statement | '{' statement* '}') ( 'else' (statement | '{' statement* '}'))?;

INTEGER : '-'?[1-9][0-9]* | '0' ;//定义整数
BOOLEN :('true'|'false');//定义bool值
STRING:'"'([0-9a-zA-Z]|'\n'|'\\'|' '|'!'|[#-/:-@^-`{-~]|'['|']')*'"';//'\"'?有问题啊 有很多问题

IDENTIFIER : ('_'|[a-zA-Z])([0-9a-zA-Z]|'_')*;//定义变量名

LOGOPERA :'&&' | '||' | '!';
STAOPERA :'*' | '-' | '+' | '/' | '%';
RELOPERA :'>'|'<'|'>='|'<='|'!='|'==';
BITOPERA : '>>' | '<<' | '&' | '|' | '^' | '~';


WS : ([ \r\n\t]+ ) -> skip;     //跳过空白类字符

LINE_COMMENT:'//' .*? '\r'? '\n' ->skip ;
COMMENT : '/*' .*? '*/'->skip;
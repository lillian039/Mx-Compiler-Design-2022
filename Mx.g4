grammar Mx;   //名称需要和文件名一致

s : funcdefine* EOF;   //解决问题: no viable alternative at input '<EOF>'

expr
    : expr OPERA expr     #standardOperator
    | '('+ expr OPERA expr + ')' #parentheses
    | INTEGER  #int
    | IDENTIFIER #variable
    ;

statement:vardefinestate|varassign;

vardefinestate : 'int' IDENTIFIER ('='(INTEGER|IDENTIFIER|expr))?(',' IDENTIFIER ?('='(INTEGER|IDENTIFIER|expr))?)* ';' |
            'bool' IDENTIFIER ('='BOOLEN)?(','IDENTIFIER ('='BOOLEN)?)*';'|
            'string' IDENTIFIER ('=' STRING)?(','IDENTIFIER ('=' STRING)?)*';';

vardefine :'int' IDENTIFIER ('='(INTEGER|IDENTIFIER|expr))? |   'bool' IDENTIFIER ('='BOOLEN)? |  'string' IDENTIFIER ('=' STRING)? ;
varassign : IDENTIFIER '='(BOOLEN|INTEGER|IDENTIFIER|expr)(',' IDENTIFIER ?('='(BOOLEN|INTEGER|IDENTIFIER|expr))?)* ';' ;
funcdefine: 'int' IDENTIFIER +'(' +(vardefine+(','+vardefine)*)?  ')' + '{' +(vardefinestate|varassign| conditionstate|returnstate)* '}'
;

conditionstate: 'if' + '(' + expr + ')'+(statement | '{' +statement* '}') +( 'else' +(statement | '{' +statement* '}'))?;

returnstate : 'return' + expr +';';
INTEGER : '-'?[1-9][0-9]* | '0' ;//定义整数
BOOLEN :('true'|'false');//定义bool值
STRING:'"'([0-9a-zA-Z]|'\n'|'\\'|' '|'!'|[#-/:-@^-`{-~]|'['|']')*'"';//'\"'?有问题啊 有很多问题

IDENTIFIER : ('_'|[a-zA-Z])([0-9a-zA-Z]|'_')*;//定义变量名

OPERA: '>'|'<'|'>='|'<='|'!='|'=='|'&&' | '||' | '!'|'+' | '-' | '*' | '/' | '%'|'>>' | '<<' | '&' | '|' | '^' | '~';
/*RELOPERA :'>'|'<'|'>='|'<='|'!='|'==';
LOGOPERA :'&&' | '||' | '!';
STAOPERA :'+' | '-' | '*' | '/' | '%';
BITOPERA : '>>' | '<<' | '&' | '|' | '^' | '~';*/


WS
    : (([ \r\n\t]+ )| ('//'STRING )) -> skip      //跳过空白类字符???
    ;

lexer grammar MxLexerRules;

ASSIGN : '=';

ADD:'+' ;
SUB:'-';
MUL:'*';
DIV:'/';
MOD : '%';
OR_OP : '|';
XOR : '^';
AND_OP : '&';
LEFT_SHIFT : '<<';
RIGHT_SHIFT : '>>';
NOT_OP : '~';

LESS_THAN : '<';
GREATER_THAN : '>';
EQUALS : '==';
GT_EQ : '>=';
LT_EQ : '<=';
NOT_EQ_1 : '<>';
NOT_EQ_2 : '!=';

RETURN:'return';
FOR:'for';
IF:'if';
WHILE:'while';

SEMI:';';
COMMA:',';
DOT : '.';

INT:'int';
BOOL:'bool';
STR:'string';
VOID:'void';

NEW:'new';




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

THIS:'this';//在Java文件里头特判 this只能在class中出现 外面就不管啦！
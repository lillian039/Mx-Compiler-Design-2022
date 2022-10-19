lexer grammar MxLexerRules;

LeftParen : '(';
RightParen : ')';
LeftBracket : '[';
RightBracket : ']';
LeftBrace : '{';
RightBrace : '}';

ASSIGN : '=';

Add:'+' ;
Sub:'-';
Mul:'*';
Div:'/';
Mod : '%';
Or : '|';
Xor : '^';
And : '&';
Not: '!';
LeftShift : '<<';
RightShift : '>>';
Invert : '~';
AddSelf:'++';
SubSelf:'--';

Less : '<';
Greater : '>';
Equals : '==';
GreaterEqual : '>=';
LessEqual : '<=';
NotEqual : '!=';
AndAnd: '&&';
Oror:'||';

Return:'return';
For:'for';
If:'if';
While:'while';

Semi:';';
Comma:',';
Dot : '.';

INT:'int';
BOOL:'bool';
STR:'string';
VOID:'void';

NEW:'new';

TRUE:'true';
FALSE:'false';
INTEGER : [1-9][0-9]* | '0' ;//定义整数
STRING:'"' (ESC | .)*? '"';//定义string值
fragment
ESC : '\\"'| '\\\\' | '\\n';

THIS:'this';
NULL:'null';

IDENTIFIER : ('_'|[a-zA-Z])([0-9a-zA-Z]|'_')*;//定义变量名

WS : ([ \r\n\t]+ ) -> skip;     //跳过空白类字符
LINE_COMMENT:'//' .*? '\r'? '\n' ->skip ;//跳过'//'
COMMENT : '/*' .*? '*/'->skip;//跳过'/* */'

BREAK : 'break';
CONTINUE : 'continue';


grammar Mx;   //名称需要和文件名一致
import MxLexerRules;
s : (classdefine|funcdefine|vardefinestate)*mainfuncdefine EOF;   //解决问题: no viable alternative at input '<EOF>'

//=======expressions=======
stringExpr
    : stringExpr  '+'  stringExpr
    | STRING
    | IDENTIFIER
    | innermember
    | classmember
    | unknownExpr
;

unknownExpr
    : callclassfunction
    | callfunction
    | lamdaExpr
    | arrayelement
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
    | unknownExpr
    | classmember
    | innermember
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
    | innermember
    | classmember
    | unknownExpr
;

classExpr
    : IDENTIFIER
    | unknownExpr
    | THIS
    ;

expression:
    boolExpr|
    intExpr|
    stringExpr|
    classExpr;

//=======definitions=======
arrayelement: IDENTIFIER ('[' intExpr ']')+;

intvardefine: IDENTIFIER ('='intExpr)?;
boolvardefine: IDENTIFIER ('='boolExpr)?;
stringvardefine: IDENTIFIER ('=' stringExpr)?;
classvardefine:IDENTIFIER IDENTIFIER;

intarraydefine:  IDENTIFIER ('='(('new''int'('['(intExpr)?']')+)|'null'))?;
boolarraydefine: IDENTIFIER ('='(('new''bool'('['(intExpr)?']')+)|'null'))?;
stringarraydefine: IDENTIFIER ('='(('new''string'('['(intExpr)?']')+)|'null'))?;

vardefine :  'int' intvardefine|
             'bool' boolvardefine|
             'string' stringvardefine|
             'int' ( '[' ']')+intarraydefine|
             'bool' ( '[' ']')+boolarraydefine|
             'string' ( '[' ']')+stringarraydefine|
             classvardefine;

funcdefine: 'int' IDENTIFIER '(' (vardefine(','vardefine)*)?  ')'  '{' (statement)* intreturnstate '}'
            |'bool' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* boolreturnstate '}'
            |'string' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* stringreturnstate '}'
            |'void' IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* voidreturnstate '}'
            | IDENTIFIER IDENTIFIER '('(vardefine(','vardefine)*)?  ')'  '{' (statement)* classreturnstate'}';//class??

mainfuncdefine :'int' 'main' '(' (vardefine(','vardefine)*)?  ')'  '{' (statement)* ('return' intExpr ';')? '}' ;

classdefine: 'class' IDENTIFIER '{'
    vardefinestate*
    (IDENTIFIER '(' ')' '{' statement*'}')?
    funcdefine*
'}' ';';

//=======statements=======
statement:
     vardefinestate
    |varassignstate
    |whilestate
    |conditionstate
    |expressstate
    |forstate
    |callfuncstate
    |returnstate;

vardefinestate :'int' intvardefine(','intvardefine)*';'|
                'int' ( '[' ']')+ intarraydefine(',' intarraydefine)* ';' |
                'bool' boolvardefine(','boolvardefine)*';'|
                'bool' ( '[' ']')+ boolarraydefine(','boolarraydefine)*';'|
                'string' stringvardefine(','stringvardefine)*';'|
                'string' ( '[' ']')+stringarraydefine(','stringarraydefine)* ';';

arrayassign: IDENTIFIER ('['(intExpr)?']')+'='(expression|('new'('int'|'bool'|'string')('['(intExpr)?']')+));
varassign: IDENTIFIER '='(expression);
varassignstate : (arrayassign|varassign)(',' (varassign|arrayassign))* ';' ;

conditionstate: 'if'  '('  boolExpr  ')'(statement | '{' statement* '}') ( 'else' (statement | '{' statement* '}'))?;

whilestate: 'while' '(' boolExpr ')'((statement|breakstate|continuestate) | '{' (statement|breakstate|continuestate)* '}') ;

expressstate : expression';';

forstate : 'for''('(('int')? IDENTIFIER ('='(intExpr))?(',' IDENTIFIER ?('='(intExpr))?)*)? ';'(boolExpr)?';' (intExpr|varassign)?')'
            ((statement|breakstate|continuestate) | '{' (statement|breakstate|continuestate)* '}');

//======function=======

breakstate: BREAK ';';

continuestate: CONTINUE ';';

callfunction: (IDENTIFIER '('(boolExpr|stringExpr|intExpr)?(','(boolExpr|stringExpr|intExpr))* ')');
callclassfunction:IDENTIFIER'.'callfunction;

callfuncstate :(callfunction)';';

returnstate:intreturnstate|boolreturnstate|stringreturnstate|classreturnstate;
lamdaglobe:'[''&'']'('(' (vardefine(','vardefine)*)?')')?'->''{'
(statement|returnstate)*
returnstate
'}' '('(expression(','expression)*)?')';

lamdainner:'['']'('(' (vardefine(','vardefine)*)?')')?'->''{'
           (statement|returnstate)*
           returnstate
           '}' '('(expression(','expression)*)?')';

lamdaExpr:lamdaglobe|lamdainner;

//=====return======
intreturnstate : 'return' intExpr ';';
boolreturnstate : 'return' boolExpr ';';
stringreturnstate : 'return' stringExpr ';';
voidreturnstate: 'return'  ';';
classreturnstate: 'return' classExpr ';';

classmember:IDENTIFIER'.'IDENTIFIER;
innermember:THIS'.'IDENTIFIER;






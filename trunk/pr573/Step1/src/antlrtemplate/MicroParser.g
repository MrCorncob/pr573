//grammar MicroParser;

class MicroLexer extends Lexer;

options {
	k = 2;
	charVocabulary = '\u0000'..'\u00FF';
}

COMMENT : "--" (~’\n’)* ;

REST : . ;

IDENTIFIER : LETTER (LETTER | INTLITERAL)* ;

FLOATLITERAL : (INTLITERAL)* ('.'(INTLITERAL)+) ;

//STRINGLITERAL : QUOTE (options {greedy=false;} : .)* QUOTE { //setText(getText().substring(1, getText().length()-1)); };

LETTER : ('a'..'z' | 'A'..'Z') ;

INTLITERAL : '0'..'9' ;

QUOTE : ' | " ;


KEYWORD : "PROGRAM" | "BEGIN" | "END" | "PROTO" | "FUNCTION" | "READ" | "WRITE" | "IF" | "THEN" | "ELSE" | "ENDIF" | "RETURN" | "FOR" | "ENDFOR" | "FLOAT" | "INT" | "VOID" | "STRING" ;

OPERATOR : ':' | ':' | '=' | '+' | '-' | '*' | '/' | '=' | '<' | '>' | '(' | ')' | ';' | ',' ;
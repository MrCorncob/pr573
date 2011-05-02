grammar Micro;

options {
	k=2;
  	language = Java;
  	output = AST;
  	ASTLabelType = CommonTree;
}

tokens {
	PGM_BODY;
	DECL;
	STRING_DECL;
	VAR_DECL;
	FUNC_DECL_LIST;
	PARAM_DECL_LIST;
	FUNC_BODY;
	STMT_LIST;
	CALL_EXPR;
	EXPR_LIST;
}


@rulecatch{
	catch (RecognitionException e) {
		throw e;
	}
}

/*****	PARSER	*****/
/* Program */

rule
	:		
		K_PROGRAM^ id! K_BEGIN! pgm_body K_END!
	;	
id           
	:	IDENTIFIER
	;
pgm_body          
	:
	decl func_declarations  -> ^(PGM_BODY decl func_declarations)
	;
decl		  
	: decl_list*  -> ^(DECL decl_list*)
	;
decl_list
	: string_decl_list | var_decl_list
	;
	
/* Global String Declaration */
string_decl_list  
	: string_decl+
	;
string_decl       
	: K_STRING id	ASSIGN str SEMICOL  -> ^(STRING_DECL id str)
	;
str               
	: STRINGLITERAL
	;

/* Variable Declaration */
var_decl_list     
	: var_decl+ 
	;
var_decl          
	: var_type var_id_list SEMICOL  -> ^(VAR_DECL var_type var_id_list)
	;
var_type	  
	: K_FLOAT
	| K_INT
	;
any_type          
	: var_type | K_VOID 
	;
var_id_list
	:	var_id (COMMA! var_id)* 
	;
var_id
	:	id
	;
id_list           
	:  id (COMMA id)* 	-> id+
	;

/* Function Paramater List */
param_decl_list   
	: param_decl (COMMA param_decl)* -> ^(PARAM_DECL_LIST param_decl*)
	;
param_decl        
	: var_type id
	;

/* Function Declarations */
func_declarations 
	: func_decl*	-> ^(FUNC_DECL_LIST func_decl*)
	;
func_decl         
	: K_FUNCTION^ any_type id 
		LP! param_decl_list? RP! K_BEGIN! func_body K_END!
	;
func_body         
	: decl stmt_list  -> ^(FUNC_BODY decl stmt_list)
	;

/* Statement List */
stmt_list         
	: stmt*	-> ^(STMT_LIST stmt*)
	;

stmt              
	: assign_stmt | read_stmt | write_stmt | return_stmt | if_stmt | for_stmt
	;

/* Basic Statements */
assign_stmt       
	: assign_expr SEMICOL!
	;
assign_expr       
	: id ASSIGN^ expr
	;
read_stmt         
	: K_READ^ LP! id_list RP! SEMICOL!
	;
write_stmt        
	: K_WRITE^ LP! id_list RP! SEMICOL!
	;
return_stmt       
	: K_RETURN^ expr SEMICOL!
	;

/* Expressions */
expr              
	: factor (addop^ factor)*
	; 
factor            
	: postfix_expr (mulop^ postfix_expr)*
	;
postfix_expr      
	: primary | call_expr
	;
call_expr         
	: id LP expr_list? RP	->	 ^(CALL_EXPR id expr_list)
	;
expr_list         
	: expr (COMMA expr)*	->	^(EXPR_LIST expr+)
	;
primary           	
	: LP! expr^ RP! 
	| id 
	| INTLITERAL 
	| FLOATLITERAL
	;
addop             
	: ADD| SUB
	;
mulop             
	: MULT | DIV
	;

/* Complex Statements and Condition */ 
if_stmt           
	: K_IF^ LP! cond RP! K_THEN! stmt_list else_part? K_ENDIF!
	;
else_part         
	: K_ELSE! stmt_list
	;
cond              
	: expr compop^ expr
	;
compop            
	: LT | GT | EQ
	;
for_stmt          
	: K_FOR^ LP! assign_expr? SEMICOL cond? SEMICOL assign_expr? RP! stmt_list K_ENDFOR! 
	;
	
/*****	LEXER	*****/		
	K_PROGRAM :	'PROGRAM';
	K_BEGIN   : 'BEGIN'; 
	K_END     : 'END'; 
	K_PROTO   : 'PROTO';
	K_FUNCTION: 'FUNCTION';
	K_READ    : 'READ';
	K_WRITE   : 'WRITE';				
	K_IF      : 'IF'; 
	K_THEN    : 'THEN';
	K_ELSE    : 'ELSE'; 
	K_ENDIF   : 'ENDIF';
	K_RETURN  : 'RETURN';
	K_FOR     : 'FOR';
	K_ENDFOR  : 'ENDFOR';
	K_FLOAT   : 'FLOAT';
	K_INT     : 'INT';
	K_VOID    : 'VOID';
	K_STRING  : 'STRING';
	ASSIGN    : ':=';
	ADD       : '+';
	SUB       : '-';
	MULT      : '*';
	DIV       : '/';
	GT        : '>';
	LT        : '<';
	EQ        : '=';
	LP        : '(';
	RP        : ')';
	SEMICOL   : ';'	;
	COMMA	  : ','	;

IDENTIFIER	:	('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'_'| DIGIT)*
			;
INTLITERAL	:	DIGIT+	
			;
FLOATLITERAL	:	DIGIT* '.' DIGIT+
			;
STRINGLITERAL: '"'~('"')*'"'
			;
COMMENT	:	'--' ~'\n'* '\n'	{ $channel=HIDDEN; };
WS : (' '|'\r'|'\n'|'\t'|'\f')+ {$channel = HIDDEN;};
fragment	
DIGIT	:	('0'..'9');

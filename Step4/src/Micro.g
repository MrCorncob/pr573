grammar Micro;

options {
	k = 2;
  language = Java;
  output = AST;
  ASTLabelType = CommonTree;
}

tokens {
	ID_LIST;
  FUNC_DECL_LIST;
  STMT_LIST;
  CALL_EXPR;
  EXPR_LIST;
}


@members {
	SymbolTable globalTab;
	SymbolTable currentTab;
}


/*------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------*/

/* Program */
program [SymbolTable globalSymTab]       
		: {
			globalTab = globalSymTab;
			currentTab = globalTab;
			currentTab.scopeId = "global";
			//System.out.println("Printing Global Symbol Table");
			}
			K_PROGRAM^ id!
			K_BEGIN! pgm_body K_END!
		;
id                
		: IDENTIFIER 
		;
pgm_body          
		: decl! func_decl_list
		;
decl
		: decl_list*
		;
decl_list		  
		: string_decl_list | var_decl_list
		;

/* Global String Declaration */
string_decl_list  
		: string_decl+
		;
string_decl       
		: K_STRING id ASSIGN str ';'
			{
			Symbol s = new Symbol($id.text, "STRING", $str.text);
			currentTab.define(s);
			//s.printStr();
			}
		;
str               
		: STRINGLITERAL 
		;

/* Variable Declaration */
var_decl_list     
		: var_decl+
		;
var_decl          
		: var_type var_id_list ';'
		;
var_type	  			
		: K_FLOAT {currentTab.currentVarType = "FLOAT";} 
		| K_INT   {currentTab.currentVarType = "INT";} 
		;
any_type          
		: var_type
		| K_VOID
		; 
var_id_list
		: var_id ( ',' var_id )*
		;
var_id
	  : id
	  	{
			Symbol s = new Symbol($id.text,currentTab.currentVarType);
			currentTab.define(s);
			//s.print();
			}
	  ;
id_list           
		: id ( ',' id )*  -> ^(ID_LIST id+)  
		;

/* Function Paramater List */
param_decl_list   
		: param_decl param_decl_tail 
		;
param_decl        
		: var_type id 
		;
param_decl_tail   
		: ( ',' param_decl )* 
		;

/* Function Declarations */
func_decl_list
		: func_decl*  -> ^(FUNC_DECL_LIST func_decl*)
		;
func_decl         
		: K_FUNCTION^ any_type id 
			{
			SymbolTable func = new SymbolTable($id.text);
			currentTab.nestScope($id.text, func);
			currentTab = func;
			//System.out.println("\nPrinting Symbol Table for "+func.scopeId);
			} 
			LP! param_decl_list? RP! K_BEGIN! func_body K_END
			{ currentTab = globalTab; } 
		;
func_body         
		: decl! stmt_list 
		;

/* Statement List */
stmt_list         
		: stmt* -> ^(STMT_LIST stmt*)
		;
stmt              
		: assign_stmt | read_stmt | write_stmt | return_stmt | if_stmt | for_stmt 
		;

/* Basic Statements */
assign_stmt       
		: assign_expr ';'!
		;
assign_expr       
		: id ASSIGN^ expr
		;
read_stmt         
		: K_READ^ LP! id_list RP! ';'!
		;
write_stmt        
		: K_WRITE^ LP! id_list RP! ';'!
		;
return_stmt       
		: K_RETURN^ expr ';'!
		;

/* Expressions */
expr              
		: factor (addop^ factor)* 
		;
factor            
		: postfix_expr ( mulop^ postfix_expr )* 
		;
postfix_expr      
		: primary | call_expr 
		;
call_expr         
		: id LP expr_list? RP  -> ^(CALL_EXPR expr_list) 
		;
expr_list         
		: expr ( ',' expr )*  -> ^(EXPR_LIST expr+)
		;
primary           
		: LP! expr RP! 
		| id 
		| INTLITERAL 
		| FLOATLITERAL 
		;
addop             
		: ADD | SUB 
		;
mulop             
		: MULT | DIV 
		;

/* Complex Statements and Condition */ 
if_stmt           
		: K_IF LP cond RP K_THEN stmt_list else_part K_ENDIF  -> ^(K_IF cond stmt_list else_part) 
		;
else_part         
		: ( K_ELSE! stmt_list )? 
		;
cond              
		: expr compop^ expr 
		;
compop            
		: LT | GT | EQ 
		;
for_stmt          
		: K_FOR^ LP! assign_expr? ';' cond? ';' assign_expr? RP! stmt_list K_ENDFOR!
		;


		
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
IDENTIFIER : ('A'..'Z'|'a'..'z')('A'..'Z'|'a'..'z'|'0'..'9')*;
INTLITERAL: ('0'..'9')+;
FLOATLITERAL: ('0'..'9')*'.'('0'..'9')+;
STRINGLITERAL: '"'~('"')*'"';
COMMENT : '--' ~'\n'* '\n' {$channel = HIDDEN;};
WS : (' '|'\r'|'\n'|'\t'|'\f')+ {$channel = HIDDEN;};
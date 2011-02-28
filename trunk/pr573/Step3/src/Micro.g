grammar Micro;

options {
	k=2;
  language = Java;
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
			System.out.println("Printing Global Symbol Table");
			}
			'PROGRAM' id
			'BEGIN' pgm_body 'END'
		;
id                
		: IDENTIFIER 
		;
pgm_body          
		: decl func_declarations 
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
		: 'STRING' id ':=' str ';'
			{
			Symbol s = new Symbol($id.text, "STRING", $str.text);
			currentTab.define(s);
			s.printStr();
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
		: 'FLOAT' {currentTab.currentVarType = "FLOAT";} 
		| 'INT'   {currentTab.currentVarType = "INT";} 
		;
any_type          
		: var_type
		| 'VOID'
		; 
var_id_list
		: var_id ( ',' var_id )*
		;
var_id
	  : id
	  	{
			Symbol s = new Symbol($id.text,currentTab.currentVarType);
			currentTab.define(s);
			s.print();
			}
	  ;
id_list           
		: id ( ',' id )*  
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
func_declarations 
		: func_decl*
		;
func_decl         
		: 'FUNCTION' any_type id 
			{
			SymbolTable func = new SymbolTable($id.text);
			currentTab = func;
			System.out.println("\nPrinting Symbol Table for "+func.scopeId);
			} 
			'(' param_decl_list? ')' 'BEGIN' func_body 'END'
			{currentTab = globalTab;} 
		;
func_body         
		: decl stmt_list 
		;

/* Statement List */
stmt_list         
		: stmt*
		;
stmt              
		: assign_stmt | read_stmt | write_stmt | return_stmt | if_stmt | for_stmt 
		;

/* Basic Statements */
assign_stmt       
		: assign_expr ';'
		;
assign_expr       
		: id ':=' expr
		;
read_stmt         
		: 'READ' '(' id_list ')' ';'
		;
write_stmt        
		: 'WRITE' '(' id_list ')' ';'
		;
return_stmt       
		: 'RETURN' expr ';'
		;

/* Expressions */
expr              
		: factor (addop factor)* 
		;
factor            
		: postfix_expr ( mulop postfix_expr )* 
		;
postfix_expr      
		: primary | call_expr 
		;
call_expr         
		: id '(' expr_list? ')' 
		;
expr_list         
		: expr ( ',' expr )*
		;
primary           
		: '(' expr ')' | id | INTLITERAL | FLOATLITERAL 
		;
addop             
		: '+' | '-' 
		;
mulop             
		: '*' | '/' 
		;

/* Complex Statements and Condition */ 
if_stmt           
		: 'IF' '(' cond ')' 'THEN' stmt_list else_part 'ENDIF' 
		;
else_part         
		: ( 'ELSE' stmt_list )? 
		;
cond              
		: expr compop expr 
		;
compop            
		: '<' | '>' | '=' 
		;
for_stmt          
		: 'FOR' '(' assign_expr? ';' cond? ';' assign_expr? ')' stmt_list 'ENDFOR' 
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
IDENTIFIER : ('A'..'Z'|'a'..'z')('A'..'Z'|'a'..'z'|'0'..'9')*;
INTLITERAL: ('0'..'9')+;
FLOATLITERAL: ('0'..'9')*'.'('0'..'9')+;
STRINGLITERAL: '"'~('"')*'"';
COMMENT : '--' ~'\n'* '\n' {$channel = HIDDEN;};
WS : (' '|'\r'|'\n'|'\t'|'\f')+ {$channel = HIDDEN;};
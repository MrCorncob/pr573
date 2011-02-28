DIGIT	[0-9]	
ID	[a-zA-Z][0-9a-zA-Z]*{0,30}

%%
PROGRAM|BEGIN|END|PROTO|FUNCTION|READ|WRITE|IF|THEN|ELSE|ENDIF|RETURN|FOR|ENDFOR|FLOAT|INT|VOID|STRING	printf("Token Type: KEYWORD\nValue: %s\n",yytext);
":="|"+"|"-"|"*"|"/"|"="|"<"|">"|"("|")"|";"|","		printf("Token Type: OPERATOR\nValue: %s\n",yytext);
{DIGIT}+					printf("Token Type: INTLITERAL\nValue: %d\n",atoi(yytext));
{DIGIT}*"."{DIGIT}+			printf("Token Type: FLOATLITERAL\nValue: %s\n",yytext);
{ID}						printf("Token Type: IDENTIFIER\nValue: %s\n",yytext);
"--"[^\n]*\n					/* comments */
\"[^\"]{0,80}\"				printf("Token Type: STRINGLITERAL\nValue: %s\n",yytext);


[ \t\n]+
[ |\t\n]+
"/*"[^*/]*"*/"
"//"[^\n]*$
.				break;
%%


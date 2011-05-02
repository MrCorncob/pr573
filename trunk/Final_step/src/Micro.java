
import java.io.IOException;
import java.util.LinkedList;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;


public class Micro {
	public static void main(String[] args) throws IOException{
		CharStream charStream = new ANTLRFileStream(args[0]);
		try {
			/* Lexer - tokenizing */
			MicroLexer lexer = new MicroLexer(charStream);
			
			/* gather the result from lexer into tokenStream */
			TokenStream tokenStream = new CommonTokenStream(lexer);
			
			/* parsing using the feed from tokenStream */
			MicroParser parser = new MicroParser(tokenStream);
			
			/* Making global symbol table */
			//SymbolTable globalSymTab = new SymbolTable();

			/* Making AST  every time when there's parsing*/
			MicroParser.rule_return result = parser.rule();

			/* Walking through AST  */
            CommonTree ast = (CommonTree)result.getTree();     
//          System.out.println(ast.toStringTree());

            /* Code generation  */
            /* instantiate with global symbol table  */
            CodeGen code = new CodeGen();
            /* Do the code generation using AST */
            code.gen(ast);
            /* Store the information in the linked list */
            LinkedList<IrNode> irList = code.irList;
            
            Translator tran = new Translator();
            RegAlloc reg = new RegAlloc(4, code);
            /* Register Allocation */
            if(args.length == 1)
            	 /* Translate IR to Tiny */    
                tran.Translate(code, irList);  
            else if(args.length == 2){
            	if(args[1].equals("-live"))
            		reg.translate();
            }  
		}
		catch (Exception e) {
            System.out.println("Not accepted");
		}
		
	}

}

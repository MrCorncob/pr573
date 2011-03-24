package test;

import java.io.IOException;
import java.util.LinkedList;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;


public class Test {
	public static void main(String[] args) throws IOException{
		CharStream charStream = new ANTLRFileStream(args[0]);
		try {
			/* Lexer - tokenizing */
			TestLexer lexer = new TestLexer(charStream);
			
			/* gather the result from lexer into tokenStream */
			TokenStream tokenStream = new CommonTokenStream(lexer);
			
			/* parsing using the feed from tokenStream */
			TestParser parser = new TestParser(tokenStream);
			
			/* Making global symbol table */
			SymbolTable globalSymTab = new SymbolTable();

			/* Making AST  every time when there's parsing*/
			TestParser.rule_return result = parser.rule(globalSymTab);

			/* Walking through AST  */
            CommonTree ast = (CommonTree)result.getTree();     
//          System.out.println(ast.toStringTree());

            /* Code generation  */
            /* instantiate with global symbol table  */
            CodeGen code = new CodeGen(globalSymTab);
            /* Do the code generation using AST */
            code.gen(ast);
            /* Store the information in the linked list */
            LinkedList<IrNode> irList = code.irList; 
            /* code generator using IR */
            Translator tran = new Translator();
            tran.Translator(globalSymTab, irList);
            
		}
		catch (Exception e) {
            System.out.println("Not accepted");
		}
		
	}

}

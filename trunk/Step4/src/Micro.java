import java.io.IOException;
import java.util.LinkedList;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.*;

import antlr.ANTLRException;

public class Micro {
	public static void main(String[] args) throws IOException, RecognitionException{
		CharStream charStream = new ANTLRFileStream(args[0]);
		MicroLexer lexer = new MicroLexer(charStream);               // lexer
		TokenStream tokenStream = new CommonTokenStream(lexer);
		MicroParser parser = new MicroParser(tokenStream);           // parser
		SymbolTable globalSymTab = new SymbolTable();                // sym tab
		MicroParser.program_return r = parser.program(globalSymTab);
		CommonTree ast = (CommonTree)r.getTree();                      // AST
		CodeGenerator codeGen = new CodeGenerator(globalSymTab);
		codeGen.gen(ast);
		LinkedList<IRnode> irList = codeGen.irList;                  // IR LinkedList
		Translator tran = new Translator();
		tran.translate(globalSymTab, irList);
		//System.out.println("irList generated\n");
	}
}

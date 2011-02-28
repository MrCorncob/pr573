import java.io.IOException;
import org.antlr.*;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

import antlr.ANTLRException;

public class Micro {
	public static void main(String[] args) throws IOException, RecognitionException{
		CharStream charStream = new ANTLRFileStream(args[0]);
		MicroLexer lexer = new MicroLexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		MicroParser parser = new MicroParser(tokenStream);
		SymbolTable globalSymTab = new SymbolTable();
		parser.program(globalSymTab); 	
	}
}

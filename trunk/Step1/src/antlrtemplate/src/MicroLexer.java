import org.antlr.runtime.*;
import java.*;

public class MicroLexer{ 
	public static void main(String args[]) throws Exception {
		MicroLexer lexer = new MicroLexer(System.in);
		Token token;
		do {
			token = lexer.nextToken(); 
			if (token.getType() == MicroLexerTokenTypes.COMMENT) {
				System.out.println(token.getText()); 
			}else{
				break;
			}
		} while (token.getType() != MicroLexerTokenTypes.EOF);
	}
}

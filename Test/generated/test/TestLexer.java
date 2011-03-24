// $ANTLR 3.3 Nov 30, 2010 12:50:56 /Users/ckbest83/Documents/workspace/Test/src/test/Test.g 2011-03-23 04:18:12

  package test;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TestLexer extends Lexer {
    public static final int EOF=-1;
    public static final int FUNC_DECL_LIST=4;
    public static final int STMT_LIST=5;
    public static final int CALL_EXPR=6;
    public static final int EXPR_LIST=7;
    public static final int K_PROGRAM=8;
    public static final int K_BEGIN=9;
    public static final int K_END=10;
    public static final int IDENTIFIER=11;
    public static final int K_STRING=12;
    public static final int ASSIGN=13;
    public static final int SEMICOL=14;
    public static final int STRINGLITERAL=15;
    public static final int K_FLOAT=16;
    public static final int K_INT=17;
    public static final int K_VOID=18;
    public static final int COMMA=19;
    public static final int K_FUNCTION=20;
    public static final int LP=21;
    public static final int RP=22;
    public static final int K_READ=23;
    public static final int K_WRITE=24;
    public static final int K_RETURN=25;
    public static final int INTLITERAL=26;
    public static final int FLOATLITERAL=27;
    public static final int ADD=28;
    public static final int SUB=29;
    public static final int MULT=30;
    public static final int DIV=31;
    public static final int K_IF=32;
    public static final int K_THEN=33;
    public static final int K_ENDIF=34;
    public static final int K_ELSE=35;
    public static final int LT=36;
    public static final int GT=37;
    public static final int EQ=38;
    public static final int K_FOR=39;
    public static final int K_ENDFOR=40;
    public static final int K_PROTO=41;
    public static final int DIGIT=42;
    public static final int COMMENT=43;
    public static final int WS=44;

    // delegates
    // delegators

    public TestLexer() {;} 
    public TestLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TestLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/Users/ckbest83/Documents/workspace/Test/src/test/Test.g"; }

    // $ANTLR start "K_PROGRAM"
    public final void mK_PROGRAM() throws RecognitionException {
        try {
            int _type = K_PROGRAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:215:12: ( 'PROGRAM' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:215:14: 'PROGRAM'
            {
            match("PROGRAM"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_PROGRAM"

    // $ANTLR start "K_BEGIN"
    public final void mK_BEGIN() throws RecognitionException {
        try {
            int _type = K_BEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:216:11: ( 'BEGIN' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:216:13: 'BEGIN'
            {
            match("BEGIN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_BEGIN"

    // $ANTLR start "K_END"
    public final void mK_END() throws RecognitionException {
        try {
            int _type = K_END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:217:11: ( 'END' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:217:13: 'END'
            {
            match("END"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_END"

    // $ANTLR start "K_PROTO"
    public final void mK_PROTO() throws RecognitionException {
        try {
            int _type = K_PROTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:218:11: ( 'PROTO' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:218:13: 'PROTO'
            {
            match("PROTO"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_PROTO"

    // $ANTLR start "K_FUNCTION"
    public final void mK_FUNCTION() throws RecognitionException {
        try {
            int _type = K_FUNCTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:219:11: ( 'FUNCTION' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:219:13: 'FUNCTION'
            {
            match("FUNCTION"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_FUNCTION"

    // $ANTLR start "K_READ"
    public final void mK_READ() throws RecognitionException {
        try {
            int _type = K_READ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:220:11: ( 'READ' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:220:13: 'READ'
            {
            match("READ"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_READ"

    // $ANTLR start "K_WRITE"
    public final void mK_WRITE() throws RecognitionException {
        try {
            int _type = K_WRITE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:221:11: ( 'WRITE' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:221:13: 'WRITE'
            {
            match("WRITE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_WRITE"

    // $ANTLR start "K_IF"
    public final void mK_IF() throws RecognitionException {
        try {
            int _type = K_IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:222:11: ( 'IF' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:222:13: 'IF'
            {
            match("IF"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_IF"

    // $ANTLR start "K_THEN"
    public final void mK_THEN() throws RecognitionException {
        try {
            int _type = K_THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:223:11: ( 'THEN' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:223:13: 'THEN'
            {
            match("THEN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_THEN"

    // $ANTLR start "K_ELSE"
    public final void mK_ELSE() throws RecognitionException {
        try {
            int _type = K_ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:224:11: ( 'ELSE' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:224:13: 'ELSE'
            {
            match("ELSE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_ELSE"

    // $ANTLR start "K_ENDIF"
    public final void mK_ENDIF() throws RecognitionException {
        try {
            int _type = K_ENDIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:225:11: ( 'ENDIF' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:225:13: 'ENDIF'
            {
            match("ENDIF"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_ENDIF"

    // $ANTLR start "K_RETURN"
    public final void mK_RETURN() throws RecognitionException {
        try {
            int _type = K_RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:226:11: ( 'RETURN' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:226:13: 'RETURN'
            {
            match("RETURN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_RETURN"

    // $ANTLR start "K_FOR"
    public final void mK_FOR() throws RecognitionException {
        try {
            int _type = K_FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:227:11: ( 'FOR' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:227:13: 'FOR'
            {
            match("FOR"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_FOR"

    // $ANTLR start "K_ENDFOR"
    public final void mK_ENDFOR() throws RecognitionException {
        try {
            int _type = K_ENDFOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:228:11: ( 'ENDFOR' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:228:13: 'ENDFOR'
            {
            match("ENDFOR"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_ENDFOR"

    // $ANTLR start "K_FLOAT"
    public final void mK_FLOAT() throws RecognitionException {
        try {
            int _type = K_FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:229:11: ( 'FLOAT' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:229:13: 'FLOAT'
            {
            match("FLOAT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_FLOAT"

    // $ANTLR start "K_INT"
    public final void mK_INT() throws RecognitionException {
        try {
            int _type = K_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:230:11: ( 'INT' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:230:13: 'INT'
            {
            match("INT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_INT"

    // $ANTLR start "K_VOID"
    public final void mK_VOID() throws RecognitionException {
        try {
            int _type = K_VOID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:231:11: ( 'VOID' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:231:13: 'VOID'
            {
            match("VOID"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_VOID"

    // $ANTLR start "K_STRING"
    public final void mK_STRING() throws RecognitionException {
        try {
            int _type = K_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:232:11: ( 'STRING' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:232:13: 'STRING'
            {
            match("STRING"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "K_STRING"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:233:11: ( ':=' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:233:13: ':='
            {
            match(":="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "ADD"
    public final void mADD() throws RecognitionException {
        try {
            int _type = ADD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:234:11: ( '+' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:234:13: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ADD"

    // $ANTLR start "SUB"
    public final void mSUB() throws RecognitionException {
        try {
            int _type = SUB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:235:11: ( '-' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:235:13: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SUB"

    // $ANTLR start "MULT"
    public final void mMULT() throws RecognitionException {
        try {
            int _type = MULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:236:11: ( '*' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:236:13: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MULT"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:237:11: ( '/' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:237:13: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:238:11: ( '>' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:238:13: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:239:11: ( '<' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:239:13: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:240:11: ( '=' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:240:13: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "LP"
    public final void mLP() throws RecognitionException {
        try {
            int _type = LP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:241:11: ( '(' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:241:13: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LP"

    // $ANTLR start "RP"
    public final void mRP() throws RecognitionException {
        try {
            int _type = RP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:242:11: ( ')' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:242:13: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RP"

    // $ANTLR start "SEMICOL"
    public final void mSEMICOL() throws RecognitionException {
        try {
            int _type = SEMICOL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:243:11: ( ';' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:243:13: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMICOL"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:244:9: ( ',' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:244:11: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:246:12: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | DIGIT )* )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:246:14: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | DIGIT )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:246:37: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | DIGIT )*
            loop1:
            do {
                int alt1=5;
                switch ( input.LA(1) ) {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt1=1;
                    }
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    {
                    alt1=2;
                    }
                    break;
                case '_':
                    {
                    alt1=3;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt1=4;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:246:38: 'a' .. 'z'
            	    {
            	    matchRange('a','z'); 

            	    }
            	    break;
            	case 2 :
            	    // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:246:47: 'A' .. 'Z'
            	    {
            	    matchRange('A','Z'); 

            	    }
            	    break;
            	case 3 :
            	    // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:246:56: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;
            	case 4 :
            	    // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:246:61: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "INTLITERAL"
    public final void mINTLITERAL() throws RecognitionException {
        try {
            int _type = INTLITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:248:12: ( ( DIGIT )+ )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:248:14: ( DIGIT )+
            {
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:248:14: ( DIGIT )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:248:14: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTLITERAL"

    // $ANTLR start "FLOATLITERAL"
    public final void mFLOATLITERAL() throws RecognitionException {
        try {
            int _type = FLOATLITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:250:14: ( ( DIGIT )* '.' ( DIGIT )+ )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:250:16: ( DIGIT )* '.' ( DIGIT )+
            {
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:250:16: ( DIGIT )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:250:16: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match('.'); 
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:250:27: ( DIGIT )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:250:27: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FLOATLITERAL"

    // $ANTLR start "STRINGLITERAL"
    public final void mSTRINGLITERAL() throws RecognitionException {
        try {
            int _type = STRINGLITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:252:14: ( '\"' (~ ( '\"' ) )* '\"' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:252:16: '\"' (~ ( '\"' ) )* '\"'
            {
            match('\"'); 
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:252:19: (~ ( '\"' ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\u0000' && LA5_0<='!')||(LA5_0>='#' && LA5_0<='\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:252:19: ~ ( '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRINGLITERAL"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:254:9: ( '--' (~ '\\n' )* '\\n' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:254:11: '--' (~ '\\n' )* '\\n'
            {
            match("--"); 

            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:254:16: (~ '\\n' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='\u0000' && LA6_0<='\t')||(LA6_0>='\u000B' && LA6_0<='\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:254:16: ~ '\\n'
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match('\n'); 
             _channel=HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:255:4: ( ( ' ' | '\\r' | '\\n' | '\\t' | '\\f' )+ )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:255:6: ( ' ' | '\\r' | '\\n' | '\\t' | '\\f' )+
            {
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:255:6: ( ' ' | '\\r' | '\\n' | '\\t' | '\\f' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\t' && LA7_0<='\n')||(LA7_0>='\f' && LA7_0<='\r')||LA7_0==' ') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:257:7: ( ( '0' .. '9' ) )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:257:9: ( '0' .. '9' )
            {
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:257:9: ( '0' .. '9' )
            // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:257:10: '0' .. '9'
            {
            matchRange('0','9'); 

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    public void mTokens() throws RecognitionException {
        // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:8: ( K_PROGRAM | K_BEGIN | K_END | K_PROTO | K_FUNCTION | K_READ | K_WRITE | K_IF | K_THEN | K_ELSE | K_ENDIF | K_RETURN | K_FOR | K_ENDFOR | K_FLOAT | K_INT | K_VOID | K_STRING | ASSIGN | ADD | SUB | MULT | DIV | GT | LT | EQ | LP | RP | SEMICOL | COMMA | IDENTIFIER | INTLITERAL | FLOATLITERAL | STRINGLITERAL | COMMENT | WS )
        int alt8=36;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:10: K_PROGRAM
                {
                mK_PROGRAM(); 

                }
                break;
            case 2 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:20: K_BEGIN
                {
                mK_BEGIN(); 

                }
                break;
            case 3 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:28: K_END
                {
                mK_END(); 

                }
                break;
            case 4 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:34: K_PROTO
                {
                mK_PROTO(); 

                }
                break;
            case 5 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:42: K_FUNCTION
                {
                mK_FUNCTION(); 

                }
                break;
            case 6 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:53: K_READ
                {
                mK_READ(); 

                }
                break;
            case 7 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:60: K_WRITE
                {
                mK_WRITE(); 

                }
                break;
            case 8 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:68: K_IF
                {
                mK_IF(); 

                }
                break;
            case 9 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:73: K_THEN
                {
                mK_THEN(); 

                }
                break;
            case 10 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:80: K_ELSE
                {
                mK_ELSE(); 

                }
                break;
            case 11 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:87: K_ENDIF
                {
                mK_ENDIF(); 

                }
                break;
            case 12 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:95: K_RETURN
                {
                mK_RETURN(); 

                }
                break;
            case 13 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:104: K_FOR
                {
                mK_FOR(); 

                }
                break;
            case 14 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:110: K_ENDFOR
                {
                mK_ENDFOR(); 

                }
                break;
            case 15 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:119: K_FLOAT
                {
                mK_FLOAT(); 

                }
                break;
            case 16 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:127: K_INT
                {
                mK_INT(); 

                }
                break;
            case 17 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:133: K_VOID
                {
                mK_VOID(); 

                }
                break;
            case 18 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:140: K_STRING
                {
                mK_STRING(); 

                }
                break;
            case 19 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:149: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 20 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:156: ADD
                {
                mADD(); 

                }
                break;
            case 21 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:160: SUB
                {
                mSUB(); 

                }
                break;
            case 22 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:164: MULT
                {
                mMULT(); 

                }
                break;
            case 23 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:169: DIV
                {
                mDIV(); 

                }
                break;
            case 24 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:173: GT
                {
                mGT(); 

                }
                break;
            case 25 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:176: LT
                {
                mLT(); 

                }
                break;
            case 26 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:179: EQ
                {
                mEQ(); 

                }
                break;
            case 27 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:182: LP
                {
                mLP(); 

                }
                break;
            case 28 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:185: RP
                {
                mRP(); 

                }
                break;
            case 29 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:188: SEMICOL
                {
                mSEMICOL(); 

                }
                break;
            case 30 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:196: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 31 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:202: IDENTIFIER
                {
                mIDENTIFIER(); 

                }
                break;
            case 32 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:213: INTLITERAL
                {
                mINTLITERAL(); 

                }
                break;
            case 33 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:224: FLOATLITERAL
                {
                mFLOATLITERAL(); 

                }
                break;
            case 34 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:237: STRINGLITERAL
                {
                mSTRINGLITERAL(); 

                }
                break;
            case 35 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:251: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 36 :
                // /Users/ckbest83/Documents/workspace/Test/src/test/Test.g:1:259: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\1\uffff\12\27\2\uffff\1\53\12\uffff\1\54\3\uffff\11\27\1\67\4\27"+
        "\3\uffff\2\27\1\101\2\27\1\104\4\27\1\uffff\1\111\10\27\1\uffff"+
        "\1\122\1\27\1\uffff\1\27\1\125\2\27\1\uffff\1\130\1\131\2\27\1\134"+
        "\1\135\1\136\1\27\1\uffff\1\27\1\141\1\uffff\1\27\1\143\2\uffff"+
        "\2\27\3\uffff\1\146\1\27\1\uffff\1\150\1\uffff\1\151\1\152\1\uffff"+
        "\1\27\3\uffff\1\154\1\uffff";
    static final String DFA8_eofS =
        "\155\uffff";
    static final String DFA8_minS =
        "\1\11\1\122\1\105\2\114\1\105\1\122\1\106\1\110\1\117\1\124\2\uffff"+
        "\1\55\12\uffff\1\56\3\uffff\1\117\1\107\1\104\1\123\1\116\1\122"+
        "\1\117\1\101\1\111\1\60\1\124\1\105\1\111\1\122\3\uffff\1\107\1"+
        "\111\1\60\1\105\1\103\1\60\1\101\1\104\1\125\1\124\1\uffff\1\60"+
        "\1\116\1\104\1\111\1\122\1\117\1\116\1\106\1\117\1\uffff\1\60\1"+
        "\124\1\uffff\1\124\1\60\1\122\1\105\1\uffff\2\60\1\116\1\101\3\60"+
        "\1\122\1\uffff\1\111\1\60\1\uffff\1\116\1\60\2\uffff\1\107\1\115"+
        "\3\uffff\1\60\1\117\1\uffff\1\60\1\uffff\2\60\1\uffff\1\116\3\uffff"+
        "\1\60\1\uffff";
    static final String DFA8_maxS =
        "\1\172\1\122\1\105\1\116\1\125\1\105\1\122\1\116\1\110\1\117\1\124"+
        "\2\uffff\1\55\12\uffff\1\71\3\uffff\1\117\1\107\1\104\1\123\1\116"+
        "\1\122\1\117\1\124\1\111\1\172\1\124\1\105\1\111\1\122\3\uffff\1"+
        "\124\1\111\1\172\1\105\1\103\1\172\1\101\1\104\1\125\1\124\1\uffff"+
        "\1\172\1\116\1\104\1\111\1\122\1\117\1\116\1\106\1\117\1\uffff\1"+
        "\172\1\124\1\uffff\1\124\1\172\1\122\1\105\1\uffff\2\172\1\116\1"+
        "\101\3\172\1\122\1\uffff\1\111\1\172\1\uffff\1\116\1\172\2\uffff"+
        "\1\107\1\115\3\uffff\1\172\1\117\1\uffff\1\172\1\uffff\2\172\1\uffff"+
        "\1\116\3\uffff\1\172\1\uffff";
    static final String DFA8_acceptS =
        "\13\uffff\1\23\1\24\1\uffff\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1"+
        "\35\1\36\1\37\1\uffff\1\41\1\42\1\44\16\uffff\1\43\1\25\1\40\12"+
        "\uffff\1\10\11\uffff\1\3\2\uffff\1\15\4\uffff\1\20\10\uffff\1\12"+
        "\2\uffff\1\6\2\uffff\1\11\1\21\2\uffff\1\4\1\2\1\13\2\uffff\1\17"+
        "\1\uffff\1\7\2\uffff\1\16\1\uffff\1\14\1\22\1\1\1\uffff\1\5";
    static final String DFA8_specialS =
        "\155\uffff}>";
    static final String[] DFA8_transitionS = {
            "\2\33\1\uffff\2\33\22\uffff\1\33\1\uffff\1\32\5\uffff\1\23\1"+
            "\24\1\16\1\14\1\26\1\15\1\31\1\17\12\30\1\13\1\25\1\21\1\22"+
            "\1\20\2\uffff\1\27\1\2\2\27\1\3\1\4\2\27\1\7\6\27\1\1\1\27\1"+
            "\5\1\12\1\10\1\27\1\11\1\6\3\27\4\uffff\1\27\1\uffff\32\27",
            "\1\34",
            "\1\35",
            "\1\37\1\uffff\1\36",
            "\1\42\2\uffff\1\41\5\uffff\1\40",
            "\1\43",
            "\1\44",
            "\1\45\7\uffff\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "",
            "",
            "\1\52",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\31\1\uffff\12\30",
            "",
            "",
            "",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64\22\uffff\1\65",
            "\1\66",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "",
            "",
            "",
            "\1\74\14\uffff\1\75",
            "\1\76",
            "\12\27\7\uffff\5\27\1\100\2\27\1\77\21\27\4\uffff\1\27\1\uffff"+
            "\32\27",
            "\1\102",
            "\1\103",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\123",
            "",
            "\1\124",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\126",
            "\1\127",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\132",
            "\1\133",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\137",
            "",
            "\1\140",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\1\142",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "",
            "\1\144",
            "\1\145",
            "",
            "",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\147",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\1\153",
            "",
            "",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( K_PROGRAM | K_BEGIN | K_END | K_PROTO | K_FUNCTION | K_READ | K_WRITE | K_IF | K_THEN | K_ELSE | K_ENDIF | K_RETURN | K_FOR | K_ENDFOR | K_FLOAT | K_INT | K_VOID | K_STRING | ASSIGN | ADD | SUB | MULT | DIV | GT | LT | EQ | LP | RP | SEMICOL | COMMA | IDENTIFIER | INTLITERAL | FLOATLITERAL | STRINGLITERAL | COMMENT | WS );";
        }
    }
 

}
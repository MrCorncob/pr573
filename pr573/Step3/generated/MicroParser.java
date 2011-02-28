// $ANTLR 3.3 Nov 30, 2010 12:50:56 src/Micro.g 2011-02-19 18:16:15

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MicroParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENTIFIER", "STRINGLITERAL", "INTLITERAL", "FLOATLITERAL", "K_PROGRAM", "K_BEGIN", "K_END", "K_PROTO", "K_FUNCTION", "K_READ", "K_WRITE", "K_IF", "K_THEN", "K_ELSE", "K_ENDIF", "K_RETURN", "K_FOR", "K_ENDFOR", "K_FLOAT", "K_INT", "K_VOID", "K_STRING", "COMMENT", "WS", "':='", "';'", "','", "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'='"
    };
    public static final int EOF=-1;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int IDENTIFIER=4;
    public static final int STRINGLITERAL=5;
    public static final int INTLITERAL=6;
    public static final int FLOATLITERAL=7;
    public static final int K_PROGRAM=8;
    public static final int K_BEGIN=9;
    public static final int K_END=10;
    public static final int K_PROTO=11;
    public static final int K_FUNCTION=12;
    public static final int K_READ=13;
    public static final int K_WRITE=14;
    public static final int K_IF=15;
    public static final int K_THEN=16;
    public static final int K_ELSE=17;
    public static final int K_ENDIF=18;
    public static final int K_RETURN=19;
    public static final int K_FOR=20;
    public static final int K_ENDFOR=21;
    public static final int K_FLOAT=22;
    public static final int K_INT=23;
    public static final int K_VOID=24;
    public static final int K_STRING=25;
    public static final int COMMENT=26;
    public static final int WS=27;

    // delegates
    // delegators


        public MicroParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public MicroParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return MicroParser.tokenNames; }
    public String getGrammarFileName() { return "src/Micro.g"; }


    	SymbolTable globalTab;
    	SymbolTable currentTab;



    // $ANTLR start "program"
    // src/Micro.g:18:1: program[SymbolTable globalSymTab] : 'PROGRAM' id 'BEGIN' pgm_body 'END' ;
    public final void program(SymbolTable globalSymTab) throws RecognitionException {
        try {
            // src/Micro.g:19:3: ( 'PROGRAM' id 'BEGIN' pgm_body 'END' )
            // src/Micro.g:19:5: 'PROGRAM' id 'BEGIN' pgm_body 'END'
            {

            			globalTab = globalSymTab;
            			currentTab = globalTab;
            			currentTab.scopeId = "global";
            			System.out.println("Printing Global Symbol Table");
            			
            match(input,K_PROGRAM,FOLLOW_K_PROGRAM_in_program60); 
            pushFollow(FOLLOW_id_in_program62);
            id();

            state._fsp--;

            match(input,K_BEGIN,FOLLOW_K_BEGIN_in_program67); 
            pushFollow(FOLLOW_pgm_body_in_program69);
            pgm_body();

            state._fsp--;

            match(input,K_END,FOLLOW_K_END_in_program71); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "program"

    public static class id_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "id"
    // src/Micro.g:28:1: id : IDENTIFIER ;
    public final MicroParser.id_return id() throws RecognitionException {
        MicroParser.id_return retval = new MicroParser.id_return();
        retval.start = input.LT(1);

        try {
            // src/Micro.g:29:3: ( IDENTIFIER )
            // src/Micro.g:29:5: IDENTIFIER
            {
            match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_id99); 

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "id"


    // $ANTLR start "pgm_body"
    // src/Micro.g:31:1: pgm_body : decl func_declarations ;
    public final void pgm_body() throws RecognitionException {
        try {
            // src/Micro.g:32:3: ( decl func_declarations )
            // src/Micro.g:32:5: decl func_declarations
            {
            pushFollow(FOLLOW_decl_in_pgm_body122);
            decl();

            state._fsp--;

            pushFollow(FOLLOW_func_declarations_in_pgm_body124);
            func_declarations();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "pgm_body"


    // $ANTLR start "decl"
    // src/Micro.g:34:1: decl : ( decl_list )* ;
    public final void decl() throws RecognitionException {
        try {
            // src/Micro.g:35:3: ( ( decl_list )* )
            // src/Micro.g:35:5: ( decl_list )*
            {
            // src/Micro.g:35:5: ( decl_list )*
            loop1:
            do {
                int alt1=2;
                alt1 = dfa1.predict(input);
                switch (alt1) {
            	case 1 :
            	    // src/Micro.g:35:5: decl_list
            	    {
            	    pushFollow(FOLLOW_decl_list_in_decl137);
            	    decl_list();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "decl"


    // $ANTLR start "decl_list"
    // src/Micro.g:37:1: decl_list : ( string_decl_list | var_decl_list );
    public final void decl_list() throws RecognitionException {
        try {
            // src/Micro.g:38:3: ( string_decl_list | var_decl_list )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==K_STRING) ) {
                alt2=1;
            }
            else if ( ((LA2_0>=K_FLOAT && LA2_0<=K_INT)) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // src/Micro.g:38:5: string_decl_list
                    {
                    pushFollow(FOLLOW_string_decl_list_in_decl_list154);
                    string_decl_list();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // src/Micro.g:38:24: var_decl_list
                    {
                    pushFollow(FOLLOW_var_decl_list_in_decl_list158);
                    var_decl_list();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "decl_list"


    // $ANTLR start "string_decl_list"
    // src/Micro.g:42:1: string_decl_list : ( string_decl )+ ;
    public final void string_decl_list() throws RecognitionException {
        try {
            // src/Micro.g:43:3: ( ( string_decl )+ )
            // src/Micro.g:43:5: ( string_decl )+
            {
            // src/Micro.g:43:5: ( string_decl )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                alt3 = dfa3.predict(input);
                switch (alt3) {
            	case 1 :
            	    // src/Micro.g:43:5: string_decl
            	    {
            	    pushFollow(FOLLOW_string_decl_in_string_decl_list175);
            	    string_decl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "string_decl_list"


    // $ANTLR start "string_decl"
    // src/Micro.g:45:1: string_decl : 'STRING' id ':=' str ';' ;
    public final void string_decl() throws RecognitionException {
        MicroParser.id_return id1 = null;

        MicroParser.str_return str2 = null;


        try {
            // src/Micro.g:46:3: ( 'STRING' id ':=' str ';' )
            // src/Micro.g:46:5: 'STRING' id ':=' str ';'
            {
            match(input,K_STRING,FOLLOW_K_STRING_in_string_decl195); 
            pushFollow(FOLLOW_id_in_string_decl197);
            id1=id();

            state._fsp--;

            match(input,28,FOLLOW_28_in_string_decl199); 
            pushFollow(FOLLOW_str_in_string_decl201);
            str2=str();

            state._fsp--;

            match(input,29,FOLLOW_29_in_string_decl203); 

            			Symbol s = new Symbol((id1!=null?input.toString(id1.start,id1.stop):null), "STRING", (str2!=null?input.toString(str2.start,str2.stop):null));
            			currentTab.define(s);
            			s.printStr();
            			

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "string_decl"

    public static class str_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "str"
    // src/Micro.g:53:1: str : STRINGLITERAL ;
    public final MicroParser.str_return str() throws RecognitionException {
        MicroParser.str_return retval = new MicroParser.str_return();
        retval.start = input.LT(1);

        try {
            // src/Micro.g:54:3: ( STRINGLITERAL )
            // src/Micro.g:54:5: STRINGLITERAL
            {
            match(input,STRINGLITERAL,FOLLOW_STRINGLITERAL_in_str235); 

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "str"


    // $ANTLR start "var_decl_list"
    // src/Micro.g:58:1: var_decl_list : ( var_decl )+ ;
    public final void var_decl_list() throws RecognitionException {
        try {
            // src/Micro.g:59:3: ( ( var_decl )+ )
            // src/Micro.g:59:5: ( var_decl )+
            {
            // src/Micro.g:59:5: ( var_decl )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                alt4 = dfa4.predict(input);
                switch (alt4) {
            	case 1 :
            	    // src/Micro.g:59:5: var_decl
            	    {
            	    pushFollow(FOLLOW_var_decl_in_var_decl_list256);
            	    var_decl();

            	    state._fsp--;


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

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "var_decl_list"


    // $ANTLR start "var_decl"
    // src/Micro.g:61:1: var_decl : var_type var_id_list ';' ;
    public final void var_decl() throws RecognitionException {
        try {
            // src/Micro.g:62:3: ( var_type var_id_list ';' )
            // src/Micro.g:62:5: var_type var_id_list ';'
            {
            pushFollow(FOLLOW_var_type_in_var_decl279);
            var_type();

            state._fsp--;

            pushFollow(FOLLOW_var_id_list_in_var_decl281);
            var_id_list();

            state._fsp--;

            match(input,29,FOLLOW_29_in_var_decl283); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "var_decl"


    // $ANTLR start "var_type"
    // src/Micro.g:64:1: var_type : ( 'FLOAT' | 'INT' );
    public final void var_type() throws RecognitionException {
        try {
            // src/Micro.g:65:3: ( 'FLOAT' | 'INT' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==K_FLOAT) ) {
                alt5=1;
            }
            else if ( (LA5_0==K_INT) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // src/Micro.g:65:5: 'FLOAT'
                    {
                    match(input,K_FLOAT,FOLLOW_K_FLOAT_in_var_type301); 
                    currentTab.currentVarType = "FLOAT";

                    }
                    break;
                case 2 :
                    // src/Micro.g:66:5: 'INT'
                    {
                    match(input,K_INT,FOLLOW_K_INT_in_var_type310); 
                    currentTab.currentVarType = "INT";

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "var_type"


    // $ANTLR start "any_type"
    // src/Micro.g:68:1: any_type : ( var_type | 'VOID' );
    public final void any_type() throws RecognitionException {
        try {
            // src/Micro.g:69:3: ( var_type | 'VOID' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( ((LA6_0>=K_FLOAT && LA6_0<=K_INT)) ) {
                alt6=1;
            }
            else if ( (LA6_0==K_VOID) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // src/Micro.g:69:5: var_type
                    {
                    pushFollow(FOLLOW_var_type_in_any_type337);
                    var_type();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // src/Micro.g:70:5: 'VOID'
                    {
                    match(input,K_VOID,FOLLOW_K_VOID_in_any_type343); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "any_type"


    // $ANTLR start "var_id_list"
    // src/Micro.g:72:1: var_id_list : var_id ( ',' var_id )* ;
    public final void var_id_list() throws RecognitionException {
        try {
            // src/Micro.g:73:3: ( var_id ( ',' var_id )* )
            // src/Micro.g:73:5: var_id ( ',' var_id )*
            {
            pushFollow(FOLLOW_var_id_in_var_id_list356);
            var_id();

            state._fsp--;

            // src/Micro.g:73:12: ( ',' var_id )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==30) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // src/Micro.g:73:14: ',' var_id
            	    {
            	    match(input,30,FOLLOW_30_in_var_id_list360); 
            	    pushFollow(FOLLOW_var_id_in_var_id_list362);
            	    var_id();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "var_id_list"


    // $ANTLR start "var_id"
    // src/Micro.g:75:1: var_id : id ;
    public final void var_id() throws RecognitionException {
        MicroParser.id_return id3 = null;


        try {
            // src/Micro.g:76:4: ( id )
            // src/Micro.g:76:6: id
            {
            pushFollow(FOLLOW_id_in_var_id378);
            id3=id();

            state._fsp--;


            			Symbol s = new Symbol((id3!=null?input.toString(id3.start,id3.stop):null),currentTab.currentVarType);
            			currentTab.define(s);
            			s.print();
            			

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "var_id"


    // $ANTLR start "id_list"
    // src/Micro.g:83:1: id_list : id ( ',' id )* ;
    public final void id_list() throws RecognitionException {
        try {
            // src/Micro.g:84:3: ( id ( ',' id )* )
            // src/Micro.g:84:5: id ( ',' id )*
            {
            pushFollow(FOLLOW_id_in_id_list408);
            id();

            state._fsp--;

            // src/Micro.g:84:8: ( ',' id )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==30) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // src/Micro.g:84:10: ',' id
            	    {
            	    match(input,30,FOLLOW_30_in_id_list412); 
            	    pushFollow(FOLLOW_id_in_id_list414);
            	    id();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "id_list"


    // $ANTLR start "param_decl_list"
    // src/Micro.g:88:1: param_decl_list : param_decl param_decl_tail ;
    public final void param_decl_list() throws RecognitionException {
        try {
            // src/Micro.g:89:3: ( param_decl param_decl_tail )
            // src/Micro.g:89:5: param_decl param_decl_tail
            {
            pushFollow(FOLLOW_param_decl_in_param_decl_list437);
            param_decl();

            state._fsp--;

            pushFollow(FOLLOW_param_decl_tail_in_param_decl_list439);
            param_decl_tail();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "param_decl_list"


    // $ANTLR start "param_decl"
    // src/Micro.g:91:1: param_decl : var_type id ;
    public final void param_decl() throws RecognitionException {
        try {
            // src/Micro.g:92:3: ( var_type id )
            // src/Micro.g:92:5: var_type id
            {
            pushFollow(FOLLOW_var_type_in_param_decl460);
            var_type();

            state._fsp--;

            pushFollow(FOLLOW_id_in_param_decl462);
            id();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "param_decl"


    // $ANTLR start "param_decl_tail"
    // src/Micro.g:94:1: param_decl_tail : ( ',' param_decl )* ;
    public final void param_decl_tail() throws RecognitionException {
        try {
            // src/Micro.g:95:3: ( ( ',' param_decl )* )
            // src/Micro.g:95:5: ( ',' param_decl )*
            {
            // src/Micro.g:95:5: ( ',' param_decl )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==30) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // src/Micro.g:95:7: ',' param_decl
            	    {
            	    match(input,30,FOLLOW_30_in_param_decl_tail480); 
            	    pushFollow(FOLLOW_param_decl_in_param_decl_tail482);
            	    param_decl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "param_decl_tail"


    // $ANTLR start "func_declarations"
    // src/Micro.g:99:1: func_declarations : ( func_decl )* ;
    public final void func_declarations() throws RecognitionException {
        try {
            // src/Micro.g:100:3: ( ( func_decl )* )
            // src/Micro.g:100:5: ( func_decl )*
            {
            // src/Micro.g:100:5: ( func_decl )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==K_FUNCTION) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // src/Micro.g:100:5: func_decl
            	    {
            	    pushFollow(FOLLOW_func_decl_in_func_declarations502);
            	    func_decl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "func_declarations"


    // $ANTLR start "func_decl"
    // src/Micro.g:102:1: func_decl : 'FUNCTION' any_type id '(' ( param_decl_list )? ')' 'BEGIN' func_body 'END' ;
    public final void func_decl() throws RecognitionException {
        MicroParser.id_return id4 = null;


        try {
            // src/Micro.g:103:3: ( 'FUNCTION' any_type id '(' ( param_decl_list )? ')' 'BEGIN' func_body 'END' )
            // src/Micro.g:103:5: 'FUNCTION' any_type id '(' ( param_decl_list )? ')' 'BEGIN' func_body 'END'
            {
            match(input,K_FUNCTION,FOLLOW_K_FUNCTION_in_func_decl524); 
            pushFollow(FOLLOW_any_type_in_func_decl526);
            any_type();

            state._fsp--;

            pushFollow(FOLLOW_id_in_func_decl528);
            id4=id();

            state._fsp--;


            			SymbolTable func = new SymbolTable((id4!=null?input.toString(id4.start,id4.stop):null));
            			currentTab = func;
            			System.out.println("\nPrinting Symbol Table for "+func.scopeId);
            			
            match(input,31,FOLLOW_31_in_func_decl540); 
            // src/Micro.g:109:8: ( param_decl_list )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=K_FLOAT && LA11_0<=K_INT)) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // src/Micro.g:109:8: param_decl_list
                    {
                    pushFollow(FOLLOW_param_decl_list_in_func_decl542);
                    param_decl_list();

                    state._fsp--;


                    }
                    break;

            }

            match(input,32,FOLLOW_32_in_func_decl545); 
            match(input,K_BEGIN,FOLLOW_K_BEGIN_in_func_decl547); 
            pushFollow(FOLLOW_func_body_in_func_decl549);
            func_body();

            state._fsp--;

            match(input,K_END,FOLLOW_K_END_in_func_decl551); 
            currentTab = globalTab;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "func_decl"


    // $ANTLR start "func_body"
    // src/Micro.g:112:1: func_body : decl stmt_list ;
    public final void func_body() throws RecognitionException {
        try {
            // src/Micro.g:113:3: ( decl stmt_list )
            // src/Micro.g:113:5: decl stmt_list
            {
            pushFollow(FOLLOW_decl_in_func_body578);
            decl();

            state._fsp--;

            pushFollow(FOLLOW_stmt_list_in_func_body580);
            stmt_list();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "func_body"


    // $ANTLR start "stmt_list"
    // src/Micro.g:117:1: stmt_list : ( stmt )* ;
    public final void stmt_list() throws RecognitionException {
        try {
            // src/Micro.g:118:3: ( ( stmt )* )
            // src/Micro.g:118:5: ( stmt )*
            {
            // src/Micro.g:118:5: ( stmt )*
            loop12:
            do {
                int alt12=2;
                alt12 = dfa12.predict(input);
                switch (alt12) {
            	case 1 :
            	    // src/Micro.g:118:5: stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_stmt_list605);
            	    stmt();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "stmt_list"


    // $ANTLR start "stmt"
    // src/Micro.g:120:1: stmt : ( assign_stmt | read_stmt | write_stmt | return_stmt | if_stmt | for_stmt );
    public final void stmt() throws RecognitionException {
        try {
            // src/Micro.g:121:3: ( assign_stmt | read_stmt | write_stmt | return_stmt | if_stmt | for_stmt )
            int alt13=6;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                alt13=1;
                }
                break;
            case K_READ:
                {
                alt13=2;
                }
                break;
            case K_WRITE:
                {
                alt13=3;
                }
                break;
            case K_RETURN:
                {
                alt13=4;
                }
                break;
            case K_IF:
                {
                alt13=5;
                }
                break;
            case K_FOR:
                {
                alt13=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // src/Micro.g:121:5: assign_stmt
                    {
                    pushFollow(FOLLOW_assign_stmt_in_stmt632);
                    assign_stmt();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // src/Micro.g:121:19: read_stmt
                    {
                    pushFollow(FOLLOW_read_stmt_in_stmt636);
                    read_stmt();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // src/Micro.g:121:31: write_stmt
                    {
                    pushFollow(FOLLOW_write_stmt_in_stmt640);
                    write_stmt();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // src/Micro.g:121:44: return_stmt
                    {
                    pushFollow(FOLLOW_return_stmt_in_stmt644);
                    return_stmt();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // src/Micro.g:121:58: if_stmt
                    {
                    pushFollow(FOLLOW_if_stmt_in_stmt648);
                    if_stmt();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // src/Micro.g:121:68: for_stmt
                    {
                    pushFollow(FOLLOW_for_stmt_in_stmt652);
                    for_stmt();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "stmt"


    // $ANTLR start "assign_stmt"
    // src/Micro.g:125:1: assign_stmt : assign_expr ';' ;
    public final void assign_stmt() throws RecognitionException {
        try {
            // src/Micro.g:126:3: ( assign_expr ';' )
            // src/Micro.g:126:5: assign_expr ';'
            {
            pushFollow(FOLLOW_assign_expr_in_assign_stmt675);
            assign_expr();

            state._fsp--;

            match(input,29,FOLLOW_29_in_assign_stmt677); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "assign_stmt"


    // $ANTLR start "assign_expr"
    // src/Micro.g:128:1: assign_expr : id ':=' expr ;
    public final void assign_expr() throws RecognitionException {
        try {
            // src/Micro.g:129:3: ( id ':=' expr )
            // src/Micro.g:129:5: id ':=' expr
            {
            pushFollow(FOLLOW_id_in_assign_expr696);
            id();

            state._fsp--;

            match(input,28,FOLLOW_28_in_assign_expr698); 
            pushFollow(FOLLOW_expr_in_assign_expr700);
            expr();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "assign_expr"


    // $ANTLR start "read_stmt"
    // src/Micro.g:131:1: read_stmt : 'READ' '(' id_list ')' ';' ;
    public final void read_stmt() throws RecognitionException {
        try {
            // src/Micro.g:132:3: ( 'READ' '(' id_list ')' ';' )
            // src/Micro.g:132:5: 'READ' '(' id_list ')' ';'
            {
            match(input,K_READ,FOLLOW_K_READ_in_read_stmt721); 
            match(input,31,FOLLOW_31_in_read_stmt723); 
            pushFollow(FOLLOW_id_list_in_read_stmt725);
            id_list();

            state._fsp--;

            match(input,32,FOLLOW_32_in_read_stmt727); 
            match(input,29,FOLLOW_29_in_read_stmt729); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "read_stmt"


    // $ANTLR start "write_stmt"
    // src/Micro.g:134:1: write_stmt : 'WRITE' '(' id_list ')' ';' ;
    public final void write_stmt() throws RecognitionException {
        try {
            // src/Micro.g:135:3: ( 'WRITE' '(' id_list ')' ';' )
            // src/Micro.g:135:5: 'WRITE' '(' id_list ')' ';'
            {
            match(input,K_WRITE,FOLLOW_K_WRITE_in_write_stmt749); 
            match(input,31,FOLLOW_31_in_write_stmt751); 
            pushFollow(FOLLOW_id_list_in_write_stmt753);
            id_list();

            state._fsp--;

            match(input,32,FOLLOW_32_in_write_stmt755); 
            match(input,29,FOLLOW_29_in_write_stmt757); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "write_stmt"


    // $ANTLR start "return_stmt"
    // src/Micro.g:137:1: return_stmt : 'RETURN' expr ';' ;
    public final void return_stmt() throws RecognitionException {
        try {
            // src/Micro.g:138:3: ( 'RETURN' expr ';' )
            // src/Micro.g:138:5: 'RETURN' expr ';'
            {
            match(input,K_RETURN,FOLLOW_K_RETURN_in_return_stmt776); 
            pushFollow(FOLLOW_expr_in_return_stmt778);
            expr();

            state._fsp--;

            match(input,29,FOLLOW_29_in_return_stmt780); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "return_stmt"


    // $ANTLR start "expr"
    // src/Micro.g:142:1: expr : factor ( addop factor )* ;
    public final void expr() throws RecognitionException {
        try {
            // src/Micro.g:143:3: ( factor ( addop factor )* )
            // src/Micro.g:143:5: factor ( addop factor )*
            {
            pushFollow(FOLLOW_factor_in_expr809);
            factor();

            state._fsp--;

            // src/Micro.g:143:12: ( addop factor )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=33 && LA14_0<=34)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // src/Micro.g:143:13: addop factor
            	    {
            	    pushFollow(FOLLOW_addop_in_expr812);
            	    addop();

            	    state._fsp--;

            	    pushFollow(FOLLOW_factor_in_expr814);
            	    factor();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expr"


    // $ANTLR start "factor"
    // src/Micro.g:145:1: factor : postfix_expr ( mulop postfix_expr )* ;
    public final void factor() throws RecognitionException {
        try {
            // src/Micro.g:146:3: ( postfix_expr ( mulop postfix_expr )* )
            // src/Micro.g:146:5: postfix_expr ( mulop postfix_expr )*
            {
            pushFollow(FOLLOW_postfix_expr_in_factor841);
            postfix_expr();

            state._fsp--;

            // src/Micro.g:146:18: ( mulop postfix_expr )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=35 && LA15_0<=36)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // src/Micro.g:146:20: mulop postfix_expr
            	    {
            	    pushFollow(FOLLOW_mulop_in_factor845);
            	    mulop();

            	    state._fsp--;

            	    pushFollow(FOLLOW_postfix_expr_in_factor847);
            	    postfix_expr();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "factor"


    // $ANTLR start "postfix_expr"
    // src/Micro.g:148:1: postfix_expr : ( primary | call_expr );
    public final void postfix_expr() throws RecognitionException {
        try {
            // src/Micro.g:149:3: ( primary | call_expr )
            int alt16=2;
            alt16 = dfa16.predict(input);
            switch (alt16) {
                case 1 :
                    // src/Micro.g:149:5: primary
                    {
                    pushFollow(FOLLOW_primary_in_postfix_expr869);
                    primary();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // src/Micro.g:149:15: call_expr
                    {
                    pushFollow(FOLLOW_call_expr_in_postfix_expr873);
                    call_expr();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "postfix_expr"


    // $ANTLR start "call_expr"
    // src/Micro.g:151:1: call_expr : id '(' ( expr_list )? ')' ;
    public final void call_expr() throws RecognitionException {
        try {
            // src/Micro.g:152:3: ( id '(' ( expr_list )? ')' )
            // src/Micro.g:152:5: id '(' ( expr_list )? ')'
            {
            pushFollow(FOLLOW_id_in_call_expr895);
            id();

            state._fsp--;

            match(input,31,FOLLOW_31_in_call_expr897); 
            // src/Micro.g:152:12: ( expr_list )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==IDENTIFIER||(LA17_0>=INTLITERAL && LA17_0<=FLOATLITERAL)||LA17_0==31) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // src/Micro.g:152:12: expr_list
                    {
                    pushFollow(FOLLOW_expr_list_in_call_expr899);
                    expr_list();

                    state._fsp--;


                    }
                    break;

            }

            match(input,32,FOLLOW_32_in_call_expr902); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "call_expr"


    // $ANTLR start "expr_list"
    // src/Micro.g:154:1: expr_list : expr ( ',' expr )* ;
    public final void expr_list() throws RecognitionException {
        try {
            // src/Micro.g:155:3: ( expr ( ',' expr )* )
            // src/Micro.g:155:5: expr ( ',' expr )*
            {
            pushFollow(FOLLOW_expr_in_expr_list924);
            expr();

            state._fsp--;

            // src/Micro.g:155:10: ( ',' expr )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==30) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // src/Micro.g:155:12: ',' expr
            	    {
            	    match(input,30,FOLLOW_30_in_expr_list928); 
            	    pushFollow(FOLLOW_expr_in_expr_list930);
            	    expr();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expr_list"


    // $ANTLR start "primary"
    // src/Micro.g:157:1: primary : ( '(' expr ')' | id | INTLITERAL | FLOATLITERAL );
    public final void primary() throws RecognitionException {
        try {
            // src/Micro.g:158:3: ( '(' expr ')' | id | INTLITERAL | FLOATLITERAL )
            int alt19=4;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt19=1;
                }
                break;
            case IDENTIFIER:
                {
                alt19=2;
                }
                break;
            case INTLITERAL:
                {
                alt19=3;
                }
                break;
            case FLOATLITERAL:
                {
                alt19=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // src/Micro.g:158:5: '(' expr ')'
                    {
                    match(input,31,FOLLOW_31_in_primary956); 
                    pushFollow(FOLLOW_expr_in_primary958);
                    expr();

                    state._fsp--;

                    match(input,32,FOLLOW_32_in_primary960); 

                    }
                    break;
                case 2 :
                    // src/Micro.g:158:20: id
                    {
                    pushFollow(FOLLOW_id_in_primary964);
                    id();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // src/Micro.g:158:25: INTLITERAL
                    {
                    match(input,INTLITERAL,FOLLOW_INTLITERAL_in_primary968); 

                    }
                    break;
                case 4 :
                    // src/Micro.g:158:38: FLOATLITERAL
                    {
                    match(input,FLOATLITERAL,FOLLOW_FLOATLITERAL_in_primary972); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "primary"


    // $ANTLR start "addop"
    // src/Micro.g:160:1: addop : ( '+' | '-' );
    public final void addop() throws RecognitionException {
        try {
            // src/Micro.g:161:3: ( '+' | '-' )
            // src/Micro.g:
            {
            if ( (input.LA(1)>=33 && input.LA(1)<=34) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "addop"


    // $ANTLR start "mulop"
    // src/Micro.g:163:1: mulop : ( '*' | '/' );
    public final void mulop() throws RecognitionException {
        try {
            // src/Micro.g:164:3: ( '*' | '/' )
            // src/Micro.g:
            {
            if ( (input.LA(1)>=35 && input.LA(1)<=36) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "mulop"


    // $ANTLR start "if_stmt"
    // src/Micro.g:168:1: if_stmt : 'IF' '(' cond ')' 'THEN' stmt_list else_part 'ENDIF' ;
    public final void if_stmt() throws RecognitionException {
        try {
            // src/Micro.g:169:3: ( 'IF' '(' cond ')' 'THEN' stmt_list else_part 'ENDIF' )
            // src/Micro.g:169:5: 'IF' '(' cond ')' 'THEN' stmt_list else_part 'ENDIF'
            {
            match(input,K_IF,FOLLOW_K_IF_in_if_stmt1060); 
            match(input,31,FOLLOW_31_in_if_stmt1062); 
            pushFollow(FOLLOW_cond_in_if_stmt1064);
            cond();

            state._fsp--;

            match(input,32,FOLLOW_32_in_if_stmt1066); 
            match(input,K_THEN,FOLLOW_K_THEN_in_if_stmt1068); 
            pushFollow(FOLLOW_stmt_list_in_if_stmt1070);
            stmt_list();

            state._fsp--;

            pushFollow(FOLLOW_else_part_in_if_stmt1072);
            else_part();

            state._fsp--;

            match(input,K_ENDIF,FOLLOW_K_ENDIF_in_if_stmt1074); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "if_stmt"


    // $ANTLR start "else_part"
    // src/Micro.g:171:1: else_part : ( 'ELSE' stmt_list )? ;
    public final void else_part() throws RecognitionException {
        try {
            // src/Micro.g:172:3: ( ( 'ELSE' stmt_list )? )
            // src/Micro.g:172:5: ( 'ELSE' stmt_list )?
            {
            // src/Micro.g:172:5: ( 'ELSE' stmt_list )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==K_ELSE) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // src/Micro.g:172:7: 'ELSE' stmt_list
                    {
                    match(input,K_ELSE,FOLLOW_K_ELSE_in_else_part1098); 
                    pushFollow(FOLLOW_stmt_list_in_else_part1100);
                    stmt_list();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "else_part"


    // $ANTLR start "cond"
    // src/Micro.g:174:1: cond : expr compop expr ;
    public final void cond() throws RecognitionException {
        try {
            // src/Micro.g:175:3: ( expr compop expr )
            // src/Micro.g:175:5: expr compop expr
            {
            pushFollow(FOLLOW_expr_in_cond1130);
            expr();

            state._fsp--;

            pushFollow(FOLLOW_compop_in_cond1132);
            compop();

            state._fsp--;

            pushFollow(FOLLOW_expr_in_cond1134);
            expr();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "cond"


    // $ANTLR start "compop"
    // src/Micro.g:177:1: compop : ( '<' | '>' | '=' );
    public final void compop() throws RecognitionException {
        try {
            // src/Micro.g:178:3: ( '<' | '>' | '=' )
            // src/Micro.g:
            {
            if ( (input.LA(1)>=37 && input.LA(1)<=39) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "compop"


    // $ANTLR start "for_stmt"
    // src/Micro.g:180:1: for_stmt : 'FOR' '(' ( assign_expr )? ';' ( cond )? ';' ( assign_expr )? ')' stmt_list 'ENDFOR' ;
    public final void for_stmt() throws RecognitionException {
        try {
            // src/Micro.g:181:3: ( 'FOR' '(' ( assign_expr )? ';' ( cond )? ';' ( assign_expr )? ')' stmt_list 'ENDFOR' )
            // src/Micro.g:181:5: 'FOR' '(' ( assign_expr )? ';' ( cond )? ';' ( assign_expr )? ')' stmt_list 'ENDFOR'
            {
            match(input,K_FOR,FOLLOW_K_FOR_in_for_stmt1190); 
            match(input,31,FOLLOW_31_in_for_stmt1192); 
            // src/Micro.g:181:15: ( assign_expr )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==IDENTIFIER) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // src/Micro.g:181:15: assign_expr
                    {
                    pushFollow(FOLLOW_assign_expr_in_for_stmt1194);
                    assign_expr();

                    state._fsp--;


                    }
                    break;

            }

            match(input,29,FOLLOW_29_in_for_stmt1197); 
            // src/Micro.g:181:32: ( cond )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==IDENTIFIER||(LA22_0>=INTLITERAL && LA22_0<=FLOATLITERAL)||LA22_0==31) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // src/Micro.g:181:32: cond
                    {
                    pushFollow(FOLLOW_cond_in_for_stmt1199);
                    cond();

                    state._fsp--;


                    }
                    break;

            }

            match(input,29,FOLLOW_29_in_for_stmt1202); 
            // src/Micro.g:181:42: ( assign_expr )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==IDENTIFIER) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // src/Micro.g:181:42: assign_expr
                    {
                    pushFollow(FOLLOW_assign_expr_in_for_stmt1204);
                    assign_expr();

                    state._fsp--;


                    }
                    break;

            }

            match(input,32,FOLLOW_32_in_for_stmt1207); 
            pushFollow(FOLLOW_stmt_list_in_for_stmt1209);
            stmt_list();

            state._fsp--;

            match(input,K_ENDFOR,FOLLOW_K_ENDFOR_in_for_stmt1211); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "for_stmt"

    // Delegated rules


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA3 dfa3 = new DFA3(this);
    protected DFA4 dfa4 = new DFA4(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA16 dfa16 = new DFA16(this);
    static final String DFA1_eotS =
        "\14\uffff";
    static final String DFA1_eofS =
        "\14\uffff";
    static final String DFA1_minS =
        "\1\4\13\uffff";
    static final String DFA1_maxS =
        "\1\31\13\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\2\7\uffff\1\1\2\uffff";
    static final String DFA1_specialS =
        "\14\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\1\5\uffff\1\1\1\uffff\4\1\3\uffff\2\1\1\uffff\2\11\1\uffff"+
            "\1\11",
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
            ""
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "()* loopback of 35:5: ( decl_list )*";
        }
    }
    static final String DFA3_eotS =
        "\14\uffff";
    static final String DFA3_eofS =
        "\14\uffff";
    static final String DFA3_minS =
        "\1\4\13\uffff";
    static final String DFA3_maxS =
        "\1\31\13\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\2\7\uffff\1\1\2\uffff";
    static final String DFA3_specialS =
        "\14\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\5\uffff\1\1\1\uffff\4\1\3\uffff\2\1\1\uffff\2\1\1\uffff"+
            "\1\11",
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
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "()+ loopback of 43:5: ( string_decl )+";
        }
    }
    static final String DFA4_eotS =
        "\14\uffff";
    static final String DFA4_eofS =
        "\14\uffff";
    static final String DFA4_minS =
        "\1\4\13\uffff";
    static final String DFA4_maxS =
        "\1\31\13\uffff";
    static final String DFA4_acceptS =
        "\1\uffff\1\2\10\uffff\2\1";
    static final String DFA4_specialS =
        "\14\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1\5\uffff\1\1\1\uffff\4\1\3\uffff\2\1\1\uffff\1\12\1\13\1"+
            "\uffff\1\1",
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
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "()+ loopback of 59:5: ( var_decl )+";
        }
    }
    static final String DFA12_eotS =
        "\13\uffff";
    static final String DFA12_eofS =
        "\13\uffff";
    static final String DFA12_minS =
        "\1\4\12\uffff";
    static final String DFA12_maxS =
        "\1\25\12\uffff";
    static final String DFA12_acceptS =
        "\1\uffff\1\2\3\uffff\1\1\5\uffff";
    static final String DFA12_specialS =
        "\13\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\5\5\uffff\1\1\2\uffff\3\5\1\uffff\2\1\2\5\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "()* loopback of 118:5: ( stmt )*";
        }
    }
    static final String DFA16_eotS =
        "\14\uffff";
    static final String DFA16_eofS =
        "\14\uffff";
    static final String DFA16_minS =
        "\1\4\1\uffff\1\35\11\uffff";
    static final String DFA16_maxS =
        "\1\37\1\uffff\1\47\11\uffff";
    static final String DFA16_acceptS =
        "\1\uffff\1\1\11\uffff\1\2";
    static final String DFA16_specialS =
        "\14\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\2\1\uffff\2\1\27\uffff\1\1",
            "",
            "\2\1\1\13\10\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "148:1: postfix_expr : ( primary | call_expr );";
        }
    }
 

    public static final BitSet FOLLOW_K_PROGRAM_in_program60 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_id_in_program62 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_K_BEGIN_in_program67 = new BitSet(new long[]{0x0000000002C01000L});
    public static final BitSet FOLLOW_pgm_body_in_program69 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_K_END_in_program71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_id99 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decl_in_pgm_body122 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_func_declarations_in_pgm_body124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decl_list_in_decl137 = new BitSet(new long[]{0x0000000002C00002L});
    public static final BitSet FOLLOW_string_decl_list_in_decl_list154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_decl_list_in_decl_list158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_decl_in_string_decl_list175 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_K_STRING_in_string_decl195 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_id_in_string_decl197 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_string_decl199 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_str_in_string_decl201 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_string_decl203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRINGLITERAL_in_str235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_decl_in_var_decl_list256 = new BitSet(new long[]{0x0000000002C00002L});
    public static final BitSet FOLLOW_var_type_in_var_decl279 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_var_id_list_in_var_decl281 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_var_decl283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_FLOAT_in_var_type301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_INT_in_var_type310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_type_in_any_type337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_VOID_in_any_type343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_id_in_var_id_list356 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_var_id_list360 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_var_id_in_var_id_list362 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_id_in_var_id378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_id_list408 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_id_list412 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_id_in_id_list414 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_param_decl_in_param_decl_list437 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_param_decl_tail_in_param_decl_list439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_type_in_param_decl460 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_id_in_param_decl462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_param_decl_tail480 = new BitSet(new long[]{0x0000000002C00000L});
    public static final BitSet FOLLOW_param_decl_in_param_decl_tail482 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_func_decl_in_func_declarations502 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_K_FUNCTION_in_func_decl524 = new BitSet(new long[]{0x0000000003C00000L});
    public static final BitSet FOLLOW_any_type_in_func_decl526 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_id_in_func_decl528 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_func_decl540 = new BitSet(new long[]{0x0000000102C00000L});
    public static final BitSet FOLLOW_param_decl_list_in_func_decl542 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_func_decl545 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_K_BEGIN_in_func_decl547 = new BitSet(new long[]{0x0000000002D8E010L});
    public static final BitSet FOLLOW_func_body_in_func_decl549 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_K_END_in_func_decl551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decl_in_func_body578 = new BitSet(new long[]{0x000000000018E010L});
    public static final BitSet FOLLOW_stmt_list_in_func_body580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stmt_in_stmt_list605 = new BitSet(new long[]{0x000000000018E012L});
    public static final BitSet FOLLOW_assign_stmt_in_stmt632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_read_stmt_in_stmt636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_write_stmt_in_stmt640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_stmt_in_stmt644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_stmt_in_stmt648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_stmt_in_stmt652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_expr_in_assign_stmt675 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_assign_stmt677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_assign_expr696 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_assign_expr698 = new BitSet(new long[]{0x00000000800000D0L});
    public static final BitSet FOLLOW_expr_in_assign_expr700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_READ_in_read_stmt721 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_read_stmt723 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_id_list_in_read_stmt725 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_read_stmt727 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_read_stmt729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_WRITE_in_write_stmt749 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_write_stmt751 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_id_list_in_write_stmt753 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_write_stmt755 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_write_stmt757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_RETURN_in_return_stmt776 = new BitSet(new long[]{0x00000000800000D0L});
    public static final BitSet FOLLOW_expr_in_return_stmt778 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_return_stmt780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_in_expr809 = new BitSet(new long[]{0x0000000600000002L});
    public static final BitSet FOLLOW_addop_in_expr812 = new BitSet(new long[]{0x00000000800000D0L});
    public static final BitSet FOLLOW_factor_in_expr814 = new BitSet(new long[]{0x0000000600000002L});
    public static final BitSet FOLLOW_postfix_expr_in_factor841 = new BitSet(new long[]{0x0000001800000002L});
    public static final BitSet FOLLOW_mulop_in_factor845 = new BitSet(new long[]{0x00000000800000D0L});
    public static final BitSet FOLLOW_postfix_expr_in_factor847 = new BitSet(new long[]{0x0000001800000002L});
    public static final BitSet FOLLOW_primary_in_postfix_expr869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_call_expr_in_postfix_expr873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_call_expr895 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_call_expr897 = new BitSet(new long[]{0x00000001800000D0L});
    public static final BitSet FOLLOW_expr_list_in_call_expr899 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_call_expr902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_expr_list924 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_expr_list928 = new BitSet(new long[]{0x00000000800000D0L});
    public static final BitSet FOLLOW_expr_in_expr_list930 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_31_in_primary956 = new BitSet(new long[]{0x00000000800000D0L});
    public static final BitSet FOLLOW_expr_in_primary958 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_primary960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_primary964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTLITERAL_in_primary968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOATLITERAL_in_primary972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_addop0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_mulop0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_IF_in_if_stmt1060 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_if_stmt1062 = new BitSet(new long[]{0x00000000800000D0L});
    public static final BitSet FOLLOW_cond_in_if_stmt1064 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_if_stmt1066 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_K_THEN_in_if_stmt1068 = new BitSet(new long[]{0x00000000001EE010L});
    public static final BitSet FOLLOW_stmt_list_in_if_stmt1070 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_else_part_in_if_stmt1072 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_K_ENDIF_in_if_stmt1074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_ELSE_in_else_part1098 = new BitSet(new long[]{0x000000000018E010L});
    public static final BitSet FOLLOW_stmt_list_in_else_part1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_cond1130 = new BitSet(new long[]{0x000000E000000000L});
    public static final BitSet FOLLOW_compop_in_cond1132 = new BitSet(new long[]{0x00000000800000D0L});
    public static final BitSet FOLLOW_expr_in_cond1134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_compop0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_FOR_in_for_stmt1190 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_for_stmt1192 = new BitSet(new long[]{0x0000000020000010L});
    public static final BitSet FOLLOW_assign_expr_in_for_stmt1194 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_for_stmt1197 = new BitSet(new long[]{0x00000000A00000D0L});
    public static final BitSet FOLLOW_cond_in_for_stmt1199 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_for_stmt1202 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_assign_expr_in_for_stmt1204 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_for_stmt1207 = new BitSet(new long[]{0x000000000038E010L});
    public static final BitSet FOLLOW_stmt_list_in_for_stmt1209 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_K_ENDFOR_in_for_stmt1211 = new BitSet(new long[]{0x0000000000000002L});

}
// $ANTLR 3.4 C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g 2015-03-11 09:44:01

package dm.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class DMLexer extends Lexer {
    public static final int EOF=-1;
    public static final int AND=4;
    public static final int COLON=5;
    public static final int COMMENT=6;
    public static final int EXPECTATIONS=7;
    public static final int IMPOSSIBLE=8;
    public static final int LINE_COMMENT=9;
    public static final int NEWLINE=10;
    public static final int NOT=11;
    public static final int POINT=12;
    public static final int PREFERENCES=13;
    public static final int RULEARROW=14;
    public static final int TEXT=15;
    public static final int TRUE=16;
    public static final int WS=17;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public DMLexer() {} 
    public DMLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public DMLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g"; }

    // $ANTLR start "PREFERENCES"
    public final void mPREFERENCES() throws RecognitionException {
        try {
            int _type = PREFERENCES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:44:13: ( 'Preferences' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:44:15: 'Preferences'
            {
            match("Preferences"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PREFERENCES"

    // $ANTLR start "EXPECTATIONS"
    public final void mEXPECTATIONS() throws RecognitionException {
        try {
            int _type = EXPECTATIONS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:45:14: ( 'Expectations' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:45:16: 'Expectations'
            {
            match("Expectations"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPECTATIONS"

    // $ANTLR start "IMPOSSIBLE"
    public final void mIMPOSSIBLE() throws RecognitionException {
        try {
            int _type = IMPOSSIBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:46:12: ( 'Impossible states' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:46:14: 'Impossible states'
            {
            match("Impossible states"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IMPOSSIBLE"

    // $ANTLR start "RULEARROW"
    public final void mRULEARROW() throws RecognitionException {
        try {
            int _type = RULEARROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:47:11: ( '=>' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:47:13: '=>'
            {
            match("=>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RULEARROW"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:48:6: ( 'true' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:48:8: 'true'
            {
            match("true"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:62:5: ( '/*' ( . )* '*/' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:62:7: '/*' ( . )* '*/'
            {
            match("/*"); 



            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:62:12: ( . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='*') ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1=='/') ) {
                        alt1=2;
                    }
                    else if ( ((LA1_1 >= '\u0000' && LA1_1 <= '.')||(LA1_1 >= '0' && LA1_1 <= '\uFFFF')) ) {
                        alt1=1;
                    }


                }
                else if ( ((LA1_0 >= '\u0000' && LA1_0 <= ')')||(LA1_0 >= '+' && LA1_0 <= '\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:62:12: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            match("*/"); 



            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:65:5: ( '%' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:65:7: '%' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match('%'); 

            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:65:11: (~ ( '\\n' | '\\r' ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '\u0000' && LA2_0 <= '\t')||(LA2_0 >= '\u000B' && LA2_0 <= '\f')||(LA2_0 >= '\u000E' && LA2_0 <= '\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:65:25: ( '\\r' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:65:25: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:67:8: ( ( '\\r' )? '\\n' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:67:9: ( '\\r' )? '\\n'
            {
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:67:9: ( '\\r' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\r') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:67:9: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:68:5: ( ( ' ' | '\\t' )+ )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:68:9: ( ' ' | '\\t' )+
            {
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:68:9: ( ' ' | '\\t' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='\t'||LA5_0==' ') ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "TEXT"
    public final void mTEXT() throws RecognitionException {
        try {
            int _type = TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:71:6: ( 'a' .. 'z' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '(' | ')' | ',' | '~' )* )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:71:8: 'a' .. 'z' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '(' | ')' | ',' | '~' )*
            {
            matchRange('a','z'); 

            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:71:16: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '(' | ')' | ',' | '~' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0 >= '(' && LA6_0 <= ')')||LA6_0==','||(LA6_0 >= '0' && LA6_0 <= '9')||(LA6_0 >= 'A' && LA6_0 <= 'Z')||LA6_0=='_'||(LA6_0 >= 'a' && LA6_0 <= 'z')||LA6_0=='~') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:
            	    {
            	    if ( (input.LA(1) >= '(' && input.LA(1) <= ')')||input.LA(1)==','||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||input.LA(1)=='~' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TEXT"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:73:5: ( '~' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:73:7: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "POINT"
    public final void mPOINT() throws RecognitionException {
        try {
            int _type = POINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:75:7: ( '.' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:75:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "POINT"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:76:6: ( '&&' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:76:8: '&&'
            {
            match("&&"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:78:7: ( ':' )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:78:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    public void mTokens() throws RecognitionException {
        // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:8: ( PREFERENCES | EXPECTATIONS | IMPOSSIBLE | RULEARROW | TRUE | COMMENT | LINE_COMMENT | NEWLINE | WS | TEXT | NOT | POINT | AND | COLON )
        int alt7=14;
        switch ( input.LA(1) ) {
        case 'P':
            {
            alt7=1;
            }
            break;
        case 'E':
            {
            alt7=2;
            }
            break;
        case 'I':
            {
            alt7=3;
            }
            break;
        case '=':
            {
            alt7=4;
            }
            break;
        case 't':
            {
            int LA7_5 = input.LA(2);

            if ( (LA7_5=='r') ) {
                int LA7_15 = input.LA(3);

                if ( (LA7_15=='u') ) {
                    int LA7_16 = input.LA(4);

                    if ( (LA7_16=='e') ) {
                        int LA7_17 = input.LA(5);

                        if ( ((LA7_17 >= '(' && LA7_17 <= ')')||LA7_17==','||(LA7_17 >= '0' && LA7_17 <= '9')||(LA7_17 >= 'A' && LA7_17 <= 'Z')||LA7_17=='_'||(LA7_17 >= 'a' && LA7_17 <= 'z')||LA7_17=='~') ) {
                            alt7=10;
                        }
                        else {
                            alt7=5;
                        }
                    }
                    else {
                        alt7=10;
                    }
                }
                else {
                    alt7=10;
                }
            }
            else {
                alt7=10;
            }
            }
            break;
        case '/':
            {
            alt7=6;
            }
            break;
        case '%':
            {
            alt7=7;
            }
            break;
        case '\n':
        case '\r':
            {
            alt7=8;
            }
            break;
        case '\t':
        case ' ':
            {
            alt7=9;
            }
            break;
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
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt7=10;
            }
            break;
        case '~':
            {
            alt7=11;
            }
            break;
        case '.':
            {
            alt7=12;
            }
            break;
        case '&':
            {
            alt7=13;
            }
            break;
        case ':':
            {
            alt7=14;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 7, 0, input);

            throw nvae;

        }

        switch (alt7) {
            case 1 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:10: PREFERENCES
                {
                mPREFERENCES(); 


                }
                break;
            case 2 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:22: EXPECTATIONS
                {
                mEXPECTATIONS(); 


                }
                break;
            case 3 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:35: IMPOSSIBLE
                {
                mIMPOSSIBLE(); 


                }
                break;
            case 4 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:46: RULEARROW
                {
                mRULEARROW(); 


                }
                break;
            case 5 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:56: TRUE
                {
                mTRUE(); 


                }
                break;
            case 6 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:61: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 7 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:69: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;
            case 8 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:82: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 9 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:90: WS
                {
                mWS(); 


                }
                break;
            case 10 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:93: TEXT
                {
                mTEXT(); 


                }
                break;
            case 11 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:98: NOT
                {
                mNOT(); 


                }
                break;
            case 12 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:102: POINT
                {
                mPOINT(); 


                }
                break;
            case 13 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:108: AND
                {
                mAND(); 


                }
                break;
            case 14 :
                // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:1:112: COLON
                {
                mCOLON(); 


                }
                break;

        }

    }


 

}
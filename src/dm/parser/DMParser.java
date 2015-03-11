// $ANTLR 3.4 C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g 2015-03-11 09:44:01

package dm.parser;

import dm.syntax.*;
import dm.gen.*;

import java.util.Set;
import java.util.HashSet;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class DMParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "COLON", "COMMENT", "EXPECTATIONS", "IMPOSSIBLE", "LINE_COMMENT", "NEWLINE", "NOT", "POINT", "PREFERENCES", "RULEARROW", "TEXT", "TRUE", "WS"
    };

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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public DMParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public DMParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return DMParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g"; }



    // $ANTLR start "program"
    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:23:1: program returns [RuleProgram prg] : PREFERENCES COLON r1= rules EXPECTATIONS COLON r2= rules IMPOSSIBLE COLON i= formulas EOF ;
    public final RuleProgram program() throws RecognitionException {
        RuleProgram prg = null;


        List<Rule> r1 =null;

        List<Rule> r2 =null;

        List<Set<Literal>> i =null;


        try {
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:23:35: ( PREFERENCES COLON r1= rules EXPECTATIONS COLON r2= rules IMPOSSIBLE COLON i= formulas EOF )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:24:2: PREFERENCES COLON r1= rules EXPECTATIONS COLON r2= rules IMPOSSIBLE COLON i= formulas EOF
            {
            match(input,PREFERENCES,FOLLOW_PREFERENCES_in_program47); 

            match(input,COLON,FOLLOW_COLON_in_program49); 

            pushFollow(FOLLOW_rules_in_program53);
            r1=rules();

            state._fsp--;


            match(input,EXPECTATIONS,FOLLOW_EXPECTATIONS_in_program57); 

            match(input,COLON,FOLLOW_COLON_in_program59); 

            pushFollow(FOLLOW_rules_in_program63);
            r2=rules();

            state._fsp--;


            match(input,IMPOSSIBLE,FOLLOW_IMPOSSIBLE_in_program67); 

            match(input,COLON,FOLLOW_COLON_in_program69); 

            pushFollow(FOLLOW_formulas_in_program73);
            i=formulas();

            state._fsp--;


            match(input,EOF,FOLLOW_EOF_in_program76); 

             prg = new RuleProgram(r1, r2, i); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return prg;
    }
    // $ANTLR end "program"



    // $ANTLR start "rules"
    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:31:1: rules returns [List<Rule> rules] : (r= rule )+ ;
    public final List<Rule> rules() throws RecognitionException {
        List<Rule> rules = null;


        Rule r =null;


        try {
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:31:34: ( (r= rule )+ )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:31:36: (r= rule )+
            {
            rules = new ArrayList<>();

            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:32:2: (r= rule )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==NOT||(LA1_0 >= TEXT && LA1_0 <= TRUE)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:32:3: r= rule
            	    {
            	    pushFollow(FOLLOW_rule_in_rules100);
            	    r=rule();

            	    state._fsp--;


            	     rules.add(r); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return rules;
    }
    // $ANTLR end "rules"



    // $ANTLR start "rule"
    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:34:1: rule returns [Rule rule] : ant= literals RULEARROW con= literals POINT ;
    public final Rule rule() throws RecognitionException {
        Rule rule = null;


        Formula ant =null;

        Formula con =null;


        try {
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:34:26: (ant= literals RULEARROW con= literals POINT )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:35:2: ant= literals RULEARROW con= literals POINT
            {
            pushFollow(FOLLOW_literals_in_rule119);
            ant=literals();

            state._fsp--;


            match(input,RULEARROW,FOLLOW_RULEARROW_in_rule121); 

            pushFollow(FOLLOW_literals_in_rule125);
            con=literals();

            state._fsp--;


            match(input,POINT,FOLLOW_POINT_in_rule127); 

             rule = new Rule(ant, con); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return rule;
    }
    // $ANTLR end "rule"



    // $ANTLR start "formulas"
    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:38:1: formulas returns [List<Set<Literal>> fmls] : (f= literalset POINT )* ;
    public final List<Set<Literal>> formulas() throws RecognitionException {
        List<Set<Literal>> fmls = null;


        Set<Literal> f =null;


        try {
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:38:44: ( (f= literalset POINT )* )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:38:46: (f= literalset POINT )*
            {
             fmls = new ArrayList<>(); 

            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:39:2: (f= literalset POINT )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==NOT||LA2_0==TEXT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:39:3: f= literalset POINT
            	    {
            	    pushFollow(FOLLOW_literalset_in_formulas149);
            	    f=literalset();

            	    state._fsp--;


            	     fmls.add(f); 

            	    match(input,POINT,FOLLOW_POINT_in_formulas153); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return fmls;
    }
    // $ANTLR end "formulas"



    // $ANTLR start "literalset"
    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:41:1: literalset returns [Set<Literal> fml] :f1= literal ( AND f2= literal )* ;
    public final Set<Literal> literalset() throws RecognitionException {
        Set<Literal> fml = null;


        Literal f1 =null;

        Literal f2 =null;


        try {
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:41:39: (f1= literal ( AND f2= literal )* )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:41:41: f1= literal ( AND f2= literal )*
            {
            fml = new HashSet<>(); 

            pushFollow(FOLLOW_literal_in_literalset172);
            f1=literal();

            state._fsp--;


             fml.add(f1); 

            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:42:36: ( AND f2= literal )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==AND) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:42:37: AND f2= literal
            	    {
            	    match(input,AND,FOLLOW_AND_in_literalset177); 

            	    pushFollow(FOLLOW_literal_in_literalset181);
            	    f2=literal();

            	    state._fsp--;


            	     fml.add(f2); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return fml;
    }
    // $ANTLR end "literalset"



    // $ANTLR start "literals"
    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:51:1: literals returns [Formula fml] : ( TRUE |l= literal AND l2= literals |l= literal );
    public final Formula literals() throws RecognitionException {
        Formula fml = null;


        Literal l =null;

        Formula l2 =null;


        try {
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:51:32: ( TRUE |l= literal AND l2= literals |l= literal )
            int alt4=3;
            switch ( input.LA(1) ) {
            case TRUE:
                {
                alt4=1;
                }
                break;
            case NOT:
                {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==TEXT) ) {
                    int LA4_4 = input.LA(3);

                    if ( (LA4_4==AND) ) {
                        alt4=2;
                    }
                    else if ( (LA4_4==POINT||LA4_4==RULEARROW) ) {
                        alt4=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 4, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;

                }
                }
                break;
            case TEXT:
                {
                int LA4_3 = input.LA(2);

                if ( (LA4_3==AND) ) {
                    alt4=2;
                }
                else if ( (LA4_3==POINT||LA4_3==RULEARROW) ) {
                    alt4=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 3, input);

                    throw nvae;

                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:52:2: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_literals238); 

                    fml = new True(); 

                    }
                    break;
                case 2 :
                    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:53:2: l= literal AND l2= literals
                    {
                    pushFollow(FOLLOW_literal_in_literals248);
                    l=literal();

                    state._fsp--;


                    match(input,AND,FOLLOW_AND_in_literals250); 

                    pushFollow(FOLLOW_literals_in_literals254);
                    l2=literals();

                    state._fsp--;


                     fml = new And(l, l2); 

                    }
                    break;
                case 3 :
                    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:54:2: l= literal
                    {
                    pushFollow(FOLLOW_literal_in_literals263);
                    l=literal();

                    state._fsp--;


                     fml = l; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return fml;
    }
    // $ANTLR end "literals"



    // $ANTLR start "literal"
    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:56:1: literal returns [Literal fml] : ( NOT )? t= TEXT ;
    public final Literal literal() throws RecognitionException {
        Literal fml = null;


        Token t=null;

        try {
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:56:31: ( ( NOT )? t= TEXT )
            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:57:2: ( NOT )? t= TEXT
            {
            boolean positive = true;

            // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:58:2: ( NOT )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==NOT) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Dropbox\\code\\phd\\decisionmaking\\src\\dm\\parser\\DM.g:58:3: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_literal284); 

                    positive = false;

                    }
                    break;

            }


            t=(Token)match(input,TEXT,FOLLOW_TEXT_in_literal292); 

             fml = new Literal(t.getText(), positive); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return fml;
    }
    // $ANTLR end "literal"

    // Delegated rules


 

    public static final BitSet FOLLOW_PREFERENCES_in_program47 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_program49 = new BitSet(new long[]{0x0000000000018800L});
    public static final BitSet FOLLOW_rules_in_program53 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EXPECTATIONS_in_program57 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_program59 = new BitSet(new long[]{0x0000000000018800L});
    public static final BitSet FOLLOW_rules_in_program63 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_IMPOSSIBLE_in_program67 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_program69 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_formulas_in_program73 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_program76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_in_rules100 = new BitSet(new long[]{0x0000000000018802L});
    public static final BitSet FOLLOW_literals_in_rule119 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RULEARROW_in_rule121 = new BitSet(new long[]{0x0000000000018800L});
    public static final BitSet FOLLOW_literals_in_rule125 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_POINT_in_rule127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalset_in_formulas149 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_POINT_in_formulas153 = new BitSet(new long[]{0x0000000000008802L});
    public static final BitSet FOLLOW_literal_in_literalset172 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_AND_in_literalset177 = new BitSet(new long[]{0x0000000000008800L});
    public static final BitSet FOLLOW_literal_in_literalset181 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_TRUE_in_literals238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_literals248 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_AND_in_literals250 = new BitSet(new long[]{0x0000000000018800L});
    public static final BitSet FOLLOW_literals_in_literals254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_literals263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_literal284 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_TEXT_in_literal292 = new BitSet(new long[]{0x0000000000000002L});

}
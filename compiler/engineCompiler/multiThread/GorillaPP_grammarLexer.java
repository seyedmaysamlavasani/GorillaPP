// $ANTLR 3.4 GorillaPP_grammar.g 2014-06-25 19:00:02

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class GorillaPP_grammarLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
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
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int BINARY_LITERAL=4;
    public static final int BinaryDigit=5;
    public static final int CHARACTER_LITERAL=6;
    public static final int COMMENT=7;
    public static final int DECIMAL_LITERAL=8;
    public static final int DIGIT=9;
    public static final int EscapeSequence=10;
    public static final int GORILLA_INSTR_NAME=11;
    public static final int HEX_LITERAL=12;
    public static final int HexDigit=13;
    public static final int ID=14;
    public static final int INSTR_RETURN_TYPE=15;
    public static final int IntegerTypeSuffix=16;
    public static final int LETTER=17;
    public static final int LINE_COMMENT=18;
    public static final int OCTAL_LITERAL=19;
    public static final int OctalEscape=20;
    public static final int SEMICOLON=21;
    public static final int STRING_LITERAL=22;
    public static final int WS=23;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public GorillaPP_grammarLexer() {} 
    public GorillaPP_grammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public GorillaPP_grammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "GorillaPP_grammar.g"; }

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:2:7: ( '!' )
            // GorillaPP_grammar.g:2:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:3:7: ( '!=' )
            // GorillaPP_grammar.g:3:9: '!='
            {
            match("!="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:4:7: ( '#define' )
            // GorillaPP_grammar.g:4:9: '#define'
            {
            match("#define"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:5:7: ( '#pragma' )
            // GorillaPP_grammar.g:5:9: '#pragma'
            {
            match("#pragma"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:6:7: ( '%' )
            // GorillaPP_grammar.g:6:9: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:7:7: ( '&&' )
            // GorillaPP_grammar.g:7:9: '&&'
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
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:8:7: ( '&' )
            // GorillaPP_grammar.g:8:9: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:9:7: ( '(' )
            // GorillaPP_grammar.g:9:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:10:7: ( ')' )
            // GorillaPP_grammar.g:10:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:11:7: ( '*' )
            // GorillaPP_grammar.g:11:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:12:7: ( '+' )
            // GorillaPP_grammar.g:12:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:13:7: ( '++' )
            // GorillaPP_grammar.g:13:9: '++'
            {
            match("++"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:14:7: ( ',' )
            // GorillaPP_grammar.g:14:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:15:7: ( '-' )
            // GorillaPP_grammar.g:15:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:16:7: ( '--' )
            // GorillaPP_grammar.g:16:9: '--'
            {
            match("--"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:17:7: ( '.' )
            // GorillaPP_grammar.g:17:9: '.'
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
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:18:7: ( '/' )
            // GorillaPP_grammar.g:18:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:19:7: ( ':' )
            // GorillaPP_grammar.g:19:9: ':'
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
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:20:7: ( '<' )
            // GorillaPP_grammar.g:20:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:21:7: ( '<<' )
            // GorillaPP_grammar.g:21:9: '<<'
            {
            match("<<"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:22:7: ( '<=' )
            // GorillaPP_grammar.g:22:9: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:23:7: ( '=' )
            // GorillaPP_grammar.g:23:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:24:7: ( '==' )
            // GorillaPP_grammar.g:24:9: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:25:7: ( '>' )
            // GorillaPP_grammar.g:25:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:26:7: ( '>=' )
            // GorillaPP_grammar.g:26:9: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:27:7: ( '>>' )
            // GorillaPP_grammar.g:27:9: '>>'
            {
            match(">>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:28:7: ( '?' )
            // GorillaPP_grammar.g:28:9: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:29:7: ( 'CONCURRENT_SAFE' )
            // GorillaPP_grammar.g:29:9: 'CONCURRENT_SAFE'
            {
            match("CONCURRENT_SAFE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:30:7: ( 'INPUT' )
            // GorillaPP_grammar.g:30:9: 'INPUT'
            {
            match("INPUT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:31:7: ( 'OFFLOAD' )
            // GorillaPP_grammar.g:31:9: 'OFFLOAD'
            {
            match("OFFLOAD"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:32:7: ( 'OUTPUT' )
            // GorillaPP_grammar.g:32:9: 'OUTPUT'
            {
            match("OUTPUT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:33:7: ( 'PIPELINABLE' )
            // GorillaPP_grammar.g:33:9: 'PIPELINABLE'
            {
            match("PIPELINABLE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:34:7: ( '[' )
            // GorillaPP_grammar.g:34:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:35:7: ( ']' )
            // GorillaPP_grammar.g:35:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:36:7: ( '^' )
            // GorillaPP_grammar.g:36:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:37:7: ( 'bit_vector' )
            // GorillaPP_grammar.g:37:9: 'bit_vector'
            {
            match("bit_vector"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:38:7: ( 'break' )
            // GorillaPP_grammar.g:38:9: 'break'
            {
            match("break"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:39:7: ( 'case' )
            // GorillaPP_grammar.g:39:9: 'case'
            {
            match("case"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:40:7: ( 'char' )
            // GorillaPP_grammar.g:40:9: 'char'
            {
            match("char"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:41:7: ( 'continue' )
            // GorillaPP_grammar.g:41:9: 'continue'
            {
            match("continue"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:42:7: ( 'default' )
            // GorillaPP_grammar.g:42:9: 'default'
            {
            match("default"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:43:7: ( 'else' )
            // GorillaPP_grammar.g:43:9: 'else'
            {
            match("else"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:44:7: ( 'emit' )
            // GorillaPP_grammar.g:44:9: 'emit'
            {
            match("emit"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:45:7: ( 'enum' )
            // GorillaPP_grammar.g:45:9: 'enum'
            {
            match("enum"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:46:7: ( 'finish' )
            // GorillaPP_grammar.g:46:9: 'finish'
            {
            match("finish"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:47:7: ( 'if' )
            // GorillaPP_grammar.g:47:9: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:48:7: ( 'int' )
            // GorillaPP_grammar.g:48:9: 'int'
            {
            match("int"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:49:7: ( 'long' )
            // GorillaPP_grammar.g:49:9: 'long'
            {
            match("long"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:50:7: ( 'short' )
            // GorillaPP_grammar.g:50:9: 'short'
            {
            match("short"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:51:7: ( 'struct {' )
            // GorillaPP_grammar.g:51:9: 'struct {'
            {
            match("struct {"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:52:7: ( 'typedef' )
            // GorillaPP_grammar.g:52:9: 'typedef'
            {
            match("typedef"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:53:7: ( 'unsigned' )
            // GorillaPP_grammar.g:53:9: 'unsigned'
            {
            match("unsigned"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:54:7: ( '{' )
            // GorillaPP_grammar.g:54:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:55:7: ( '|' )
            // GorillaPP_grammar.g:55:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:56:7: ( '||' )
            // GorillaPP_grammar.g:56:9: '||'
            {
            match("||"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:57:7: ( '}' )
            // GorillaPP_grammar.g:57:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:58:7: ( '~' )
            // GorillaPP_grammar.g:58:9: '~'
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
    // $ANTLR end "T__80"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:936:11: ( ';' )
            // GorillaPP_grammar.g:936:13: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:1339:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // GorillaPP_grammar.g:1339:8: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:1343:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // GorillaPP_grammar.g:1343:9: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 



            // GorillaPP_grammar.g:1343:14: ( options {greedy=false; } : . )*
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
            	    // GorillaPP_grammar.g:1343:42: .
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
            // GorillaPP_grammar.g:1347:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // GorillaPP_grammar.g:1347:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 



            // GorillaPP_grammar.g:1347:12: (~ ( '\\n' | '\\r' ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '\u0000' && LA2_0 <= '\t')||(LA2_0 >= '\u000B' && LA2_0 <= '\f')||(LA2_0 >= '\u000E' && LA2_0 <= '\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // GorillaPP_grammar.g:
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


            // GorillaPP_grammar.g:1347:26: ( '\\r' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // GorillaPP_grammar.g:1347:26: '\\r'
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

    // $ANTLR start "INSTR_RETURN_TYPE"
    public final void mINSTR_RETURN_TYPE() throws RecognitionException {
        try {
            int _type = INSTR_RETURN_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:1352:2: ( 'instr_addr_t' )
            // GorillaPP_grammar.g:1352:4: 'instr_addr_t'
            {
            match("instr_addr_t"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INSTR_RETURN_TYPE"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // GorillaPP_grammar.g:1354:17: ( 'a' .. 'z' | 'A' .. 'Z' )
            // GorillaPP_grammar.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // GorillaPP_grammar.g:1357:16: ( '0' .. '9' )
            // GorillaPP_grammar.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "GORILLA_INSTR_NAME"
    public final void mGORILLA_INSTR_NAME() throws RecognitionException {
        try {
            int _type = GORILLA_INSTR_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:1361:2: ( 'GS_' ID )
            // GorillaPP_grammar.g:1361:4: 'GS_' ID
            {
            match("GS_"); 



            mID(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GORILLA_INSTR_NAME"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:1371:4: ( LETTER ( LETTER | DIGIT | '_' )* )
            // GorillaPP_grammar.g:1371:6: LETTER ( LETTER | DIGIT | '_' )*
            {
            mLETTER(); 


            // GorillaPP_grammar.g:1371:13: ( LETTER | DIGIT | '_' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '0' && LA4_0 <= '9')||(LA4_0 >= 'A' && LA4_0 <= 'Z')||LA4_0=='_'||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // GorillaPP_grammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
            	    break loop4;
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
    // $ANTLR end "ID"

    // $ANTLR start "CHARACTER_LITERAL"
    public final void mCHARACTER_LITERAL() throws RecognitionException {
        try {
            int _type = CHARACTER_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:1374:5: ( '\\'' ( EscapeSequence |~ ( '\\'' | '\\\\' ) ) '\\'' )
            // GorillaPP_grammar.g:1374:9: '\\'' ( EscapeSequence |~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 

            // GorillaPP_grammar.g:1374:14: ( EscapeSequence |~ ( '\\'' | '\\\\' ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\\') ) {
                alt5=1;
            }
            else if ( ((LA5_0 >= '\u0000' && LA5_0 <= '&')||(LA5_0 >= '(' && LA5_0 <= '[')||(LA5_0 >= ']' && LA5_0 <= '\uFFFF')) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // GorillaPP_grammar.g:1374:16: EscapeSequence
                    {
                    mEscapeSequence(); 


                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1374:33: ~ ( '\\'' | '\\\\' )
                    {
                    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHARACTER_LITERAL"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:1378:5: ( '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"' )
            // GorillaPP_grammar.g:1378:8: '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 

            // GorillaPP_grammar.g:1378:12: ( EscapeSequence |~ ( '\\\\' | '\"' ) )*
            loop6:
            do {
                int alt6=3;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\\') ) {
                    alt6=1;
                }
                else if ( ((LA6_0 >= '\u0000' && LA6_0 <= '!')||(LA6_0 >= '#' && LA6_0 <= '[')||(LA6_0 >= ']' && LA6_0 <= '\uFFFF')) ) {
                    alt6=2;
                }


                switch (alt6) {
            	case 1 :
            	    // GorillaPP_grammar.g:1378:14: EscapeSequence
            	    {
            	    mEscapeSequence(); 


            	    }
            	    break;
            	case 2 :
            	    // GorillaPP_grammar.g:1378:31: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
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


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "HexDigit"
    public final void mHexDigit() throws RecognitionException {
        try {
            // GorillaPP_grammar.g:1383:10: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // GorillaPP_grammar.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexDigit"

    // $ANTLR start "BinaryDigit"
    public final void mBinaryDigit() throws RecognitionException {
        try {
            // GorillaPP_grammar.g:1386:13: ( '0' .. '1' )
            // GorillaPP_grammar.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '1') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BinaryDigit"

    // $ANTLR start "IntegerTypeSuffix"
    public final void mIntegerTypeSuffix() throws RecognitionException {
        try {
            // GorillaPP_grammar.g:1390:2: ( ( 'u' | 'U' )? ( 'l' | 'L' ) )
            // GorillaPP_grammar.g:1390:4: ( 'u' | 'U' )? ( 'l' | 'L' )
            {
            // GorillaPP_grammar.g:1390:4: ( 'u' | 'U' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='U'||LA7_0=='u') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // GorillaPP_grammar.g:
                    {
                    if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IntegerTypeSuffix"

    // $ANTLR start "HEX_LITERAL"
    public final void mHEX_LITERAL() throws RecognitionException {
        try {
            int _type = HEX_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:1393:13: ( '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )? )
            // GorillaPP_grammar.g:1393:15: '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )?
            {
            match('0'); 

            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // GorillaPP_grammar.g:1393:29: ( HexDigit )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= '0' && LA8_0 <= '9')||(LA8_0 >= 'A' && LA8_0 <= 'F')||(LA8_0 >= 'a' && LA8_0 <= 'f')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // GorillaPP_grammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
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
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            // GorillaPP_grammar.g:1393:39: ( IntegerTypeSuffix )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='L'||LA9_0=='U'||LA9_0=='l'||LA9_0=='u') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // GorillaPP_grammar.g:1393:39: IntegerTypeSuffix
                    {
                    mIntegerTypeSuffix(); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEX_LITERAL"

    // $ANTLR start "BINARY_LITERAL"
    public final void mBINARY_LITERAL() throws RecognitionException {
        try {
            int _type = BINARY_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:1395:16: ( DECIMAL_LITERAL 'b' ( BinaryDigit )+ )
            // GorillaPP_grammar.g:1395:18: DECIMAL_LITERAL 'b' ( BinaryDigit )+
            {
            mDECIMAL_LITERAL(); 


            match('b'); 

            // GorillaPP_grammar.g:1395:38: ( BinaryDigit )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0 >= '0' && LA10_0 <= '1')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // GorillaPP_grammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '1') ) {
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
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BINARY_LITERAL"

    // $ANTLR start "DECIMAL_LITERAL"
    public final void mDECIMAL_LITERAL() throws RecognitionException {
        try {
            int _type = DECIMAL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:1398:17: ( ( '-' )? ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )? )
            // GorillaPP_grammar.g:1398:19: ( '-' )? ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )?
            {
            // GorillaPP_grammar.g:1398:19: ( '-' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='-') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // GorillaPP_grammar.g:1398:20: '-'
                    {
                    match('-'); 

                    }
                    break;

            }


            // GorillaPP_grammar.g:1398:26: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='0') ) {
                alt13=1;
            }
            else if ( ((LA13_0 >= '1' && LA13_0 <= '9')) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }
            switch (alt13) {
                case 1 :
                    // GorillaPP_grammar.g:1398:27: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1398:33: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 

                    // GorillaPP_grammar.g:1398:42: ( '0' .. '9' )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0 >= '0' && LA12_0 <= '9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // GorillaPP_grammar.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
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
                    	    break loop12;
                        }
                    } while (true);


                    }
                    break;

            }


            // GorillaPP_grammar.g:1398:53: ( IntegerTypeSuffix )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='L'||LA14_0=='U'||LA14_0=='l'||LA14_0=='u') ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // GorillaPP_grammar.g:1398:53: IntegerTypeSuffix
                    {
                    mIntegerTypeSuffix(); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DECIMAL_LITERAL"

    // $ANTLR start "OCTAL_LITERAL"
    public final void mOCTAL_LITERAL() throws RecognitionException {
        try {
            int _type = OCTAL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GorillaPP_grammar.g:1400:15: ( '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )? )
            // GorillaPP_grammar.g:1400:17: '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )?
            {
            match('0'); 

            // GorillaPP_grammar.g:1400:21: ( '0' .. '7' )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0 >= '0' && LA15_0 <= '7')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // GorillaPP_grammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
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
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            // GorillaPP_grammar.g:1400:33: ( IntegerTypeSuffix )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='L'||LA16_0=='U'||LA16_0=='l'||LA16_0=='u') ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // GorillaPP_grammar.g:1400:33: IntegerTypeSuffix
                    {
                    mIntegerTypeSuffix(); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OCTAL_LITERAL"

    // $ANTLR start "EscapeSequence"
    public final void mEscapeSequence() throws RecognitionException {
        try {
            // GorillaPP_grammar.g:1405:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | OctalEscape )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='\\') ) {
                int LA17_1 = input.LA(2);

                if ( (LA17_1=='\"'||LA17_1=='\''||LA17_1=='\\'||LA17_1=='b'||LA17_1=='f'||LA17_1=='n'||LA17_1=='r'||LA17_1=='t') ) {
                    alt17=1;
                }
                else if ( ((LA17_1 >= '0' && LA17_1 <= '7')) ) {
                    alt17=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // GorillaPP_grammar.g:1405:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 

                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1406:9: OctalEscape
                    {
                    mOctalEscape(); 


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EscapeSequence"

    // $ANTLR start "OctalEscape"
    public final void mOctalEscape() throws RecognitionException {
        try {
            // GorillaPP_grammar.g:1411:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt18=3;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='\\') ) {
                int LA18_1 = input.LA(2);

                if ( ((LA18_1 >= '0' && LA18_1 <= '3')) ) {
                    int LA18_2 = input.LA(3);

                    if ( ((LA18_2 >= '0' && LA18_2 <= '7')) ) {
                        int LA18_4 = input.LA(4);

                        if ( ((LA18_4 >= '0' && LA18_4 <= '7')) ) {
                            alt18=1;
                        }
                        else {
                            alt18=2;
                        }
                    }
                    else {
                        alt18=3;
                    }
                }
                else if ( ((LA18_1 >= '4' && LA18_1 <= '7')) ) {
                    int LA18_3 = input.LA(3);

                    if ( ((LA18_3 >= '0' && LA18_3 <= '7')) ) {
                        alt18=2;
                    }
                    else {
                        alt18=3;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }
            switch (alt18) {
                case 1 :
                    // GorillaPP_grammar.g:1411:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '3') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1412:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:1413:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OctalEscape"

    public void mTokens() throws RecognitionException {
        // GorillaPP_grammar.g:1:8: ( T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | SEMICOLON | WS | COMMENT | LINE_COMMENT | INSTR_RETURN_TYPE | GORILLA_INSTR_NAME | ID | CHARACTER_LITERAL | STRING_LITERAL | HEX_LITERAL | BINARY_LITERAL | DECIMAL_LITERAL | OCTAL_LITERAL )
        int alt19=70;
        alt19 = dfa19.predict(input);
        switch (alt19) {
            case 1 :
                // GorillaPP_grammar.g:1:10: T__24
                {
                mT__24(); 


                }
                break;
            case 2 :
                // GorillaPP_grammar.g:1:16: T__25
                {
                mT__25(); 


                }
                break;
            case 3 :
                // GorillaPP_grammar.g:1:22: T__26
                {
                mT__26(); 


                }
                break;
            case 4 :
                // GorillaPP_grammar.g:1:28: T__27
                {
                mT__27(); 


                }
                break;
            case 5 :
                // GorillaPP_grammar.g:1:34: T__28
                {
                mT__28(); 


                }
                break;
            case 6 :
                // GorillaPP_grammar.g:1:40: T__29
                {
                mT__29(); 


                }
                break;
            case 7 :
                // GorillaPP_grammar.g:1:46: T__30
                {
                mT__30(); 


                }
                break;
            case 8 :
                // GorillaPP_grammar.g:1:52: T__31
                {
                mT__31(); 


                }
                break;
            case 9 :
                // GorillaPP_grammar.g:1:58: T__32
                {
                mT__32(); 


                }
                break;
            case 10 :
                // GorillaPP_grammar.g:1:64: T__33
                {
                mT__33(); 


                }
                break;
            case 11 :
                // GorillaPP_grammar.g:1:70: T__34
                {
                mT__34(); 


                }
                break;
            case 12 :
                // GorillaPP_grammar.g:1:76: T__35
                {
                mT__35(); 


                }
                break;
            case 13 :
                // GorillaPP_grammar.g:1:82: T__36
                {
                mT__36(); 


                }
                break;
            case 14 :
                // GorillaPP_grammar.g:1:88: T__37
                {
                mT__37(); 


                }
                break;
            case 15 :
                // GorillaPP_grammar.g:1:94: T__38
                {
                mT__38(); 


                }
                break;
            case 16 :
                // GorillaPP_grammar.g:1:100: T__39
                {
                mT__39(); 


                }
                break;
            case 17 :
                // GorillaPP_grammar.g:1:106: T__40
                {
                mT__40(); 


                }
                break;
            case 18 :
                // GorillaPP_grammar.g:1:112: T__41
                {
                mT__41(); 


                }
                break;
            case 19 :
                // GorillaPP_grammar.g:1:118: T__42
                {
                mT__42(); 


                }
                break;
            case 20 :
                // GorillaPP_grammar.g:1:124: T__43
                {
                mT__43(); 


                }
                break;
            case 21 :
                // GorillaPP_grammar.g:1:130: T__44
                {
                mT__44(); 


                }
                break;
            case 22 :
                // GorillaPP_grammar.g:1:136: T__45
                {
                mT__45(); 


                }
                break;
            case 23 :
                // GorillaPP_grammar.g:1:142: T__46
                {
                mT__46(); 


                }
                break;
            case 24 :
                // GorillaPP_grammar.g:1:148: T__47
                {
                mT__47(); 


                }
                break;
            case 25 :
                // GorillaPP_grammar.g:1:154: T__48
                {
                mT__48(); 


                }
                break;
            case 26 :
                // GorillaPP_grammar.g:1:160: T__49
                {
                mT__49(); 


                }
                break;
            case 27 :
                // GorillaPP_grammar.g:1:166: T__50
                {
                mT__50(); 


                }
                break;
            case 28 :
                // GorillaPP_grammar.g:1:172: T__51
                {
                mT__51(); 


                }
                break;
            case 29 :
                // GorillaPP_grammar.g:1:178: T__52
                {
                mT__52(); 


                }
                break;
            case 30 :
                // GorillaPP_grammar.g:1:184: T__53
                {
                mT__53(); 


                }
                break;
            case 31 :
                // GorillaPP_grammar.g:1:190: T__54
                {
                mT__54(); 


                }
                break;
            case 32 :
                // GorillaPP_grammar.g:1:196: T__55
                {
                mT__55(); 


                }
                break;
            case 33 :
                // GorillaPP_grammar.g:1:202: T__56
                {
                mT__56(); 


                }
                break;
            case 34 :
                // GorillaPP_grammar.g:1:208: T__57
                {
                mT__57(); 


                }
                break;
            case 35 :
                // GorillaPP_grammar.g:1:214: T__58
                {
                mT__58(); 


                }
                break;
            case 36 :
                // GorillaPP_grammar.g:1:220: T__59
                {
                mT__59(); 


                }
                break;
            case 37 :
                // GorillaPP_grammar.g:1:226: T__60
                {
                mT__60(); 


                }
                break;
            case 38 :
                // GorillaPP_grammar.g:1:232: T__61
                {
                mT__61(); 


                }
                break;
            case 39 :
                // GorillaPP_grammar.g:1:238: T__62
                {
                mT__62(); 


                }
                break;
            case 40 :
                // GorillaPP_grammar.g:1:244: T__63
                {
                mT__63(); 


                }
                break;
            case 41 :
                // GorillaPP_grammar.g:1:250: T__64
                {
                mT__64(); 


                }
                break;
            case 42 :
                // GorillaPP_grammar.g:1:256: T__65
                {
                mT__65(); 


                }
                break;
            case 43 :
                // GorillaPP_grammar.g:1:262: T__66
                {
                mT__66(); 


                }
                break;
            case 44 :
                // GorillaPP_grammar.g:1:268: T__67
                {
                mT__67(); 


                }
                break;
            case 45 :
                // GorillaPP_grammar.g:1:274: T__68
                {
                mT__68(); 


                }
                break;
            case 46 :
                // GorillaPP_grammar.g:1:280: T__69
                {
                mT__69(); 


                }
                break;
            case 47 :
                // GorillaPP_grammar.g:1:286: T__70
                {
                mT__70(); 


                }
                break;
            case 48 :
                // GorillaPP_grammar.g:1:292: T__71
                {
                mT__71(); 


                }
                break;
            case 49 :
                // GorillaPP_grammar.g:1:298: T__72
                {
                mT__72(); 


                }
                break;
            case 50 :
                // GorillaPP_grammar.g:1:304: T__73
                {
                mT__73(); 


                }
                break;
            case 51 :
                // GorillaPP_grammar.g:1:310: T__74
                {
                mT__74(); 


                }
                break;
            case 52 :
                // GorillaPP_grammar.g:1:316: T__75
                {
                mT__75(); 


                }
                break;
            case 53 :
                // GorillaPP_grammar.g:1:322: T__76
                {
                mT__76(); 


                }
                break;
            case 54 :
                // GorillaPP_grammar.g:1:328: T__77
                {
                mT__77(); 


                }
                break;
            case 55 :
                // GorillaPP_grammar.g:1:334: T__78
                {
                mT__78(); 


                }
                break;
            case 56 :
                // GorillaPP_grammar.g:1:340: T__79
                {
                mT__79(); 


                }
                break;
            case 57 :
                // GorillaPP_grammar.g:1:346: T__80
                {
                mT__80(); 


                }
                break;
            case 58 :
                // GorillaPP_grammar.g:1:352: SEMICOLON
                {
                mSEMICOLON(); 


                }
                break;
            case 59 :
                // GorillaPP_grammar.g:1:362: WS
                {
                mWS(); 


                }
                break;
            case 60 :
                // GorillaPP_grammar.g:1:365: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 61 :
                // GorillaPP_grammar.g:1:373: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;
            case 62 :
                // GorillaPP_grammar.g:1:386: INSTR_RETURN_TYPE
                {
                mINSTR_RETURN_TYPE(); 


                }
                break;
            case 63 :
                // GorillaPP_grammar.g:1:404: GORILLA_INSTR_NAME
                {
                mGORILLA_INSTR_NAME(); 


                }
                break;
            case 64 :
                // GorillaPP_grammar.g:1:423: ID
                {
                mID(); 


                }
                break;
            case 65 :
                // GorillaPP_grammar.g:1:426: CHARACTER_LITERAL
                {
                mCHARACTER_LITERAL(); 


                }
                break;
            case 66 :
                // GorillaPP_grammar.g:1:444: STRING_LITERAL
                {
                mSTRING_LITERAL(); 


                }
                break;
            case 67 :
                // GorillaPP_grammar.g:1:459: HEX_LITERAL
                {
                mHEX_LITERAL(); 


                }
                break;
            case 68 :
                // GorillaPP_grammar.g:1:471: BINARY_LITERAL
                {
                mBINARY_LITERAL(); 


                }
                break;
            case 69 :
                // GorillaPP_grammar.g:1:486: DECIMAL_LITERAL
                {
                mDECIMAL_LITERAL(); 


                }
                break;
            case 70 :
                // GorillaPP_grammar.g:1:502: OCTAL_LITERAL
                {
                mOCTAL_LITERAL(); 


                }
                break;

        }

    }


    protected DFA19 dfa19 = new DFA19(this);
    static final String DFA19_eotS =
        "\1\uffff\1\60\2\uffff\1\64\3\uffff\1\66\1\uffff\1\70\1\uffff\1\74"+
        "\1\uffff\1\77\1\101\1\104\1\uffff\4\52\3\uffff\12\52\1\uffff\1\134"+
        "\4\uffff\1\52\3\uffff\2\141\12\uffff\1\141\13\uffff\17\52\1\164"+
        "\6\52\2\uffff\1\52\2\uffff\1\141\3\uffff\1\141\17\52\1\uffff\1\u008c"+
        "\16\52\1\u009b\1\u009c\2\52\1\u009f\1\u00a0\1\u00a1\1\52\1\uffff"+
        "\1\52\1\u00a4\4\52\1\u00a9\1\52\1\u00ac\4\52\1\u00b1\2\uffff\2\52"+
        "\3\uffff\2\52\1\uffff\1\u00b6\3\52\1\uffff\1\u00a9\1\52\1\uffff"+
        "\1\52\1\u00bc\2\52\1\uffff\2\52\1\u00c1\1\52\1\uffff\4\52\1\u00c7"+
        "\1\uffff\3\52\1\u00cb\1\uffff\1\52\1\uffff\1\u00cd\2\52\1\uffff"+
        "\2\52\1\u00d2\1\uffff\1\52\1\uffff\1\u00d4\3\52\1\uffff\1\52\1\uffff"+
        "\2\52\1\u00db\2\52\1\u00de\1\uffff\2\52\1\uffff\1\u00e1\1\52\1\uffff"+
        "\1\52\1\u00e4\1\uffff";
    static final String DFA19_eofS =
        "\u00e5\uffff";
    static final String DFA19_minS =
        "\1\11\1\75\1\144\1\uffff\1\46\3\uffff\1\53\1\uffff\1\55\1\uffff"+
        "\1\52\1\uffff\1\74\2\75\1\uffff\1\117\1\116\1\106\1\111\3\uffff"+
        "\1\151\1\141\1\145\1\154\1\151\1\146\1\157\1\150\1\171\1\156\1\uffff"+
        "\1\174\4\uffff\1\123\3\uffff\2\60\12\uffff\1\114\13\uffff\1\116"+
        "\1\120\1\106\1\124\1\120\1\164\1\145\1\163\1\141\1\156\1\146\1\163"+
        "\1\151\1\165\1\156\1\60\1\163\1\156\1\157\1\162\1\160\1\163\2\uffff"+
        "\1\137\1\uffff\1\114\1\142\3\uffff\1\60\1\103\1\125\1\114\1\120"+
        "\1\105\1\137\1\141\1\145\1\162\1\164\1\141\1\145\1\164\1\155\1\151"+
        "\1\uffff\1\60\1\164\1\147\1\162\1\165\1\145\1\151\1\101\1\125\1"+
        "\124\1\117\1\125\1\114\1\166\1\153\2\60\1\151\1\165\3\60\1\163\1"+
        "\uffff\1\162\1\60\1\164\1\143\1\144\1\147\1\60\1\122\1\60\1\101"+
        "\1\124\1\111\1\145\1\60\2\uffff\1\156\1\154\3\uffff\1\150\1\137"+
        "\1\uffff\1\60\1\164\1\145\1\156\1\uffff\1\60\1\122\1\uffff\1\104"+
        "\1\60\1\116\1\143\1\uffff\1\165\1\164\1\60\1\141\1\uffff\1\40\1"+
        "\146\1\145\1\105\1\60\1\uffff\1\101\1\164\1\145\1\60\1\uffff\1\144"+
        "\1\uffff\1\60\1\144\1\116\1\uffff\1\102\1\157\1\60\1\uffff\1\144"+
        "\1\uffff\1\60\1\124\1\114\1\162\1\uffff\1\162\1\uffff\1\137\1\105"+
        "\1\60\1\137\1\123\1\60\1\uffff\1\164\1\101\1\uffff\1\60\1\106\1"+
        "\uffff\1\105\1\60\1\uffff";
    static final String DFA19_maxS =
        "\1\176\1\75\1\160\1\uffff\1\46\3\uffff\1\53\1\uffff\1\71\1\uffff"+
        "\1\57\1\uffff\2\75\1\76\1\uffff\1\117\1\116\1\125\1\111\3\uffff"+
        "\1\162\1\157\1\145\1\156\1\151\1\156\1\157\1\164\1\171\1\156\1\uffff"+
        "\1\174\4\uffff\1\123\3\uffff\1\170\1\165\12\uffff\1\165\13\uffff"+
        "\1\116\1\120\1\106\1\124\1\120\1\164\1\145\1\163\1\141\1\156\1\146"+
        "\1\163\1\151\1\165\1\156\1\172\1\164\1\156\1\157\1\162\1\160\1\163"+
        "\2\uffff\1\137\1\uffff\1\154\1\142\3\uffff\1\165\1\103\1\125\1\114"+
        "\1\120\1\105\1\137\1\141\1\145\1\162\1\164\1\141\1\145\1\164\1\155"+
        "\1\151\1\uffff\1\172\1\164\1\147\1\162\1\165\1\145\1\151\1\172\1"+
        "\125\1\124\1\117\1\125\1\114\1\166\1\153\2\172\1\151\1\165\3\172"+
        "\1\163\1\uffff\1\162\1\172\1\164\1\143\1\144\1\147\1\172\1\122\1"+
        "\172\1\101\1\124\1\111\1\145\1\172\2\uffff\1\156\1\154\3\uffff\1"+
        "\150\1\137\1\uffff\1\172\1\164\1\145\1\156\1\uffff\1\172\1\122\1"+
        "\uffff\1\104\1\172\1\116\1\143\1\uffff\1\165\1\164\1\172\1\141\1"+
        "\uffff\1\40\1\146\1\145\1\105\1\172\1\uffff\1\101\1\164\1\145\1"+
        "\172\1\uffff\1\144\1\uffff\1\172\1\144\1\116\1\uffff\1\102\1\157"+
        "\1\172\1\uffff\1\144\1\uffff\1\172\1\124\1\114\1\162\1\uffff\1\162"+
        "\1\uffff\1\137\1\105\1\172\1\137\1\123\1\172\1\uffff\1\164\1\101"+
        "\1\uffff\1\172\1\106\1\uffff\1\105\1\172\1\uffff";
    static final String DFA19_acceptS =
        "\3\uffff\1\5\1\uffff\1\10\1\11\1\12\1\uffff\1\15\1\uffff\1\20\1"+
        "\uffff\1\22\3\uffff\1\33\4\uffff\1\41\1\42\1\43\12\uffff\1\65\1"+
        "\uffff\1\70\1\71\1\72\1\73\1\uffff\1\100\1\101\1\102\2\uffff\1\2"+
        "\1\1\1\3\1\4\1\6\1\7\1\14\1\13\1\17\1\16\1\uffff\1\74\1\75\1\21"+
        "\1\24\1\25\1\23\1\27\1\26\1\31\1\32\1\30\26\uffff\1\67\1\66\1\uffff"+
        "\1\103\2\uffff\1\105\1\104\1\106\20\uffff\1\56\27\uffff\1\57\16"+
        "\uffff\1\46\1\47\2\uffff\1\52\1\53\1\54\2\uffff\1\60\4\uffff\1\77"+
        "\2\uffff\1\35\4\uffff\1\45\4\uffff\1\61\5\uffff\1\37\4\uffff\1\55"+
        "\1\uffff\1\62\3\uffff\1\36\3\uffff\1\51\1\uffff\1\63\4\uffff\1\50"+
        "\1\uffff\1\64\6\uffff\1\44\2\uffff\1\40\2\uffff\1\76\2\uffff\1\34";
    static final String DFA19_specialS =
        "\u00e5\uffff}>";
    static final String[] DFA19_transitionS = {
            "\2\50\1\uffff\2\50\22\uffff\1\50\1\1\1\54\1\2\1\uffff\1\3\1"+
            "\4\1\53\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\55\11\56\1\15"+
            "\1\47\1\16\1\17\1\20\1\21\1\uffff\2\52\1\22\3\52\1\51\1\52\1"+
            "\23\5\52\1\24\1\25\12\52\1\26\1\uffff\1\27\1\30\2\uffff\1\52"+
            "\1\31\1\32\1\33\1\34\1\35\2\52\1\36\2\52\1\37\6\52\1\40\1\41"+
            "\1\42\5\52\1\43\1\44\1\45\1\46",
            "\1\57",
            "\1\61\13\uffff\1\62",
            "",
            "\1\63",
            "",
            "",
            "",
            "\1\65",
            "",
            "\1\67\2\uffff\1\71\11\56",
            "",
            "\1\72\4\uffff\1\73",
            "",
            "\1\75\1\76",
            "\1\100",
            "\1\102\1\103",
            "",
            "\1\105",
            "\1\106",
            "\1\107\16\uffff\1\110",
            "\1\111",
            "",
            "",
            "",
            "\1\112\10\uffff\1\113",
            "\1\114\6\uffff\1\115\6\uffff\1\116",
            "\1\117",
            "\1\120\1\121\1\122",
            "\1\123",
            "\1\124\7\uffff\1\125",
            "\1\126",
            "\1\127\13\uffff\1\130",
            "\1\131",
            "\1\132",
            "",
            "\1\133",
            "",
            "",
            "",
            "",
            "\1\135",
            "",
            "",
            "",
            "\10\143\24\uffff\1\140\10\uffff\1\137\2\uffff\1\136\11\uffff"+
            "\1\142\11\uffff\1\140\10\uffff\1\137\2\uffff\1\136",
            "\12\144\22\uffff\1\140\10\uffff\1\137\14\uffff\1\142\11\uffff"+
            "\1\140\10\uffff\1\137",
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
            "\1\140\10\uffff\1\137\14\uffff\1\142\11\uffff\1\140\10\uffff"+
            "\1\137",
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
            "",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\166\1\165",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173",
            "",
            "",
            "\1\174",
            "",
            "\1\140\37\uffff\1\140",
            "\1\142",
            "",
            "",
            "",
            "\12\144\22\uffff\1\140\10\uffff\1\137\14\uffff\1\142\11\uffff"+
            "\1\140\10\uffff\1\137",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\32\u0093\6\uffff\32\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u009d",
            "\1\u009e",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u00a2",
            "",
            "\1\u00a3",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\12\u00aa\7\uffff\32\u00aa\4\uffff\1\u00aa\1\uffff\32\u00aa",
            "\1\u00ab",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "",
            "",
            "\1\u00b2",
            "\1\u00b3",
            "",
            "",
            "",
            "\1\u00b4",
            "\1\u00b5",
            "",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "",
            "\12\u00aa\7\uffff\32\u00aa\4\uffff\1\u00aa\1\uffff\32\u00aa",
            "\1\u00ba",
            "",
            "\1\u00bb",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u00bd",
            "\1\u00be",
            "",
            "\1\u00bf",
            "\1\u00c0",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u00c2",
            "",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "",
            "\1\u00c8",
            "\1\u00c9",
            "\1\u00ca",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "",
            "\1\u00cc",
            "",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u00ce",
            "\1\u00cf",
            "",
            "\1\u00d0",
            "\1\u00d1",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "",
            "\1\u00d3",
            "",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "",
            "\1\u00d8",
            "",
            "\1\u00d9",
            "\1\u00da",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u00dc",
            "\1\u00dd",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "",
            "\1\u00df",
            "\1\u00e0",
            "",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            "\1\u00e2",
            "",
            "\1\u00e3",
            "\12\52\7\uffff\32\52\4\uffff\1\52\1\uffff\32\52",
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | SEMICOLON | WS | COMMENT | LINE_COMMENT | INSTR_RETURN_TYPE | GORILLA_INSTR_NAME | ID | CHARACTER_LITERAL | STRING_LITERAL | HEX_LITERAL | BINARY_LITERAL | DECIMAL_LITERAL | OCTAL_LITERAL );";
        }
    }
 

}
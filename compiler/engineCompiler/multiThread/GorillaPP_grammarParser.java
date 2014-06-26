// $ANTLR 3.4 GorillaPP_grammar.g 2014-06-25 19:00:01

  import java.util.Set;
  import java.util.HashSet;
  import java.util.Iterator;
  import java.io.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class GorillaPP_grammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BINARY_LITERAL", "BinaryDigit", "CHARACTER_LITERAL", "COMMENT", "DECIMAL_LITERAL", "DIGIT", "EscapeSequence", "GORILLA_INSTR_NAME", "HEX_LITERAL", "HexDigit", "ID", "INSTR_RETURN_TYPE", "IntegerTypeSuffix", "LETTER", "LINE_COMMENT", "OCTAL_LITERAL", "OctalEscape", "SEMICOLON", "STRING_LITERAL", "WS", "'!'", "'!='", "'#define'", "'#pragma'", "'%'", "'&&'", "'&'", "'('", "')'", "'*'", "'+'", "'++'", "','", "'-'", "'--'", "'.'", "'/'", "':'", "'<'", "'<<'", "'<='", "'='", "'=='", "'>'", "'>='", "'>>'", "'?'", "'CONCURRENT_SAFE'", "'INPUT'", "'OFFLOAD'", "'OUTPUT'", "'PIPELINABLE'", "'['", "']'", "'^'", "'bit_vector'", "'break'", "'case'", "'char'", "'continue'", "'default'", "'else'", "'emit'", "'enum'", "'finish'", "'if'", "'int'", "'long'", "'short'", "'struct {'", "'typedef'", "'unsigned'", "'{'", "'|'", "'||'", "'}'", "'~'"
    };

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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators

    protected static class Symbols_scope {
        Set <String> types;
        Set <String> constantMacros;
        List <String> gorillaInstrs;
        Map <String, String> instrOfOffload;
        Set <String> globals;
        Set <String> currentGlobals;
        Set <String> locals;
        Set <String> jumpTargets;
        Set <String> offloadPorts;
        Map <String, String> offloadPortsReqType;
        Map <String, String> offloadPortsRepType;
        Map <String, String> localsType;
        Map <String, String> typedefs;
        Set <String> externalMacros;
        Map <String, Integer> localsVersion;
        Map <String, String> OffValidString;
        Map <String, String> OffReqString;
        String inputType;
        String outputType;
        String typeName;
        String offloadPortsString;
        String ioString;
        String definitionString;
        String typeDefinitionString;
        String macroString;
        String globalMacroString;
        String globalRequestBuilderString;
        String requestBuilderString;
        String globalContextEditString;
        String contextEditString;
        String combinationalString;
        String instrString;
        String offString;
        String gorillaInstrName;
        OUT_STREAM outStream;
        boolean isPreOff;
        boolean isPostOff;
        boolean localLvalue;
        boolean colonAssign;
        String offId;
        boolean isLocal;
        int instrNumber;
        Stack <String> condStack;
        boolean isTypeDef;
        boolean isHeaderFile;
        boolean isMacro;
        boolean globalInitialized;
        boolean firstOffload;
        boolean isMacroWithArgument;
        boolean isCasted;
        String macroId;
        boolean isPredicate;
        String predicateString;
    }
    protected Stack Symbols_stack = new Stack();



    public GorillaPP_grammarParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public GorillaPP_grammarParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[196+1];
         

    }

    public String[] getTokenNames() { return GorillaPP_grammarParser.tokenNames; }
    public String getGrammarFileName() { return "GorillaPP_grammar.g"; }


      public enum OUT_STREAM { REQUEST_BUILDER, CONTEXT_EDIT, JUMP};
      String c2ChiselType(String type) {
        String s;
        if (((Symbols_scope)Symbols_stack.peek()).typedefs.containsKey(type)) {
              s = (String) ((Symbols_scope)Symbols_stack.peek()).typedefs.get(type);
        } else {
          s = type;
        }
      
        if (s.equals("bit")) { 
          return("UFix(width=1)");
        }
        if (s.equals("bool")) { 
          return("Bool()");
        }
        if (s.equals("int")) { 
          return ("UFix(width = 32)");
        }
        if (s.equals("uint1_t")) { 
          return ("UFix(width = 1)");
        }
        if (s.equals("uint2_t")) { 
          return ("UFix(width = 2)");
        }
        if (s.equals("uint4_t")) { 
          return ("UFix(width = 4)");
        }
        if (s.equals("uint8_t")) { 
          return ("UFix(width = 8)");
        }
        if (s.equals("uint16_t")) { 
          return ("UFix(width = 16)");
        }
        if (s.equals("uint32_t")) { 
          return ("UFix(width = 32)");
        }
        if (s.equals("uint64_t")) { 
          return ("UFix(width = 64)");
        }
        if (s.equals("uint48_t")) { 
          return ("UFix(width = 48)");
        }
        if (s.equals("uint64_t")) { 
          return ("UFix(width = 64)");
        }
        if (s.equals("uint128_t")) { 
          return ("UFix(width = 128)");
        }
        if (s.equals("uint192_t")) { 
          return ("UFix(width = 192)");
        }
      
        return ("new " + s);
      }
      
      String Ctype2ChiselInit(String type, String init) {
        String s;
        if (((Symbols_scope)Symbols_stack.peek()).typedefs.containsKey(type)) {
              s = (String) ((Symbols_scope)Symbols_stack.peek()).typedefs.get(type);
        } else {
          s = type;
        }
      
        if (s.equals("bit")) { 
          return ("UFix(" + init + ", width = 1)");
        }
      
        if (s.equals("bool")) { 
          return ("Bool(" + init + ")");
        }
      
        if (s.equals("int")) { 
          return ("UFix(" + init + ", width = 32)");
        }
        if (s.equals("uint8_t")) { 
          return ("UFix(" + init + ", width = 8)");
        }
        if (s.equals("uint16_t")) { 
          return ("UFix(" + init + ", width = 16)");
        }
        if (s.equals("uint32_t")) { 
          return ("UFix(" + init + ", width = 32)");
        }
        if (s.equals("uint64_t")) { 
          return ("UFix(" + init + ", width = 64)");
        }
        if (s.equals("uint128_t")) { 
          return ("UFix(" + init + ", width = 128)");
        }
        if (s.equals("uint48_t")) { 
          return ("UFix(" + init + ", width = 48)");
        }
        if (s.equals("uint192_t")) { 
          return ("UFix(" + init + ", width = 192)");
        }
        return ("");
      }
      
      String versioned(String s) {
        if (((Symbols_scope)Symbols_stack.peek()).localsVersion.containsKey(s) && 
         ((Integer) ((Symbols_scope)Symbols_stack.peek()).localsVersion.get(s)) != 0) { 
          return (s + "_" + ((Symbols_scope)Symbols_stack.peek()).localsVersion.get(s).toString());
        }
        return (s);
      }
      
      void outString(String s) {
        if (((Symbols_scope)Symbols_stack.peek()).isMacro) {
          ((Symbols_scope)Symbols_stack.peek()).macroString += s;
        } else if (((Symbols_scope)Symbols_stack.peek()).localLvalue) { 
          ((Symbols_scope)Symbols_stack.peek()).combinationalString += s;
        } else if (((Symbols_scope)Symbols_stack.peek()).isPreOff) {
          ((Symbols_scope)Symbols_stack.peek()).contextEditString += s;
          //((Symbols_scope)Symbols_stack.peek()).requestBuilderString += s;
        } else if (((Symbols_scope)Symbols_stack.peek()).isPostOff) {
          ((Symbols_scope)Symbols_stack.peek()).contextEditString += s;	
        } else {  
          ((Symbols_scope)Symbols_stack.peek()).offString +=s ; 
        }
      }
      
      int hex2decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          int d = digits.indexOf(c);
          val = 16*val + d;
        }
        return val;
      }
      
      boolean isTypeName(String name) {
        for (int i = Symbols_stack.size()-1; i>=0; i--) {
           Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
           if ( scope.types.contains(name) ) {
             return true;
           }
         }
         return false;
      }

      boolean gorillaInstrDefined(String name) {
        for (int i = Symbols_stack.size()-1; i>=0; i--) {
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          if ( scope.gorillaInstrs.contains(name) ) {
            return true;
          }
        }
        return false;
      }

      boolean globalVarDefined(String name) {
        for (int i = Symbols_stack.size()-1; i>=0; i--) {
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          if ( scope.globals.contains(name) ) {
            return true;
          }
        }
        return false;
      }
      
      boolean localVarDefined(String name) {
        for (int i = Symbols_stack.size()-1; i>=0; i--) {
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          if ( scope.locals.contains(name) ) {
            return true;
          }
        }
        return false;
      }
      
      boolean offPortDefined(String name) {
        for (int i = Symbols_stack.size()-1; i>=0; i--) {
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          if ( scope.offloadPorts.contains(name) ) {
            return true;
          }
        }
        return false;
      }
      
      //Is this a single stage instruciton -- no offload instruction
      boolean isSS(String instr) {     
        return (!((Symbols_scope)Symbols_stack.peek()).instrOfOffload.containsValue(instr));
      }
      
      void readInstrsMagillaC() {
        BufferedReader br;
        try {
          br = new BufferedReader(new FileReader("instrFp.magillac"));
          String line;
          while ((line = br.readLine()) != null) {
            String [] splitted = line.split("\\s+");
            if (splitted.length == 0) {
              System.out.println("Gorilla++Error: wrong instr.magillac format");
            } else {
              for (int i=2; i < splitted.length; i++) {
                if (!splitted[i].equals("hasNoOffload"))
                  ((Symbols_scope)Symbols_stack.peek()).instrOfOffload.put(splitted[i], splitted[0]);
              } 
            }        
          }
          br.close(); 
        } catch(IOException e) {
        }
      }
      
      String preOffName(String vName, String instrName) {
        return (instrName + "__preOff__" + vName);
      }
     
      String postOffName(String vName, String instrName) {
        return (instrName + "__postOff__" + vName);
      }

      public void generateCode() {
        System.out.println("//This Chisel code is generated" + 
         "Automatically using GorillaC compiler");
        //System.out.println("//List of Gorilla processing steps:");
        for (int i = Symbols_stack.size()-1; i>=0; i--) {
          //note that we have just one scope so we can remove this loop
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          Iterator instr_it = scope.gorillaInstrs.iterator();
          while (instr_it.hasNext()) {
            //System.out.println("//"+instr_it.next());
            ((Symbols_scope)Symbols_stack.peek()).instrString += instr_it.next() + "\n";
          } 
        }
        System.out.println("//List of offload interfaces:");
        for (int i = Symbols_stack.size()-1; i>=0; i--) {
        //note that we have just one scope so we can remove this loop
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          Iterator fu_it = scope.offloadPorts.iterator();
          while (fu_it.hasNext()) {
            System.out.println("//"+fu_it.next());
          }
        }
        System.out.println("//List of Global variables:");
        for (int i = Symbols_stack.size()-1; i>=0; i--) {
          //note that we have just one scope so we can remove this loop
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          Iterator global_it = scope.globals.iterator();
          while (global_it.hasNext()) {
          System.out.println("//"+global_it.next());
          }
        }
        Symbols_scope scope = (Symbols_scope)Symbols_stack.get(0);
        //System.out.println("//list of offload interfaces");
        //if (scope.ioString != null) System.out.print(scope.ioString);
        System.out.println("//definitions list");
        if (scope.definitionString != null) 
          System.out.print(scope.definitionString);
        //System.out.println("//Wait for request accept");
        //if (scope.requestBuilderString != null) 
        //  System.out.print(scope.globalRequestBuilderString);
        System.out.println("//context edit");
        if (scope.globalContextEditString != null) 
          System.out.print(scope.globalContextEditString);
        System.out.println("//Combinational logic");
        if (scope.combinationalString != null) 
          System.out.print(scope.combinationalString);
      
        try {
          BufferedWriter out = 
           new BufferedWriter(new FileWriter("definition.magillac"));
          out.write(scope.definitionString);
          out.close();
        } catch (IOException e) {
        }
        try {
          BufferedWriter out = 
           new BufferedWriter(new FileWriter(
            "requestBuilder.magillac"));
          out.write(scope.globalRequestBuilderString);
          out.close();
        } catch (IOException e) {
        }
        try {
          BufferedWriter out = 
           new BufferedWriter(new FileWriter("contextEdit.magillac"));
          out.write(scope.globalContextEditString);
          out.close();
        } catch (IOException e) {
        }
        try {
          BufferedWriter out = 
           new BufferedWriter(new FileWriter("combinational.magillac"));
          out.write(scope.combinationalString);
          out.close();
        } catch (IOException e) {
        }
        try {
          BufferedWriter out = 
           new BufferedWriter(new FileWriter("instr.magillac"));
          out.write(scope.instrString);
          out.close();
        } catch (IOException e) {
        }
        try {
          BufferedWriter out = new BufferedWriter(new FileWriter("io.magillac"));
          out.write(scope.ioString);
          out.close();
        } catch (IOException e) {
        }
        try {
          BufferedWriter out = 
           new BufferedWriter(new FileWriter("offloadPorts.magillac"));
          out.write(scope.offloadPortsString);
          out.close();
        } catch (IOException e) {
        }
        try {
          BufferedWriter out = 
           new BufferedWriter(new FileWriter("macroDefintions.magillac"));
          out.write(scope.globalMacroString);
          out.close();
        } catch (IOException e) {
        }
      }



    // $ANTLR start "translation_unit"
    // GorillaPP_grammar.g:438:1: translation_unit : external_declaration ;
    public final void translation_unit() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());

        int translation_unit_StartIndex = input.index();


          ((Symbols_scope)Symbols_stack.peek()).constantMacros = new HashSet <String> ();
          ((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String> ();
          ((Symbols_scope)Symbols_stack.peek()).gorillaInstrs = new ArrayList <String> ();
          ((Symbols_scope)Symbols_stack.peek()).instrOfOffload = new HashMap<String, String> ();
          ((Symbols_scope)Symbols_stack.peek()).globals = new HashSet <String> ();
          ((Symbols_scope)Symbols_stack.peek()).currentGlobals = new HashSet <String> ();
          ((Symbols_scope)Symbols_stack.peek()).locals = new HashSet <String> ();
          ((Symbols_scope)Symbols_stack.peek()).offloadPorts = new <String> HashSet();
          ((Symbols_scope)Symbols_stack.peek()).offloadPortsReqType = new HashMap<String, String> ();
          ((Symbols_scope)Symbols_stack.peek()).offloadPortsRepType = new HashMap<String, String> ();
          ((Symbols_scope)Symbols_stack.peek()).localsVersion = new HashMap<String, Integer> ();
          ((Symbols_scope)Symbols_stack.peek()).localsType = new HashMap<String, String> ();
          ((Symbols_scope)Symbols_stack.peek()).typedefs = new HashMap<String, String> ();
          ((Symbols_scope)Symbols_stack.peek()).externalMacros = new HashSet<String> ();
          ((Symbols_scope)Symbols_stack.peek()).OffValidString = new HashMap<String, String> ();
          ((Symbols_scope)Symbols_stack.peek()).OffReqString = new HashMap<String, String> ();
          ((Symbols_scope)Symbols_stack.peek()).requestBuilderString = "";
          ((Symbols_scope)Symbols_stack.peek()).globalRequestBuilderString = "";
          ((Symbols_scope)Symbols_stack.peek()).contextEditString = ""; 
          ((Symbols_scope)Symbols_stack.peek()).globalContextEditString = ""; 
          ((Symbols_scope)Symbols_stack.peek()).ioString = "";				
          ((Symbols_scope)Symbols_stack.peek()).offloadPortsString = "";				 
          ((Symbols_scope)Symbols_stack.peek()).macroString = "";			
          ((Symbols_scope)Symbols_stack.peek()).globalMacroString = "";			
          ((Symbols_scope)Symbols_stack.peek()).typeDefinitionString = "";			
          ((Symbols_scope)Symbols_stack.peek()).definitionString = "";			
          ((Symbols_scope)Symbols_stack.peek()).instrString = "";				 
          ((Symbols_scope)Symbols_stack.peek()).combinationalString = "";
          ((Symbols_scope)Symbols_stack.peek()).isPreOff = true;
          ((Symbols_scope)Symbols_stack.peek()).isPostOff = false;
          ((Symbols_scope)Symbols_stack.peek()).isLocal = false;
          ((Symbols_scope)Symbols_stack.peek()).isTypeDef = false;
          ((Symbols_scope)Symbols_stack.peek()).isHeaderFile = false;
          ((Symbols_scope)Symbols_stack.peek()).localLvalue = false;
          ((Symbols_scope)Symbols_stack.peek()).colonAssign = true;
          ((Symbols_scope)Symbols_stack.peek()).isMacro = false;
          ((Symbols_scope)Symbols_stack.peek()).instrNumber = 1;
          ((Symbols_scope)Symbols_stack.peek()).condStack = new Stack <String> ();
          ((Symbols_scope)Symbols_stack.peek()).predicateString = "";
          ((Symbols_scope)Symbols_stack.peek()).firstOffload = true;
          ((Symbols_scope)Symbols_stack.peek()).isPredicate = false;
          try {
            BufferedReader in = 
             new BufferedReader(new FileReader("typedef.magillac"));
            String line = in.readLine();
            while (line != null) {
              String types[] = line.split(",");
              if (types.length != 2) {
                System.out.println("typedef.magillac is not" + 
                 "obeying the correct comma sperated format\n");
              }
              ((Symbols_scope)Symbols_stack.peek()).typedefs.put(types[1], types[0]);
              line = in.readLine();
            }
            in.close();
            
            BufferedReader in1 = new BufferedReader(
             new FileReader("macros.magillac"));
            String line1 = in1.readLine();
            while (line1 != null) {
              ((Symbols_scope)Symbols_stack.peek()).externalMacros.add(line1);
              line1 = in1.readLine();
            }
            in1.close();
          } catch (IOException e) {
          }
          readInstrsMagillaC();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return ; }

            // GorillaPP_grammar.g:509:2: ( external_declaration )
            // GorillaPP_grammar.g:509:5: external_declaration
            {
            pushFollow(FOLLOW_external_declaration_in_translation_unit74);
            external_declaration();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {generateCode();}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 1, translation_unit_StartIndex); }

            Symbols_stack.pop();

        }
        return ;
    }
    // $ANTLR end "translation_unit"



    // $ANTLR start "external_declaration"
    // GorillaPP_grammar.g:513:1: external_declaration : ( ( ( type_definition | define )* ) | ( in_pragma out_pragma ( off_pragma )* ( other_pragma )* ( global_declaration )* ( magilla_instr )+ ) );
    public final void external_declaration() throws RecognitionException {
        int external_declaration_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return ; }

            // GorillaPP_grammar.g:514:2: ( ( ( type_definition | define )* ) | ( in_pragma out_pragma ( off_pragma )* ( other_pragma )* ( global_declaration )* ( magilla_instr )+ ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==EOF||LA6_0==26||LA6_0==74) ) {
                alt6=1;
            }
            else if ( (LA6_0==27) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // GorillaPP_grammar.g:514:5: ( ( type_definition | define )* )
                    {
                    // GorillaPP_grammar.g:514:5: ( ( type_definition | define )* )
                    // GorillaPP_grammar.g:514:6: ( type_definition | define )*
                    {
                    if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).isHeaderFile = true;}

                    // GorillaPP_grammar.g:515:8: ( type_definition | define )*
                    loop1:
                    do {
                        int alt1=3;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==74) ) {
                            alt1=1;
                        }
                        else if ( (LA1_0==26) ) {
                            alt1=2;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // GorillaPP_grammar.g:515:9: type_definition
                    	    {
                    	    pushFollow(FOLLOW_type_definition_in_external_declaration100);
                    	    type_definition();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;
                    	case 2 :
                    	    // GorillaPP_grammar.g:515:25: define
                    	    {
                    	    pushFollow(FOLLOW_define_in_external_declaration102);
                    	    define();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }


                    if ( state.backtracking==0 ) {
                             ((Symbols_scope)Symbols_stack.peek()).isHeaderFile = true; 
                             ((Symbols_scope)Symbols_stack.peek()).definitionString = 
                             ((Symbols_scope)Symbols_stack.peek()).typeDefinitionString;}

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:519:8: ( in_pragma out_pragma ( off_pragma )* ( other_pragma )* ( global_declaration )* ( magilla_instr )+ )
                    {
                    // GorillaPP_grammar.g:519:8: ( in_pragma out_pragma ( off_pragma )* ( other_pragma )* ( global_declaration )* ( magilla_instr )+ )
                    // GorillaPP_grammar.g:519:9: in_pragma out_pragma ( off_pragma )* ( other_pragma )* ( global_declaration )* ( magilla_instr )+
                    {
                    pushFollow(FOLLOW_in_pragma_in_external_declaration119);
                    in_pragma();

                    state._fsp--;
                    if (state.failed) return ;

                    pushFollow(FOLLOW_out_pragma_in_external_declaration121);
                    out_pragma();

                    state._fsp--;
                    if (state.failed) return ;

                    // GorillaPP_grammar.g:519:30: ( off_pragma )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==27) ) {
                            int LA2_1 = input.LA(2);

                            if ( (LA2_1==53) ) {
                                alt2=1;
                            }


                        }


                        switch (alt2) {
                    	case 1 :
                    	    // GorillaPP_grammar.g:519:30: off_pragma
                    	    {
                    	    pushFollow(FOLLOW_off_pragma_in_external_declaration123);
                    	    off_pragma();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    // GorillaPP_grammar.g:519:42: ( other_pragma )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==27) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // GorillaPP_grammar.g:519:42: other_pragma
                    	    {
                    	    pushFollow(FOLLOW_other_pragma_in_external_declaration126);
                    	    other_pragma();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {
                             ((Symbols_scope)Symbols_stack.peek()).ioString += "))";
                             Iterator off_it = 
                              ((Symbols_scope)Symbols_stack.peek()).offloadPorts.iterator();
                      	     while (off_it.hasNext()) {
                               String offPort = (String) off_it.next();
                               ((Symbols_scope)Symbols_stack.peek()).OffValidString.put(offPort, "");
                               ((Symbols_scope)Symbols_stack.peek()).OffReqString.put(offPort, "");
                             } 
                           }

                    // GorillaPP_grammar.g:528:10: ( global_declaration )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==ID||LA4_0==26||LA4_0==59||LA4_0==62||LA4_0==67||(LA4_0 >= 70 && LA4_0 <= 72)||LA4_0==75) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // GorillaPP_grammar.g:528:10: global_declaration
                    	    {
                    	    pushFollow(FOLLOW_global_declaration_in_external_declaration131);
                    	    global_declaration();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {
                    	     ((Symbols_scope)Symbols_stack.peek()).definitionString += 
                              "val inputReg = Vec(numOfThreads) {Reg(" + 
                              c2ChiselType(((Symbols_scope)Symbols_stack.peek()).inputType) +  ")}\n";
                    	     ((Symbols_scope)Symbols_stack.peek()).definitionString += 
                              "val outputReg = Vec(numOfThreads) {Reg(" + 
                              c2ChiselType(((Symbols_scope)Symbols_stack.peek()).outputType) +  ")}\n";
                           	 Iterator off_it = 
                              ((Symbols_scope)Symbols_stack.peek()).offloadPorts.iterator();
                             while (off_it.hasNext()) {
                               String offPort = (String) off_it.next();
                               ((Symbols_scope)Symbols_stack.peek()).definitionString += 
                                "def mymyOff" + offPort+ 
                                " = myOff.asInstanceOf[Bundle]." + 
                                "elements.find" + 
                                "(_._1 == \"" + offPort + 
                                "\").getOrElse(elseV)._2\n"; 
                    	       ((Symbols_scope)Symbols_stack.peek()).definitionString += 
                                "val " + offPort + 
                                "Port = new gOffBundleND(() => " + 
                                ((Symbols_scope)Symbols_stack.peek()).offloadPortsReqType.get(offPort) + 
                                ", () => " + ((Symbols_scope)Symbols_stack.peek()).offloadPortsRepType.
                                 get(offPort) + ")\n";
                    	       ((Symbols_scope)Symbols_stack.peek()).definitionString += 
                                offPort + "Port <>" + " mymyOff" + offPort + 
                                "\n";
                    	      } 
                    	    }

                    // GorillaPP_grammar.g:555:8: ( magilla_instr )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==GORILLA_INSTR_NAME) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // GorillaPP_grammar.g:555:8: magilla_instr
                    	    {
                    	    pushFollow(FOLLOW_magilla_instr_in_external_declaration136);
                    	    magilla_instr();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt5 >= 1 ) break loop5;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);


                    if ( state.backtracking==0 ) {
                                  Iterator off_it = 
                                  ((Symbols_scope)Symbols_stack.peek()).offloadPorts.iterator();
                                  while (off_it.hasNext()) {
                                   String offPort = (String) off_it.next();
                                   String OffValidString = (String) 
                                    ((Symbols_scope)Symbols_stack.peek()).OffValidString.get(offPort);
                                   String OffReqString = 
                                    (String) ((Symbols_scope)Symbols_stack.peek()).OffReqString.
                                     get(offPort);
                                   ((Symbols_scope)Symbols_stack.peek()).combinationalString += 
                                    offPort + "Port.req.tag :=  rThread\n";  
                                   ((Symbols_scope)Symbols_stack.peek()).combinationalString += offPort + 
                                    "Port.req.valid :=  " + 
                                    "(rThread != NONE_SELECTED) && !" + 
                                    offPort + "_valid_received(rThread) && (" + 
                                    OffValidString + ")\n"; 
                                   ((Symbols_scope)Symbols_stack.peek()).combinationalString += offPort + 
                                    "Port.req.bits := MuxCase(UFix(0, 32)," + 
                                    "Seq(" + OffReqString + "))" + "\n";
                                  }
                     	    }

                    }


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
            if ( state.backtracking>0 ) { memoize(input, 2, external_declaration_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "external_declaration"



    // $ANTLR start "global_declaration"
    // GorillaPP_grammar.g:578:1: global_declaration : ( define | global_variable_definition );
    public final void global_declaration() throws RecognitionException {
        int global_declaration_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return ; }

            // GorillaPP_grammar.g:579:2: ( define | global_variable_definition )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==26) ) {
                alt7=1;
            }
            else if ( (LA7_0==ID||LA7_0==59||LA7_0==62||LA7_0==67||(LA7_0 >= 70 && LA7_0 <= 72)||LA7_0==75) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // GorillaPP_grammar.g:579:4: define
                    {
                    pushFollow(FOLLOW_define_in_global_declaration149);
                    define();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:579:13: global_variable_definition
                    {
                    pushFollow(FOLLOW_global_variable_definition_in_global_declaration153);
                    global_variable_definition();

                    state._fsp--;
                    if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 3, global_declaration_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "global_declaration"



    // $ANTLR start "magilla_instr"
    // GorillaPP_grammar.g:583:1: magilla_instr : GORILLA_INSTR_NAME ( '(' ')' ) '{' instr_body '}' ;
    public final void magilla_instr() throws RecognitionException {
        int magilla_instr_StartIndex = input.index();

        Token GORILLA_INSTR_NAME1=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return ; }

            // GorillaPP_grammar.g:584:2: ( GORILLA_INSTR_NAME ( '(' ')' ) '{' instr_body '}' )
            // GorillaPP_grammar.g:584:4: GORILLA_INSTR_NAME ( '(' ')' ) '{' instr_body '}'
            {
            GORILLA_INSTR_NAME1=(Token)match(input,GORILLA_INSTR_NAME,FOLLOW_GORILLA_INSTR_NAME_in_magilla_instr175); if (state.failed) return ;

            if ( state.backtracking==0 ) {	
                        if(gorillaInstrDefined((GORILLA_INSTR_NAME1!=null?GORILLA_INSTR_NAME1.getText():null))) {
            	      System.out.println(
                           "Redundant magilla instruction" + 
                           " detected");
            	    } else {
                          ((Symbols_scope)Symbols_stack.peek()).gorillaInstrs.add(
                          (GORILLA_INSTR_NAME1!=null?GORILLA_INSTR_NAME1.getText():null));
            	    }
                        ((Symbols_scope)Symbols_stack.peek()).definitionString += "val " + 
                        (GORILLA_INSTR_NAME1!=null?GORILLA_INSTR_NAME1.getText():null) + 
                         " = " + "UFix(" + ((Symbols_scope)Symbols_stack.peek()).instrNumber + 
                         ")\n";
                        ((Symbols_scope)Symbols_stack.peek()).instrNumber++;
                        ((Symbols_scope)Symbols_stack.peek()).gorillaInstrName = 
                         (GORILLA_INSTR_NAME1!=null?GORILLA_INSTR_NAME1.getText():null);
                        ((Symbols_scope)Symbols_stack.peek()).isPreOff = true;
                        ((Symbols_scope)Symbols_stack.peek()).isPostOff = false;
            	    ((Symbols_scope)Symbols_stack.peek()).isLocal = true;
                        ((Symbols_scope)Symbols_stack.peek()).requestBuilderString = ""; 
                        ((Symbols_scope)Symbols_stack.peek()).contextEditString = ""; 
                      }

            // GorillaPP_grammar.g:605:13: ( '(' ')' )
            // GorillaPP_grammar.g:605:14: '(' ')'
            {
            match(input,31,FOLLOW_31_in_magilla_instr180); if (state.failed) return ;

            match(input,32,FOLLOW_32_in_magilla_instr181); if (state.failed) return ;

            }


            match(input,76,FOLLOW_76_in_magilla_instr184); if (state.failed) return ;

            pushFollow(FOLLOW_instr_body_in_magilla_instr186);
            instr_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,79,FOLLOW_79_in_magilla_instr187); if (state.failed) return ;

            if ( state.backtracking==0 ) {	
                        ((Symbols_scope)Symbols_stack.peek()).globalRequestBuilderString += 
                         "\n when (rThread != NONE_SELECTED &&" + 
                         " State(rThread) === " + 
                         (GORILLA_INSTR_NAME1!=null?GORILLA_INSTR_NAME1.getText():null) ;	
                        ((Symbols_scope)Symbols_stack.peek()).globalContextEditString += 
                         "\n when (vThread != NONE_SELECTED &&" +  
                         "State(vThread) === " + 
                         (GORILLA_INSTR_NAME1!=null?GORILLA_INSTR_NAME1.getText():null);	
                        ((Symbols_scope)Symbols_stack.peek()).globalRequestBuilderString +=  
                         ")" + "{\n";	
                        ((Symbols_scope)Symbols_stack.peek()).globalContextEditString +=  
                         ")" +  "{\n";	
                        ((Symbols_scope)Symbols_stack.peek()).globalRequestBuilderString += 
                        ((Symbols_scope)Symbols_stack.peek()).requestBuilderString;	
                        ((Symbols_scope)Symbols_stack.peek()).globalContextEditString += 
                        ((Symbols_scope)Symbols_stack.peek()).contextEditString;	
                        ((Symbols_scope)Symbols_stack.peek()).globalRequestBuilderString += 
                         "} \n";	
            	    ((Symbols_scope)Symbols_stack.peek()).globalContextEditString += 
                         "} \n";	
            	    ((Symbols_scope)Symbols_stack.peek()).locals.clear();
                        ((Symbols_scope)Symbols_stack.peek()).localsVersion.clear();
            	  ((Symbols_scope)Symbols_stack.peek()).localsType.clear();
            	}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 4, magilla_instr_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "magilla_instr"


    protected static class instr_body_scope {
        int num_of_FU_reqs;
    }
    protected Stack instr_body_stack = new Stack();



    // $ANTLR start "instr_body"
    // GorillaPP_grammar.g:631:1: instr_body : ( local_variable_definition )* ( statement )* ( offload_statement )* ( statement )* ( finish_statement )? ;
    public final void instr_body() throws RecognitionException {
        instr_body_stack.push(new instr_body_scope());
        int instr_body_StartIndex = input.index();


          ((instr_body_scope)instr_body_stack.peek()).num_of_FU_reqs = 0;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return ; }

            // GorillaPP_grammar.g:637:2: ( ( local_variable_definition )* ( statement )* ( offload_statement )* ( statement )* ( finish_statement )? )
            // GorillaPP_grammar.g:637:5: ( local_variable_definition )* ( statement )* ( offload_statement )* ( statement )* ( finish_statement )?
            {
            // GorillaPP_grammar.g:637:5: ( local_variable_definition )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ID) ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1==ID||LA8_1==56) ) {
                        alt8=1;
                    }


                }
                else if ( (LA8_0==62||LA8_0==67||(LA8_0 >= 70 && LA8_0 <= 72)||LA8_0==75) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // GorillaPP_grammar.g:637:5: local_variable_definition
            	    {
            	    pushFollow(FOLLOW_local_variable_definition_in_instr_body208);
            	    local_variable_definition();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            // GorillaPP_grammar.g:637:32: ( statement )*
            loop9:
            do {
                int alt9=2;
                switch ( input.LA(1) ) {
                case ID:
                    {
                    switch ( input.LA(2) ) {
                    case 45:
                        {
                        int LA9_12 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case 41:
                        {
                        int LA9_13 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case 39:
                        {
                        int LA9_14 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;

                    }

                    }
                    break;
                case 61:
                    {
                    switch ( input.LA(2) ) {
                    case 31:
                        {
                        int LA9_15 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case ID:
                        {
                        int LA9_16 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case HEX_LITERAL:
                        {
                        int LA9_17 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case OCTAL_LITERAL:
                        {
                        int LA9_18 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case DECIMAL_LITERAL:
                        {
                        int LA9_19 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case CHARACTER_LITERAL:
                        {
                        int LA9_20 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case STRING_LITERAL:
                        {
                        int LA9_21 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case BINARY_LITERAL:
                        {
                        int LA9_22 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case GORILLA_INSTR_NAME:
                        {
                        int LA9_23 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case 35:
                        {
                        int LA9_24 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case 38:
                        {
                        int LA9_25 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case 24:
                    case 30:
                    case 33:
                    case 34:
                    case 37:
                    case 80:
                        {
                        int LA9_26 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;

                    }

                    }
                    break;
                case 64:
                    {
                    int LA9_3 = input.LA(2);

                    if ( (LA9_3==41) ) {
                        int LA9_27 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                    }


                    }
                    break;
                case SEMICOLON:
                    {
                    int LA9_4 = input.LA(2);

                    if ( (synpred10_GorillaPP_grammar()) ) {
                        alt9=1;
                    }


                    }
                    break;
                case 69:
                    {
                    int LA9_5 = input.LA(2);

                    if ( (LA9_5==31) ) {
                        int LA9_40 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                    }


                    }
                    break;
                case 76:
                    {
                    switch ( input.LA(2) ) {
                    case ID:
                        {
                        int LA9_41 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case 61:
                        {
                        int LA9_42 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case 64:
                        {
                        int LA9_43 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case SEMICOLON:
                        {
                        int LA9_44 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case 69:
                        {
                        int LA9_45 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case 76:
                        {
                        int LA9_46 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case 63:
                        {
                        int LA9_47 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;
                    case 60:
                        {
                        int LA9_48 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                        }
                        break;

                    }

                    }
                    break;
                case 63:
                    {
                    int LA9_7 = input.LA(2);

                    if ( (LA9_7==SEMICOLON) ) {
                        int LA9_49 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                    }


                    }
                    break;
                case 60:
                    {
                    int LA9_8 = input.LA(2);

                    if ( (LA9_8==SEMICOLON) ) {
                        int LA9_50 = input.LA(3);

                        if ( (synpred10_GorillaPP_grammar()) ) {
                            alt9=1;
                        }


                    }


                    }
                    break;

                }

                switch (alt9) {
            	case 1 :
            	    // GorillaPP_grammar.g:637:32: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_instr_body211);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            if ( state.backtracking==0 ) {
                         ((Symbols_scope)Symbols_stack.peek()).isPreOff = false;}

            // GorillaPP_grammar.g:639:12: ( offload_statement )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==ID) ) {
                    int LA10_1 = input.LA(2);

                    if ( (LA10_1==45) ) {
                        int LA10_13 = input.LA(3);

                        if ( (synpred11_GorillaPP_grammar()) ) {
                            alt10=1;
                        }


                    }


                }


                switch (alt10) {
            	case 1 :
            	    // GorillaPP_grammar.g:639:12: offload_statement
            	    {
            	    pushFollow(FOLLOW_offload_statement_in_instr_body228);
            	    offload_statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            if ( state.backtracking==0 ) {
                         ((Symbols_scope)Symbols_stack.peek()).isPostOff = true; }

            // GorillaPP_grammar.g:641:12: ( statement )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==ID||LA11_0==SEMICOLON||(LA11_0 >= 60 && LA11_0 <= 61)||(LA11_0 >= 63 && LA11_0 <= 64)||LA11_0==69||LA11_0==76) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // GorillaPP_grammar.g:641:12: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_instr_body245);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            // GorillaPP_grammar.g:641:23: ( finish_statement )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==66||LA12_0==68) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // GorillaPP_grammar.g:641:23: finish_statement
                    {
                    pushFollow(FOLLOW_finish_statement_in_instr_body248);
                    finish_statement();

                    state._fsp--;
                    if (state.failed) return ;

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
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 5, instr_body_StartIndex); }

            instr_body_stack.pop();
        }
        return ;
    }
    // $ANTLR end "instr_body"



    // $ANTLR start "local_variable_definition"
    // GorillaPP_grammar.g:643:1: local_variable_definition : ( ( type_specifier variablelist ) | ( ID array_index ) ) ';' ;
    public final void local_variable_definition() throws RecognitionException {
        int local_variable_definition_StartIndex = input.index();

        GorillaPP_grammarParser.type_specifier_return type_specifier2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return ; }

            // GorillaPP_grammar.g:644:2: ( ( ( type_specifier variablelist ) | ( ID array_index ) ) ';' )
            // GorillaPP_grammar.g:644:4: ( ( type_specifier variablelist ) | ( ID array_index ) ) ';'
            {
            // GorillaPP_grammar.g:644:4: ( ( type_specifier variablelist ) | ( ID array_index ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==62||LA13_0==67||(LA13_0 >= 70 && LA13_0 <= 72)||LA13_0==75) ) {
                alt13=1;
            }
            else if ( (LA13_0==ID) ) {
                int LA13_7 = input.LA(2);

                if ( (LA13_7==ID) ) {
                    alt13=1;
                }
                else if ( (LA13_7==56) ) {
                    alt13=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 7, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }
            switch (alt13) {
                case 1 :
                    // GorillaPP_grammar.g:644:5: ( type_specifier variablelist )
                    {
                    // GorillaPP_grammar.g:644:5: ( type_specifier variablelist )
                    // GorillaPP_grammar.g:644:6: type_specifier variablelist
                    {
                    pushFollow(FOLLOW_type_specifier_in_local_variable_definition261);
                    type_specifier2=type_specifier();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                            ((Symbols_scope)Symbols_stack.peek()).typeName = (type_specifier2!=null?input.toString(type_specifier2.start,type_specifier2.stop):null);}

                    pushFollow(FOLLOW_variablelist_in_local_variable_definition272);
                    variablelist();

                    state._fsp--;
                    if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:646:23: ( ID array_index )
                    {
                    // GorillaPP_grammar.g:646:23: ( ID array_index )
                    // GorillaPP_grammar.g:646:24: ID array_index
                    {
                    match(input,ID,FOLLOW_ID_in_local_variable_definition278); if (state.failed) return ;

                    pushFollow(FOLLOW_array_index_in_local_variable_definition280);
                    array_index();

                    state._fsp--;
                    if (state.failed) return ;

                    }


                    }
                    break;

            }


            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_local_variable_definition284); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 6, local_variable_definition_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "local_variable_definition"



    // $ANTLR start "global_variable_definition"
    // GorillaPP_grammar.g:650:1: global_variable_definition : ( ( type_specifier variablelist ) ( array_index )? | ( 'bit_vector' array_index ID ) ) ( '=' constant )? ';' ;
    public final void global_variable_definition() throws RecognitionException {
        int global_variable_definition_StartIndex = input.index();

        GorillaPP_grammarParser.type_specifier_return type_specifier3 =null;

        GorillaPP_grammarParser.constant_return constant4 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return ; }

            // GorillaPP_grammar.g:651:2: ( ( ( type_specifier variablelist ) ( array_index )? | ( 'bit_vector' array_index ID ) ) ( '=' constant )? ';' )
            // GorillaPP_grammar.g:651:4: ( ( type_specifier variablelist ) ( array_index )? | ( 'bit_vector' array_index ID ) ) ( '=' constant )? ';'
            {
            // GorillaPP_grammar.g:651:4: ( ( type_specifier variablelist ) ( array_index )? | ( 'bit_vector' array_index ID ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==ID||LA15_0==62||LA15_0==67||(LA15_0 >= 70 && LA15_0 <= 72)||LA15_0==75) ) {
                alt15=1;
            }
            else if ( (LA15_0==59) ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }
            switch (alt15) {
                case 1 :
                    // GorillaPP_grammar.g:651:5: ( type_specifier variablelist ) ( array_index )?
                    {
                    // GorillaPP_grammar.g:651:5: ( type_specifier variablelist )
                    // GorillaPP_grammar.g:651:6: type_specifier variablelist
                    {
                    pushFollow(FOLLOW_type_specifier_in_global_variable_definition303);
                    type_specifier3=type_specifier();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                            ((Symbols_scope)Symbols_stack.peek()).globalInitialized = false; 
                            ((Symbols_scope)Symbols_stack.peek()).typeName = (type_specifier3!=null?input.toString(type_specifier3.start,type_specifier3.stop):null); 
                            ((Symbols_scope)Symbols_stack.peek()).currentGlobals.clear();}

                    pushFollow(FOLLOW_variablelist_in_global_variable_definition307);
                    variablelist();

                    state._fsp--;
                    if (state.failed) return ;

                    }


                    // GorillaPP_grammar.g:655:11: ( array_index )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==56) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // GorillaPP_grammar.g:655:12: array_index
                            {
                            pushFollow(FOLLOW_array_index_in_global_variable_definition322);
                            array_index();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:655:28: ( 'bit_vector' array_index ID )
                    {
                    // GorillaPP_grammar.g:655:28: ( 'bit_vector' array_index ID )
                    // GorillaPP_grammar.g:655:29: 'bit_vector' array_index ID
                    {
                    match(input,59,FOLLOW_59_in_global_variable_definition329); if (state.failed) return ;

                    pushFollow(FOLLOW_array_index_in_global_variable_definition331);
                    array_index();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,ID,FOLLOW_ID_in_global_variable_definition333); if (state.failed) return ;

                    }


                    }
                    break;

            }


            // GorillaPP_grammar.g:656:11: ( '=' constant )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==45) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // GorillaPP_grammar.g:656:13: '=' constant
                    {
                    if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).globalInitialized = true;}

                    match(input,45,FOLLOW_45_in_global_variable_definition352); if (state.failed) return ;

                    pushFollow(FOLLOW_constant_in_global_variable_definition366);
                    constant4=constant();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_global_variable_definition370); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                         Iterator variable_it = 
                          ((Symbols_scope)Symbols_stack.peek()).currentGlobals.iterator();
                         while (variable_it.hasNext()) {
                           ((Symbols_scope)Symbols_stack.peek()).definitionString +=  
                            "val " + variable_it.next() + 
                            " = Vec (numOfThreads) {Reg(";
                           if (((Symbols_scope)Symbols_stack.peek()).globalInitialized) {
                             ((Symbols_scope)Symbols_stack.peek()).definitionString += 
                             c2ChiselType(((Symbols_scope)Symbols_stack.peek()).typeName) + 
                              ", resetVal=" + 
                              Ctype2ChiselInit(((Symbols_scope)Symbols_stack.peek()).typeName, 
                               (constant4!=null?input.toString(constant4.start,constant4.stop):null)) + 
                               ")}\t//Global variable \n";
                          } else {
                            ((Symbols_scope)Symbols_stack.peek()).definitionString += 
                             c2ChiselType(((Symbols_scope)Symbols_stack.peek()).typeName) + 
                             ")}\t//Global variable \n";
                          }
                        } 
                      }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 7, global_variable_definition_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "global_variable_definition"



    // $ANTLR start "type_definition"
    // GorillaPP_grammar.g:680:1: type_definition : 'typedef' type_specifier_struct ID ';' ;
    public final void type_definition() throws RecognitionException {
        int type_definition_StartIndex = input.index();

        Token ID5=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return ; }

            // GorillaPP_grammar.g:681:2: ( 'typedef' type_specifier_struct ID ';' )
            // GorillaPP_grammar.g:681:4: 'typedef' type_specifier_struct ID ';'
            {
            match(input,74,FOLLOW_74_in_type_definition383); if (state.failed) return ;

            pushFollow(FOLLOW_type_specifier_struct_in_type_definition385);
            type_specifier_struct();

            state._fsp--;
            if (state.failed) return ;

            ID5=(Token)match(input,ID,FOLLOW_ID_in_type_definition387); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                    ((Symbols_scope)Symbols_stack.peek()).types.add((ID5!=null?ID5.getText():null));
                    //For now we are only supporting typedef for structs
            		((Symbols_scope)Symbols_stack.peek()).typeDefinitionString += "class " + 
                     (ID5!=null?ID5.getText():null) + " extends Bundle { \n" + 
                     ((Symbols_scope)Symbols_stack.peek()).definitionString + "}\n"; 
                     System.out.println("//define type "+(ID5!=null?ID5.getText():null));
            		((Symbols_scope)Symbols_stack.peek()).definitionString = "";
            	  }

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_type_definition391); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, type_definition_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "type_definition"



    // $ANTLR start "pragma"
    // GorillaPP_grammar.g:691:1: pragma : ( off_pragma | in_pragma | out_pragma | other_pragma );
    public final void pragma() throws RecognitionException {
        int pragma_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return ; }

            // GorillaPP_grammar.g:692:2: ( off_pragma | in_pragma | out_pragma | other_pragma )
            int alt17=4;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==27) ) {
                switch ( input.LA(2) ) {
                case 53:
                    {
                    alt17=1;
                    }
                    break;
                case 52:
                    {
                    alt17=2;
                    }
                    break;
                case 54:
                    {
                    alt17=3;
                    }
                    break;
                case 51:
                case 55:
                    {
                    alt17=4;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // GorillaPP_grammar.g:692:4: off_pragma
                    {
                    pushFollow(FOLLOW_off_pragma_in_pragma402);
                    off_pragma();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:692:18: in_pragma
                    {
                    pushFollow(FOLLOW_in_pragma_in_pragma407);
                    in_pragma();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:692:30: out_pragma
                    {
                    pushFollow(FOLLOW_out_pragma_in_pragma411);
                    out_pragma();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // GorillaPP_grammar.g:692:43: other_pragma
                    {
                    pushFollow(FOLLOW_other_pragma_in_pragma415);
                    other_pragma();

                    state._fsp--;
                    if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 9, pragma_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "pragma"



    // $ANTLR start "other_pragma"
    // GorillaPP_grammar.g:694:1: other_pragma : '#pragma' ( 'CONCURRENT_SAFE' | 'PIPELINABLE' ) ;
    public final void other_pragma() throws RecognitionException {
        int other_pragma_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return ; }

            // GorillaPP_grammar.g:695:2: ( '#pragma' ( 'CONCURRENT_SAFE' | 'PIPELINABLE' ) )
            // GorillaPP_grammar.g:695:4: '#pragma' ( 'CONCURRENT_SAFE' | 'PIPELINABLE' )
            {
            match(input,27,FOLLOW_27_in_other_pragma424); if (state.failed) return ;

            if ( input.LA(1)==51||input.LA(1)==55 ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
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
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 10, other_pragma_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "other_pragma"



    // $ANTLR start "in_pragma"
    // GorillaPP_grammar.g:698:1: in_pragma : '#pragma' 'INPUT' in_type= ID ;
    public final void in_pragma() throws RecognitionException {
        int in_pragma_StartIndex = input.index();

        Token in_type=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return ; }

            // GorillaPP_grammar.g:699:2: ( '#pragma' 'INPUT' in_type= ID )
            // GorillaPP_grammar.g:699:4: '#pragma' 'INPUT' in_type= ID
            {
            match(input,27,FOLLOW_27_in_in_pragma443); if (state.failed) return ;

            match(input,52,FOLLOW_52_in_in_pragma445); if (state.failed) return ;

            in_type=(Token)match(input,ID,FOLLOW_ID_in_in_pragma451); if (state.failed) return ;

            if ( state.backtracking==0 ) {	
            	    ((Symbols_scope)Symbols_stack.peek()).ioString += "(() => " + 
                     c2ChiselType((in_type!=null?in_type.getText():null)) + ")";
                    ((Symbols_scope)Symbols_stack.peek()).inputType = (in_type!=null?in_type.getText():null);
            	}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, in_pragma_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "in_pragma"



    // $ANTLR start "out_pragma"
    // GorillaPP_grammar.g:705:1: out_pragma : '#pragma' 'OUTPUT' out_type= ID ;
    public final void out_pragma() throws RecognitionException {
        int out_pragma_StartIndex = input.index();

        Token out_type=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return ; }

            // GorillaPP_grammar.g:706:2: ( '#pragma' 'OUTPUT' out_type= ID )
            // GorillaPP_grammar.g:706:4: '#pragma' 'OUTPUT' out_type= ID
            {
            match(input,27,FOLLOW_27_in_out_pragma463); if (state.failed) return ;

            match(input,54,FOLLOW_54_in_out_pragma465); if (state.failed) return ;

            out_type=(Token)match(input,ID,FOLLOW_ID_in_out_pragma470); if (state.failed) return ;

            if ( state.backtracking==0 ) {	
            	    ((Symbols_scope)Symbols_stack.peek()).ioString += "(() => " + 
                     c2ChiselType((out_type!=null?out_type.getText():null)) + ") (ArrayBuffer(";
                    ((Symbols_scope)Symbols_stack.peek()).outputType = (out_type!=null?out_type.getText():null);
            	}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 12, out_pragma_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "out_pragma"



    // $ANTLR start "off_pragma"
    // GorillaPP_grammar.g:712:1: off_pragma : '#pragma' 'OFFLOAD' '(' offPort= ID ',' req_type= ID ',' rep_type= ID ')' ;
    public final void off_pragma() throws RecognitionException {
        int off_pragma_StartIndex = input.index();

        Token offPort=null;
        Token req_type=null;
        Token rep_type=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return ; }

            // GorillaPP_grammar.g:713:2: ( '#pragma' 'OFFLOAD' '(' offPort= ID ',' req_type= ID ',' rep_type= ID ')' )
            // GorillaPP_grammar.g:713:4: '#pragma' 'OFFLOAD' '(' offPort= ID ',' req_type= ID ',' rep_type= ID ')'
            {
            match(input,27,FOLLOW_27_in_off_pragma483); if (state.failed) return ;

            match(input,53,FOLLOW_53_in_off_pragma485); if (state.failed) return ;

            match(input,31,FOLLOW_31_in_off_pragma487); if (state.failed) return ;

            offPort=(Token)match(input,ID,FOLLOW_ID_in_off_pragma491); if (state.failed) return ;

            match(input,36,FOLLOW_36_in_off_pragma493); if (state.failed) return ;

            req_type=(Token)match(input,ID,FOLLOW_ID_in_off_pragma504); if (state.failed) return ;

            match(input,36,FOLLOW_36_in_off_pragma506); if (state.failed) return ;

            rep_type=(Token)match(input,ID,FOLLOW_ID_in_off_pragma510); if (state.failed) return ;

            match(input,32,FOLLOW_32_in_off_pragma512); if (state.failed) return ;

            if ( state.backtracking==0 ) {	
                    if(offPortDefined((offPort!=null?offPort.getText():null))) 
            		System.out.println(
                     "Redundant functional unit detected");
               	  else {
            		((Symbols_scope)Symbols_stack.peek()).offloadPortsString += 
                      (offPort!=null?offPort.getText():null) + "\n";
            		((Symbols_scope)Symbols_stack.peek()).offloadPorts.add((offPort!=null?offPort.getText():null));
            		((Symbols_scope)Symbols_stack.peek()).offloadPortsReqType.put((offPort!=null?offPort.getText():null), 
                     c2ChiselType((req_type!=null?req_type.getText():null)));
            		((Symbols_scope)Symbols_stack.peek()).offloadPortsRepType.put((offPort!=null?offPort.getText():null), 
                     c2ChiselType((rep_type!=null?rep_type.getText():null)));
                    if (((Symbols_scope)Symbols_stack.peek()).firstOffload) {
            	      ((Symbols_scope)Symbols_stack.peek()).ioString += 
                        "(\"" + (offPort!=null?offPort.getText():null)  + "\", () => " + 
                        c2ChiselType((req_type!=null?req_type.getText():null)) + 
                        " , () => " + c2ChiselType((rep_type!=null?rep_type.getText():null)) + ")";
                      ((Symbols_scope)Symbols_stack.peek()).firstOffload = false;
                    } else {
                      ((Symbols_scope)Symbols_stack.peek()).ioString += 
                       ", (\"" + (offPort!=null?offPort.getText():null)  + "\", () => " + 
                       c2ChiselType((req_type!=null?req_type.getText():null)) + 
                       " , () => " + c2ChiselType((rep_type!=null?rep_type.getText():null)) + ")";
                    }
                  }
            	}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, off_pragma_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "off_pragma"



    // $ANTLR start "defineArg"
    // GorillaPP_grammar.g:745:1: defineArg : ( '(' {...}?id1= ID ( ',' id2= ID )* ')' )? ;
    public final void defineArg() throws RecognitionException {
        int defineArg_StartIndex = input.index();

        Token id1=null;
        Token id2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return ; }

            // GorillaPP_grammar.g:746:2: ( ( '(' {...}?id1= ID ( ',' id2= ID )* ')' )? )
            // GorillaPP_grammar.g:746:4: ( '(' {...}?id1= ID ( ',' id2= ID )* ')' )?
            {
            // GorillaPP_grammar.g:746:4: ( '(' {...}?id1= ID ( ',' id2= ID )* ')' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==31) ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1==ID) ) {
                    int LA19_13 = input.LA(3);

                    if ( (synpred23_GorillaPP_grammar()) ) {
                        alt19=1;
                    }
                }
            }
            switch (alt19) {
                case 1 :
                    // GorillaPP_grammar.g:746:5: '(' {...}?id1= ID ( ',' id2= ID )* ')'
                    {
                    match(input,31,FOLLOW_31_in_defineArg528); if (state.failed) return ;

                    if ( !((c2ChiselType(input.LT(1).getText()).contains("new") && 
                               !isTypeName(input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        throw new FailedPredicateException(input, "defineArg", "c2ChiselType(input.LT(1).getText()).contains(\"new\") && \n           !isTypeName(input.LT(1).getText())");
                    }

                    if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).isMacroWithArgument = true; 
                               ((Symbols_scope)Symbols_stack.peek()).macroString += "def " + ((Symbols_scope)Symbols_stack.peek()).macroId + "(";}

                    id1=(Token)match(input,ID,FOLLOW_ID_in_defineArg558); if (state.failed) return ;

                    if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).macroString += (id1!=null?id1.getText():null)+":"+"UFix";}

                    // GorillaPP_grammar.g:751:11: ( ',' id2= ID )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==36) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // GorillaPP_grammar.g:751:12: ',' id2= ID
                    	    {
                    	    match(input,36,FOLLOW_36_in_defineArg574); if (state.failed) return ;

                    	    id2=(Token)match(input,ID,FOLLOW_ID_in_defineArg578); if (state.failed) return ;

                    	    if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).macroString += ", " + 
                    	               (id2!=null?id2.getText():null)+":"+"UFix";}

                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    match(input,32,FOLLOW_32_in_defineArg595); if (state.failed) return ;

                    if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).macroString += ") = ";}

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
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, defineArg_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "defineArg"



    // $ANTLR start "define"
    // GorillaPP_grammar.g:756:1: define : '#define' id= ID defineArg ( constant_expression ) ;
    public final void define() throws RecognitionException {
        int define_StartIndex = input.index();

        Token id=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return ; }

            // GorillaPP_grammar.g:757:2: ( '#define' id= ID defineArg ( constant_expression ) )
            // GorillaPP_grammar.g:757:4: '#define' id= ID defineArg ( constant_expression )
            {
            match(input,26,FOLLOW_26_in_define609); if (state.failed) return ;

            if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).isMacroWithArgument = false;}

            id=(Token)match(input,ID,FOLLOW_ID_in_define622); if (state.failed) return ;

            if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).macroId = (id!=null?id.getText():null);}

            pushFollow(FOLLOW_defineArg_in_define626);
            defineArg();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).isMacro = true;}

            // GorillaPP_grammar.g:760:7: ( constant_expression )
            // GorillaPP_grammar.g:760:29: constant_expression
            {
            pushFollow(FOLLOW_constant_expression_in_define648);
            constant_expression();

            state._fsp--;
            if (state.failed) return ;

            }


            if ( state.backtracking==0 ) {
            	    ((Symbols_scope)Symbols_stack.peek()).constantMacros.add((id!=null?id.getText():null));
            	    System.out.println("//constant macro definition for " + 
                     (id!=null?id.getText():null));
                    if (((Symbols_scope)Symbols_stack.peek()).isHeaderFile) {
                      if(((Symbols_scope)Symbols_stack.peek()).isMacroWithArgument) {
            	        ((Symbols_scope)Symbols_stack.peek()).globalMacroString +=  
                        ((Symbols_scope)Symbols_stack.peek()).macroString + 
                        "//constant macro definition \n";
                      } else {
            	        ((Symbols_scope)Symbols_stack.peek()).globalMacroString +=  
                         "val\t" + (id!=null?id.getText():null) + " = " + 
                         ((Symbols_scope)Symbols_stack.peek()).macroString + 
                         "//constant macro definition \n";
                      }
                    } else {
                      if(((Symbols_scope)Symbols_stack.peek()).isMacroWithArgument) {
            	      ((Symbols_scope)Symbols_stack.peek()).definitionString +=  
                        ((Symbols_scope)Symbols_stack.peek()).macroString + 
                        "//constant macro definition \n";
                      } else {
            	        ((Symbols_scope)Symbols_stack.peek()).definitionString +=  "val\t" + 
                         (id!=null?id.getText():null) + " = " + ((Symbols_scope)Symbols_stack.peek()).macroString + 
                         "//constant macro definition \n";
                      }
                    }
                    ((Symbols_scope)Symbols_stack.peek()).macroString = "";
            	    ((Symbols_scope)Symbols_stack.peek()).isMacro = false;
                  }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, define_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "define"


    protected static class declaration_scope {
        boolean isTypedef;
    }
    protected Stack declaration_stack = new Stack();



    // $ANTLR start "declaration"
    // GorillaPP_grammar.g:789:1: declaration : ( 'typedef' declaration_specifiers init_declarator_list ';' | declaration_specifiers init_declarator_list ';' );
    public final void declaration() throws RecognitionException {
        declaration_stack.push(new declaration_scope());
        int declaration_StartIndex = input.index();


          ((declaration_scope)declaration_stack.peek()).isTypedef = false;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return ; }

            // GorillaPP_grammar.g:796:2: ( 'typedef' declaration_specifiers init_declarator_list ';' | declaration_specifiers init_declarator_list ';' )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==74) ) {
                alt20=1;
            }
            else if ( (LA20_0==ID||LA20_0==62||LA20_0==67||(LA20_0 >= 70 && LA20_0 <= 72)||LA20_0==75) ) {
                alt20=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // GorillaPP_grammar.g:796:4: 'typedef' declaration_specifiers init_declarator_list ';'
                    {
                    match(input,74,FOLLOW_74_in_declaration668); if (state.failed) return ;

                    if ( state.backtracking==0 ) {((declaration_scope)declaration_stack.peek()).isTypedef =true;}

                    pushFollow(FOLLOW_declaration_specifiers_in_declaration676);
                    declaration_specifiers();

                    state._fsp--;
                    if (state.failed) return ;

                    pushFollow(FOLLOW_init_declarator_list_in_declaration678);
                    init_declarator_list();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declaration680); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:799:5: declaration_specifiers init_declarator_list ';'
                    {
                    pushFollow(FOLLOW_declaration_specifiers_in_declaration699);
                    declaration_specifiers();

                    state._fsp--;
                    if (state.failed) return ;

                    pushFollow(FOLLOW_init_declarator_list_in_declaration701);
                    init_declarator_list();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declaration703); if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 16, declaration_StartIndex); }

            declaration_stack.pop();
        }
        return ;
    }
    // $ANTLR end "declaration"



    // $ANTLR start "init_declarator_list"
    // GorillaPP_grammar.g:802:1: init_declarator_list : init_declarator ( ',' init_declarator )* ;
    public final void init_declarator_list() throws RecognitionException {
        int init_declarator_list_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return ; }

            // GorillaPP_grammar.g:803:2: ( init_declarator ( ',' init_declarator )* )
            // GorillaPP_grammar.g:803:4: init_declarator ( ',' init_declarator )*
            {
            pushFollow(FOLLOW_init_declarator_in_init_declarator_list714);
            init_declarator();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:803:20: ( ',' init_declarator )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==36) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // GorillaPP_grammar.g:803:21: ',' init_declarator
            	    {
            	    match(input,36,FOLLOW_36_in_init_declarator_list717); if (state.failed) return ;

            	    pushFollow(FOLLOW_init_declarator_in_init_declarator_list719);
            	    init_declarator();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop21;
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
            if ( state.backtracking>0 ) { memoize(input, 17, init_declarator_list_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "init_declarator_list"



    // $ANTLR start "init_declarator"
    // GorillaPP_grammar.g:806:1: init_declarator : declarator ( '=' initializer )? ;
    public final void init_declarator() throws RecognitionException {
        int init_declarator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return ; }

            // GorillaPP_grammar.g:807:2: ( declarator ( '=' initializer )? )
            // GorillaPP_grammar.g:807:4: declarator ( '=' initializer )?
            {
            pushFollow(FOLLOW_declarator_in_init_declarator732);
            declarator();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:807:16: ( '=' initializer )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==45) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // GorillaPP_grammar.g:807:17: '=' initializer
                    {
                    match(input,45,FOLLOW_45_in_init_declarator736); if (state.failed) return ;

                    pushFollow(FOLLOW_initializer_in_init_declarator738);
                    initializer();

                    state._fsp--;
                    if (state.failed) return ;

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
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 18, init_declarator_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "init_declarator"



    // $ANTLR start "declarator"
    // GorillaPP_grammar.g:810:1: declarator : direct_declarator ;
    public final void declarator() throws RecognitionException {
        int declarator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return ; }

            // GorillaPP_grammar.g:811:2: ( direct_declarator )
            // GorillaPP_grammar.g:811:5: direct_declarator
            {
            pushFollow(FOLLOW_direct_declarator_in_declarator752);
            direct_declarator();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 19, declarator_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "declarator"



    // $ANTLR start "direct_declarator"
    // GorillaPP_grammar.g:814:1: direct_declarator : ( ID | '(' declarator ')' ) ( declarator_suffix )* ;
    public final void direct_declarator() throws RecognitionException {
        int direct_declarator_StartIndex = input.index();

        Token ID6=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return ; }

            // GorillaPP_grammar.g:815:2: ( ( ID | '(' declarator ')' ) ( declarator_suffix )* )
            // GorillaPP_grammar.g:815:4: ( ID | '(' declarator ')' ) ( declarator_suffix )*
            {
            // GorillaPP_grammar.g:815:4: ( ID | '(' declarator ')' )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==ID) ) {
                alt23=1;
            }
            else if ( (LA23_0==31) ) {
                alt23=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
                case 1 :
                    // GorillaPP_grammar.g:815:5: ID
                    {
                    ID6=(Token)match(input,ID,FOLLOW_ID_in_direct_declarator764); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                    	    if (declaration_stack.size()>0&&((declaration_scope)declaration_stack.peek()).isTypedef) {
                                ((Symbols_scope)Symbols_stack.peek()).types.add((ID6!=null?ID6.getText():null));
                                System.out.println("define type "+(ID6!=null?ID6.getText():null));
                                }
                              }

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:821:11: '(' declarator ')'
                    {
                    match(input,31,FOLLOW_31_in_direct_declarator779); if (state.failed) return ;

                    pushFollow(FOLLOW_declarator_in_direct_declarator781);
                    declarator();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,32,FOLLOW_32_in_direct_declarator783); if (state.failed) return ;

                    }
                    break;

            }


            // GorillaPP_grammar.g:821:31: ( declarator_suffix )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==31||LA24_0==56) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // GorillaPP_grammar.g:821:31: declarator_suffix
            	    {
            	    pushFollow(FOLLOW_declarator_suffix_in_direct_declarator786);
            	    declarator_suffix();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop24;
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
            if ( state.backtracking>0 ) { memoize(input, 20, direct_declarator_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "direct_declarator"



    // $ANTLR start "declarator_suffix"
    // GorillaPP_grammar.g:824:1: declarator_suffix : ( '[' constant_expression ']' | '[' ']' | '(' identifier_list ')' | '(' ')' );
    public final void declarator_suffix() throws RecognitionException {
        int declarator_suffix_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return ; }

            // GorillaPP_grammar.g:825:2: ( '[' constant_expression ']' | '[' ']' | '(' identifier_list ')' | '(' ')' )
            int alt25=4;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==56) ) {
                int LA25_1 = input.LA(2);

                if ( (LA25_1==57) ) {
                    alt25=2;
                }
                else if ( (LA25_1==BINARY_LITERAL||LA25_1==CHARACTER_LITERAL||LA25_1==DECIMAL_LITERAL||(LA25_1 >= GORILLA_INSTR_NAME && LA25_1 <= HEX_LITERAL)||LA25_1==ID||LA25_1==OCTAL_LITERAL||LA25_1==STRING_LITERAL||LA25_1==24||(LA25_1 >= 30 && LA25_1 <= 31)||(LA25_1 >= 33 && LA25_1 <= 35)||(LA25_1 >= 37 && LA25_1 <= 38)||LA25_1==80) ) {
                    alt25=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA25_0==31) ) {
                int LA25_2 = input.LA(2);

                if ( (LA25_2==32) ) {
                    alt25=4;
                }
                else if ( (LA25_2==ID) ) {
                    alt25=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }
            switch (alt25) {
                case 1 :
                    // GorillaPP_grammar.g:825:4: '[' constant_expression ']'
                    {
                    match(input,56,FOLLOW_56_in_declarator_suffix798); if (state.failed) return ;

                    pushFollow(FOLLOW_constant_expression_in_declarator_suffix800);
                    constant_expression();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,57,FOLLOW_57_in_declarator_suffix802); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:826:13: '[' ']'
                    {
                    match(input,56,FOLLOW_56_in_declarator_suffix816); if (state.failed) return ;

                    match(input,57,FOLLOW_57_in_declarator_suffix818); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:827:13: '(' identifier_list ')'
                    {
                    match(input,31,FOLLOW_31_in_declarator_suffix832); if (state.failed) return ;

                    pushFollow(FOLLOW_identifier_list_in_declarator_suffix834);
                    identifier_list();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,32,FOLLOW_32_in_declarator_suffix836); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // GorillaPP_grammar.g:828:13: '(' ')'
                    {
                    match(input,31,FOLLOW_31_in_declarator_suffix850); if (state.failed) return ;

                    match(input,32,FOLLOW_32_in_declarator_suffix852); if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 21, declarator_suffix_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "declarator_suffix"



    // $ANTLR start "identifier_list"
    // GorillaPP_grammar.g:831:1: identifier_list : ID ( ',' ID )* ;
    public final void identifier_list() throws RecognitionException {
        int identifier_list_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return ; }

            // GorillaPP_grammar.g:832:2: ( ID ( ',' ID )* )
            // GorillaPP_grammar.g:832:4: ID ( ',' ID )*
            {
            match(input,ID,FOLLOW_ID_in_identifier_list863); if (state.failed) return ;

            // GorillaPP_grammar.g:832:7: ( ',' ID )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==36) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // GorillaPP_grammar.g:832:8: ',' ID
            	    {
            	    match(input,36,FOLLOW_36_in_identifier_list866); if (state.failed) return ;

            	    match(input,ID,FOLLOW_ID_in_identifier_list868); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop26;
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
            if ( state.backtracking>0 ) { memoize(input, 22, identifier_list_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "identifier_list"



    // $ANTLR start "declaration_specifiers"
    // GorillaPP_grammar.g:835:1: declaration_specifiers : type_specifier ;
    public final void declaration_specifiers() throws RecognitionException {
        int declaration_specifiers_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return ; }

            // GorillaPP_grammar.g:836:2: ( type_specifier )
            // GorillaPP_grammar.g:836:6: type_specifier
            {
            pushFollow(FOLLOW_type_specifier_in_declaration_specifiers883);
            type_specifier();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 23, declaration_specifiers_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "declaration_specifiers"



    // $ANTLR start "initializer"
    // GorillaPP_grammar.g:840:1: initializer : ( assignment_expression | '{' initializer_list ( ',' )? '}' );
    public final void initializer() throws RecognitionException {
        int initializer_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return ; }

            // GorillaPP_grammar.g:841:2: ( assignment_expression | '{' initializer_list ( ',' )? '}' )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==ID) ) {
                alt28=1;
            }
            else if ( (LA28_0==76) ) {
                alt28=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;

            }
            switch (alt28) {
                case 1 :
                    // GorillaPP_grammar.g:841:4: assignment_expression
                    {
                    pushFollow(FOLLOW_assignment_expression_in_initializer895);
                    assignment_expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:842:4: '{' initializer_list ( ',' )? '}'
                    {
                    match(input,76,FOLLOW_76_in_initializer900); if (state.failed) return ;

                    pushFollow(FOLLOW_initializer_list_in_initializer902);
                    initializer_list();

                    state._fsp--;
                    if (state.failed) return ;

                    // GorillaPP_grammar.g:842:25: ( ',' )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==36) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // GorillaPP_grammar.g:842:25: ','
                            {
                            match(input,36,FOLLOW_36_in_initializer904); if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,79,FOLLOW_79_in_initializer907); if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 24, initializer_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "initializer"



    // $ANTLR start "initializer_list"
    // GorillaPP_grammar.g:845:1: initializer_list : initializer ( ',' initializer )* ;
    public final void initializer_list() throws RecognitionException {
        int initializer_list_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return ; }

            // GorillaPP_grammar.g:846:2: ( initializer ( ',' initializer )* )
            // GorillaPP_grammar.g:846:4: initializer ( ',' initializer )*
            {
            pushFollow(FOLLOW_initializer_in_initializer_list918);
            initializer();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:846:16: ( ',' initializer )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==36) ) {
                    int LA29_1 = input.LA(2);

                    if ( (LA29_1==ID||LA29_1==76) ) {
                        alt29=1;
                    }


                }


                switch (alt29) {
            	case 1 :
            	    // GorillaPP_grammar.g:846:17: ',' initializer
            	    {
            	    match(input,36,FOLLOW_36_in_initializer_list921); if (state.failed) return ;

            	    pushFollow(FOLLOW_initializer_in_initializer_list923);
            	    initializer();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop29;
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
            if ( state.backtracking>0 ) { memoize(input, 25, initializer_list_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "initializer_list"



    // $ANTLR start "define_lable"
    // GorillaPP_grammar.g:849:1: define_lable : ID ;
    public final void define_lable() throws RecognitionException {
        int define_lable_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return ; }

            // GorillaPP_grammar.g:850:2: ( ID )
            // GorillaPP_grammar.g:850:4: ID
            {
            match(input,ID,FOLLOW_ID_in_define_lable936); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 26, define_lable_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "define_lable"



    // $ANTLR start "offload_statement"
    // GorillaPP_grammar.g:853:1: offload_statement : offload_expression SEMICOLON ;
    public final void offload_statement() throws RecognitionException {
        int offload_statement_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return ; }

            // GorillaPP_grammar.g:854:5: ( offload_expression SEMICOLON )
            // GorillaPP_grammar.g:854:7: offload_expression SEMICOLON
            {
            pushFollow(FOLLOW_offload_expression_in_offload_statement951);
            offload_expression();

            state._fsp--;
            if (state.failed) return ;

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_offload_statement953); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 27, offload_statement_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "offload_statement"



    // $ANTLR start "statement"
    // GorillaPP_grammar.g:857:1: statement : ( labeled_statement | expression_statement | selection_statement | compound_statement | jump_statement );
    public final void statement() throws RecognitionException {
        int statement_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return ; }

            // GorillaPP_grammar.g:858:2: ( labeled_statement | expression_statement | selection_statement | compound_statement | jump_statement )
            int alt30=5;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA30_1 = input.LA(2);

                if ( (LA30_1==41) ) {
                    alt30=1;
                }
                else if ( (LA30_1==39||LA30_1==45) ) {
                    alt30=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 1, input);

                    throw nvae;

                }
                }
                break;
            case 61:
            case 64:
                {
                alt30=1;
                }
                break;
            case SEMICOLON:
                {
                alt30=2;
                }
                break;
            case 69:
                {
                alt30=3;
                }
                break;
            case 76:
                {
                alt30=4;
                }
                break;
            case 60:
            case 63:
                {
                alt30=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;

            }

            switch (alt30) {
                case 1 :
                    // GorillaPP_grammar.g:858:3: labeled_statement
                    {
                    pushFollow(FOLLOW_labeled_statement_in_statement968);
                    labeled_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:859:3: expression_statement
                    {
                    pushFollow(FOLLOW_expression_statement_in_statement972);
                    expression_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:860:3: selection_statement
                    {
                    pushFollow(FOLLOW_selection_statement_in_statement976);
                    selection_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // GorillaPP_grammar.g:861:3: compound_statement
                    {
                    pushFollow(FOLLOW_compound_statement_in_statement980);
                    compound_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // GorillaPP_grammar.g:862:3: jump_statement
                    {
                    pushFollow(FOLLOW_jump_statement_in_statement984);
                    jump_statement();

                    state._fsp--;
                    if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 28, statement_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "statement"



    // $ANTLR start "compound_statement"
    // GorillaPP_grammar.g:865:1: compound_statement : '{' ( statement )+ '}' ;
    public final void compound_statement() throws RecognitionException {
        int compound_statement_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return ; }

            // GorillaPP_grammar.g:866:2: ( '{' ( statement )+ '}' )
            // GorillaPP_grammar.g:866:3: '{' ( statement )+ '}'
            {
            match(input,76,FOLLOW_76_in_compound_statement994); if (state.failed) return ;

            // GorillaPP_grammar.g:866:7: ( statement )+
            int cnt31=0;
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==ID||LA31_0==SEMICOLON||(LA31_0 >= 60 && LA31_0 <= 61)||(LA31_0 >= 63 && LA31_0 <= 64)||LA31_0==69||LA31_0==76) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // GorillaPP_grammar.g:866:7: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_compound_statement996);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt31 >= 1 ) break loop31;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(31, input);
                        throw eee;
                }
                cnt31++;
            } while (true);


            match(input,79,FOLLOW_79_in_compound_statement999); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 29, compound_statement_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "compound_statement"



    // $ANTLR start "offload_expression"
    // GorillaPP_grammar.g:869:1: offload_expression :target= ID '=' offId= ID '(' conditional_expression ')' ;
    public final void offload_expression() throws RecognitionException {
        int offload_expression_StartIndex = input.index();

        Token target=null;
        Token offId=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return ; }

            // GorillaPP_grammar.g:870:2: (target= ID '=' offId= ID '(' conditional_expression ')' )
            // GorillaPP_grammar.g:870:4: target= ID '=' offId= ID '(' conditional_expression ')'
            {
            if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).offString = ""; }

            target=(Token)match(input,ID,FOLLOW_ID_in_offload_expression1014); if (state.failed) return ;

            match(input,45,FOLLOW_45_in_offload_expression1016); if (state.failed) return ;

            offId=(Token)match(input,ID,FOLLOW_ID_in_offload_expression1020); if (state.failed) return ;

            if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).offId += offId; }

            match(input,31,FOLLOW_31_in_offload_expression1038); if (state.failed) return ;

            pushFollow(FOLLOW_conditional_expression_in_offload_expression1040);
            conditional_expression();

            state._fsp--;
            if (state.failed) return ;

            match(input,32,FOLLOW_32_in_offload_expression1042); if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                    if (!((Symbols_scope)Symbols_stack.peek()).OffValidString.containsKey(
                     (offId!=null?offId.getText():null))) {
                      System.out.println("Error: offload " + 
                      (offId!=null?offId.getText():null) + " is not added yet");
                    }
                    if (!((Symbols_scope)Symbols_stack.peek()).OffValidString.get(
                     (offId!=null?offId.getText():null)).equals("")) {
                      ((Symbols_scope)Symbols_stack.peek()).OffValidString.put((offId!=null?offId.getText():null), 
                      ((Symbols_scope)Symbols_stack.peek()).OffValidString.get((offId!=null?offId.getText():null)) + " || ");
                      ((Symbols_scope)Symbols_stack.peek()).OffReqString.put((offId!=null?offId.getText():null), 
                      ((Symbols_scope)Symbols_stack.peek()).OffReqString.get((offId!=null?offId.getText():null)) + " , ");
                    }
                    ((Symbols_scope)Symbols_stack.peek()).OffValidString.put((offId!=null?offId.getText():null), 
                     ((Symbols_scope)Symbols_stack.peek()).OffValidString.get((offId!=null?offId.getText():null)) + 
                     " (rThread != NONE_SELECTED && State(rThread)" +  
                     " === " + ((Symbols_scope)Symbols_stack.peek()).gorillaInstrName + ")");
                     ((Symbols_scope)Symbols_stack.peek()).OffReqString.put((offId!=null?offId.getText():null), 
                       ((Symbols_scope)Symbols_stack.peek()).OffReqString.get((offId!=null?offId.getText():null)) + 
                       " ((rThread != NONE_SELECTED && State(rThread) === " + 
                       ((Symbols_scope)Symbols_stack.peek()).gorillaInstrName + ")" + 
                       "," +  ((Symbols_scope)Symbols_stack.peek()).offString + ")");
                       if (localVarDefined((target!=null?target.getText():null)) && 
                        !c2ChiselType((String)((Symbols_scope)Symbols_stack.peek()).localsType.get(
                        (target!=null?target.getText():null))).contains("new")) {
                         ((Symbols_scope)Symbols_stack.peek()).combinationalString += 
                          "val " + (target!=null?target.getText():null) + " = " +  (offId!=null?offId.getText():null) + 
                          "Port.rep.bits" + "\n" ;
                       } else if (localVarDefined((target!=null?target.getText():null)) && 
                         c2ChiselType((String)((Symbols_scope)Symbols_stack.peek()).localsType.get(
                          (target!=null?target.getText():null))).contains("new")){
                          ((Symbols_scope)Symbols_stack.peek()).combinationalString += 
                           (target!=null?target.getText():null) + " := " + 
                             (offId!=null?offId.getText():null) + "Port.rep.bits" + "\n" ;
                       } else {
                         ((Symbols_scope)Symbols_stack.peek()).contextEditString += (target!=null?target.getText():null) + "(vThread) := " + 
                         (offId!=null?offId.getText():null) + "Port.rep.bits" + "\n" ;
                       }
                       }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 30, offload_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "offload_expression"



    // $ANTLR start "macro_expression"
    // GorillaPP_grammar.g:913:1: macro_expression :{...}? ID '(' conditional_expression ( ',' conditional_expression )* ')' ;
    public final void macro_expression() throws RecognitionException {
        int macro_expression_StartIndex = input.index();

        Token ID7=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return ; }

            // GorillaPP_grammar.g:914:2: ({...}? ID '(' conditional_expression ( ',' conditional_expression )* ')' )
            // GorillaPP_grammar.g:914:5: {...}? ID '(' conditional_expression ( ',' conditional_expression )* ')'
            {
            if ( !((!((Symbols_scope)Symbols_stack.peek()).offloadPorts.contains(input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "macro_expression", "!$Symbols::offloadPorts.contains(input.LT(1).getText())");
            }

            ID7=(Token)match(input,ID,FOLLOW_ID_in_macro_expression1071); if (state.failed) return ;

            if ( state.backtracking==0 ) {outString((ID7!=null?ID7.getText():null));}

            match(input,31,FOLLOW_31_in_macro_expression1074); if (state.failed) return ;

            if ( state.backtracking==0 ) {outString("(");}

            pushFollow(FOLLOW_conditional_expression_in_macro_expression1089);
            conditional_expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:916:35: ( ',' conditional_expression )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==36) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // GorillaPP_grammar.g:916:36: ',' conditional_expression
            	    {
            	    match(input,36,FOLLOW_36_in_macro_expression1092); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString(",");}

            	    pushFollow(FOLLOW_conditional_expression_in_macro_expression1107);
            	    conditional_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            match(input,32,FOLLOW_32_in_macro_expression1110); if (state.failed) return ;

            if ( state.backtracking==0 ) {outString(")");}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 31, macro_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "macro_expression"



    // $ANTLR start "labeled_statement"
    // GorillaPP_grammar.g:920:1: labeled_statement : ( ID ':' statement | 'case' constant_expression ':' statement | 'default' ':' statement );
    public final void labeled_statement() throws RecognitionException {
        int labeled_statement_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return ; }

            // GorillaPP_grammar.g:921:2: ( ID ':' statement | 'case' constant_expression ':' statement | 'default' ':' statement )
            int alt33=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt33=1;
                }
                break;
            case 61:
                {
                alt33=2;
                }
                break;
            case 64:
                {
                alt33=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;

            }

            switch (alt33) {
                case 1 :
                    // GorillaPP_grammar.g:921:4: ID ':' statement
                    {
                    match(input,ID,FOLLOW_ID_in_labeled_statement1136); if (state.failed) return ;

                    match(input,41,FOLLOW_41_in_labeled_statement1138); if (state.failed) return ;

                    pushFollow(FOLLOW_statement_in_labeled_statement1140);
                    statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:922:4: 'case' constant_expression ':' statement
                    {
                    match(input,61,FOLLOW_61_in_labeled_statement1145); if (state.failed) return ;

                    pushFollow(FOLLOW_constant_expression_in_labeled_statement1148);
                    constant_expression();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,41,FOLLOW_41_in_labeled_statement1150); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString(": begin\n");}

                    pushFollow(FOLLOW_statement_in_labeled_statement1189);
                    statement();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString("end\n");}

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:926:4: 'default' ':' statement
                    {
                    match(input,64,FOLLOW_64_in_labeled_statement1196); if (state.failed) return ;

                    match(input,41,FOLLOW_41_in_labeled_statement1198); if (state.failed) return ;

                    pushFollow(FOLLOW_statement_in_labeled_statement1200);
                    statement();

                    state._fsp--;
                    if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 32, labeled_statement_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "labeled_statement"



    // $ANTLR start "expression_statement"
    // GorillaPP_grammar.g:929:1: expression_statement : ( SEMICOLON | expression SEMICOLON );
    public final void expression_statement() throws RecognitionException {
        int expression_statement_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return ; }

            // GorillaPP_grammar.g:930:2: ( SEMICOLON | expression SEMICOLON )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==SEMICOLON) ) {
                alt34=1;
            }
            else if ( (LA34_0==ID) ) {
                alt34=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;

            }
            switch (alt34) {
                case 1 :
                    // GorillaPP_grammar.g:930:4: SEMICOLON
                    {
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_expression_statement1211); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:930:16: expression SEMICOLON
                    {
                    pushFollow(FOLLOW_expression_in_expression_statement1215);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_expression_statement1217); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                                outString(" \n"); 
                    	    ((Symbols_scope)Symbols_stack.peek()).localLvalue = false;
                                ((Symbols_scope)Symbols_stack.peek()).isCasted = false;
                    	  }

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
            if ( state.backtracking>0 ) { memoize(input, 33, expression_statement_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "expression_statement"



    // $ANTLR start "selection_statement"
    // GorillaPP_grammar.g:937:1: selection_statement : 'if' '(' conditional_expression ')' statement ( options {k=1; backtrack=false; } : 'else' statement )? ;
    public final void selection_statement() throws RecognitionException {
        int selection_statement_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return ; }

            // GorillaPP_grammar.g:938:2: ( 'if' '(' conditional_expression ')' statement ( options {k=1; backtrack=false; } : 'else' statement )? )
            // GorillaPP_grammar.g:938:4: 'if' '(' conditional_expression ')' statement ( options {k=1; backtrack=false; } : 'else' statement )?
            {
            match(input,69,FOLLOW_69_in_selection_statement1237); if (state.failed) return ;

            match(input,31,FOLLOW_31_in_selection_statement1240); if (state.failed) return ;

            if ( state.backtracking==0 ) {outString("when (");}

            if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).isPredicate = true;}

            pushFollow(FOLLOW_conditional_expression_in_selection_statement1267);
            conditional_expression();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {((Symbols_scope)Symbols_stack.peek()).isPredicate = false;}

            match(input,32,FOLLOW_32_in_selection_statement1294); if (state.failed) return ;

            if ( state.backtracking==0 ) {outString(") {\n");}

            pushFollow(FOLLOW_statement_in_selection_statement1309);
            statement();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {outString("}\n");}

            // GorillaPP_grammar.g:944:11: ( options {k=1; backtrack=false; } : 'else' statement )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==65) ) {
                int LA35_1 = input.LA(2);

                if ( (true) ) {
                    alt35=1;
                }
            }
            switch (alt35) {
                case 1 :
                    // GorillaPP_grammar.g:945:10: 'else' statement
                    {
                    match(input,65,FOLLOW_65_in_selection_statement1347); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString(".otherwise {");}

                    if ( state.backtracking==0 ) {outString("\n");}

                    pushFollow(FOLLOW_statement_in_selection_statement1363);
                    statement();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString("}\n");}

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
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 34, selection_statement_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "selection_statement"



    // $ANTLR start "finish_statement"
    // GorillaPP_grammar.g:951:1: finish_statement : ( 'finish' '(' ')' SEMICOLON | 'emit' '(' (emitTarget= ID )? ')' SEMICOLON );
    public final void finish_statement() throws RecognitionException {
        int finish_statement_StartIndex = input.index();

        Token emitTarget=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return ; }

            // GorillaPP_grammar.g:952:5: ( 'finish' '(' ')' SEMICOLON | 'emit' '(' (emitTarget= ID )? ')' SEMICOLON )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==68) ) {
                alt37=1;
            }
            else if ( (LA37_0==66) ) {
                alt37=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;

            }
            switch (alt37) {
                case 1 :
                    // GorillaPP_grammar.g:952:7: 'finish' '(' ')' SEMICOLON
                    {
                    match(input,68,FOLLOW_68_in_finish_statement1394); if (state.failed) return ;

                    match(input,31,FOLLOW_31_in_finish_statement1396); if (state.failed) return ;

                    match(input,32,FOLLOW_32_in_finish_statement1398); if (state.failed) return ;

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_finish_statement1400); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                          outString("EmitReturnState(vThread) := WaitForInputValid\n"); 
                          outString("State(vThread) := WaitForOutputReady\n");
                        }

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:955:9: 'emit' '(' (emitTarget= ID )? ')' SEMICOLON
                    {
                    match(input,66,FOLLOW_66_in_finish_statement1406); if (state.failed) return ;

                    match(input,31,FOLLOW_31_in_finish_statement1408); if (state.failed) return ;

                    // GorillaPP_grammar.g:955:20: (emitTarget= ID )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==ID) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // GorillaPP_grammar.g:955:21: emitTarget= ID
                            {
                            emitTarget=(Token)match(input,ID,FOLLOW_ID_in_finish_statement1415); if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,32,FOLLOW_32_in_finish_statement1419); if (state.failed) return ;

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_finish_statement1421); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                          outString("State(vThread) := WaitForOutputReady\n");
                        }

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
            if ( state.backtracking>0 ) { memoize(input, 35, finish_statement_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "finish_statement"



    // $ANTLR start "variablelist"
    // GorillaPP_grammar.g:962:1: variablelist : variable_in_def ( ',' variable_in_def )* ;
    public final void variablelist() throws RecognitionException {
        int variablelist_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return ; }

            // GorillaPP_grammar.g:963:2: ( variable_in_def ( ',' variable_in_def )* )
            // GorillaPP_grammar.g:963:4: variable_in_def ( ',' variable_in_def )*
            {
            pushFollow(FOLLOW_variable_in_def_in_variablelist1440);
            variable_in_def();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:963:20: ( ',' variable_in_def )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==36) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // GorillaPP_grammar.g:963:22: ',' variable_in_def
            	    {
            	    match(input,36,FOLLOW_36_in_variablelist1444); if (state.failed) return ;

            	    pushFollow(FOLLOW_variable_in_def_in_variablelist1446);
            	    variable_in_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop38;
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
            if ( state.backtracking>0 ) { memoize(input, 36, variablelist_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "variablelist"



    // $ANTLR start "variable_in_def"
    // GorillaPP_grammar.g:965:1: variable_in_def : variable ;
    public final void variable_in_def() throws RecognitionException {
        int variable_in_def_StartIndex = input.index();

        GorillaPP_grammarParser.variable_return variable8 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return ; }

            // GorillaPP_grammar.g:966:2: ( variable )
            // GorillaPP_grammar.g:966:4: variable
            {
            pushFollow(FOLLOW_variable_in_variable_in_def1459);
            variable8=variable();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                        if(!((Symbols_scope)Symbols_stack.peek()).isLocal && !((Symbols_scope)Symbols_stack.peek()).isHeaderFile) {
                          if(globalVarDefined((variable8!=null?input.toString(variable8.start,variable8.stop):null))) {
                            System.out.println("Redundant definition " +
                            "of global variable");
                          } else {
                            ((Symbols_scope)Symbols_stack.peek()).globals.add((variable8!=null?input.toString(variable8.start,variable8.stop):null));
                            ((Symbols_scope)Symbols_stack.peek()).currentGlobals.add((variable8!=null?input.toString(variable8.start,variable8.stop):null));
                          }
                        } else if (!((Symbols_scope)Symbols_stack.peek()).isHeaderFile){
                          ((Symbols_scope)Symbols_stack.peek()).localsType.put((variable8!=null?input.toString(variable8.start,variable8.stop):null), 
                           ((Symbols_scope)Symbols_stack.peek()).typeName);
                          ((Symbols_scope)Symbols_stack.peek()).locals.add((variable8!=null?input.toString(variable8.start,variable8.stop):null));
                          //TODO: Redundancy check for local variable - 
                          //Also, adding instruction name to variable
                          //to make a new scope in each instruction.  
                          //if simple local variable, you dont need to 
                          //declare it in chisel instead you should declare 
                          //it at the first def point. If a bundle variable 
                          //however we declare it here and later we dont 
                          //define it at first def site

                          if (c2ChiselType(((Symbols_scope)Symbols_stack.peek()).typeName).contains("new")) {
                            //It is a bundle
                            ((Symbols_scope)Symbols_stack.peek()).definitionString += 
                             ("val " + (variable8!=null?input.toString(variable8.start,variable8.stop):null) + " = new " + 
                             ((Symbols_scope)Symbols_stack.peek()).typeName + "\n");
                          }  
                        } else if (((Symbols_scope)Symbols_stack.peek()).isHeaderFile) {
            	      ((Symbols_scope)Symbols_stack.peek()).definitionString +=  
                           "val " + (variable8!=null?input.toString(variable8.start,variable8.stop):null) + " = " + 
                           c2ChiselType(((Symbols_scope)Symbols_stack.peek()).typeName) + "\n";
                        }
            	}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 37, variable_in_def_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "variable_in_def"


    public static class variable_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "variable"
    // GorillaPP_grammar.g:1001:1: variable : ID ;
    public final GorillaPP_grammarParser.variable_return variable() throws RecognitionException {
        GorillaPP_grammarParser.variable_return retval = new GorillaPP_grammarParser.variable_return();
        retval.start = input.LT(1);

        int variable_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }

            // GorillaPP_grammar.g:1002:5: ( ID )
            // GorillaPP_grammar.g:1002:7: ID
            {
            match(input,ID,FOLLOW_ID_in_variable1474); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 38, variable_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "variable"


    public static class type_name_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "type_name"
    // GorillaPP_grammar.g:1005:1: type_name : specifier_qualifier_list ;
    public final GorillaPP_grammarParser.type_name_return type_name() throws RecognitionException {
        GorillaPP_grammarParser.type_name_return retval = new GorillaPP_grammarParser.type_name_return();
        retval.start = input.LT(1);

        int type_name_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }

            // GorillaPP_grammar.g:1006:2: ( specifier_qualifier_list )
            // GorillaPP_grammar.g:1006:4: specifier_qualifier_list
            {
            pushFollow(FOLLOW_specifier_qualifier_list_in_type_name1488);
            specifier_qualifier_list();

            state._fsp--;
            if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 39, type_name_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "type_name"



    // $ANTLR start "specifier_qualifier_list"
    // GorillaPP_grammar.g:1010:1: specifier_qualifier_list : ( type_specifier )+ ;
    public final void specifier_qualifier_list() throws RecognitionException {
        int specifier_qualifier_list_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return ; }

            // GorillaPP_grammar.g:1011:2: ( ( type_specifier )+ )
            // GorillaPP_grammar.g:1011:4: ( type_specifier )+
            {
            // GorillaPP_grammar.g:1011:4: ( type_specifier )+
            int cnt39=0;
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==ID||LA39_0==62||LA39_0==67||(LA39_0 >= 70 && LA39_0 <= 72)||LA39_0==75) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // GorillaPP_grammar.g:1011:6: type_specifier
            	    {
            	    pushFollow(FOLLOW_type_specifier_in_specifier_qualifier_list1503);
            	    type_specifier();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt39 >= 1 ) break loop39;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(39, input);
                        throw eee;
                }
                cnt39++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 40, specifier_qualifier_list_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "specifier_qualifier_list"



    // $ANTLR start "type_specifier_struct"
    // GorillaPP_grammar.g:1014:1: type_specifier_struct : ( type_specifier | 'struct {' ( type_specifier variablelist ';' )+ '}' );
    public final void type_specifier_struct() throws RecognitionException {
        int type_specifier_struct_StartIndex = input.index();

        GorillaPP_grammarParser.type_specifier_return type_specifier9 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return ; }

            // GorillaPP_grammar.g:1014:22: ( type_specifier | 'struct {' ( type_specifier variablelist ';' )+ '}' )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==ID||LA41_0==62||LA41_0==67||(LA41_0 >= 70 && LA41_0 <= 72)||LA41_0==75) ) {
                alt41=1;
            }
            else if ( (LA41_0==73) ) {
                alt41=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }
            switch (alt41) {
                case 1 :
                    // GorillaPP_grammar.g:1015:5: type_specifier
                    {
                    pushFollow(FOLLOW_type_specifier_in_type_specifier_struct1519);
                    type_specifier();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1016:7: 'struct {' ( type_specifier variablelist ';' )+ '}'
                    {
                    match(input,73,FOLLOW_73_in_type_specifier_struct1529); if (state.failed) return ;

                    // GorillaPP_grammar.g:1016:18: ( type_specifier variablelist ';' )+
                    int cnt40=0;
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==ID||LA40_0==62||LA40_0==67||(LA40_0 >= 70 && LA40_0 <= 72)||LA40_0==75) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // GorillaPP_grammar.g:1016:19: type_specifier variablelist ';'
                    	    {
                    	    pushFollow(FOLLOW_type_specifier_in_type_specifier_struct1532);
                    	    type_specifier9=type_specifier();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    if ( state.backtracking==0 ) {
                    	          ((Symbols_scope)Symbols_stack.peek()).typeName = (type_specifier9!=null?input.toString(type_specifier9.start,type_specifier9.stop):null);
                    	          /*((Symbols_scope)Symbols_stack.peek()).definitionString += 
                    	          (type_specifier9!=null?input.toString(type_specifier9.start,type_specifier9.stop):null);*/}

                    	    pushFollow(FOLLOW_variablelist_in_type_specifier_struct1541);
                    	    variablelist();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_type_specifier_struct1543); if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt40 >= 1 ) break loop40;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(40, input);
                                throw eee;
                        }
                        cnt40++;
                    } while (true);


                    match(input,79,FOLLOW_79_in_type_specifier_struct1547); if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 41, type_specifier_struct_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "type_specifier_struct"


    public static class type_specifier_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "type_specifier"
    // GorillaPP_grammar.g:1022:1: type_specifier : ( 'char' | 'short' | 'int' | 'long' | 'unsigned' | enum_specifier | type_id );
    public final GorillaPP_grammarParser.type_specifier_return type_specifier() throws RecognitionException {
        GorillaPP_grammarParser.type_specifier_return retval = new GorillaPP_grammarParser.type_specifier_return();
        retval.start = input.LT(1);

        int type_specifier_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }

            // GorillaPP_grammar.g:1023:2: ( 'char' | 'short' | 'int' | 'long' | 'unsigned' | enum_specifier | type_id )
            int alt42=7;
            switch ( input.LA(1) ) {
            case 62:
                {
                alt42=1;
                }
                break;
            case 72:
                {
                alt42=2;
                }
                break;
            case 70:
                {
                alt42=3;
                }
                break;
            case 71:
                {
                alt42=4;
                }
                break;
            case 75:
                {
                alt42=5;
                }
                break;
            case 67:
                {
                alt42=6;
                }
                break;
            case ID:
                {
                alt42=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }

            switch (alt42) {
                case 1 :
                    // GorillaPP_grammar.g:1024:3: 'char'
                    {
                    match(input,62,FOLLOW_62_in_type_specifier1563); if (state.failed) return retval;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1025:4: 'short'
                    {
                    match(input,72,FOLLOW_72_in_type_specifier1568); if (state.failed) return retval;

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:1026:4: 'int'
                    {
                    match(input,70,FOLLOW_70_in_type_specifier1573); if (state.failed) return retval;

                    }
                    break;
                case 4 :
                    // GorillaPP_grammar.g:1028:4: 'long'
                    {
                    match(input,71,FOLLOW_71_in_type_specifier1581); if (state.failed) return retval;

                    }
                    break;
                case 5 :
                    // GorillaPP_grammar.g:1029:4: 'unsigned'
                    {
                    match(input,75,FOLLOW_75_in_type_specifier1586); if (state.failed) return retval;

                    }
                    break;
                case 6 :
                    // GorillaPP_grammar.g:1030:4: enum_specifier
                    {
                    pushFollow(FOLLOW_enum_specifier_in_type_specifier1591);
                    enum_specifier();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;
                case 7 :
                    // GorillaPP_grammar.g:1031:4: type_id
                    {
                    pushFollow(FOLLOW_type_id_in_type_specifier1596);
                    type_id();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 42, type_specifier_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "type_specifier"



    // $ANTLR start "type_id"
    // GorillaPP_grammar.g:1034:1: type_id : ID ;
    public final void type_id() throws RecognitionException {
        int type_id_StartIndex = input.index();

        Token ID10=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return ; }

            // GorillaPP_grammar.g:1035:5: ( ID )
            // GorillaPP_grammar.g:1035:50: ID
            {
            ID10=(Token)match(input,ID,FOLLOW_ID_in_type_id1614); if (state.failed) return ;

            if ( state.backtracking==0 ) {System.out.println("//"+(ID10!=null?ID10.getText():null)+" is a type");}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 43, type_id_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "type_id"



    // $ANTLR start "abstract_declarator"
    // GorillaPP_grammar.g:1042:1: abstract_declarator : direct_abstract_declarator ;
    public final void abstract_declarator() throws RecognitionException {
        int abstract_declarator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return ; }

            // GorillaPP_grammar.g:1043:2: ( direct_abstract_declarator )
            // GorillaPP_grammar.g:1043:4: direct_abstract_declarator
            {
            pushFollow(FOLLOW_direct_abstract_declarator_in_abstract_declarator1661);
            direct_abstract_declarator();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 44, abstract_declarator_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "abstract_declarator"



    // $ANTLR start "direct_abstract_declarator"
    // GorillaPP_grammar.g:1046:1: direct_abstract_declarator : ( '(' abstract_declarator ')' | abstract_declarator_suffix ) ( abstract_declarator_suffix )* ;
    public final void direct_abstract_declarator() throws RecognitionException {
        int direct_abstract_declarator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return ; }

            // GorillaPP_grammar.g:1047:2: ( ( '(' abstract_declarator ')' | abstract_declarator_suffix ) ( abstract_declarator_suffix )* )
            // GorillaPP_grammar.g:1047:5: ( '(' abstract_declarator ')' | abstract_declarator_suffix ) ( abstract_declarator_suffix )*
            {
            // GorillaPP_grammar.g:1047:5: ( '(' abstract_declarator ')' | abstract_declarator_suffix )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==31) ) {
                int LA43_1 = input.LA(2);

                if ( (LA43_1==32) ) {
                    alt43=2;
                }
                else if ( (LA43_1==31||LA43_1==56) ) {
                    alt43=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 43, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA43_0==56) ) {
                alt43=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;

            }
            switch (alt43) {
                case 1 :
                    // GorillaPP_grammar.g:1047:7: '(' abstract_declarator ')'
                    {
                    match(input,31,FOLLOW_31_in_direct_abstract_declarator1675); if (state.failed) return ;

                    pushFollow(FOLLOW_abstract_declarator_in_direct_abstract_declarator1677);
                    abstract_declarator();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,32,FOLLOW_32_in_direct_abstract_declarator1679); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1048:12: abstract_declarator_suffix
                    {
                    pushFollow(FOLLOW_abstract_declarator_suffix_in_direct_abstract_declarator1695);
                    abstract_declarator_suffix();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // GorillaPP_grammar.g:1049:12: ( abstract_declarator_suffix )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==31||LA44_0==56) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // GorillaPP_grammar.g:1049:12: abstract_declarator_suffix
            	    {
            	    pushFollow(FOLLOW_abstract_declarator_suffix_in_direct_abstract_declarator1711);
            	    abstract_declarator_suffix();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop44;
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
            if ( state.backtracking>0 ) { memoize(input, 45, direct_abstract_declarator_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "direct_abstract_declarator"



    // $ANTLR start "abstract_declarator_suffix"
    // GorillaPP_grammar.g:1052:1: abstract_declarator_suffix : ( '[' ']' | '[' constant_expression ']' | '(' ')' );
    public final void abstract_declarator_suffix() throws RecognitionException {
        int abstract_declarator_suffix_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return ; }

            // GorillaPP_grammar.g:1053:2: ( '[' ']' | '[' constant_expression ']' | '(' ')' )
            int alt45=3;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==56) ) {
                int LA45_1 = input.LA(2);

                if ( (LA45_1==57) ) {
                    alt45=1;
                }
                else if ( (LA45_1==BINARY_LITERAL||LA45_1==CHARACTER_LITERAL||LA45_1==DECIMAL_LITERAL||(LA45_1 >= GORILLA_INSTR_NAME && LA45_1 <= HEX_LITERAL)||LA45_1==ID||LA45_1==OCTAL_LITERAL||LA45_1==STRING_LITERAL||LA45_1==24||(LA45_1 >= 30 && LA45_1 <= 31)||(LA45_1 >= 33 && LA45_1 <= 35)||(LA45_1 >= 37 && LA45_1 <= 38)||LA45_1==80) ) {
                    alt45=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 45, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA45_0==31) ) {
                alt45=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }
            switch (alt45) {
                case 1 :
                    // GorillaPP_grammar.g:1053:5: '[' ']'
                    {
                    match(input,56,FOLLOW_56_in_abstract_declarator_suffix1724); if (state.failed) return ;

                    match(input,57,FOLLOW_57_in_abstract_declarator_suffix1726); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1054:5: '[' constant_expression ']'
                    {
                    match(input,56,FOLLOW_56_in_abstract_declarator_suffix1732); if (state.failed) return ;

                    pushFollow(FOLLOW_constant_expression_in_abstract_declarator_suffix1734);
                    constant_expression();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,57,FOLLOW_57_in_abstract_declarator_suffix1736); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:1055:5: '(' ')'
                    {
                    match(input,31,FOLLOW_31_in_abstract_declarator_suffix1742); if (state.failed) return ;

                    match(input,32,FOLLOW_32_in_abstract_declarator_suffix1744); if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 46, abstract_declarator_suffix_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "abstract_declarator_suffix"



    // $ANTLR start "enum_specifier"
    // GorillaPP_grammar.g:1057:1: enum_specifier options {k=3; } : ( 'enum' '{' enumerator_list '}' | 'enum' ID '{' enumerator_list '}' | 'enum' ID );
    public final void enum_specifier() throws RecognitionException {
        int enum_specifier_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return ; }

            // GorillaPP_grammar.g:1059:2: ( 'enum' '{' enumerator_list '}' | 'enum' ID '{' enumerator_list '}' | 'enum' ID )
            int alt46=3;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==67) ) {
                int LA46_1 = input.LA(2);

                if ( (LA46_1==76) ) {
                    alt46=1;
                }
                else if ( (LA46_1==ID) ) {
                    int LA46_3 = input.LA(3);

                    if ( (LA46_3==76) ) {
                        alt46=2;
                    }
                    else if ( (LA46_3==EOF||LA46_3==ID||(LA46_3 >= 31 && LA46_3 <= 32)||LA46_3==62||LA46_3==67||(LA46_3 >= 70 && LA46_3 <= 72)||LA46_3==75) ) {
                        alt46=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 46, 3, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 46, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }
            switch (alt46) {
                case 1 :
                    // GorillaPP_grammar.g:1059:4: 'enum' '{' enumerator_list '}'
                    {
                    match(input,67,FOLLOW_67_in_enum_specifier1762); if (state.failed) return ;

                    match(input,76,FOLLOW_76_in_enum_specifier1764); if (state.failed) return ;

                    pushFollow(FOLLOW_enumerator_list_in_enum_specifier1766);
                    enumerator_list();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,79,FOLLOW_79_in_enum_specifier1768); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1060:4: 'enum' ID '{' enumerator_list '}'
                    {
                    match(input,67,FOLLOW_67_in_enum_specifier1773); if (state.failed) return ;

                    match(input,ID,FOLLOW_ID_in_enum_specifier1775); if (state.failed) return ;

                    match(input,76,FOLLOW_76_in_enum_specifier1777); if (state.failed) return ;

                    pushFollow(FOLLOW_enumerator_list_in_enum_specifier1779);
                    enumerator_list();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,79,FOLLOW_79_in_enum_specifier1781); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:1061:4: 'enum' ID
                    {
                    match(input,67,FOLLOW_67_in_enum_specifier1786); if (state.failed) return ;

                    match(input,ID,FOLLOW_ID_in_enum_specifier1788); if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 47, enum_specifier_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "enum_specifier"



    // $ANTLR start "enumerator_list"
    // GorillaPP_grammar.g:1064:1: enumerator_list : enumerator ( ',' enumerator )* ;
    public final void enumerator_list() throws RecognitionException {
        int enumerator_list_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return ; }

            // GorillaPP_grammar.g:1065:2: ( enumerator ( ',' enumerator )* )
            // GorillaPP_grammar.g:1065:4: enumerator ( ',' enumerator )*
            {
            pushFollow(FOLLOW_enumerator_in_enumerator_list1799);
            enumerator();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1065:15: ( ',' enumerator )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==36) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // GorillaPP_grammar.g:1065:16: ',' enumerator
            	    {
            	    match(input,36,FOLLOW_36_in_enumerator_list1802); if (state.failed) return ;

            	    pushFollow(FOLLOW_enumerator_in_enumerator_list1804);
            	    enumerator();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop47;
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
            if ( state.backtracking>0 ) { memoize(input, 48, enumerator_list_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "enumerator_list"



    // $ANTLR start "enumerator"
    // GorillaPP_grammar.g:1068:1: enumerator : ID ( '=' constant_expression )? ;
    public final void enumerator() throws RecognitionException {
        int enumerator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return ; }

            // GorillaPP_grammar.g:1069:2: ( ID ( '=' constant_expression )? )
            // GorillaPP_grammar.g:1069:4: ID ( '=' constant_expression )?
            {
            match(input,ID,FOLLOW_ID_in_enumerator1817); if (state.failed) return ;

            // GorillaPP_grammar.g:1069:7: ( '=' constant_expression )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==45) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // GorillaPP_grammar.g:1069:8: '=' constant_expression
                    {
                    match(input,45,FOLLOW_45_in_enumerator1820); if (state.failed) return ;

                    pushFollow(FOLLOW_constant_expression_in_enumerator1822);
                    constant_expression();

                    state._fsp--;
                    if (state.failed) return ;

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
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 49, enumerator_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "enumerator"



    // $ANTLR start "argument_expression_list"
    // GorillaPP_grammar.g:1072:1: argument_expression_list : assignment_expression ( ',' assignment_expression )* ;
    public final void argument_expression_list() throws RecognitionException {
        int argument_expression_list_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return ; }

            // GorillaPP_grammar.g:1073:2: ( assignment_expression ( ',' assignment_expression )* )
            // GorillaPP_grammar.g:1073:6: assignment_expression ( ',' assignment_expression )*
            {
            pushFollow(FOLLOW_assignment_expression_in_argument_expression_list1837);
            assignment_expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1073:28: ( ',' assignment_expression )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==36) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // GorillaPP_grammar.g:1073:29: ',' assignment_expression
            	    {
            	    match(input,36,FOLLOW_36_in_argument_expression_list1840); if (state.failed) return ;

            	    pushFollow(FOLLOW_assignment_expression_in_argument_expression_list1842);
            	    assignment_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop49;
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
            if ( state.backtracking>0 ) { memoize(input, 50, argument_expression_list_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "argument_expression_list"



    // $ANTLR start "expression"
    // GorillaPP_grammar.g:1076:1: expression : assignment_expression ;
    public final void expression() throws RecognitionException {
        int expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return ; }

            // GorillaPP_grammar.g:1079:2: ( assignment_expression )
            // GorillaPP_grammar.g:1079:5: assignment_expression
            {
            pushFollow(FOLLOW_assignment_expression_in_expression1858);
            assignment_expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 51, expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "expression"



    // $ANTLR start "concat_expression"
    // GorillaPP_grammar.g:1081:1: concat_expression : '{' expression ( ',' expression )* '}' ;
    public final void concat_expression() throws RecognitionException {
        int concat_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return ; }

            // GorillaPP_grammar.g:1082:2: ( '{' expression ( ',' expression )* '}' )
            // GorillaPP_grammar.g:1082:4: '{' expression ( ',' expression )* '}'
            {
            match(input,76,FOLLOW_76_in_concat_expression1874); if (state.failed) return ;

            if ( state.backtracking==0 ) {outString("{");}

            pushFollow(FOLLOW_expression_in_concat_expression1888);
            expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1083:22: ( ',' expression )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==36) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // GorillaPP_grammar.g:1083:23: ',' expression
            	    {
            	    match(input,36,FOLLOW_36_in_concat_expression1891); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString(",");}

            	    pushFollow(FOLLOW_expression_in_concat_expression1894);
            	    expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);


            match(input,79,FOLLOW_79_in_concat_expression1898); if (state.failed) return ;

            if ( state.backtracking==0 ) {outString("}");}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 52, concat_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "concat_expression"



    // $ANTLR start "assignment_expression"
    // GorillaPP_grammar.g:1087:1: assignment_expression : lValueId= ID fieldPostfix assignment_operator ( sel_expression | conditional_expression | macro_expression ) ;
    public final void assignment_expression() throws RecognitionException {
        int assignment_expression_StartIndex = input.index();

        Token lValueId=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return ; }

            // GorillaPP_grammar.g:1088:2: (lValueId= ID fieldPostfix assignment_operator ( sel_expression | conditional_expression | macro_expression ) )
            // GorillaPP_grammar.g:1088:4: lValueId= ID fieldPostfix assignment_operator ( sel_expression | conditional_expression | macro_expression )
            {
            lValueId=(Token)match(input,ID,FOLLOW_ID_in_assignment_expression1927); if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    if (localVarDefined((lValueId!=null?lValueId.getText():null))) {
                          ((Symbols_scope)Symbols_stack.peek()).localsVersion.put((lValueId!=null?lValueId.getText():null), 0);
                          if (c2ChiselType((String)
                           ((Symbols_scope)Symbols_stack.peek()).localsType.get(
                           (lValueId!=null?lValueId.getText():null))).contains("new")) {
                            ((Symbols_scope)Symbols_stack.peek()).localLvalue = true;
                            ((Symbols_scope)Symbols_stack.peek()).colonAssign = true;
                            outString((lValueId!=null?lValueId.getText():null));
                          } else {
                            ((Symbols_scope)Symbols_stack.peek()).localLvalue = true;
                            ((Symbols_scope)Symbols_stack.peek()).colonAssign = false;
                            outString("val " + (lValueId!=null?lValueId.getText():null));
                          } 
                       } else {
                         String idPrint;
                         if ((lValueId!=null?lValueId.getText():null).equals("Output")){
                           idPrint = "outputReg(vThread)";
                         } else {
                           idPrint = (lValueId!=null?lValueId.getText():null) + "(vThread)";
                         }
            	     ((Symbols_scope)Symbols_stack.peek()).colonAssign = true;
            	     outString(idPrint);
                      }
                    }

            pushFollow(FOLLOW_fieldPostfix_in_assignment_expression1931);
            fieldPostfix();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_assignment_operator_in_assignment_expression1933);
            assignment_operator();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1113:9: ( sel_expression | conditional_expression | macro_expression )
            int alt51=3;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // GorillaPP_grammar.g:1113:10: sel_expression
                    {
                    pushFollow(FOLLOW_sel_expression_in_assignment_expression1945);
                    sel_expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1113:28: conditional_expression
                    {
                    pushFollow(FOLLOW_conditional_expression_in_assignment_expression1950);
                    conditional_expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:1113:53: macro_expression
                    {
                    pushFollow(FOLLOW_macro_expression_in_assignment_expression1954);
                    macro_expression();

                    state._fsp--;
                    if (state.failed) return ;

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
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 53, assignment_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "assignment_expression"



    // $ANTLR start "sel_expression"
    // GorillaPP_grammar.g:1115:1: sel_expression : conditional_expression '?' conditional_expression ':' conditional_expression ;
    public final void sel_expression() throws RecognitionException {
        int sel_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return ; }

            // GorillaPP_grammar.g:1115:15: ( conditional_expression '?' conditional_expression ':' conditional_expression )
            // GorillaPP_grammar.g:1115:17: conditional_expression '?' conditional_expression ':' conditional_expression
            {
            if ( state.backtracking==0 ) {outString(" Mux(");}

            pushFollow(FOLLOW_conditional_expression_in_sel_expression1988);
            conditional_expression();

            state._fsp--;
            if (state.failed) return ;

            match(input,50,FOLLOW_50_in_sel_expression1990); if (state.failed) return ;

            if ( state.backtracking==0 ) {outString(",");}

            pushFollow(FOLLOW_conditional_expression_in_sel_expression2011);
            conditional_expression();

            state._fsp--;
            if (state.failed) return ;

            match(input,41,FOLLOW_41_in_sel_expression2013); if (state.failed) return ;

            if ( state.backtracking==0 ) {outString(",");}

            pushFollow(FOLLOW_conditional_expression_in_sel_expression2034);
            conditional_expression();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {outString(")");}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 54, sel_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "sel_expression"



    // $ANTLR start "lvalue"
    // GorillaPP_grammar.g:1122:1: lvalue : unary_expression ;
    public final void lvalue() throws RecognitionException {
        int lvalue_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return ; }

            // GorillaPP_grammar.g:1123:2: ( unary_expression )
            // GorillaPP_grammar.g:1123:4: unary_expression
            {
            pushFollow(FOLLOW_unary_expression_in_lvalue2070);
            unary_expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 55, lvalue_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "lvalue"



    // $ANTLR start "constant_expression"
    // GorillaPP_grammar.g:1125:1: constant_expression : conditional_expression ;
    public final void constant_expression() throws RecognitionException {
        int constant_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return ; }

            // GorillaPP_grammar.g:1126:2: ( conditional_expression )
            // GorillaPP_grammar.g:1126:4: conditional_expression
            {
            pushFollow(FOLLOW_conditional_expression_in_constant_expression2080);
            conditional_expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 56, constant_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "constant_expression"



    // $ANTLR start "conditional_expression"
    // GorillaPP_grammar.g:1130:1: conditional_expression : logical_or_expression ;
    public final void conditional_expression() throws RecognitionException {
        int conditional_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return ; }

            // GorillaPP_grammar.g:1131:2: ( logical_or_expression )
            // GorillaPP_grammar.g:1131:4: logical_or_expression
            {
            pushFollow(FOLLOW_logical_or_expression_in_conditional_expression2092);
            logical_or_expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 57, conditional_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "conditional_expression"



    // $ANTLR start "logical_or_expression"
    // GorillaPP_grammar.g:1135:1: logical_or_expression : logical_and_expression ( '||' logical_and_expression )* ;
    public final void logical_or_expression() throws RecognitionException {
        int logical_or_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return ; }

            // GorillaPP_grammar.g:1136:2: ( logical_and_expression ( '||' logical_and_expression )* )
            // GorillaPP_grammar.g:1136:4: logical_and_expression ( '||' logical_and_expression )*
            {
            pushFollow(FOLLOW_logical_and_expression_in_logical_or_expression2125);
            logical_and_expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1136:27: ( '||' logical_and_expression )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==78) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // GorillaPP_grammar.g:1136:28: '||' logical_and_expression
            	    {
            	    match(input,78,FOLLOW_78_in_logical_or_expression2128); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString("||");}

            	    pushFollow(FOLLOW_logical_and_expression_in_logical_or_expression2143);
            	    logical_and_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop52;
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
            if ( state.backtracking>0 ) { memoize(input, 58, logical_or_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "logical_or_expression"



    // $ANTLR start "logical_and_expression"
    // GorillaPP_grammar.g:1140:1: logical_and_expression : inclusive_or_expression ( '&&' inclusive_or_expression )* ;
    public final void logical_and_expression() throws RecognitionException {
        int logical_and_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return ; }

            // GorillaPP_grammar.g:1141:2: ( inclusive_or_expression ( '&&' inclusive_or_expression )* )
            // GorillaPP_grammar.g:1141:4: inclusive_or_expression ( '&&' inclusive_or_expression )*
            {
            pushFollow(FOLLOW_inclusive_or_expression_in_logical_and_expression2156);
            inclusive_or_expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1141:28: ( '&&' inclusive_or_expression )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==29) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // GorillaPP_grammar.g:1141:29: '&&' inclusive_or_expression
            	    {
            	    match(input,29,FOLLOW_29_in_logical_and_expression2159); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString("&&");}

            	    pushFollow(FOLLOW_inclusive_or_expression_in_logical_and_expression2174);
            	    inclusive_or_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop53;
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
            if ( state.backtracking>0 ) { memoize(input, 59, logical_and_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "logical_and_expression"



    // $ANTLR start "inclusive_or_expression"
    // GorillaPP_grammar.g:1145:1: inclusive_or_expression : exclusive_or_expression ( '|' exclusive_or_expression )* ;
    public final void inclusive_or_expression() throws RecognitionException {
        int inclusive_or_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return ; }

            // GorillaPP_grammar.g:1146:2: ( exclusive_or_expression ( '|' exclusive_or_expression )* )
            // GorillaPP_grammar.g:1146:4: exclusive_or_expression ( '|' exclusive_or_expression )*
            {
            pushFollow(FOLLOW_exclusive_or_expression_in_inclusive_or_expression2187);
            exclusive_or_expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1146:28: ( '|' exclusive_or_expression )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==77) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // GorillaPP_grammar.g:1146:29: '|' exclusive_or_expression
            	    {
            	    match(input,77,FOLLOW_77_in_inclusive_or_expression2190); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString("|");}

            	    pushFollow(FOLLOW_exclusive_or_expression_in_inclusive_or_expression2205);
            	    exclusive_or_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop54;
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
            if ( state.backtracking>0 ) { memoize(input, 60, inclusive_or_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "inclusive_or_expression"



    // $ANTLR start "exclusive_or_expression"
    // GorillaPP_grammar.g:1150:1: exclusive_or_expression : and_expression ( '^' and_expression )* ;
    public final void exclusive_or_expression() throws RecognitionException {
        int exclusive_or_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return ; }

            // GorillaPP_grammar.g:1151:2: ( and_expression ( '^' and_expression )* )
            // GorillaPP_grammar.g:1151:4: and_expression ( '^' and_expression )*
            {
            pushFollow(FOLLOW_and_expression_in_exclusive_or_expression2218);
            and_expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1151:19: ( '^' and_expression )*
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==58) ) {
                    alt55=1;
                }


                switch (alt55) {
            	case 1 :
            	    // GorillaPP_grammar.g:1151:20: '^' and_expression
            	    {
            	    match(input,58,FOLLOW_58_in_exclusive_or_expression2221); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString("^");}

            	    pushFollow(FOLLOW_and_expression_in_exclusive_or_expression2224);
            	    and_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop55;
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
            if ( state.backtracking>0 ) { memoize(input, 61, exclusive_or_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "exclusive_or_expression"



    // $ANTLR start "and_expression"
    // GorillaPP_grammar.g:1154:1: and_expression : equality_expression ( '&' equality_expression )* ;
    public final void and_expression() throws RecognitionException {
        int and_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return ; }

            // GorillaPP_grammar.g:1155:2: ( equality_expression ( '&' equality_expression )* )
            // GorillaPP_grammar.g:1155:4: equality_expression ( '&' equality_expression )*
            {
            pushFollow(FOLLOW_equality_expression_in_and_expression2237);
            equality_expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1155:24: ( '&' equality_expression )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==30) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // GorillaPP_grammar.g:1155:25: '&' equality_expression
            	    {
            	    match(input,30,FOLLOW_30_in_and_expression2240); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString("&");}

            	    pushFollow(FOLLOW_equality_expression_in_and_expression2243);
            	    equality_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop56;
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
            if ( state.backtracking>0 ) { memoize(input, 62, and_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "and_expression"



    // $ANTLR start "equality_expression"
    // GorillaPP_grammar.g:1157:1: equality_expression : relational_expression ( ( '==' | '!=' ) relational_expression )* ;
    public final void equality_expression() throws RecognitionException {
        int equality_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return ; }

            // GorillaPP_grammar.g:1158:2: ( relational_expression ( ( '==' | '!=' ) relational_expression )* )
            // GorillaPP_grammar.g:1158:4: relational_expression ( ( '==' | '!=' ) relational_expression )*
            {
            pushFollow(FOLLOW_relational_expression_in_equality_expression2255);
            relational_expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1158:26: ( ( '==' | '!=' ) relational_expression )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==25||LA58_0==46) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // GorillaPP_grammar.g:1158:27: ( '==' | '!=' ) relational_expression
            	    {
            	    // GorillaPP_grammar.g:1158:27: ( '==' | '!=' )
            	    int alt57=2;
            	    int LA57_0 = input.LA(1);

            	    if ( (LA57_0==46) ) {
            	        alt57=1;
            	    }
            	    else if ( (LA57_0==25) ) {
            	        alt57=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 57, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt57) {
            	        case 1 :
            	            // GorillaPP_grammar.g:1158:28: '=='
            	            {
            	            match(input,46,FOLLOW_46_in_equality_expression2259); if (state.failed) return ;

            	            if ( state.backtracking==0 ) {outString("===");}

            	            }
            	            break;
            	        case 2 :
            	            // GorillaPP_grammar.g:1159:11: '!='
            	            {
            	            match(input,25,FOLLOW_25_in_equality_expression2273); if (state.failed) return ;

            	            if ( state.backtracking==0 ) {outString("!=");}

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_relational_expression_in_equality_expression2277);
            	    relational_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop58;
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
            if ( state.backtracking>0 ) { memoize(input, 63, equality_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "equality_expression"



    // $ANTLR start "relational_expression"
    // GorillaPP_grammar.g:1162:1: relational_expression : shift_expression ( ( '<' | '>' | '<=' | '>=' ) shift_expression )* ;
    public final void relational_expression() throws RecognitionException {
        int relational_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return ; }

            // GorillaPP_grammar.g:1163:2: ( shift_expression ( ( '<' | '>' | '<=' | '>=' ) shift_expression )* )
            // GorillaPP_grammar.g:1163:4: shift_expression ( ( '<' | '>' | '<=' | '>=' ) shift_expression )*
            {
            pushFollow(FOLLOW_shift_expression_in_relational_expression2290);
            shift_expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1163:21: ( ( '<' | '>' | '<=' | '>=' ) shift_expression )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==42||LA60_0==44||(LA60_0 >= 47 && LA60_0 <= 48)) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // GorillaPP_grammar.g:1163:22: ( '<' | '>' | '<=' | '>=' ) shift_expression
            	    {
            	    // GorillaPP_grammar.g:1163:22: ( '<' | '>' | '<=' | '>=' )
            	    int alt59=4;
            	    switch ( input.LA(1) ) {
            	    case 42:
            	        {
            	        alt59=1;
            	        }
            	        break;
            	    case 47:
            	        {
            	        alt59=2;
            	        }
            	        break;
            	    case 44:
            	        {
            	        alt59=3;
            	        }
            	        break;
            	    case 48:
            	        {
            	        alt59=4;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 59, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt59) {
            	        case 1 :
            	            // GorillaPP_grammar.g:1163:23: '<'
            	            {
            	            match(input,42,FOLLOW_42_in_relational_expression2294); if (state.failed) return ;

            	            if ( state.backtracking==0 ) {outString("<");}

            	            }
            	            break;
            	        case 2 :
            	            // GorillaPP_grammar.g:1164:11: '>'
            	            {
            	            match(input,47,FOLLOW_47_in_relational_expression2308); if (state.failed) return ;

            	            if ( state.backtracking==0 ) {outString(">");}

            	            }
            	            break;
            	        case 3 :
            	            // GorillaPP_grammar.g:1164:32: '<='
            	            {
            	            match(input,44,FOLLOW_44_in_relational_expression2311); if (state.failed) return ;

            	            if ( state.backtracking==0 ) {outString("<=");}

            	            }
            	            break;
            	        case 4 :
            	            // GorillaPP_grammar.g:1165:11: '>='
            	            {
            	            match(input,48,FOLLOW_48_in_relational_expression2325); if (state.failed) return ;

            	            if ( state.backtracking==0 ) {outString(">=");}

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_shift_expression_in_relational_expression2329);
            	    shift_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop60;
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
            if ( state.backtracking>0 ) { memoize(input, 64, relational_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "relational_expression"



    // $ANTLR start "shift_expression"
    // GorillaPP_grammar.g:1168:1: shift_expression : additive_expression ( ( '<<' | '>>' ) additive_expression )* ;
    public final void shift_expression() throws RecognitionException {
        int shift_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return ; }

            // GorillaPP_grammar.g:1169:2: ( additive_expression ( ( '<<' | '>>' ) additive_expression )* )
            // GorillaPP_grammar.g:1169:4: additive_expression ( ( '<<' | '>>' ) additive_expression )*
            {
            pushFollow(FOLLOW_additive_expression_in_shift_expression2342);
            additive_expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1169:24: ( ( '<<' | '>>' ) additive_expression )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==43||LA62_0==49) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // GorillaPP_grammar.g:1169:25: ( '<<' | '>>' ) additive_expression
            	    {
            	    // GorillaPP_grammar.g:1169:25: ( '<<' | '>>' )
            	    int alt61=2;
            	    int LA61_0 = input.LA(1);

            	    if ( (LA61_0==43) ) {
            	        alt61=1;
            	    }
            	    else if ( (LA61_0==49) ) {
            	        alt61=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 61, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt61) {
            	        case 1 :
            	            // GorillaPP_grammar.g:1169:26: '<<'
            	            {
            	            match(input,43,FOLLOW_43_in_shift_expression2346); if (state.failed) return ;

            	            if ( state.backtracking==0 ) {outString("<<");}

            	            }
            	            break;
            	        case 2 :
            	            // GorillaPP_grammar.g:1170:11: '>>'
            	            {
            	            match(input,49,FOLLOW_49_in_shift_expression2360); if (state.failed) return ;

            	            if ( state.backtracking==0 ) {outString(">>");}

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_additive_expression_in_shift_expression2364);
            	    additive_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop62;
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
            if ( state.backtracking>0 ) { memoize(input, 65, shift_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "shift_expression"



    // $ANTLR start "additive_expression"
    // GorillaPP_grammar.g:1174:1: additive_expression : ( multiplicative_expression ) ( '+' multiplicative_expression | '-' multiplicative_expression )* ;
    public final void additive_expression() throws RecognitionException {
        int additive_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return ; }

            // GorillaPP_grammar.g:1175:2: ( ( multiplicative_expression ) ( '+' multiplicative_expression | '-' multiplicative_expression )* )
            // GorillaPP_grammar.g:1175:4: ( multiplicative_expression ) ( '+' multiplicative_expression | '-' multiplicative_expression )*
            {
            // GorillaPP_grammar.g:1175:4: ( multiplicative_expression )
            // GorillaPP_grammar.g:1175:5: multiplicative_expression
            {
            pushFollow(FOLLOW_multiplicative_expression_in_additive_expression2379);
            multiplicative_expression();

            state._fsp--;
            if (state.failed) return ;

            }


            // GorillaPP_grammar.g:1175:32: ( '+' multiplicative_expression | '-' multiplicative_expression )*
            loop63:
            do {
                int alt63=3;
                int LA63_0 = input.LA(1);

                if ( (LA63_0==34) ) {
                    alt63=1;
                }
                else if ( (LA63_0==37) ) {
                    alt63=2;
                }


                switch (alt63) {
            	case 1 :
            	    // GorillaPP_grammar.g:1175:33: '+' multiplicative_expression
            	    {
            	    match(input,34,FOLLOW_34_in_additive_expression2383); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString("+");}

            	    pushFollow(FOLLOW_multiplicative_expression_in_additive_expression2397);
            	    multiplicative_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // GorillaPP_grammar.g:1176:39: '-' multiplicative_expression
            	    {
            	    match(input,37,FOLLOW_37_in_additive_expression2401); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString("-");}

            	    pushFollow(FOLLOW_multiplicative_expression_in_additive_expression2415);
            	    multiplicative_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop63;
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
            if ( state.backtracking>0 ) { memoize(input, 66, additive_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "additive_expression"



    // $ANTLR start "multiplicative_expression"
    // GorillaPP_grammar.g:1180:1: multiplicative_expression : ( cast_expression ) ( '*' cast_expression | '/' cast_expression | '%' cast_expression )* ;
    public final void multiplicative_expression() throws RecognitionException {
        int multiplicative_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return ; }

            // GorillaPP_grammar.g:1181:2: ( ( cast_expression ) ( '*' cast_expression | '/' cast_expression | '%' cast_expression )* )
            // GorillaPP_grammar.g:1181:4: ( cast_expression ) ( '*' cast_expression | '/' cast_expression | '%' cast_expression )*
            {
            // GorillaPP_grammar.g:1181:4: ( cast_expression )
            // GorillaPP_grammar.g:1181:5: cast_expression
            {
            pushFollow(FOLLOW_cast_expression_in_multiplicative_expression2429);
            cast_expression();

            state._fsp--;
            if (state.failed) return ;

            }


            // GorillaPP_grammar.g:1181:22: ( '*' cast_expression | '/' cast_expression | '%' cast_expression )*
            loop64:
            do {
                int alt64=4;
                switch ( input.LA(1) ) {
                case 33:
                    {
                    alt64=1;
                    }
                    break;
                case 40:
                    {
                    alt64=2;
                    }
                    break;
                case 28:
                    {
                    alt64=3;
                    }
                    break;

                }

                switch (alt64) {
            	case 1 :
            	    // GorillaPP_grammar.g:1181:23: '*' cast_expression
            	    {
            	    match(input,33,FOLLOW_33_in_multiplicative_expression2433); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString("*");}

            	    pushFollow(FOLLOW_cast_expression_in_multiplicative_expression2448);
            	    cast_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // GorillaPP_grammar.g:1183:11: '/' cast_expression
            	    {
            	    match(input,40,FOLLOW_40_in_multiplicative_expression2463); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString("/");}

            	    pushFollow(FOLLOW_cast_expression_in_multiplicative_expression2477);
            	    cast_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // GorillaPP_grammar.g:1184:29: '%' cast_expression
            	    {
            	    match(input,28,FOLLOW_28_in_multiplicative_expression2481); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString("%");}

            	    pushFollow(FOLLOW_cast_expression_in_multiplicative_expression2495);
            	    cast_expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop64;
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
            if ( state.backtracking>0 ) { memoize(input, 67, multiplicative_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "multiplicative_expression"



    // $ANTLR start "cast_expression"
    // GorillaPP_grammar.g:1188:1: cast_expression : ( '(' type_name ')' cast_expression | unary_expression );
    public final void cast_expression() throws RecognitionException {
        int cast_expression_StartIndex = input.index();

        GorillaPP_grammarParser.type_name_return type_name11 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return ; }

            // GorillaPP_grammar.g:1189:2: ( '(' type_name ')' cast_expression | unary_expression )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==31) ) {
                switch ( input.LA(2) ) {
                case 62:
                case 67:
                case 70:
                case 71:
                case 72:
                case 75:
                    {
                    alt65=1;
                    }
                    break;
                case ID:
                    {
                    int LA65_19 = input.LA(3);

                    if ( (synpred87_GorillaPP_grammar()) ) {
                        alt65=1;
                    }
                    else if ( (true) ) {
                        alt65=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 65, 19, input);

                        throw nvae;

                    }
                    }
                    break;
                case BINARY_LITERAL:
                case CHARACTER_LITERAL:
                case DECIMAL_LITERAL:
                case GORILLA_INSTR_NAME:
                case HEX_LITERAL:
                case OCTAL_LITERAL:
                case STRING_LITERAL:
                case 24:
                case 30:
                case 31:
                case 33:
                case 34:
                case 35:
                case 37:
                case 38:
                case 80:
                    {
                    alt65=2;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 65, 1, input);

                    throw nvae;

                }

            }
            else if ( (LA65_0==BINARY_LITERAL||LA65_0==CHARACTER_LITERAL||LA65_0==DECIMAL_LITERAL||(LA65_0 >= GORILLA_INSTR_NAME && LA65_0 <= HEX_LITERAL)||LA65_0==ID||LA65_0==OCTAL_LITERAL||LA65_0==STRING_LITERAL||LA65_0==24||LA65_0==30||(LA65_0 >= 33 && LA65_0 <= 35)||(LA65_0 >= 37 && LA65_0 <= 38)||LA65_0==80) ) {
                alt65=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;

            }
            switch (alt65) {
                case 1 :
                    // GorillaPP_grammar.g:1189:4: '(' type_name ')' cast_expression
                    {
                    match(input,31,FOLLOW_31_in_cast_expression2508); if (state.failed) return ;

                    pushFollow(FOLLOW_type_name_in_cast_expression2510);
                    type_name11=type_name();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                                ((Symbols_scope)Symbols_stack.peek()).isCasted = true;
                                outString("(" + 
                                 c2ChiselType((type_name11!=null?input.toString(type_name11.start,type_name11.stop):null)) +
                                 ")" +  ".fromBits(Bits(0, width=(" + 
                                 c2ChiselType((type_name11!=null?input.toString(type_name11.start,type_name11.stop):null)) + 
                                 ").getWidth) | " +
                                 "(");
                              }

                    match(input,32,FOLLOW_32_in_cast_expression2514); if (state.failed) return ;

                    pushFollow(FOLLOW_cast_expression_in_cast_expression2527);
                    cast_expression();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) { 
                                outString(").toBits)");
                              }

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1201:6: unary_expression
                    {
                    pushFollow(FOLLOW_unary_expression_in_cast_expression2536);
                    unary_expression();

                    state._fsp--;
                    if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 68, cast_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "cast_expression"



    // $ANTLR start "unary_expression"
    // GorillaPP_grammar.g:1205:1: unary_expression : ( postfix_expression | '++' unary_expression | '--' unary_expression | unary_operator cast_expression );
    public final void unary_expression() throws RecognitionException {
        int unary_expression_StartIndex = input.index();

        GorillaPP_grammarParser.unary_operator_return unary_operator12 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 69) ) { return ; }

            // GorillaPP_grammar.g:1206:2: ( postfix_expression | '++' unary_expression | '--' unary_expression | unary_operator cast_expression )
            int alt66=4;
            switch ( input.LA(1) ) {
            case BINARY_LITERAL:
            case CHARACTER_LITERAL:
            case DECIMAL_LITERAL:
            case GORILLA_INSTR_NAME:
            case HEX_LITERAL:
            case ID:
            case OCTAL_LITERAL:
            case STRING_LITERAL:
            case 31:
                {
                alt66=1;
                }
                break;
            case 35:
                {
                alt66=2;
                }
                break;
            case 38:
                {
                alt66=3;
                }
                break;
            case 24:
            case 30:
            case 33:
            case 34:
            case 37:
            case 80:
                {
                alt66=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;

            }

            switch (alt66) {
                case 1 :
                    // GorillaPP_grammar.g:1206:4: postfix_expression
                    {
                    pushFollow(FOLLOW_postfix_expression_in_unary_expression2548);
                    postfix_expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1207:4: '++' unary_expression
                    {
                    match(input,35,FOLLOW_35_in_unary_expression2553); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString("++");}

                    pushFollow(FOLLOW_unary_expression_in_unary_expression2556);
                    unary_expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:1208:4: '--' unary_expression
                    {
                    match(input,38,FOLLOW_38_in_unary_expression2561); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString("--");}

                    pushFollow(FOLLOW_unary_expression_in_unary_expression2564);
                    unary_expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // GorillaPP_grammar.g:1209:4: unary_operator cast_expression
                    {
                    pushFollow(FOLLOW_unary_operator_in_unary_expression2569);
                    unary_operator12=unary_operator();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {if (((Symbols_scope)Symbols_stack.peek()).isLocal) 
                             outString((unary_operator12!=null?input.toString(unary_operator12.start,unary_operator12.stop):null));}

                    pushFollow(FOLLOW_cast_expression_in_unary_expression2573);
                    cast_expression();

                    state._fsp--;
                    if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 69, unary_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "unary_expression"



    // $ANTLR start "postfix_expression"
    // GorillaPP_grammar.g:1215:1: postfix_expression : primary_expression ( '++' | '--' | '[' expression ']' )* ;
    public final void postfix_expression() throws RecognitionException {
        int postfix_expression_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 70) ) { return ; }

            // GorillaPP_grammar.g:1216:2: ( primary_expression ( '++' | '--' | '[' expression ']' )* )
            // GorillaPP_grammar.g:1216:6: primary_expression ( '++' | '--' | '[' expression ']' )*
            {
            pushFollow(FOLLOW_primary_expression_in_postfix_expression2604);
            primary_expression();

            state._fsp--;
            if (state.failed) return ;

            // GorillaPP_grammar.g:1216:25: ( '++' | '--' | '[' expression ']' )*
            loop67:
            do {
                int alt67=4;
                switch ( input.LA(1) ) {
                case 35:
                    {
                    alt67=1;
                    }
                    break;
                case 38:
                    {
                    alt67=2;
                    }
                    break;
                case 56:
                    {
                    alt67=3;
                    }
                    break;

                }

                switch (alt67) {
            	case 1 :
            	    // GorillaPP_grammar.g:1216:26: '++'
            	    {
            	    match(input,35,FOLLOW_35_in_postfix_expression2607); if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // GorillaPP_grammar.g:1216:33: '--'
            	    {
            	    match(input,38,FOLLOW_38_in_postfix_expression2611); if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // GorillaPP_grammar.g:1216:40: '[' expression ']'
            	    {
            	    match(input,56,FOLLOW_56_in_postfix_expression2615); if (state.failed) return ;

            	    pushFollow(FOLLOW_expression_in_postfix_expression2617);
            	    expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    match(input,57,FOLLOW_57_in_postfix_expression2619); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop67;
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
            if ( state.backtracking>0 ) { memoize(input, 70, postfix_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "postfix_expression"



    // $ANTLR start "fieldPostfix"
    // GorillaPP_grammar.g:1228:1: fieldPostfix : ( '.' ID )* ;
    public final void fieldPostfix() throws RecognitionException {
        int fieldPostfix_StartIndex = input.index();

        Token ID13=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 71) ) { return ; }

            // GorillaPP_grammar.g:1229:2: ( ( '.' ID )* )
            // GorillaPP_grammar.g:1229:5: ( '.' ID )*
            {
            // GorillaPP_grammar.g:1229:5: ( '.' ID )*
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( (LA68_0==39) ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // GorillaPP_grammar.g:1229:6: '.' ID
            	    {
            	    match(input,39,FOLLOW_39_in_fieldPostfix2704); if (state.failed) return ;

            	    ID13=(Token)match(input,ID,FOLLOW_ID_in_fieldPostfix2706); if (state.failed) return ;

            	    if ( state.backtracking==0 ) {outString("." + (ID13!=null?ID13.getText():null));}

            	    }
            	    break;

            	default :
            	    break loop68;
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
            if ( state.backtracking>0 ) { memoize(input, 71, fieldPostfix_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "fieldPostfix"



    // $ANTLR start "primary_expression"
    // GorillaPP_grammar.g:1231:1: primary_expression : ( ID fieldPostfix | constant | '(' conditional_expression ')' | ID array_index | ID array_range | GORILLA_INSTR_NAME );
    public final void primary_expression() throws RecognitionException {
        int primary_expression_StartIndex = input.index();

        Token ID14=null;
        Token ID16=null;
        Token ID18=null;
        Token GORILLA_INSTR_NAME20=null;
        GorillaPP_grammarParser.constant_return constant15 =null;

        GorillaPP_grammarParser.array_index_return array_index17 =null;

        GorillaPP_grammarParser.array_range_return array_range19 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 72) ) { return ; }

            // GorillaPP_grammar.g:1232:2: ( ID fieldPostfix | constant | '(' conditional_expression ')' | ID array_index | ID array_range | GORILLA_INSTR_NAME )
            int alt69=6;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA69_1 = input.LA(2);

                if ( (LA69_1==EOF||LA69_1==GORILLA_INSTR_NAME||LA69_1==ID||LA69_1==SEMICOLON||(LA69_1 >= 25 && LA69_1 <= 26)||(LA69_1 >= 28 && LA69_1 <= 30)||(LA69_1 >= 32 && LA69_1 <= 44)||(LA69_1 >= 46 && LA69_1 <= 50)||(LA69_1 >= 57 && LA69_1 <= 59)||LA69_1==62||LA69_1==67||(LA69_1 >= 70 && LA69_1 <= 72)||(LA69_1 >= 74 && LA69_1 <= 75)||(LA69_1 >= 77 && LA69_1 <= 79)) ) {
                    alt69=1;
                }
                else if ( (LA69_1==56) ) {
                    int LA69_13 = input.LA(3);

                    if ( (synpred95_GorillaPP_grammar()) ) {
                        alt69=1;
                    }
                    else if ( (synpred98_GorillaPP_grammar()) ) {
                        alt69=4;
                    }
                    else if ( (synpred99_GorillaPP_grammar()) ) {
                        alt69=5;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 69, 13, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 69, 1, input);

                    throw nvae;

                }
                }
                break;
            case BINARY_LITERAL:
            case CHARACTER_LITERAL:
            case DECIMAL_LITERAL:
            case HEX_LITERAL:
            case OCTAL_LITERAL:
            case STRING_LITERAL:
                {
                alt69=2;
                }
                break;
            case 31:
                {
                alt69=3;
                }
                break;
            case GORILLA_INSTR_NAME:
                {
                alt69=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;

            }

            switch (alt69) {
                case 1 :
                    // GorillaPP_grammar.g:1232:4: ID fieldPostfix
                    {
                    ID14=(Token)match(input,ID,FOLLOW_ID_in_primary_expression2720); if (state.failed) return ;

                    if ( state.backtracking==0 ) { 
                              String idPrint = "";
                              if ((ID14!=null?ID14.getText():null).equals("Input")) { 
                                if (((Symbols_scope)Symbols_stack.peek()).localLvalue) {
                                  if (((Symbols_scope)Symbols_stack.peek()).isPreOff) {
                                    idPrint = "inputReg(rThread)";
                                  } else {
                                    idPrint = "inputReg(vThread)";
                                  }  //TODO what if none
                                } else {
                                  if (!((Symbols_scope)Symbols_stack.peek()).isPreOff && 
                                   !((Symbols_scope)Symbols_stack.peek()).isPostOff) {
                                    idPrint = "inputReg(rThread)";
                                  } else {
                                    idPrint = "inputReg(vThread)";
                                  }
                                }
                              } else { 
                                if ((ID14!=null?ID14.getText():null).equals("true"))
                                  idPrint = "Bool(true)";
                                else if ((ID14!=null?ID14.getText():null).equals("false")) 
                                  idPrint = "Bool(false)";
                                else { 
                                  if (!((Symbols_scope)Symbols_stack.peek()).locals.contains((ID14!=null?ID14.getText():null))) {
                                    if (((Symbols_scope)Symbols_stack.peek()).isMacro || 
                                      ((Symbols_scope)Symbols_stack.peek()).constantMacros.contains((ID14!=null?ID14.getText():null)) || 
                                      ((Symbols_scope)Symbols_stack.peek()).externalMacros.contains((ID14!=null?ID14.getText():null)) || 
                                      (ID14!=null?ID14.getText():null).equals("WaitForInputValid")) {
                                       idPrint = (ID14!=null?ID14.getText():null);
                    	            } else if (((Symbols_scope)Symbols_stack.peek()).localLvalue) {
                                      if (((Symbols_scope)Symbols_stack.peek()).isPreOff) {
                                        idPrint = (ID14!=null?ID14.getText():null) + "(rThread)";
                                      } else {
                                        idPrint = (ID14!=null?ID14.getText():null) + "(vThread)";
                                      }
                                    } else {
                                      if (!((Symbols_scope)Symbols_stack.peek()).isPreOff && !((Symbols_scope)Symbols_stack.peek()).isPostOff) {
                                        idPrint = (ID14!=null?ID14.getText():null) + "(rThread)";
                                      } else {
                                        idPrint = (ID14!=null?ID14.getText():null) + "(vThread)";
                                      }
                                    }
                                  } else {
                                    idPrint = (ID14!=null?ID14.getText():null); 
                                  }
                                }
                              }
                              outString(idPrint); 
                            }

                    pushFollow(FOLLOW_fieldPostfix_in_primary_expression2725);
                    fieldPostfix();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1281:4: constant
                    {
                    pushFollow(FOLLOW_constant_in_primary_expression2730);
                    constant15=constant();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) { /*outString((constant15!=null?input.toString(constant15.start,constant15.stop):null));*/ }

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:1282:4: '(' conditional_expression ')'
                    {
                    match(input,31,FOLLOW_31_in_primary_expression2737); if (state.failed) return ;

                    if ( state.backtracking==0 ) { outString("("); }

                    pushFollow(FOLLOW_conditional_expression_in_primary_expression2743);
                    conditional_expression();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,32,FOLLOW_32_in_primary_expression2747); if (state.failed) return ;

                    if ( state.backtracking==0 ) { outString(")"); }

                    }
                    break;
                case 4 :
                    // GorillaPP_grammar.g:1285:4: ID array_index
                    {
                    ID16=(Token)match(input,ID,FOLLOW_ID_in_primary_expression2754); if (state.failed) return ;

                    pushFollow(FOLLOW_array_index_in_primary_expression2757);
                    array_index17=array_index();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString((ID16!=null?ID16.getText():null) + (array_index17!=null?input.toString(array_index17.start,array_index17.stop):null));}

                    }
                    break;
                case 5 :
                    // GorillaPP_grammar.g:1286:4: ID array_range
                    {
                    ID18=(Token)match(input,ID,FOLLOW_ID_in_primary_expression2765); if (state.failed) return ;

                    pushFollow(FOLLOW_array_range_in_primary_expression2768);
                    array_range19=array_range();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString((ID18!=null?ID18.getText():null) + (array_range19!=null?input.toString(array_range19.start,array_range19.stop):null));}

                    }
                    break;
                case 6 :
                    // GorillaPP_grammar.g:1287:4: GORILLA_INSTR_NAME
                    {
                    GORILLA_INSTR_NAME20=(Token)match(input,GORILLA_INSTR_NAME,FOLLOW_GORILLA_INSTR_NAME_in_primary_expression2775); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString((GORILLA_INSTR_NAME20!=null?GORILLA_INSTR_NAME20.getText():null));}

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
            if ( state.backtracking>0 ) { memoize(input, 72, primary_expression_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "primary_expression"


    public static class array_index_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "array_index"
    // GorillaPP_grammar.g:1290:1: array_index : '[' ( DECIMAL_LITERAL | ID ) ']' ;
    public final GorillaPP_grammarParser.array_index_return array_index() throws RecognitionException {
        GorillaPP_grammarParser.array_index_return retval = new GorillaPP_grammarParser.array_index_return();
        retval.start = input.LT(1);

        int array_index_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 73) ) { return retval; }

            // GorillaPP_grammar.g:1291:2: ( '[' ( DECIMAL_LITERAL | ID ) ']' )
            // GorillaPP_grammar.g:1291:4: '[' ( DECIMAL_LITERAL | ID ) ']'
            {
            match(input,56,FOLLOW_56_in_array_index2788); if (state.failed) return retval;

            if ( input.LA(1)==DECIMAL_LITERAL||input.LA(1)==ID ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            match(input,57,FOLLOW_57_in_array_index2798); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 73, array_index_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "array_index"


    public static class array_range_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "array_range"
    // GorillaPP_grammar.g:1292:1: array_range : '[' DECIMAL_LITERAL ':' DECIMAL_LITERAL ']' ;
    public final GorillaPP_grammarParser.array_range_return array_range() throws RecognitionException {
        GorillaPP_grammarParser.array_range_return retval = new GorillaPP_grammarParser.array_range_return();
        retval.start = input.LT(1);

        int array_range_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 74) ) { return retval; }

            // GorillaPP_grammar.g:1293:2: ( '[' DECIMAL_LITERAL ':' DECIMAL_LITERAL ']' )
            // GorillaPP_grammar.g:1293:4: '[' DECIMAL_LITERAL ':' DECIMAL_LITERAL ']'
            {
            match(input,56,FOLLOW_56_in_array_range2806); if (state.failed) return retval;

            match(input,DECIMAL_LITERAL,FOLLOW_DECIMAL_LITERAL_in_array_range2808); if (state.failed) return retval;

            match(input,41,FOLLOW_41_in_array_range2810); if (state.failed) return retval;

            match(input,DECIMAL_LITERAL,FOLLOW_DECIMAL_LITERAL_in_array_range2812); if (state.failed) return retval;

            match(input,57,FOLLOW_57_in_array_range2814); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 74, array_range_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "array_range"


    public static class constant_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "constant"
    // GorillaPP_grammar.g:1299:1: constant : (hl= HEX_LITERAL | OCTAL_LITERAL |dl= DECIMAL_LITERAL | CHARACTER_LITERAL | STRING_LITERAL | BINARY_LITERAL );
    public final GorillaPP_grammarParser.constant_return constant() throws RecognitionException {
        GorillaPP_grammarParser.constant_return retval = new GorillaPP_grammarParser.constant_return();
        retval.start = input.LT(1);

        int constant_StartIndex = input.index();

        Token hl=null;
        Token dl=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 75) ) { return retval; }

            // GorillaPP_grammar.g:1300:5: (hl= HEX_LITERAL | OCTAL_LITERAL |dl= DECIMAL_LITERAL | CHARACTER_LITERAL | STRING_LITERAL | BINARY_LITERAL )
            int alt70=6;
            switch ( input.LA(1) ) {
            case HEX_LITERAL:
                {
                alt70=1;
                }
                break;
            case OCTAL_LITERAL:
                {
                alt70=2;
                }
                break;
            case DECIMAL_LITERAL:
                {
                alt70=3;
                }
                break;
            case CHARACTER_LITERAL:
                {
                alt70=4;
                }
                break;
            case STRING_LITERAL:
                {
                alt70=5;
                }
                break;
            case BINARY_LITERAL:
                {
                alt70=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;

            }

            switch (alt70) {
                case 1 :
                    // GorillaPP_grammar.g:1300:9: hl= HEX_LITERAL
                    {
                    hl=(Token)match(input,HEX_LITERAL,FOLLOW_HEX_LITERAL_in_constant2831); if (state.failed) return retval;

                    if ( state.backtracking==0 ) {outString("UFix(" + hex2decimal((hl!=null?hl.getText():null).substring(2)) + ", width = 32)");}

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1301:9: OCTAL_LITERAL
                    {
                    match(input,OCTAL_LITERAL,FOLLOW_OCTAL_LITERAL_in_constant2843); if (state.failed) return retval;

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:1302:9: dl= DECIMAL_LITERAL
                    {
                    dl=(Token)match(input,DECIMAL_LITERAL,FOLLOW_DECIMAL_LITERAL_in_constant2855); if (state.failed) return retval;

                    if ( state.backtracking==0 ) {outString("UFix(" + (dl!=null?dl.getText():null) + ", width = 32)");}

                    }
                    break;
                case 4 :
                    // GorillaPP_grammar.g:1303:7: CHARACTER_LITERAL
                    {
                    match(input,CHARACTER_LITERAL,FOLLOW_CHARACTER_LITERAL_in_constant2865); if (state.failed) return retval;

                    }
                    break;
                case 5 :
                    // GorillaPP_grammar.g:1304:7: STRING_LITERAL
                    {
                    match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_constant2873); if (state.failed) return retval;

                    }
                    break;
                case 6 :
                    // GorillaPP_grammar.g:1305:9: BINARY_LITERAL
                    {
                    match(input,BINARY_LITERAL,FOLLOW_BINARY_LITERAL_in_constant2883); if (state.failed) return retval;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 75, constant_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "constant"


    public static class unary_operator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "unary_operator"
    // GorillaPP_grammar.g:1307:1: unary_operator : ( '&' | '*' | '+' | '-' | '~' | '!' );
    public final GorillaPP_grammarParser.unary_operator_return unary_operator() throws RecognitionException {
        GorillaPP_grammarParser.unary_operator_return retval = new GorillaPP_grammarParser.unary_operator_return();
        retval.start = input.LT(1);

        int unary_operator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 76) ) { return retval; }

            // GorillaPP_grammar.g:1308:2: ( '&' | '*' | '+' | '-' | '~' | '!' )
            // GorillaPP_grammar.g:
            {
            if ( input.LA(1)==24||input.LA(1)==30||(input.LA(1) >= 33 && input.LA(1) <= 34)||input.LA(1)==37||input.LA(1)==80 ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 76, unary_operator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "unary_operator"



    // $ANTLR start "assignment_operator"
    // GorillaPP_grammar.g:1316:1: assignment_operator : '=' ;
    public final void assignment_operator() throws RecognitionException {
        int assignment_operator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 77) ) { return ; }

            // GorillaPP_grammar.g:1317:2: ( '=' )
            // GorillaPP_grammar.g:1317:4: '='
            {
            match(input,45,FOLLOW_45_in_assignment_operator2934); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                      if (!((Symbols_scope)Symbols_stack.peek()).colonAssign) 
                        outString("="); 
                      else 
                        outString(":=");
                    }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 77, assignment_operator_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "assignment_operator"



    // $ANTLR start "operator"
    // GorillaPP_grammar.g:1336:1: operator : ( '^' | '+' | '-' | '&' | '|' | '&&' | '||' );
    public final void operator() throws RecognitionException {
        int operator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 78) ) { return ; }

            // GorillaPP_grammar.g:1337:2: ( '^' | '+' | '-' | '&' | '|' | '&&' | '||' )
            int alt71=7;
            switch ( input.LA(1) ) {
            case 58:
                {
                alt71=1;
                }
                break;
            case 34:
                {
                alt71=2;
                }
                break;
            case 37:
                {
                alt71=3;
                }
                break;
            case 30:
                {
                alt71=4;
                }
                break;
            case 77:
                {
                alt71=5;
                }
                break;
            case 29:
                {
                alt71=6;
                }
                break;
            case 78:
                {
                alt71=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;

            }

            switch (alt71) {
                case 1 :
                    // GorillaPP_grammar.g:1337:4: '^'
                    {
                    match(input,58,FOLLOW_58_in_operator2976); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString("^");}

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1337:26: '+'
                    {
                    match(input,34,FOLLOW_34_in_operator2980); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString("+");}

                    }
                    break;
                case 3 :
                    // GorillaPP_grammar.g:1337:49: '-'
                    {
                    match(input,37,FOLLOW_37_in_operator2985); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString("-");}

                    }
                    break;
                case 4 :
                    // GorillaPP_grammar.g:1337:72: '&'
                    {
                    match(input,30,FOLLOW_30_in_operator2990); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString("&");}

                    }
                    break;
                case 5 :
                    // GorillaPP_grammar.g:1337:95: '|'
                    {
                    match(input,77,FOLLOW_77_in_operator2995); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString("|");}

                    }
                    break;
                case 6 :
                    // GorillaPP_grammar.g:1337:117: '&&'
                    {
                    match(input,29,FOLLOW_29_in_operator2999); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString("&&");}

                    }
                    break;
                case 7 :
                    // GorillaPP_grammar.g:1337:142: '||'
                    {
                    match(input,78,FOLLOW_78_in_operator3004); if (state.failed) return ;

                    if ( state.backtracking==0 ) {outString("||");}

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
            if ( state.backtracking>0 ) { memoize(input, 78, operator_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "operator"



    // $ANTLR start "jump_statement"
    // GorillaPP_grammar.g:1363:1: jump_statement : ( 'continue' ';' | 'break' ';' );
    public final void jump_statement() throws RecognitionException {
        int jump_statement_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 79) ) { return ; }

            // GorillaPP_grammar.g:1364:2: ( 'continue' ';' | 'break' ';' )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==63) ) {
                alt72=1;
            }
            else if ( (LA72_0==60) ) {
                alt72=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;

            }
            switch (alt72) {
                case 1 :
                    // GorillaPP_grammar.g:1365:6: 'continue' ';'
                    {
                    match(input,63,FOLLOW_63_in_jump_statement3170); if (state.failed) return ;

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jump_statement3172); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // GorillaPP_grammar.g:1366:4: 'break' ';'
                    {
                    match(input,60,FOLLOW_60_in_jump_statement3177); if (state.failed) return ;

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jump_statement3179); if (state.failed) return ;

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
            if ( state.backtracking>0 ) { memoize(input, 79, jump_statement_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "jump_statement"

    // $ANTLR start synpred10_GorillaPP_grammar
    public final void synpred10_GorillaPP_grammar_fragment() throws RecognitionException {
        // GorillaPP_grammar.g:637:32: ( statement )
        // GorillaPP_grammar.g:637:32: statement
        {
        pushFollow(FOLLOW_statement_in_synpred10_GorillaPP_grammar211);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred10_GorillaPP_grammar

    // $ANTLR start synpred11_GorillaPP_grammar
    public final void synpred11_GorillaPP_grammar_fragment() throws RecognitionException {
        // GorillaPP_grammar.g:639:12: ( offload_statement )
        // GorillaPP_grammar.g:639:12: offload_statement
        {
        pushFollow(FOLLOW_offload_statement_in_synpred11_GorillaPP_grammar228);
        offload_statement();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred11_GorillaPP_grammar

    // $ANTLR start synpred23_GorillaPP_grammar
    public final void synpred23_GorillaPP_grammar_fragment() throws RecognitionException {
        Token id1=null;
        Token id2=null;

        // GorillaPP_grammar.g:746:5: ( '(' {...}?id1= ID ( ',' id2= ID )* ')' )
        // GorillaPP_grammar.g:746:5: '(' {...}?id1= ID ( ',' id2= ID )* ')'
        {
        match(input,31,FOLLOW_31_in_synpred23_GorillaPP_grammar528); if (state.failed) return ;

        if ( !((c2ChiselType(input.LT(1).getText()).contains("new") && 
                   !isTypeName(input.LT(1).getText()))) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred23_GorillaPP_grammar", "c2ChiselType(input.LT(1).getText()).contains(\"new\") && \n           !isTypeName(input.LT(1).getText())");
        }

        id1=(Token)match(input,ID,FOLLOW_ID_in_synpred23_GorillaPP_grammar558); if (state.failed) return ;

        // GorillaPP_grammar.g:751:11: ( ',' id2= ID )*
        loop75:
        do {
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==36) ) {
                alt75=1;
            }


            switch (alt75) {
        	case 1 :
        	    // GorillaPP_grammar.g:751:12: ',' id2= ID
        	    {
        	    match(input,36,FOLLOW_36_in_synpred23_GorillaPP_grammar574); if (state.failed) return ;

        	    id2=(Token)match(input,ID,FOLLOW_ID_in_synpred23_GorillaPP_grammar578); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop75;
            }
        } while (true);


        match(input,32,FOLLOW_32_in_synpred23_GorillaPP_grammar595); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred23_GorillaPP_grammar

    // $ANTLR start synpred67_GorillaPP_grammar
    public final void synpred67_GorillaPP_grammar_fragment() throws RecognitionException {
        // GorillaPP_grammar.g:1113:10: ( sel_expression )
        // GorillaPP_grammar.g:1113:10: sel_expression
        {
        pushFollow(FOLLOW_sel_expression_in_synpred67_GorillaPP_grammar1945);
        sel_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred67_GorillaPP_grammar

    // $ANTLR start synpred68_GorillaPP_grammar
    public final void synpred68_GorillaPP_grammar_fragment() throws RecognitionException {
        // GorillaPP_grammar.g:1113:28: ( conditional_expression )
        // GorillaPP_grammar.g:1113:28: conditional_expression
        {
        pushFollow(FOLLOW_conditional_expression_in_synpred68_GorillaPP_grammar1950);
        conditional_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred68_GorillaPP_grammar

    // $ANTLR start synpred87_GorillaPP_grammar
    public final void synpred87_GorillaPP_grammar_fragment() throws RecognitionException {
        // GorillaPP_grammar.g:1189:4: ( '(' type_name ')' cast_expression )
        // GorillaPP_grammar.g:1189:4: '(' type_name ')' cast_expression
        {
        match(input,31,FOLLOW_31_in_synpred87_GorillaPP_grammar2508); if (state.failed) return ;

        pushFollow(FOLLOW_type_name_in_synpred87_GorillaPP_grammar2510);
        type_name();

        state._fsp--;
        if (state.failed) return ;

        match(input,32,FOLLOW_32_in_synpred87_GorillaPP_grammar2514); if (state.failed) return ;

        pushFollow(FOLLOW_cast_expression_in_synpred87_GorillaPP_grammar2527);
        cast_expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred87_GorillaPP_grammar

    // $ANTLR start synpred95_GorillaPP_grammar
    public final void synpred95_GorillaPP_grammar_fragment() throws RecognitionException {
        // GorillaPP_grammar.g:1232:4: ( ID fieldPostfix )
        // GorillaPP_grammar.g:1232:4: ID fieldPostfix
        {
        match(input,ID,FOLLOW_ID_in_synpred95_GorillaPP_grammar2720); if (state.failed) return ;

        pushFollow(FOLLOW_fieldPostfix_in_synpred95_GorillaPP_grammar2725);
        fieldPostfix();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred95_GorillaPP_grammar

    // $ANTLR start synpred98_GorillaPP_grammar
    public final void synpred98_GorillaPP_grammar_fragment() throws RecognitionException {
        // GorillaPP_grammar.g:1285:4: ( ID array_index )
        // GorillaPP_grammar.g:1285:4: ID array_index
        {
        match(input,ID,FOLLOW_ID_in_synpred98_GorillaPP_grammar2754); if (state.failed) return ;

        pushFollow(FOLLOW_array_index_in_synpred98_GorillaPP_grammar2757);
        array_index();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred98_GorillaPP_grammar

    // $ANTLR start synpred99_GorillaPP_grammar
    public final void synpred99_GorillaPP_grammar_fragment() throws RecognitionException {
        // GorillaPP_grammar.g:1286:4: ( ID array_range )
        // GorillaPP_grammar.g:1286:4: ID array_range
        {
        match(input,ID,FOLLOW_ID_in_synpred99_GorillaPP_grammar2765); if (state.failed) return ;

        pushFollow(FOLLOW_array_range_in_synpred99_GorillaPP_grammar2768);
        array_range();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred99_GorillaPP_grammar

    // Delegated rules

    public final boolean synpred10_GorillaPP_grammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_GorillaPP_grammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred68_GorillaPP_grammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred68_GorillaPP_grammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_GorillaPP_grammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_GorillaPP_grammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred98_GorillaPP_grammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred98_GorillaPP_grammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred95_GorillaPP_grammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred95_GorillaPP_grammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred67_GorillaPP_grammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred67_GorillaPP_grammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred99_GorillaPP_grammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred99_GorillaPP_grammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred87_GorillaPP_grammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred87_GorillaPP_grammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred23_GorillaPP_grammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred23_GorillaPP_grammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA51 dfa51 = new DFA51(this);
    static final String DFA51_eotS =
        "\u011d\uffff";
    static final String DFA51_eofS =
        "\2\uffff\10\66\u0113\uffff";
    static final String DFA51_minS =
        "\2\4\10\25\3\4\50\0\7\uffff\25\0\6\uffff\25\0\6\uffff\25\0\6\uffff"+
        "\25\0\6\uffff\25\0\6\uffff\25\0\6\uffff\25\0\6\uffff\44\0";
    static final String DFA51_maxS =
        "\2\120\10\117\3\120\50\0\7\uffff\25\0\6\uffff\25\0\6\uffff\25\0"+
        "\6\uffff\25\0\6\uffff\25\0\6\uffff\25\0\6\uffff\25\0\6\uffff\44"+
        "\0";
    static final String DFA51_acceptS =
        "\65\uffff\1\1\1\2\4\uffff\1\3\u00e1\uffff";
    static final String DFA51_specialS =
        "\15\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30"+
        "\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\44\1\45"+
        "\1\46\1\47\7\uffff\1\50\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60"+
        "\1\61\1\62\1\63\1\64\1\65\1\66\1\67\1\70\1\71\1\72\1\73\1\74\6\uffff"+
        "\1\75\1\76\1\77\1\100\1\101\1\102\1\103\1\104\1\105\1\106\1\107"+
        "\1\110\1\111\1\112\1\113\1\114\1\115\1\116\1\117\1\120\1\121\6\uffff"+
        "\1\122\1\123\1\124\1\125\1\126\1\127\1\130\1\131\1\132\1\133\1\134"+
        "\1\135\1\136\1\137\1\140\1\141\1\142\1\143\1\144\1\145\1\146\6\uffff"+
        "\1\147\1\150\1\151\1\152\1\153\1\154\1\155\1\156\1\157\1\160\1\161"+
        "\1\162\1\163\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\6\uffff"+
        "\1\174\1\175\1\176\1\177\1\u0080\1\u0081\1\u0082\1\u0083\1\u0084"+
        "\1\u0085\1\u0086\1\u0087\1\u0088\1\u0089\1\u008a\1\u008b\1\u008c"+
        "\1\u008d\1\u008e\1\u008f\1\u0090\6\uffff\1\u0091\1\u0092\1\u0093"+
        "\1\u0094\1\u0095\1\u0096\1\u0097\1\u0098\1\u0099\1\u009a\1\u009b"+
        "\1\u009c\1\u009d\1\u009e\1\u009f\1\u00a0\1\u00a1\1\u00a2\1\u00a3"+
        "\1\u00a4\1\u00a5\6\uffff\1\u00a6\1\u00a7\1\u00a8\1\u00a9\1\u00aa"+
        "\1\u00ab\1\u00ac\1\u00ad\1\u00ae\1\u00af\1\u00b0\1\u00b1\1\u00b2"+
        "\1\u00b3\1\u00b4\1\u00b5\1\u00b6\1\u00b7\1\u00b8\1\u00b9\1\u00ba"+
        "\6\uffff\1\u00bb\1\u00bc\1\u00bd\1\u00be\1\u00bf\1\u00c0\1\u00c1"+
        "\1\u00c2\1\u00c3\1\u00c4\1\u00c5\1\u00c6\1\u00c7\1\u00c8\1\u00c9"+
        "\1\u00ca\1\u00cb\1\u00cc\1\u00cd\1\u00ce\1\u00cf\1\u00d0\1\u00d1"+
        "\1\u00d2\1\u00d3\1\u00d4\1\u00d5\1\u00d6\1\u00d7\1\u00d8\1\u00d9"+
        "\1\u00da\1\u00db\1\u00dc\1\u00dd\1\u00de}>";
    static final String[] DFA51_transitionS = {
            "\1\10\1\uffff\1\6\1\uffff\1\5\2\uffff\1\11\1\3\1\uffff\1\2\4"+
            "\uffff\1\4\2\uffff\1\7\1\uffff\1\14\5\uffff\1\14\1\1\1\uffff"+
            "\2\14\1\12\1\uffff\1\14\1\13\51\uffff\1\14",
            "\1\32\1\uffff\1\30\1\uffff\1\27\2\uffff\1\33\1\25\1\uffff\1"+
            "\23\4\uffff\1\26\2\uffff\1\31\1\uffff\1\36\5\uffff\1\36\1\24"+
            "\1\uffff\2\36\1\34\1\uffff\1\36\1\35\27\uffff\1\15\4\uffff\1"+
            "\22\2\uffff\1\17\1\20\1\16\2\uffff\1\21\4\uffff\1\36",
            "\1\66\3\uffff\1\57\2\uffff\1\45\1\63\1\60\1\73\1\uffff\1\43"+
            "\1\46\1\40\1\66\1\47\1\41\1\37\1\44\1\uffff\1\52\1\50\1\54\1"+
            "\uffff\1\56\1\53\1\55\1\51\1\65\5\uffff\1\42\1\66\1\61\22\uffff"+
            "\1\62\1\64\1\66",
            "\1\66\3\uffff\1\113\2\uffff\1\101\1\117\1\114\2\uffff\1\77"+
            "\1\102\1\74\1\66\1\103\1\75\1\uffff\1\100\1\uffff\1\106\1\104"+
            "\1\110\1\uffff\1\112\1\107\1\111\1\105\1\65\5\uffff\1\76\1\66"+
            "\1\115\22\uffff\1\116\1\120\1\66",
            "\1\66\3\uffff\1\146\2\uffff\1\134\1\152\1\147\2\uffff\1\132"+
            "\1\135\1\127\1\66\1\136\1\130\1\uffff\1\133\1\uffff\1\141\1"+
            "\137\1\143\1\uffff\1\145\1\142\1\144\1\140\1\65\5\uffff\1\131"+
            "\1\66\1\150\22\uffff\1\151\1\153\1\66",
            "\1\66\3\uffff\1\u0081\2\uffff\1\167\1\u0085\1\u0082\2\uffff"+
            "\1\165\1\170\1\162\1\66\1\171\1\163\1\uffff\1\166\1\uffff\1"+
            "\174\1\172\1\176\1\uffff\1\u0080\1\175\1\177\1\173\1\65\5\uffff"+
            "\1\164\1\66\1\u0083\22\uffff\1\u0084\1\u0086\1\66",
            "\1\66\3\uffff\1\u009c\2\uffff\1\u0092\1\u00a0\1\u009d\2\uffff"+
            "\1\u0090\1\u0093\1\u008d\1\66\1\u0094\1\u008e\1\uffff\1\u0091"+
            "\1\uffff\1\u0097\1\u0095\1\u0099\1\uffff\1\u009b\1\u0098\1\u009a"+
            "\1\u0096\1\65\5\uffff\1\u008f\1\66\1\u009e\22\uffff\1\u009f"+
            "\1\u00a1\1\66",
            "\1\66\3\uffff\1\u00b7\2\uffff\1\u00ad\1\u00bb\1\u00b8\2\uffff"+
            "\1\u00ab\1\u00ae\1\u00a8\1\66\1\u00af\1\u00a9\1\uffff\1\u00ac"+
            "\1\uffff\1\u00b2\1\u00b0\1\u00b4\1\uffff\1\u00b6\1\u00b3\1\u00b5"+
            "\1\u00b1\1\65\5\uffff\1\u00aa\1\66\1\u00b9\22\uffff\1\u00ba"+
            "\1\u00bc\1\66",
            "\1\66\3\uffff\1\u00d2\2\uffff\1\u00c8\1\u00d6\1\u00d3\2\uffff"+
            "\1\u00c6\1\u00c9\1\u00c3\1\66\1\u00ca\1\u00c4\1\uffff\1\u00c7"+
            "\1\uffff\1\u00cd\1\u00cb\1\u00cf\1\uffff\1\u00d1\1\u00ce\1\u00d0"+
            "\1\u00cc\1\65\5\uffff\1\u00c5\1\66\1\u00d4\22\uffff\1\u00d5"+
            "\1\u00d7\1\66",
            "\1\66\3\uffff\1\u00ed\2\uffff\1\u00e3\1\u00f1\1\u00ee\2\uffff"+
            "\1\u00e1\1\u00e4\1\u00de\1\66\1\u00e5\1\u00df\1\uffff\1\u00e2"+
            "\1\uffff\1\u00e8\1\u00e6\1\u00ea\1\uffff\1\u00ec\1\u00e9\1\u00eb"+
            "\1\u00e7\1\65\5\uffff\1\u00e0\1\66\1\u00ef\22\uffff\1\u00f0"+
            "\1\u00f2\1\66",
            "\1\u00ff\1\uffff\1\u00fd\1\uffff\1\u00fc\2\uffff\1\u0101\1"+
            "\u00fa\1\uffff\1\u00f9\4\uffff\1\u00fb\2\uffff\1\u00fe\1\uffff"+
            "\1\u0104\5\uffff\1\u0104\1\u0100\1\uffff\2\u0104\1\u0102\1\uffff"+
            "\1\u0104\1\u0103\51\uffff\1\u0104",
            "\1\u010b\1\uffff\1\u0109\1\uffff\1\u0108\2\uffff\1\u010d\1"+
            "\u0106\1\uffff\1\u0105\4\uffff\1\u0107\2\uffff\1\u010a\1\uffff"+
            "\1\u0110\5\uffff\1\u0110\1\u010c\1\uffff\2\u0110\1\u010e\1\uffff"+
            "\1\u0110\1\u010f\51\uffff\1\u0110",
            "\1\u0118\1\uffff\1\u0116\1\uffff\1\u0115\2\uffff\1\u0119\1"+
            "\u0113\1\uffff\1\u0112\4\uffff\1\u0114\2\uffff\1\u0117\1\uffff"+
            "\1\u011c\5\uffff\1\u011c\1\u0111\1\uffff\2\u011c\1\u011a\1\uffff"+
            "\1\u011c\1\u011b\51\uffff\1\u011c",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff"
    };

    static final short[] DFA51_eot = DFA.unpackEncodedString(DFA51_eotS);
    static final short[] DFA51_eof = DFA.unpackEncodedString(DFA51_eofS);
    static final char[] DFA51_min = DFA.unpackEncodedStringToUnsignedChars(DFA51_minS);
    static final char[] DFA51_max = DFA.unpackEncodedStringToUnsignedChars(DFA51_maxS);
    static final short[] DFA51_accept = DFA.unpackEncodedString(DFA51_acceptS);
    static final short[] DFA51_special = DFA.unpackEncodedString(DFA51_specialS);
    static final short[][] DFA51_transition;

    static {
        int numStates = DFA51_transitionS.length;
        DFA51_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA51_transition[i] = DFA.unpackEncodedString(DFA51_transitionS[i]);
        }
    }

    class DFA51 extends DFA {

        public DFA51(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 51;
            this.eot = DFA51_eot;
            this.eof = DFA51_eof;
            this.min = DFA51_min;
            this.max = DFA51_max;
            this.accept = DFA51_accept;
            this.special = DFA51_special;
            this.transition = DFA51_transition;
        }
        public String getDescription() {
            return "1113:9: ( sel_expression | conditional_expression | macro_expression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA51_13 = input.LA(1);

                         
                        int index51_13 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_13);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA51_14 = input.LA(1);

                         
                        int index51_14 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_14);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA51_15 = input.LA(1);

                         
                        int index51_15 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_15);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA51_16 = input.LA(1);

                         
                        int index51_16 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_16);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA51_17 = input.LA(1);

                         
                        int index51_17 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_17);

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA51_18 = input.LA(1);

                         
                        int index51_18 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_18);

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA51_19 = input.LA(1);

                         
                        int index51_19 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_19);

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA51_20 = input.LA(1);

                         
                        int index51_20 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_20);

                        if ( s>=0 ) return s;
                        break;

                    case 8 : 
                        int LA51_21 = input.LA(1);

                         
                        int index51_21 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_21);

                        if ( s>=0 ) return s;
                        break;

                    case 9 : 
                        int LA51_22 = input.LA(1);

                         
                        int index51_22 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_22);

                        if ( s>=0 ) return s;
                        break;

                    case 10 : 
                        int LA51_23 = input.LA(1);

                         
                        int index51_23 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_23);

                        if ( s>=0 ) return s;
                        break;

                    case 11 : 
                        int LA51_24 = input.LA(1);

                         
                        int index51_24 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_24);

                        if ( s>=0 ) return s;
                        break;

                    case 12 : 
                        int LA51_25 = input.LA(1);

                         
                        int index51_25 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_25);

                        if ( s>=0 ) return s;
                        break;

                    case 13 : 
                        int LA51_26 = input.LA(1);

                         
                        int index51_26 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_26);

                        if ( s>=0 ) return s;
                        break;

                    case 14 : 
                        int LA51_27 = input.LA(1);

                         
                        int index51_27 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_27);

                        if ( s>=0 ) return s;
                        break;

                    case 15 : 
                        int LA51_28 = input.LA(1);

                         
                        int index51_28 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_28);

                        if ( s>=0 ) return s;
                        break;

                    case 16 : 
                        int LA51_29 = input.LA(1);

                         
                        int index51_29 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_29);

                        if ( s>=0 ) return s;
                        break;

                    case 17 : 
                        int LA51_30 = input.LA(1);

                         
                        int index51_30 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_30);

                        if ( s>=0 ) return s;
                        break;

                    case 18 : 
                        int LA51_31 = input.LA(1);

                         
                        int index51_31 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_31);

                        if ( s>=0 ) return s;
                        break;

                    case 19 : 
                        int LA51_32 = input.LA(1);

                         
                        int index51_32 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_32);

                        if ( s>=0 ) return s;
                        break;

                    case 20 : 
                        int LA51_33 = input.LA(1);

                         
                        int index51_33 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_33);

                        if ( s>=0 ) return s;
                        break;

                    case 21 : 
                        int LA51_34 = input.LA(1);

                         
                        int index51_34 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_34);

                        if ( s>=0 ) return s;
                        break;

                    case 22 : 
                        int LA51_35 = input.LA(1);

                         
                        int index51_35 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_35);

                        if ( s>=0 ) return s;
                        break;

                    case 23 : 
                        int LA51_36 = input.LA(1);

                         
                        int index51_36 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_36);

                        if ( s>=0 ) return s;
                        break;

                    case 24 : 
                        int LA51_37 = input.LA(1);

                         
                        int index51_37 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_37);

                        if ( s>=0 ) return s;
                        break;

                    case 25 : 
                        int LA51_38 = input.LA(1);

                         
                        int index51_38 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_38);

                        if ( s>=0 ) return s;
                        break;

                    case 26 : 
                        int LA51_39 = input.LA(1);

                         
                        int index51_39 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_39);

                        if ( s>=0 ) return s;
                        break;

                    case 27 : 
                        int LA51_40 = input.LA(1);

                         
                        int index51_40 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_40);

                        if ( s>=0 ) return s;
                        break;

                    case 28 : 
                        int LA51_41 = input.LA(1);

                         
                        int index51_41 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_41);

                        if ( s>=0 ) return s;
                        break;

                    case 29 : 
                        int LA51_42 = input.LA(1);

                         
                        int index51_42 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_42);

                        if ( s>=0 ) return s;
                        break;

                    case 30 : 
                        int LA51_43 = input.LA(1);

                         
                        int index51_43 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_43);

                        if ( s>=0 ) return s;
                        break;

                    case 31 : 
                        int LA51_44 = input.LA(1);

                         
                        int index51_44 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_44);

                        if ( s>=0 ) return s;
                        break;

                    case 32 : 
                        int LA51_45 = input.LA(1);

                         
                        int index51_45 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_45);

                        if ( s>=0 ) return s;
                        break;

                    case 33 : 
                        int LA51_46 = input.LA(1);

                         
                        int index51_46 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_46);

                        if ( s>=0 ) return s;
                        break;

                    case 34 : 
                        int LA51_47 = input.LA(1);

                         
                        int index51_47 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_47);

                        if ( s>=0 ) return s;
                        break;

                    case 35 : 
                        int LA51_48 = input.LA(1);

                         
                        int index51_48 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_48);

                        if ( s>=0 ) return s;
                        break;

                    case 36 : 
                        int LA51_49 = input.LA(1);

                         
                        int index51_49 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_49);

                        if ( s>=0 ) return s;
                        break;

                    case 37 : 
                        int LA51_50 = input.LA(1);

                         
                        int index51_50 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_50);

                        if ( s>=0 ) return s;
                        break;

                    case 38 : 
                        int LA51_51 = input.LA(1);

                         
                        int index51_51 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_51);

                        if ( s>=0 ) return s;
                        break;

                    case 39 : 
                        int LA51_52 = input.LA(1);

                         
                        int index51_52 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_52);

                        if ( s>=0 ) return s;
                        break;

                    case 40 : 
                        int LA51_60 = input.LA(1);

                         
                        int index51_60 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_60);

                        if ( s>=0 ) return s;
                        break;

                    case 41 : 
                        int LA51_61 = input.LA(1);

                         
                        int index51_61 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_61);

                        if ( s>=0 ) return s;
                        break;

                    case 42 : 
                        int LA51_62 = input.LA(1);

                         
                        int index51_62 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_62);

                        if ( s>=0 ) return s;
                        break;

                    case 43 : 
                        int LA51_63 = input.LA(1);

                         
                        int index51_63 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_63);

                        if ( s>=0 ) return s;
                        break;

                    case 44 : 
                        int LA51_64 = input.LA(1);

                         
                        int index51_64 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_64);

                        if ( s>=0 ) return s;
                        break;

                    case 45 : 
                        int LA51_65 = input.LA(1);

                         
                        int index51_65 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_65);

                        if ( s>=0 ) return s;
                        break;

                    case 46 : 
                        int LA51_66 = input.LA(1);

                         
                        int index51_66 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_66);

                        if ( s>=0 ) return s;
                        break;

                    case 47 : 
                        int LA51_67 = input.LA(1);

                         
                        int index51_67 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_67);

                        if ( s>=0 ) return s;
                        break;

                    case 48 : 
                        int LA51_68 = input.LA(1);

                         
                        int index51_68 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_68);

                        if ( s>=0 ) return s;
                        break;

                    case 49 : 
                        int LA51_69 = input.LA(1);

                         
                        int index51_69 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_69);

                        if ( s>=0 ) return s;
                        break;

                    case 50 : 
                        int LA51_70 = input.LA(1);

                         
                        int index51_70 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_70);

                        if ( s>=0 ) return s;
                        break;

                    case 51 : 
                        int LA51_71 = input.LA(1);

                         
                        int index51_71 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_71);

                        if ( s>=0 ) return s;
                        break;

                    case 52 : 
                        int LA51_72 = input.LA(1);

                         
                        int index51_72 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_72);

                        if ( s>=0 ) return s;
                        break;

                    case 53 : 
                        int LA51_73 = input.LA(1);

                         
                        int index51_73 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_73);

                        if ( s>=0 ) return s;
                        break;

                    case 54 : 
                        int LA51_74 = input.LA(1);

                         
                        int index51_74 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_74);

                        if ( s>=0 ) return s;
                        break;

                    case 55 : 
                        int LA51_75 = input.LA(1);

                         
                        int index51_75 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_75);

                        if ( s>=0 ) return s;
                        break;

                    case 56 : 
                        int LA51_76 = input.LA(1);

                         
                        int index51_76 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_76);

                        if ( s>=0 ) return s;
                        break;

                    case 57 : 
                        int LA51_77 = input.LA(1);

                         
                        int index51_77 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_77);

                        if ( s>=0 ) return s;
                        break;

                    case 58 : 
                        int LA51_78 = input.LA(1);

                         
                        int index51_78 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_78);

                        if ( s>=0 ) return s;
                        break;

                    case 59 : 
                        int LA51_79 = input.LA(1);

                         
                        int index51_79 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_79);

                        if ( s>=0 ) return s;
                        break;

                    case 60 : 
                        int LA51_80 = input.LA(1);

                         
                        int index51_80 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_80);

                        if ( s>=0 ) return s;
                        break;

                    case 61 : 
                        int LA51_87 = input.LA(1);

                         
                        int index51_87 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_87);

                        if ( s>=0 ) return s;
                        break;

                    case 62 : 
                        int LA51_88 = input.LA(1);

                         
                        int index51_88 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_88);

                        if ( s>=0 ) return s;
                        break;

                    case 63 : 
                        int LA51_89 = input.LA(1);

                         
                        int index51_89 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_89);

                        if ( s>=0 ) return s;
                        break;

                    case 64 : 
                        int LA51_90 = input.LA(1);

                         
                        int index51_90 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_90);

                        if ( s>=0 ) return s;
                        break;

                    case 65 : 
                        int LA51_91 = input.LA(1);

                         
                        int index51_91 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_91);

                        if ( s>=0 ) return s;
                        break;

                    case 66 : 
                        int LA51_92 = input.LA(1);

                         
                        int index51_92 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_92);

                        if ( s>=0 ) return s;
                        break;

                    case 67 : 
                        int LA51_93 = input.LA(1);

                         
                        int index51_93 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_93);

                        if ( s>=0 ) return s;
                        break;

                    case 68 : 
                        int LA51_94 = input.LA(1);

                         
                        int index51_94 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_94);

                        if ( s>=0 ) return s;
                        break;

                    case 69 : 
                        int LA51_95 = input.LA(1);

                         
                        int index51_95 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_95);

                        if ( s>=0 ) return s;
                        break;

                    case 70 : 
                        int LA51_96 = input.LA(1);

                         
                        int index51_96 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_96);

                        if ( s>=0 ) return s;
                        break;

                    case 71 : 
                        int LA51_97 = input.LA(1);

                         
                        int index51_97 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_97);

                        if ( s>=0 ) return s;
                        break;

                    case 72 : 
                        int LA51_98 = input.LA(1);

                         
                        int index51_98 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_98);

                        if ( s>=0 ) return s;
                        break;

                    case 73 : 
                        int LA51_99 = input.LA(1);

                         
                        int index51_99 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_99);

                        if ( s>=0 ) return s;
                        break;

                    case 74 : 
                        int LA51_100 = input.LA(1);

                         
                        int index51_100 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_100);

                        if ( s>=0 ) return s;
                        break;

                    case 75 : 
                        int LA51_101 = input.LA(1);

                         
                        int index51_101 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_101);

                        if ( s>=0 ) return s;
                        break;

                    case 76 : 
                        int LA51_102 = input.LA(1);

                         
                        int index51_102 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_102);

                        if ( s>=0 ) return s;
                        break;

                    case 77 : 
                        int LA51_103 = input.LA(1);

                         
                        int index51_103 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_103);

                        if ( s>=0 ) return s;
                        break;

                    case 78 : 
                        int LA51_104 = input.LA(1);

                         
                        int index51_104 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_104);

                        if ( s>=0 ) return s;
                        break;

                    case 79 : 
                        int LA51_105 = input.LA(1);

                         
                        int index51_105 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_105);

                        if ( s>=0 ) return s;
                        break;

                    case 80 : 
                        int LA51_106 = input.LA(1);

                         
                        int index51_106 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_106);

                        if ( s>=0 ) return s;
                        break;

                    case 81 : 
                        int LA51_107 = input.LA(1);

                         
                        int index51_107 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_107);

                        if ( s>=0 ) return s;
                        break;

                    case 82 : 
                        int LA51_114 = input.LA(1);

                         
                        int index51_114 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_114);

                        if ( s>=0 ) return s;
                        break;

                    case 83 : 
                        int LA51_115 = input.LA(1);

                         
                        int index51_115 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_115);

                        if ( s>=0 ) return s;
                        break;

                    case 84 : 
                        int LA51_116 = input.LA(1);

                         
                        int index51_116 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_116);

                        if ( s>=0 ) return s;
                        break;

                    case 85 : 
                        int LA51_117 = input.LA(1);

                         
                        int index51_117 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_117);

                        if ( s>=0 ) return s;
                        break;

                    case 86 : 
                        int LA51_118 = input.LA(1);

                         
                        int index51_118 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_118);

                        if ( s>=0 ) return s;
                        break;

                    case 87 : 
                        int LA51_119 = input.LA(1);

                         
                        int index51_119 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_119);

                        if ( s>=0 ) return s;
                        break;

                    case 88 : 
                        int LA51_120 = input.LA(1);

                         
                        int index51_120 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_120);

                        if ( s>=0 ) return s;
                        break;

                    case 89 : 
                        int LA51_121 = input.LA(1);

                         
                        int index51_121 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_121);

                        if ( s>=0 ) return s;
                        break;

                    case 90 : 
                        int LA51_122 = input.LA(1);

                         
                        int index51_122 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_122);

                        if ( s>=0 ) return s;
                        break;

                    case 91 : 
                        int LA51_123 = input.LA(1);

                         
                        int index51_123 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_123);

                        if ( s>=0 ) return s;
                        break;

                    case 92 : 
                        int LA51_124 = input.LA(1);

                         
                        int index51_124 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_124);

                        if ( s>=0 ) return s;
                        break;

                    case 93 : 
                        int LA51_125 = input.LA(1);

                         
                        int index51_125 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_125);

                        if ( s>=0 ) return s;
                        break;

                    case 94 : 
                        int LA51_126 = input.LA(1);

                         
                        int index51_126 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_126);

                        if ( s>=0 ) return s;
                        break;

                    case 95 : 
                        int LA51_127 = input.LA(1);

                         
                        int index51_127 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_127);

                        if ( s>=0 ) return s;
                        break;

                    case 96 : 
                        int LA51_128 = input.LA(1);

                         
                        int index51_128 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_128);

                        if ( s>=0 ) return s;
                        break;

                    case 97 : 
                        int LA51_129 = input.LA(1);

                         
                        int index51_129 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_129);

                        if ( s>=0 ) return s;
                        break;

                    case 98 : 
                        int LA51_130 = input.LA(1);

                         
                        int index51_130 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_130);

                        if ( s>=0 ) return s;
                        break;

                    case 99 : 
                        int LA51_131 = input.LA(1);

                         
                        int index51_131 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_131);

                        if ( s>=0 ) return s;
                        break;

                    case 100 : 
                        int LA51_132 = input.LA(1);

                         
                        int index51_132 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_132);

                        if ( s>=0 ) return s;
                        break;

                    case 101 : 
                        int LA51_133 = input.LA(1);

                         
                        int index51_133 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_133);

                        if ( s>=0 ) return s;
                        break;

                    case 102 : 
                        int LA51_134 = input.LA(1);

                         
                        int index51_134 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_134);

                        if ( s>=0 ) return s;
                        break;

                    case 103 : 
                        int LA51_141 = input.LA(1);

                         
                        int index51_141 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_141);

                        if ( s>=0 ) return s;
                        break;

                    case 104 : 
                        int LA51_142 = input.LA(1);

                         
                        int index51_142 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_142);

                        if ( s>=0 ) return s;
                        break;

                    case 105 : 
                        int LA51_143 = input.LA(1);

                         
                        int index51_143 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_143);

                        if ( s>=0 ) return s;
                        break;

                    case 106 : 
                        int LA51_144 = input.LA(1);

                         
                        int index51_144 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_144);

                        if ( s>=0 ) return s;
                        break;

                    case 107 : 
                        int LA51_145 = input.LA(1);

                         
                        int index51_145 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_145);

                        if ( s>=0 ) return s;
                        break;

                    case 108 : 
                        int LA51_146 = input.LA(1);

                         
                        int index51_146 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_146);

                        if ( s>=0 ) return s;
                        break;

                    case 109 : 
                        int LA51_147 = input.LA(1);

                         
                        int index51_147 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_147);

                        if ( s>=0 ) return s;
                        break;

                    case 110 : 
                        int LA51_148 = input.LA(1);

                         
                        int index51_148 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_148);

                        if ( s>=0 ) return s;
                        break;

                    case 111 : 
                        int LA51_149 = input.LA(1);

                         
                        int index51_149 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_149);

                        if ( s>=0 ) return s;
                        break;

                    case 112 : 
                        int LA51_150 = input.LA(1);

                         
                        int index51_150 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_150);

                        if ( s>=0 ) return s;
                        break;

                    case 113 : 
                        int LA51_151 = input.LA(1);

                         
                        int index51_151 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_151);

                        if ( s>=0 ) return s;
                        break;

                    case 114 : 
                        int LA51_152 = input.LA(1);

                         
                        int index51_152 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_152);

                        if ( s>=0 ) return s;
                        break;

                    case 115 : 
                        int LA51_153 = input.LA(1);

                         
                        int index51_153 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_153);

                        if ( s>=0 ) return s;
                        break;

                    case 116 : 
                        int LA51_154 = input.LA(1);

                         
                        int index51_154 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_154);

                        if ( s>=0 ) return s;
                        break;

                    case 117 : 
                        int LA51_155 = input.LA(1);

                         
                        int index51_155 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_155);

                        if ( s>=0 ) return s;
                        break;

                    case 118 : 
                        int LA51_156 = input.LA(1);

                         
                        int index51_156 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_156);

                        if ( s>=0 ) return s;
                        break;

                    case 119 : 
                        int LA51_157 = input.LA(1);

                         
                        int index51_157 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_157);

                        if ( s>=0 ) return s;
                        break;

                    case 120 : 
                        int LA51_158 = input.LA(1);

                         
                        int index51_158 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_158);

                        if ( s>=0 ) return s;
                        break;

                    case 121 : 
                        int LA51_159 = input.LA(1);

                         
                        int index51_159 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_159);

                        if ( s>=0 ) return s;
                        break;

                    case 122 : 
                        int LA51_160 = input.LA(1);

                         
                        int index51_160 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_160);

                        if ( s>=0 ) return s;
                        break;

                    case 123 : 
                        int LA51_161 = input.LA(1);

                         
                        int index51_161 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_161);

                        if ( s>=0 ) return s;
                        break;

                    case 124 : 
                        int LA51_168 = input.LA(1);

                         
                        int index51_168 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_168);

                        if ( s>=0 ) return s;
                        break;

                    case 125 : 
                        int LA51_169 = input.LA(1);

                         
                        int index51_169 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_169);

                        if ( s>=0 ) return s;
                        break;

                    case 126 : 
                        int LA51_170 = input.LA(1);

                         
                        int index51_170 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_170);

                        if ( s>=0 ) return s;
                        break;

                    case 127 : 
                        int LA51_171 = input.LA(1);

                         
                        int index51_171 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_171);

                        if ( s>=0 ) return s;
                        break;

                    case 128 : 
                        int LA51_172 = input.LA(1);

                         
                        int index51_172 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_172);

                        if ( s>=0 ) return s;
                        break;

                    case 129 : 
                        int LA51_173 = input.LA(1);

                         
                        int index51_173 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_173);

                        if ( s>=0 ) return s;
                        break;

                    case 130 : 
                        int LA51_174 = input.LA(1);

                         
                        int index51_174 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_174);

                        if ( s>=0 ) return s;
                        break;

                    case 131 : 
                        int LA51_175 = input.LA(1);

                         
                        int index51_175 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_175);

                        if ( s>=0 ) return s;
                        break;

                    case 132 : 
                        int LA51_176 = input.LA(1);

                         
                        int index51_176 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_176);

                        if ( s>=0 ) return s;
                        break;

                    case 133 : 
                        int LA51_177 = input.LA(1);

                         
                        int index51_177 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_177);

                        if ( s>=0 ) return s;
                        break;

                    case 134 : 
                        int LA51_178 = input.LA(1);

                         
                        int index51_178 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_178);

                        if ( s>=0 ) return s;
                        break;

                    case 135 : 
                        int LA51_179 = input.LA(1);

                         
                        int index51_179 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_179);

                        if ( s>=0 ) return s;
                        break;

                    case 136 : 
                        int LA51_180 = input.LA(1);

                         
                        int index51_180 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_180);

                        if ( s>=0 ) return s;
                        break;

                    case 137 : 
                        int LA51_181 = input.LA(1);

                         
                        int index51_181 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_181);

                        if ( s>=0 ) return s;
                        break;

                    case 138 : 
                        int LA51_182 = input.LA(1);

                         
                        int index51_182 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_182);

                        if ( s>=0 ) return s;
                        break;

                    case 139 : 
                        int LA51_183 = input.LA(1);

                         
                        int index51_183 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_183);

                        if ( s>=0 ) return s;
                        break;

                    case 140 : 
                        int LA51_184 = input.LA(1);

                         
                        int index51_184 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_184);

                        if ( s>=0 ) return s;
                        break;

                    case 141 : 
                        int LA51_185 = input.LA(1);

                         
                        int index51_185 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_185);

                        if ( s>=0 ) return s;
                        break;

                    case 142 : 
                        int LA51_186 = input.LA(1);

                         
                        int index51_186 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_186);

                        if ( s>=0 ) return s;
                        break;

                    case 143 : 
                        int LA51_187 = input.LA(1);

                         
                        int index51_187 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_187);

                        if ( s>=0 ) return s;
                        break;

                    case 144 : 
                        int LA51_188 = input.LA(1);

                         
                        int index51_188 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_188);

                        if ( s>=0 ) return s;
                        break;

                    case 145 : 
                        int LA51_195 = input.LA(1);

                         
                        int index51_195 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_195);

                        if ( s>=0 ) return s;
                        break;

                    case 146 : 
                        int LA51_196 = input.LA(1);

                         
                        int index51_196 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_196);

                        if ( s>=0 ) return s;
                        break;

                    case 147 : 
                        int LA51_197 = input.LA(1);

                         
                        int index51_197 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_197);

                        if ( s>=0 ) return s;
                        break;

                    case 148 : 
                        int LA51_198 = input.LA(1);

                         
                        int index51_198 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_198);

                        if ( s>=0 ) return s;
                        break;

                    case 149 : 
                        int LA51_199 = input.LA(1);

                         
                        int index51_199 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_199);

                        if ( s>=0 ) return s;
                        break;

                    case 150 : 
                        int LA51_200 = input.LA(1);

                         
                        int index51_200 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_200);

                        if ( s>=0 ) return s;
                        break;

                    case 151 : 
                        int LA51_201 = input.LA(1);

                         
                        int index51_201 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_201);

                        if ( s>=0 ) return s;
                        break;

                    case 152 : 
                        int LA51_202 = input.LA(1);

                         
                        int index51_202 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_202);

                        if ( s>=0 ) return s;
                        break;

                    case 153 : 
                        int LA51_203 = input.LA(1);

                         
                        int index51_203 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_203);

                        if ( s>=0 ) return s;
                        break;

                    case 154 : 
                        int LA51_204 = input.LA(1);

                         
                        int index51_204 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_204);

                        if ( s>=0 ) return s;
                        break;

                    case 155 : 
                        int LA51_205 = input.LA(1);

                         
                        int index51_205 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_205);

                        if ( s>=0 ) return s;
                        break;

                    case 156 : 
                        int LA51_206 = input.LA(1);

                         
                        int index51_206 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_206);

                        if ( s>=0 ) return s;
                        break;

                    case 157 : 
                        int LA51_207 = input.LA(1);

                         
                        int index51_207 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_207);

                        if ( s>=0 ) return s;
                        break;

                    case 158 : 
                        int LA51_208 = input.LA(1);

                         
                        int index51_208 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_208);

                        if ( s>=0 ) return s;
                        break;

                    case 159 : 
                        int LA51_209 = input.LA(1);

                         
                        int index51_209 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_209);

                        if ( s>=0 ) return s;
                        break;

                    case 160 : 
                        int LA51_210 = input.LA(1);

                         
                        int index51_210 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_210);

                        if ( s>=0 ) return s;
                        break;

                    case 161 : 
                        int LA51_211 = input.LA(1);

                         
                        int index51_211 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_211);

                        if ( s>=0 ) return s;
                        break;

                    case 162 : 
                        int LA51_212 = input.LA(1);

                         
                        int index51_212 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_212);

                        if ( s>=0 ) return s;
                        break;

                    case 163 : 
                        int LA51_213 = input.LA(1);

                         
                        int index51_213 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_213);

                        if ( s>=0 ) return s;
                        break;

                    case 164 : 
                        int LA51_214 = input.LA(1);

                         
                        int index51_214 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_214);

                        if ( s>=0 ) return s;
                        break;

                    case 165 : 
                        int LA51_215 = input.LA(1);

                         
                        int index51_215 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_215);

                        if ( s>=0 ) return s;
                        break;

                    case 166 : 
                        int LA51_222 = input.LA(1);

                         
                        int index51_222 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_222);

                        if ( s>=0 ) return s;
                        break;

                    case 167 : 
                        int LA51_223 = input.LA(1);

                         
                        int index51_223 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_223);

                        if ( s>=0 ) return s;
                        break;

                    case 168 : 
                        int LA51_224 = input.LA(1);

                         
                        int index51_224 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_224);

                        if ( s>=0 ) return s;
                        break;

                    case 169 : 
                        int LA51_225 = input.LA(1);

                         
                        int index51_225 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_225);

                        if ( s>=0 ) return s;
                        break;

                    case 170 : 
                        int LA51_226 = input.LA(1);

                         
                        int index51_226 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_226);

                        if ( s>=0 ) return s;
                        break;

                    case 171 : 
                        int LA51_227 = input.LA(1);

                         
                        int index51_227 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_227);

                        if ( s>=0 ) return s;
                        break;

                    case 172 : 
                        int LA51_228 = input.LA(1);

                         
                        int index51_228 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_228);

                        if ( s>=0 ) return s;
                        break;

                    case 173 : 
                        int LA51_229 = input.LA(1);

                         
                        int index51_229 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_229);

                        if ( s>=0 ) return s;
                        break;

                    case 174 : 
                        int LA51_230 = input.LA(1);

                         
                        int index51_230 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_230);

                        if ( s>=0 ) return s;
                        break;

                    case 175 : 
                        int LA51_231 = input.LA(1);

                         
                        int index51_231 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_231);

                        if ( s>=0 ) return s;
                        break;

                    case 176 : 
                        int LA51_232 = input.LA(1);

                         
                        int index51_232 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_232);

                        if ( s>=0 ) return s;
                        break;

                    case 177 : 
                        int LA51_233 = input.LA(1);

                         
                        int index51_233 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_233);

                        if ( s>=0 ) return s;
                        break;

                    case 178 : 
                        int LA51_234 = input.LA(1);

                         
                        int index51_234 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_234);

                        if ( s>=0 ) return s;
                        break;

                    case 179 : 
                        int LA51_235 = input.LA(1);

                         
                        int index51_235 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_235);

                        if ( s>=0 ) return s;
                        break;

                    case 180 : 
                        int LA51_236 = input.LA(1);

                         
                        int index51_236 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_236);

                        if ( s>=0 ) return s;
                        break;

                    case 181 : 
                        int LA51_237 = input.LA(1);

                         
                        int index51_237 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_237);

                        if ( s>=0 ) return s;
                        break;

                    case 182 : 
                        int LA51_238 = input.LA(1);

                         
                        int index51_238 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_238);

                        if ( s>=0 ) return s;
                        break;

                    case 183 : 
                        int LA51_239 = input.LA(1);

                         
                        int index51_239 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_239);

                        if ( s>=0 ) return s;
                        break;

                    case 184 : 
                        int LA51_240 = input.LA(1);

                         
                        int index51_240 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_240);

                        if ( s>=0 ) return s;
                        break;

                    case 185 : 
                        int LA51_241 = input.LA(1);

                         
                        int index51_241 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_241);

                        if ( s>=0 ) return s;
                        break;

                    case 186 : 
                        int LA51_242 = input.LA(1);

                         
                        int index51_242 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_242);

                        if ( s>=0 ) return s;
                        break;

                    case 187 : 
                        int LA51_249 = input.LA(1);

                         
                        int index51_249 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_249);

                        if ( s>=0 ) return s;
                        break;

                    case 188 : 
                        int LA51_250 = input.LA(1);

                         
                        int index51_250 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_250);

                        if ( s>=0 ) return s;
                        break;

                    case 189 : 
                        int LA51_251 = input.LA(1);

                         
                        int index51_251 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_251);

                        if ( s>=0 ) return s;
                        break;

                    case 190 : 
                        int LA51_252 = input.LA(1);

                         
                        int index51_252 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_252);

                        if ( s>=0 ) return s;
                        break;

                    case 191 : 
                        int LA51_253 = input.LA(1);

                         
                        int index51_253 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_253);

                        if ( s>=0 ) return s;
                        break;

                    case 192 : 
                        int LA51_254 = input.LA(1);

                         
                        int index51_254 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_254);

                        if ( s>=0 ) return s;
                        break;

                    case 193 : 
                        int LA51_255 = input.LA(1);

                         
                        int index51_255 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_255);

                        if ( s>=0 ) return s;
                        break;

                    case 194 : 
                        int LA51_256 = input.LA(1);

                         
                        int index51_256 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_256);

                        if ( s>=0 ) return s;
                        break;

                    case 195 : 
                        int LA51_257 = input.LA(1);

                         
                        int index51_257 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_257);

                        if ( s>=0 ) return s;
                        break;

                    case 196 : 
                        int LA51_258 = input.LA(1);

                         
                        int index51_258 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_258);

                        if ( s>=0 ) return s;
                        break;

                    case 197 : 
                        int LA51_259 = input.LA(1);

                         
                        int index51_259 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_259);

                        if ( s>=0 ) return s;
                        break;

                    case 198 : 
                        int LA51_260 = input.LA(1);

                         
                        int index51_260 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_260);

                        if ( s>=0 ) return s;
                        break;

                    case 199 : 
                        int LA51_261 = input.LA(1);

                         
                        int index51_261 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_261);

                        if ( s>=0 ) return s;
                        break;

                    case 200 : 
                        int LA51_262 = input.LA(1);

                         
                        int index51_262 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_262);

                        if ( s>=0 ) return s;
                        break;

                    case 201 : 
                        int LA51_263 = input.LA(1);

                         
                        int index51_263 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_263);

                        if ( s>=0 ) return s;
                        break;

                    case 202 : 
                        int LA51_264 = input.LA(1);

                         
                        int index51_264 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_264);

                        if ( s>=0 ) return s;
                        break;

                    case 203 : 
                        int LA51_265 = input.LA(1);

                         
                        int index51_265 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_265);

                        if ( s>=0 ) return s;
                        break;

                    case 204 : 
                        int LA51_266 = input.LA(1);

                         
                        int index51_266 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_266);

                        if ( s>=0 ) return s;
                        break;

                    case 205 : 
                        int LA51_267 = input.LA(1);

                         
                        int index51_267 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_267);

                        if ( s>=0 ) return s;
                        break;

                    case 206 : 
                        int LA51_268 = input.LA(1);

                         
                        int index51_268 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_268);

                        if ( s>=0 ) return s;
                        break;

                    case 207 : 
                        int LA51_269 = input.LA(1);

                         
                        int index51_269 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_269);

                        if ( s>=0 ) return s;
                        break;

                    case 208 : 
                        int LA51_270 = input.LA(1);

                         
                        int index51_270 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_270);

                        if ( s>=0 ) return s;
                        break;

                    case 209 : 
                        int LA51_271 = input.LA(1);

                         
                        int index51_271 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_271);

                        if ( s>=0 ) return s;
                        break;

                    case 210 : 
                        int LA51_272 = input.LA(1);

                         
                        int index51_272 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_272);

                        if ( s>=0 ) return s;
                        break;

                    case 211 : 
                        int LA51_273 = input.LA(1);

                         
                        int index51_273 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_273);

                        if ( s>=0 ) return s;
                        break;

                    case 212 : 
                        int LA51_274 = input.LA(1);

                         
                        int index51_274 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_274);

                        if ( s>=0 ) return s;
                        break;

                    case 213 : 
                        int LA51_275 = input.LA(1);

                         
                        int index51_275 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_275);

                        if ( s>=0 ) return s;
                        break;

                    case 214 : 
                        int LA51_276 = input.LA(1);

                         
                        int index51_276 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_276);

                        if ( s>=0 ) return s;
                        break;

                    case 215 : 
                        int LA51_277 = input.LA(1);

                         
                        int index51_277 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_277);

                        if ( s>=0 ) return s;
                        break;

                    case 216 : 
                        int LA51_278 = input.LA(1);

                         
                        int index51_278 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_278);

                        if ( s>=0 ) return s;
                        break;

                    case 217 : 
                        int LA51_279 = input.LA(1);

                         
                        int index51_279 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_279);

                        if ( s>=0 ) return s;
                        break;

                    case 218 : 
                        int LA51_280 = input.LA(1);

                         
                        int index51_280 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_280);

                        if ( s>=0 ) return s;
                        break;

                    case 219 : 
                        int LA51_281 = input.LA(1);

                         
                        int index51_281 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_281);

                        if ( s>=0 ) return s;
                        break;

                    case 220 : 
                        int LA51_282 = input.LA(1);

                         
                        int index51_282 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_282);

                        if ( s>=0 ) return s;
                        break;

                    case 221 : 
                        int LA51_283 = input.LA(1);

                         
                        int index51_283 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_283);

                        if ( s>=0 ) return s;
                        break;

                    case 222 : 
                        int LA51_284 = input.LA(1);

                         
                        int index51_284 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred67_GorillaPP_grammar()) ) {s = 53;}

                        else if ( (synpred68_GorillaPP_grammar()) ) {s = 54;}

                         
                        input.seek(index51_284);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 51, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

    public static final BitSet FOLLOW_external_declaration_in_translation_unit74 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_definition_in_external_declaration100 = new BitSet(new long[]{0x0000000004000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_define_in_external_declaration102 = new BitSet(new long[]{0x0000000004000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_in_pragma_in_external_declaration119 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_out_pragma_in_external_declaration121 = new BitSet(new long[]{0x480000000C004800L,0x00000000000009C8L});
    public static final BitSet FOLLOW_off_pragma_in_external_declaration123 = new BitSet(new long[]{0x480000000C004800L,0x00000000000009C8L});
    public static final BitSet FOLLOW_other_pragma_in_external_declaration126 = new BitSet(new long[]{0x480000000C004800L,0x00000000000009C8L});
    public static final BitSet FOLLOW_global_declaration_in_external_declaration131 = new BitSet(new long[]{0x4800000004004800L,0x00000000000009C8L});
    public static final BitSet FOLLOW_magilla_instr_in_external_declaration136 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_define_in_global_declaration149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_variable_definition_in_global_declaration153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GORILLA_INSTR_NAME_in_magilla_instr175 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_magilla_instr180 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_magilla_instr181 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_magilla_instr184 = new BitSet(new long[]{0xF000000000204000L,0x00000000000099FDL});
    public static final BitSet FOLLOW_instr_body_in_magilla_instr186 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_magilla_instr187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_local_variable_definition_in_instr_body208 = new BitSet(new long[]{0xF000000000204002L,0x00000000000019FDL});
    public static final BitSet FOLLOW_statement_in_instr_body211 = new BitSet(new long[]{0xB000000000204002L,0x0000000000001035L});
    public static final BitSet FOLLOW_offload_statement_in_instr_body228 = new BitSet(new long[]{0xB000000000204002L,0x0000000000001035L});
    public static final BitSet FOLLOW_statement_in_instr_body245 = new BitSet(new long[]{0xB000000000204002L,0x0000000000001035L});
    public static final BitSet FOLLOW_finish_statement_in_instr_body248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_specifier_in_local_variable_definition261 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_variablelist_in_local_variable_definition272 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_ID_in_local_variable_definition278 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_array_index_in_local_variable_definition280 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_local_variable_definition284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_specifier_in_global_variable_definition303 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_variablelist_in_global_variable_definition307 = new BitSet(new long[]{0x0100200000200000L});
    public static final BitSet FOLLOW_array_index_in_global_variable_definition322 = new BitSet(new long[]{0x0000200000200000L});
    public static final BitSet FOLLOW_59_in_global_variable_definition329 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_array_index_in_global_variable_definition331 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_global_variable_definition333 = new BitSet(new long[]{0x0000200000200000L});
    public static final BitSet FOLLOW_45_in_global_variable_definition352 = new BitSet(new long[]{0x0000000000481150L});
    public static final BitSet FOLLOW_constant_in_global_variable_definition366 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_global_variable_definition370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_type_definition383 = new BitSet(new long[]{0x4000000000004000L,0x0000000000000BC8L});
    public static final BitSet FOLLOW_type_specifier_struct_in_type_definition385 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_type_definition387 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_type_definition391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_off_pragma_in_pragma402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_pragma_in_pragma407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_out_pragma_in_pragma411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_other_pragma_in_pragma415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_other_pragma424 = new BitSet(new long[]{0x0088000000000000L});
    public static final BitSet FOLLOW_set_in_other_pragma426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_in_pragma443 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_in_pragma445 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_in_pragma451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_out_pragma463 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_out_pragma465 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_out_pragma470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_off_pragma483 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_off_pragma485 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_off_pragma487 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_off_pragma491 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_off_pragma493 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_off_pragma504 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_off_pragma506 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_off_pragma510 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_off_pragma512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_defineArg528 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_defineArg558 = new BitSet(new long[]{0x0000001100000000L});
    public static final BitSet FOLLOW_36_in_defineArg574 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_defineArg578 = new BitSet(new long[]{0x0000001100000000L});
    public static final BitSet FOLLOW_32_in_defineArg595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_define609 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_define622 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_defineArg_in_define626 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_constant_expression_in_define648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_declaration668 = new BitSet(new long[]{0x4000000000004000L,0x00000000000009C8L});
    public static final BitSet FOLLOW_declaration_specifiers_in_declaration676 = new BitSet(new long[]{0x0000000080004000L});
    public static final BitSet FOLLOW_init_declarator_list_in_declaration678 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_declaration680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_specifiers_in_declaration699 = new BitSet(new long[]{0x0000000080004000L});
    public static final BitSet FOLLOW_init_declarator_list_in_declaration701 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_declaration703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_init_declarator_in_init_declarator_list714 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_init_declarator_list717 = new BitSet(new long[]{0x0000000080004000L});
    public static final BitSet FOLLOW_init_declarator_in_init_declarator_list719 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_declarator_in_init_declarator732 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_init_declarator736 = new BitSet(new long[]{0x0000000000004000L,0x0000000000001000L});
    public static final BitSet FOLLOW_initializer_in_init_declarator738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_direct_declarator_in_declarator752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_direct_declarator764 = new BitSet(new long[]{0x0100000080000002L});
    public static final BitSet FOLLOW_31_in_direct_declarator779 = new BitSet(new long[]{0x0000000080004000L});
    public static final BitSet FOLLOW_declarator_in_direct_declarator781 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_direct_declarator783 = new BitSet(new long[]{0x0100000080000002L});
    public static final BitSet FOLLOW_declarator_suffix_in_direct_declarator786 = new BitSet(new long[]{0x0100000080000002L});
    public static final BitSet FOLLOW_56_in_declarator_suffix798 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_constant_expression_in_declarator_suffix800 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_declarator_suffix802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_declarator_suffix816 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_declarator_suffix818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_declarator_suffix832 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_identifier_list_in_declarator_suffix834 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_declarator_suffix836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_declarator_suffix850 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_declarator_suffix852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_identifier_list863 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_identifier_list866 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_identifier_list868 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_type_specifier_in_declaration_specifiers883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_expression_in_initializer895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_initializer900 = new BitSet(new long[]{0x0000000000004000L,0x0000000000001000L});
    public static final BitSet FOLLOW_initializer_list_in_initializer902 = new BitSet(new long[]{0x0000001000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_36_in_initializer904 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_initializer907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_initializer_in_initializer_list918 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_initializer_list921 = new BitSet(new long[]{0x0000000000004000L,0x0000000000001000L});
    public static final BitSet FOLLOW_initializer_in_initializer_list923 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_ID_in_define_lable936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_offload_expression_in_offload_statement951 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_offload_statement953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_labeled_statement_in_statement968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_statement_in_statement972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selection_statement_in_statement976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_statement_in_statement980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jump_statement_in_statement984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_compound_statement994 = new BitSet(new long[]{0xB000000000204000L,0x0000000000001021L});
    public static final BitSet FOLLOW_statement_in_compound_statement996 = new BitSet(new long[]{0xB000000000204000L,0x0000000000009021L});
    public static final BitSet FOLLOW_79_in_compound_statement999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_offload_expression1014 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_offload_expression1016 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_offload_expression1020 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_offload_expression1038 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_conditional_expression_in_offload_expression1040 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_offload_expression1042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_macro_expression1071 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_macro_expression1074 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_conditional_expression_in_macro_expression1089 = new BitSet(new long[]{0x0000001100000000L});
    public static final BitSet FOLLOW_36_in_macro_expression1092 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_conditional_expression_in_macro_expression1107 = new BitSet(new long[]{0x0000001100000000L});
    public static final BitSet FOLLOW_32_in_macro_expression1110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_labeled_statement1136 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_labeled_statement1138 = new BitSet(new long[]{0xB000000000204000L,0x0000000000001021L});
    public static final BitSet FOLLOW_statement_in_labeled_statement1140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_labeled_statement1145 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_constant_expression_in_labeled_statement1148 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_labeled_statement1150 = new BitSet(new long[]{0xB000000000204000L,0x0000000000001021L});
    public static final BitSet FOLLOW_statement_in_labeled_statement1189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_labeled_statement1196 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_labeled_statement1198 = new BitSet(new long[]{0xB000000000204000L,0x0000000000001021L});
    public static final BitSet FOLLOW_statement_in_labeled_statement1200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_expression_statement1211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expression_statement1215 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_expression_statement1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_selection_statement1237 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_selection_statement1240 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_conditional_expression_in_selection_statement1267 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_selection_statement1294 = new BitSet(new long[]{0xB000000000204000L,0x0000000000001021L});
    public static final BitSet FOLLOW_statement_in_selection_statement1309 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_selection_statement1347 = new BitSet(new long[]{0xB000000000204000L,0x0000000000001021L});
    public static final BitSet FOLLOW_statement_in_selection_statement1363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_finish_statement1394 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_finish_statement1396 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_finish_statement1398 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_finish_statement1400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_finish_statement1406 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_finish_statement1408 = new BitSet(new long[]{0x0000000100004000L});
    public static final BitSet FOLLOW_ID_in_finish_statement1415 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_finish_statement1419 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_finish_statement1421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_in_def_in_variablelist1440 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_variablelist1444 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_variable_in_def_in_variablelist1446 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_variable_in_variable_in_def1459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_variable1474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_specifier_qualifier_list_in_type_name1488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_specifier_in_specifier_qualifier_list1503 = new BitSet(new long[]{0x4000000000004002L,0x00000000000009C8L});
    public static final BitSet FOLLOW_type_specifier_in_type_specifier_struct1519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_type_specifier_struct1529 = new BitSet(new long[]{0x4000000000004000L,0x00000000000009C8L});
    public static final BitSet FOLLOW_type_specifier_in_type_specifier_struct1532 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_variablelist_in_type_specifier_struct1541 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_type_specifier_struct1543 = new BitSet(new long[]{0x4000000000004000L,0x00000000000089C8L});
    public static final BitSet FOLLOW_79_in_type_specifier_struct1547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_type_specifier1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_type_specifier1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_type_specifier1573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_type_specifier1581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_type_specifier1586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enum_specifier_in_type_specifier1591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_id_in_type_specifier1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type_id1614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_direct_abstract_declarator_in_abstract_declarator1661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_direct_abstract_declarator1675 = new BitSet(new long[]{0x0100000080000000L});
    public static final BitSet FOLLOW_abstract_declarator_in_direct_abstract_declarator1677 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_direct_abstract_declarator1679 = new BitSet(new long[]{0x0100000080000002L});
    public static final BitSet FOLLOW_abstract_declarator_suffix_in_direct_abstract_declarator1695 = new BitSet(new long[]{0x0100000080000002L});
    public static final BitSet FOLLOW_abstract_declarator_suffix_in_direct_abstract_declarator1711 = new BitSet(new long[]{0x0100000080000002L});
    public static final BitSet FOLLOW_56_in_abstract_declarator_suffix1724 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_abstract_declarator_suffix1726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_abstract_declarator_suffix1732 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_constant_expression_in_abstract_declarator_suffix1734 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_abstract_declarator_suffix1736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_abstract_declarator_suffix1742 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_abstract_declarator_suffix1744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_enum_specifier1762 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_enum_specifier1764 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_enumerator_list_in_enum_specifier1766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_enum_specifier1768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_enum_specifier1773 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_enum_specifier1775 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_enum_specifier1777 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_enumerator_list_in_enum_specifier1779 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_enum_specifier1781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_enum_specifier1786 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_enum_specifier1788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumerator_in_enumerator_list1799 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_enumerator_list1802 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_enumerator_in_enumerator_list1804 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_ID_in_enumerator1817 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_enumerator1820 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_constant_expression_in_enumerator1822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_expression_in_argument_expression_list1837 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_argument_expression_list1840 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_assignment_expression_in_argument_expression_list1842 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_assignment_expression_in_expression1858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_concat_expression1874 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_expression_in_concat_expression1888 = new BitSet(new long[]{0x0000001000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_36_in_concat_expression1891 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_expression_in_concat_expression1894 = new BitSet(new long[]{0x0000001000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_concat_expression1898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_assignment_expression1927 = new BitSet(new long[]{0x0000208000000000L});
    public static final BitSet FOLLOW_fieldPostfix_in_assignment_expression1931 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_assignment_operator_in_assignment_expression1933 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_sel_expression_in_assignment_expression1945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_expression_in_assignment_expression1950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_macro_expression_in_assignment_expression1954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_expression_in_sel_expression1988 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_sel_expression1990 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_conditional_expression_in_sel_expression2011 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_sel_expression2013 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_conditional_expression_in_sel_expression2034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_expression_in_lvalue2070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_expression_in_constant_expression2080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logical_or_expression_in_conditional_expression2092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logical_and_expression_in_logical_or_expression2125 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_logical_or_expression2128 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_logical_and_expression_in_logical_or_expression2143 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_inclusive_or_expression_in_logical_and_expression2156 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_logical_and_expression2159 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_inclusive_or_expression_in_logical_and_expression2174 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_exclusive_or_expression_in_inclusive_or_expression2187 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_inclusive_or_expression2190 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_exclusive_or_expression_in_inclusive_or_expression2205 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_and_expression_in_exclusive_or_expression2218 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_58_in_exclusive_or_expression2221 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_and_expression_in_exclusive_or_expression2224 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_equality_expression_in_and_expression2237 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_and_expression2240 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_equality_expression_in_and_expression2243 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_relational_expression_in_equality_expression2255 = new BitSet(new long[]{0x0000400002000002L});
    public static final BitSet FOLLOW_46_in_equality_expression2259 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_25_in_equality_expression2273 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_relational_expression_in_equality_expression2277 = new BitSet(new long[]{0x0000400002000002L});
    public static final BitSet FOLLOW_shift_expression_in_relational_expression2290 = new BitSet(new long[]{0x0001940000000002L});
    public static final BitSet FOLLOW_42_in_relational_expression2294 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_47_in_relational_expression2308 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_44_in_relational_expression2311 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_48_in_relational_expression2325 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_shift_expression_in_relational_expression2329 = new BitSet(new long[]{0x0001940000000002L});
    public static final BitSet FOLLOW_additive_expression_in_shift_expression2342 = new BitSet(new long[]{0x0002080000000002L});
    public static final BitSet FOLLOW_43_in_shift_expression2346 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_49_in_shift_expression2360 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_additive_expression_in_shift_expression2364 = new BitSet(new long[]{0x0002080000000002L});
    public static final BitSet FOLLOW_multiplicative_expression_in_additive_expression2379 = new BitSet(new long[]{0x0000002400000002L});
    public static final BitSet FOLLOW_34_in_additive_expression2383 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_multiplicative_expression_in_additive_expression2397 = new BitSet(new long[]{0x0000002400000002L});
    public static final BitSet FOLLOW_37_in_additive_expression2401 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_multiplicative_expression_in_additive_expression2415 = new BitSet(new long[]{0x0000002400000002L});
    public static final BitSet FOLLOW_cast_expression_in_multiplicative_expression2429 = new BitSet(new long[]{0x0000010210000002L});
    public static final BitSet FOLLOW_33_in_multiplicative_expression2433 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_cast_expression_in_multiplicative_expression2448 = new BitSet(new long[]{0x0000010210000002L});
    public static final BitSet FOLLOW_40_in_multiplicative_expression2463 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_cast_expression_in_multiplicative_expression2477 = new BitSet(new long[]{0x0000010210000002L});
    public static final BitSet FOLLOW_28_in_multiplicative_expression2481 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_cast_expression_in_multiplicative_expression2495 = new BitSet(new long[]{0x0000010210000002L});
    public static final BitSet FOLLOW_31_in_cast_expression2508 = new BitSet(new long[]{0x4000000000004000L,0x00000000000009C8L});
    public static final BitSet FOLLOW_type_name_in_cast_expression2510 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_cast_expression2514 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_cast_expression_in_cast_expression2527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_expression_in_cast_expression2536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfix_expression_in_unary_expression2548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_unary_expression2553 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_unary_expression_in_unary_expression2556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_unary_expression2561 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_unary_expression_in_unary_expression2564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_operator_in_unary_expression2569 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_cast_expression_in_unary_expression2573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_expression_in_postfix_expression2604 = new BitSet(new long[]{0x0100004800000002L});
    public static final BitSet FOLLOW_35_in_postfix_expression2607 = new BitSet(new long[]{0x0100004800000002L});
    public static final BitSet FOLLOW_38_in_postfix_expression2611 = new BitSet(new long[]{0x0100004800000002L});
    public static final BitSet FOLLOW_56_in_postfix_expression2615 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_expression_in_postfix_expression2617 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_postfix_expression2619 = new BitSet(new long[]{0x0100004800000002L});
    public static final BitSet FOLLOW_39_in_fieldPostfix2704 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_fieldPostfix2706 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_ID_in_primary_expression2720 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_fieldPostfix_in_primary_expression2725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_primary_expression2730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_primary_expression2737 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_conditional_expression_in_primary_expression2743 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_primary_expression2747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary_expression2754 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_array_index_in_primary_expression2757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary_expression2765 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_array_range_in_primary_expression2768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GORILLA_INSTR_NAME_in_primary_expression2775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_array_index2788 = new BitSet(new long[]{0x0000000000004100L});
    public static final BitSet FOLLOW_set_in_array_index2790 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_array_index2798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_array_range2806 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_DECIMAL_LITERAL_in_array_range2808 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_array_range2810 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_DECIMAL_LITERAL_in_array_range2812 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_array_range2814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEX_LITERAL_in_constant2831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OCTAL_LITERAL_in_constant2843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECIMAL_LITERAL_in_constant2855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARACTER_LITERAL_in_constant2865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_constant2873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BINARY_LITERAL_in_constant2883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_assignment_operator2934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_operator2976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_operator2980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_operator2985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_operator2990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_operator2995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_operator2999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_operator3004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_jump_statement3170 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_jump_statement3172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_jump_statement3177 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SEMICOLON_in_jump_statement3179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_synpred10_GorillaPP_grammar211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_offload_statement_in_synpred11_GorillaPP_grammar228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_synpred23_GorillaPP_grammar528 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_synpred23_GorillaPP_grammar558 = new BitSet(new long[]{0x0000001100000000L});
    public static final BitSet FOLLOW_36_in_synpred23_GorillaPP_grammar574 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_synpred23_GorillaPP_grammar578 = new BitSet(new long[]{0x0000001100000000L});
    public static final BitSet FOLLOW_32_in_synpred23_GorillaPP_grammar595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sel_expression_in_synpred67_GorillaPP_grammar1945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_expression_in_synpred68_GorillaPP_grammar1950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_synpred87_GorillaPP_grammar2508 = new BitSet(new long[]{0x4000000000004000L,0x00000000000009C8L});
    public static final BitSet FOLLOW_type_name_in_synpred87_GorillaPP_grammar2510 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_synpred87_GorillaPP_grammar2514 = new BitSet(new long[]{0x0000006EC1485950L,0x0000000000010000L});
    public static final BitSet FOLLOW_cast_expression_in_synpred87_GorillaPP_grammar2527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred95_GorillaPP_grammar2720 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_fieldPostfix_in_synpred95_GorillaPP_grammar2725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred98_GorillaPP_grammar2754 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_array_index_in_synpred98_GorillaPP_grammar2757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred99_GorillaPP_grammar2765 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_array_range_in_synpred99_GorillaPP_grammar2768 = new BitSet(new long[]{0x0000000000000002L});

}
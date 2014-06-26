grammar GorillaPP_grammar_fp;
options {
  backtrack=true;
  memoize=true;
  k=2;
}

scope Symbols {
  Set types; 
  Set constant_macros; //list of constant macros
  List magilla_instrs; //list of magilla instructions
  Map magilla_next_instrs; //map of next magilla instructions for each instruction
  Map instr_of_offload; //map from each offload to an instruction
  Map global_lvalues_of_instr; //map from each instruction to a set containing all 
                               //global lvalues
  Set globals; //list of global variables
  Set currentGlobals; //list of global variables
  Set locals; //list of local variables
  Set jump_targets; //checking that jump targets are valid instructions cant be done 
                    //in single pass
  Set defined_labled; //lable definitions mainly used in protocol fields
  Set offload_ports;
  Map offload_ports_req_type;   //The request type for an offload port
  Map offload_ports_rep_type;   //The reply type for an offload port
  Map localsType; //The type of each local variable
  Map localsVersion; //For each local variable, we have a version number. 
                     //In case it gets reassigned to, the version will be incremented. 
                     //Not correct. Requires phi.
  String inputType;  
  String outputType;  
  String typeName;  //We carry the type name, so we can define the global variables with 
                    //correct type in register
  String offloadPorts_string; //list of offload ports 
  String IO_string;  //String that contains the Chisel interface definition of the module
  String definition_string;  //definition of global variables and instruction numbers
  String type_definition_string;  //definition of types. After type definition part flushed 
                                  //into definition_string 
  String macro_string;  //temporary buffer to fill the macro expression 
  String global_macro_string;  //buffer to fill the macro expression 
  String global_request_builder_string;
  String request_builder_string;
  String global_context_edit_string;
  String context_edit_string;
  String combinational_string; //assignement to local variables goes here
  String instr_string;  //list of instructions
  String off_string;  //the buffer we use for offload argument expression
  String global_lvalue_string; //The string containing the list of global lvalues for each
                               // instruction
  String magilla_instr_name;
  String prev_magilla_instr_name;
  OUT_STREAM out_stream;
  boolean Is_preOff; //Are we before offloading(s)?
  boolean Is_postOff; //Are we after offloading(s)?
  boolean localLvalue; //Is the Lvalue a local variable. For local varaibles code is 
                       //generated as combinational.
                         //Whereas for global variables codes is generated as context edit
  boolean StateLvalue;  //Whether the current statement is assigning to 
                        //State to extract the next state
  String off_id; //the id of offload operation is stored here 
  boolean Is_local; //differentiate between local and global variables, 
                    //set to one when we enter the instruction
  int instrNumber;  //The index of instruction to initialize their index value
  Stack condStack;
  boolean Is_type_def;
  boolean Is_global_type_def;
  boolean Is_macro;
  boolean globalInitialized; //a helper variable that is true if current global 
                            //definition has initialization;
  boolean isIfStatement;

}

@header {
  import java.util.Set;
  import java.util.HashSet;
  import java.util.Iterator;
  import java.io.*;
}

@members {
  public enum OUT_STREAM { REQUEST_BUILDER, CONTEXT_EDIT, JUMP};
  void   set_output_string(OUT_STREAM os) {
      Symbols_scope scope = (Symbols_scope)Symbols_stack.get(0);
      scope.out_stream = os;    
  }
  
  String C2ChiselType(String s) {
    if (s.equals("int")) { 
      return ("UFix(width = 32)");
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
    return ("new " + s);
  }

  String Ctype2ChiselInit(String type, String init) {
    if (type.equals("int")) { 
      return ("UFix(" + init + ", width = 32)");
    }
    if (type.equals("uint8_t")) { 
      return ("UFix(" + init + ", width = 8)");
    }
    if (type.equals("uint16_t")) { 
      return ("UFix(" + init + ", width = 16)");
    }
    if (type.equals("uint32_t")) { 
      return ("UFix(" + init + ", width = 32)");
    }
    if (type.equals("uint64_t")) { 
      return ("UFix(" + init + ", width = 64)");
    }
    return ("");
  }

  String versioned(String s) {
    if ($Symbols::localsVersion.containsKey(s) &&  
      ((Integer) $Symbols::localsVersion.get(s)) != 0) { 
      return (s + "_" + $Symbols::localsVersion.get(s).toString());
    }
    return (s);
  }

  void out_string(String s) {
      if ($Symbols::Is_macro) {
          $Symbols::macro_string += s;
      } else if ($Symbols::localLvalue) 
          $Symbols::combinational_string += s;
      else if ($Symbols::Is_preOff)
          //$Symbols::request_builder_string += s;
          $Symbols::context_edit_string += s;
      else if ($Symbols::Is_postOff)
          $Symbols::context_edit_string += s;    
      else  
          $Symbols::off_string +=s ; 
  }

  void handle_lValue (String idString) {
    if (local_var_defined(idString)) {
      $Symbols::localLvalue = true;
      if ($Symbols::localsVersion.containsKey(idString)) {
      //  System.out.println("Gorilla++Error: No reassignment to local variable " + idString);
      } else {
        $Symbols::localsVersion.put(idString, 0);
        out_string("val " + idString);
      }
    } else {
      String idPrint;
//      if (idString.equals("Output")){
//        idPrint = "outputReg";
//      } else {
        idPrint = idString;
        if (!$Symbols::global_lvalues_of_instr.containsKey($Symbols::magilla_instr_name)){
          System.out.println("Gorilla++Error: cannot find instr to assign lvalue");
        } else {
          HashSet global_lvalues = (HashSet<String>) $Symbols::global_lvalues_of_instr.get(
           $Symbols::magilla_instr_name); 
          if (!global_lvalues.contains(idString)) {
            global_lvalues.add(idString);
          }
        } 
//      }
      if (idString.equals("State") && $Symbols::StateLvalue) {
        System.out.println("Gorilla++Error: not pipelinable engine more than one jump for" +
          "instr " +  $Symbols::magilla_instr_name); 
      } else if (idString.equals("State")) {
        if ($Symbols::isIfStatement) {
          System.out.println("Gorilla++Error: not pipelinable engine " +
          "State is assigned inside if statement in instr " +  
          $Symbols::magilla_instr_name); 
        }
        $Symbols::StateLvalue = true;
      }
      out_string(idPrint);
    }
  }

  void handle_instr_header(String magilla_instr_name_string) {    
    if(mag_instr_defined(magilla_instr_name_string)) {
      System.out.println("Redundant magilla instruction detected");
    } else {
      $Symbols::magilla_instrs.add(magilla_instr_name_string);
      $Symbols::global_lvalues_of_instr.put(magilla_instr_name_string, 
        new HashSet <String>());
    }
    $Symbols::definition_string += "val " + magilla_instr_name_string + 
     " = " + "UFix(" + $Symbols::instrNumber + ")\n";
    $Symbols::prev_magilla_instr_name = $Symbols::magilla_instr_name;
    $Symbols::magilla_instr_name = magilla_instr_name_string;
    if ($Symbols::instrNumber != 1) {
      if (!$Symbols::magilla_next_instrs.containsKey($Symbols::prev_magilla_instr_name)) {
        System.out.println("Gorilla++Error: previous instr " + 
         $Symbols::prev_magilla_instr_name + " does not exist instr number is " + 
         $Symbols::instrNumber);
      } else if (!$Symbols::magilla_next_instrs.get(
       $Symbols::prev_magilla_instr_name).equals(magilla_instr_name_string)) {
        System.out.println(
         "Gorilla++Error: not pipelinable engine jumps are not sequential");
      }
    } 
    $Symbols::instrNumber++;
    $Symbols::Is_preOff = true;
    $Symbols::Is_postOff = false;
    $Symbols::Is_local = true;
    $Symbols::request_builder_string = ""; 
    $Symbols::context_edit_string = ""; 
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

  boolean isconstant_macro(String name) {
      for (int i = Symbols_stack.size()-1; i>=0; i--) {
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          if ( scope.constant_macros.contains(name) ) {
              return true;
          }
      }
      return false;
  }

  boolean mag_instr_defined(String name) {
      for (int i = Symbols_stack.size()-1; i>=0; i--) {
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          if ( scope.magilla_instrs.contains(name) ) {
              return true;
          }
      }
      return false;
  }

  boolean global_var_defined(String name) {
      for (int i = Symbols_stack.size()-1; i>=0; i--) {
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          if ( scope.globals.contains(name) ) {
              return true;
          }
      }
      return false;
  }

  boolean local_var_defined(String name) {
      for (int i = Symbols_stack.size()-1; i>=0; i--) {
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          if ( scope.locals.contains(name) ) {
              return true;
          }
      }
      return false;
  }

  boolean offPort_defined(String name) {
      for (int i = Symbols_stack.size()-1; i>=0; i--) {
          Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
          if ( scope.offload_ports.contains(name) ) {
              return true;
          }
      }
      return false;
  }

  public void generate_code() {
    System.out.println("//First pass for Magilla piper compiler");
    //System.out.println("//List of Gorilla processing steps:");
    for (int i = Symbols_stack.size()-1; i>=0; i--) {
      Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
      Iterator instr_it = scope.magilla_instrs.iterator();
      //build instr strings which contains instr names, next instr, 
      //and offload calls per instr
      while (instr_it.hasNext()) {
        String instr = instr_it.next().toString();
        // Add instr name and next instr
        if ($Symbols::magilla_next_instrs.containsKey(instr)) {
          $Symbols::instr_string += instr + " " + 
           $Symbols::magilla_next_instrs.get(instr) + " ";
        } else {
          $Symbols::instr_string += instr + " noNextInstr ";
        } 
        // Add offload ports
        if ($Symbols::instr_of_offload.containsValue(instr)) {
          for (Map.Entry<String, String> entry : 
           (Set<Map.Entry<String, String>>) 
           scope.instr_of_offload.entrySet()) {
            if (entry.getValue().equals(instr)) {
              $Symbols::instr_string += entry.getKey() + " ";
            }
          }
        } else {
          $Symbols::instr_string += " hasNoOffload";
        } 
        $Symbols::instr_string += "\n";
      } 
      //Build global_lvalues_string which contains instr and all global lvalues per instr
      Iterator instr_it_1 = scope.magilla_instrs.iterator();
      while (instr_it_1.hasNext()) {
        String instr = instr_it_1.next().toString();
        $Symbols::global_lvalue_string  += instr + " ";
        for (String entry: (Set<String>) $Symbols::global_lvalues_of_instr.get(instr)) {
          $Symbols::global_lvalue_string += entry + " ";
        } 
        $Symbols::global_lvalue_string += "\n";
      }
    }
    Symbols_scope scope = (Symbols_scope)Symbols_stack.get(0);
    //Write instruction string to file 
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter("instr.magillac"));
      out.write(scope.instr_string);
      out.close();
    } catch (IOException e) {
    }
    //Write global lvalue string to file  
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter("global_lvalues.magillac"));
      out.write(scope.global_lvalue_string);
      out.close();
    } catch (IOException e) {
    }
  }
}

translation_unit
scope Symbols; // entire file is a scope
@init {
  $Symbols::constant_macros = new HashSet();
  $Symbols::types = new HashSet();
  $Symbols::magilla_instrs = new ArrayList();
  $Symbols::magilla_next_instrs = new HashMap<String, String> ();
  $Symbols::instr_of_offload = new HashMap<String, String> ();
  $Symbols::global_lvalues_of_instr = new HashMap<String, Set<String>> ();
  $Symbols::globals = new HashSet();
  $Symbols::currentGlobals = new HashSet();
  $Symbols::locals = new HashSet();
  $Symbols::offload_ports = new HashSet();
  $Symbols::offload_ports_req_type = new HashMap<String, String> ();
  $Symbols::offload_ports_rep_type = new HashMap<String, String> ();
  $Symbols::localsVersion = new HashMap<String, Integer> ();
  $Symbols::localsType = new HashMap<String, String> ();
  $Symbols::request_builder_string = "";
  $Symbols::global_request_builder_string = "";
  $Symbols::context_edit_string = ""; 
  $Symbols::global_context_edit_string = ""; 
  $Symbols::IO_string = "";                
  $Symbols::offloadPorts_string = "";                 
  $Symbols::macro_string = "";            
  $Symbols::global_macro_string = "";            
  $Symbols::type_definition_string = "";            
  $Symbols::definition_string = "";            
  $Symbols::instr_string = "";                 
  $Symbols::combinational_string = "";
  $Symbols::global_lvalue_string = "";
  $Symbols::Is_preOff = true;
  $Symbols::Is_postOff = false;
  $Symbols::Is_local = false;
  $Symbols::Is_type_def = false;
  $Symbols::Is_global_type_def = false;
  $Symbols::localLvalue = false;
  $Symbols::StateLvalue = false;
  $Symbols::Is_macro = false;
  $Symbols::instrNumber = 1;
  $Symbols::condStack = new Stack <String> ();
  $Symbols::isIfStatement = false;
}
    : external_declaration {generate_code();};


external_declaration 
  : ({$Symbols::Is_global_type_def = true;}
     (type_definition|define)*) 
     {$Symbols::Is_global_type_def = true; 
      $Symbols::definition_string = $Symbols::type_definition_string;}| 
     (in_pragma out_pragma off_pragma* other_pragma* {$Symbols::IO_string += "))";} 
      global_declaration* {
          $Symbols::global_context_edit_string += 
            "when (SubState === WaitForValid && AllOffloadsValid) {";
          $Symbols::definition_string += 
            "val inputReg = Reg(" + C2ChiselType($Symbols::inputType) +  ")\n";
          $Symbols::definition_string += 
            "val outputReg = Reg(" + C2ChiselType($Symbols::outputType) +  ")\n";
          Iterator off_it = $Symbols::offload_ports.iterator();
          while (off_it.hasNext()) {
            String offPort = (String) off_it.next();
            $Symbols::definition_string += 
              "def  myOff = io.elements.find(_._1 == \"off\").getOrElse(elseV)._2\n";
            $Symbols::definition_string += 
              "def mymyOff = myOff.asInstanceOf[Bundle].elements.find(_._1 == \"" + offPort + 
              "\").getOrElse(elseV)._2\n"; 
            $Symbols::definition_string += 
              "val " + offPort + "Port = " + "new gOffBundleND(() => " + 
              $Symbols::offload_ports_req_type.get(offPort) + 
              ", () => " + $Symbols::offload_ports_rep_type.get(offPort) + ")\n";
            $Symbols::definition_string += 
              offPort + "Port <>" + "mymyOff\n";
          } 
       } magilla_instr+ {
         $Symbols::global_context_edit_string += " SubState := WaitForReady \n } \n"; 
         //TODO: We can check to see if we really need to Context Edit
       });

global_declaration
    :  define | global_variable_definition 
    //type_definition
    ;
    
magilla_instr 
    :  MAGILLA_INSTR_NAME {handle_instr_header($MAGILLA_INSTR_NAME.text);}
     ('('')') '{' instr_body'}' {    
      $Symbols::global_request_builder_string += "\n when (State === " + $MAGILLA_INSTR_NAME.text ;    
      $Symbols::global_context_edit_string += "\n when (State === " + $MAGILLA_INSTR_NAME.text;    
      //List of 
      $Symbols::global_request_builder_string +=  " && AllOffloadsReady)" + "{\n";    
      $Symbols::global_context_edit_string +=  ")" +  "{\n";    
      
      $Symbols::global_request_builder_string += $Symbols::request_builder_string;    
      $Symbols::global_context_edit_string += $Symbols::context_edit_string;    
      $Symbols::global_request_builder_string += "} \n";    
      $Symbols::global_context_edit_string += "} \n";    
      $Symbols::locals.clear();
      $Symbols::localsVersion.clear();
      $Symbols::localsType.clear();
    };

instr_body scope {
  int num_of_FU_reqs;
}
@init {
  $instr_body::num_of_FU_reqs = 0;
}
    :  local_variable_definition* statement* {$Symbols::Is_preOff = false;} offload_statement* {$Symbols::Is_postOff = true; } statement* finish_statement?; 
        

local_variable_definition
    :  ((type_specifier {$Symbols::typeName = $type_specifier.text;} variablelist) | ('bit_vector' bv_index ID)) ';' ; 

global_variable_definition
    :  ((type_specifier {$Symbols::globalInitialized = false; 
                              $Symbols::typeName = $type_specifier.text; 
                              $Symbols::currentGlobals.clear();} 
          variablelist) 
          (bv_index)? | ('bit_vector' bv_index ID)) 
          ( {$Symbols::globalInitialized = true;} '=' constant)? ';' {
            Iterator variable_it = $Symbols::currentGlobals.iterator();
            while (variable_it.hasNext()) {
              $Symbols::definition_string +=  "val " + variable_it.next() + " = Reg(";
              if ($Symbols::globalInitialized) {
                $Symbols::definition_string += "resetVal=" + 
                  Ctype2ChiselInit($Symbols::typeName, $constant.text) + ")\t//Global variable \n";
              } else {
                $Symbols::definition_string += C2ChiselType($Symbols::typeName) + ")\t//Global variable \n";
              }
            } 
          } ;

type_definition 
    :  'typedef' type_specifier_struct ID {
        $Symbols::types.add($ID.text);
        //For now we are only supporting typedef for structs
        $Symbols::type_definition_string += "class " + $ID.text + " extends Bundle { \n" + $Symbols::definition_string + "}\n"; 
        System.out.println("//define type "+$ID.text);
        $Symbols::definition_string = "";
    } ';' ; 

pragma
    : off_pragma | in_pragma | out_pragma | other_pragma;

other_pragma
	: '#pragma' ('CONCURRENT_SAFE' | 'PIPELINABLE')
	;

in_pragma
    : '#pragma' 'INPUT'   in_type=ID  {    
        $Symbols::IO_string += "(() => " + C2ChiselType($in_type.text) + ")";
        $Symbols::inputType = $in_type.text;
    };

out_pragma
    : '#pragma' 'OUTPUT'  out_type=ID  {    
        $Symbols::IO_string += "(() => " + C2ChiselType($out_type.text) + ") (ArrayBuffer(";
        $Symbols::outputType = $out_type.text;
    };

off_pragma 
    : '#pragma' 'OFFLOAD' '(' offPort=ID ',' req_type=ID ',' rep_type=ID ')' {    
      if(offPort_defined($offPort.text)) 
        System.out.println("Redundant functional unit detected");
         else {
        $Symbols::offloadPorts_string += $offPort.text;
        $Symbols::offload_ports.add($offPort.text);
        $Symbols::offload_ports_req_type.put($offPort.text, C2ChiselType($req_type.text));
        $Symbols::offload_ports_rep_type.put($offPort.text, C2ChiselType($rep_type.text));
        $Symbols::IO_string += "(\"" + $offPort.text  + "\", () => " + C2ChiselType($req_type.text) + " , () => " + C2ChiselType($rep_type.text) + ")";
      }
    };

define
    : '#define' ID  {$Symbols::Is_macro = true;} constant_expression{
                $Symbols::constant_macros.add($ID.text);
                System.out.println("//constant macro definition for " + $ID.text);
                if ($Symbols::Is_global_type_def) {
                  $Symbols::global_macro_string +=  "val\t" + $ID.text + " = " + $Symbols::macro_string + "//constant macro definition \n";
                } else {
                  $Symbols::definition_string +=  "val\t" + $ID.text + " = " + $Symbols::macro_string + "//constant macro definition \n";
                }
                $Symbols::macro_string = "";
                $Symbols::Is_macro = false;
            };

declaration
scope {
  boolean isTypedef;
}
@init {
  $declaration::isTypedef = false;
}
    : 'typedef' {$declaration::isTypedef=true;}
       declaration_specifiers init_declarator_list ';' // special case, looking for typedef    
     | declaration_specifiers init_declarator_list ';'
    ;

init_declarator_list
    : init_declarator (',' init_declarator)*
    ;

init_declarator
    : declarator  ('=' initializer)?
    ;

declarator
    :  direct_declarator
    ;

direct_declarator
    :   (    ID
            {
                if ($declaration.size()>0&&$declaration::isTypedef) {
                    $Symbols::types.add($ID.text);
                    System.out.println("define type "+$ID.text);
                }
            }
        |    '(' declarator ')'
        )
        declarator_suffix*
    ;

declarator_suffix
    :   '[' constant_expression ']'
    |   '[' ']'
    |   '(' identifier_list ')'
    |   '(' ')'
    ;

identifier_list
    : ID (',' ID)*
    ;

declaration_specifiers
    :   type_specifier

    ;

initializer
    : assignment_expression
    | '{' initializer_list ','? '}'
    ;

initializer_list
    : initializer (',' initializer)*
    ;

define_lable
    : ID //TODO: make sure that the ID is define lable
    ;

offload_statement
    : offload_expression SEMICOLON  
    ;

statement
    :labeled_statement
    |expression_statement
    |selection_statement
    |compound_statement
    |jump_statement
    ;

compound_statement
    :'{' /*{out_string("begin\n");}*/ statement+ '}' /*{out_string("end\n");}*/
    ;

offload_expression
    :  {$Symbols::off_string = ""; } target=ID '=' off_id=ID {$Symbols::off_id += $off_id; }
       '(' conditional_expression ')' { 
         $Symbols::combinational_string += $off_id.text + "Port.req.bits" + " := " + 
           $Symbols::off_string + "\n" ;
         $Symbols::combinational_string += $off_id.text + "Port.req.valid" + " := State === " + 
           $Symbols::magilla_instr_name + "\n" ;
         if (local_var_defined($target.text)) {
           $Symbols::combinational_string += "val " + $target.text + " = " +  $off_id.text + 
             "Port.rep.bits" + "\n" ;
         } else {
           $Symbols::context_edit_string += $target.text + " := " + $off_id.text + "Port.rep.bits" + "\n" ;
         }
         
         if (!$Symbols::instr_of_offload.containsKey($off_id.text)) {
           System.out.println("instr " + $Symbols::magilla_instr_name + 
            " offload " + $off_id.text + "\n");
           $Symbols::instr_of_offload.put($off_id.text, $Symbols::magilla_instr_name);
         }
       } ;


macro_expression 
    : {!$Symbols::offload_ports.contains(input.LT(1).getText())}? ID 
      {out_string($ID.text);}'('{out_string("(");} conditional_expression 
      (','{out_string(",");} conditional_expression)*')'
      {out_string(")");} ; //e.g. access fields of packet 


//TODO for chisel
labeled_statement
    : ID ':' statement
    | 'case'  constant_expression ':' {out_string(": begin\n");} /*{out_string(" begin\n");}*/ statement {out_string("end\n");}
    | 'default' ':' statement
    ;

expression_statement
scope {
  boolean isFU;
}
@init {
  $expression_statement::isFU = false;
}
    : SEMICOLON | expression SEMICOLON {
        out_string(" \n"); 
        $Symbols::localLvalue = false;
        $Symbols::StateLvalue = false;
      };

SEMICOLON : ';'  ;
//TODO chisel
selection_statement
    : 'if'  {$Symbols::isIfStatement= true;} '(' {out_string("when (");} 
       conditional_expression ')' 
       {out_string(") {\n");} statement  {out_string("}\n");}
       (options {k=1; backtrack=false;}:'else' {out_string(".otherwise {");} 
       {out_string("\n");} statement {out_string("}\n");})?
       {$Symbols::isIfStatement= false;}| 
       'switch' '(' {out_string("case (");} expression ')' 
       {out_string(") ");} statement {out_string("endcase\n");}
    ;

finish_statement
    : 'finish' '(' ')' SEMICOLON {out_string("State := WaitForOutputReady\n");}
    ;
//expr     
//    :variable operator expr;

variablelist 
    :  variable_in_def ( ',' variable_in_def)* ;
    
variable_in_def
    : variable  { 
        if(!$Symbols::Is_local && !$Symbols::Is_global_type_def) {
            if(global_var_defined($variable.text)) {
                System.out.println("Redundant definition of global variable");
            } else {
                $Symbols::globals.add($variable.text);
                $Symbols::currentGlobals.add($variable.text);
                //$Symbols::definition_string +=  "val " + $variable.text + " = Reg(" + C2ChiselType($Symbols::typeName) + ")\t//Global variable \n";
            }
        } else if (!$Symbols::Is_global_type_def){
          $Symbols::localsType.put($variable.text, $Symbols::typeName);
          $Symbols::locals.add($variable.text);
          //TODO: Redundancy check for local variable - scope maybe
            //Gorilla++ Todo: if local variable, you dont need to declare it in chisel
            //instead you should declare it at the first def point 
        } else if ($Symbols::Is_global_type_def) {
          $Symbols::definition_string +=  "val " + $variable.text + " = " + C2ChiselType($Symbols::typeName) + "\n";
        }
    };

variable
    : ID
    ;

type_name
    : specifier_qualifier_list //abstract_declarator?
    ;


specifier_qualifier_list
    : ( type_specifier )+
    ;

type_specifier_struct:
    type_specifier  
    | 'struct {' (type_specifier {$Symbols::typeName = $type_specifier.text;
                               /*$Symbols::definition_string += $type_specifier.text;*/} variablelist ';')+ '}'
    ;
type_specifier
    : 
     'char'
    | 'short'
    | 'int' 
    //| 'uint32_t' 
    | 'long'
    | 'unsigned'
    | enum_specifier
    | type_id
    ;

type_id
    :   /*{isTypeName(input.LT(1).getText())}?*/ ID  //We turn off this check so that including is not bugging us for now
    {System.out.println("//"+$ID.text+" is a type");} //Type checks will be done in chisel later, after all
    ;

abstract_declarator
    : direct_abstract_declarator
    ;

direct_abstract_declarator
    :  ( '(' abstract_declarator ')' | abstract_declarator_suffix ) abstract_declarator_suffix*
    ;

abstract_declarator_suffix
    :  '[' ']'
    |    '[' constant_expression ']'
    |    '(' ')'
    ;    
enum_specifier
options {k=3;}
    : 'enum' '{' enumerator_list '}'
    | 'enum' ID '{' enumerator_list '}'
    | 'enum' ID
    ;

enumerator_list
    : enumerator (',' enumerator)*
    ;

enumerator
    : ID ('=' constant_expression)?
    ;

argument_expression_list
    :   assignment_expression (',' assignment_expression)*
    ;

expression
//    : assignment_expression (',' assignment_expression)*     ;
//    : assignment_expression | variable operator expression  ;
    :  assignment_expression  
    ;    
concat_expression
    : '{'{out_string("{");} expression (','{out_string(",");} expression)* '}' {out_string("}");};
    

assignment_expression   
    : id=ID {handle_lValue($id.text);} 
      fieldPostfix assignment_operator 
      (trinary_expression | conditional_expression | macro_expression)    
      ;
  
trinary_expression
    :{out_string(" Mux(");} conditional_expression '?' {out_string(",");} conditional_expression ':' {out_string(",");} conditional_expression {out_string(")");}
    ; 
lvalue
    : unary_expression
    ;
constant_expression
    : conditional_expression
//    : constant
    ;

conditional_expression
    : logical_or_expression //('?'{out_string("?");} expression ':'{out_string(":");} conditional_expression)?
    ;
logical_or_expression
    : logical_and_expression ('||' {out_string("||");} logical_and_expression)*
    ;

logical_and_expression
    : inclusive_or_expression ('&&' {out_string("&&");} inclusive_or_expression)*
    ;

inclusive_or_expression
    : exclusive_or_expression ('|' {out_string("|");} exclusive_or_expression)*
    ;

exclusive_or_expression
    : and_expression ('^' {out_string("^");}and_expression)*
    ;

and_expression
    : equality_expression ('&' {out_string("&");}equality_expression)*
    ;
equality_expression
    : relational_expression (('=='{out_string("===");}|'!='{out_string("!=");}) relational_expression)*
    ;

relational_expression
    : shift_expression (('<'{out_string("<");}|'>'{out_string(">");}|'<='{out_string("<=");}|'>='{out_string(">=");}) shift_expression)*
    ;

shift_expression
    : additive_expression (('<<'{out_string("<<");}|'>>'{out_string(">>");}) additive_expression)*
    ;


additive_expression
    : (multiplicative_expression) ('+'{out_string("+");} multiplicative_expression | '-'{out_string("-");} multiplicative_expression)*
    ;

multiplicative_expression
    : (cast_expression) ('*' {out_string("*");}cast_expression | '/'{out_string("/");} cast_expression | '%'{out_string("\%");} cast_expression)*
    ;

cast_expression
    : '(' type_name ')' cast_expression
    | unary_expression
    ;


unary_expression
    : postfix_expression
    | '++' {out_string("++");}unary_expression
    | '--' {out_string("--");}unary_expression
    | unary_operator {if ($Symbols::Is_local) out_string($unary_operator.text);} cast_expression
//    | 'sizeof' unary_expression
//    | 'sizeof' '(' type_name ')'
    ;

postfix_expression
    :   primary_expression ('++' | '--' | '[' expression ']')* 
        //(   
        //'[' expression ']'
        //|   '(' ')'
        //|   '(' argument_expression_list ')'
        //| fieldPostfix          //|   '->' ID
        //  |(('.' ID)+)* //{out_string("." + $ID.text);}
        //|   '++'
        //|   '--'
        //)*
    ;

fieldPostfix
    :  ('.' ID {out_string("." + $ID.text);})*
    ;
primary_expression
    : ID  { String idPrint;
           if ($ID.text.equals("Input")) 
             idPrint = "inputReg";
           else 
             idPrint = $ID.text;
                      out_string(versioned(idPrint));}
      fieldPostfix
    | constant { /*out_string($constant.text);*/ }
    | '(' { out_string("("); }
        conditional_expression 
    ')' { out_string(")"); }
    | ID  bv_index {out_string(versioned($ID.text) + $bv_index.text);} 
    | ID  bv_range {out_string(versioned($ID.text) + $bv_range.text);}
    | MAGILLA_INSTR_NAME {
      if ($Symbols::StateLvalue) {
        $Symbols::magilla_next_instrs.put($Symbols::magilla_instr_name, 
         $MAGILLA_INSTR_NAME.text);
      }
      out_string($MAGILLA_INSTR_NAME.text);
    } ;

bv_index
    : '[' (DECIMAL_LITERAL | ID) ']';
bv_range
    : '[' DECIMAL_LITERAL ':' DECIMAL_LITERAL ']';
/*bv_index_low
    : DECIMAL_LITERAL;
bv_index_high
    :DECIMAL_LITERAL;
*/
constant
    :   hl=HEX_LITERAL {out_string("UFix(" + hex2decimal($hl.text.substring(2)) + ", width = 64)");}
    |   OCTAL_LITERAL
    |   dl=DECIMAL_LITERAL {out_string("UFix(" + $dl.text + ", width = 64)");}
    |    CHARACTER_LITERAL
    |    STRING_LITERAL
    |   BINARY_LITERAL 
    ;    
unary_operator
    : '&'
    | '*'
    | '+'
    | '-'
    | '~'
    | '!'
    ;

assignment_operator
    : '=' {if ($Symbols::localLvalue) out_string("="); else out_string(":=");}  //TODO: we need to have a boundry between request builder and context edit 
    | '*='
    | '/='
    | '%='
    | '+=' //{out_string("+=");}
    | '-=' //{out_string("+=");}
    | '<<='
    | '>>='
    | '&='
    | '^='
    | '|='
    ;

operator 
    :  '+'{out_string("+");} | '-'{out_string("-");} | '&'{out_string("&");} | '|'{out_string("|");}| '&&'{out_string("&&");} | '||'{out_string("||");};

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;

COMMENT
    :   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;


INSTR_RETURN_TYPE
    :  'instr_addr_t';
    
fragment LETTER    :  'a'..'z' | 'A'..'Z'
    ;
    
fragment DIGIT    :  '0'..'9'
    ;

MAGILLA_INSTR_NAME
    :  'GS_' ID;

jump_statement
    : /*'goto' ID ';'
    | */'continue' ';'
    | 'break' ';'
//    | 'return' ';'
//    | 'return' expression ';'
    ;

ID    :  LETTER (LETTER | DIGIT | '_')*
    ;
CHARACTER_LITERAL
    :   '\'' ( EscapeSequence | ~('\''|'\\') ) '\''
    ;

STRING_LITERAL
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
BinaryDigit : '0' .. '1';

fragment
IntegerTypeSuffix
    :  ('u'|'U')? ('l'|'L')
    //|    ('u'|'U')  ('l'|'L')?
    ;

HEX_LITERAL : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ;

BINARY_LITERAL : DECIMAL_LITERAL 'b' BinaryDigit+ ;

DECIMAL_LITERAL : ('-')? ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

OCTAL_LITERAL : '0' ('0'..'7')+ IntegerTypeSuffix? ;

fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   OctalEscape
    ;

fragment
OctalEscape
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;


 



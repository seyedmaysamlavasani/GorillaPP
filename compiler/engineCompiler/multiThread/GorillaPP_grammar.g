grammar GorillaPP_grammar;
options {
    backtrack=true;
    memoize=true;
    k=2;
}

scope Symbols {
  Set <String> types; 
  Set <String> constantMacros; //list of constant macros
  List <String> gorillaInstrs; //list of magilla instructions
  Map <String, String> instrOfOffload; //map from each offload to an instruction
  Set <String> globals; //list of global variables
  Set <String> currentGlobals; //list of global variables in 
                               //this single definition spearated 
                               //by comma 
  Set <String> locals; //list of local variables
  Set <String> jumpTargets; //checking that jump targets are 
                             //valid instructions cant be done in 
                             //single pass
  Set <String> offloadPorts;
  Map <String, String> offloadPortsReqType; //The request type 
                                               //for an offload port
  Map <String, String> offloadPortsRepType; //The reply type for 
                                               //an offload port
  Map <String, String> localsType; //The type of each local variable
  Map <String, String> typedefs; //map of typedefs. typedefs will be 
                                 //replaced by their originial type
  Set <String> externalMacros; //list of external macros defined in 
                               //header files
  Map <String, Integer> localsVersion; //For each local variable, 
                                       //we have a version number. 
                                       //In case it gets reassigned to, 
                                       //the version will be incremented. 
                                       //Not correct. Requires phi.
  Map <String, String> OffValidString; //This string keeps the conditions 
                                       //that should set the valid for a 
                                       //given offload port request
                                       //Since we might have multiple calls 
                                       //to a given offload we need this.
  Map <String, String> OffReqString;   //This string is the muxCase conditions 
                                       //and values that send the request bits 
                                       //to the offload.   
  String inputType;  
  String outputType;  
  String typeName;  //We carry the type name, so we can define the global 
                    //variables with correct type in register
  String offloadPortsString; //list of offload ports 
  String ioString;  //String that contains the Chisel interface definition 
                     //of the module
  String definitionString;  //definition of global variables and 
                             //instruction numbers
  String typeDefinitionString;  //definition of types. After type 
                                  //definition part flushed into 
                                  //definitionString 
  String macroString;  //temporary buffer to fill the macro expression 
  String globalMacroString;  //buffer to fill the macro expression 
  String globalRequestBuilderString;
  String requestBuilderString;
  String globalContextEditString;
  String contextEditString;
  String combinationalString; //assignement to local variables goes here
  String instrString;  //list of instructions to be 
                       //serialized into output file
  String offString;  //the buffer we use for offload argument expression
  String gorillaInstrName;
  OUT_STREAM outStream;
  boolean isPreOff; //Are we before offloading(s)?
  boolean isPostOff; //Are we after offloading(s)?

  boolean localLvalue; //Is the Lvalue a local variable. 
                       //For local varaibles code is generated 
                       //as combinational
                       //Whereas for global variables codes is 
                       //generated as context edit
  boolean colonAssign; 
  String offId; //The id of the offload operation is stored here 
  boolean isLocal; //Differentiate between local and global variables, 
                    //set to one when we enter the instruction
  int instrNumber;  //The index of instruction to initialize 
                    //their index value
  Stack <String> condStack;  //The stack that contains
                             //preicates of (possibley
                             //conditionl, e.g. if,
                             //statements. TOS is the 
                             //associated with the clo
                             //clause, consequent elem
                             //conditions associated w
                             //conditional statements
  boolean isTypeDef;
  boolean isHeaderFile;
  boolean isMacro;
  boolean globalInitialized; //A helper variable that is true if 
                             //current global definition has initialization
  boolean firstOffload;
  boolean isMacroWithArgument;
  boolean isCasted; //Is this a casted operation. If yest we need to 
                    //flatten everyting by toBits and rebuilt the LHS
                    //using fromBits
  String macroId;  //The id of a macro definition
  boolean isPredicate; //Are we in the condition part of the conditional 
                       //statements
  String predicateString;  //The string containing the current predicate
}

@header {
  import java.util.Set;
  import java.util.HashSet;
  import java.util.Iterator;
  import java.io.*;
}

@members {
  public enum OUT_STREAM { REQUEST_BUILDER, CONTEXT_EDIT, JUMP};
  String c2ChiselType(String type) {
    String s;
    if ($Symbols::typedefs.containsKey(type)) {
          s = (String) $Symbols::typedefs.get(type);
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
    if ($Symbols::typedefs.containsKey(type)) {
          s = (String) $Symbols::typedefs.get(type);
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
    if ($Symbols::localsVersion.containsKey(s) && 
     ((Integer) $Symbols::localsVersion.get(s)) != 0) { 
      return (s + "_" + $Symbols::localsVersion.get(s).toString());
    }
    return (s);
  }
  
  void outString(String s) {
    if ($Symbols::isMacro) {
      $Symbols::macroString += s;
    } else if ($Symbols::localLvalue) { 
      $Symbols::combinationalString += s;
    } else if ($Symbols::isPreOff) {
      $Symbols::contextEditString += s;
      //$Symbols::requestBuilderString += s;
    } else if ($Symbols::isPostOff) {
      $Symbols::contextEditString += s;	
    } else {  
      $Symbols::offString +=s ; 
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
    return (!$Symbols::instrOfOffload.containsValue(instr));
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
              $Symbols::instrOfOffload.put(splitted[i], splitted[0]);
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
        $Symbols::instrString += instr_it.next() + "\n";
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
}
// $<declration group

translation_unit
scope Symbols; // entire file is a scope
@init {
  $Symbols::constantMacros = new HashSet <String> ();
  $Symbols::types = new HashSet<String> ();
  $Symbols::gorillaInstrs = new ArrayList <String> ();
  $Symbols::instrOfOffload = new HashMap<String, String> ();
  $Symbols::globals = new HashSet <String> ();
  $Symbols::currentGlobals = new HashSet <String> ();
  $Symbols::locals = new HashSet <String> ();
  $Symbols::offloadPorts = new <String> HashSet();
  $Symbols::offloadPortsReqType = new HashMap<String, String> ();
  $Symbols::offloadPortsRepType = new HashMap<String, String> ();
  $Symbols::localsVersion = new HashMap<String, Integer> ();
  $Symbols::localsType = new HashMap<String, String> ();
  $Symbols::typedefs = new HashMap<String, String> ();
  $Symbols::externalMacros = new HashSet<String> ();
  $Symbols::OffValidString = new HashMap<String, String> ();
  $Symbols::OffReqString = new HashMap<String, String> ();
  $Symbols::requestBuilderString = "";
  $Symbols::globalRequestBuilderString = "";
  $Symbols::contextEditString = ""; 
  $Symbols::globalContextEditString = ""; 
  $Symbols::ioString = "";				
  $Symbols::offloadPortsString = "";				 
  $Symbols::macroString = "";			
  $Symbols::globalMacroString = "";			
  $Symbols::typeDefinitionString = "";			
  $Symbols::definitionString = "";			
  $Symbols::instrString = "";				 
  $Symbols::combinationalString = "";
  $Symbols::isPreOff = true;
  $Symbols::isPostOff = false;
  $Symbols::isLocal = false;
  $Symbols::isTypeDef = false;
  $Symbols::isHeaderFile = false;
  $Symbols::localLvalue = false;
  $Symbols::colonAssign = true;
  $Symbols::isMacro = false;
  $Symbols::instrNumber = 1;
  $Symbols::condStack = new Stack <String> ();
  $Symbols::predicateString = "";
  $Symbols::firstOffload = true;
  $Symbols::isPredicate = false;
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
      $Symbols::typedefs.put(types[1], types[0]);
      line = in.readLine();
    }
    in.close();
    
    BufferedReader in1 = new BufferedReader(
     new FileReader("macros.magillac"));
    String line1 = in1.readLine();
    while (line1 != null) {
      $Symbols::externalMacros.add(line1);
      line1 = in1.readLine();
    }
    in1.close();
  } catch (IOException e) {
  }
  readInstrsMagillaC();
}
	:  external_declaration {generateCode();};

// $>

external_declaration 
	:  ({$Symbols::isHeaderFile = true;}
       (type_definition|define)*) {
         $Symbols::isHeaderFile = true; 
         $Symbols::definitionString = 
         $Symbols::typeDefinitionString;}| 
       (in_pragma out_pragma off_pragma* other_pragma* {
         $Symbols::ioString += "))";
         Iterator off_it = 
          $Symbols::offloadPorts.iterator();
  	     while (off_it.hasNext()) {
           String offPort = (String) off_it.next();
           $Symbols::OffValidString.put(offPort, "");
           $Symbols::OffReqString.put(offPort, "");
         } 
       } global_declaration* {
	     $Symbols::definitionString += 
          "val inputReg = Vec(numOfThreads) {Reg(" + 
          c2ChiselType($Symbols::inputType) +  ")}\n";
	     $Symbols::definitionString += 
          "val outputReg = Vec(numOfThreads) {Reg(" + 
          c2ChiselType($Symbols::outputType) +  ")}\n";
       	 Iterator off_it = 
          $Symbols::offloadPorts.iterator();
         while (off_it.hasNext()) {
           String offPort = (String) off_it.next();
           $Symbols::definitionString += 
            "def mymyOff" + offPort+ 
            " = myOff.asInstanceOf[Bundle]." + 
            "elements.find" + 
            "(_._1 == \"" + offPort + 
            "\").getOrElse(elseV)._2\n"; 
	       $Symbols::definitionString += 
            "val " + offPort + 
            "Port = new gOffBundleND(() => " + 
            $Symbols::offloadPortsReqType.get(offPort) + 
            ", () => " + $Symbols::offloadPortsRepType.
             get(offPort) + ")\n";
	       $Symbols::definitionString += 
            offPort + "Port <>" + " mymyOff" + offPort + 
            "\n";
	      } 
	    } magilla_instr+ {
              Iterator off_it = 
              $Symbols::offloadPorts.iterator();
              while (off_it.hasNext()) {
               String offPort = (String) off_it.next();
               String OffValidString = (String) 
                $Symbols::OffValidString.get(offPort);
               String OffReqString = 
                (String) $Symbols::OffReqString.
                 get(offPort);
               $Symbols::combinationalString += 
                offPort + "Port.req.tag :=  rThread\n";  
               $Symbols::combinationalString += offPort + 
                "Port.req.valid :=  " + 
                "(rThread != NONE_SELECTED) && !" + 
                offPort + "_valid_received(rThread) && (" + 
                OffValidString + ")\n"; 
               $Symbols::combinationalString += offPort + 
                "Port.req.bits := MuxCase(UFix(0, 32)," + 
                "Seq(" + OffReqString + "))" + "\n";
              }
 	    });

global_declaration
	:	define | global_variable_definition 
    //type_definition
    ;
	
magilla_instr 
	: GORILLA_INSTR_NAME {	
            if(gorillaInstrDefined($GORILLA_INSTR_NAME.text)) {
	      System.out.println(
               "Redundant magilla instruction" + 
               " detected");
	    } else {
              $Symbols::gorillaInstrs.add(
              $GORILLA_INSTR_NAME.text);
	    }
            $Symbols::definitionString += "val " + 
            $GORILLA_INSTR_NAME.text + 
             " = " + "UFix(" + $Symbols::instrNumber + 
             ")\n";
            $Symbols::instrNumber++;
            $Symbols::gorillaInstrName = 
             $GORILLA_INSTR_NAME.text;
            $Symbols::isPreOff = true;
            $Symbols::isPostOff = false;
	    $Symbols::isLocal = true;
            $Symbols::requestBuilderString = ""; 
            $Symbols::contextEditString = ""; 
          } ('('')') '{' instr_body'}' {	
            $Symbols::globalRequestBuilderString += 
             "\n when (rThread != NONE_SELECTED &&" + 
             " State(rThread) === " + 
             $GORILLA_INSTR_NAME.text ;	
            $Symbols::globalContextEditString += 
             "\n when (vThread != NONE_SELECTED &&" +  
             "State(vThread) === " + 
             $GORILLA_INSTR_NAME.text;	
            $Symbols::globalRequestBuilderString +=  
             ")" + "{\n";	
            $Symbols::globalContextEditString +=  
             ")" +  "{\n";	
            $Symbols::globalRequestBuilderString += 
            $Symbols::requestBuilderString;	
            $Symbols::globalContextEditString += 
            $Symbols::contextEditString;	
            $Symbols::globalRequestBuilderString += 
             "} \n";	
	    $Symbols::globalContextEditString += 
             "} \n";	
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
	:  local_variable_definition* statement* {
             $Symbols::isPreOff = false;} 
           offload_statement* {
             $Symbols::isPostOff = true; } 
           statement* finish_statement?; 

local_variable_definition
	: ((type_specifier {
        $Symbols::typeName = $type_specifier.text;} 
      variablelist) | (ID array_index)) ';' {
    }
    ; 

global_variable_definition
	: ((type_specifier {
        $Symbols::globalInitialized = false; 
        $Symbols::typeName = $type_specifier.text; 
        $Symbols::currentGlobals.clear();} variablelist) 
          (array_index)? | ('bit_vector' array_index ID)) 
          ( {$Symbols::globalInitialized = true;} '=' 
           constant)? ';' {
             Iterator variable_it = 
              $Symbols::currentGlobals.iterator();
             while (variable_it.hasNext()) {
               $Symbols::definitionString +=  
                "val " + variable_it.next() + 
                " = Vec (numOfThreads) {Reg(";
               if ($Symbols::globalInitialized) {
                 $Symbols::definitionString += 
                 c2ChiselType($Symbols::typeName) + 
                  ", resetVal=" + 
                  Ctype2ChiselInit($Symbols::typeName, 
                   $constant.text) + 
                   ")}\t//Global variable \n";
              } else {
                $Symbols::definitionString += 
                 c2ChiselType($Symbols::typeName) + 
                 ")}\t//Global variable \n";
              }
            } 
          }
;

type_definition 
	: 'typedef' type_specifier_struct ID {
        $Symbols::types.add($ID.text);
        //For now we are only supporting typedef for structs
		$Symbols::typeDefinitionString += "class " + 
         $ID.text + " extends Bundle { \n" + 
         $Symbols::definitionString + "}\n"; 
         System.out.println("//define type "+$ID.text);
		$Symbols::definitionString = "";
	  } ';' ; 

pragma
	: off_pragma  | in_pragma | out_pragma | other_pragma;

other_pragma
	: '#pragma' ('CONCURRENT_SAFE' | 'PIPELINABLE')
	;

in_pragma
	: '#pragma' 'INPUT'   in_type=ID  {	
	    $Symbols::ioString += "(() => " + 
         c2ChiselType($in_type.text) + ")";
        $Symbols::inputType = $in_type.text;
	};

out_pragma
	: '#pragma' 'OUTPUT'  out_type=ID  {	
	    $Symbols::ioString += "(() => " + 
         c2ChiselType($out_type.text) + ") (ArrayBuffer(";
        $Symbols::outputType = $out_type.text;
	};

off_pragma 
	: '#pragma' 'OFFLOAD' '(' offPort=ID ',' 
      req_type=ID ',' rep_type=ID ')' {	
        if(offPortDefined($offPort.text)) 
		System.out.println(
         "Redundant functional unit detected");
   	  else {
		$Symbols::offloadPortsString += 
          $offPort.text + "\n";
		$Symbols::offloadPorts.add($offPort.text);
		$Symbols::offloadPortsReqType.put($offPort.text, 
         c2ChiselType($req_type.text));
		$Symbols::offloadPortsRepType.put($offPort.text, 
         c2ChiselType($rep_type.text));
        if ($Symbols::firstOffload) {
	      $Symbols::ioString += 
            "(\"" + $offPort.text  + "\", () => " + 
            c2ChiselType($req_type.text) + 
            " , () => " + c2ChiselType($rep_type.text) + ")";
          $Symbols::firstOffload = false;
        } else {
          $Symbols::ioString += 
           ", (\"" + $offPort.text  + "\", () => " + 
           c2ChiselType($req_type.text) + 
           " , () => " + c2ChiselType($rep_type.text) + ")";
        }
      }
	};

//There are two cases that we see paranteces in a definition 
//(i) when we are type casting the value part of a macro 
//(ii) when the macro has arguemnts. This rule differentes
//between these two.
defineArg
	: ('('{c2ChiselType(input.LT(1).getText()).contains("new") && 
           !isTypeName(input.LT(1).getText())}? 
           {$Symbols::isMacroWithArgument = true; 
           $Symbols::macroString += "def " + $Symbols::macroId + "(";}
           id1=ID {$Symbols::macroString += $id1.text+":"+"UFix";} 
          (',' id2=ID {$Symbols::macroString += ", " + 
           $id2.text+":"+"UFix";})* 
          ')'{$Symbols::macroString += ") = ";})?
	;

define
	: '#define' {$Symbols::isMacroWithArgument = false;} 
      id=ID {$Symbols::macroId = $id.text;} defineArg  
      {$Symbols::isMacro = true;} 
      (/*sel_expression |*/ constant_expression) {
	    $Symbols::constantMacros.add($id.text);
	    System.out.println("//constant macro definition for " + 
         $id.text);
        if ($Symbols::isHeaderFile) {
          if($Symbols::isMacroWithArgument) {
	        $Symbols::globalMacroString +=  
            $Symbols::macroString + 
            "//constant macro definition \n";
          } else {
	        $Symbols::globalMacroString +=  
             "val\t" + $id.text + " = " + 
             $Symbols::macroString + 
             "//constant macro definition \n";
          }
        } else {
          if($Symbols::isMacroWithArgument) {
	      $Symbols::definitionString +=  
            $Symbols::macroString + 
            "//constant macro definition \n";
          } else {
	        $Symbols::definitionString +=  "val\t" + 
             $id.text + " = " + $Symbols::macroString + 
             "//constant macro definition \n";
          }
        }
        $Symbols::macroString = "";
	    $Symbols::isMacro = false;
      };
declaration
scope {
  boolean isTypedef;
}
@init {
  $declaration::isTypedef = false;
}
	: 'typedef' {$declaration::isTypedef=true;}
	   declaration_specifiers init_declarator_list ';' 
           // special case, looking for typedef	
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
	: (ID {
	    if ($declaration.size()>0&&$declaration::isTypedef) {
            $Symbols::types.add($ID.text);
            System.out.println("define type "+$ID.text);
            }
          } 
        | '(' declarator ')') declarator_suffix*
	;

declarator_suffix
	: '[' constant_expression ']'
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
	:'{' statement+ '}'
	;

offload_expression
	: {$Symbols::offString = ""; } target=ID '=' offId=ID 
      {$Symbols::offId += $offId; } 
      '(' conditional_expression ')' { 
        if (!$Symbols::OffValidString.containsKey(
         $offId.text)) {
          System.out.println("Error: offload " + 
          $offId.text + " is not added yet");
        }
        if (!$Symbols::OffValidString.get(
         $offId.text).equals("")) {
          $Symbols::OffValidString.put($offId.text, 
          $Symbols::OffValidString.get($offId.text) + " || ");
          $Symbols::OffReqString.put($offId.text, 
          $Symbols::OffReqString.get($offId.text) + " , ");
        }
        $Symbols::OffValidString.put($offId.text, 
         $Symbols::OffValidString.get($offId.text) + 
         " (rThread != NONE_SELECTED && State(rThread)" +  
         " === " + $Symbols::gorillaInstrName + ")");
         $Symbols::OffReqString.put($offId.text, 
           $Symbols::OffReqString.get($offId.text) + 
           " ((rThread != NONE_SELECTED && State(rThread) === " + 
           $Symbols::gorillaInstrName + ")" + 
           "," +  $Symbols::offString + ")");
           if (localVarDefined($target.text) && 
            !c2ChiselType((String)$Symbols::localsType.get(
            $target.text)).contains("new")) {
             $Symbols::combinationalString += 
              "val " + $target.text + " = " +  $offId.text + 
              "Port.rep.bits" + "\n" ;
           } else if (localVarDefined($target.text) && 
             c2ChiselType((String)$Symbols::localsType.get(
              $target.text)).contains("new")){
              $Symbols::combinationalString += 
               $target.text + " := " + 
                 $offId.text + "Port.rep.bits" + "\n" ;
           } else {
             $Symbols::contextEditString += $target.text + "(vThread) := " + 
             $offId.text + "Port.rep.bits" + "\n" ;
           }
           }
	;

macro_expression //e.g. access fields of packet
	:  {!$Symbols::offloadPorts.contains(input.LT(1).getText())}? 
           ID {outString($ID.text);}'('{outString("(");} 
           conditional_expression (','{outString(",");} 
           conditional_expression)*')'{outString(")");} ;  
            
//TODO for chisel
labeled_statement
	: ID ':' statement
	| 'case'  constant_expression ':' 
          {outString(": begin\n");} 
          /*{outString(" begin\n");}*/ 
          statement {outString("end\n");}
	| 'default' ':' statement
	;

expression_statement
	: SEMICOLON | expression SEMICOLON {
            outString(" \n"); 
	    $Symbols::localLvalue = false;
            $Symbols::isCasted = false;
	  };

SEMICOLON : ';'  ;
selection_statement
	: 'if'  '(' {outString("when (");} 
          {$Symbols::isPredicate = true;}
          conditional_expression 
          {$Symbols::isPredicate = false;}
          /*| macro_expression*/ ')' {outString(") {\n");} 
          statement  {outString("}\n");}
          (options {k=1; backtrack=false;}:
         'else' {outString(".otherwise {");} 
         {outString("\n");} statement {outString("}\n");})?
	 //| 'switch' '(' {outString("case (");} expression ')' 
         // {outString(") ");} statement {outString("endcase\n");}
	;

finish_statement
    : 'finish' '(' ')' SEMICOLON {
      outString("EmitReturnState(vThread) := WaitForInputValid\n"); 
      outString("State(vThread) := WaitForOutputReady\n");
    } | 'emit' '(' (emitTarget = ID)? ')' SEMICOLON {
      outString("State(vThread) := WaitForOutputReady\n");
    }
    ;
//expr 	
//	:variable operator expr;

variablelist 
	:	variable_in_def ( ',' variable_in_def)* ;
	
variable_in_def
	: variable  { 
            if(!$Symbols::isLocal && !$Symbols::isHeaderFile) {
              if(globalVarDefined($variable.text)) {
                System.out.println("Redundant definition " +
                "of global variable");
              } else {
                $Symbols::globals.add($variable.text);
                $Symbols::currentGlobals.add($variable.text);
              }
            } else if (!$Symbols::isHeaderFile){
              $Symbols::localsType.put($variable.text, 
               $Symbols::typeName);
              $Symbols::locals.add($variable.text);
              //TODO: Redundancy check for local variable - 
              //Also, adding instruction name to variable
              //to make a new scope in each instruction.  
              //if simple local variable, you dont need to 
              //declare it in chisel instead you should declare 
              //it at the first def point. If a bundle variable 
              //however we declare it here and later we dont 
              //define it at first def site

              if (c2ChiselType($Symbols::typeName).contains("new")) {
                //It is a bundle
                $Symbols::definitionString += 
                 ("val " + $variable.text + " = new " + 
                 $Symbols::typeName + "\n");
              }  
            } else if ($Symbols::isHeaderFile) {
	      $Symbols::definitionString +=  
               "val " + $variable.text + " = " + 
               c2ChiselType($Symbols::typeName) + "\n";
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
    | 'struct {' (type_specifier {
      $Symbols::typeName = $type_specifier.text;
      /*$Symbols::definitionString += 
      $type_specifier.text;*/} 
    variablelist ';')+ '}'
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
    :   /*{isTypeName(input.LT(1).getText())}?*/ ID  
        //We turn off this check so including types from 
        //other files  is not bugging us for now
	{System.out.println("//"+$ID.text+" is a type");} 
        //Type checks will be done in chisel later, after all
    ;

abstract_declarator
	: direct_abstract_declarator
	;

direct_abstract_declarator
	:  ( '(' abstract_declarator ')' | 
           abstract_declarator_suffix ) 
           abstract_declarator_suffix*
	;

abstract_declarator_suffix
	:  '[' ']'
	|  '[' constant_expression ']'
	|  '(' ')'
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
//	: assignment_expression (',' assignment_expression)* 	;
//	: assignment_expression | variable operator expression  ;
	:  assignment_expression  
    ;	
concat_expression
	: '{'{outString("{");} 
          expression (','{outString(",");} expression)* '}' 
          {outString("}");};
	

assignment_expression   
	: lValueId=ID {
	    if (localVarDefined($lValueId.text)) {
              $Symbols::localsVersion.put($lValueId.text, 0);
              if (c2ChiselType((String)
               $Symbols::localsType.get(
               $lValueId.text)).contains("new")) {
                $Symbols::localLvalue = true;
                $Symbols::colonAssign = true;
                outString($lValueId.text);
              } else {
                $Symbols::localLvalue = true;
                $Symbols::colonAssign = false;
                outString("val " + $lValueId.text);
              } 
           } else {
             String idPrint;
             if ($lValueId.text.equals("Output")){
               idPrint = "outputReg(vThread)";
             } else {
               idPrint = $lValueId.text + "(vThread)";
             }
	     $Symbols::colonAssign = true;
	     outString(idPrint);
          }
        } fieldPostfix assignment_operator 
        (sel_expression |  conditional_expression | macro_expression)	
      ;
sel_expression: {outString(" Mux(");} 
                conditional_expression '?' 
                {outString(",");} conditional_expression ':' 
                {outString(",");} conditional_expression 
                {outString(")");}
      ;
	
lvalue
	: unary_expression
	;
constant_expression
	: conditional_expression
//	: constant
	;

conditional_expression
	: logical_or_expression //('?'{outString("?");} 
          //expression ':'{outString(":");} 
          //conditional_expression)?
	;
logical_or_expression
	: logical_and_expression ('||' {outString("||");} 
          logical_and_expression)*
	;

logical_and_expression
	: inclusive_or_expression ('&&' {outString("&&");} 
          inclusive_or_expression)*
	;

inclusive_or_expression
	: exclusive_or_expression ('|' {outString("|");} 
          exclusive_or_expression)*
	;

exclusive_or_expression
	: and_expression ('^' {outString("^");}and_expression)*
	;

and_expression
	: equality_expression ('&' {outString("&");}equality_expression)*
	;
equality_expression
	: relational_expression (('=='{outString("===");}|
          '!='{outString("!=");}) relational_expression)*
	;

relational_expression
	: shift_expression (('<'{outString("<");}|
          '>'{outString(">");}|'<='{outString("<=");}|
          '>='{outString(">=");}) shift_expression)*
	;

shift_expression
	: additive_expression (('<<'{outString("<<");}|
          '>>'{outString(">>");}) additive_expression)*
	;


additive_expression
	: (multiplicative_expression) ('+'{outString("+");} 
          multiplicative_expression | '-'{outString("-");} 
          multiplicative_expression)*
	;

multiplicative_expression
	: (cast_expression) ('*' {outString("*");} 
          cast_expression | 
          '/'{outString("/");} 
          cast_expression | '%'{outString("\%");} 
          cast_expression)*
	;

cast_expression
	: '(' type_name {
            $Symbols::isCasted = true;
            outString("(" + 
             c2ChiselType($type_name.text) +
             ")" +  ".fromBits(Bits(0, width=(" + 
             c2ChiselType($type_name.text) + 
             ").getWidth) | " +
             "(");
          } ')' 
          cast_expression { 
            outString(").toBits)");
          }
	  | unary_expression
	;


unary_expression
	: postfix_expression
	| '++' {outString("++");}unary_expression
	| '--' {outString("--");}unary_expression
	| unary_operator {if ($Symbols::isLocal) 
         outString($unary_operator.text);} cast_expression
        //| 'sizeof' unary_expression
        //| 'sizeof' '(' type_name ')'
	;

postfix_expression
	:   primary_expression ('++' | '--' | '[' expression ']')* 
        //(   
		//'[' expression ']'
        //|   '(' ')'
        //|   '(' argument_expression_list ')'
        //| fieldPostfix          //|   '->' ID
		//  |(('.' ID)+)* //{outString("." + $ID.text);}
        //|   '++'
        //|   '--'
        //)*
	;

fieldPostfix
	:  ('.' ID {outString("." + $ID.text);})*
	;
primary_expression
	: ID  { 
          String idPrint = "";
          if ($ID.text.equals("Input")) { 
            if ($Symbols::localLvalue) {
              if ($Symbols::isPreOff) {
                idPrint = "inputReg(rThread)";
              } else {
                idPrint = "inputReg(vThread)";
              }  //TODO what if none
            } else {
              if (!$Symbols::isPreOff && 
               !$Symbols::isPostOff) {
                idPrint = "inputReg(rThread)";
              } else {
                idPrint = "inputReg(vThread)";
              }
            }
          } else { 
            if ($ID.text.equals("true"))
              idPrint = "Bool(true)";
            else if ($ID.text.equals("false")) 
              idPrint = "Bool(false)";
            else { 
              if (!$Symbols::locals.contains($ID.text)) {
                if ($Symbols::isMacro || 
                  $Symbols::constantMacros.contains($ID.text) || 
                  $Symbols::externalMacros.contains($ID.text) || 
                  $ID.text.equals("WaitForInputValid")) {
                   idPrint = $ID.text;
	            } else if ($Symbols::localLvalue) {
                  if ($Symbols::isPreOff) {
                    idPrint = $ID.text + "(rThread)";
                  } else {
                    idPrint = $ID.text + "(vThread)";
                  }
                } else {
                  if (!$Symbols::isPreOff && !$Symbols::isPostOff) {
                    idPrint = $ID.text + "(rThread)";
                  } else {
                    idPrint = $ID.text + "(vThread)";
                  }
                }
              } else {
                idPrint = $ID.text; 
              }
            }
          }
          outString(idPrint); 
        } fieldPostfix
	| constant { /*outString($constant.text);*/ }
	| '(' { outString("("); }
		conditional_expression 
	')' { outString(")"); }
	| ID  array_index {outString($ID.text + $array_index.text);} 
	| ID  array_range {outString($ID.text + $array_range.text);}
	| GORILLA_INSTR_NAME {outString($GORILLA_INSTR_NAME.text);}
	;

array_index
	: '[' (DECIMAL_LITERAL | ID) ']';
array_range
	: '[' DECIMAL_LITERAL ':' DECIMAL_LITERAL ']';
/*array_index_low
	: DECIMAL_LITERAL;
array_index_high
	:DECIMAL_LITERAL;
*/
constant
    :   hl=HEX_LITERAL {outString("UFix(" + hex2decimal($hl.text.substring(2)) + ", width = 32)");}
    |   OCTAL_LITERAL
    |   dl=DECIMAL_LITERAL {outString("UFix(" + $dl.text + ", width = 32)");}
    |	CHARACTER_LITERAL
    |	STRING_LITERAL
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
	: '=' {
          if (!$Symbols::colonAssign) 
            outString("="); 
          else 
            outString(":=");
        }

        //| '*='
	//| '/='
	//| '%='
	//| '+=' //{outString("+=");}
	//| '-=' //{outString("+=");}
	//| '<<='
	//| '>>='
	//| '&='
	//| '^='
	//| '|='
	;

operator 
	:	'^'{outString("^");} |'+'{outString("+");} | '-'{outString("-");} | '&'{outString("&");} | '|'{outString("|");}| '&&'{outString("&&");} | '||'{outString("||");};

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;

COMMENT
    :   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;


INSTR_RETURN_TYPE
	:	'instr_addr_t';
	
fragment LETTER	:	'a'..'z' | 'A'..'Z'
	;
	
fragment DIGIT	:	'0'..'9'
	;

GORILLA_INSTR_NAME
	:	'GS_' ID;

jump_statement
	: /*'goto' ID ';'
	| */'continue' ';'
	| 'break' ';'
//	| 'return' ';'
//	| 'return' expression ';'
	;

ID	:	LETTER (LETTER | DIGIT | '_')*
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
	:	('u'|'U')? ('l'|'L')
	//|	('u'|'U')  ('l'|'L')?
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


 



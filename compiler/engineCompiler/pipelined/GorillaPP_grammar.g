grammar GorillaPP_grammar;
options {
  backtrack=true;
  memoize=true;
  k=2;
}

scope Symbols {
  Set <String> types; 
  Set <String> constantMacros; //list of constant macros
  List <String> instrs; //list of magilla instructions
  Map nextInstrs; //map of next magilla instructions for each instruction
  Map instrOfOffload; //map from each offload to an instruction
  Map instrGlobalLvalues; //map of instr to its global lvalues  
  Set <String> globals; //list of global variables
  Map <String, String> globalsFirstDef; //the first defination of each global variable
  Map <String, String> globalsLastUse; //the last use of each global variable
  Set <String> currentGlobals; //list of global variables in this single definition 
                               //spearated by comma 
  Set <String> locals; //list of local variables
  Set <String> jumpTargets; //checking that jump targets are valid instructions 
                             //cant be done in single pass
  Set <String> offloadPorts;
  Map <String, String> offloadPortsReqType;   //The request type for an offload port
  Map <String, String> offloadPortsRepType;   //The reply type for an offload port
  Map <String, String> localsType; //The type of each local variable
  Map <String, String> typedefs; //map of typedefs. typedefs will be replaced by 
                                 //their originial type
  Set <String> externalMacros; //list of external macros defined in header files
  Map <String, Integer> localsVersion; //For each local variable, we have a version 
                                       //number. 
                                       //In case it gets reassigned to, the version 
                                       //will be incremented. Not correct. Requires phi.
  Map <String, String> OffValidString; //This string keeps the conditions that should 
                                       //set the valid for a given offload port request.
                                       //Since we might have multiple calls to a given 
                                       //offload we need this.
  Map <String, String> OffReqString;   //This string is the muxCase conditions and values 
                                       //that send the request bits to the offload.   
  String inputType;  
  String outputType;  
  String typeName;  //We carry the type name, so we can define the global variables 
                    //with correct type in register
  String offloadPortsString; //list of offload ports 
  String ioString;  //String that contains the Chisel interface definition of the module
  String definitionString;  //definition of global variables and instruction numbers
  String typeDefinitionString;  //definition of types. After type definition part 
                                  //flushed into definitionString 
  String macroString;  //temporary buffer to fill the macro expression 
  String globalMacroString;  //buffer to fill the macro expression 
  String globalRequestBuildString;
  String requestBuildString;
  String globalContextEditString;
  String contextEditString;
  String combinationalString; //assignement to local variables goes here
  String instrString;  //list of instructions
  String offString;  //the buffer we use for offload argument expression
  String firstInstrName;
  String instrName;
  boolean isPreOff; //Are we before offloading(s)?
  boolean isPostOff; //Are we after offloading(s)?
  boolean localLvalue; //Is the Lvalue a local variable. For local variables code 
                       //is generated as combinational
                       //Whereas for global variables codes is generated as context edit
  boolean colonAssign; 
  String offId; //the id of offload operation is stored here 
  boolean isLocal; //differentiate between local and global variables, 
                    //set to one when we enter the instruction
  int instrNumber;  //The index of instruction to initialize their index value
  Stack <String> condStack;
  boolean isTypeDef;
  boolean isHeaderFile;
  boolean isMacro;
  boolean globalInitialized; //a helper variable that is true if current global 
                             //definition has initialization;
  boolean firstOffload;
  boolean macroWithArgument;
  boolean silentOutString;
  boolean isIfStatement;
}

@header {
  import java.util.Set;
  import java.util.HashSet;
  import java.util.Iterator;
  import java.io.*;
}

@members {
  String C2ChiselType(String type) {
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
    return ("");
  }

  //Not used for now
  String versioned(String s) {
    if ($Symbols::localsVersion.containsKey(s)&&  
     ((Integer) $Symbols::localsVersion.get(s)) != 0) { 
      return (s + "_" + $Symbols::localsVersion.get(s).toString());
    }
    return (s);
  }

  void outString(String s) {
    if (!$Symbols::silentOutString) {
      if ($Symbols::isMacro) {
        $Symbols::macroString += s;
      } else if ($Symbols::localLvalue) { 
        $Symbols::combinationalString += s;
      } else if ($Symbols::isPreOff) {
        $Symbols::contextEditString += s;
      } else if ($Symbols::isPostOff) {
        $Symbols::contextEditString += s;  
      } else {  
        $Symbols::offString +=s ; 
      }
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

  boolean isConstantMacro(String name) {
    for (int i = Symbols_stack.size()-1; i>=0; i--) {
      Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
      if ( scope.constantMacros.contains(name) ) {
        return true;
      }
    }
    return false;
  }

  boolean magInstrDefined(String name) {
    for (int i = Symbols_stack.size()-1; i>=0; i--) {
      Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
      if ( scope.instrs.contains(name) ) {
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

  boolean isFirstInstr(String instrName) {
    return instrName.equals($Symbols::firstInstrName);
  }

  boolean isLastInstr(String instr) {
    if ($Symbols::nextInstrs.containsKey(instr)) 
      return ($Symbols::nextInstrs.get(instr).equals("noNextInstr"));
    else  {
      System.out.println("Instr was not in nextInstr list");
      return false;
    }
  }

  //next stage name. if last instruction, next stage is outputReg
  //if not depending on whether next stage is SS or DS, the next 
  //stage is SS register or PreOff register
  String nsName(String instrName, String variableName) {
    if (!isLastInstr(instrName)) {
      if (isSS((String) $Symbols::nextInstrs.get(instrName))) {
        return ($Symbols::nextInstrs.get(instrName) + 
        "PRegSS_" + variableName);  
      } else {
        return ($Symbols::nextInstrs.get(instrName) + 
        "PRegPreOff_" + variableName);  
      }
    } else {
      if (!(variableName.equals("Output") || variableName.equals("tag") ||
       variableName.equals("valid") || variableName.equals("ready"))) {
        System.out.println("Gorilla++Error: cannot copy arbitrary" + 
         " variable " + variableName + " to output");
        return ("");
      } else {
        if (variableName.equals("Output")) 
          return ("outputReg_bits");
        else 
          return ("outputReg_" + variableName);
      }
    }
  }

  //current stage name of and SS instruction
  //if first stage, it points to input port. 
  String ssName(String instrName, String variableName) {
    if (isFirstInstr(instrName)) {
      if (variableName.equals("Input") || variableName.equals("tag") ||
       variableName.equals("valid")) {
        if (variableName.equals("Input")) {
          return("io.in.bits");
        } else {
          return("io.in." + variableName);
        } 
      } else {
        System.out.println("Gorilla++Error: cannot copy arbitrary" + 
          " variable " + variableName + " from input");
        return("");
      }
    } else {
      return(instrName + "PRegSS_" + variableName);
    }
  }
   
  //request builder name of a double stage instruciton.
  //if first instruction, it points to input port.
  String rbName(String instrName, String variableName) {
    if (isFirstInstr(instrName)) {
      if (variableName.equals("Input") || variableName.equals("tag") ||
       variableName.equals("valid")) {
        if (variableName.equals("Input")) {
          return("io.in.bits");
        } else {
          return("io.in." + variableName);
        } 
      } else {
        System.out.println("Gorilla++Error: cannot copy arbitrary" + 
          " variable " + variableName + " from input");
        return("");
      }
    } else {
      return(instrName + "PRegPreOff_" + variableName);
    }
  }

  //context edit stage of a double stage instruction
  //for read purpose
  String ceRvName(String instrName, String variableName) {
    return(instrName + "PRegPostOff_" + variableName + "(" + instrName + "VThread)");
  }

  //context edit stage of a double stage instruction
  //for write purpose
  String ceLvName(String instrName, String variableName) {
    return(instrName + "PRegPostOff_" + variableName + "(" + instrName + "RThread)");
  }

  //right value name. depends on whether we are 
  //at preOff, postOff, or in the middle of offloads
  //and also whether it is an SS or DS instruction.
  String rvName(String instrName, String variableName) {
    if (isSS(instrName)) {
      return (ssName(instrName, variableName));
    } else {
      if ($Symbols::isPreOff) {
        return (rbName(instrName, variableName));
      } else if ($Symbols::isPostOff) {
        return (ceRvName(instrName, variableName));
      } else {
        return (rbName(instrName, variableName));
      }
    }
  }

  //left value name 
  String lvName(String instrName, String variableName) {
    return (nsName(instrName, variableName));
  }

  void defaultCopySS(String instrName, String variableName) {
    $Symbols::globalContextEditString += 
     nsName(instrName, variableName) + 
     " := " + ssName(instrName, variableName)+ "\n";  
  }

  void defaultCopyDS(String instrName, String variableName) {
    $Symbols::globalContextEditString += 
     nsName(instrName, variableName) +  " := " + 
     ceRvName(instrName, variableName) + "\n"; 
    $Symbols::globalRequestBuildString += 
     ceLvName(instrName, variableName) + " := " + 
     rbName(instrName, variableName) + "\n";  
  }

  boolean isInScope(String instrName, String variableName) {
   //System.out.println("XXX isInScope: first def of " + 
   //  variableName + " is " + 
   //  $Symbols::globalsFirstDef.get(variableName));
   //System.out.println("XXX ");
   return ($Symbols::instrs.indexOf(
     $Symbols::globalsFirstDef.get(variableName)) < 
     $Symbols::instrs.indexOf(instrName)); 
  }

  boolean isDefInstr(String instrName, String variableName) {
   return ($Symbols::instrs.indexOf(
     $Symbols::globalsFirstDef.get(variableName)) == 
     $Symbols::instrs.indexOf(instrName)); 
  }

  void defaultCopy(String instrName, String variableName) {
    if (isInScope(instrName, variableName)) {
      if (isSS(instrName)) {
        defaultCopySS(instrName, variableName);
      } else {
        defaultCopyDS(instrName, variableName);
      }
    }
  }

  void defaultCopyAll(String instrName) {
    defaultCopy(instrName, "valid");
    defaultCopy(instrName, "tag");
    if (!isLastInstr(instrName))
      defaultCopy(instrName, "Input");
    if (!isFirstInstr(instrName) && isInScope(instrName, "Output")) {
      //System.out.println("XXX output is in scope " + instrName);
      defaultCopy(instrName, "Output");
    }
    //If a global variable doesnt have a def in this instruction,
    // copy it to the next stage
    Iterator globalsIt = $Symbols::globals.iterator();
    while (globalsIt.hasNext()) {
      String gv =globalsIt.next().toString();
      if (!$Symbols::instrGlobalLvalues.containsKey(instrName)) {
        System.out.println("Gorilla++Error: instr " + instrName + 
         " is not in lvalue hash");
      } else if (!((Set<String>)
       $Symbols::instrGlobalLvalues.get(instrName)).contains(gv)) {
         if (!isFirstInstr(instrName) && !isLastInstr(instrName)) 
         //TODO: FIX this add last use from first pass
           if (isInScope(instrName, gv))
             defaultCopy(instrName, gv);
      }
    } 
  }
 
  //What is your current stage valid if you 
  //are preOff
  String preOffValid(String instrName) {
    if (isFirstInstr(instrName)) {
      return ("io.in.valid");
    } else {
      return (instrName + "PRegPreOff_valid");
    }
  }

  //What is your next stage ready if you 
  //are preOff
  String preOffNextStageReady(String instrName) {
    return (instrName + "PRegPostOff_ready" +  
     " && " + instrName + 
     "AllOffloadsReady");
  }

  public void generateCode() {
    System.out.println(
     "//This Chisel code is generated Automatically using MagillaC compiler");
    for (int i = Symbols_stack.size()-1; i>=0; i--) {
      Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
      Iterator instrIt = scope.instrs.iterator();
      while (instrIt.hasNext()) {
        $Symbols::instrString += instrIt.next() + "\n";
      } 
    }
    System.out.println("//List of offload interfaces:");
    for (int i = Symbols_stack.size()-1; i>=0; i--) {
      Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
      Iterator fuIt = scope.offloadPorts.iterator();
      while (fuIt.hasNext()) {
        System.out.println("//"+fuIt.next());
      }
    }
    System.out.println("//List of Global variables:");
    for (int i = Symbols_stack.size()-1; i>=0; i--) {
      Symbols_scope scope = (Symbols_scope)Symbols_stack.get(i);
      Iterator globalIt = scope.globals.iterator();
      while (globalIt.hasNext()) {
        System.out.println("//"+ globalIt.next());
      }
    }
    Symbols_scope scope = (Symbols_scope)Symbols_stack.get(0);
    System.out.println("//definitions list");
    if (scope.definitionString != null) System.out.print(scope.definitionString);
    System.out.println("//context edit");
    if (scope.globalContextEditString != null) System.out.print(scope.globalContextEditString);
    System.out.println("//Combinational logic");
    if (scope.combinationalString != null) System.out.print(scope.combinationalString);
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter("definition.magillac"));
      out.write(scope.definitionString);
      out.close();
    } catch (IOException e) {
    }
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter("requestBuilder.magillac"));
      out.write(scope.globalRequestBuildString);
      out.close();
    } catch (IOException e) {
    }
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter("contextEdit.magillac"));
      out.write(scope.globalContextEditString);
      out.close();
    } catch (IOException e) {
    }
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter("combinational.magillac"));
      out.write(scope.combinationalString);
      out.close();
    } catch (IOException e) {
    }
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter("instr.magillac"));
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
      BufferedWriter out = new BufferedWriter(new FileWriter("offloadPorts.magillac"));
      out.write(scope.offloadPortsString);
      out.close();
    } catch (IOException e) {
    }
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter("macroDefintions.magillac"));
      out.write(scope.globalMacroString);
      out.close();
    } catch (IOException e) {
    }

  }

  void  handleVariableInDef(String variableString) { 
    if(!$Symbols::isLocal && !$Symbols::isHeaderFile) {
      if(globalVarDefined(variableString)) {
        System.out.println("Redundant definition of global variable");
      } else {
        $Symbols::globals.add(variableString);
        $Symbols::currentGlobals.add(variableString);
      }
    } else if (!$Symbols::isHeaderFile){
      $Symbols::localsType.put(variableString, $Symbols::typeName);
      $Symbols::locals.add(variableString);
      //TODO: Redundancy check for local variable - 
      //Also, adding instruction name to variable
      //to make a new scope in each instruction  
      //if simple local variable, you dont need to declare it in chisel
      //instead you should declare it at the first def point. If a bundle variable however
      // we declare it here and later we dont define it at first def site
      if (C2ChiselType($Symbols::typeName).contains("new")) {
        //It is a bundle
        $Symbols::definitionString += ("val " + variableString + " = new " + 
         $Symbols::typeName + "\n");
      }   
    } else if ($Symbols::isHeaderFile) {
      $Symbols::definitionString +=  
       "val " + variableString + " = " + C2ChiselType($Symbols::typeName) + "\n";
    }
  }

  void handlePreGlobalDeclaration() {
    $Symbols::ioString += "))";
    Iterator offIt = $Symbols::offloadPorts.iterator();
    while (offIt.hasNext()) {
      String offPort = (String) offIt.next();
      $Symbols::OffValidString.put(offPort, "");
      $Symbols::OffReqString.put(offPort, "");
    }
  }

  void handlePostGlobalDeclaration() {
    $Symbols::definitionString += "val outputReg_bits = Reg(" + 
     C2ChiselType($Symbols::outputType) +  ")\n";
    $Symbols::definitionString += "val outputReg_valid = Reg(" + 
     "resetVal=Bool(false))\n"; 
    $Symbols::definitionString += "val outputReg_tag = Reg(" + 
     "resetVal=UFix(width=tagWidth))\n"; 
    Iterator offIt = $Symbols::offloadPorts.iterator();
    while (offIt.hasNext()) {
      String offPort = (String) offIt.next();
      $Symbols::definitionString += 
       "def mymyOff" + offPort+ 
       " = myOff.asInstanceOf[Bundle].elements.find(_._1 == \"" + offPort + 
       "\").getOrElse(elseV)._2\n"; 
      $Symbols::definitionString += 
       "val " + offPort + "Port = new gOffBundleND(() => " + 
       $Symbols::offloadPortsReqType.get(offPort) + 
       ", () => " + $Symbols::offloadPortsRepType.get(offPort) + ")\n";
      $Symbols::definitionString += 
       offPort + "Port <>" + " mymyOff" + offPort + "\n";
    } 
  }

  void handlePostInstrs() {
    Iterator offIt = $Symbols::offloadPorts.iterator();
    while (offIt.hasNext()) {
      String offPort = (String) offIt.next();
      String OffValidString = (String) $Symbols::OffValidString.get(offPort);
      String OffReqString = (String) $Symbols::OffReqString.get(offPort);
      $Symbols::combinationalString += offPort + "Port.req.tag :=  " + 
       $Symbols::instrOfOffload.get(offPort) + "RThread\n";  
      $Symbols::combinationalString += offPort + 
       "Port.req.valid :=  (" + $Symbols::instrOfOffload.get(offPort) + 
       "RThread != NONE_SELECTED) && !" + offPort + 
       "ValidReceived(" + $Symbols::instrOfOffload.get(offPort) + "RThread) && (" + 
       OffValidString + ")\n"; 
      $Symbols::combinationalString += offPort + 
       "Port.req.bits := MuxCase(UFix(0, 32)," + 
       "Seq(" + OffReqString + "))\n";  
    }
  }

  void handleMagillaInstrHeader () {  
    if (isSS($Symbols::instrName)) {
      if(!isFirstInstr($Symbols::instrName)) {
        $Symbols::definitionString += "val " + $Symbols::instrName + 
         "PRegSS_valid = " + "Reg(resetVal=Bool(false))\n";
        $Symbols::definitionString += "val " + $Symbols::instrName + 
         "PRegSS_tag = " + "Reg(resetVal=UFix(width=tagWidth))\n";
        $Symbols::definitionString += "val " + $Symbols::instrName + 
         "PRegSS_Input = " + "Reg(resetVal=" + 
         C2ChiselType($Symbols::inputType) + ")\n";
        if (isInScope($Symbols::instrName, "Output")) {
          $Symbols::definitionString += "val " + $Symbols::instrName + 
           "PRegSS_Output = " + "Reg(resetVal=" + 
           C2ChiselType($Symbols::outputType) + ")\n";
        }
        $Symbols::definitionString += "val " + $Symbols::instrName +
         "PRegSS_ready = Bool()\n";
        $Symbols::combinationalString += $Symbols::instrName + 
         "PRegSS_ready := " + nsName($Symbols::instrName, "ready") + 
         "\n";
//        $Symbols::combinationalString += $Symbols::instrName + 
//         "PRegSS_ready := " + nsName($Symbols::instrName, "ready") + 
//         "\n";
      }
    } else {
      if(!isFirstInstr($Symbols::instrName)) {
        $Symbols::definitionString += "val " + $Symbols::instrName + 
         "PRegPreOff_valid = " + "Reg(resetVal=Bool(false))\n";
        $Symbols::definitionString += "val " + $Symbols::instrName + 
         "PRegPreOff_tag = " + "Reg(resetVal=UFix(width=tagWidth))\n";
        $Symbols::definitionString += "val " + $Symbols::instrName + 
         "PRegPreOff_Input = " + "Reg(resetVal=" + 
         C2ChiselType($Symbols::inputType) + ")\n";
        if (isInScope($Symbols::instrName, "Output")) {
          if (!isDefInstr($Symbols::instrName, "Output")) {
            $Symbols::definitionString += "val " + $Symbols::instrName + 
             "PRegPreOff_Output = " + "Reg(resetVal=" + 
             C2ChiselType($Symbols::outputType) + ")\n";
          }
        }
        $Symbols::definitionString += "val " + $Symbols::instrName +
         "PRegPreOff_ready = Bool()\n";
        $Symbols::combinationalString += $Symbols::instrName + 
         "PRegPreOff_ready :=" + 
         preOffNextStageReady($Symbols::instrName) + "\n";
      }
      $Symbols::definitionString += "val " + $Symbols::instrName + 
       "PRegPostOff_valid = " + "Vec(" + $Symbols::instrName + 
       "NumOfThreads) {" + "Reg(resetVal=Bool(false))}\n";
      $Symbols::definitionString += "val " + $Symbols::instrName + 
       "PRegPostOff_tag = " + "Vec(" + $Symbols::instrName + 
       "NumOfThreads) {" + "Reg(resetVal=UFix(width=tagWidth))}\n";
      if (!isLastInstr($Symbols::instrName)) {
        $Symbols::definitionString += "val " + $Symbols::instrName + 
         "PRegPostOff_Input = " + "Vec(" + $Symbols::instrName + 
         "NumOfThreads) {" + "Reg(resetVal=" +  
         C2ChiselType($Symbols::inputType) + ")}\n";
      }
      if (isInScope($Symbols::instrName, "Output")) {
        $Symbols::definitionString += "val " + $Symbols::instrName + 
         "PRegPostOff_Output = " + "Vec(" + $Symbols::instrName + 
         "NumOfThreads) {" + "Reg(resetVal=" + 
         C2ChiselType($Symbols::outputType) + ")}\n";
      }
      $Symbols::definitionString += "val " + $Symbols::instrName +
       "PRegPostOff_ready = Bool()\n";
      $Symbols::combinationalString +=  $Symbols::instrName + 
       "PRegPostOff_ready := " + $Symbols::instrName + 
       "RThread != NONE_SELECTED \n";
    }
    $Symbols::instrNumber++;
    $Symbols::isPreOff = true;
    $Symbols::isPostOff = false;
    $Symbols::isLocal = true;
    $Symbols::requestBuildString = ""; 
    $Symbols::contextEditString = ""; 
  }

  void handlePostInstrBody () {  
    $Symbols::globalContextEditString += "\n when (" + 
     nsName($Symbols::instrName, "ready") + " || !" + 
     nsName($Symbols::instrName, "valid") + ") {\n";  
    if (!isSS($Symbols::instrName)) {
      $Symbols::globalRequestBuildString += 
       "\n when (" + $Symbols::instrName + "PRegPreOff_valid && " +  
       preOffNextStageReady($Symbols::instrName) + ") {\n";  
      $Symbols::globalContextEditString += "when (" +  
       $Symbols::instrName + "VThread != NONE_SELECTED" + 
       ") {\n";  
    }
    defaultCopyAll($Symbols::instrName);
    $Symbols::globalRequestBuildString += $Symbols::requestBuildString;  
    $Symbols::globalContextEditString += $Symbols::contextEditString;  
    if (!isSS($Symbols::instrName)) {
      $Symbols::globalRequestBuildString += "} \n";  
      $Symbols::globalContextEditString += "} .otherwise { \n" +  
       nsName($Symbols::instrName, "valid") + " := Bool(false)\n" +
       "} \n";  
    }
    $Symbols::globalContextEditString += "} \n";  
    $Symbols::locals.clear();
    $Symbols::localsVersion.clear();
    $Symbols::localsType.clear();
  }

  void handleOffloadArgument(String offIdString, String targetString) { 
    if (!$Symbols::OffValidString.containsKey(offIdString)) {
      System.out.println("Error: offload " + offIdString + " is not added yet");
    }
    if (!$Symbols::OffValidString.get(offIdString).equals("")) {
      $Symbols::OffValidString.put(offIdString, 
       $Symbols::OffValidString.get(offIdString) + " || ");
      $Symbols::OffReqString.put(offIdString, 
       $Symbols::OffReqString.get(offIdString) + " , ");
    }
    $Symbols::OffValidString.put(offIdString, 
     $Symbols::OffValidString.get(offIdString) + 
      preOffValid($Symbols::instrName) + " && " +
      "(" + $Symbols::instrName + "RThread != NONE_SELECTED)");
    $Symbols::OffReqString.put(offIdString, 
     $Symbols::OffReqString.get(offIdString) + 
     "((" + $Symbols::instrName + "RThread != NONE_SELECTED)" + 
     "," + $Symbols::offString + ")");
    if (localVarDefined(targetString) && 
     !C2ChiselType((String)$Symbols::localsType.get(targetString)).contains("new")) {
      $Symbols::combinationalString += "val " + targetString + " = " +  
       offIdString + "Port.rep.bits" + "\n" ;
    } else if (localVarDefined(targetString) && 
     C2ChiselType((String)$Symbols::localsType.get(targetString)).contains("new")){
      $Symbols::combinationalString += targetString + " := " + offIdString + 
       "Port.rep.bits" + "\n" ;
    } else {
      $Symbols::contextEditString += lvName($Symbols::instrName, targetString) + 
       ":=" + offIdString + "Port.rep.bits" + "\n" ;
    }
  }

  void handleOffloadPragma (String offPortString, String reqTypeString, 
   String repTypeString) {  
    if(offPortDefined(offPortString)) 
      System.out.println("Redundant functional unit detected");
    else {
      $Symbols::offloadPortsString += offPortString + "\n";
      $Symbols::offloadPorts.add(offPortString);
      $Symbols::offloadPortsReqType.put(offPortString, C2ChiselType(reqTypeString));
      $Symbols::offloadPortsRepType.put(offPortString, C2ChiselType(repTypeString));
      if ($Symbols::firstOffload) {
        $Symbols::ioString += 
         "(\"" + offPortString  + "\", () => " + C2ChiselType(reqTypeString) + 
         " , () => " + C2ChiselType(repTypeString) + ")";
        $Symbols::firstOffload = false;
      } else {
        $Symbols::ioString += 
         ", (\"" + offPortString  + "\", () => " + C2ChiselType(reqTypeString) + 
         " , () => " + C2ChiselType(repTypeString) + ")";
      }
    }
  }

  void handleGlobalVariableDefinition(String constantString) {
    Iterator variableIt = $Symbols::currentGlobals.iterator();
    while (variableIt.hasNext()) {
      String variableName = (String) variableIt.next();
      Iterator instrIt = $Symbols::instrs.iterator();
      while (instrIt.hasNext()) {
        String instrName = (String) instrIt.next();
        if (isSS(instrName)) {
          if (!isFirstInstr(instrName)) {
            $Symbols::definitionString +=  "val " + instrName + "PRegSS_" + 
             variableName;         
            completeVariableDefinition(constantString);
          }
        } else {
          if (!isFirstInstr(instrName)) {
            $Symbols::definitionString +=  "val " + instrName + "PRegPreOff_" + 
             variableName;         
            completeVariableDefinition(constantString);
          }
          $Symbols::definitionString +=  "val " + instrName + "PRegPostOff_" + 
           variableName;         
          completeVariableDefinition(constantString);
        }
      }
    } 
  }

  void handlePostTypeSpecifier(String typeSpecifierString) {
    $Symbols::globalInitialized = false; 
    $Symbols::typeName = typeSpecifierString; 
    $Symbols::currentGlobals.clear();
  } 

  void handlePostTypeSpecifierStructId(String idString) {
    $Symbols::types.add(idString);
    //For now we are only supporting typedef for structs
    $Symbols::typeDefinitionString += "class " + idString + 
      " extends Bundle { \n" + $Symbols::definitionString + "}\n"; 
    System.out.println("//define type " + idString);
    $Symbols::definitionString = "";
  }

  void handlePostConstantExpressionInDefine(String idString) {
    $Symbols::constantMacros.add(idString);
    System.out.println("//constant macro definition for " + idString);
    if ($Symbols::isHeaderFile) {
      if ($Symbols::macroWithArgument) {
        $Symbols::globalMacroString +=  $Symbols::macroString + "//constant macro definition \n";
      } else {
        $Symbols::globalMacroString +=  "val\t" + idString + " = " + $Symbols::macroString + 
         "//constant macro definition \n";
      }
    } else {
      if($Symbols::macroWithArgument) {
        $Symbols::definitionString +=  $Symbols::macroString + "//constant macro definition \n";
      } else {
        $Symbols::definitionString +=  "val\t" + idString + " = " + $Symbols::macroString + 
         "//constant macro definition \n";
      }
    }
    $Symbols::macroString = "";
    $Symbols::isMacro = false;
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
          if(magInstrDefined(splitted[0])) {
            System.out.println("Redundant magilla instruction detected");
          } else {
            $Symbols::instrs.add(splitted[0]);
          }
          $Symbols::nextInstrs.put(splitted[0], splitted[1]);
          if ($Symbols::firstInstrName.equals("")) {
            $Symbols::firstInstrName = splitted[0];
          }
          for (int i=2; i < splitted.length; i++) {
            if ($Symbols::instrOfOffload.containsKey(splitted[i])) {
              System.out.println( "Gorilla++Error: not pipelinable offload " +  
               splitted[i] + "in more than one instruction"); 
            } else {
              if (!splitted[i].equals("hasNoOffload"))
                $Symbols::instrOfOffload.put(splitted[i], splitted[0]);
            }
          } 
        }        
      }
      br.close(); 
    } catch(IOException e) {
    }
  }

  void readGlobalLvaluesMagillaC() {
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader("globalLvalues.magillac"));
      String line;
      while ((line = br.readLine()) != null) {
        String [] splitted = line.split("\\s+");
        if (splitted.length == 0) {
          System.out.println("Gorilla++Error: wrong instr.magillac format");
        } else {
          HashSet globalLvaluesThisInstruction = new HashSet <String>(); 
          $Symbols::instrGlobalLvalues.put(splitted[0], 
           globalLvaluesThisInstruction); 
          for (int i=1; i < splitted.length; i++) {
            if (globalLvaluesThisInstruction.contains(splitted[i])) {
              System.out.println( "Gorilla++Error: lvalue repeated");
            } else {
              if (!$Symbols::globalsFirstDef.containsKey(splitted[i])) {
                $Symbols::globalsFirstDef.put(splitted[i], splitted[0]);
              }
              if (!(splitted[i].equals("State") ||
                  splitted[i].equals("Input") ||
                  splitted[i].equals("Output"))) {
                globalLvaluesThisInstruction.add(splitted[i]);
              }
            }
          } 
        }        
      }
      br.close(); 
    } catch(IOException e) {
    }
  }

  void handlePostIdInPrimaryExpression(String idString) { 
    String idPrint = "";
    if (idString.equals("true"))
      idPrint = "Bool(true)";
    else if (idString.equals("false")) 
      idPrint = "Bool(false)";
    else { 
      if (!$Symbols::locals.contains(idString)) {
        if ($Symbols::isMacro || $Symbols::constantMacros.contains(idString) || 
         $Symbols::externalMacros.contains(idString)) {
          idPrint = idString;
        } else {
          idPrint = rvName($Symbols::instrName, idString);
        }
      } else {
        idPrint = idString; 
      }
    }
    outString(idPrint); 
  }

  void handleLvalueInAssignment(String idString) {
    $Symbols::silentOutString = false;
    if (localVarDefined(idString)) {
      $Symbols::localsVersion.put(idString, 0);
      if (C2ChiselType((String)$Symbols::localsType.get(idString)).contains("new")) {
        $Symbols::localLvalue = true;
        $Symbols::colonAssign = true;
        outString(idString);
      } else {
        $Symbols::localLvalue = true;
        $Symbols::colonAssign = false;
        outString("val " + idString);
      } 
    } else {
      String idPrint = "";
      if (!idString.equals("State")) {
        idPrint = lvName($Symbols::instrName, idString);
      } else {
        $Symbols::silentOutString = true;
      }
      $Symbols::colonAssign = true;
      outString(idPrint);
    }
  }

  void handleFinish() {
    $Symbols::combinationalString += "io.out.tag := " + 
      "outputReg_tag" + "\n"; 
    $Symbols::combinationalString += "io.out.bits := " + 
      "outputReg_bits" + "\n"; 
    $Symbols::combinationalString += "io.out.valid := " + 
      "outputReg_valid" + "\n"; 
    if (isSS($Symbols::firstInstrName)) {
      $Symbols::combinationalString += "io.in.ready := " + 
       nsName($Symbols::firstInstrName, "ready") + "\n"; 
    } else {
      $Symbols::combinationalString += "io.in.ready := " +
       preOffNextStageReady($Symbols::firstInstrName) + "\n"; 
    }
  }

  void completeVariableDefinition(String constantString) {
    $Symbols::definitionString += " = {Reg(";
    if ($Symbols::globalInitialized) {
      $Symbols::definitionString += C2ChiselType($Symbols::typeName) + ", resetVal=" + 
       Ctype2ChiselInit($Symbols::typeName, constantString) + ")}\t//Global variable \n";
    } else {
      $Symbols::definitionString += C2ChiselType($Symbols::typeName) + ")}\t//Global variable \n";
    }
  }  
}

translation_unit
scope Symbols; // entire file is a scope
@init {
  $Symbols::constantMacros = new HashSet <String> ();
  $Symbols::types = new HashSet<String> ();
  $Symbols::instrs = new ArrayList <String> ();
  $Symbols::nextInstrs = new HashMap<String, String> ();
  $Symbols::instrOfOffload = new HashMap<String, String> ();
  $Symbols::instrGlobalLvalues = new HashMap<String, Set<String>> ();
  $Symbols::globals = new HashSet <String> ();
  $Symbols::globalsFirstDef = new HashMap <String, String> ();
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
  $Symbols::requestBuildString = "";
  $Symbols::globalRequestBuildString = "";
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
  $Symbols::firstOffload = true;
  $Symbols::firstInstrName = "";
  $Symbols::isIfStatement = false;
  try {
    //System.out.println("ZZZ1");
    BufferedReader in = new BufferedReader(new FileReader("typedef.magillac"));
    //System.out.println("ZZZ2");
    String line = in.readLine();
    while (line != null) {
      String types[] = line.split(",");
      if (types.length != 2) {
        System.out.println(
         "typedef.magillac is not obeying the correct comma sperated format\n");
        }
      $Symbols::typedefs.put(types[1], types[0]);
        line = in.readLine();
    }
    in.close();
  } catch (IOException e) {
  }
  try {
    BufferedReader in1 = new BufferedReader(new FileReader("macros.magillac"));
    String line1 = in1.readLine();
    while (line1 != null) {
      $Symbols::externalMacros.add(line1);
      line1 = in1.readLine();
    }
    in1.close();
  } catch (IOException e) {
  }
  readInstrsMagillaC();
  readGlobalLvaluesMagillaC();
} : external_declaration {generateCode();};

external_declaration 
  :  header_file | engine_file;

header_file
  : {$Symbols::isHeaderFile = true;}(type_definition|define)*
    {$Symbols::definitionString = $Symbols::typeDefinitionString;}
  ;

engine_file
  : in_pragma out_pragma off_pragma* other_pragma* {handlePreGlobalDeclaration();}
    global_declaration*  {handlePostGlobalDeclaration();}
    magilla_instr+ {handlePostInstrs();}
  ;

global_declaration
  :  define | global_variable_definition 
  ;
  
magilla_instr 
  :  MAGILLA_INSTR_NAME 
     {$Symbols::instrName=$MAGILLA_INSTR_NAME.text;
      handleMagillaInstrHeader();}
     ('('')') '{' instr_body'}' {handlePostInstrBody();} 
  ;

instr_body scope {
  int num_of_FU_reqs;
}
@init {
  $instr_body::num_of_FU_reqs = 0;
}
  : local_variable_definition* statement* {$Symbols::isPreOff = false;} 
    offload_statement* {$Symbols::isPostOff = true; } statement* finish_statement?
  ; 

local_variable_definition
  : ((type_specifier {$Symbols::typeName = $type_specifier.text;} variablelist) | 
    ('bit_vector' bv_index ID)) ';' 
  ; 

global_variable_definition
  : ((type_specifier {handlePostTypeSpecifier($type_specifier.text);}
      variablelist) (bv_index)? | ('bit_vector' bv_index ID)) 
    ( {$Symbols::globalInitialized = true;} '=' constant)? ';' 
    {handleGlobalVariableDefinition($constant.text);}
  ;

type_definition 
  : 'typedef' type_specifier_struct ID {handlePostTypeSpecifierStructId($ID.text);}
    ';' 
  ; 

pragma
  : off_pragma  | in_pragma | out_pragma | other_pragma;

other_pragma
  : '#pragma' ('CONCURRENT_SAFE' | 'PIPELINABLE')
  ;

in_pragma
  : '#pragma' 'INPUT'   in_type=ID  {  
      $Symbols::ioString += "(() => " + C2ChiselType($in_type.text) + ")";
        $Symbols::inputType = $in_type.text;
  };

out_pragma
  : '#pragma' 'OUTPUT'  out_type=ID  {  
      $Symbols::ioString += "(() => " + C2ChiselType($out_type.text) + ") (ArrayBuffer(";
        $Symbols::outputType = $out_type.text;
  };

off_pragma 
  : '#pragma' 'OFFLOAD' '(' offPort=ID ',' reqType=ID ',' repType=ID ')' 
    {handleOffloadPragma($offPort.text, $reqType.text, $repType.text);} 
  ;

define
  : '#define' {$Symbols::macroWithArgument = false;} id=ID  ('(' 
    {$Symbols::macroWithArgument = true; $Symbols::macroString += "def " + $id.text + "(";}
    id1=ID {$Symbols::macroString += $id1.text+":"+"UFix";} (',' 
    id2=ID {$Symbols::macroString += ", " + $id2.text+":"+"UFix";})* ')'
    {$Symbols::macroString += ") = ";})? {$Symbols::isMacro = true;} 
    ( constant_expression) {handlePostConstantExpressionInDefine($id.text);}
  ;

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
  :   (  ID
      {
        if ($declaration.size()>0&&$declaration::isTypedef) {
          $Symbols::types.add($ID.text);
          System.out.println("define type "+$ID.text);
        }
      }
    |  '(' declarator ')'
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
  :'{' /*{outString("begin\n");}*/ statement+ '}' /*{outString("end\n");}*/
  ;

offload_expression
  :  {$Symbols::offString = ""; } target=ID '=' offId=ID {$Symbols::offId += $offId; }
    '(' conditional_expression ')' {handleOffloadArgument($offId.text, $target.text);} 

  ;


macro_expression 
  :  {!$Symbols::offloadPorts.contains(input.LT(1).getText())}? ID 
     {outString($ID.text);}'('{outString("(");} conditional_expression 
     (','{outString(",");} conditional_expression)*')' {outString(")");} 
  ; //e.g. access fields of packet 


//TODO for chisel
labeled_statement
  : ID ':' statement
  | 'case'  constant_expression ':' {outString(": begin\n");}  statement {outString("end\n");}
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
        outString(" \n"); 
      $Symbols::localLvalue = false;
    };

SEMICOLON : ';'  ;
//TODO chisel
selection_statement
  : 'if' {$Symbols::isIfStatement= true;} '(' {outString("when (");} 
     conditional_expression ')' 
     {outString(") {\n");} statement  {outString("}\n");}
     (options {k=1; backtrack=false;}:'else' {outString(".otherwise {");} 
     {outString("\n");} statement {outString("}\n");})? 
     {$Symbols::isIfStatement= false;} | 
     'switch' '(' {outString("case (");} expression ')' {outString(") ");} 
     statement {outString("endcase\n");}
  ;

finish_statement
  : 'finish' '(' ')' SEMICOLON {handleFinish();}
   ;

variablelist 
  :  variable_in_def ( ',' variable_in_def)* ;
  
variable_in_def
  : variable  {handleVariableInDef($variable.text);} 
 ;

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
    | 'struct {' (type_specifier 
    {$Symbols::typeName = $type_specifier.text;} 
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
  :  ID  //We turn off this check so that including is not bugging us for now
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
  :  assignment_expression  
  ;  

concat_expression
  : '{'{outString("{");} expression (','{outString(",");} expression)* '}' 
     {outString("}");}
  ;

assignment_expression   
  : id=ID {handleLvalueInAssignment($id.text);}
    fieldPostfix assignment_operator 
    ( sel_expression |  conditional_expression | macro_expression)  
    ;

sel_expression
  : {outString(" Mux(");} conditional_expression '?' {outString(",");} 
    conditional_expression ':' {outString(",");} conditional_expression 
    {outString(")");}
  ;
  
lvalue
  : unary_expression
  ;

constant_expression
  : conditional_expression
  ;

conditional_expression
  : logical_or_expression 
  ;

logical_or_expression
  : logical_and_expression ('||' {outString("||");} logical_and_expression)*
  ;

logical_and_expression
  : inclusive_or_expression ('&&' {outString("&&");} inclusive_or_expression)*
  ;

inclusive_or_expression
  : exclusive_or_expression ('|' {outString("|");} exclusive_or_expression)*
  ;

exclusive_or_expression
  : and_expression ('^' {outString("^");}and_expression)*
  ;

and_expression
  : equality_expression ('&' {outString("&");}equality_expression)*
  ;
equality_expression
  : relational_expression (('=='{outString("===");}|'!='{outString("!=");}) 
    relational_expression)*
  ;

relational_expression
  : shift_expression (('<'{outString("<");}|
    '>'{outString(">");}|'<='{outString("<=");}|
    '>='{outString(">=");}) shift_expression)*
  ;

shift_expression
  : additive_expression 
    (('<<'{outString("<<");}|'>>'{outString(">>");}) additive_expression)*
  ;


additive_expression
  : (multiplicative_expression) 
    ('+'{outString("+");} multiplicative_expression | 
     '-'{outString("-");} multiplicative_expression)*
  ;

multiplicative_expression
  : (cast_expression) 
    ('*' {outString("*");}cast_expression | 
     '/'{outString("/");} cast_expression | 
     '%'{outString("\%");} cast_expression)*
  ;

cast_expression
  : '(' type_name ')' cast_expression
  | unary_expression
  ;


unary_expression
  : postfix_expression
  | '++' {outString("++");}unary_expression
  | '--' {outString("--");}unary_expression
  | unary_operator 
   {if ($Symbols::isLocal) outString($unary_operator.text);} cast_expression
//  | 'sizeof' unary_expression
//  | 'sizeof' '(' type_name ')'
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
  : ID  {handlePostIdInPrimaryExpression($ID.text);}

    fieldPostfix
  | constant { /*outString($constant.text);*/ }
  | '(' { outString("("); }
    conditional_expression 
  ')' { outString(")"); }
  | ID  bv_index {outString($ID.text + $bv_index.text);} 
  | ID  bv_range {outString($ID.text + $bv_range.text);}
  | MAGILLA_INSTR_NAME {outString($MAGILLA_INSTR_NAME.text);}
  ;

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
    : hl=HEX_LITERAL 
      {outString("UFix(" + hex2decimal($hl.text.substring(2)) + ", width = 32)");}
      | OCTAL_LITERAL
      | dl=DECIMAL_LITERAL {outString("UFix(" + $dl.text + ", width = 32)");}
      | CHARACTER_LITERAL
      |  STRING_LITERAL
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
  : '=' {if (!$Symbols::colonAssign) outString("="); else outString(":=");}  
        //TODO: we need to have a boundry between request builder and context edit 
  | '*='
  | '/='
  | '%='
  | '+=' //{outString("+=");}
  | '-=' //{outString("+=");}
  | '<<='
  | '>>='
  | '&='
  | '^='
  | '|='
  ;

operator 
  :  '^'{outString("^");} |
     '+'{outString("+");} | 
     '-'{outString("-");} | 
     '&'{outString("&");} | 
     '|'{outString("|");}| 
     '&&'{outString("&&");} | 
     '||'{outString("||");};

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
  
fragment LETTER  :  'a'..'z' | 'A'..'Z'
  ;
  
fragment DIGIT  :  '0'..'9'
  ;

MAGILLA_INSTR_NAME
  :  'GS_' ID;

jump_statement
  : /*'goto' ID ';'
  | */'continue' ';'
  | 'break' ';'
//  | 'return' ';'
//  | 'return' expression ';'
  ;

ID  :  LETTER (LETTER | DIGIT | '_')*
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
  //|  ('u'|'U')  ('l'|'L')?
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


 



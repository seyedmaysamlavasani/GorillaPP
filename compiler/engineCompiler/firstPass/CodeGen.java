import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
import java.util.ArrayList;
import java.io.*;


public class CodeGen {
  ArrayList<String> instrs; //list of magilla instructions
  HashMap <String, HashSet<Variable>> globalLvaluesOfInstr; 
//  HashMap <String, Variables> variablesOfInstr; 
                             //map from each instruction to 
                            //a set containing all 
                            //global lvalues Variable 
                            //data
  String definitionString; //definition of global variables 
                           //and instruction numbers
  String typeDefinitionString; //definition of types. After 
                               //type definition part 
                               //flushed into 
                               //definitionString 

  boolean isHeaderFile;
  boolean isTupeDef;

  Set<String> types; 
  Set<String> constantMacros; //list of constant macros
  HashMap<String, String> nextInstrs; 
                       //map of next magilla 
                       //instructions for 
                       //each instruction
  HashMap<String, String> instrsOfOffload; 
                       //map from each 
                       //offload to an 
                       //instruction
  HashMap<String, Set<Variable>> localLvaluesOfInstr; 
                       //map from each instruction to 
                       //a set containing all 
                       //local lvalues Variable 
                       //data
  HashMap<String, Boolean> isOffDominated; 
                      //map from each variable to 
                      //a bollean value indicating 
                      //if at the current point 
                      //a variable is dominated by
                      //any offload operation
  Set<String> globals; //list of global variables
  Set<String> currentGlobals; 
                      //list of global variables in the 
                      //current definition
  Variables  variables;
  Set<String> locals; //list of local variables
  Set<String> jumpTargets; //checking that jump targets are valid 
                   //instructions cant be done 
                   //in single pass
  Set<String> offloadPorts;
  HashMap<String, String> offloadPortsReqType; 
                           //The request type for an 
                           //offload port
  HashMap<String, String> offloadPortsRepType; 
                           //The reply type for an 
                           //offload port
  HashMap<String, String> localsType; 
                           //The type of each local variable
  HashMap<String, Integer> localsVersion; 
                          //For each local variable, 
                          //we have a version number. 
                          //In case it gets reassigned to, 
                          //the version will be incremented. 
                          //Not used now.
  String inputType;  
  String outputType;  
  String typeName;  //We carry the type name, so we can 
                    //define the global variables with 
                    //correct type in register
  String offloadPortsString; //list of offload ports 
  String ioString; //String that contains the Chisel 
                   //interface definition of the module
  String macroString;  //temporary buffer to fill the macro 
                       //expression 
  String globalMacroString;  //buffer to fill the macro 
                             //expression 
  String globalRequestBuilderString;
  String requestBuilderString;
  String globalContextEditString;
  String contextEditString;
  String combinationalString; //assignement to local 
                              //variables goes here
  String instrString; //list of instructions
  String offString; //the buffer we use for offload 
                    //argument expression
  String globalLvalueString; //The string containing the 
                             //list of global lvalues for each
                             // instruction
  String instrName;
  String prevInstrName;
  OutputStream outStream;
  boolean isPreOff; //Are we before offloading(s)?
  boolean isPostOff; //Are we after offloading(s)?
  boolean localLvalue; //Is the Lvalue a local variable. 
                       //For local varaibles code is 
                       //generated as combinational.
                       //Whereas for global variables codes 
                       //is generated as context edit
  boolean stateLvalue; //Whether the current statement is 
                       //assigning to State to extract the 
                       //next state
  String offId; //The id of offload operation is stored here 
  boolean isLocal; //differentiate between local and global
                   //variables, set to true when we enter an
                   //instruction 
  int instrNumber; //The index of instruction 
  Stack<String> condStack; 
                   //For each conditional clause, e.g. if
                   // we push the condition in the stack. 
                   //When the clause is finished the 
                   //condition is poped.
  boolean isTypeDef;
  boolean isMacro;
  boolean globalInitialized; //A helper variable that is 
                             //true if current global 
                             //definition has initialization;
  boolean isIfStatement;

  public CodeGen() {
    instrs = new ArrayList<String> ();
    globalLvaluesOfInstr = 
     new HashMap<String, HashSet<Variable>> ();
//    variablesOfInstr = 
//     new HashMap<String, Variables> ();
    definitionString = "";            
    isHeaderFile = false;
    isTypeDef = false;
    typeDefinitionString = "";            
    constantMacros = new HashSet <String> ();
    types = new HashSet <String> ();
    nextInstrs = 
     new HashMap<String, String> ();
    instrsOfOffload = 
     new HashMap<String, String> ();
    localLvaluesOfInstr = 
     new HashMap<String, Set<Variable>> ();
    isOffDominated = 
     new HashMap<String, Boolean> ();
    globals = new HashSet <String> ();
     currentGlobals = new HashSet <String> ();
    locals = new HashSet <String> ();
    variables = new Variables();
    offloadPorts = new HashSet <String> ();
    offloadPortsReqType = 
     new HashMap<String, String> ();
    offloadPortsRepType = 
     new HashMap<String, String> ();
    localsVersion = 
     new HashMap<String, Integer> ();
    localsType = 
     new HashMap<String, String> ();
    requestBuilderString = "";
    globalRequestBuilderString = "";
    contextEditString = ""; 
    globalContextEditString = ""; 
    ioString = "";                
    offloadPortsString = "";                 
    macroString = "";            
    globalMacroString = "";            
    instrString = "";                 
    combinationalString = "";
    globalLvalueString = "";
    isPreOff = true;
    isPostOff = false;
    isLocal = false;
    isTypeDef = false;
    localLvalue = false;
    stateLvalue = false;
    isMacro = false;
    instrNumber = 1;
    condStack = new Stack <String> ();
    isIfStatement = false;
  }  

  public boolean instrDefined(String name) {
   if (instrs.contains(name) ) {
     return true;
    } else {
      return false;
    }
  }

  public void setIsHeaderFile() {
    isHeaderFile = true;
  }

  public void setIsPostOff() {
   isPostOff = true;
  }

  public void unsetIsPreOff() {
   isPreOff = false;
  }

  public void addTypeDefsToDefString() {
    definitionString = typeDefinitionString;
  }

  public void setTypeName(String typeName) {
   this.typeName = typeName;
  }

  public void setGlobalInitialized() {
    globalInitialized = true;
  }

  public void setIsTypeDef() {
    isTypeDef = true;
  }

  public void unsetIsTypeDef() {
    isTypeDef = false;
  }

  public void setOffString(String s) {
    offString = s;
  }

  public String C2ChiselType(String s) {
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

  //Not used for now
  String versioned(String s) {
    if (localsVersion.containsKey(s) &&  
      ((int) localsVersion.get(s)) != 0) { 
      return (s + "_" + localsVersion.
       get(s).toString());
    }
    return (s);
  }

  public String Ctype2ChiselInit(String type, String init) {
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

  public void setIsIfStatement () {
    isIfStatement = true;  
  }

  public void unsetIsIfStatement () {
    isIfStatement = false;  
  }

  public void setIsMacro () {
    isMacro = true;
  }
 
  void outString(String s) {
    if (isMacro) {
        macroString += s;
    } else if (localLvalue) 
      combinationalString += s;
    else if (isPreOff)
      contextEditString += s;
    else if (isPostOff)
      contextEditString += s;    
    else  
      offString +=s ; 
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
    return (types.contains(name));
  }

  boolean isConstantMacro(String name) {
    return (constantMacros.contains(name));  
  }

  boolean globalVarDefined(String name) {
    return (globals.contains(name));
  }

  boolean localVarDefined(String name) {
    return (locals.contains(name));
  }

  boolean offloadPortDefined(String name) {
    return(offloadPorts.contains(name));
  }

}

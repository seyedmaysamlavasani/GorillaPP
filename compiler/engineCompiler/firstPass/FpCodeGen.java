import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
import java.util.ArrayList;
import java.io.*;


public class FpCodeGen extends CodeGen {

  public void handlePreInstrBody(String instrName) {    
    if(instrDefined(instrName)) {
      System.out.println("Redundant magilla instruction" +  
       " detected");
    } else {
      instrs.add(instrName);
      globalLvaluesOfInstr.put(instrName, 
       new HashSet <Variable>());
    }
    definitionString += "val " + instrName + 
     " = " + "UFix(" + instrNumber + ")\n";
    prevInstrName = this.instrName;
    this.instrName = instrName;
    if (instrNumber != 1) {
      if (!nextInstrs.containsKey(prevInstrName)) {
        System.out.println("Gorilla++Hint: previous " +
         "instr " + prevInstrName + " does not have a " +  
         "next instr. Possibly finished instrs. Instr " +
         "number is " + instrNumber);
      } else if (!nextInstrs.get(prevInstrName).
       equals(instrName)) {
        System.out.println(
         "Gorilla++Hint: not pipelinable engine jumps " + 
         "are not sequential");
      }
    } 
    instrNumber++;
    isPreOff = true;
    isPostOff = false;
    isLocal = true;
    requestBuilderString = ""; 
    contextEditString = ""; 
  }

  public void handlePostInstrBody(String instr) {    
    globalRequestBuilderString += 
     "\n when (State === " + instr;    
    globalContextEditString += 
     "\n when (State === " + instr;    
    globalRequestBuilderString +=  
     " && AllOffloadsReady)" + "{\n";    
    globalContextEditString +=  ")" +  "{\n";    
    globalRequestBuilderString += 
     requestBuilderString;    
    globalContextEditString += 
     contextEditString;    
    globalRequestBuilderString += "} \n";    
    globalContextEditString += "} \n";    
    locals.clear();
    localsVersion.clear();
    localsType.clear();
  }
  public void handleLValue (String idString) {
    //Increment the version number
    if (localsVersion.containsKey(idString)) {
      localsVersion.put(idString, 
       (int) localsVersion.get(idString) + 1);
    } else {
      localsVersion.put(idString, 0);
    }
    //Add the lvlue of instrs
    if (localVarDefined(idString)) {
      localLvalue = true;
      //Add to localLvaluesOfInstr
    } else {
      String idPrint;
      idPrint = idString;
      if (!globalLvaluesOfInstr.containsKey( 
       instrName)){
        System.out.println("Gorilla++Error: cannot" + 
         "find instr to assign lvalue");
      } else {
        HashSet<Variable> globalLvalues = (HashSet<Variable>) 
         globalLvaluesOfInstr.get(
          instrName); 
        if (!globalLvalues.contains(idString)) {
          Variable v = new Variable(idString, true);
          globalLvalues.add(v);
        }
      } 
      if (idString.equals("State") && stateLvalue) {
        System.out.println("Gorilla++Hint: not" + 
         "pipelinable engine more than one jump" + 
         "for instr " +  instrName); 
      } else if (idString.equals("State")) {
        if (isIfStatement) {
          System.out.println("Gorilla++Hint: not" + 
           " pipelinable engine State is assigned inside " + 
           "if statement in instr " +  
           instrName); 
        }
        stateLvalue = true;
      }
      outString(idPrint);
    }
  }

  public void  handleVariableInDefinition (String vName) {  
    if(!isLocal && 
     !isHeaderFile) {
      if(globalVarDefined(vName)) {
        System.out.println("Redundant definition of " +  
         " global variable");
      } else {
        globals.add(vName);
        currentGlobals.add(vName);
      }
    } else if (!isHeaderFile){
      localsType.put(vName, 
       typeName);
      locals.add(vName);
    } else if (isHeaderFile) {
      definitionString +=  "val " + 
      vName + " = " + 
      C2ChiselType(typeName) + "\n";
    }
  }

  public void handleDefine(String id) {
    constantMacros.add(id);
    System.out.println("//constant macro definition for " + 
      id);
    if (isHeaderFile) {
      globalMacroString +=  "val\t" + id + 
       " = " + macroString + 
       "//constant macro definition \n";
    } else {
      definitionString +=  "val\t" + id + 
       " = " + macroString + 
       "//constant macro definition \n";
    }
    macroString = "";
    isMacro = false;
  }

  public void writeOutputs() {
    System.out.println("//First pass for Magilla piper compiler");
    //System.out.println("//List of Gorilla processing steps:");
    Iterator instr_it = instrs.iterator();
    //build instr strings which contains instr names, next instr, 
    //and offload calls per instr
    while (instr_it.hasNext()) {
      String instr = instr_it.next().toString();
      // Add instr name and next instr
      if (nextInstrs.containsKey(instr)) {
        instrString += instr + " " + 
         nextInstrs.get(instr) + " ";
      } else {
        instrString += instr + " noNextInstr ";
      } 
      // Add offload ports
      if (instrsOfOffload.containsValue(instr)) {
        for (Map.Entry<String, String> entry : 
         //(HashMap<String, String>) 
         instrsOfOffload.entrySet()) {
          if (entry.getValue().equals(instr)) {
            instrString += entry.getKey() + " ";
          }
        }
      } else {
        instrString += " hasNoOffload";
      } 
      instrString += "\n";
    } 
    //Build global_lvalues_string which contains 
    //instr and all global lvalues per instr
    Iterator instr_it_1 = instrs.iterator();
    while (instr_it_1.hasNext()) {
      String instr = instr_it_1.next().toString();
      globalLvalueString  += instr + " ";
      for (Variable entry: (HashSet<Variable>) 
       globalLvaluesOfInstr.get(instr)) {
        globalLvalueString += entry.name + " ";
      } 
//      globalLvalueString += variablesOfInstr.serialize();
      globalLvalueString += "\n";
    }
    //Write instruction string to file 
    try {
      BufferedWriter out = new BufferedWriter(
       new FileWriter("instr.magillac"));
      out.write(instrString);
      out.close();
    } catch (IOException e) {
    }
    //Write global lvalue string to file  
    try {
      BufferedWriter out = new BufferedWriter(
       new FileWriter("globalLvalues.magillac"));
      out.write(globalLvalueString);
      out.close();
    } catch (IOException e) {
    }
  }
  public void handlePreInstrs () {
    globalContextEditString += 
     "when (SubState === WaitForValid && AllOffloadsValid) {";
    definitionString += 
     "val inputReg = Reg(" + 
     C2ChiselType(inputType) +  ")\n";
    definitionString += 
     "val outputReg = Reg(" + 
     C2ChiselType(outputType) +  ")\n";
    Iterator off_it = offloadPorts.iterator();
    while (off_it.hasNext()) {
      String offPort = (String) off_it.next();
      definitionString += 
       "def  myOff = io.elements.find(_._1 == \"off\")" + 
       ".getOrElse(elseV)._2\n";
      definitionString += 
       "def mymyOff = myOff.asInstanceOf[Bundle]." + 
       "elements.find(_._1 == \"" + offPort + 
       "\").getOrElse(elseV)._2\n"; 
      definitionString += 
       "val " + offPort + "Port = " + 
       "new gOffBundleND(() => " + 
       offloadPortsReqType.get(offPort) + 
       ", () => " + offloadPortsRepType.
        get(offPort) + 
       ")\n";
      definitionString += 
        offPort + "Port <>" + "mymyOff\n";
    }
  }
  public void handlePostInstrs() {
    globalContextEditString += 
     " SubState := WaitForReady \n } \n"; 
  }

  public void handlePrePragmas() {
    addTypeDefsToDefString();
  } 

  public void handlePostPragmas() {
    ioString += "))";
  } 

  public void handlePostTypeSpecifier (String typeSpecifier) {
    globalInitialized = false; 
    typeName = typeSpecifier; 
    currentGlobals.clear();
  } 

  public void handleInPragma (String type) {
    ioString += "(() => " + 
     C2ChiselType(type) + ")";
    inputType = type;
  }

  public void handleOutPragma (String type) {
    ioString += "(() => " + 
     C2ChiselType(type) + ") (ArrayBuffer(";
    outputType = type;
  }

  public void handleOffPragma (String offPort, String reqType, String repType) {
    if(offloadPortDefined(offPort)) 
      System.out.println("Redundant functional unit detected");
    else {
      offloadPortsString += offPort;
      offloadPorts.add(offPort);
      offloadPortsReqType.put(offPort, 
       C2ChiselType(reqType));
      offloadPortsRepType.put(offPort, 
       C2ChiselType(repType));
      ioString += "(\"" + offPort + 
       "\", () => " + C2ChiselType(reqType) + 
       " , () => " + C2ChiselType(repType) + ")";
    }
  }

  public void handleGlobalVariableDefinition(String constant) {
    Iterator variable_it = 
     currentGlobals.iterator();
    while (variable_it.hasNext()) {
      definitionString +=  
       "val " + variable_it.next() + " = Reg(";
      if (globalInitialized) {
        definitionString += "resetVal=" + 
        Ctype2ChiselInit(typeName, 
         constant) + ")\t//Global variable \n";
      } else {
        definitionString += 
         C2ChiselType(typeName) + ")\t//Global variable \n";
      }
    } 
  } 

  public void handlePostIdInDirectDeclarator(String id) {
    if (isTypeDef) {
      types.add(id);
      System.out.println("define type " + id);
    }
  }

  public void handleTypeDefinition (String id) {
    types.add(id);
    //For now we are only supporting typedef 
    //for structs
    typeDefinitionString += "class " + 
     id + " extends Bundle { \n" + 
     definitionString + "}\n"; 
    System.out.println("//define type " + id);
    definitionString = "";
  }

  void handleOffloadExpression (String target, String offId) { 
    combinationalString += offId + 
     "Port.req.bits" + " := " + 
     offString + "\n" ;
    combinationalString += offId + 
     "Port.req.valid" + " := State === " + 
     instrName + "\n" ;
    if (localVarDefined(target)) {
      combinationalString += "val " + 
       target + " = " +  offId + 
       "Port.rep.bits" + "\n" ;
    } else {
      contextEditString += target + 
       " := " + offId + "Port.rep.bits" + "\n" ;
    }
         
    if (!instrsOfOffload.containsKey(offId)) {
      System.out.println("instr " + instrName + 
       " offload " + offId + "\n");

      instrsOfOffload.put(offId, 
       instrName);
    }
  }

  public void handleExpressionStatement() {
    outString(" \n"); 
    localLvalue = false;
    stateLvalue = false;
  }

  public void handlePostIfStatement() {
    outString("}\n");
  }

  public void handlePreElseStatement() {
    outString(".otherwise {\n"); 
  }

  public void handlePostElseStatement() {
    outString("}\n");
  }

  public void handlePreIfCondition() {
    outString("when (");
  }

  public void handlePostIfCondition() {
    outString(") {\n");
  }

  public void handleFinish() {
    outString("State := WaitForOutputReady\n");
  }

  public void handlePrimaryExpression(String id) { 
    String idPrint;
    if (id.equals("Input")) 
      idPrint = "inputReg";
    else 
      idPrint = id;
    outString(idPrint);
  }
  public void postInstrNameAsId(String s) {
    if (stateLvalue) {
      nextInstrs.put(instrName, s);
    }
    outString(s);
  } 

  public void handleSimpleAssignmentOperator () {
    if (localLvalue) 
      outString("="); 
    else 
      outString(":=");}  
  }

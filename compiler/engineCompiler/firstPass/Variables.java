import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
import java.util.ArrayList;
import java.io.*;


public class Variables {
  HashMap<String, Variable> vData;

  public Variables() {
    vData = new HashMap<String, Variable> ();
  } 

  String serialize2String() {
    String s = "";
    for (Map.Entry<String, Variable> entry: 
     vData.entrySet()) {
      s += "(" + entry.getValue().name +
       "(" + entry.getValue().isPreOffLHS() + "," +
       entry.getValue().isPreOffLHS() + "," +
       entry.getValue().isPreOffRHS() + "," +
       entry.getValue().isPostOffLHS() + "," +
       entry.getValue().isPostOffRHS() + ")";
    }
    return (s);
  }

  //We call this at the start of instruction
  void addGlobals(HashSet<String> globals) {
    for(String gvName: globals) { 
      Variable v = new Variable(gvName, true);
      vData.put(gvName, v);
    } 
  }
  //We call this after each local definition
  void addLocal(String local) {
    Variable v = new Variable(local, false);
    vData.put(local, v);
  }

  void LHSVistited(String name, boolean isOffload) {
    if (vData.containsKey(name)) {
      Variable v = vData.get(name);
      v.def(isOffload);
    } else {
      System.out.println("Gorilla++Error: variable " + 
        name + "is not defined\n");
    }
  }

  void RHSVisited(String name, String lValueName) {
    if (vData.containsKey(name)) {
      Variable v = vData.get(name);
      if (!vData.containsKey(lValueName)) {
        System.out.println("Gorilla++Error: lvalue not found\n");
      } else {
        v.use(vData.get(lValueName));
      }
    } else {
      System.out.println("Gorilla++Error: variable " + 
        name + "is not defined\n");
    }
  }
}


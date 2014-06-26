import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
import java.util.ArrayList;
import java.io.*;


//Currently, we just use the name field in
//this data-structure. However, this data-structure 
//is designed to keep track of variables and their 
//versions for each instruction. We also keep track 
//of the offload domination. 

public class Variable {
  String name;
  int preOffVersion;  //last version before 
                      //offload domination
  int version;
  boolean isPreOffLHS;
  boolean isPreOffRHS;
  boolean isPostOffLHS;
  boolean isPostOffRHS;
  boolean isOffDominated; 
  public enum Type {
    Local, Global 
  }
  Type type;
  public boolean isOffDominated() {
    return(isOffDominated);
  }
  public void setIsOffDominated() {
    isOffDominated = true;
  }
  public Variable (String name, boolean isGlobal) {
    this.name = name;
    resetVersion();
    isOffDominated = false;
    isPreOffLHS = false;
    isPreOffRHS = false;
    isPostOffLHS = false;
    isPostOffRHS = false;
    isOffDominated = false;    
    if (isGlobal) {
      type = Type.Global;
    } else {
      type = Type.Local;
    }
  }

  public boolean isPreOffLHS() {
    return(isPreOffLHS);
  }
  public boolean isPreOffRHS() {
    return(isPreOffRHS);
  }
  public boolean isPostOffLHS() {
    return(isPostOffLHS);
  }
  public boolean isPostOffRHS() {
    return(isPostOffRHS);
  }

  public boolean isGlobal() {
    return(type == Type.Global);
  } 

  public void resetVersion () {
    version = 0;
    preOffVersion = 0;
  }
  
  public void def(boolean isOffloadStatement) {
    version = version + 1;  
    if (!isOffDominated && !isOffloadStatement) {
      isPreOffLHS = true;
    } else {
      isPostOffLHS = true;
    }
    if (isOffloadStatement) {
      isOffDominated = true;
    }
  }   

  public void use(Variable lValue) {
    if (isOffDominated) {
      lValue.setIsOffDominated();
    }
    if (!lValue.isOffDominated()) {
      isPreOffRHS = true;
    } else {
      isPostOffRHS = true;
    }
  }

}

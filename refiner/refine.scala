import scala.io.Source
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex
import java.io._

trait NodeTrait {
  val Engine = 0
  val MTEngine = 1
  val PipeEngine = 2
  val Replicated = 3
  val Offloaded = 4
  val Chained = 5
}

class PC {
  var inBackPressure : Double = -1
  var outBackPressure : Double = -1
  var offBackPressure : ArrayBuffer[Double] = ArrayBuffer[Double]() 
  var offloadRate : Double = -1
  var engineUtilization : Double = -1
}

class Node extends NodeTrait {
  var name: String = ""
  var parent: Node = null
  var children: ArrayBuffer[Node] = ArrayBuffer[Node]()
  var nodeType: Int = MTEngine
  var className: String = ""
  var pc = new PC
  var concurrentSafe = false 
  var pipelinable  = false 
  var pipelineStages : Int = 0
}

object refine extends NodeTrait {
  val nodes = collection.mutable.Map[String, Node]() 
  var rootNode : Node = null
  def nodeType(node: String) : Int = {
    var rt: Int = 0
    if (node.contains("engine__MT__1__") && isEngineInTheSource(sourceName(node))) {
      rt = Engine 
    } else if (node.contains("engine__MT")) {
      rt = MTEngine 
    }
    if (node.contains("engine__pipelined")) {
      rt = PipeEngine 
    }
    if (node.contains("replicated")) {
      rt = Replicated 
    }
    if (node.contains("offloaded")) {
      rt = Offloaded 
    }
    if (node.contains("chained")) {
      rt = Chained 
    }
    rt
  }

  def addNode(parent: String, child: String) {
    if (!nodes.contains(parent)) {
      val parentNode = new Node
      parentNode.nodeType = nodeType(parent)        
      parentNode.className = className(parent)
      parentNode.name = parent
      nodes += (parent -> parentNode) 
      rootNode = parentNode
      println("Parent " + parentNode.name + " added to nodes class is " + parentNode.className)
    }  
    if (!nodes.contains(child)) {
      val parentNode = nodes.getOrElse(parent, null)
      val childNode = new Node 
      childNode.parent = parentNode
      childNode.nodeType = nodeType(child)
      childNode.className = className(child)
      childNode.name = child
      parentNode.children += childNode
      println("Child " + childNode.name + " added to parent " + parentNode.name + " class is " + 
        parentNode.className)
      nodes += (child -> childNode)
    } else if (nodes.getOrElse(child, null).parent == null) {
      val parentNode = nodes.getOrElse(parent, null)
      nodes.getOrElse(child, null).parent = parentNode 
      parentNode.children += nodes.getOrElse(child, null)
      println("Child " + child + " linked to parent " + parentNode.name)
    }
  }
  
  //The argument function is applied on all children as long as the function
  //returns true
  def topDownSearch (f:(Node) => Boolean, node:Node = rootNode) {
    //println("Refine: topDownSearch called for " + node.name + 
    // " inBackPressure ", node.pc.inBackPressure)
    if (node != null) {
      if (f(node))  {
        var rv = true
        node.children.foreach(c => {
          rv = rv && (f(c) || c == null)
          if (rv) {
            topDownSearch(f, c)
          }
        }) 
      }
    } 
  }  

  //The argument function is applied on all ancestors as long as the function
  //returns false
  def bottomUpSearch(f:(Node) => Boolean, node:Node) {
    if (node != null) {
      if (!f(node)) {
        bottomUpSearch(f, node.parent) 
      }
    } 
  } 

  def sourceName(nodeName: String): String = {
    val snP = "^(\\w*)__type".r
    snP.findFirstIn(nodeName) match {
      case Some(snP(sn)) => {
        sn  
      }
      case None => nodeName 
    }
  } 

  def noVersionName(nodeName: String): String = {
    val snP = "(\\w*)_".r
    snP.findFirstIn(nodeName) match {
      case Some(snP(sn)) => {
        sn  
      }
      case None => nodeName 
    }
  }

  var backTrack = 0

  def checkBackTrack() : Boolean = {
    if (backTrack != 0) {
      backTrack = backTrack - 1
      false
    } else {
      true 
    }
  }

  def readBackTrack() = {
    var rv = 0
    val rP = "(\\d*)".r
    Source.fromFile("backTrack" ).getLines().foreach { 
      line => {
        rP.findFirstIn(line) match {
          case Some(rP(bt)) => {
            rv = bt.toInt;
          }
          case None =>
        }    
      }
    } 
    rv
  }  

  def pipelineEngine(node: Node) = {
    val rP = "(\\s*val\\s*)(\\w*)\\s*=\\s*Engine\\s*\\(\\s*\"(\\w*\\.\\w)\"\\s*\\)".r
    val topWriter = new PrintWriter(new File("output/TopRefined.scala" ))
    Source.fromFile("input/TopSSF.scala" ).getLines().foreach { 
      var replacedLine = ""
      line => {
        replacedLine = line + "\n"
        rP.findFirstIn(line) match {
          case Some(rP(prefix, nodeName, argNodeName)) => {
            if (nodeName == sourceName(node.name)) {
              replacedLine = prefix + nodeName + " = " + "PipeEngine" + 
               "(\"" + argNodeName + "\")\n"
            }
          }
          case None => {
          }
        }
        topWriter.write(replacedLine)
      }
    }
    topWriter.close()
  }

  def multiThreadEngine(node: Node) = {
    val rP = "(\\s*val\\s*)(\\w*)\\s*=\\s*Engine\\s*\\(\\s*\"(\\w*\\.\\w)\"\\s*\\)".r
    val topWriter = new PrintWriter(new File("output/TopRefined.scala" ))
    Source.fromFile("input/TopSSF.scala" ).getLines().foreach { 
      var replacedLine = ""
      line => {
        replacedLine = line + "\n"
        //replication with constant parameter
        rP.findFirstIn(line) match {
          case Some(rP(prefix, nodeName, argNodeName)) => {
            if (noVersionName(nodeName) == sourceName(node.name))
              replacedLine = prefix + nodeName + " = " + "MTEngine" + "(\"" + argNodeName + 
                "\", 2)\n"
          }
          case None => {
          }
        }
        topWriter.write(replacedLine)
      }
    }
    topWriter.close()
  }

  def replicateNode(node: Node) {
    val rP = "(\\s*val\\s*)(\\w*)\\s*=\\s*(.*)".r
    val topWriter = new PrintWriter(new File("output/TopRefined.scala" ))
    Source.fromFile("input/TopSSF.scala" ).getLines().foreach { 
      var replacedLine = ""
      line => {
        replacedLine = line + "\n"
        //replication with constant parameter
        rP.findFirstIn(line) match {
          case Some(rP(prefix, nodeName, rest)) => {
            if (nodeName == sourceName(node.name))
              replacedLine = prefix + nodeName + " = Replicate(" + rest + ", 2)\n"
          }
          case None => {
          }
        }
        topWriter.write(replacedLine)
      }
    }
    topWriter.close()
  }

  def secondParamIsLogBased(node: Node) : Boolean = {
    var rv = false
    val prP = "(\\s*val\\s*)(\\w*)\\s*=\\s*(\\w*)\\s*\\(\\s*\"?(\\S*)\"?\\s*,\\s*.*#(.*LOG).*\\s*\\)".r
    Source.fromFile("input/TopSSF.scala" ).getLines().foreach { 
      line => {
        prP.findFirstIn(line) match {
          case Some(prP(prefix, nodeName, compFunc, argNodeName, replicationFactor)) => {
            if (nodeName == sourceName(node.name)) {
              rv = true
            }
          }
          case None => {
          }
        } 
      }
    }
    rv
  }

  def changeSecondParam(node: Node, f:(String) => String) = {
    val rP = "(\\s*val\\s*)(\\w*)\\s*=\\s*(\\w*)\\s*\\(\\s*(\\S*)\\s*,\\s*(\\d*)\\s*\\)".r
    //val rP = "(\\s*val\\s*)(\\w*)\\s*=\\s*(\\w*)\\s*\\(\\s*\"?(\\S*)\"?\\s*,\\s*(\\d*)\\s*\\)".r
    //val prP = "(\\s*val\\s*)(\\w*)\\s*=\\s*(\\w*)\\s*\\(\\s*\"?(\\S*)\"?\\s*,\\s*#(\\w*)\\s*\\)".r
    val prP = "(\\s*val\\s*)(\\w*)\\s*=\\s*(\\w*)\\s*\\(\\s*\"?(\\S*)\"?\\s*,\\s*.*#(\\w*).*\\s*\\)".r
    val cP = "(\\w*)\\W*(\\w*)".r
    val topWriter = new PrintWriter(new File("output/TopRefined.scala" ))
    Source.fromFile("input/TopSSF.scala" ).getLines().foreach { 
      var replacedLine = ""
      line => {
        replacedLine = line + "\n"
        //replication with constant parameter
        rP.findFirstIn(line) match {
          case Some(rP(prefix, nodeName, compFunc, argNodeName, replicationFactor)) => {
            if (nodeName == sourceName(node.name))
              replacedLine = prefix + nodeName + " = " + compFunc + "(" + argNodeName + 
                ", " + f(replicationFactor) + ")\n"
          }
          case None => {
          }
        }
        //replication with configed parameter
        prP.findFirstIn(line) match {
          case Some(prP(prefix, nodeName, compFunc, argNodeName, replicationFactor)) => {
            if (nodeName == sourceName(node.name)) {
              val configWriter = new PrintWriter(new File("output/TopConfig" ))
              Source.fromFile("input/TopConfig").getLines().foreach {
                configLine => {
                  cP.findFirstIn(configLine) match {
                    case Some(cP(parameter, value)) => {
                      if (parameter == replicationFactor) {
                        configWriter.write(parameter + " " + f(value) + "\n")
                      } else {
                        configWriter.write(configLine + "\n")
                      }
                    }
                    case None => {
                      configWriter.write(configLine + "\n")
                    }
                  }
                }
              }
              configWriter.close()
            }
          }
          case None => {
          }
        }
        topWriter.write(replacedLine)
      }
    }
    topWriter.close()
  }

//  def preProcessTop() = {
//    setEngineClassNames()
//  }  

  def className(nodeName: String) : String = {
    val cnP = "__class__(\\w*)".r
    cnP.findFirstIn(nodeName) match {
      case Some(cnP(cn)) => {
        cn  
      }
      case None => nodeName 
    }
  }

  def isEngineInTheSource(node: String) : Boolean = {
    var rv = false
    val eP = "\\s*val\\s*(\\w*)\\s*=\\s*Engine\\s*\\(\\s*\"(\\w*)\\.c.*".r
    Source.fromFile("input/TopSSF.scala" ).getLines().foreach { 
      line => {
        eP.findFirstIn(line) match {
          case Some(eP(nodeName, className)) => {
            rv = true
          }
          case None => {
            rv = false
          }
        }
      }
    }  
    rv
  }

//  def setEngineClassNames() = {
//    val mteP = "\\s*val\\s*(\\w*)\\s*=\\s*MTEngine\\s*\\(\\s*\"(\\w*)\\.c.*".r
//    val peP = "\\s*val\\s*(\\w*)\\s*=\\s*PipeEngine\\s*\\(\\s*\"(\\w*)\\.c.*".r
//    val eP = "\\s*val\\s*(\\w*)\\s*=\\s*Engine\\s*\\(\\s*\"(\\w*)\\.c.*".r
//    Source.fromFile("input/TopSSF.scala" ).getLines().foreach { 
//      line => {
//        mteP.findFirstIn(line) match {
//          case Some(mteP(nodeName, className)) => {
//            if (nodes.contains(nodeName)) {
//              nodes.getOrElse(nodeName, null).nodeType = MTEngine
//              nodes.getOrElse(nodeName, null).className = className 
//            }
//          }
//          case None =>
//        }
//        peP.findFirstIn(line) match {
//          case Some(peP(nodeName, className)) => {
//            if (nodes.contains(nodeName)) {
//              nodes.getOrElse(nodeName, null).nodeType = PipeEngine
//              nodes.getOrElse(nodeName, null).className = className 
//            }
//          }
//          case None =>
//        }
//        eP.findFirstIn(line) match {
//          case Some(eP(nodeName, className)) => {
//            if (nodes.contains(nodeName)) {
//              nodes.getOrElse(nodeName, null).nodeType = Engine
//              nodes.getOrElse(nodeName, null).className = className 
//            }
//          }
//          case None =>
//        }
//      }
//    } 
//  }


  val bpThreshold = 0.1
  val orThreshold = 0.6
  val bpDampFactor = 0.4
  var intact = true
  val debugBp = false 
  def isBottleneck(n: Node): Boolean = {
    if (debugBp && n.pc.inBackPressure > bpThreshold) {
    //if (n.pc.inBackPressure > bpThreshold && n.pc.outBackPressure < bpThreshold)
    //if (n.pc.inBackPressure > bpThreshold && n.pc.outBackPressure < bpThreshold &&
     // (n.pc.offBackPressure.size > 0 && n.pc.offBackPressure.map(i => i < bpThreshold).reduce(_&&_)))
     val obp =  if (n.pc.offBackPressure.size == 0)  0 else 
        n.pc.offBackPressure.reduce((a,b) => math.max(a,b))
      println(n.name + " input backpressure is: " + n.pc.inBackPressure + 
       " " + n.pc.offloadRate + " output backpressure is: " + 
       n.pc.outBackPressure + " max offload backpressure is: " + obp)
    }
    
    val rv = n.pc.inBackPressure > bpThreshold && 
     n.pc.outBackPressure < (n.pc.inBackPressure * bpDampFactor) && 
     (n.pc.offBackPressure.size == 0 || 
     n.pc.offBackPressure.map(i => 
     i < n.pc.inBackPressure * bpDampFactor).reduce(_&&_))
    if (rv && debugBp) {
      println(n.name + " is bottleneck")
    }
    rv
  }

  def doubleSecondParameter(n: Node) {
    if (secondParamIsLogBased(n)) {
      changeSecondParam(n, x => (x.toInt+1).toString)
    } else {
      changeSecondParam(n, x => (x.toInt*2).toString)
    }
  }


  def nodeRefine(n: Node): Boolean = {
    //*********************************************************
    //Rule: Pipeline 
    //*********************************************************
    if (intact && isBottleneck(n) && n.pipelinable && n.concurrentSafe && 
     n.nodeType == Engine && checkBackTrack()) { 
      println("Refine: In bottleneck node " + n.name  + ", backpressure is " + n.pc.inBackPressure + 
              ". pipelining the engine") 
      pipelineEngine(n) 
      intact = false
    }    
    //*********************************************************
    //Rule: MultiThread 
    //*********************************************************
    if (intact && isBottleneck(n) && n.nodeType == Engine && n.concurrentSafe && 
     n.pc.offloadRate/n.pc.engineUtilization > orThreshold && checkBackTrack()) { 
      println("Refine: In bottleneck node " + n.name  + ", backpressure is " + n.pc.inBackPressure + 
              ". multiThreading the engine") 
      //println("name is " + n.name + " type is " + n.nodeType)
      multiThreadEngine(n) 
      intact = false
    }
    //*********************************************************
    //Rule: Increase number of threads 
    //*********************************************************
    if (intact && isBottleneck(n) && n.nodeType == MTEngine && 
     n.pc.offloadRate/n.pc.engineUtilization > orThreshold && checkBackTrack()) { 
      println("Refine: In bottleneck node " + n.name  + ", backpressure is " + 
       n.pc.inBackPressure + ". offload rate is " + n.pc.offloadRate + 
       ". engine utilizaiton is " + n.pc.engineUtilization + 
       ". increase number of threads in engine") 
      doubleSecondParameter(n)
      intact = false
    }
    //*********************************************************
    //Rule: Increase replication factor 
    //*********************************************************
    if (intact && isBottleneck(n)) { 
      var ancestor : Node = null 
      var ancestorReplicated = false
      bottomUpSearch((i) => {ancestor = i; ancestorReplicated = (i.nodeType == Replicated); ancestorReplicated}, n)
      if (ancestorReplicated && checkBackTrack()) {
        println("Refine: In bottleneck node " + n.name  + ", backpressure is " + n.pc.inBackPressure + 
                ". Increase Replicating factor for node " + ancestor.name) 
        println("Node type is " + n.nodeType)
        intact = false
        doubleSecondParameter(ancestor)
      }
    }
    //*********************************************************
    ////Rule: Replicate
    //*********************************************************
    if (intact && isBottleneck(n) && n.concurrentSafe) {
      var ancestor : Node = null 
      var ancestorReplicated = false
      bottomUpSearch((i) => {ancestor = i; 
       ancestorReplicated = (i.nodeType == Replicated); 
        ancestorReplicated}, n)
      if (!ancestorReplicated && checkBackTrack()) {
        println("Refine: In bottleneck node " + n.name  + 
         ", backpressure is " + n.pc.inBackPressure + 
         ". Replicating the node") 
        println("node " + n.name + " pipelinable is " +
         n.pipelinable) 
        intact = false
        replicateNode(n)
      }
    } 
    intact 
  }

  def readDesignHierarchyAndPCs(path: String) = {
    println("Read hierarchy and PCs from " + path);
    val dhmP = "hierarchy --- parent (\\w*) child (\\w+)".r
    val dhlP = "hierarchy --- parent (\\w*) child (\\w*)".r
    val ibpP = "input back pressure (\\w*) received ([\\d\\.]*)".r
    val obpP = "output back pressure (\\w*) received ([\\d\\.]*)".r
    val offbpP = "offload back pressure (\\w*) (\\w*) received ([\\d\\.]*)".r
    val offrP = "offload rate (\\w*) received ([\\d\\.]*)".r
    val eurP = "engine utilization (\\w*) received ([\\d\\.]*)".r
 
 


    Source.fromFile(path + "/PCREPORT.out" ).getLines().foreach { 
      line => 
      dhlP.findFirstIn(line) match {
        case Some(dhmP(parent, child)) => {
          println ("parent is " + parent + " child is " + child)
          addNode(parent, child)
        }
        case Some(dhlP(parent, child)) => println ("leaf node is " + parent)
        case None => 
      } 
      ibpP.findFirstIn(line) match {
        case Some(ibpP(node, bp)) => 
         nodes.getOrElse(node, null).pc.inBackPressure = bp.toDouble 
        case None =>
      }
      obpP.findFirstIn(line) match {
        case Some(obpP(node, bp)) => 
         nodes.getOrElse(node, null).pc.outBackPressure = bp.toDouble 
        case None =>
      }
      offbpP.findFirstIn(line) match {
        case Some(offbpP(portNumber, node, bp)) => {
          nodes.getOrElse(node, null).pc.offBackPressure += bp.toDouble 
        }
        case None =>
      }
      offrP.findFirstIn(line) match {
        case Some(offrP(node, or)) => {
          nodes.getOrElse(node, null).pc.offloadRate = or.toDouble 
        }
        case None =>
      }
      eurP.findFirstIn(line) match {
        case Some(eurP(node, eu)) => {
          nodes.getOrElse(node, null).pc.engineUtilization = eu.toDouble 
        }
        case None =>
      }
    }
  }

  def readEnginesMetaData(path: String) = {
    val tpP = "(\\w+)\\s*(\\w+)\\s*(\\w+)".r
    val dpP = "(\\w+)\\s*(\\w+)".r

    Source.fromFile(path + "/enginesMD").getLines().foreach { 
      line => 
      tpP.findFirstIn(line) match {
        case Some(tpP(fp, sp, tp)) => {
          sp match {
            case "PIPELINABLE" => {
              val n = nodes.find({case (x, y) => y.className == fp}) 
              if (n != None) {
                //n.getOrElse(null).pipelinable = true
                //n.getOrElse(null).pipelineStages = tp.toInt 
                n.getOrElse(null)._2.pipelinable = true;
                n.getOrElse(null)._2.pipelineStages = tp.toInt;
              }
            }
            case _ =>
          }
        }
        case None => 
      } 
      dpP.findFirstIn(line) match {
        case Some(dpP(fp, sp)) => { 
           sp match {
             case "CONCURRENT_SAFE" =>  {
               val n = nodes.find({case (x, y) => y.className == fp}) 
               if (n != None) {
                 //println(fp + " is CONCURRENT_SAFE " +
                 // "lv name is" + n.getOrElse(null)._1)
                 n.getOrElse(null)._2.concurrentSafe = true;
               }
             }
             case _ =>
           }
         }
        case None =>
      }
    }
  }
  def main(args: Array[String]) = {
    backTrack = readBackTrack()
    readDesignHierarchyAndPCs(args(0)) 
    //preProcessTop()
    readEnginesMetaData(args(0))
    topDownSearch(n => nodeRefine(n))
  }
}

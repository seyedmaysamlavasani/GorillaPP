package Tutorial

import Chisel._
import Node._
import Literal._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


/*This source implements a module that adds a constat value 
 *to each input data element, and send the result out as 
 * the output data element. The constant value is retrived 
 * through an offload interface. 
 */

class Top extends Component with GorillaUtil {
  val io = new gInOutBundle (() => new testStruct_t, () => new testStruct_t)
  val main =  MTEngine("incThroughOffload.c", 1)
  val incFactor =  Engine("sendConst.c")
  val result =  Offload(main, incFactor, "incrementFactor")
}

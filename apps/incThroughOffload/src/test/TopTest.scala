//class TopTests(o: Top) extends Tester(o, Array(o.io)) {
class TopTests(o: Top) extends gTester(o) {
  defTests {
    val inputData = List(1, 2, 4, 8)
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    val iDelay = 2

    //Spin for a while without any test input
    for (time <- 0 until 5) {
      svars(o.io.in.valid) = Bool(false)
      //svars(o.io.in.bits) = UFix(0) 
      svars(o.io.in.bits.a) = UFix(0) 
      svars(o.io.pcIn.valid)= Bool(false)
      svars(o.io.pcIn.bits.pcType)= Pcounters.pcReset
      svars(o.io.pcIn.bits.moduleId)= UFix(0) 
      svars(o.io.pcIn.bits.portId)= UFix(0) 
      step(svars, ovars, false)
    }

    //Reset the PC Ring
    svars(o.io.pcIn.valid) = Bool(true)
    svars(o.io.pcIn.bits.request) = Bool(true) 
    svars(o.io.pcIn.bits.pcType) = Pcounters.pcReset
    step(svars, ovars)
    svars(o.io.pcIn.valid) = Bool(false)
    while(ovars(o.io.pcOut.valid).litValue().intValue == 0) {
      step(svars, ovars)
    }
    println("PCREPORT: Performance counter reset received")

    //Actual tests 
    var sourced = 0
    var sourcedIndex = 0
    var sinked = 0
    var sinkedIndex = 0
    var allPassed = true
    var cycles = 0
    val numOfInputs = 100 
    while(sourced < numOfInputs || sinked < numOfInputs) {
      if ((sourced < numOfInputs) && (cycles % iDelay == 0)) {
        //svars(o.io.in.bits) = Bits(inputData(sourcedIndex))
        svars(o.io.in.bits.a) = Bits(inputData(sourcedIndex))
        svars(o.io.in.valid) = Bool(true)
        svars(o.io.out.ready) = Bool(true)
      } else {
        //svars(o.io.in.bits) = UFix(0) 
        svars(o.io.in.bits.a) = UFix(0) 
        svars(o.io.in.valid) = Bool(false)
        svars(o.io.out.ready) = Bool(true)
      }
      step(svars, ovars)
      // bump counters and check outputs after advancing clock
      if (ovars(o.io.in.ready).litValue() == 1 && (cycles % iDelay == 0) && (sourced < numOfInputs)) {
        sourced += 1
        sourcedIndex = sourced % inputData.length 
        println("sourced and sourcedIndex are " + sourced + " " + sourcedIndex + " sinked is " + sinked)
      }
      if (ovars(o.io.out.valid).litValue() == 1) {
        //When multi-threading or replication is used order of outputs are not preserved.
        //Otherwise, we can check the values 
        //allPassed = allPassed && (ovars(o.io.out.bits).litValue() == (inputData(sinkedIndex) + 2))
        allPassed = allPassed && (ovars(o.io.out.bits.a).litValue() == (inputData(sinkedIndex) + 2))

        if (allPassed == false) {
			//println("Test failed because output is " + ovars(o.io.out.bits).litValue() + 
			println("Test failed because output is " + ovars(o.io.out.bits.a).litValue() + 
                          " expected " + (inputData(sinkedIndex) +2))
			println("Sinked is " + sinked)
        }
		//println("At " + cycles + " output " + ovars(o.io.out.bits).litValue() + 
		println("At " + cycles + " output " + ovars(o.io.out.bits.a).litValue() + 
         " sinked. sinked is " + sinked);
        sinked += 1
        sinkedIndex = sinked % inputData.length 
      }
      cycles += 1
    }
    step(svars, ovars)

    val UsePCReport = true 
    if (!UsePCReport) {
      // Inquire the back pressure through the PC ring
      svars(o.io.pcIn.valid) = Bool(true)
      svars(o.io.pcIn.bits.request) = Bool(true) 
      svars(o.io.pcIn.bits.pcType) = Pcounters.backPressure
      svars(o.io.pcIn.bits.moduleId) = UFix(3) //incthrough module Replicated
      svars(o.io.pcIn.bits.portId) = UFix(1) //Input port
      step(svars, ovars)
      svars(o.io.pcIn.valid) = Bool(false)
      while(ovars(o.io.pcOut.valid).litValue().intValue() == 0) {
        step(svars, ovars)
      }
      println("PCREPORT: back pressure received " + ovars(o.io.pcOut.bits.pcValue))
      println("cycles: " + cycles)
    } else {
      PCReport(cycles, numOfInputs)
    }
    step(svars, ovars)

    allPassed 
  }
}



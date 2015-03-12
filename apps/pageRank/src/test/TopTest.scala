class TopTests(o: Top) extends gTester(o) with include {
  defTests {
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    val iDelay = 1

    for (time <- 0 until 5) {
      svars(o.io.in.valid) = Bool(false)
      step(svars, ovars, false)
    }
    if (compilerControl.pcEnable) {
      resetPC()
    }
    val timeOut = 500
    var sourced = 0
    var sinked = 0
    var allPassed = true
    var cycles = 0
    var lastReceivedCycle = 0 
    var pageId = 0
    var pageChunkSize = 5 
    val numOfPageChunks = 20  
    while((sourced < numOfPageChunks) /*|| (sinked < 400)*/ && ((cycles-lastReceivedCycle) < timeOut)) {
      if (cycles % 200 == 0) {
        println("At " + cycles + " lastReceived was " + lastReceivedCycle )
      }
      pageId = sourced * pageChunkSize
      svars(o.io.out.ready) = Bool(true)
      svars(o.io.in.bits.startPageId) = UFix(pageId) 
      svars(o.io.in.bits.length) = UFix(pageChunkSize)
      if (cycles % iDelay == 0 && sourced < numOfPageChunks) {
        if (sourced == numOfPageChunks-1) {
          svars(o.io.in.valid) = Bool(true)
          svars(o.io.in.bits.done) = Bool(false)  //TODO: making this true causes a problem
        } else { 
          svars(o.io.in.valid) = Bool(true)
          svars(o.io.in.bits.done) = Bool(false)
        }
      } else {
        svars(o.io.in.valid) = Bool(false)
      }
      step(svars, ovars)
      // bump counters and check outputs after advancing the clock
      if (ovars(o.io.in.ready).litValue() == 1 && 
       (cycles % iDelay == 0) && 
       (sourced < numOfPageChunks)) {
        sourced += 1
        println("sourced is " + sourced)
      }
      cycles += 1
      if (ovars(o.io.out.valid).litValue() == 1) {
        if (allPassed == false) {
          println("Test failed ") 
          println("Sinked is " + sinked)
        }
        sinked += 1
        println("At " + cycles + " outpout " + " sinked. Sinked is " + sinked + 
         " sourced is " + sourced );
        lastReceivedCycle = cycles;
      }
    }
    println("At the end, after while loop\n");
    if (compilerControl.pcEnable) {
      println("XXX last received at " + lastReceivedCycle + " " + cycles)
      PCReport(lastReceivedCycle, numOfPageChunks * pageChunkSize)
    }
    step(svars, ovars)
    allPassed 
  }
}



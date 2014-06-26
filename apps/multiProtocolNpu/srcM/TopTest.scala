//class TopTests(o: Top) extends Tester(o, Array(o.io)) {
class TopTests(o: Top) extends gTester(o) { 
  defTests {
    val inputs_data = List(1, 2, 4, 8)
    //val test = new Top 
    val iDelay = 1
    for (time <- 0 until 5) {
      svars(o.io.in.valid) = Bool(false)
      svars(o.io.pcIn.valid)= Bool(false)
      svars(o.io.pcIn.bits.pcType)= Pcounters.pcReset
      svars(o.io.pcIn.bits.moduleId)= UFix(0) 
      svars(o.io.pcIn.bits.portId)= UFix(0) 
      step(svars, ovars, false)
    }
    svars(o.io.pcIn.valid) = Bool(true)
    svars(o.io.pcIn.bits.request) = Bool(true) 
    svars(o.io.pcIn.bits.pcType) = Pcounters.pcReset
    step(svars, ovars)
    svars(o.io.pcIn.valid) = Bool(false)
    while(ovars(o.io.pcOut.valid).litValue().intValue == 0) {
      step(svars, ovars)
    }
    println("PCREPORT: Performance counter reset received")
    var sourced = 0
    var sourcedIndex = 0
    var sinked = 0
    var sinkedIndex = 0
    var time = 0
    //var sinkStarted = false
    var allPassed = true
    var cycles = 0
    val numOfPackets = 3000
    while(cycles > 50000 || sourced < numOfPackets || sinked < numOfPackets-100) {
      if ((sourced < numOfPackets) && (cycles % iDelay == 0)) {
        //svars(o.io.in.bits) = Bits(inputs_data(sourcedIndex))
        svars(o.io.in.bits.l2Protocol) = Bits(128)
        svars(o.io.in.bits.eth.l3Type) = Bits(64)
        svars(o.io.in.bits.ipv4.ttl) = Bits(4)
        svars(o.io.in.valid) = Bool(true)
        svars(o.io.out.ready) = Bool(true)
      } else {
        //svars(o.io.in.bits) = UFix(0) 
        svars(o.io.in.valid) = Bool(false)
        svars(o.io.out.ready) = Bool(true)
      }
      step(svars, ovars)
      // bump counters and check outputs after advancing clock
      if (ovars(o.io.in.ready).litValue() == 1 && (cycles % iDelay == 0) && (sourced < numOfPackets)) {
        sourced += 1
        sourcedIndex = sourced % 4 
        println("sourced and sourcedIndex are " + sourced + " " + sourcedIndex)
      }
      cycles += 1
      if (ovars(o.io.out.valid).litValue() == 1) {
        if (allPassed == false) {
			println("Test failed ") //+ ovars(o.io.out.bits).litValue() + 
                          //" expected " + (inputs_data(sinkedIndex) +2))
			println("Sinked is " + sinked)
        }
		println("At " + time + " outpout " + " sinked. Sinked is" + sinked + " sourced is " + sourced );
        sinked += 1
        sinkedIndex = sinked % 4
      }
      time += 1
    }
    //get ipv4Engine moduleID
    //getBackPressures(cycles)
    //println("PCREPORT: throughput " + numOfPackets.toDouble/cycles.toDouble)
    PCReport(cycles, numOfPackets)
    step(svars, ovars)
    allPassed 
  }
}

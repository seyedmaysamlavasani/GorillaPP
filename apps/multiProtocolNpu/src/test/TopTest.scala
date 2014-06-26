class TopTests(o: Top) extends gTester(o) { 
  defTests {
    val inputs_data = List(1, 2, 4, 8)
    //val test = new Top 
    val iDelay = 1
    for (time <- 0 until 5) {
      svars(o.io.in.valid) = Bool(false)
      step(svars, ovars, false)
    }
    if (compilerControl.pcEnable) {
      resetPC()
    }
    var sourced = 0
    var sourcedIndex = 0
    var sinked = 0
    var sinkedIndex = 0
    var time = 0
    var allPassed = true
    var cycles = 0
    //val numOfPackets = 5000
    val numOfPackets = 2500
    val version = UFix(4, width=4)
    val hLength = UFix(14, width=4)
    val tos = UFix(0, width=8)
    val length = UFix(21, width=16)
    val identification = UFix(10, width=16)
    val flagsOffset = UFix(10, width=16)
    val ttl = UFix(5, width=8)
    val protocol = UFix(4, width=8)
    val checksum = UFix(21, width=16)
    val srcAddr = UFix(0, width=32)
    val dstAddr = UFix(0, width=32)
    val ipv4Header = Cat(version, hLength, tos, length, 
     identification, flagsOffset, ttl, protocol, checksum, 
     srcAddr, dstAddr)
    while(sourced < numOfPackets || sinked < numOfPackets-100) {
      if ((sourced < numOfPackets) && (cycles % iDelay == 0)) {
        svars(o.io.in.bits.l3) = (new mpl3Header_t).fromBits(ipv4Header.toBits)
        svars(o.io.in.bits.l2Protocol) = Bits(128)
        svars(o.io.in.bits.eth.l3Type) = Bits(64)
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
          println("At " + time + " outpout " + " sinked. Sinked is " + 
                 sinked + " sourced is " + sourced );
        sinked += 1
        sinkedIndex = sinked % 4
      }
      time += 1
    }
    if (compilerControl.pcEnable) {
      PCReport(cycles, numOfPackets)
    }
    step(svars, ovars)
    allPassed 
  }
}

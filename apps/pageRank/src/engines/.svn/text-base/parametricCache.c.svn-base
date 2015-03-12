#pragma INPUT mem_req_t
#pragma OUTPUT mem_rep_t
#pragma OFFLOAD_ARRAY (tagBank(NUM_OF_WAYS), bankIndex_t, tagNlru_t)
#pragma OFFLOAD_ARRAY (dataBank(NUM_OF_WAYS), bankIndex_t, cacheLine_t)
#pragma OFFLOAD (dram, mem_req_t, mem_rep_t)

metaData_t md(NUM_OF_WAYS);
data_t line(NUM_OF_WAYS);
uint8_t way = NOT_FOUND_WAY_INDEX;

GS_HIT () {
  way = NOT_FOUND_WAY;
  uint8_t loadWay = NOT_FOUND_WAY;
  uint8_t lastLru = EARLIEST_IN_CACHE_LRU;
  uint8_t lruWay = NOT_FOUND_WAY_INDEX;
  lastLru = EARLIEST_IN_CACHE_LRU;
  for (i=0; i<NUM_OF_WAYS; i++) {
    md(i) = tagBank(i)(index(Input.addr))  
    line(i) = dataBank(i)(index(Input.addr))
  }
  for (i=0; i<NUM_OF_WAYS; i++) {
    //HIT?
    if (md(i).tag == tag(Input.addr) && md(i).status = LINE_PRESENT) {
      if (Input.req == LOAD) {
        Output = line(i);
        finish();
      } else { 
        State = GS_HIT_STORE_TO_CACHE;
      }
      Hit = 1;
    }
  } 
  if (!Hit) {
    for (i=0; i<NUM_OF_WAYS; i++) {
      //Any clean cache way to replace ?
      if (md(i).status == LINE_NOT_PRESENT || 
       md(i).status == LINE_CLEAN) {
        loadWay = i;  
      }
      //What is the LRU way ?
      if (OlderInLru(md(i).lru, lastLru)) {
        lastLru = md(i).lru;
        lruWay = i; 
      }
    }
    if (loadWay != NOT_FOUND_WAY) {
      way = loadWay;
      State = GS_LOAD_1;
    } else {
      way = evictWay;
      State = GS_EVICT;
    }
  }
}

GS_LOAD_1() {
  line(way) = dram(Input);
  md(way).tag = tag(Input.addr);
  md(way).status = LINE_CLEAN;
  //TODO: what about lru update
  State = GS_LOAD_2;
}

GS_LOAD_2() {
  tagW_Input.rw = WRITE;
  tagW_Input.md = md(way);
  dataW_Input.rw = WRITE;
  dataW_Input.data = line(way);
  tag(way)(tagW_Input); 
  data(way)(dataW_Input);
  if (Input.req == LOAD) {
    Output = tag
  } else {
  }
}

#pragma INPUT NP_EthMpl3Header_t 
#pragma OUTPUT NP_EthMpl3Header_t 
#pragma OFFLOAD(ipv4Lookup1, uint32_t, uint8_t)
#pragma OFFLOAD(ipv4Lookup2, uint32_t, uint8_t)
#pragma OFFLOAD(qosCount, uint32_t, uint8_t)
#pragma CONCURRENT_SAFE 

IPv4Header_t ipv4Input;
IPv4Header_t ipv4Output;
uint32_t gOutPort; 

//GS_init()
//{
//  ipv4Input = (IPv4Header_t) Input.l3;
//  State = GS_LOOKUP;
//}
GS_ETHERNET()
{
  ipv4Input = (IPv4Header_t) Input.l3;
  Output = Input;
  if (Input.l2Protocol == ETHERNET) {
    State = GS_IPV4;
  } else { 
    State = GS_EXCEPTION;
  }
}

GS_IPV4()
{
  if (Input.eth.l3Type == IPV4) {
    State = GS_LOOKUP;
    ipv4Output = ipv4Input;
  } else { 
    State = GS_EXCEPTION;
  }
//  if (ipv4Input.length < 20 || ipv4Input.verion != 4) {
//    State = GS_EXCEPTION;
//  }
}

GS_LOOKUP()
{
  int outPort;
  int srcLookupResult;
  
  outPort = ipv4Lookup1(ipv4Input.dstAddr);
  srcLookupResult = ipv4Lookup2(ipv4Input.srcAddr);
  Output.outPort = outPort + srcLookupResult;
  gOutPort = outPort;
//  if (srcLookupResult == INVALID_ADDRESS || 
//   outPort == INVALID_ADDRESS) {
//    State = GS_EXCEPTION;
//  } else {  
State = GS_UPDATE;
//  }
}

GS_UPDATE()
{
  uint8_t qcOutput;
  qcOutput = qosCount(ipv4Input.srcAddr);
  Output.outPort = qcOutput;
  finish();
}

GS_EXCEPTION() 
{
  Output.outPort = CONTROL_PLANE;
  finish();
}

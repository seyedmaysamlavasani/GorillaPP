#pragma INPUT NP_EthIPv4Header_t 
#pragma OUTPUT NP_EthIPv4Header_t 
#pragma OFFLOAD(lookup, uint32_t, uint8_t)
#pragma CONCURRENT_SAFE 

GS_ETHERNET()
{
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
    Output = Input;
  } else { 
    State = GS_EXCEPTION;
  }
}

GS_LOOKUP()
{
  int outPort;

  outPort = lookup(Input.ipv4.dstAddr);
  Output.outPort = outPort;
  State = GS_UPDATE;
}

GS_UPDATE()
{
   if (Input.ipv4.ttl == 1) {
    State = GS_EXCEPTION;
  } else {
    Output.ipv4.ttl = Input.ipv4.ttl - 1; 
    Output.ipv4.chksum = Input.ipv4.chksum + 0x80;  //TODO: Update this number
  }
  finish();
}

GS_EXCEPTION() 
{
  finish();
}

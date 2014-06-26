typedef struct {
  uint4_t version;
  uint4_t hLength;
  uint8_t tos;
  //uint16_t length;
  //uint16_t identification;
  //uint16_t flagsOffset;
  uint8_t ttl;
  uint8_t protocol;
  uint16_t chksum;
  uint32_t srcAddr;
  uint32_t dstAddr;
} IPv4Header_t;

typedef struct {
  //uint48_t dstAddr;
  uint8_t dstAddr;
  //uint48_t srcAddr;
  uint8_t srcAddr;
  uint8_t l3Type;
  uint8_t length;
} EthernetHeader_t;

typedef struct {
 uint8_t l2Protocol;
 uint8_t outPort;
 EthernetHeader_t  eth;
 IPv4Header_t  ipv4;
} NP_EthIPv4Header_t;

#define ETHERNET 0x80
#define IPV4  0x40


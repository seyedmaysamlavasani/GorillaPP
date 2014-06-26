typedef struct {
  uint4_t version;
  uint4_t hLength;
  uint8_t tos;
  uint16_t length;
  uint16_t identification;
  uint16_t flagsOffset;
  uint8_t ttl;
  uint8_t protocol;
  uint16_t chksum;
  uint32_t srcAddr;
  uint32_t dstAddr;
} IPv4Header_t;

typedef struct {
  uint4_t version;
  uint4_t hLength;
  uint8_t tos;
  uint16_t length;
  uint16_t identification;
  uint16_t flagsOffset;
  uint8_t ttl;
  uint8_t protocol;
  uint16_t chksum;
  uint128_t srcAddr;
  uint128_t dstAddr;
} IPv6Header_t;

typedef struct {
  uint128_t h1;
  uint128_t h2;
  uint128_t h3;
  uint128_t h4;
  uint128_t h5;
  uint128_t h6;
  uint128_t h7;
  uint128_t h8;
} mpl3Header_t;

typedef struct {
  uint48_t dstAddr;
  uint48_t srcAddr;
  uint8_t l3Type;
  uint8_t length;
} EthernetHeader_t;

typedef struct {
 uint8_t l2Protocol;
 uint8_t outPort;
 EthernetHeader_t  eth;
 IPv4Header_t  ipv4;
} NP_EthIPv4Header_t;

typedef struct {
 uint8_t l2Protocol;
 uint8_t outPort;
 EthernetHeader_t  eth;
 mpl3Header_t  l3;
} NP_EthMpl3Header_t;

//typedef struct {
//  uint32_t address;
//  uint1_t rw;
//  uint32_t wData; 
//} memReq32_t;
//
//typedef struct {
//  uint32_t rData; 
//} memRep32_t;
//
//typedef struct {
//  uint32_t address;
//} memReadOnlyReq32_t;
//
//typedef struct {
//  uint32_t data; 
//} memReadOnlyRep32_t;
//
//typedef struct {
//  uint32_t address;
//  uint32_t data; 
//} memWriteOnlyReq32_t;
//
//typedef struct {
//  uint8_t dummy;
//} memWriteOnlyRep32_t;
//


#define ETHERNET (uint8_t) 0x80
#define IPV4  (uint8_t) 0x40
#define test(x) x+1
#define CONTROL_PLANE 255
#define INVALID_ADDRESS 255

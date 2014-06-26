#include "emulator.h"

class Memo_t : public mod_t {
 public:
  dat_t<8> Memo__io_rdAddr;
  dat_t<1> Memo__io_ren;
  dat_t<8> Memo__io_wrData;
  dat_t<8> Memo__io_wrAddr;
  dat_t<1> Memo__io_wen;
  mem_t<8,256> Memo__mem;
  dat_t<8> Memo__io_rdData;

  void init ( bool rand_init = false );
  void clock_lo ( dat_t<1> reset );
  void clock_hi ( dat_t<1> reset );
  void print ( FILE* f );
  bool scan ( FILE* f );
  void dump ( FILE* f, int t );
};

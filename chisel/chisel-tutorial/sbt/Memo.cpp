#include "Memo.h"

void Memo_t::init ( bool rand_init ) {
  if (rand_init) Memo__mem.randomize();
}
void Memo_t::clock_lo ( dat_t<1> reset ) {
  val_t T0__w0;
  { T0__w0 = Memo__mem.get(Memo__io_rdAddr.values[0], 0); }
  val_t T1__w0;
  { val_t __mask = -Memo__io_ren.values[0]; T1__w0 = 0x0L ^ ((0x0L ^ T0__w0) & __mask); }
  { Memo__io_rdData.values[0] = T1__w0; }
}
void Memo_t::clock_hi ( dat_t<1> reset ) {
  { Memo__mem.put(Memo__io_wrAddr.values[0], 0, (Memo__io_wrData.values[0] & (-Memo__io_wen.values[0])) | (Memo__mem.get(Memo__io_wrAddr.values[0], 0) & ~(-Memo__io_wen.values[0]))); }
}
void Memo_t::print ( FILE* f ) {
  fprintf(f, "%s", TO_CSTR(Memo__io_rdData));
  fprintf(f, "\n");
  fflush(f);
}
bool Memo_t::scan ( FILE* f ) {
  str_to_dat(read_tok(f), Memo__io_wen);
  str_to_dat(read_tok(f), Memo__io_wrAddr);
  str_to_dat(read_tok(f), Memo__io_wrData);
  str_to_dat(read_tok(f), Memo__io_ren);
  str_to_dat(read_tok(f), Memo__io_rdAddr);
  return(!feof(f));
}
void Memo_t::dump(FILE *f, int t) {
}

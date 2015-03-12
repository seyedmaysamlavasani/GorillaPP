typedef uint64_t double;

typedef struct {
  uint64_t x;
  uint64_t y;
  uint64_t z;
} point_t;

typedef struct {
  bool centeroidsFinished;
  bool pointsFinished;
  uint16_t centeroidIndex;
  point_t point; 
} KEngineIO_t;

typedef struct {
  double in1;
  double  in2;
} FU_in_t;

typedef struct {
  double out; 
} FU_out_t;

typedef struct {
  point_t in1;
  point_t  in2;
} distanceFU_in_t;

typedef struct {
  double out;
} distanceFU_out_t; 

typedef struct {
  bool rw;
  uint16_t addr;
  point_t data;  
} pointMemInput_t;

typedef struct {
  point_t data;  
} pointMemOutput_t;

#define READ 0 
#define WRITE 1 
#define INFINITI 1<<63

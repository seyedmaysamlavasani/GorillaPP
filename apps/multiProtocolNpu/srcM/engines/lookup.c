#pragma INPUT uint32_t
#pragma OUTPUT uint8_t
#pragma OFFLOAD(qdr, uint32_t, uint32_t)
#pragma CONCURRENT_SAFE
#pragma PIPELINABLE

int result;

GS_FIRST_LEVEL()
{
  result = qdr(Input >> LOOKUP_FIRST_LEVEL_SHIFT); 
  State = GS_SECOND_LEVEL;
}

GS_SECOND_LEVEL()
{
 result = LOOKUP_SECOND_LEVEL_BASE + (result << LOOKUP_SECOND_LEVEL_CHUNK_SIZE_LOG) + ((Input >> (LOOKUP_SECOND_LEVEL_SHIFT)) & LOOKUP_SECOND_LEVEL_MASK);
 State = GS_THIRD_LEVEL;
}

GS_THIRD_LEVEL()
{
 result = LOOKUP_THIRD_LEVEL_BASE + (result << LOOKUP_THIRD_LEVEL_CHUNK_SIZE_LOG) + ((Input >> (LOOKUP_THIRD_LEVEL_SHIFT)) & LOOKUP_THIRD_LEVEL_MASK);
 Output = result;
 finish();
}

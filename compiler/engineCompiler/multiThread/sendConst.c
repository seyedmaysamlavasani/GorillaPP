#pragma INPUT uint32_t 
#pragma OUTPUT uint32_t 

GS_GET()
{
 Output = 2;
 State = GS_SEND;
}

GS_SEND()
{
 finish(); 
}


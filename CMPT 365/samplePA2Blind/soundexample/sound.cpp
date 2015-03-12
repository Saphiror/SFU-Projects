
#include "stdafx.h"
#include "sound.h"
#include <mmsystem.h>
#include <mmreg.h>

HWAVEOUT Sound::hWaveOut;

//================================================================================================
void Sound::init(){
    WAVEFORMATEX wfx; 

    wfx.nSamplesPerSec = 8000; 
    wfx.wBitsPerSample = 8; 
    wfx.nChannels = 1; 

    wfx.cbSize = 0; 
    wfx.wFormatTag = WAVE_FORMAT_PCM;
   // wfx.nBlockAlign = (wfx.wBitsPerSample >> 2) * wfx.nChannels;
    wfx.nBlockAlign = (wfx.wBitsPerSample >> 3) * wfx.nChannels;
    wfx.nAvgBytesPerSec = wfx.nBlockAlign * wfx.nSamplesPerSec;

  if(waveOutOpen(&hWaveOut, WAVE_MAPPER, &wfx, 0, 0, CALLBACK_NULL)
	  != MMSYSERR_NOERROR) 
//	int tt;
//	tt = waveOutOpen(&hWaveOut, ((UINT)1), &wfx, 0, 0, CALLBACK_NULL);
//    if(tt != MMSYSERR_NOERROR) 
	{
        fprintf(stderr, "unable to open WAVE_MAPPER device\n");
		int tt;
		tt = waveOutOpen(&hWaveOut, WAVE_MAPPER, &wfx, 0, 0, CALLBACK_NULL);
		MessageBox(0, "unable to open WAVE_MAPPER device\n", "Error", MB_ICONERROR|MB_OK);
/* DBG:		tt=MMSYSERR_ALLOCATED;
		tt=MMSYSERR_BADDEVICEID;
		tt=MMSYSERR_NODRIVER;
		tt=MMSYSERR_NOMEM;
		tt=WAVERR_BADFORMAT; //** this is it //
		tt=WAVERR_SYNC;  // END DBG */

         ExitProcess(1);
    }
}

//================================================================================================
void Sound::writeAudioBlock(LPSTR block)
{
    WAVEHDR header;
    ZeroMemory(&header, sizeof(WAVEHDR));
    header.dwBufferLength = 500;
    header.lpData = block;
    waveOutPrepareHeader(hWaveOut, &header, sizeof(WAVEHDR));
    waveOutWrite(hWaveOut, &header, sizeof(WAVEHDR));
	do{
		Sleep(100);
	}while(waveOutUnprepareHeader(hWaveOut,&header,sizeof(WAVEHDR)) == WAVERR_STILLPLAYING);
		
}
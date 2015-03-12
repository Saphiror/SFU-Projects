//file: soundthread.h

#include <mmsystem.h>

#ifndef SOUNDTHREAD_H
#define SOUNDTHREAD_H


class Sound
{
public:

	//================================================================================================
	static void init();

	//================================================================================================
	static void close(){waveOutReset(hWaveOut); waveOutClose(hWaveOut);};

	//================================================================================================
	static void writeAudioBlock(LPSTR block);

private:
	static HWAVEOUT hWaveOut;

};

#endif
// video.h: interface for the dxmedia class.

#ifndef video_h
#define video_h

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "stdafx.h"
#include <strmif.h>
#include <control.h>

class Video 
{
public:
	LPWSTR pFilename;
	int iRate;
	LONGLONG lDuration;
	BYTE * pImage;
	LONG lImageSize;
	bool paused;

	//================================================================================================
	Video();

	//================================================================================================
	virtual ~Video();
    
	//================================================================================================
	bool open(LPCSTR pSFileName, CRect rc, HWND hWnd);

	//================================================================================================
	void play();

	//================================================================================================
	void restart();

	//================================================================================================
	void stop(){bStopped=true; pControl->Stop(); restart();};

	//================================================================================================
	BYTE * getCurrentImage();

	//================================================================================================
	LONGLONG getCurrentPosition(){return lCurrentPosition;};

	//================================================================================================
	LONGLONG getDuration(){return lDuration;};

	//================================================================================================
	bool isEnded(){return((int)getCurrentPosition()>=(int)lDuration);};

	//================================================================================================
	bool isStopped(){return(bStopped);};

	//================================================================================================
	bool isRestarted(){return(bRestart);}; 


private:
	bool bStopped;
	bool bRestart;
	LONGLONG lCurrentPosition;

	IGraphBuilder * pGraph;	
	IMediaControl * pControl;
	IMediaEventEx * pEvent;
	IBasicVideo * pBasicVideo;
	IMediaSeeking * pSeek;
	IMediaPosition * pPosition;
	//CComQIPtr<IVideoWindow, &IID_IVideoWindow> pVidWin;	//custom
	IVideoWindow *pVideoWindow;	//custom
};

#endif
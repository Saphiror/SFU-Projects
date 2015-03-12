// video.cpp

#include <uuids.h>
#include "stdafx.h"
#include "pa2sample.h"
#include "video.h"

#include <math.h>
#include <strmif.h>
#include <dshow.h>
#include <control.h>
#include <errors.h>
#include <direct.h>
#include <afxpriv.h>
//#include <dmplugin.h>

#define RELEASE(pInterface) if(pInterface) {pInterface->Release(); pInterface=NULL; }

//================================================================================================
Video::Video()
{
	CoInitialize(NULL);

	pGraph = NULL;
	pControl = NULL;
	pEvent = NULL;
	pBasicVideo = NULL;
	pSeek = 0;
	pPosition=NULL;

	HRESULT hr;

	hr = CoCreateInstance(CLSID_FilterGraph, NULL, CLSCTX_INPROC_SERVER, IID_IGraphBuilder, (void **)&pGraph);
	if (FAILED(hr))
	{
		char buffer[255];
		AMGetErrorText(hr, buffer, 255);
		AfxMessageBox(buffer);
		throw;
	}
	
	pGraph->QueryInterface(IID_IMediaControl, (void **)&pControl);
	pGraph->QueryInterface(IID_IMediaEvent, (void **)&pEvent);
	pGraph->QueryInterface(IID_IBasicVideo, (void **)&pBasicVideo);
	pGraph->QueryInterface(IID_IMediaSeeking, (void **)&pSeek);
	pGraph->QueryInterface(IID_IMediaPosition, (void **)&pPosition);
	pGraph->QueryInterface(IID_IVideoWindow, (void **)&pVideoWindow);	//custom
	//IVideoWindow *pVidWin;
	
	pFilename = NULL;
	pImage = NULL;
	lImageSize = 0;
	lDuration = 0;
	iRate = 1;
	bStopped=true;
	bRestart=false;
	paused = true;
}

//================================================================================================
Video::~Video()
{

	if (pImage)
	{
		delete [] pImage;
		pImage=NULL;
	}
	
	pControl->Stop();	
	RELEASE(pEvent);
	RELEASE(pGraph);
	RELEASE(pSeek);
	RELEASE(pBasicVideo);
	RELEASE(pControl);
	CoUninitialize();
}

//================================================================================================
bool Video::open(LPCSTR pSFilename, CRect rc, HWND hWnd)
{
	if ((!pGraph)||(!pSeek))
		return false;

	USES_CONVERSION;
	
	if (pVideoWindow)	//custom
	{
//		pVidWin->put_AutoShow(OAFALSE);
	}
	pFilename = A2W(pSFilename);
	HRESULT hr=pGraph->RenderFile(pFilename, NULL);

	if(FAILED(hr))
	{
		char szMsg[256];
		AMGetErrorText(hr,szMsg,sizeof(szMsg));
		AfxMessageBox(szMsg);
	}

	pSeek->SetTimeFormat(&TIME_FORMAT_FRAME);
	pSeek->GetDuration(&lDuration);

	REFTIME totaltime;
	pPosition->get_Duration(&totaltime);

	if (pImage)
	{
		delete [] pImage;
		pImage = NULL;
		lImageSize = 0;
	}
	//ActiveWindow.close();	

	play();
	return true;
}

//================================================================================================
// Sets the current position of the video to the very beginning
void Video::restart()
{
	bRestart=true;
	iRate=1;
	lCurrentPosition = 0; 
    HRESULT hr = pSeek->SetPositions(&lCurrentPosition, AM_SEEKING_AbsolutePositioning ,
							 &lCurrentPosition, AM_SEEKING_NoPositioning);
}

void Video::play()
{
	bStopped=false; 
	
	if (paused || bRestart) 
	{
		if(pGraph)
		{
//			pVidWin->put_Visible(OATRUE);

			pGraph->QueryInterface(IID_IMediaControl, (void **)&pControl);
			pControl->Run();			

			bRestart = false;
			paused = false; 
		}
		else
			return;	
	}	
	else 
		{
			if (pGraph)
			{
				paused = true;
				pControl->Pause();
			}
			else
				return;
		}
}

//================================================================================================
BYTE * Video::getCurrentImage()
{
	HRESULT hr;

	if(!bRestart)
	{		
		pSeek->GetCurrentPosition(&lCurrentPosition);
		
		if(isEnded())
			return NULL;

		lCurrentPosition +=  iRate;
        hr = pSeek->SetPositions(&lCurrentPosition, AM_SEEKING_AbsolutePositioning ,&lCurrentPosition, AM_SEEKING_NoPositioning);		
	}
	bRestart=false;

	// initialize pImage buffer if necessary
	if (pImage == NULL)
	{
		lImageSize = 0;
		hr = pBasicVideo->GetCurrentImage(&lImageSize, (long *)NULL);
		if(lImageSize)
			pImage = new BYTE[lImageSize];
	}
	hr = pBasicVideo->GetCurrentImage(&lImageSize, (long *)pImage);

	return pImage;
}
/*
HRESULT Video::UpdateVideoWindow(const LPRECT prc)
{
	if (m_pWindowless == NULL)
	{
		return S_OK; // no-op
	}

	if (prc)
	{
		return m_pWindowless->SetVideoPosition(NULL, prc);
	}
	else
	{

		RECT rc;
		GetClientRect(m_hwndVideo, &rc);
		return m_pWindowless->SetVideoPosition(NULL, &rc);
	}

}*/
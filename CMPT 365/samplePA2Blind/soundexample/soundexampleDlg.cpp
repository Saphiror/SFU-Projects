// soundexampleDlg.cpp : implementation file
//

#include "stdafx.h"
#include "soundexample.h"
#include "soundexampleDlg.h"
#include "sound.h"
#include <math.h>

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

#define PI 3.141592653f
/////////////////////////////////////////////////////////////////////////////
// CSoundexampleDlg dialog

CSoundexampleDlg::CSoundexampleDlg(CWnd* pParent /*=NULL*/)
	: CDialog(CSoundexampleDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CSoundexampleDlg)
		// NOTE: the ClassWizard will add member initialization here
	//}}AFX_DATA_INIT
	// Note that LoadIcon does not require a subsequent DestroyIcon in Win32
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
	Sound::init();

}

CSoundexampleDlg::~CSoundexampleDlg()
{
	Sound::close();
}

void CSoundexampleDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CSoundexampleDlg)
		// NOTE: the ClassWizard will add DDX and DDV calls here
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CSoundexampleDlg, CDialog)
	//{{AFX_MSG_MAP(CSoundexampleDlg)
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_sound, OnSound)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CSoundexampleDlg message handlers

BOOL CSoundexampleDlg::OnInitDialog()
{
	CDialog::OnInitDialog();

	// Set the icon for this dialog.  The framework does this automatically
	//  when the application's main window is not a dialog
	SetIcon(m_hIcon, TRUE);			// Set big icon
	SetIcon(m_hIcon, FALSE);		// Set small icon
	
	// TODO: Add extra initialization here
	
	return TRUE;  // return TRUE  unless you set the focus to a control
}

// If you add a minimize button to your dialog, you will need the code below
//  to draw the icon.  For MFC applications using the document/view model,
//  this is automatically done for you by the framework.

void CSoundexampleDlg::OnPaint() 
{
	if (IsIconic())
	{
		CPaintDC dc(this); // device context for painting

		SendMessage(WM_ICONERASEBKGND, (WPARAM) dc.GetSafeHdc(), 0);

		// Center icon in client rectangle
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// Draw the icon
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialog::OnPaint();
	}
}

// The system calls this to obtain the cursor to display while the user drags
//  the minimized window.
HCURSOR CSoundexampleDlg::OnQueryDragIcon()
{
	return (HCURSOR) m_hIcon;
}

void CSoundexampleDlg::OnSound() 
{
	// produce a sin wave sound.
	float freq = 400.f;

/*	float freq[64];

	for(int f=0; f<=63; f++){
		freq[f]=0;
	}

	freq[31]=440.0;*/ //centre around A

	//for(int m=32; m<=63; m++){
	//	freq[m]= freq[m-1] * pow(2.0, (1.0/12.0));
	//}
	//for(int m=0; m<31; m++){
	//	freq[m] = freq[m+1] * pow(2.0, (-1.0/12.0));
	//}

	DWORD Fs=8000;
	int N=500;
	float* tt=new float[N];

	for(int i=0;i<N;i++)
	{
		tt[i]=(float)i/(float)Fs;
	}

	float intensity = 5.0f; //volume
	float *signal=new float[N];
	float ss;
	for(int i=0;i<N;i++)
	{
		//ss=sin(2.f*PI*freq*tt[i]);
		//signal[i]=signal[i]+intensity*ss;
		signal[i]=intensity*sin(2.f*PI*freq*tt[i]);
	}
	
	BYTE* data=new BYTE[N];
	for(int i=0;i<N;i++)
	{
		data[i]=(BYTE)(signal[i]+128.f);
		
	}

	delete []signal;

	Sound::writeAudioBlock((LPSTR)data);
	delete []data;
	delete []tt;
}

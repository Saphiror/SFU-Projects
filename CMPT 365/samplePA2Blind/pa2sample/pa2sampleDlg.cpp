// pa2sampleDlg.cpp : implementation file
//

#include "stdafx.h"
#include "pa2sample.h"
#include "pa2sampleDlg.h"
#include "video.h"
#include "sound.h"
#include <math.h>
#include "mmsystem.h" 
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

#define PI 3.141592653f

/////////////////////////////////////////////////////////////////////////////
// CPa2sampleDlg dialog

CPa2sampleDlg::CPa2sampleDlg(CWnd* pParent /*=NULL*/)
	: CDialog(CPa2sampleDlg::IDD, pParent)
	, m_pathname(_T(""))
{
	//{{AFX_DATA_INIT(CPa2sampleDlg)
		// NOTE: the ClassWizard will add member initialization here
	//}}AFX_DATA_INIT
	// Note that LoadIcon does not require a subsequent DestroyIcon in Win32
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
	video = NULL;
	Sound::init();
}

CPa2sampleDlg::~CPa2sampleDlg()
{
	delete video;
	Sound::close();
}

void CPa2sampleDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CPa2sampleDlg)
	// NOTE: the ClassWizard will add DDX and DDV calls here
	//}}AFX_DATA_MAP
	//DDX_Text(pDX, IDC_EDIT1, m_pathname);
}

BEGIN_MESSAGE_MAP(CPa2sampleDlg, CDialog)
	//{{AFX_MSG_MAP(CPa2sampleDlg)
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_COMMAND(IDR_MENU1, OnOpen)
	ON_BN_CLICKED(IDC_getframe, Ongetframe)
	ON_BN_CLICKED(APPCOMMAND_MEDIA_PLAY, OnPlay)
	ON_BN_CLICKED(APPCOMMAND_MEDIA_STOP, OnStop)
	//}}AFX_MSG_MAP
//	ON_BN_CLICKED(PLAY, &CPa2sampleDlg::OnBnClickedPlay)
//	ON_BN_CLICKED(STOP, &CPa2sampleDlg::OnBnClickedStop)
ON_BN_CLICKED(STOP, &CPa2sampleDlg::OnClickedStop)
ON_BN_CLICKED(PLAY, &CPa2sampleDlg::OnClickedPlay)
//ON_EN_UPDATE(IDC_EDIT1, &CPa2sampleDlg::OnUpdateEdit1)
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CPa2sampleDlg message handlers

BOOL CPa2sampleDlg::OnInitDialog()
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

void CPa2sampleDlg::OnPaint() 
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
		// in case, usr drag or pull the window.
		if(video!=NULL&&video->pImage) 
			showFrame((LPSTR)video->pImage); 
	}
}


HCURSOR CPa2sampleDlg::OnQueryDragIcon()
{
	return (HCURSOR) m_hIcon;
}



void CPa2sampleDlg::OnOpen() 
{
	
	CFileDialog dlgFile(TRUE, NULL, NULL, OFN_HIDEREADONLY | OFN_OVERWRITEPROMPT, "Movie Files (*.avi;*.mpg;*.mpeg)|*.avi;*.mpg;*.mpeg||", this);
	
	if (dlgFile.DoModal() == IDOK) 
	{	
		if(video!=NULL)	//a video is currently loaded
		{      
			delete video;
		}
		
		CRect rcOld;
		GetDlgItem(IDC_frame)->GetWindowRect(&rcOld);
		ScreenToClient(rcOld);
		CString sFileName  = dlgFile.GetPathName();
		//m_pathname=  dlgFile.GetPathName();
		
		
	
		video=new Video();
		if (!video->open(sFileName,rcOld,m_hWnd))
		{
			AfxMessageBox("No video interface. Assuming audio/MIDI file or unsupported video codec.");
			return;
		}
	
		video->restart(); //Take a look at this function in class video to see
		                  //what restart function is doing.
		
	}
}

void CPa2sampleDlg::OnPlay() 
{
	/*
	CWnd* pWnd=GetDlgItem(IDC_frame);
	CDC* pDC=pWnd->GetDC();
	CRect rect;
	pWnd->GetClientRect(&rect);
*/
/*	if(!video)
		return;
	
	LPSTR pDIB = (LPSTR)video->play(); 
		if(pDIB!=NULL)
			showFrame();	
*/
	video->play();
}

void CPa2sampleDlg::OnStop() 
{
	if(!video)
		return;
	else
		video->stop();
}

void CPa2sampleDlg::Ongetframe() 
{
		if(!video)return;
		if(video->isEnded()) 
		{
			AfxMessageBox("The end!");
			return;
		}

// The function getCurrentImage will retrive the current frame,
// and then move the pointer to the next frame.
// Take a look at the function.
		LPSTR pDIB = (LPSTR)video->getCurrentImage(); 
		if(pDIB!=NULL)
			showFrame(pDIB);

}

void CPa2sampleDlg::showFrame(LPSTR pImageInfo)
{
	
// The following code shows a routine to display a image which is saved in
// a block of RAM pointed by pImageInpo. For more information about handling 
// bitmap in RAM, you may refer to the PA2_Readme.doc in samplePA2videoeditor.zip 
// for 2004-1, which includes a small paragraph "understanding DIB bitmaps" 
// explaining how a DIB image is saved in RAM.
 	

	BITMAPINFOHEADER * pInfo = (BITMAPINFOHEADER *)pImageInfo;

	CWnd* pWnd=GetDlgItem(IDC_frame);
	
	CDC* pDC=pWnd->GetDC();
	int oldbk=pDC->SetBkMode(TRANSPARENT);
	int oldmode=pDC->SetMapMode(MM_TEXT);

	CRect rect;
	pWnd->GetClientRect(&rect);

	SetDIBitsToDevice(pDC->GetSafeHdc(), 0, 0, rect.Width(), rect.Height(),
		0, 0, 0, pInfo->biHeight,
		pImageInfo + *(LPDWORD)pImageInfo, (LPBITMAPINFO) pInfo, DIB_RGB_COLORS);
// end

// You have to resize the image to 64 by 64.

// the following code shows the way of how to obtain the 
// R,G and B values of a particular pixel (row 10, column 10).
	/*
	int x = 10;
	int y = 10;
	COLORREF rgb=pDC->GetPixel(x,y);
		        
	short r=(short)GetRValue(rgb);
	short g=(short)GetGValue(rgb);
	short b=(short)GetBValue(rgb);
	*/

	int red;
	int green;
	int blue;



	float Gray[244][244];// Grayscale RGB values; Max image stored in the snapshot is 244x244

	////RGB -> Gray
	for(int y=0; y<244; y++){
		for(int x=0; x<244; x++){
			COLORREF rgb=pDC->GetPixel(x,y);
			red = GetRValue(rgb);
			green = GetGValue(rgb);
			blue = GetBValue(rgb);
			Gray[x][y]= 0.299*red + 0.587*green + 0.114*blue; //Luminance image formula
		}
	}


	int c=244;
	int r=244;
	//get the actual size of image store in (r,c)






	//get the number needed to resize 
	float xval1 = r/64; 
	float yval1 = c/64;

	//round down so we dont exceed 64 pixels
	xval1 = floor (xval1);
	yval1 = floor (yval1);

	
	int xval = xval1;
	int yval = yval1;



	//Resize to 64x64 
	//Maybe divide the resized values by 255, get them on a scale of 0 to 1.0, 0 being Black and 1.0 being white

	float Resize[244][244];
	int count = 1;


	//Take the first row and column and add it to new array
	for(int y=0; y<c; y++){
		Resize[0][y]=Gray[0][y];
	}
	for(int x=0; x<r; x++){
		Resize[x][0]=Gray[x][0];
	}

	//Resize[0][0]=Gray[0][0];
	//resize column; choose every yvalth column and put that column into the resize array
	for(int y=1; y<c; y++){
		if(y%(yval)==0){//factor of val
			for(int x1=1; x1<r; x1++){
				Resize[x1][count] = Gray[x1][y];
			}
			count++;
		}
	}
	count=1;
	//rows; choose every xvalth row and put that row into he resize array
	for(int x=1; x<r; x++){
		if(x%(xval)==0){
			for(int y1=1; y1<c; y1++){
				Resize[count][y1]= Gray[x][y1];
			}
			count++;
		}
	}

	//Shrink Resize array, removing null values
	float Resize1[64][64];
	for(int col=0; col<64;col++){
		for(int row=0; row<64; row++){
			Resize1[row][col]=Resize[row][col];
		}
	}

	//Get to 0-15 from 0-255 for intensity
	//for(int y=0; y<64; y++){
	//	for(int x=0; x<64; x++){
	//		Resize1[x][y]= Resize1[x][y]/255;
	//		Resize1[x][y]=Resize1[x][y]*15;
	//		//Resize1[x][y]=Resize1[x][y]/15;
	//	}
	//}
	

	/*srand (time(NULL));
	float Gray[64][64];
	for(int y=0; y<64;y++){
		for(int x=0; x<64;x++){
			Gray[x][y]= rand() % 255 + 1;
		}
	}*/

// end
// Here, you have to call functions of class sound which is included in another
// example soundexample.zip to 'sing' the image.

	DWORD Fs=8000; // sampling frequency


	float freq[64];

	for(int f=0; f<=63; f++){
		freq[f]=0;
	}

	freq[31]=440.f; //centre around A

	for(int m=32; m<=63; m++){
		freq[m]= freq[m-1] * pow(2.0, (1.0/12.0));
	}
	for(int m=30; m>=0; m--){
		freq[m] = freq[m+1] * pow(2.0, (-1.0/12.0));
	}

	int N = 500; //  500/8000=0.0625 seconds per column

	//samples
	float* tt=new float[N];
	for(int i=0; i<N;i++){
		tt[i]=(float)i/(float)Fs;
	}

	float intensity;
	float *signal=new float[N];


	//for(int s=0; s<N; s++){
	//	signal[s]=0;
	//}
	int m =0;

	float ss;
	BYTE *data=new BYTE[N];

	for(int col = 0; col<64; col++){
		signal[col]=0;
		for(int row=0; row<64; row++){
			intensity= (((Resize1[row][col])/255)*15);//contains value 0-15 for Loudness
			m=64-row;//lower column numbers are at the top = higher freq

			for(int i=0; i<N; i++){
				ss= sin(2.f*PI*freq[m]*tt[i]);//sound of specific pixel
				signal[col]= signal[col] + intensity*ss;//add to the chord of the column
				data[i]=(BYTE)(signal[col]+128.f);

			}
				
		}
		//signal[col]=signal[col]/64;
		//data[col]=(BYTE)(signal[col]+128.f);
		Sound::writeAudioBlock((LPSTR)data);
	}
	
	//ending click
	float *click= new float[N];
	intensity= 20.f;
	for(int i=0; i<N;i++){
	 click[i] = intensity*(sin(2*PI*50*tt[i]));
	}
	for(int i=0; i<N;i++){
		data[i]=(BYTE)(click[i]+128.f);
	}
	Sleep(10);
	Sound::writeAudioBlock((LPSTR)data);
	
	delete[]signal;
	delete[]click;
	delete[]data;
	delete[]tt;




	//float freq = 440.f;
	//DWORD Fs=8000;
	//int N=500;
	//float* tt=new float[N];

	//for(int i=0;i<N;i++)
	//{
	//	tt[i]=(float)i/(float)Fs;
	//}

	//float intensity = 0.5f; //volume
	//float *signal=new float[N];

	//for(int i=0;i<N;i++)
	//{
	//	signal[i]=intensity*sin(2.f*PI*freq*tt[i]);
	//}
	//
	//BYTE* data=new BYTE[N];
	//for(int i=0;i<N;i++)
	//{
	//	data[i]=(BYTE)(signal[i]+128.f);
	//}

	//delete []signal;
	//Sound::writeAudioBlock((LPSTR)data);
	//delete []data;
	//delete []tt;


	ReleaseDC(pDC);
}


void CPa2sampleDlg::OnClickedStop()
{
	
		if(!video)
		return;
	else
		video->stop();
}


void CPa2sampleDlg::OnClickedPlay()
{
	
			if(!video)
		return;
	else
		video->play();

}


// pa2sampleDlg.cpp : implementation file
//

#include "stdafx.h"
#include "pa2sample.h"
#include "pa2sampleDlg.h"
#include "video.h"
#include "sound.h"
#include <Math.h>
#include "mmsystem.h" 
#include <algorithm>  //min

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif



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
	
	ON_STN_CLICKED(IDC_message, &CPa2sampleDlg::OnStnClickedmessage)
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
	int frames=50;// Default number of total frames
	float Hist_all[50][32][32]; // 3D array of all histograms in frames
	SetDlgItemText(IDC_message, "");
	int z =0;
	CRect rect;
	int chrome[32][2];// Chromarticity array of specific column per frame; each index has 2 values: r,g
	float Hist[7][7];//Histogram for column; Sturges Rule 6 =1 + log2 32; 7 Blocks: 0-6
	CString sTmp;//for testing

	if(!video)return;
	if(video->isEnded()) 
	{
		AfxMessageBox("The end!");
		return;
	}
	LPSTR pDIB = (LPSTR)video->getCurrentImage(); 
	if(pDIB!=NULL){
		while(frames>0){//cycle through all frames
			pDIB = (LPSTR)video->getCurrentImage(); 
			BITMAPINFOHEADER * pInfo = (BITMAPINFOHEADER *)pDIB;
			CWnd* pWnd=GetDlgItem(IDC_frame);
			CDC* pDC=pWnd->GetDC();
			int oldbk=pDC->SetBkMode(TRANSPARENT);
			int oldmode=pDC->SetMapMode(MM_TEXT);
			pWnd->GetClientRect(&rect);
			StretchDIBits(pDC->GetSafeHdc(), 0, 0, 32, 32, 0, 0, pInfo->biWidth, pInfo->biHeight, pDIB + *(LPDWORD)pDIB, (LPBITMAPINFO) pInfo, DIB_RGB_COLORS, SRCCOPY);

			
			int R; int G; int B; double r; double g;

			for(int y =0; y<32; y++){
				COLORREF rgb = pDC->GetPixel(16,y);//get the middle column
				R=GetRValue(rgb);
				G=GetGValue(rgb);
				B=GetBValue(rgb);

				if(R+G+B==0){ // check for division by zero
					r=0;
					g=0;
				}
				else{

					r = R/(R+G+B)*6;//chromaticity
					g = G/(R+G+B)*6;//from 0-6
					r = floor(r+0.5);//rounding to nearest integer
					g= floor(g+0.5);

				}

				chrome[y][0]=r;
				chrome[y][1]=g;

			}


			
			for(int i=0; i<7; i++){ //setting values to 0
				for(int j=0; j<7; j++){
					Hist[i][j]=0;
				}
			}

			for(int x=0; x<32; x++){
				for(int i=0; i<7; i++){
					for(int j=0; j<7;j++){
						if( chrome[x][0]==i && chrome[x][1]==j){
							Hist[i][j]=Hist[i][j] + 1;//increase the count at that (r,g)
						}
					}
				}
			}



			
			//normalize histogram
			for(int x=0; x<7; x++){
				for(int y=0; y<7; y++){
					Hist[x][y]=(Hist[x][y])/32;//now Sum of Histogram = 1

				}
			}

			//put into total histogram
			for(int x=0; x<7; x++){
				for(int y=0; y<7; y++){
					Hist_all[z][x][y]=Hist[x][y];
				}
			}
			z++;frames--;
			ReleaseDC(pDC);

		}//end while
		
		
		frames =50;
		float min=0;
		
		while(frames>=2){
			for(int x=0; x<7; x++){
				for(int y=0; y<7; y++){
					min+=  min(Hist_all[frames-1][x][y], Hist_all[frames-2][x][y]);//compare histogram t to previous histogram t-1

				}
			}
			//sTmp.Format("%.2f",min);
			//SetDlgItemText(IDC_transition, sTmp);

			if(min<=.875){//closer to 0
				//output "transition detected" at frame
				SetDlgItemText(IDC_message, "Transition Detected!");
				min=0;

				//OnClickedPlay();
			}
			else{
				//no Transition detected
				//SetDlgItemText(IDC_message, "No Transition Detected!");
				min=0;
			}
			frames--;

		}

	
	}//end if
	

}

void CPa2sampleDlg::GetFrame()
{


}

void CPa2sampleDlg::showFrame(LPSTR pImageInfo)
{

	// The following code shows a routine to display a image which is saved in
	// a block of RAM pointed by pImageInpo. For more information about handling 
	// bitmap in RAM, you may refer to the PA2_Readme.doc in samplePA2videoeditor.zip 
	// for 2004-1, which includes a small paragraph "understanding DIB bitmaps" 
	// explaining how a DIB image is saved in RAM.


	//BITMAPINFOHEADER * pInfo = (BITMAPINFOHEADER *)pImageInfo;

	//CWnd* pWnd=GetDlgItem(IDC_frame);
	//
	//CDC* pDC=pWnd->GetDC();
	//int oldbk=pDC->SetBkMode(TRANSPARENT);
	//int oldmode=pDC->SetMapMode(MM_TEXT);

	//CRect rect;
	//pWnd->GetClientRect(&rect);

	//StretchDIBits(pDC->GetSafeHdc(), 0, 0, 32, 32, 0, 0, pInfo->biWidth, pInfo->biHeight, pImageInfo + *(LPDWORD)pImageInfo, (LPBITMAPINFO) pInfo, DIB_RGB_COLORS, SRCCOPY);

	//COLORREF STI[32][32];




	//return Hist;
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






void CPa2sampleDlg::OnStnClickedtransition()
{
	// TODO: Add your control notification handler code here
}


void CPa2sampleDlg::OnStnClickedmessage()
{
	// TODO: Add your control notification handler code here
}

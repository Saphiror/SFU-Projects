// pa2sampleDlg.h : header file
//
#include "resource.h"
#if !defined(AFX_PA2SAMPLEDLG_H__E18D499F_9C02_452A_80EE_D11F33936AB9__INCLUDED_)
#define AFX_PA2SAMPLEDLG_H__E18D499F_9C02_452A_80EE_D11F33936AB9__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "video.h"
/////////////////////////////////////////////////////////////////////////////
// CPa2sampleDlg dialog

class CPa2sampleDlg : public CDialog
{
// Construction
public:
	CPa2sampleDlg(CWnd* pParent = NULL);	// standard constructor
	~CPa2sampleDlg();
// Dialog Data
	//{{AFX_DATA(CPa2sampleDlg)
	enum { IDD = IDD_PA2SAMPLE_DIALOG };
		// NOTE: the ClassWizard will add data members here
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CPa2sampleDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	void showFrame(LPSTR pImageInfo);
	HICON m_hIcon;
	Video *video;

	// Generated message map functions
	//{{AFX_MSG(CPa2sampleDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	afx_msg void OnOpen();
	afx_msg void OnPlay();
	afx_msg void OnStop();
	afx_msg void Ongetframe();
	void GetFrame();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
public:
//	afx_msg void OnBnClickedPlay();
//	afx_msg void OnBnClickedStop();
	afx_msg void OnClickedStop();
	CString m_pathname;
	afx_msg void OnClickedPlay();
	afx_msg void OnUpdateEdit1();
private:
	
public:
	afx_msg void OnStnClickedtransition();
	afx_msg void OnStnClickedmessage();
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_PA2SAMPLEDLG_H__E18D499F_9C02_452A_80EE_D11F33936AB9__INCLUDED_)

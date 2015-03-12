// soundexampleDlg.h : header file
//

#if !defined(AFX_SOUNDEXAMPLEDLG_H__92A99F1B_41EC_4381_8EC9_42B9CCBD94B4__INCLUDED_)
#define AFX_SOUNDEXAMPLEDLG_H__92A99F1B_41EC_4381_8EC9_42B9CCBD94B4__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "sound.h"
/////////////////////////////////////////////////////////////////////////////
// CSoundexampleDlg dialog

class CSoundexampleDlg : public CDialog
{
// Construction
public:
	CSoundexampleDlg(CWnd* pParent = NULL);	// standard constructor
	
	~CSoundexampleDlg();

// Dialog Data
	//{{AFX_DATA(CSoundexampleDlg)
	enum { IDD = IDD_SOUNDEXAMPLE_DIALOG };
		// NOTE: the ClassWizard will add data members here
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CSoundexampleDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	HICON m_hIcon;

	// Generated message map functions
	//{{AFX_MSG(CSoundexampleDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	afx_msg void OnSound();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_SOUNDEXAMPLEDLG_H__92A99F1B_41EC_4381_8EC9_42B9CCBD94B4__INCLUDED_)

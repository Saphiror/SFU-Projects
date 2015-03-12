// soundexample.h : main header file for the SOUNDEXAMPLE application
//

#if !defined(AFX_SOUNDEXAMPLE_H__BE8C15D4_1E2C_49C8_B761_427EDA817E07__INCLUDED_)
#define AFX_SOUNDEXAMPLE_H__BE8C15D4_1E2C_49C8_B761_427EDA817E07__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"		// main symbols

/////////////////////////////////////////////////////////////////////////////
// CSoundexampleApp:
// See soundexample.cpp for the implementation of this class
//

class CSoundexampleApp : public CWinApp
{
public:
	CSoundexampleApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CSoundexampleApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

	//{{AFX_MSG(CSoundexampleApp)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_SOUNDEXAMPLE_H__BE8C15D4_1E2C_49C8_B761_427EDA817E07__INCLUDED_)

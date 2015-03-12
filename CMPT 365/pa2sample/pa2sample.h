// pa2sample.h : main header file for the PA2SAMPLE application
//

#if !defined(AFX_PA2SAMPLE_H__21834985_7C27_4744_9D57_C9D7AB22DF23__INCLUDED_)
#define AFX_PA2SAMPLE_H__21834985_7C27_4744_9D57_C9D7AB22DF23__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"		// main symbols

/////////////////////////////////////////////////////////////////////////////
// CPa2sampleApp:
// See pa2sample.cpp for the implementation of this class
//

class CPa2sampleApp : public CWinApp
{
public:
	CPa2sampleApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CPa2sampleApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

	//{{AFX_MSG(CPa2sampleApp)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_PA2SAMPLE_H__21834985_7C27_4744_9D57_C9D7AB22DF23__INCLUDED_)

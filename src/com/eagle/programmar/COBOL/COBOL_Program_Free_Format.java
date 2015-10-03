// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 21, 2012

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.COBOL_Syntax.COBOL_Free_Format_Syntax;


public class COBOL_Program_Free_Format extends COBOL_Program_Complete
{
	public static final String NAME = "COBOL_Free_Format";

	public COBOL_Program_Free_Format()
	{
		super(NAME, new COBOL_Free_Format_Syntax());
	}
}

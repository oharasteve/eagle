// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.EagleLanguage;

/**
 * COBOL grammar
 */

public abstract class COBOL_Program extends EagleLanguage
{
	public COBOL_Program(String name, COBOL_Syntax syntax)
	{
		super(name, syntax);
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://publib.boulder.ibm.com/infocenter/pdthelp/v1r1/index.jsp?topic=/com.ibm.entcobol.doc_3.4/";
	}
}

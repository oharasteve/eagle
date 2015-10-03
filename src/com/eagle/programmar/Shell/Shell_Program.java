// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2014

package com.eagle.programmar.Shell;

import com.eagle.programmar.EagleLanguage;


public class Shell_Program extends EagleLanguage
{
	public static final String NAME = "Shell";
	
	public Shell_Program()
	{
		super(NAME, new Shell_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}
	
	// Add body
}

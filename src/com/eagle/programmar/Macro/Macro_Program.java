// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2014

package com.eagle.programmar.Macro;

import com.eagle.programmar.EagleLanguage;

public class Macro_Program extends EagleLanguage
{
	public static final String NAME = "Macro";
	
	public Macro_Program()
	{
		super(NAME, new Macro_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}
	
	// Add body
}

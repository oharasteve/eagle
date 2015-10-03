// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.Gupta.Declarations.Gupta_Application;

public class Gupta_Program extends EagleLanguage
{
	public static final String NAME = "Gupta";
	
	public Gupta_Program()
	{
		super(NAME, new Gupta_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "Unknown";
	}

	// Components of a Gupta Program
	public Gupta_Application application;
}

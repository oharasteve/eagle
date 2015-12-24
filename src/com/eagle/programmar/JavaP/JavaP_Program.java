// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.EagleLanguage;
import com.eagle.tokens.TokenList;

public class JavaP_Program extends EagleLanguage
{
	public static final String NAME = "JavaP";
	
	public JavaP_Program()
	{
		super(NAME, new JavaP_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}
	
	public TokenList<JavaP_Statement> statements;
}

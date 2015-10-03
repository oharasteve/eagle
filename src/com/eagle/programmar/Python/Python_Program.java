// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.EagleLanguage;
import com.eagle.tokens.TokenList;

public class Python_Program extends EagleLanguage
{
	public static final String NAME = "Python";
	
	public Python_Program()
	{
		super(NAME, new Python_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://docs.python.org/2/reference/index.html";
	}
	
	public @OPT TokenList<Python_Statement> entries;
}

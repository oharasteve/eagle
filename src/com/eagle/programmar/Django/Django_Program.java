// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 19, 2014

package com.eagle.programmar.Django;

import com.eagle.programmar.EagleLanguage;
import com.eagle.tokens.TokenList;

public class Django_Program extends EagleLanguage
{
	public static final String NAME = "Django";
	
	public Django_Program()
	{
		super(NAME, new Django_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "Unknown";
	}

	// Components of a Django Program
	public TokenList<Django_Element> elements;
}
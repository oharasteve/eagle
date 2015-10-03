// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

package com.eagle.programmar.Natural;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenList;

public class Natural_Program extends EagleLanguage
{
	public static final String NAME = "Natural";
	
	public Natural_Program()
	{
		super(NAME, new Natural_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://documentation.softwareag.com/natural/nat627unx/";
	}

	// Components of a Natural Program
	public TokenList<Natural_Statement> statements;
	public @OPT Natural_Keyword END = new Natural_Keyword("END");
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.Django.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Django_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Django_KeywordChoice()
	{
		super();
	}
	
	public Django_KeywordChoice(String... words)
	{
		super(words);
	}
}

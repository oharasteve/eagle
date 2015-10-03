// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Natural.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Natural_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Natural_KeywordChoice()
	{
		super();
	}
	
	public Natural_KeywordChoice(String... words)
	{
		super(words);
	}
}

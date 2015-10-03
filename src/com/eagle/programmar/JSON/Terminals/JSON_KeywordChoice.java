// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2015

package com.eagle.programmar.JSON.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class JSON_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public JSON_KeywordChoice()
	{
		super();
	}
	
	public JSON_KeywordChoice(String... words)
	{
		super(words);
	}
}

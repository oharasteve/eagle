// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Javascript_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Javascript_KeywordChoice()
	{
		super();
	}
	
	public Javascript_KeywordChoice(String... words)
	{
		super(words);
	}
}

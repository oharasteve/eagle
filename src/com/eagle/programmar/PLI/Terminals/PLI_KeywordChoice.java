// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.PLI.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class PLI_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public PLI_KeywordChoice()
	{
		super();
	}
	
	public PLI_KeywordChoice(String... words)
	{
		super(words);
	}
}

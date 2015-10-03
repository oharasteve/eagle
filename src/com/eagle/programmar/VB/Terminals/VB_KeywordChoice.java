// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.VB.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class VB_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public VB_KeywordChoice()
	{
		super();
	}
	
	public VB_KeywordChoice(String... words)
	{
		super(words);
	}
}

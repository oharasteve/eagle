// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 22, 2015

package com.eagle.programmar.PPSM.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class PPSM_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public PPSM_KeywordChoice()
	{
		super();
	}
	
	public PPSM_KeywordChoice(String... words)
	{
		super(words);
	}
}

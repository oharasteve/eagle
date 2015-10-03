// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class IntelASM_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public IntelASM_KeywordChoice()
	{
		super();
	}
	
	public IntelASM_KeywordChoice(String... words)
	{
		super(words);
	}
}

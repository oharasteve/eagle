// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.IBMASM.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class IBMASM_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public IBMASM_KeywordChoice()
	{
		super();
	}
	
	public IBMASM_KeywordChoice(String... words)
	{
		super(words);
	}
}

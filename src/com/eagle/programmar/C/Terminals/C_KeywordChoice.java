// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.C.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class C_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public C_KeywordChoice()
	{
		super();
	}
	
	public C_KeywordChoice(String... words)
	{
		super(words);
	}
}

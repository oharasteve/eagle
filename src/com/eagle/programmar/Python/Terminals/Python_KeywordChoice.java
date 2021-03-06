// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Python_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Python_KeywordChoice()
	{
		super();
	}
	
	public Python_KeywordChoice(String... words)
	{
		super(words);
	}
}

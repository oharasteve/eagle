// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Gupta.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Gupta_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Gupta_KeywordChoice()
	{
		super();
	}
	
	public Gupta_KeywordChoice(String... words)
	{
		super(words);
	}
}

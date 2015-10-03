// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 6, 2015

package com.eagle.programmar.CMD.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class CMD_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public CMD_KeywordChoice()
	{
		super();
	}
	
	public CMD_KeywordChoice(String... words)
	{
		super(words);
	}

}

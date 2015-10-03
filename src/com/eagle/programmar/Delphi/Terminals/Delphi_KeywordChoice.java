// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Delphi.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Delphi_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Delphi_KeywordChoice()
	{
		super();
	}
	
	public Delphi_KeywordChoice(String... words)
	{
		super(words);
	}
}

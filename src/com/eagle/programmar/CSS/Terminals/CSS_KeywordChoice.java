// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2014

package com.eagle.programmar.CSS.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class CSS_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public CSS_KeywordChoice()
	{
		super();
	}
	
	public CSS_KeywordChoice(String... words)
	{
		super(words);
	}
}

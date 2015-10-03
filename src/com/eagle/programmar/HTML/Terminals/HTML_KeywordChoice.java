// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2014

package com.eagle.programmar.HTML.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class HTML_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public HTML_KeywordChoice()
	{
		super();
	}
	
	public HTML_KeywordChoice(String... words)
	{
		super(words);
	}
}

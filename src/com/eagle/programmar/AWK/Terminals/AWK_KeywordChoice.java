// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class AWK_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public AWK_KeywordChoice()
	{
		super();
	}
	
	public AWK_KeywordChoice(String... words)
	{
		super(words);
	}
}

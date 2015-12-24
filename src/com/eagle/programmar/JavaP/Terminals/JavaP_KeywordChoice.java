// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class JavaP_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public JavaP_KeywordChoice()
	{
		super();
	}
	
	public JavaP_KeywordChoice(String... words)
	{
		super(words);
	}
}

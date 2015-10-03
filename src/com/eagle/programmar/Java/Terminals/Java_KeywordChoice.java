// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Java.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Java_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Java_KeywordChoice()
	{
		super();
	}
	
	public Java_KeywordChoice(String... words)
	{
		super(words);
	}
}

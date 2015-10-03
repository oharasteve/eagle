// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.SQL.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class SQL_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public SQL_KeywordChoice()
	{
		super();
	}
	
	public SQL_KeywordChoice(String... words)
	{
		super(words);
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class SQL_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public SQL_Keyword()
	{
		this("");
	}

	public SQL_Keyword(String word)
	{
		super(word);
	}
}

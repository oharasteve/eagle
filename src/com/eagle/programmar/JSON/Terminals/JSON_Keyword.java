// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class JSON_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public JSON_Keyword()
	{
		this("");
	}

	public JSON_Keyword(String word)
	{
		super(word);
	}
}

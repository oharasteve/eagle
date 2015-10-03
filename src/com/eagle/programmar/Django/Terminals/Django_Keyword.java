// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Django_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Django_Keyword()
	{
		this("");
	}

	public Django_Keyword(String word)
	{
		super(word);
	}
}

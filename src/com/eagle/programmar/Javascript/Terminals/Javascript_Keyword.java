// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Javascript_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Javascript_Keyword()
	{
		this("");
	}

	public Javascript_Keyword(String word)
	{
		super(word);
	}
}

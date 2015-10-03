// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class HTML_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public HTML_Keyword()
	{
		this("");
	}

	public HTML_Keyword(String word)
	{
		super(word);
	}
}

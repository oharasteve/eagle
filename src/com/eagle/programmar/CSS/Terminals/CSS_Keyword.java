// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2011

package com.eagle.programmar.CSS.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class CSS_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public CSS_Keyword()
	{
		this("");
	}

	public CSS_Keyword(String word)
	{
		super(word);
	}
}

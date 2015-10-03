// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class PLI_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public PLI_Keyword()
	{
		this("");
	}

	public PLI_Keyword(String word)
	{
		super(word);
	}
}

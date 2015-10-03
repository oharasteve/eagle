// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Gupta_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Gupta_Keyword()
	{
		this("");
	}

	public Gupta_Keyword(String word)
	{
		super(word);
	}
}

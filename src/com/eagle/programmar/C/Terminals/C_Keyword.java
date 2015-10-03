// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class C_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public C_Keyword()
	{
		this(null);
	}

	public C_Keyword(String word)
	{
		super(word);
	}
}

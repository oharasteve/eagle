// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Python_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Python_Keyword()
	{
		this("");
	}

	public Python_Keyword(String word)
	{
		super(word);
	}
}

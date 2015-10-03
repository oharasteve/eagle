// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class CMD_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public CMD_Keyword()
	{
		this("");
	}

	public CMD_Keyword(String word)
	{
		super(word);
	}
}

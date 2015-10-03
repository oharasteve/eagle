// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class VB_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public VB_Keyword()
	{
		this("");
	}

	public VB_Keyword(String word)
	{
		super(word);
	}
}

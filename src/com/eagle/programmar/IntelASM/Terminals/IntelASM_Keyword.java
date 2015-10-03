// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class IntelASM_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public IntelASM_Keyword()
	{
		this("");
	}

	public IntelASM_Keyword(String word)
	{
		super(word);
	}
}

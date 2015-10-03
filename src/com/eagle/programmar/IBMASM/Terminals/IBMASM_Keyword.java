// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

package com.eagle.programmar.IBMASM.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class IBMASM_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public IBMASM_Keyword()
	{
		this("");
	}

	public IBMASM_Keyword(String word)
	{
		super(word);
	}
}

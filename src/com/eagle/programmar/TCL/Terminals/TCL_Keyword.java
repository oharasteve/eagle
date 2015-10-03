// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class TCL_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public TCL_Keyword()
	{
		this("");
	}

	public TCL_Keyword(String word)
	{
		super(word);
	}
}

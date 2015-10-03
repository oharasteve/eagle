// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class COBOL_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public COBOL_Keyword()
	{
		this("");
	}

	public COBOL_Keyword(String word)
	{
		super(word);
	}
}

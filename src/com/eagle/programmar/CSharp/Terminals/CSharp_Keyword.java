// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class CSharp_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public CSharp_Keyword()
	{
		this("");
	}

	public CSharp_Keyword(String word)
	{
		super(word);
	}
}

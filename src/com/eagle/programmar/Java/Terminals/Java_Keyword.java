// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.Java.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Java_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Java_Keyword()
	{
		this("");
	}

	public Java_Keyword(String word)
	{
		super(word);
	}
}

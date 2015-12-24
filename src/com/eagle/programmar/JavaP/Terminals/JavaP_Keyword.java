// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class JavaP_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public JavaP_Keyword()
	{
		this("");
	}

	public JavaP_Keyword(String word)
	{
		super(word);
	}
}

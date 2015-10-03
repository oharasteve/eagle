// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class AWK_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public AWK_Keyword()
	{
		this(null);
	}

	public AWK_Keyword(String word)
	{
		super(word);
	}
}

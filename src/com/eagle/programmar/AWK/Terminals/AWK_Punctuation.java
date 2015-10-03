// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class AWK_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public AWK_Punctuation()
	{
		this('\0');
	}

	public AWK_Punctuation(char punct)
	{
		super(punct);
	}

	public AWK_Punctuation(String punct)
	{
		super(punct);
	}
}

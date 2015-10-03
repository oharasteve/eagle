// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Natural_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Natural_Punctuation()
	{
		this('\0');
	}

	public Natural_Punctuation(char punct)
	{
		super(punct);
	}

	public Natural_Punctuation(String punct)
	{
		super(punct);
	}
}
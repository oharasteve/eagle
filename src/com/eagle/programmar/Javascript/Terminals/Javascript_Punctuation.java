// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Javascript_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Javascript_Punctuation()
	{
		this('\0');
	}

	public Javascript_Punctuation(char punct)
	{
		super(punct);
	}

	public Javascript_Punctuation(String punct)
	{
		super(punct);
	}
}

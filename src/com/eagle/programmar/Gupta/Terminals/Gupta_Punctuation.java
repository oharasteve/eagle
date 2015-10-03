// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Gupta_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Gupta_Punctuation()
	{
		this('\0');
	}

	public Gupta_Punctuation(char punct)
	{
		super(punct);
	}

	public Gupta_Punctuation(String punct)
	{
		super(punct);
	}
}

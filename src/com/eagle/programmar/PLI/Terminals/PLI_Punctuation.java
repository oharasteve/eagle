// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class PLI_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public PLI_Punctuation()
	{
		this('\0');
	}

	public PLI_Punctuation(char punct)
	{
		super(punct);
	}

	public PLI_Punctuation(String punct)
	{
		super(punct);
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class CMD_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public CMD_Punctuation()
	{
		this('\0');
	}

	public CMD_Punctuation(char punct)
	{
		super(punct);
	}

	public CMD_Punctuation(String punct)
	{
		super(punct);
	}
}

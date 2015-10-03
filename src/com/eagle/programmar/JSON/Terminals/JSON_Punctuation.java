// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class JSON_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public JSON_Punctuation()
	{
		this('\0');
	}

	public JSON_Punctuation(char punct)
	{
		super(punct);
	}
}
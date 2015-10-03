// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Django_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Django_Punctuation()
	{
		this('\0');
	}

	public Django_Punctuation(char punct)
	{
		super(punct);
	}
}
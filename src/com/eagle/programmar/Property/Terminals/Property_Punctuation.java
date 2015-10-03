// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 29, 2014

package com.eagle.programmar.Property.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Property_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Property_Punctuation()
	{
		this('\0');
	}

	public Property_Punctuation(char punct)
	{
		super(punct);
	}
}
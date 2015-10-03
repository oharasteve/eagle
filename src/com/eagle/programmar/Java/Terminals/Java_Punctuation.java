// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.Java.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Java_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Java_Punctuation()
	{
		this('\0');
	}

	public Java_Punctuation(char punct)
	{
		super(punct);
	}

	public Java_Punctuation(String punct)
	{
		super(punct);
	}
}

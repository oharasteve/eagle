// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class JavaP_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public JavaP_Punctuation()
	{
		this('\0');
	}

	public JavaP_Punctuation(char punct)
	{
		super(punct);
	}

	public JavaP_Punctuation(String punct)
	{
		super(punct);
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Python_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Python_Punctuation()
	{
		this('\0');
	}

	public Python_Punctuation(char punct)
	{
		super(punct);
	}
	
	public Python_Punctuation(String punct)
	{
		super(punct);
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class C_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public C_Punctuation()
	{
		this('\0');
	}

	public C_Punctuation(char punct)
	{
		super(punct);
	}
	
	public C_Punctuation(String punct)
	{
		super(punct);
	}
}

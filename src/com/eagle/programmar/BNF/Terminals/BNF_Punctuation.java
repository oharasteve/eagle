// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class BNF_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public BNF_Punctuation()
	{
		this('\0');
	}

	public BNF_Punctuation(char punct)
	{
		super(punct);
	}

	public BNF_Punctuation(String punct)
	{
		super(punct);
	}
}

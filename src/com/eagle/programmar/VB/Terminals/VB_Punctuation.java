// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class VB_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public VB_Punctuation()
	{
		this('\0');
	}

	public VB_Punctuation(char punct)
	{
		super(punct);
	}

	public VB_Punctuation(String punct)
	{
		super(punct);
	}
}

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class IntelASM_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public IntelASM_Punctuation()
	{
		this('\0');
	}

	public IntelASM_Punctuation(char punct)
	{
		super(punct);
	}
}

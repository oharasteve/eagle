// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

package com.eagle.programmar.IBMASM.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class IBMASM_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public IBMASM_Punctuation()
	{
		this('\0');
	}

	public IBMASM_Punctuation(char punct)
	{
		super(punct);
	}
}

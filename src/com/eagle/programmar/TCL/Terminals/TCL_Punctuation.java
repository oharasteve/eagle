// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class TCL_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public TCL_Punctuation()
	{
		this('\0');
	}

	public TCL_Punctuation(char punct)
	{
		super(punct);
	}
	
	public TCL_Punctuation(String punct)
	{
		super(punct);
	}
}
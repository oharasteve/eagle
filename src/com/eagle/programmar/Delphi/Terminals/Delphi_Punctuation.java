// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Delphi_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Delphi_Punctuation()
	{
		this('\0');
	}

	public Delphi_Punctuation(char punct)
	{
		super(punct);
	}

	public Delphi_Punctuation(String punct)
	{
		super(punct);
	}
}

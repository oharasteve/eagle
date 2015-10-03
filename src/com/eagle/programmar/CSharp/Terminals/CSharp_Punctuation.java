// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class CSharp_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public CSharp_Punctuation()
	{
		this('\0');
	}

	public CSharp_Punctuation(char punct)
	{
		super(punct);
	}

	public CSharp_Punctuation(String punct)
	{
		super(punct);
	}
}

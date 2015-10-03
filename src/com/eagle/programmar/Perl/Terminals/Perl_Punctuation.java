// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Perl_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Perl_Punctuation()
	{
		this('\0');
	}

	public Perl_Punctuation(char punct)
	{
		super(punct);
	}

	public Perl_Punctuation(String punct)
	{
		super(punct);
	}
}

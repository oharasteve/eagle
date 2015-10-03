// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Perl_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Perl_Keyword()
	{
		this("");
	}

	public Perl_Keyword(String word)
	{
		super(word);
	}
}

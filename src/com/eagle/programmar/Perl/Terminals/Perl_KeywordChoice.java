// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Perl.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Perl_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Perl_KeywordChoice()
	{
		super();
	}

	public Perl_KeywordChoice(String... words)
	{
		super(words);
	}
}
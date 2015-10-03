// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.Perl.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class Perl_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Perl_PunctuationChoice()
	{
		super();
	}
	
	public Perl_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}
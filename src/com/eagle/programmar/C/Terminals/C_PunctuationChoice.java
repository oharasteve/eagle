// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.C.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class C_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public C_PunctuationChoice()
	{
		super();
	}
	
	public C_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}
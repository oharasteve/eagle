// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class BNF_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public BNF_PunctuationChoice()
	{
		super();
	}
	
	public BNF_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}
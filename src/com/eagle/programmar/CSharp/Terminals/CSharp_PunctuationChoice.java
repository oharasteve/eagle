// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class CSharp_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public CSharp_PunctuationChoice()
	{
		super();
	}
	
	public CSharp_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}

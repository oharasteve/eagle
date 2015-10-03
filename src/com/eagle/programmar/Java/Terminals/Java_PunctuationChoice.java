// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.Java.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class Java_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Java_PunctuationChoice()
	{
		super();
	}
	
	public Java_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}
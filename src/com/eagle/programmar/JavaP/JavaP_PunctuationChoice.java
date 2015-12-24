// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 5, 2015

package com.eagle.programmar.JavaP;

import com.eagle.tokens.TerminalPunctuationChoice;

public class JavaP_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public JavaP_PunctuationChoice()
	{
		super();
	}
	
	public JavaP_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class COBOL_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public COBOL_KeywordChoice()
	{
		super();
	}
	
	public COBOL_KeywordChoice(String... words)
	{
		super(words);
	}

}

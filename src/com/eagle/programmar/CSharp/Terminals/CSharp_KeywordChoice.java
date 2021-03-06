// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class CSharp_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public CSharp_KeywordChoice()
	{
		super();
	}
	
	public CSharp_KeywordChoice(String... words)
	{
		super(words);
	}
}

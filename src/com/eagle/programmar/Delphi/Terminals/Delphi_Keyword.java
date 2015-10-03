// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Delphi_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Delphi_Keyword()
	{
		this("");
	}

	public Delphi_Keyword(String word)
	{
		super(word);
	}
}

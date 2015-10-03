// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

package com.eagle.programmar.Lisp.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Lisp_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Lisp_Keyword()
	{
		this("");
	}

	public Lisp_Keyword(String word)
	{
		super(word);
	}
}

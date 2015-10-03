// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

package com.eagle.programmar.Lisp.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Lisp_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Lisp_Punctuation()
	{
		this('\0');
	}

	public Lisp_Punctuation(char punct)
	{
		super(punct);
	}
}
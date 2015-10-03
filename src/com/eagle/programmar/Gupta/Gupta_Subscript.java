// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Terminals.Gupta_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Gupta_Subscript extends TokenSequence
{
	public Gupta_Punctuation leftParen = new Gupta_Punctuation('(');
	public Gupta_Expression expr;
	public Gupta_Punctuation righttParen = new Gupta_Punctuation(')');
}

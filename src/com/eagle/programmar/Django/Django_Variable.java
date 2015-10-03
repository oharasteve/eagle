// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django;

import com.eagle.programmar.Django.Symbols.Django_Identifier_Reference;
import com.eagle.programmar.Django.Terminals.Django_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Django_Variable extends TokenSequence
{
	public Django_Identifier_Reference variable;
	public @OPT Django_DotVariable more;
	
	public static class Django_DotVariable extends TokenSequence
	{
		public Django_Punctuation dot = new Django_Punctuation('.');
		public Django_Identifier_Reference variable;
	}
}

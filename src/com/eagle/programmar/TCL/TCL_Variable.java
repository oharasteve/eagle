// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Symbols.TCL_Identifier_Reference;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.tokens.TokenSequence;

public class TCL_Variable extends TokenSequence
{
	public TCL_Identifier_Reference id;
	public @OPT TCL_Subscript subscript;
	
	public static class TCL_Subscript extends TokenSequence
	{
		public TCL_Punctuation leftParen = new TCL_Punctuation('(');
		public TCL_Expression expr;
		public TCL_Punctuation rightParen = new TCL_Punctuation(')');
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Symbols.TCL_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class TCL_Variable extends TokenSequence
{
	public TCL_Identifier_Reference id;
	public @OPT TCL_Subscript subscript;
	
	public static class TCL_Subscript extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public TCL_Expression expr;
		public PunctuationRightParen rightParen;
	}
}

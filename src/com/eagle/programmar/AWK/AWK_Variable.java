// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class AWK_Variable extends TokenSequence
{
	public AWK_Identifier_Reference id;
	public @OPT AWK_VarSubscript subscript;
	
	public static class AWK_VarSubscript extends TokenSequence
	{
		public PunctuationLeftBracket leftBracket;
		public AWK_Expression expr;
		public PunctuationRightBracket rightBracket;
	}
}

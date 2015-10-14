// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp;

import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class CSharp_Subscript extends TokenSequence
{
	public @NOSPACE PunctuationLeftBracket leftBracket;
	public @NOSPACE CSharp_Expression expr;
	public @OPT @NOSPACE TokenList<CSharp_MoreSubscripts> more;
	public @NOSPACE PunctuationRightBracket rightBracket;
	
	public static class CSharp_MoreSubscripts extends TokenSequence
	{
		public PunctuationComma comma;
		public CSharp_Expression expr;
	}
}

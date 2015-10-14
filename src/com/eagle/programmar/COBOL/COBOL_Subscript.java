// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_Subscript extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public COBOL_Expression expr;
	public @OPT COBOL_SubscriptRange range;
	public PunctuationRightParen rightParen;
	
	public static class COBOL_SubscriptRange extends TokenSequence
	{
		public COBOL_PunctuationChoice colon = new COBOL_PunctuationChoice(":", ",");
		public COBOL_Expression expr;
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tokens.TokenSequence;

public class COBOL_Subscript extends TokenSequence
{
	public COBOL_Punctuation leftParen = new COBOL_Punctuation('(');
	public COBOL_Expression expr;
	public @OPT COBOL_SubscriptRange range;
	public COBOL_Punctuation righttParen = new COBOL_Punctuation(')');
	
	public static class COBOL_SubscriptRange extends TokenSequence
	{
		public COBOL_PunctuationChoice colon = new COBOL_PunctuationChoice(":", ",");
		public COBOL_Expression expr;
	}
}

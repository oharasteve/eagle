// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 3, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class PLI_Signals extends TokenSequence
{
	public static String signals[] = new String[] {
		"CONVERSION",
		"ERROR",
		"FIXEDOVERFLOW",
		"NOFIXEDOVERFLOW",
		"NOFOFL",
		"NOSIZE",
		"NOSTRINGSIZE",
		"OVERFLOW",
		"SIZE",
		"STRINGRANGE",
		"STRINGSIZE",
		"SUBSCRIPTRANGE",
		"UNDERFLOW",
		"ZERODIVIDE"
		};

	public PunctuationLeftParen leftParen;
	public PLI_KeywordChoice which = new PLI_KeywordChoice(signals);
	public @OPT TokenList<PLI_Procedure_MoreSignals> moreSignals;
	public PunctuationRightParen rightParen;
	public PunctuationColon colon;
	
	public static class PLI_Procedure_MoreSignals extends TokenSequence
	{
		public PunctuationComma comma;
		public PLI_KeywordChoice which = new PLI_KeywordChoice(signals);
	}
}

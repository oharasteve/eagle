// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 3, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

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

	public PLI_Punctuation leftParen = new PLI_Punctuation('(');
	public PLI_KeywordChoice which = new PLI_KeywordChoice(signals);
	public @OPT TokenList<PLI_Procedure_MoreSignals> moreSignals;
	public PLI_Punctuation rightParen = new PLI_Punctuation(')');
	public PLI_Punctuation colon = new PLI_Punctuation(':');
	
	public static class PLI_Procedure_MoreSignals extends TokenSequence
	{
		public PLI_Punctuation comma = new PLI_Punctuation(',');
		public PLI_KeywordChoice which = new PLI_KeywordChoice(signals);
	}
}

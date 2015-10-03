// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 18, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Signal;
import com.eagle.programmar.PLI.PLI_Signals;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenSequence;

public class PLI_SignalStatement extends TokenSequence
{
	public @OPT PLI_Signal_Label label;
	public @DOC("7.52") PLI_Keyword SIGNAL = new PLI_Keyword("SIGNAL");
	public PLI_Signal signal;
	public PLI_Punctuation semiColon = new PLI_Punctuation(';');

	public static class PLI_Signal_Label extends TokenSequence
	{
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public PLI_KeywordChoice which = new PLI_KeywordChoice(PLI_Signals.signals);
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		public PLI_Punctuation colon = new PLI_Punctuation(':');
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 18, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Signal;
import com.eagle.programmar.PLI.PLI_Signals;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_SignalStatement extends TokenSequence
{
	public @OPT PLI_Signal_Label label;
	public @DOC("7.52") PLI_Keyword SIGNAL = new PLI_Keyword("SIGNAL");
	public PLI_Signal signal;
	public PunctuationSemicolon semicolon;

	public static class PLI_Signal_Label extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public PLI_KeywordChoice which = new PLI_KeywordChoice(PLI_Signals.signals);
		public PunctuationRightParen rightParen;
		public PunctuationColon colon;
	}
}

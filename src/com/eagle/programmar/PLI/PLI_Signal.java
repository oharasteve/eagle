// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2012

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class PLI_Signal extends TokenChooser
{
	public PLI_KeywordChoice which = new PLI_KeywordChoice(PLI_Signals.signals);

	public static class PLI_OnEndFile extends TokenSequence
	{
		public PLI_Keyword ENDFILE = new PLI_Keyword("ENDFILE");
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public PLI_Identifier_Reference file;
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');
	}
}

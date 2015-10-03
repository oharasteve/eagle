// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Signal;
import com.eagle.programmar.PLI.PLI_Statement;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_OnStatement extends TokenSequence
{
	public @OPT PLI_Label label;
	public @DOC("7.36") PLI_Keyword ON = new PLI_Keyword("ON");
	public PLI_Signal signal;
	public @OPT TokenList<PLI_OnMoreSignals> moreSignals;
	public @OPT PLI_Keyword SNAP = new PLI_Keyword("SNAP");
	public PLI_OnAction action;
		
	public static class PLI_OnMoreSignals extends TokenSequence
	{
		public PLI_Punctuation comma = new PLI_Punctuation(',');
		public PLI_Signal signal;
	}
	
	public static class PLI_OnAction extends TokenChooser
	{
		public PLI_Statement stmt;

		public static class PLI_On_Action_System extends TokenSequence
		{
			public PLI_Keyword SYSTEM = new PLI_Keyword("SYSTEM");
			public PLI_Punctuation semicolon = new PLI_Punctuation(';');
		}
	}
}

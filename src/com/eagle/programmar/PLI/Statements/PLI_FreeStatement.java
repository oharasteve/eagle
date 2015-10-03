// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_FreeStatement extends TokenSequence
{
	public @DOC("7.23") PLI_Keyword FREE = new PLI_Keyword("FREE");
	public PLI_Identifier_Reference id;
	public @OPT TokenList<PLI_Free_MoreVars> moreIds;
	public PLI_Punctuation semiColon = new PLI_Punctuation(';');
	
	public static class PLI_Free_MoreVars extends TokenSequence
	{
		public PLI_Punctuation comma = new PLI_Punctuation(',');
		public PLI_Identifier_Reference id;
	}
}

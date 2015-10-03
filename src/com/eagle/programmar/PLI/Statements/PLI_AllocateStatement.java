// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_AllocateStatement extends TokenSequence
{
	public @DOC("7.2") PLI_Keyword ALLOCATE = new PLI_Keyword("ALLOCATE");
	public PLI_AllocateVariable var;
	public @OPT TokenList<PLI_Allocate_MoreVars> moreIds;
	public PLI_Punctuation semiColon = new PLI_Punctuation(';');
	
	public static class PLI_AllocateVariable extends TokenSequence
	{
		public PLI_Identifier_Reference id;
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public PLI_AllocateSize size;
		public @OPT TokenList<PLI_Allocate_MoreDimensions> more;
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		
		public static class PLI_Allocate_MoreDimensions extends TokenSequence
		{
			public PLI_Punctuation comma = new PLI_Punctuation(',');
			public PLI_AllocateSize size;
		}
	}

	public static class PLI_AllocateSize extends TokenSequence
	{
		public PLI_Expression size1;
		public @OPT PLI_Punctuation colon = new PLI_Punctuation(':');
		public @OPT PLI_Expression size2;
	}
	
	public static class PLI_Allocate_MoreVars extends TokenSequence
	{
		public PLI_Punctuation comma = new PLI_Punctuation(',');
		public PLI_AllocateVariable var;
	}
}

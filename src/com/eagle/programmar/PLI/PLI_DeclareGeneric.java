// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_DeclareGeneric extends TokenSequence
{
	public PLI_Keyword DECLARE = new PLI_Keyword("DECLARE");
	public PLI_Variable_Definition id;
	public PLI_Keyword GENERIC = new PLI_Keyword("GENERIC");
	public PLI_Punctuation leftParen = new PLI_Punctuation('(');
	public PLI_GenericWhen when;
	public @OPT TokenList<PLI_GenericMoreWhens> moreWhens;
	public PLI_Punctuation rightParen = new PLI_Punctuation(')');
	public PLI_Punctuation semicolon = new PLI_Punctuation(';');
	
	public static class PLI_GenericWhen extends TokenSequence
	{
		public PLI_Identifier_Reference id;
		public PLI_Keyword WHEN = new PLI_Keyword("WHEN");
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public @OPT PLI_Type type;
		public @OPT TokenList<PLI_MoreTypes> moreTypes;
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		
		public static class PLI_MoreTypes extends TokenSequence
		{
			public PLI_Punctuation comma = new PLI_Punctuation(',');
			public PLI_Type type;
		}
	}
	
	public static class PLI_GenericMoreWhens extends TokenSequence
	{
		public PLI_Punctuation comma = new PLI_Punctuation(',');
		public PLI_GenericWhen when;
	}
}

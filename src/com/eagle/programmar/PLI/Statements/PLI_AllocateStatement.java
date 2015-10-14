// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_AllocateStatement extends TokenSequence
{
	public @DOC("7.2") PLI_Keyword ALLOCATE = new PLI_Keyword("ALLOCATE");
	public PLI_AllocateVariable var;
	public @OPT TokenList<PLI_Allocate_MoreVars> moreIds;
	public PunctuationSemicolon semicolon;
	
	public static class PLI_AllocateVariable extends TokenSequence
	{
		public PLI_Identifier_Reference id;
		public PunctuationLeftParen leftParen;
		public SeparatedList<PLI_AllocateSize,PunctuationComma> sizes;
		public PunctuationRightParen rightParen;
	}

	public static class PLI_AllocateSize extends TokenSequence
	{
		public PLI_Expression size1;
		public @OPT PunctuationColon colon;
		public @OPT PLI_Expression size2;
	}
	
	public static class PLI_Allocate_MoreVars extends TokenSequence
	{
		public PunctuationComma comma;
		public PLI_AllocateVariable var;
	}
}

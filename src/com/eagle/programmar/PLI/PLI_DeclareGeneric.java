// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_DeclareGeneric extends TokenSequence
{
	public PLI_Keyword DECLARE = new PLI_Keyword("DECLARE");
	public PLI_Variable_Definition id;
	public PLI_Keyword GENERIC = new PLI_Keyword("GENERIC");
	public PunctuationLeftParen leftParen;
	public PLI_GenericWhen when;
	public @OPT TokenList<PLI_GenericMoreWhens> moreWhens;
	public PunctuationRightParen rightParen;
	public PunctuationSemicolon semicolon;
	
	public static class PLI_GenericWhen extends TokenSequence
	{
		public PLI_Identifier_Reference id;
		public PLI_Keyword WHEN = new PLI_Keyword("WHEN");
		public PunctuationLeftParen leftParen;
		public @OPT SeparatedList<PLI_Type,PunctuationComma> types;
		public PunctuationRightParen rightParen;
	}
	
	public static class PLI_GenericMoreWhens extends TokenSequence
	{
		public PunctuationComma comma;
		public PLI_GenericWhen when;
	}
}

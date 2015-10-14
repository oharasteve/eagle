// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 3, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_GetStatement extends TokenSequence
{
	public @DOC("7.24") PLI_Keyword GET = new PLI_Keyword("GET");
	public @OPT PLI_Keyword SKIP = new PLI_Keyword("SKIP");
	public @OPT PLI_GetFile getFile;
	public @OPT PLI_GetEdit getEdit;
	public PunctuationSemicolon semicolon;
	
	public static class PLI_GetFile extends TokenSequence
	{
		public PLI_Keyword FILE = new PLI_Keyword("FILE");
		public PunctuationLeftParen leftParen1;
		public PLI_Identifier_Reference fileName;
		public PunctuationRightParen rightParen1;
	}
	
	public static class PLI_GetEdit extends TokenSequence
	{
		public PLI_Keyword EDIT = new PLI_Keyword("EDIT");
		public PunctuationLeftParen leftParen2;
		public SeparatedList<PLI_Expression,PunctuationComma> exprs;
		public PunctuationRightParen rightParen2;
		public PLI_Punctuation leftParen3 = new PLI_Punctuation('(');
		public SeparatedList<PLI_GetFormat,PunctuationComma> formats;
		public PLI_Punctuation rightParen3 = new PLI_Punctuation(')');
		
		public static class PLI_Get_Subscript extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public PLI_Expression expr;
			public PunctuationRightParen rightParen;
		}
	
		public static class PLI_GetFormat extends TokenSequence
		{
			public @OPT PLI_GetFormat_Count formatCount;
			public PLI_KeywordChoice formatCode = new PLI_KeywordChoice("A", "F", "L", "X");
			public @OPT PLI_GetFormat_Count formatSize;
		}
		
		public static class PLI_GetFormat_Count extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public PLI_Expression count;
			public PunctuationRightParen rightParen;
		}
	}
}

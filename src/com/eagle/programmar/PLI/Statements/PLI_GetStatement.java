// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 3, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_GetStatement extends TokenSequence
{
	public @DOC("7.24") PLI_Keyword GET = new PLI_Keyword("GET");
	public @OPT PLI_Keyword SKIP = new PLI_Keyword("SKIP");
	public @OPT PLI_GetFile getFile;
	public @OPT PLI_GetEdit getEdit;
	public PLI_Punctuation semicolon = new PLI_Punctuation(';');
	
	public static class PLI_GetFile extends TokenSequence
	{
		public PLI_Keyword FILE = new PLI_Keyword("FILE");
		public PLI_Punctuation leftParen1 = new PLI_Punctuation('(');
		public PLI_Identifier_Reference fileName;
		public PLI_Punctuation rightParen1 = new PLI_Punctuation(')');
	}
	
	public static class PLI_GetEdit extends TokenSequence
	{
		public PLI_Keyword EDIT = new PLI_Keyword("EDIT");
		public PLI_Punctuation leftParen2 = new PLI_Punctuation('(');
		public PLI_Expression expr;
		public @OPT TokenList<PLI_GetMore> putMore;
		public PLI_Punctuation rightParen2 = new PLI_Punctuation(')');
		public PLI_Punctuation leftParen3 = new PLI_Punctuation('(');
		public PLI_GetFormat format;
		public @OPT TokenList<PLI_GetMoreFormats> moreFormats;
		public PLI_Punctuation rightParen3 = new PLI_Punctuation(')');
		
		public static class PLI_Get_Subscript extends TokenSequence
		{
			public PLI_Punctuation leftParen = new PLI_Punctuation('(');
			public PLI_Expression expr;
			public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		}
		
		public static class PLI_GetMore extends TokenSequence
		{
			public PLI_Punctuation comma = new PLI_Punctuation(',');
			public PLI_Expression expr;
		}
	
		public static class PLI_GetFormat extends TokenSequence
		{
			public @OPT PLI_GetFormat_Count formatCount;
			public PLI_KeywordChoice formatCode = new PLI_KeywordChoice("A", "F", "L", "X");
			public @OPT PLI_GetFormat_Count formatSize;
		}
		
		public static class PLI_GetMoreFormats extends TokenSequence
		{
			public PLI_Punctuation comma = new PLI_Punctuation(',');
			public PLI_GetFormat fmt;
		}
		
		public static class PLI_GetFormat_Count extends TokenSequence
		{
			public PLI_Punctuation leftParen = new PLI_Punctuation('(');
			public PLI_Expression count;
			public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		}
	}
}

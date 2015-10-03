// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Number;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_PutStatement extends TokenSequence
{
	public @OPT PLI_Label label;
	public @DOC("7.45") PLI_Keyword PUT = new PLI_Keyword("PUT");
	public @OPT PLI_PutFile file;
	public @OPT PLI_Keyword SKIP = new PLI_Keyword("SKIP");
	public @OPT PLI_PutFormat_Count count;
	public @OPT PLI_PutString string;
	public @OPT PLI_KeywordChoice dataOrEditOrList = new PLI_KeywordChoice(
			"DATA", "EDIT", "LIST");
	
	public @OPT PLI_PutValues values;

	public @OPT PLI_PutFormat putFormat;
	
	public PLI_Punctuation semiColon = new PLI_Punctuation(';');

	public static class PLI_PutFile extends TokenSequence
	{
		public PLI_Keyword FILE = new PLI_Keyword("FILE");
		public PLI_Punctuation leftParen1 = new PLI_Punctuation('(');
		public PLI_Identifier_Reference file;
		public PLI_Punctuation rightParen1 = new PLI_Punctuation(')');
	}
	
	public static class PLI_PutString extends TokenSequence
	{
		public PLI_Keyword STRING = new PLI_Keyword("STRING");
		public PLI_Punctuation leftParen1 = new PLI_Punctuation('(');
		public PLI_Identifier_Reference var;
		public PLI_Punctuation rightParen1 = new PLI_Punctuation(')');
	}
	
	public static class PLI_PutValues extends TokenSequence
	{
		public PLI_Punctuation leftParen1 = new PLI_Punctuation('(');
		public PLI_Expression expr;
		public @OPT TokenList<PLI_PutMore> putMore;
		public PLI_Punctuation rightParen1 = new PLI_Punctuation(')');
	}

	public static class PLI_PutMore extends TokenSequence
	{
		public PLI_Punctuation comma = new PLI_Punctuation(',');
		public PLI_Expression expr;
	}
	
	public static class PLI_PutFormat extends TokenSequence
	{
		public PLI_Punctuation leftParen2 = new PLI_Punctuation('(');
		public PLI_PutEditFormat editFormat;
		public @OPT TokenList<PLI_PutMoreFormats> moreFmts;
		public PLI_Punctuation rightParen2 = new PLI_Punctuation(')');
		
		public static class PLI_PutEditFormat extends TokenChooser
		{
			public PLI_Keyword SKIP = new PLI_Keyword("SKIP");
			public PLI_Literal literal;
			
			public static class PLI_PutMultipleFormats extends TokenSequence
			{
				public PLI_Number number;
				public PLI_PutFormat format;
			}
			
			public static class PLI_PutFormat_A extends TokenSequence
			{
				public @OPT PLI_Number number;
				public PLI_Keyword A = new PLI_Keyword("A");
				public @OPT PLI_PutFormat_Count formatCount;
			}

			public static class PLI_PutFormat_E extends TokenSequence
			{
				public @OPT PLI_Number number;
				public PLI_Keyword E = new PLI_Keyword("E");
				public PLI_PutFormat_Count formatCount;
			}

			public static class PLI_PutFormat_F extends TokenSequence
			{
				public @OPT PLI_Number number;
				public PLI_Keyword F = new PLI_Keyword("F");
				public PLI_PutFormat_Count formatCount;
			}

			public static class PLI_PutFormat_R extends TokenSequence
			{
				public PLI_Keyword R = new PLI_Keyword("R");
				public PLI_Punctuation leftParen = new PLI_Punctuation('(');
				public PLI_Identifier_Reference label;
				public PLI_Punctuation rightParen = new PLI_Punctuation(')');
			}

			public static class PLI_PutFormat_X extends TokenSequence
			{
				public @OPT PLI_Number number;
				public PLI_Keyword X = new PLI_Keyword("X");
				public PLI_PutFormat_Count formatCount;
			}
		}
		
		public static class PLI_PutMoreFormats extends TokenSequence
		{
			public PLI_Punctuation comma = new PLI_Punctuation(',');
			public PLI_PutEditFormat editFormat;
		}
	}
	
	public static class PLI_PutFormat_Count extends TokenSequence
	{
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public PLI_Expression expr;
		public @OPT PLI_PutFormat_SecondCount secondCount;
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		
		public static class PLI_PutFormat_SecondCount extends TokenSequence
		{
			public PLI_Punctuation comma = new PLI_Punctuation(',');
			public PLI_Expression expr;
		}
	}
}

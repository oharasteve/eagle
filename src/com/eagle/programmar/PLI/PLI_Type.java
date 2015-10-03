// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Terminals.PLI_BitLiteral;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_Type extends TokenChooser
{
	public PLI_Punctuation star = new PLI_Punctuation('*');
	
	public static class PLI_BaseType extends TokenChooser
	{
		public PLI_KeywordChoice base = new PLI_KeywordChoice("COMPLEX", "FILE", "VARYING", "UNION");

		public static class PLI_TypeCharacter extends TokenSequence
		{
			public @OPT PLI_TypeSize size1;
			public PLI_KeywordChoice CHARACTER = new PLI_KeywordChoice("CHAR", "CHARACTER", "WIDECHAR");
			public @OPT PLI_TypeSize size2;
			public @OPT PLI_KeywordChoice varyingOrStatic = new PLI_KeywordChoice(
					"STATIC", "VARYING");
			public @OPT PLI_CharInitial initialValue;
			
			public static class PLI_CharInitial extends TokenSequence
			{
				public PLI_Keyword INITIAL = new PLI_Keyword("INITIAL");
				public PLI_Punctuation leftParen = new PLI_Punctuation('(');
				public PLI_Literal initialValue;
				public PLI_Punctuation rightParen = new PLI_Punctuation(')');
			}
		}

		public static class PLI_TypeFixedBinary extends TokenSequence
		{
			public PLI_KeywordChoice FIXED = new PLI_KeywordChoice("FIXED", "FLOAT");
			public PLI_Keyword BINARY = new PLI_Keyword("BINARY");
			public @OPT PLI_TypeSize size;
			public @OPT PLI_Keyword COMPLEX = new PLI_Keyword("COMPLEX");
		}

		public static class PLI_TypeFloat extends TokenSequence
		{
			public PLI_KeywordChoice FIXED = new PLI_KeywordChoice("FIXED", "FLOAT");
			public @OPT PLI_Keyword DECIMAL = new PLI_Keyword("DECIMAL");
			public @OPT PLI_TypeSize size;
			public @OPT PLI_Keyword COMPLEX = new PLI_Keyword("COMPLEX");
		}
		
		public static class PLI_TypeBit extends TokenSequence
		{
			public @OPT PLI_TypeSize size1;
			public PLI_Keyword BIT = new PLI_Keyword("BIT");
			public @OPT PLI_TypeSize size2;
			public @OPT PLI_KeywordChoice alignedOrStatic = new PLI_KeywordChoice(
					"STATIC", "VARYING");
			public @OPT PLI_Keyword ALIGNED = new PLI_Keyword("ALIGNED");
			public @OPT PLI_BitInitial initialValue;
			
			public static class PLI_BitInitial extends TokenSequence
			{
				public PLI_Keyword INITIAL = new PLI_Keyword("INITIAL");
				public PLI_Punctuation leftParen = new PLI_Punctuation('(');
				public PLI_BitLiteral initialValue;
				public PLI_Punctuation rightParen = new PLI_Punctuation(')');
			}
		}
		
		public static class PLI_TypeGraphic extends TokenSequence
		{
			public PLI_Keyword GRAPHIC = new PLI_Keyword("GRAPHIC");
			public @OPT PLI_TypeSize size;
			public @OPT PLI_KeywordChoice varyingOrStatic = new PLI_KeywordChoice(
					"VARYING");
		}
	}

	public static class PLI_TypeSize extends TokenSequence
	{
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public PLI_TypeSizeContents typeSizeContents;
		public @OPT TokenList<PLI_TypeMoreSizes> moreSizes;
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		
		public static class PLI_TypeSizeContents extends TokenChooser
		{
			public PLI_Punctuation star = new PLI_Punctuation('*');
			
			public static class PLI_TypeSizeNormal extends TokenSequence
			{
				public PLI_Expression size1;
				public @OPT PLI_Punctuation comma = new PLI_Punctuation(',');
				public @OPT PLI_Expression size2;
			}
		}
		
		public static class PLI_TypeMoreSizes extends TokenSequence
		{
			public PLI_Punctuation comma = new PLI_Punctuation(',');
			public PLI_TypeSizeContents typeSizeContents;
		}
	}
}

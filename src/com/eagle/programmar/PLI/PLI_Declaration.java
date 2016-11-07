// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.PLI_Declaration.PLI_Declare_Variables.PLI_Identifier_List.PLI_Declare_Initial;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Level;
import com.eagle.programmar.PLI.Terminals.PLI_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class PLI_Declaration extends TokenSequence
{
	public @OPT TokenList<PLI_Comment> commentList;
	
	public PLI_KeywordChoice DECLARE = new PLI_KeywordChoice(
			"DCL", "DECLARE");
	public SeparatedList<PLI_Declare_Item,PunctuationComma> items;
	public PunctuationSemicolon semicolon;

	public static class PLI_Declare_Item extends TokenSequence
	{
		public @OPT PLI_Level level;
		public PLI_Declare_Variables declareVariables;
		public @OPT PLI_Declare_Size declareSize;
		public @OPT PLI_Type type1;
		public @OPT PLI_KeywordChoice options = new PLI_KeywordChoice(
				"BUILTIN", "CONTROLLED", "EXTERNAL", "NONASSIGNABLE", "OPTIONAL");
		public @OPT PLI_Declare_Character character1;
		public @OPT PLI_Keyword STATIC = new PLI_Keyword("STATIC");
		public @OPT PLI_Type type2;
		public @OPT PLI_Declare_Initial initial;
		public @OPT PLI_Declare_Character character2;
	}
	
	public static class PLI_Declare_Variables extends TokenChooser
	{
		public @CHOICE PLI_Variable_Definition varDecl;
		
		public @CHOICE static class PLI_Identifier_List extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public PLI_Variable_Definition varDecl;
			public @OPT PLI_Declare_Size size;
			public @OPT PLI_Type type;
			public @OPT PLI_Keyword STATIC = new PLI_Keyword("STATIC");
			public @OPT PLI_Declare_Initial initial;
			public @OPT TokenList<PLI_More_Identifier_List> moreIdentifiers;
			public PunctuationRightParen rightParen;

			public static class PLI_More_Identifier_List extends TokenSequence
			{
				public PunctuationComma comma;
				public PLI_Variable_Definition varDecl;
				public @OPT PLI_Declare_Size size;
				public @OPT PLI_Type type;
				public @OPT PLI_Keyword STATIC = new PLI_Keyword("STATIC");
				public @OPT PLI_Declare_Initial initial;
			}
			
			public static class PLI_Declare_Initial extends TokenSequence
			{
				public PLI_Keyword INITIAL = new PLI_Keyword("INITIAL");
				public PunctuationLeftParen leftParen;
				public SeparatedList<PLI_Expression,PunctuationComma> exprs;
				public PunctuationRightParen rightParen;
			}
		}
	}
	
	public static class PLI_Declare_Size extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public SeparatedList<PLI_Declare_Size_OneDimension,PunctuationComma> dims;
		public PunctuationRightParen rightParen;
		
		public static class PLI_Declare_Size_OneDimension extends TokenChooser
		{
			public @CHOICE static class PLI_ParenStar extends TokenSequence
			{
				public PunctuationStar star;
			}
	
			public @CHOICE static class PLI_Declare_Array extends TokenSequence
			{
				public PLI_Expression exprFrom;
				public PunctuationColon colon;
				public PLI_Expression exprTo;
			}
			
			public @CHOICE static class PLI_Declare_Bounds_Array extends TokenSequence
			{
				public PLI_Keyword LBOUND = new PLI_Keyword("LBOUND");
				public PunctuationLeftParen leftParen1;
				public PLI_Identifier_Reference var1;
				public @OPT PLI_Declare_Array_Dim dim1;
				public PunctuationRightParen rightParen1;
				public PunctuationColon colon;
				public PLI_Keyword HBOUND = new PLI_Keyword("HBOUND");
				public PunctuationLeftParen leftParen2;
				public PLI_Identifier_Reference var2;
				public @OPT PLI_Declare_Array_Dim dim2;
				public PunctuationRightParen rightParen2;
				
				public static class PLI_Declare_Array_Dim extends TokenSequence
				{
					public PunctuationComma comma;
					public PLI_Number num;
				}
			}
		}
	}
	
	public static class PLI_Declare_Character extends TokenSequence
	{
		public PLI_KeywordChoice CHARACTER = new PLI_KeywordChoice(new String[]
				{ "CHAR", "CHARACTER" } );
		public @OPT PLI_Declare_Character_Size size;
		public @OPT PLI_Keyword VARYING = new PLI_Keyword("VARYING");
		
		public static class PLI_Declare_Character_Size extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public PLI_Expression expr;
			public PunctuationRightParen rightParen;
		}
	}
}

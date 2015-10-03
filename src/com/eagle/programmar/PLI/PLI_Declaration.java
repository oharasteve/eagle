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
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_Declaration extends TokenSequence
{
	public @OPT TokenList<PLI_Comment> commentList;
	
	public PLI_KeywordChoice DECLARE = new PLI_KeywordChoice(
			"DCL", "DECLARE");
	public PLI_Declare_Item item;
	public @OPT TokenList<PLI_Declare_MoreItems> moreItems;
	public PLI_Punctuation semiColon = new PLI_Punctuation(';');

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
	
	public static class PLI_Declare_MoreItems extends TokenSequence
	{
		public PLI_Punctuation comma = new PLI_Punctuation(',');
		public PLI_Declare_Item item;
	}
	
	public static class PLI_Declare_Variables extends TokenChooser
	{
		public PLI_Variable_Definition varDecl;
		
		public static class PLI_Identifier_List extends TokenSequence
		{
			public PLI_Punctuation leftParen = new PLI_Punctuation('(');
			public PLI_Variable_Definition varDecl;
			public @OPT PLI_Declare_Size size;
			public @OPT PLI_Type type;
			public @OPT PLI_Keyword STATIC = new PLI_Keyword("STATIC");
			public @OPT PLI_Declare_Initial initial;
			public @OPT TokenList<PLI_More_Identifier_List> moreIdentifiers;
			public PLI_Punctuation rightParen = new PLI_Punctuation(')');

			public static class PLI_More_Identifier_List extends TokenSequence
			{
				public PLI_Punctuation comma = new PLI_Punctuation(',');
				public PLI_Variable_Definition varDecl;
				public @OPT PLI_Declare_Size size;
				public @OPT PLI_Type type;
				public @OPT PLI_Keyword STATIC = new PLI_Keyword("STATIC");
				public @OPT PLI_Declare_Initial initial;
			}
			
			public static class PLI_Declare_Initial extends TokenSequence
			{
				public PLI_Keyword INITIAL = new PLI_Keyword("INITIAL");
				public PLI_Punctuation leftParen = new PLI_Punctuation('(');
				public PLI_Expression expr;
				public @OPT TokenList<PLI_Declare_MoreInitial> more;
				public PLI_Punctuation rightParen = new PLI_Punctuation(')');
				
				public static class PLI_Declare_MoreInitial extends TokenSequence
				{
					public PLI_Punctuation comma = new PLI_Punctuation(',');
					public PLI_Expression expr;
				}
			}
		}
	}
	
	public static class PLI_Declare_Size extends TokenSequence
	{
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public PLI_Declare_Size_OneDimension dim;
		public @OPT TokenList<PLI_Declare_Size_MoreDimensions> moreDims;
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');
			
		public static class PLI_Declare_Size_MoreDimensions extends TokenSequence
		{
			public PLI_Punctuation comma = new PLI_Punctuation(',');
			public PLI_Declare_Size_OneDimension dim;
		}
		
		public static class PLI_Declare_Size_OneDimension extends TokenChooser
		{
			public static class PLI_ParenStar extends TokenSequence
			{
				public PLI_Punctuation star = new PLI_Punctuation('*');
			}
	
			public static class PLI_Declare_Array extends TokenSequence
			{
				public PLI_Expression exprFrom;
				public PLI_Punctuation colon = new PLI_Punctuation(':');
				public PLI_Expression exprTo;
			}
			
			public static class PLI_Declare_Bounds_Array extends TokenSequence
			{
				public PLI_Keyword LBOUND = new PLI_Keyword("LBOUND");
				public PLI_Punctuation leftParen1 = new PLI_Punctuation('(');
				public PLI_Identifier_Reference var1;
				public @OPT PLI_Declare_Array_Dim dim1;
				public PLI_Punctuation rightParen1 = new PLI_Punctuation(')');
				public PLI_Punctuation colon = new PLI_Punctuation(':');
				public PLI_Keyword HBOUND = new PLI_Keyword("HBOUND");
				public PLI_Punctuation leftParen2 = new PLI_Punctuation('(');
				public PLI_Identifier_Reference var2;
				public @OPT PLI_Declare_Array_Dim dim2;
				public PLI_Punctuation rightParen2 = new PLI_Punctuation(')');
				
				public static class PLI_Declare_Array_Dim extends TokenSequence
				{
					public PLI_Punctuation comma = new PLI_Punctuation(',');
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
			public PLI_Punctuation leftParen = new PLI_Punctuation('(');
			public PLI_Expression expr;
			public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		}
	}
}

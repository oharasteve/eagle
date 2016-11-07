// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 26, 2016

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.Symbols.CSS_Class_Definition;
import com.eagle.programmar.CSS.Terminals.CSS_Identifier;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.programmar.CSS.Terminals.CSS_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class CSS_TagList extends TokenSequence
{
	public @OPT CSS_Punctuation at = new CSS_Punctuation('@');
	public @OPT PunctuationColon colon;
	public @OPT CSS_Punctuation colonColon = new CSS_Punctuation("::");
	public CSS_Tag tag;
	public @OPT CSS_DotClass dotClass;
	public @OPT @NOSPACE CSS_Qualifier qualifier;
	public @OPT TokenList<CSS_MoreQualifiers> moreQualifiers;
	public @OPT @NOSPACE TokenList<CSS_ColonOption> colonOption;
	public @OPT @NOSPACE CSS_PunctuationChoice separator =
			new CSS_PunctuationChoice(",", "+", "~", ">");

	public static class CSS_MoreQualifiers extends TokenSequence
	{
		public @OPT @NOSPACE PunctuationComma comma;
		public CSS_Qualifier qualifier;
	}
	
	public static class CSS_Tag extends TokenChooser
	{
		public @CHOICE CSS_Identifier id;
		public @CHOICE CSS_DotClass dotClass;
		public @CHOICE PunctuationStar star;
		
		public @CHOICE static class CSS_Id_DotClass extends TokenSequence
		{
			public CSS_Identifier id;
			public CSS_DotClass dotClass;
		}
	}
	
	public static class CSS_DotClass extends TokenSequence
	{
		public PunctuationPeriod dot;
		public CSS_DotWhat what;
		
		public static class CSS_DotWhat extends TokenChooser
		{
			public @CHOICE @NOSPACE CSS_Keyword MEDIA = new CSS_Keyword("media");
			public @CHOICE @NOSPACE CSS_Class_Definition classDefinition;
		}
	}

	public static class CSS_ColonOption extends TokenSequence
	{
		public PunctuationColon colon;
		public @NOSPACE CSS_ColonWhat what;
		public @OPT @NOSPACE PunctuationComma comma;
		
		public static class CSS_ColonWhat extends TokenChooser
		{
			public @CHOICE CSS_KeywordChoice option = new CSS_KeywordChoice(
					"active",
					"after",
					"before",
					"checked",
					"decrement",
					"default",
					"end",
					"first-child",
					"focus",
					"horizontal", 
					"hover",
					"increment",
					"last-child",
					"link",
					"-moz-any-link",
					"-o-prefocus",
					"start",
					"vertical",
					"visited");
			
			public @CHOICE static class CSS_Nth_Child extends TokenSequence
			{
				public CSS_KeywordChoice NTH_CHILD = new CSS_KeywordChoice(
						"nth-child",
						"nth-last-child");
				public @NOSPACE PunctuationLeftParen leftParen;
				public @NOSPACE CSS_Value value;
				public @NOSPACE PunctuationRightParen rightParen;
			}
			
			public @CHOICE static class CSS_ColonNot extends TokenSequence
			{
				public CSS_Keyword NOT = new CSS_Keyword("not");
				public @NOSPACE PunctuationLeftParen leftParen;
				public @NOSPACE CSS_ColonNotWhat what;
				public @NOSPACE PunctuationRightParen rightParen;
				
				public static class CSS_ColonNotWhat extends TokenChooser
				{
					public @CHOICE CSS_Qualifier qualifier;
					
					public @CHOICE static class CSS_ColonNotClass extends TokenSequence
					{
						public CSS_PunctuationChoice separator = new CSS_PunctuationChoice(".", ":");
						public @NOSPACE CSS_Class_Definition classDefinition;
					}
				}
			}
		}
	}
}

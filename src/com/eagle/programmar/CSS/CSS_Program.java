// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.CSS;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.CSS.CSS_TagList.CSS_ColonOption;
import com.eagle.programmar.CSS.Directives.CSS_If_Directive;
import com.eagle.programmar.CSS.Terminals.CSS_Comment;
import com.eagle.programmar.CSS.Terminals.CSS_Identifier;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.programmar.CSS.Terminals.CSS_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class CSS_Program extends EagleLanguage
{
	public static final String NAME = "CSS";
	
	public CSS_Program()
	{
		super(NAME, new CSS_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/css/";
	}
	
	public @OPT TokenList<CSS_Entry> entries;
	
	public static class CSS_Entry extends TokenChooser
	{
		public @CHOICE CSS_Comment comment;
		public @CHOICE CSS_Body body;
		public @CHOICE CSS_If_Directive ifDirective;
		public @CHOICE CSS_AtEntry atEntry;
	}

	public static class CSS_Body extends TokenSequence
	{
		public @OPT @NEWLINE TokenList<CSS_TagList> tags;
		public @OPT PunctuationStar star;
		public @INDENT PunctuationLeftBrace leftBrace;
		public @OPT TokenList<CSS_Item> items;
		public @OPT CSS_BackslashZero zero;
		public @OUTDENT PunctuationRightBrace rightBrace;
		public @OPT TokenList<CSS_ColonOption> colonOption;
		public @OPT CSS_StarPiece starPiece;
		public @OPT CSS_Qualifier qualifier;
		public @OPT @NOSPACE PunctuationComma comma;
		public @OPT @NOSPACE @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
		
		public static class CSS_StarPiece extends TokenSequence
		{
			public PunctuationStar star;
			public @OPT PunctuationColon colon;
			public @OPT CSS_Keyword FIRSTCHILD = new CSS_Keyword("first-child");
			public @OPT CSS_Punctuation plus = new CSS_Punctuation('+');
			public CSS_Keyword HTML = new CSS_Keyword("html");
		}
		
		public static class CSS_BackslashZero extends TokenSequence
		{
			public CSS_Punctuation backslash = new CSS_Punctuation("\\");
			public CSS_Number zero;
		}
	}
	
	public static class CSS_Item extends TokenChooser
	{
		public @CHOICE CSS_Comment comment;
		public @CHOICE CSS_Line line;
		
		public @CHOICE static class CSS_PercentItem extends TokenSequence
		{
			public @NEWLINE CSS_Number pct;
			public @NOSPACE CSS_Punctuation percent = new CSS_Punctuation('%');
			public @INDENT PunctuationLeftBrace leftBrace;
			public @OPT TokenList<CSS_Item> items;
			public @NOSPACE @OUTDENT PunctuationRightBrace rightBrace;
		}
		
		public @CHOICE static class CSS_ToItem extends TokenSequence
		{
			public CSS_KeywordChoice TO = new CSS_KeywordChoice("from", "to");
			public @INDENT PunctuationLeftBrace leftBrace;
			public @OPT TokenList<CSS_Item> items;
			public @NOSPACE @OUTDENT PunctuationRightBrace rightBrace;
		}
	}
	
	public static class CSS_Line extends TokenSequence
	{
		public @NEWLINE @OPT PunctuationStar star;
		public @NOSPACE CSS_Identifier attribute;
		public @NOSPACE CSS_PunctuationChoice colonEquals = new CSS_PunctuationChoice(":", "=");
		public @OPT CSS_Value_List values;
		public @OPT CSS_Tab tab;
		public @OPT @NOSPACE PunctuationSemicolon semicolon1;
		public @OPT @NOSPACE @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon2;
	
		public static class CSS_Tab extends TokenSequence
		{
			public CSS_Punctuation tab = new CSS_Punctuation("\\9");
		}
		
		public static class CSS_Value_List extends TokenSequence
		{
			public CSS_Value val;
			public @OPT TokenList<CSS_MoreValues> more;
			
			public static class CSS_MoreValues extends TokenChooser
			{
				public @CHOICE CSS_Value val;
				
				public @CHOICE static class CSS_MoreValuesComma extends TokenSequence
				{
					public @NOSPACE PunctuationComma comma;
					public CSS_Value val;
				}
			}
		}
	}
}

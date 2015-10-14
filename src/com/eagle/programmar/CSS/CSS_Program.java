// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.CSS;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.CSS.CSS_Program.CSS_TagList.CSS_ColonOption;
import com.eagle.programmar.CSS.CSS_Value.CSS_URL_Value;
import com.eagle.programmar.CSS.Directives.CSS_If_Directive;
import com.eagle.programmar.CSS.Symbols.CSS_Class_Definition;
import com.eagle.programmar.CSS.Terminals.CSS_Comment;
import com.eagle.programmar.CSS.Terminals.CSS_FileName;
import com.eagle.programmar.CSS.Terminals.CSS_Identifier;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.programmar.CSS.Terminals.CSS_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
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
		public CSS_Comment comment;
		public CSS_Body body;
		public CSS_AtMedia atMedia;
		public CSS_Moz_Document mozDocument;
		public CSS_AtImport atImport;
		public CSS_If_Directive ifDirective;
		public CSS_AtCharset charset;
		public CSS_AtNamespace namespace;
	}

	public static class CSS_TagList extends TokenSequence
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
			public CSS_Identifier id;
			public CSS_DotClass dotClass;
			public PunctuationStar star;
			
			public static class CSS_Id_DotClass extends TokenSequence
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
				public @NOSPACE CSS_Keyword MEDIA = new CSS_Keyword("media");
				public @NOSPACE CSS_Class_Definition classDefinition;
			}
		}

		public static class CSS_ColonOption extends TokenSequence
		{
			public PunctuationColon colon;
			public @NOSPACE CSS_ColonWhat what;
			public @OPT @NOSPACE PunctuationComma comma;
			
			public static class CSS_ColonWhat extends TokenChooser
			{
				public CSS_KeywordChoice option = new CSS_KeywordChoice(
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
				
				public static class CSS_Nth_Child extends TokenSequence
				{
					public CSS_KeywordChoice NTH_CHILD = new CSS_KeywordChoice(
							"nth-child",
							"nth-last-child");
					public @NOSPACE PunctuationLeftParen leftParen;
					public @NOSPACE CSS_Value value;
					public @NOSPACE PunctuationRightParen rightParen;
				}
				
				public static class CSS_ColonNot extends TokenSequence
				{
					public CSS_Keyword NOT = new CSS_Keyword("not");
					public @NOSPACE PunctuationLeftParen leftParen;
					public @NOSPACE CSS_ColonNotWhat what;
					public @NOSPACE PunctuationRightParen rightParen;
					
					public static class CSS_ColonNotWhat extends TokenChooser
					{
						public CSS_Qualifier qualifier;
						
						public static class CSS_ColonNotClass extends TokenSequence
						{
							public CSS_PunctuationChoice separator = new CSS_PunctuationChoice(".", ":");
							public @NOSPACE CSS_Class_Definition classDefinition;
						}
					}
				}
			}
		}
	}
	
	public static class CSS_Body extends TokenSequence
	{
		public @OPT @NEWLINE TokenList<CSS_TagList> tags;
		public @OPT PunctuationStar star;
		public @INDENT PunctuationLeftBrace leftBrace;
		public @OPT TokenList<CSS_Item> items;
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
	}
	
	public static class CSS_Item extends TokenChooser
	{
		public CSS_Comment comment;
		public CSS_Line line;
		
		public static class CSS_PercentItem extends TokenSequence
		{
			public @NEWLINE CSS_Number pct;
			public @NOSPACE CSS_Punctuation percent = new CSS_Punctuation('%');
			public @INDENT PunctuationLeftBrace leftBrace;
			public @OPT TokenList<CSS_Item> items;
			public @NOSPACE @OUTDENT PunctuationRightBrace rightBrace;
		}
		
		public static class CSS_ToItem extends TokenSequence
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
				public CSS_Value val;
				
				public static class CSS_MoreValuesComma extends TokenSequence
				{
					public @NOSPACE PunctuationComma comma;
					public CSS_Value val;
				}
			}
		}
	}
	
	public static class CSS_Moz_Document extends TokenSequence
	{
		public CSS_Punctuation at = new CSS_Punctuation('@');
		public CSS_Keyword MOZDOCUMENT = new CSS_Keyword("-moz-document");
		public CSS_Keyword URLPREFIX = new CSS_Keyword("url-prefix");
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE CSS_Literal literal;
		public @NOSPACE PunctuationRightParen rightParen;
		public @INDENT PunctuationLeftBrace leftBrace;
		public TokenList<CSS_Body> bodies;
		public @NOSPACE @OUTDENT PunctuationRightBrace rightBrace;
	}
	
	public static class CSS_AtMedia extends TokenSequence
	{
		public CSS_Punctuation at = new CSS_Punctuation('@');
		public @NOSPACE CSS_Keyword MEDIA = new CSS_Keyword("media");
		public CSS_MediaParam param;
		public @OPT TokenList<CSS_MoreMediaParam> moreParams;
		public @INDENT PunctuationLeftBrace leftBrace;
		public @OPT CSS_Comment comment;
		public TokenList<CSS_Body> bodies;
		public @NOSPACE @OUTDENT PunctuationRightBrace rightBrace;
		
		public static class CSS_MediaParam extends TokenChooser
		{
			public static class CSS_MediaParamParens extends TokenSequence
			{
				public PunctuationLeftParen leftParen;
				public CSS_Line line;
				public PunctuationRightParen rightParen;
			}
			
			public static class CSS_MediaScreen extends TokenSequence
			{
				public @OPT CSS_Keyword ONLY = new CSS_Keyword("only");
				public CSS_Keyword SCREEN = new CSS_Keyword("screen");
			}
			
			public static class CSS_MediaPrint extends TokenSequence
			{
				public CSS_Keyword PRINT = new CSS_Keyword("print");
			}
		}
		
		public static class CSS_MoreMediaParam extends TokenSequence
		{
			public @OPT @NOSPACE PunctuationComma comma;
			public @OPT CSS_Keyword AND = new CSS_Keyword("and");
			public CSS_MediaParam param;
		}
	}
	
	public static class CSS_AtImport extends TokenSequence
	{
		public CSS_Punctuation at = new CSS_Punctuation('@');
		public @NOSPACE CSS_Keyword IMPORT = new CSS_Keyword("import");
		public CSS_ImportWhat what;
		public @NOSPACE PunctuationSemicolon semicolon;
		
		public static class CSS_ImportWhat extends TokenChooser
		{
			public CSS_FileName fileName;
			public @FIRST CSS_URL_Value urlValue;
		}
	}
	
	public static class CSS_AtCharset extends TokenSequence
	{
		public CSS_Punctuation at = new CSS_Punctuation('@');
		public @NOSPACE CSS_Keyword CHARSET = new CSS_Keyword("charset");
		public CSS_Literal charset;
		public @NOSPACE PunctuationSemicolon semicolon;
	}

	public static class CSS_AtNamespace extends TokenSequence
	{
		public CSS_Punctuation at = new CSS_Punctuation('@');
		public @NOSPACE CSS_Keyword NAMESPACE = new CSS_Keyword("namespace");
		public CSS_Identifier name;
		public CSS_Keyword URL = new CSS_Keyword("URL");
		public PunctuationLeftParen leftParen;
		public CSS_FileName url;
		public PunctuationRightParen rightParen;
		public @NOSPACE PunctuationSemicolon semicolon;
	}
}

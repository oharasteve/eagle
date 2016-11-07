// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 26, 2016

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.CSS_Program.CSS_Body;
import com.eagle.programmar.CSS.CSS_Program.CSS_Line;
import com.eagle.programmar.CSS.CSS_Value.CSS_URL_Value;
import com.eagle.programmar.CSS.Terminals.CSS_Comment;
import com.eagle.programmar.CSS.Terminals.CSS_FileName;
import com.eagle.programmar.CSS.Terminals.CSS_Identifier;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSS_AtEntry extends TokenChooser
{
	public @CHOICE static class CSS_Moz_Document extends TokenSequence
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
	
	public @CHOICE static class CSS_AtMedia extends TokenSequence
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
			public @CHOICE static class CSS_MediaParamParens extends TokenSequence
			{
				public PunctuationLeftParen leftParen;
				public CSS_Line line;
				public PunctuationRightParen rightParen;
			}
			
			public @CHOICE static class CSS_MediaScreen extends TokenSequence
			{
				public @OPT CSS_Keyword ONLY = new CSS_Keyword("only");
				public CSS_Keyword SCREEN = new CSS_Keyword("screen");
			}
			
			public @CHOICE static class CSS_MediaPrint extends TokenSequence
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
	
	public @CHOICE static class CSS_AtImport extends TokenSequence
	{
		public CSS_Punctuation at = new CSS_Punctuation('@');
		public @NOSPACE CSS_Keyword IMPORT = new CSS_Keyword("import");
		public CSS_ImportWhat what;
		public @NOSPACE PunctuationSemicolon semicolon;
		
		public static class CSS_ImportWhat extends TokenChooser
		{
			public @CHOICE CSS_FileName fileName;
			public @FIRST CSS_URL_Value urlValue;
		}
	}
	
	public @CHOICE static class CSS_AtCharset extends TokenSequence
	{
		public CSS_Punctuation at = new CSS_Punctuation('@');
		public @NOSPACE CSS_Keyword CHARSET = new CSS_Keyword("charset");
		public CSS_Literal charset;
		public @NOSPACE PunctuationSemicolon semicolon;
	}

	public @CHOICE static class CSS_AtNamespace extends TokenSequence
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

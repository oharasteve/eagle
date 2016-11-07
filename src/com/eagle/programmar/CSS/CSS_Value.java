// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2014

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.Terminals.CSS_Base64;
import com.eagle.programmar.CSS.Terminals.CSS_Color;
import com.eagle.programmar.CSS.Terminals.CSS_FileName;
import com.eagle.programmar.CSS.Terminals.CSS_HexNumber;
import com.eagle.programmar.CSS.Terminals.CSS_Identifier;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CSS_Value extends TokenChooser
{
	public @CHOICE CSS_HexNumber hex;
	public @CHOICE CSS_Color color;
	public @CHOICE CSS_Literal lit;
	public @FIRST CSS_Gradient gradient;
	
	public @LAST static class CSS_IdentifierValue extends TokenSequence
	{
		public CSS_Identifier id;
		public @OPT CSS_FunctionCall functionCall;
		
		public static class CSS_FunctionCall extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public @OPT SeparatedList<CSS_Value,PunctuationComma> values;
			public PunctuationRightParen rightParen;
		}
	}
	
	public @CHOICE static class CSS_NumericValue extends TokenSequence
	{
		public CSS_Number number;
		public @OPT @NOSPACE CSS_NumericSuffix suffix;
		public @OPT CSS_SlashNumber slash;
		
		public static class CSS_NumericSuffix extends TokenChooser
		{
			public @CHOICE @NOSPACE CSS_KeywordChoice units = new CSS_KeywordChoice("px", "em", "s", "in", "deg");
			public @CHOICE @NOSPACE CSS_Punctuation percent = new CSS_Punctuation('%');
		}
		
		public static class CSS_SlashNumber extends TokenSequence
		{
			public @NOSPACE PunctuationSlash slash;
			public @NOSPACE CSS_Number number;
			public @OPT @NOSPACE CSS_NumericSuffix suffix;
		}
	}
	
	public @CHOICE static class CSS_Important extends TokenSequence
	{
		public CSS_Punctuation exclamation = new CSS_Punctuation('!');
		public @NOSPACE CSS_Keyword IMPORTANT = new CSS_Keyword("important");
	}
	
	public @CHOICE static class CSS_Format extends TokenSequence
	{
		public CSS_Keyword FORMAT = new CSS_Keyword("format");
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE CSS_Literal literal;
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Attr extends TokenSequence
	{
		public CSS_Keyword ATTR = new CSS_Keyword("attr");
		public @NOSPACE PunctuationLeftParen leftParen;
		public CSS_KeywordChoice CODE = new CSS_KeywordChoice(
				"href",
				"title");
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Rect extends TokenSequence
	{
		public CSS_Keyword RECT = new CSS_Keyword("rect");
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE CSS_Number num1;
		public @NOSPACE @OPT PunctuationComma comma1;
		public @NOSPACE CSS_Number num2;
		public @NOSPACE @OPT PunctuationComma comma2;
		public @NOSPACE CSS_Number num3;
		public @NOSPACE @OPT PunctuationComma comma3;
		public @NOSPACE CSS_Number num4;
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Alpha_Value extends TokenSequence
	{
		public CSS_Keyword ALPHA = new CSS_Keyword("alpha");
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE CSS_Keyword OPACITY = new CSS_Keyword("opacity");
		public @NOSPACE PunctuationEquals equals;
		public @NOSPACE CSS_Number number;
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Translate3d extends TokenSequence
	{
		public CSS_Keyword TRANSLATE3D = new CSS_Keyword("translate3d");
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE CSS_Number number1;
		public @NOSPACE PunctuationComma comma1;
		public CSS_Number number2;
		public @NOSPACE PunctuationComma comma2;
		public CSS_Number number3;
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Cubic_Bezier extends TokenSequence
	{
		public CSS_Keyword CUBIC_BEZIER = new CSS_Keyword("cubic-bezier");
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE CSS_Number number1;
		public @NOSPACE PunctuationComma comma1;
		public CSS_Number number2;
		public @NOSPACE PunctuationComma comma2;
		public CSS_Number number3;
		public @NOSPACE PunctuationComma comma3;
		public CSS_Number number4;
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_RotateValue extends TokenSequence
	{
		public CSS_Keyword ROTATE = new CSS_Keyword("rotate");
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE CSS_Number number;
		public CSS_Keyword DEG = new CSS_Keyword("deg");
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_URL_Value extends TokenSequence
	{
		public CSS_Keyword URL = new CSS_Keyword("url");
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE CSS_File file;
		public @NOSPACE PunctuationRightParen rightParen;
		
		public static class CSS_File extends TokenChooser
		{
			public @CHOICE CSS_FileName fileName;
			
			public @CHOICE static class CSS_FileInline extends TokenSequence
			{
				public CSS_Keyword DATA = new CSS_Keyword("data");
				public @NOSPACE PunctuationColon colon;
				public @NOSPACE CSS_Keyword IMAGE = new CSS_Keyword("image");
				public @NOSPACE PunctuationSlash slash;
				public @NOSPACE CSS_Keyword PNG = new CSS_Keyword("png");
				public @NOSPACE PunctuationSemicolon semicolon;
				public @NOSPACE CSS_Keyword BASE64 = new CSS_Keyword("base64");
				public @NOSPACE PunctuationComma comma;
				public @NOSPACE CSS_Base64 base64;
			}
		}
	}
	
	public @CHOICE static class CSS_Scale extends TokenSequence
	{
		public CSS_Keyword SCALE = new CSS_Keyword("scale");
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE CSS_Number number1;
		public @NOSPACE PunctuationComma comma2;
		public CSS_Number number2;
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Microsoft_Gradient extends TokenSequence
	{
		public CSS_Keyword PROGID = new CSS_Keyword("progid");
		public @NOSPACE PunctuationColon colon;
		public @NOSPACE CSS_Keyword IMAGETRANSFORM = new CSS_Keyword("DXImageTransform");
		public @NOSPACE PunctuationPeriod dot1;
		public @NOSPACE CSS_Keyword MICROSOFT = new CSS_Keyword("Microsoft");
		public @NOSPACE PunctuationPeriod dot2;
		public @NOSPACE CSS_Keyword GRADIENT = new CSS_Keyword("gradient");
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE CSS_MS_GradientPiece piece;
		public @OPT TokenList<CSS_More_MS_GradientPieces> morePieces;
		public @NOSPACE PunctuationRightParen rightParen;
		
		public static class CSS_MS_GradientPiece extends TokenSequence
		{
			public CSS_KeywordChoice gradPiece = new CSS_KeywordChoice(
					"startColorStr", "endColorStr", "GradientType", "enabled");
			public PunctuationEquals equals;
			public CSS_Value value;
		}
		
		public static class CSS_More_MS_GradientPieces extends TokenSequence
		{
			public PunctuationComma comma;
			public CSS_MS_GradientPiece piece;
		}
	}
	
	public @CHOICE static class CSS_Webkit_Image_Set extends TokenSequence
	{
		public CSS_Keyword WEBKIT_IMAGE_GET = new CSS_Keyword("-webkit-image-set");
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE CSS_Webkit_Value piece;
		public @OPT TokenList<CSS_More_WebkitPieces> morePieces;
		public @NOSPACE PunctuationRightParen rightParen;
		
		public static class CSS_Webkit_Value extends TokenSequence
		{
			public CSS_Value url;
			public CSS_KeywordChoice factor = new CSS_KeywordChoice("1x", "2x", "3x");
		}
		
		public static class CSS_More_WebkitPieces extends TokenSequence
		{
			public PunctuationComma comma;
			public CSS_Webkit_Value value;
		}
	}
}
// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber.JavaP_HexNoPrefix;
import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class JavaP_RuntimeVisibleAnnotations extends TokenSequence
{
	public JavaP_Keyword ANNOTATIONS = new JavaP_Keyword("RuntimeVisibleAnnotations");
	public PunctuationColon colon;
	public JavaP_RuntimeVisibleAnnotationChoice choice;
	
	public static class JavaP_RuntimeVisibleAnnotationChoice extends TokenChooser
	{
		public static class JavaP_RuntimeVisibleAnnotationLength extends TokenSequence
		{
			public JavaP_Keyword LENGTH = new JavaP_Keyword("length");
			public PunctuationEquals equals;
			public JavaP_HexNumber hex;
			public TokenList<JavaP_AnnotHexes> hexes;
			
			public static class JavaP_AnnotHexes extends TokenChooser
			{
				public JavaP_HexNoPrefix hex;
				public JavaP_EndOfLine eoln;
			}
		}
		
		public static class JavaP_RuntimeVisibleAnnotationNoLength extends TokenSequence
		{
			public JavaP_EndOfLine eoln;
			public TokenList<JavaP_RuntimeAnnotation> annotations;

			public static class JavaP_RuntimeAnnotation extends TokenSequence
			{
				public JavaP_Number seq;
				public PunctuationColon colon;
				public JavaP_Identifier id;
				public PunctuationLeftParen leftParen;
				public @OPT JavaP_RuntimeAnnotationValue value;
				public PunctuationRightParen rightParen;
				public JavaP_EndOfLine eoln;
				
				public static class JavaP_RuntimeAnnotationValue extends TokenSequence
				{
					public JavaP_Identifier id;
					public PunctuationEquals equals;
					public JavaP_WhichAnnotation which;
				}
				
				public static class JavaP_AnnotationIdentifier extends TokenSequence
				{
					public JavaP_KeywordChoice CES = new JavaP_KeywordChoice("c", "e", "s");
					public JavaP_Identifier id;
					public @OPT PunctuationPeriod dot;
					public @OPT JavaP_Identifier id4;
				}
		
				public static class JavaP_WhichAnnotation extends TokenChooser
				{
					public JavaP_AnnotationIdentifier id;
		
					public static class JavaP_AnnotationList extends TokenSequence
					{
						public PunctuationLeftBracket leftBracket;
						public SeparatedList<JavaP_AnnotationIdentifier,PunctuationComma> idList;
						public PunctuationRightBracket rightBracket;
					}
				}
			}
		}
	}
}

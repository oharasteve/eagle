// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 4, 2015

package com.eagle.programmar.JavaP.Blocks;

import com.eagle.programmar.JavaP.JavaP_Syntax;
import com.eagle.programmar.JavaP.JavaP_Value;
import com.eagle.programmar.JavaP.Terminals.JavaP_Comment;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
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
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class JavaP_CodeStackInfo extends TokenSequence
{
	public SeparatedList<JavaP_CodeStackParam, PunctuationComma> params;
	public JavaP_EndOfLine eoln;
	public @OPT TokenList<JavaP_CodeStackEntry> entries;
	
	public static class JavaP_CodeStackParam extends TokenSequence
	{
		public JavaP_KeywordChoice STACK = new JavaP_KeywordChoice("args_size", "locals", "stack");
		public PunctuationEquals equals;
		public JavaP_Number number;
	}
	
	public static class JavaP_CodeStackEntry extends TokenSequence
	{
		public JavaP_Number number;
		public PunctuationColon colon;
		public JavaP_KeywordChoice command = new JavaP_KeywordChoice(JavaP_Syntax.COMMANDS);
		public JavaP_CodeStackValue value;
		public JavaP_EndOfLine eoln;
		
		public static class JavaP_CodeStackValue extends TokenChooser
		{
			public static class JavaP_CodeValueRegular extends TokenSequence
			{
				public @OPT SeparatedList<JavaP_Value,PunctuationComma> values;
				public @OPT JavaP_Comment comment;
			}
			
			public static class JavaP_CodeValueBraces extends TokenSequence
			{
				public PunctuationLeftBrace leftBrace;
				public @OPT JavaP_Comment comment;
				public JavaP_EndOfLine eoln;
				public TokenList<JavaP_CodeValueLine> lines;
				public PunctuationRightBrace rightBrace;
				
				public static class JavaP_CodeValueLine extends TokenSequence
				{
					public JavaP_CodeValueCase value;
					public PunctuationColon colon;
					public JavaP_Number number;
					public JavaP_EndOfLine eoln;
					
					public static class JavaP_CodeValueCase extends TokenChooser
					{
						public JavaP_Number number;
						public JavaP_Keyword DEFAULT = new JavaP_Keyword("default");
					}
				}
			}
		}
	}
}
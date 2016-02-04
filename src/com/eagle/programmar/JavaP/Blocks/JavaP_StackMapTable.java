// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 4, 2015

package com.eagle.programmar.JavaP.Blocks;

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
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class JavaP_StackMapTable extends TokenSequence
{
	public JavaP_Keyword STACKMAP = new JavaP_Keyword("StackMapTable");
	public PunctuationColon colon;
	public JavaP_Keyword NUMBER_OF_ENTRIES = new JavaP_Keyword("number_of_entries");
	public PunctuationEquals equals;
	public JavaP_Number entries;
	public JavaP_EndOfLine eoln;
	
	public @OPT TokenList<JavaP_StackMapFrame> frames;
	
	public static class JavaP_StackMapFrame extends TokenSequence
	{
		public JavaP_Keyword FRAMETYPE = new JavaP_Keyword("frame_type");
		public PunctuationEquals equals;
		public JavaP_Number frameType;
		public @OPT JavaP_Comment comment;
		public JavaP_EndOfLine eoln;
		
		public @OPT JavaP_StackMapOffset offsetDelta;
		public @OPT JavaP_StackMapLocals locals;
		public @OPT JavaP_StackMapStack stack;

		public static class JavaP_StackMapOffset extends TokenSequence
		{
			public JavaP_Keyword OFFSETDELTA = new JavaP_Keyword("offset_delta");
			public PunctuationEquals equals;
			public JavaP_Number delta;
			public JavaP_EndOfLine eoln;
		}
		
		public static class JavaP_StackMapLocals extends TokenSequence
		{
			public JavaP_Keyword LOCALS = new JavaP_Keyword("locals");
			public PunctuationEquals equals;
			public PunctuationLeftBracket leftBracket;
			public @OPT SeparatedList<JavaP_StackMapLocal,PunctuationComma> locals;
			public PunctuationRightBracket rightBracket;
			public JavaP_EndOfLine eoln;
			
			public static class JavaP_StackMapLocal extends TokenChooser
			{
				public JavaP_KeywordChoice type = new JavaP_KeywordChoice("bogus", "int", "long", "top");
				
				public static class JavaP_StackMapLocalClass extends TokenSequence
				{
					public JavaP_Keyword CLASS = new JavaP_Keyword("class");
					public JavaP_Value value;
				}
			}
		}

		public static class JavaP_StackMapStack extends TokenSequence
		{
			public JavaP_Keyword STACK = new JavaP_Keyword("stack");
			public PunctuationEquals equals;
			public PunctuationLeftBracket leftBracket;
			public SeparatedList<JavaP_StackMapStackClass,PunctuationComma> stackClasses;
			public PunctuationRightBracket rightBracket;
			public JavaP_EndOfLine eoln;
			
			public static class JavaP_StackMapStackClass extends TokenSequence
			{
				public @OPT JavaP_KeywordChoice CLASS = new JavaP_KeywordChoice("class", "uninitialized");
				public @OPT JavaP_Value value;
			}
		}
	}
}
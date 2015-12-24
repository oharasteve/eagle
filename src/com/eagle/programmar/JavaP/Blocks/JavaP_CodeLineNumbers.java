// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 4, 2015

package com.eagle.programmar.JavaP.Blocks;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_CodeLineNumbers extends TokenSequence
{
	public JavaP_Keyword LINENUMBER = new JavaP_Keyword("LineNumberTable");
	public PunctuationColon colon;
	public JavaP_EndOfLine eoln;
	public TokenList<JavaP_CodeLineEntry> entries;
	
	public static class JavaP_CodeLineEntry extends TokenSequence
	{
		public JavaP_Keyword LINE = new JavaP_Keyword("line");
		public JavaP_Number number1;
		public PunctuationColon colon;
		public JavaP_Number number2;
		public JavaP_EndOfLine eoln;
	}
}
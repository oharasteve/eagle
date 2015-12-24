// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 4, 2015

package com.eagle.programmar.JavaP.Blocks;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_CodeExceptions extends TokenSequence
{
	public JavaP_Keyword EXCEPTION = new JavaP_Keyword("Exception");
	public JavaP_Keyword TABLE = new JavaP_Keyword("table");
	public PunctuationColon colon;
	public JavaP_EndOfLine eoln1;
	
	public JavaP_Keyword FROM = new JavaP_Keyword("from");
	public JavaP_Keyword TO = new JavaP_Keyword("to");
	public JavaP_Keyword TARGET = new JavaP_Keyword("target");
	public JavaP_Keyword TYPE = new JavaP_Keyword("type");
	public JavaP_EndOfLine eoln2;

	public @OPT TokenList<JavaP_CodeExceptionEntry> entries;
	
	public static class JavaP_CodeExceptionEntry extends TokenSequence
	{
		public JavaP_Number from;
		public JavaP_Number to;
		public JavaP_Number target;
		public @OPT JavaP_Keyword CLASS = new JavaP_Keyword("Class");
		public JavaP_QualifiedName name;
		public JavaP_EndOfLine eoln;
	}
}
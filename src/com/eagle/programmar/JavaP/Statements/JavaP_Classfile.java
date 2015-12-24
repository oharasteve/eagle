// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber.JavaP_HexNoPrefix;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class JavaP_Classfile extends TokenSequence
{
	public JavaP_Keyword CLASSFILE = new JavaP_Keyword("Classfile");
	public JavaP_QualifiedName filename;
	public JavaP_EndOfLine eoln;
	public @OPT JavaP_LastModified lastModified;
	public @OPT JavaP_MD5Checksum checksum;
	
	public static class JavaP_LastModified extends TokenSequence
	{
		public JavaP_Keyword LAST = new JavaP_Keyword("Last");
		public JavaP_Keyword MODIFIED = new JavaP_Keyword("modified");
		public JavaP_KeywordChoice MONTH = new JavaP_KeywordChoice(
				"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		public JavaP_Number day;
		public PunctuationComma comma;
		public JavaP_Number year;
		public PunctuationSemicolon semicolon;
		public JavaP_Keyword SIZE = new JavaP_Keyword("size");
		public JavaP_Number bytes;
		public JavaP_Keyword BYTES = new JavaP_Keyword("bytes");
		public JavaP_EndOfLine eoln;
	}
	
	public static class JavaP_MD5Checksum extends TokenSequence
	{
		public JavaP_Keyword MD5 = new JavaP_Keyword("MD5");
		public JavaP_Keyword CHECKSUM = new JavaP_Keyword("checksum");
		public JavaP_HexNoPrefix hex;
		public JavaP_EndOfLine eoln;
	}
}

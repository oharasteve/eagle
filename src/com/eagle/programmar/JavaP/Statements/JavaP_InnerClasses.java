// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference;
import com.eagle.programmar.JavaP.Terminals.JavaP_Comment;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class JavaP_InnerClasses extends TokenSequence
{
	public JavaP_Keyword INNERCLASSES = new JavaP_Keyword("InnerClasses");
	public PunctuationColon colon;
	public JavaP_EndOfLine eoln;
	
	public TokenList<JavaP_InnerClassEntry> entries;
	
	public static class JavaP_InnerClassEntry extends TokenSequence
	{
		public @OPT JavaP_KeywordChoice PUBLIC = new JavaP_KeywordChoice("private", "protected", "public");
		public @OPT JavaP_Keyword STATIC = new JavaP_Keyword("static");
		public @OPT JavaP_Keyword ABSTRACT = new JavaP_Keyword("abstract");
		public @OPT JavaP_Keyword FINAL = new JavaP_Keyword("final");
		public JavaP_Symbol_Reference id1;
		public PunctuationEquals equals;
		public JavaP_Symbol_Reference id2;
		public JavaP_Keyword OF = new JavaP_Keyword("of");
		public JavaP_Symbol_Reference id3;
		public PunctuationSemicolon semicolon;
		public JavaP_Comment comment;
		public JavaP_EndOfLine eoln;
	}
}

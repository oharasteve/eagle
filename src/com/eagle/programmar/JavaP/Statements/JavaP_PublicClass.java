// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.JavaP_Syntax;
import com.eagle.programmar.JavaP.Statements.JavaP_Classes.JavaP_OneClass.JavaP_OneClassGeneric;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

public class JavaP_PublicClass extends TokenSequence
{
	public @OPT JavaP_Keyword PUBLIC = new JavaP_Keyword("public");
	public @OPT JavaP_Keyword ABSTRACT = new JavaP_Keyword("abstract");
	public @OPT JavaP_Keyword FINAL = new JavaP_Keyword("final");
	public JavaP_KeywordChoice CLASS = new JavaP_KeywordChoice("class", "interface");
	public JavaP_QualifiedName className;
	public @OPT JavaP_OneClassGeneric generic;
	public @OPT TokenList<JavaP_ClassExtends> extendsOrImplements;
	public JavaP_EndOfLine eoln;

	public @OPT JavaP_MinorVersion minorVersion;
	public @OPT JavaP_MajorVersion majorVersion;
	public @OPT JavaP_Flags flags;
	
	public static class JavaP_ClassExtends extends TokenSequence
	{
		public JavaP_KeywordChoice EXTENDS = new JavaP_KeywordChoice("extends", "implements");
		public SeparatedList<JavaP_QualifiedName,PunctuationComma> parentClassName;
		public @OPT JavaP_ExtendsTemplatedClass templatedClass;
		
		public static class JavaP_ExtendsTemplatedClass extends TokenSequence
		{
			public JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
			public JavaP_QualifiedName name;
			public JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
		}
	}

	public static class JavaP_Flags extends TokenSequence
	{
		public JavaP_Keyword FLAGS = new JavaP_Keyword("flags");
		public PunctuationColon colon;
		public SeparatedList<JavaP_Flag, PunctuationComma> flags;
		public JavaP_EndOfLine eoln;
		
		public static class JavaP_Flag extends TokenSequence
		{
			public JavaP_KeywordChoice name = new JavaP_KeywordChoice(JavaP_Syntax.ACC_CODES);
		}
	}
}

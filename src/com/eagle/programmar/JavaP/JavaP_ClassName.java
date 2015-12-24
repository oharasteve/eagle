// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.JavaP.Terminals.JavaP_LClassName;
import com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class JavaP_ClassName extends TokenSequence
{
	public JavaP_LClassName lClass;
	public @OPT JavaP_TemplatedClass template;
	public PunctuationSemicolon semicolon;
	
	public static class JavaP_TemplatedClass extends TokenSequence
	{
		public JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
		public @OPT JavaP_PunctuationChoice plus = new JavaP_PunctuationChoice("+", "*");
		public @OPT TokenList<JavaP_ClassName> classes;
		public JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
	}
}

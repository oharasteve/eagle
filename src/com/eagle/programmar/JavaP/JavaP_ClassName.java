// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.programmar.JavaP.Terminals.JavaP_LClassName;
import com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class JavaP_ClassName extends TokenChooser
{
	public @CHOICE static class JavaP_ClassNameL extends TokenSequence
	{
		public @OPT JavaP_PunctuationChoice plus = new JavaP_PunctuationChoice("[", "+", "*");
		public JavaP_LClassName lClass;
		public @OPT JavaP_TemplatedClass template;
		public PunctuationSemicolon semicolon;
		
		public static class JavaP_TemplatedClass extends TokenSequence
		{
			public JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
			public @OPT TokenList<JavaP_TemplateContents> contents;
			public JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
			
			public static class JavaP_TemplateContents extends TokenChooser
			{
				public @CHOICE JavaP_ClassName className;
				public @CHOICE PunctuationStar star;
				
				public @CHOICE static class JavaP_TemplateIds extends TokenSequence
				{
					public JavaP_Identifier id;
					public PunctuationSemicolon semicolon;
				}
			}
		}
	}
	
	public @CHOICE static class JavaP_ClassNameNoL extends TokenSequence
	{
		public JavaP_Identifier identifier;
		public PunctuationSemicolon semicolon;
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Java_Program extends EagleLanguage implements EagleRunnable, EagleScopeInterface
{
	public static final String NAME = "Java";
	
	public Java_Program()
	{
		super(NAME, new Java_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://docs.oracle.com/javase/specs/jls/se5.0/html/";
	}
	
	public static final String[] MODIFIERS = new String[] {
		"abstract",
		"final",
		"native",
		"private",
		"protected",
		"public",
		"static",
		"synchronized",
		"transient",
		"volatile"
	}; 

	public @OPT TokenList<Java_Comment> comments1;
	public @OPT Java_Annotation annotation;
	public @OPT TokenList<Java_Comment> comments2;
	public @OPT Java_Package jpackage;
	public @OPT TokenList<Java_ImportOrComment> jimportList;
	public @OPT TokenList<Java_ClassOrEnum> classOrEnum;
	
	public static class Java_ClassOrEnum extends TokenChooser
	{
		public Java_Class jclass;
		public Java_Enum jenum;
	}

	public static class Java_Package extends TokenSequence
	{
		public @NEWLINE2 Java_Keyword PACKAGE = new Java_Keyword("package");
		public SeparatedList<Java_Identifier,PunctuationPeriod> ids;
		public @NOSPACE PunctuationSemicolon semicolon;
	}
	
	public static class Java_ImportOrComment extends TokenChooser
	{
		public @NEWLINE Java_Comment comment;
		public Java_Import jimport;
	}
	
	public static class Java_Import extends TokenSequence
	{
		public @NEWLINE Java_Keyword IMPORT = new Java_Keyword("import");
		public @OPT Java_Keyword STATIC = new Java_Keyword("static");
		public Java_Identifier id;
		public @OPT TokenList<Java_DotIdentifierStar> dotId;
		public @NOSPACE PunctuationSemicolon semicolon;

		public static class Java_DotIdentifierStar extends TokenSequence
		{
			public @NOSPACE PunctuationPeriod dot;
			public Java_IdentifierStar idStar;
			
			public static class Java_IdentifierStar extends TokenChooser
			{
				public @NOSPACE Java_Identifier id;
				public @NOSPACE PunctuationStar star;
			}
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		classOrEnum.first()._whichToken.tryToInterpret(interpreter);
	}
}

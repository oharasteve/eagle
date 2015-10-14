// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Java_Class.Java_ClassElement;
import com.eagle.programmar.Java.Java_Class.Java_ClassImplements;
import com.eagle.programmar.Java.Java_Data.Java_DataModifier;
import com.eagle.programmar.Java.Java_Enum.Java_EnumDeclarations.Java_EnumClassBodyDeclaration;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Enum extends TokenSequence
{
	public @OPT @NEWLINE TokenList<Java_Annotation> annotations;
	public @OPT TokenList<Java_DataModifier> modifiers;
	public Java_Keyword ENUM = new Java_Keyword("enum");
	public Java_Variable_Definition id;
	public @OPT Java_ClassImplements implement;
	public @INDENT PunctuationLeftBrace leftBrace;
	public @OPT Java_Comment comment1;

	public @OPT Java_EnumConstants constants;
	public @OPT PunctuationComma comma;
	public @OPT TokenList<Java_Comment> comment2;
	public @OPT Java_EnumDeclarations declarations;
	public @OPT TokenList<Java_Comment> comment3;
	
	public @OPT PunctuationSemicolon semicolon1;
	public @OUTDENT PunctuationRightBrace rightBrace;
	public @OPT TokenList<Java_Comment> comment4;
	public @OPT @NOSPACE @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon2;
	
	public static class Java_EnumConstants extends TokenSequence
	{
		public Java_EnumConstant constant;
		public @OPT TokenList<Java_MoreEnumConstants> more;
		public @OPT TokenList<Java_Comment> comments;
		
		public static class Java_MoreEnumConstants extends TokenSequence
		{
			public @NOSPACE PunctuationComma comma;
			public @OPT TokenList<Java_Comment> comments;
			public Java_EnumConstant constant;
		}
	}
	
	public static class Java_EnumConstant extends TokenSequence
	{
		public @OPT @NEWLINE TokenList<Java_Annotation> annotations;
		public Java_Variable_Definition id;
		public @OPT Java_EnumInitializer initializer;
		public @OPT Java_EnumClassBody body;
		
		public static class Java_EnumClassBody extends TokenSequence
		{
			public @INDENT PunctuationLeftBrace leftBrace;
			public @OPT TokenList<Java_EnumClassBodyDeclaration> declarations;
			public @OUTDENT PunctuationRightBrace rightBrace;
		}
	}
	
	public static class Java_EnumDeclarations extends TokenSequence
	{
		public @NOSPACE PunctuationSemicolon semicolon;
		public TokenList<Java_EnumClassBodyDeclaration> body;
		
		public static class Java_EnumClassBodyDeclaration extends TokenChooser
		{
			public Java_ClassElement element;
		}
	}
	
	public static class Java_EnumInitializer extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public @OPT SeparatedList<Java_Expression,PunctuationComma> exprs;
		public PunctuationRightParen rightParen;
	}
}

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
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Java_Enum extends TokenSequence
{
	public @OPT @NEWLINE TokenList<Java_Annotation> annotations;
	public @OPT TokenList<Java_DataModifier> modifiers;
	public Java_Keyword ENUM = new Java_Keyword("enum");
	public Java_Variable_Definition id;
	public @OPT Java_ClassImplements implement;
	public @INDENT Java_Punctuation leftBrace = new Java_Punctuation('{');
	public @OPT Java_Comment comment1;

	public @OPT Java_EnumConstants constants;
	public @OPT Java_Punctuation comma = new Java_Punctuation(',');
	public @OPT TokenList<Java_Comment> comment2;
	public @OPT Java_EnumDeclarations declarations;
	public @OPT TokenList<Java_Comment> comment3;
	
	public @OPT Java_Punctuation semicolon1 = new Java_Punctuation(';');
	public @OUTDENT Java_Punctuation rightBrace = new Java_Punctuation('}');
	public @OPT TokenList<Java_Comment> comment4;
	public @OPT @NOSPACE @CURIOUS("Extra semicolon") Java_Punctuation semicolon2 = new Java_Punctuation(';');
	
	public static class Java_EnumConstants extends TokenSequence
	{
		public Java_EnumConstant constant;
		public @OPT TokenList<Java_MoreEnumConstants> more;
		public @OPT TokenList<Java_Comment> comments;
		
		public static class Java_MoreEnumConstants extends TokenSequence
		{
			public @NOSPACE Java_Punctuation comma = new Java_Punctuation(',');
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
			public @INDENT Java_Punctuation leftBrace = new Java_Punctuation('{');
			public @OPT TokenList<Java_EnumClassBodyDeclaration> declarations;
			public @OUTDENT Java_Punctuation rightBrace = new Java_Punctuation('}');
		}
	}
	
	public static class Java_EnumDeclarations extends TokenSequence
	{
		public @NOSPACE Java_Punctuation semicolon = new Java_Punctuation(';');
		public TokenList<Java_EnumClassBodyDeclaration> body;
		
		public static class Java_EnumClassBodyDeclaration extends TokenChooser
		{
			public Java_ClassElement element;
		}
	}
	
	public static class Java_EnumInitializer extends TokenSequence
	{
		public Java_Punctuation leftParen = new Java_Punctuation('(');
		public @OPT Java_Expression expr;
		public @OPT TokenList<Java_EnumMoreInitializers> moreInits;
		public Java_Punctuation rightParen = new Java_Punctuation(')');
		
		public static class Java_EnumMoreInitializers extends TokenSequence
		{
			public @NOSPACE Java_Punctuation comma = new Java_Punctuation(',');
			public Java_Expression expr;
		}
	}
}

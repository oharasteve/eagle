// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Java_Type.Java_GenericType;
import com.eagle.programmar.Java.Symbols.Java_Class_Definition;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Java_Class extends TokenSequence implements EagleRunnable, EagleScopeInterface
{
	private EagleScope _scope = new EagleScope(true);
	
	public @OPT @NEWLINE TokenList<Java_ClassModifier> modifiers;
	public @OPT @CURIOUS("Extra at sign") Java_Punctuation atSign = new Java_Punctuation('@');
	public Java_KeywordChoice classOrInterface = new Java_KeywordChoice("class", "interface");
	public Java_Class_Definition className;
	public @OPT Java_GenericType genericType;
	public @OPT Java_ClassExtends jextends;
	public @OPT Java_ClassImplements jimplements;
	public @OPT TokenList<Java_Comment> comments2;
	public @INDENT Java_Punctuation leftBrace = new Java_Punctuation('{');
	public @OPT TokenList<Java_ClassElement> elements;
	public @OUTDENT Java_Punctuation rightBrace = new Java_Punctuation('}');
	public @OPT @NEWLINE TokenList<Java_Comment> comments3;
	public @CURIOUS("Extra semicolon") @OPT Java_Punctuation semicolon = new Java_Punctuation(';');
	
	public static class Java_ClassModifier extends TokenChooser
	{
		public @FIRST @NEWLINE Java_Comment comment;
		public Java_Annotation annotation;
		public Java_KeywordChoice modifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
	}

	public static class Java_DotIdentifier extends TokenSequence
	{
		public @NOSPACE Java_Punctuation dot = new Java_Punctuation('.');
		public @NOSPACE Java_Identifier id;
	}

	public static class Java_ClassExtends extends TokenSequence
	{
		public Java_Keyword EXTENDS = new Java_Keyword("extends");
		public Java_Identifier_Reference className;
		public @OPT TokenList<Java_DotIdentifier> more;
		public @OPT Java_GenericType genericType;
		public @OPT TokenList<Java_MoreExtends> moreExtend;
		
		public static class Java_MoreExtends extends TokenSequence
		{
			public Java_Punctuation comma = new Java_Punctuation(',');
			public Java_Identifier_Reference className;
			public @OPT TokenList<Java_DotIdentifier> moreIds;
			public @OPT Java_GenericType genericType;
		}
	}
	
	public static class Java_ClassImplements extends TokenSequence
	{
		public Java_Keyword IMPLEMENTS = new Java_Keyword("implements");
		public Java_Identifier_Reference className;
		public @OPT TokenList<Java_DotIdentifier> moreIds;
		public @OPT Java_GenericType genericType;
		public @OPT TokenList<Java_MoreImplements> moreImpl;
		
		public static class Java_MoreImplements extends TokenSequence
		{
			public Java_Punctuation comma = new Java_Punctuation(',');
			public @OPT Java_Comment comment;
			public Java_Identifier_Reference className;
			public @OPT TokenList<Java_DotIdentifier> moreIds;
			public @OPT Java_GenericType genericType;
		}
	}
	
	public static class Java_ClassElement extends TokenChooser
	{
		public @FIRST @NEWLINE Java_Comment comment;
		public @NEWLINE Java_Method jmethod;
		public @NEWLINE Java_Method.Java_Constructor jconstructor;
		public @CURIOUS("Extra semicolon") Java_Punctuation semicolon = new Java_Punctuation(';');
		
		public static class Java_StaticStatement extends TokenSequence implements EagleRunnable
		{
			public @OPT Java_Keyword STATIC = new Java_Keyword("static");
			public Java_Statement statement;
			
			@Override
			public void interpret(EagleInterpreter interpreter)
			{
				statement._whichToken.tryToInterpret(interpreter);
			}
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (Java_ClassElement element : elements._elements)
		{
			element._whichToken.tryToInterpret(interpreter);
		}
	}
	
	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
//	@Override
//	public void setScope(EagleScope scope)
//	{
//		_scope = scope;
//	}
}
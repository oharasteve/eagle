// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
import com.eagle.programmar.CSharp.Directives.CSharp_RegionDirective;
import com.eagle.programmar.CSharp.Symbols.CSharp_Class_Definition;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Class extends TokenSequence
{
	public @OPT TokenList<CSharp_AnnotationOrComment> annotationOrComment;
	public @OPT TokenList<CSharp_ClassModifier> modifiers;
	public CSharp_KeywordChoice classOrInterface = new CSharp_KeywordChoice(
			"class", "interface", "struct");
	public CSharp_Class_Definition className;
	public @OPT CSharp_GenericType genericType;
	public @OPT CSharp_ExtendsOrImplements extendsOrImplements;
	public @OPT @NEWLINE TokenList<CSharp_Comment> comments1;
	public @INDENT PunctuationLeftBrace leftBrace;
	public @OPT TokenList<CSharp_ClassElement> elements;
	public @OUTDENT PunctuationRightBrace rightBrace;
	public @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
	
	public static class CSharp_AnnotationOrComment extends TokenChooser
	{
		public @CHOICE CSharp_Annotation annotation;
		public @CHOICE CSharp_Comment comment;
	}
	
	public static class CSharp_ClassModifier extends TokenSequence
	{
		public CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
	}

	public static class CSharp_DotIdentifier extends TokenSequence
	{
		public PunctuationPeriod dot;
		public CSharp_Identifier id;
	}
	
	public static class CSharp_ExtendsOrImplements extends TokenSequence
	{
		public PunctuationColon colon;
		public @OPT CSharp_NamespaceQualifer namespaceQualifier;
		public SeparatedList<CSharp_Identifier_Reference,PunctuationPeriod> className;
		public @OPT CSharp_GenericType genericType;
		public @OPT TokenList<CSharp_MoreImplements> moreImpl;
		
		public static class CSharp_NamespaceQualifer extends TokenSequence
		{
			public CSharp_Identifier_Reference nameSpace;
			public CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
		}
		
		public static class CSharp_MoreImplements extends TokenSequence
		{
			public PunctuationComma comma;
			public SeparatedList<CSharp_Identifier_Reference,PunctuationPeriod> className;
			public @OPT CSharp_GenericType genericType;
		}
	}
	
	public static class CSharp_ClassElement extends TokenChooser
	{
		public @CHOICE @NEWLINE CSharp_Comment comment;
		
		public @CHOICE @NEWLINE CSharp_Property property;
		public @CHOICE @NEWLINE CSharp_Constructor constructor;
		public @FIRST @NEWLINE CSharp_Method method;
		public @LAST @NEWLINE CSharp_Statement statement;
		public @CHOICE @NEWLINE CSharp_SubscriptOperator subscriptOperator;
				
		public @CHOICE @NEWLINE CSharp_RegionDirective region;
		
		public @CHOICE static class CSharp_StaticStatement extends TokenSequence
		{
			public @OPT CSharp_Keyword STATIC = new CSharp_Keyword("static");
			public CSharp_Statement statement;
		}
	}
}
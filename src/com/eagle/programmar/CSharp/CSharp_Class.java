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
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_Class extends TokenSequence
{
	public @OPT TokenList<CSharp_AnnotationOrComment> annotationOrComment;
	public @OPT TokenList<CSharp_ClassModifier> modifiers;
	public CSharp_KeywordChoice classOrInterface = new CSharp_KeywordChoice(
			"class", "interface", "struct");
	public CSharp_Class_Definition className;
	public @OPT CSharp_GenericType genericType;
	public @OPT CSharp_ExtendsOrImplements extendsorimplements;
	public @OPT @NEWLINE TokenList<CSharp_Comment> comments1;
	public @NEWLINE CSharp_Punctuation leftBrace = new CSharp_Punctuation('{');
	public @OPT @NEWLINE TokenList<CSharp_ClassElement> elements;
	public CSharp_Punctuation rightBrace = new CSharp_Punctuation('}');
	public @OPT @CURIOUS("Extra semicolon") CSharp_Punctuation semicolon = new CSharp_Punctuation(';');
	
	public static class CSharp_AnnotationOrComment extends TokenChooser
	{
		public CSharp_Annotation annotation;
		public CSharp_Comment comment;
	}
	
	public static class CSharp_ClassModifier extends TokenSequence
	{
		public CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
	}

	public static class CSharp_DotIdentifier extends TokenSequence
	{
		public CSharp_Punctuation dot = new CSharp_Punctuation('.');
		public CSharp_Identifier id;
	}
	
	public static class CSharp_ExtendsOrImplements extends TokenSequence
	{
		public CSharp_Punctuation colon = new CSharp_Punctuation(':');
		public @OPT CSharp_NamespaceQualifer namespaceQualifier;
		public CSharp_Identifier_Reference className;
		public @OPT TokenList<CSharp_DotIdentifier> moreIds;
		public @OPT CSharp_GenericType genericType;
		public @OPT TokenList<CSharp_MoreImplements> moreImpl;
		
		public static class CSharp_NamespaceQualifer extends TokenSequence
		{
			public CSharp_Identifier_Reference nameSpace;
			public CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
		}
		
		public static class CSharp_MoreImplements extends TokenSequence
		{
			public CSharp_Punctuation comma = new CSharp_Punctuation(',');
			public CSharp_Identifier_Reference className;
			public @OPT TokenList<CSharp_DotIdentifier> moreIds;
			public @OPT CSharp_GenericType genericType;
		}
	}
	
	public static class CSharp_ClassElement extends TokenChooser
	{
		public @NEWLINE CSharp_Comment comment;
		
		public @NEWLINE CSharp_Property property;
		public @NEWLINE CSharp_Constructor constructor;
		public @NEWLINE @FIRST CSharp_Method method;
		public @NEWLINE @LAST CSharp_Statement statement;
		public @NEWLINE CSharp_SubscriptOperator subscriptOperator;
				
		public @NEWLINE CSharp_RegionDirective region;
		
		public static class CSharp_StaticStatement extends TokenSequence
		{
			public @OPT CSharp_Keyword STATIC = new CSharp_Keyword("static");
			public CSharp_Statement statement;
		}
	}
}
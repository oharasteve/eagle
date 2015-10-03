// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_Program extends EagleLanguage
{
	public static final String NAME = "CSharp";
	
	public CSharp_Program()
	{
		super(NAME, new CSharp_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://java.sun.com/docs/books/jls/third_edition/html/";
	}
	
	public static final String[] MODIFIERS = new String[] {
		"abstract",
		"const",
		"delegate",
		"event",
		"extern",
		"final",
		"internal",
		"lock",
		"override",
		"partial",
		"private",
		"protected",
		"public",
		"readonly",
		"sealed",
		"static",
		"virtual"
	}; 

	public @NEWLINE2 TokenList<CSharp_NamespaceOrClassList> myClasses;
	
	public static class CSharp_NamespaceOrClassList extends TokenChooser
	{
		public @NEWLINE CSharp_Using importList;
		public @NEWLINE CSharp_Comment comment;
		public @NEWLINE CSharp_Namespace myNamespace;
		public @NEWLINE CSharp_Class elems;
		public @NEWLINE CSharp_Annotation annotation;
	}

	public static class CSharp_Using extends TokenSequence
	{
		public CSharp_Keyword USING = new CSharp_Keyword("using");
		public CSharp_Identifier id;
		public @OPT TokenList<CSharp_DotIdentifier> dotId;
		public @OPT CSharp_UsingEquals alternateName;
		public CSharp_Punctuation semicolon = new CSharp_Punctuation(';');

		public static class CSharp_UsingEquals extends TokenSequence
		{
			public CSharp_Punctuation equals = new CSharp_Punctuation('=');
			public CSharp_Identifier id;
			public @OPT TokenList<CSharp_DotIdentifier> dotId;
		}
		
		public static class CSharp_DotIdentifier extends TokenSequence
		{
			public CSharp_Punctuation dot = new CSharp_Punctuation('.');
			public CSharp_Identifier id;
		}
	}
	
	public static class CSharp_Namespace extends TokenSequence
	{
		public CSharp_Keyword NAMESPACE = new CSharp_Keyword("namespace");
		public CSharp_Identifier id;
		public @OPT TokenList<CSharp_DotIdentifier> dotId;
		public CSharp_Punctuation leftBrace = new CSharp_Punctuation('{');
		public TokenList<CSharp_ProgramElems> elems; 
		public CSharp_Punctuation rightBrace = new CSharp_Punctuation('}');
		
		public static class CSharp_DotIdentifier extends TokenSequence
		{
			public CSharp_Punctuation dot = new CSharp_Punctuation('.');
			public CSharp_Identifier id;
		}
	}
	
	public static class CSharp_ProgramElems extends TokenChooser
	{
		public @NEWLINE CSharp_Namespace myNamespace;
		public @NEWLINE CSharp_Using using;
		public @NEWLINE CSharp_Comment comment;
		public @NEWLINE CSharp_Class myClass;
		public @NEWLINE CSharp_Enum enumeration;
		public @NEWLINE CSharp_Method method;
	}
}

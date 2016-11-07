// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

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

	public @BLANKLINE TokenList<CSharp_NamespaceOrClassEntry> myClasses;
	
	public static class CSharp_NamespaceOrClassEntry extends TokenChooser
	{
		public @CHOICE @NEWLINE CSharp_Using importList;
		public @CHOICE @NEWLINE CSharp_Comment comment;
		public @CHOICE @NEWLINE CSharp_Namespace myNamespace;
		public @CHOICE @NEWLINE CSharp_Class elems;
		public @CHOICE @NEWLINE CSharp_Annotation annotation;
	}

	public static class CSharp_Using extends TokenSequence
	{
		public CSharp_Keyword USING = new CSharp_Keyword("using");
		public SeparatedList<CSharp_Identifier,PunctuationPeriod> id;
		public @OPT CSharp_UsingEquals alternateName;
		public PunctuationSemicolon semicolon;

		public static class CSharp_UsingEquals extends TokenSequence
		{
			public PunctuationEquals equals;
			public SeparatedList<CSharp_Identifier,PunctuationPeriod> id;
		}
	}
	
	public static class CSharp_Namespace extends TokenSequence
	{
		public CSharp_Keyword NAMESPACE = new CSharp_Keyword("namespace");
		public SeparatedList<CSharp_Identifier,PunctuationPeriod> ids;
		public PunctuationLeftBrace leftBrace;
		public TokenList<CSharp_ProgramElems> elems; 
		public PunctuationRightBrace rightBrace;
	}
	
	public static class CSharp_ProgramElems extends TokenChooser
	{
		public @CHOICE @NEWLINE CSharp_Namespace myNamespace;
		public @CHOICE @NEWLINE CSharp_Using using;
		public @CHOICE @NEWLINE CSharp_Comment comment;
		public @CHOICE @NEWLINE CSharp_Class myClass;
		public @CHOICE @NEWLINE CSharp_Enum enumeration;
		public @CHOICE @NEWLINE CSharp_Method method;
	}
}

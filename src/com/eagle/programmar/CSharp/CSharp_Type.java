// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_Type extends TokenSequence
{
	public CSharp_TypeName typeName;
	public @OPT CSharp_GenericType genericType;
	public @OPT TokenList<CSharp_ArrayType> arrayTypes;
	
	public static class CSharp_ArrayType extends TokenSequence
	{
		public CSharp_Punctuation leftBracket = new CSharp_Punctuation('[');
		public @OPT CSharp_Punctuation comma = new CSharp_Punctuation(',');
		public CSharp_Punctuation rightBracket = new CSharp_Punctuation(']');
	}
	
	public static class CSharp_GenericType extends TokenSequence
	{
		public CSharp_Punctuation lessThan = new CSharp_Punctuation('<');
		public CSharp_Type subType;
		public @OPT TokenList<CSharp_MoreTypes> moreType;
		public CSharp_Punctuation greaterThan = new CSharp_Punctuation('>');
		
		public static class CSharp_MoreTypes extends TokenSequence
		{
			public CSharp_Punctuation comma = new CSharp_Punctuation(',');
			public CSharp_Type subType;
		}
	}

	// Delay finding this one until after looking for [] and <>
	public static class CSharp_TypeName extends TokenChooser
	{
		public static class CSharp_IdList extends TokenSequence
		{
			public @OPT CSharp_NamespaceId namespaceId;
			public CSharp_Identifier_Reference typeName;
			public @OPT CSharp_ExtendsType extendsType;

			public @OPT TokenList<CSharp_MoreIds> moreIds;
			
			public static class CSharp_MoreIds extends TokenSequence
			{
				public CSharp_Punctuation dot = new CSharp_Punctuation('.');
				public CSharp_TypeName nextId;
			}
			
			public static class CSharp_NamespaceId extends TokenSequence
			{
				public CSharp_Identifier_Reference namespace;
				public CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
			}
		}

		public CSharp_KeywordChoice primitive = new CSharp_KeywordChoice(
				"void", "bool", "boolean", "byte", "short", "int",
				"long", "char", "float", "double", "string", "String", "class");
		
		public static class CSharp_GenericTypeQuestion extends TokenSequence
		{
			public CSharp_Punctuation question = new CSharp_Punctuation('?');
			public @OPT CSharp_ExtendsType extendsType;
		}
	}
	
	public static class CSharp_ExtendsType extends TokenSequence
	{
		public CSharp_Keyword EXTENDS = new CSharp_Keyword("extends");
		public CSharp_Identifier_Reference typeName;
	}
}

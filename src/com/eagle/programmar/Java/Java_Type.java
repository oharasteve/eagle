// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Java_Type extends TokenSequence
{
	public @OPT Java_Comment comment;
	public Java_TypeName typeName;
	public @OPT Java_GenericType genericType;
	public @OPT TokenList<Java_ArrayType> arrayTypes;
	
	public static class Java_ArrayType extends TokenSequence
	{
		public @NOSPACE PunctuationLeftBracket leftBracket;
		public @NOSPACE PunctuationRightBracket rightBracket;
	}
	
	public static class Java_GenericType extends TokenSequence
	{
		public Java_Punctuation lessThan = new Java_Punctuation('<');
		public @OPT Java_Type subType1;
		public @OPT Java_Punctuation emptySubscript = new Java_Punctuation("[]");
		public @OPT TokenList<Java_MoreTypes> moreType;
		public Java_Punctuation greaterThan = new Java_Punctuation('>');
		
		public static class Java_MoreTypes extends TokenSequence
		{
			public PunctuationComma comma;
			public Java_Type subType2;
		}
	}

	// Delay finding this one until after looking for [] and <>
	public static class Java_TypeName extends TokenChooser
	{
		public static class Java_IdList extends TokenSequence
		{
			public Java_Identifier_Reference typeName;
			public @OPT Java_ExtendsType extendsType;
			public @OPT TokenList<Java_MoreIds> moreIds;
			public @OPT Java_ExtendsMultiple multiple;
			
			public static class Java_MoreIds extends TokenSequence
			{
				public @NOSPACE PunctuationPeriod dot;
				public @NOSPACE Java_TypeName nextId;
			}
			
			public static class Java_ExtendsMultiple extends TokenSequence
			{
				public Java_Punctuation ampersand = new Java_Punctuation('&');
				public Java_Identifier_Reference typeName;
				public @OPT Java_ExtendsType extendsType;
				public @OPT TokenList<Java_MoreIds> moreIds;
			}
		}

		public Java_KeywordChoice primitive = new Java_KeywordChoice(
				"void", "boolean", "byte", "short", "int",
				"long", "char", "float", "double", "String", "class");
		
		public static class Java_GenericTypeQuestion extends TokenSequence
		{
			public Java_Punctuation question = new Java_Punctuation('?');
			public @OPT Java_ExtendsType extendsType;
		}
	}
	
	public static class Java_ExtendsType extends TokenSequence
	{
		public Java_KeywordChoice EXTENDS = new Java_KeywordChoice("extends", "super");
		public Java_Identifier_Reference typeName;
	}
}

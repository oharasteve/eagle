// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Java_Variable extends TokenSequence
{
//	public SeparatedList<Java_VariableIdentifier, Java_Punctuation> ids =
//			new SeparatedList<Java_VariableIdentifier, Java_Punctuation>(
//					Java_VariableIdentifier.class, Java_Punctuation.class, '.');
	
	public Java_VariableIdentifier firstId;
	public @OPT TokenList<Java_DotIdentifier> moreIds;
	public @OPT TokenList<Java_Subscript> subscript;
	
	public static class Java_VariableIdentifier extends TokenChooser
	{
		public Java_KeywordChoice THIS = new Java_KeywordChoice("this", "class", "super");
		public Java_Identifier_Reference id;
		
		public static class Java_CastedVariable extends TokenSequence
		{
			public Java_Punctuation leftParen1 = new Java_Punctuation('(');
			public Java_Punctuation leftParen2 = new Java_Punctuation('(');
			public Java_Type jtype;
			public Java_Punctuation rightParen1 = new Java_Punctuation(')');
			public Java_Identifier_Reference id;
			public Java_Punctuation rightParen2 = new Java_Punctuation(')');
		}
	}

	public static class Java_DotIdentifier extends TokenSequence
	{
		public @NOSPACE Java_Punctuation dot = new Java_Punctuation('.');
		public @NOSPACE Java_VariableIdentifier id;
	}
}

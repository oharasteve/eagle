// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_Variable extends TokenSequence
{
//	public SeparatedList<Java_VariableIdentifier, Java_Punctuation> ids =
//			new SeparatedList<Java_VariableIdentifier, Java_Punctuation>(
//					Java_VariableIdentifier.class, Java_Punctuation.class, '.');
	
	public SeparatedList<Java_VariableIdentifier,PunctuationPeriod> id;
	public @OPT TokenList<Java_Subscript> subscript;
	
	public static class Java_VariableIdentifier extends TokenChooser
	{
		public Java_KeywordChoice THIS = new Java_KeywordChoice("this", "class", "super");
		public Java_Identifier_Reference id;
		
		public static class Java_CastedVariable extends TokenSequence
		{
			public PunctuationLeftParen leftParen1;
			public PunctuationLeftParen leftParen2;
			public Java_Type jtype;
			public PunctuationRightParen rightParen1;
			public Java_Identifier_Reference id;
			public PunctuationRightParen rightParen2;
		}
	}
}

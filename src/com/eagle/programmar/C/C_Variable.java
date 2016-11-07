// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_Variable extends TokenSequence
{
	public @OPT TokenList<C_VariableStar> stars;
	public C_VariableIdentifier firstId;
	public @OPT TokenList<C_ExtendedIdentifier> moreIds;
	public @OPT TokenList<C_Subscript> subscript;
	
	public static class C_VariableStar extends TokenSequence
	{
		public PunctuationStar star;
	}
	
	public static class C_VariableIdentifier extends TokenChooser
	{
		public @CHOICE C_Identifier_Reference id;
		
		public @CHOICE static class C_CastedVariable extends TokenSequence
		{
			public PunctuationLeftParen leftParen1;
			public PunctuationLeftParen leftParen2;
			public C_Type jtype;
			public PunctuationRightParen rightParen1;
			public C_Identifier_Reference id;
			public PunctuationRightParen rightParen2;
		}

		public @CHOICE static class C_IndirectVariable extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public TokenList<C_VariableStar> stars;
			public C_Identifier_Reference id;
			public PunctuationRightParen rightParen;
		}

		public @CHOICE static class C_SubscriptedVariable extends TokenSequence
		{
			public C_Identifier_Reference id;
			public TokenList<C_Subscript> subscripts;
		}
	}

	public static class C_ExtendedIdentifier extends TokenChooser
	{
		public @CHOICE static class C_DotIdentifier extends TokenSequence
		{
			public @NOSPACE PunctuationPeriod dot;
			public @NOSPACE C_Identifier_Reference id;
		}
		
		public @CHOICE static class C_ArrowIdentifier extends TokenSequence
		{
			public @NOSPACE C_Punctuation arrow = new C_Punctuation("->");
			public @NOSPACE C_Identifier_Reference id;
		}
		
		public @CHOICE static class C_ColonColonIdentifier extends TokenSequence
		{
			public @NOSPACE C_Punctuation colonColon = new C_Punctuation("::");
			public @NOSPACE C_Identifier_Reference id;
		}
	}
}

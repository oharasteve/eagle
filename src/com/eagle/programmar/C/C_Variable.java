// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class C_Variable extends TokenSequence
{
	public @OPT TokenList<C_VariableStar> stars;
	public C_VariableIdentifier firstId;
	public @OPT TokenList<C_ExtendedIdentifier> moreIds;
	public @OPT TokenList<C_Subscript> subscript;
	
	public static class C_VariableStar extends TokenSequence
	{
		public C_Punctuation star = new C_Punctuation('*');
	}
	
	public static class C_VariableIdentifier extends TokenChooser
	{
		public C_Identifier_Reference id;
		
		public static class C_CastedVariable extends TokenSequence
		{
			public C_Punctuation leftParen1 = new C_Punctuation('(');
			public C_Punctuation leftParen2 = new C_Punctuation('(');
			public C_Type jtype;
			public C_Punctuation rightParen1 = new C_Punctuation(')');
			public C_Identifier_Reference id;
			public C_Punctuation rightParen2 = new C_Punctuation(')');
		}

		public static class C_IndirectVariable extends TokenSequence
		{
			public C_Punctuation leftParen = new C_Punctuation('(');
			public TokenList<C_VariableStar> stars;
			public C_Identifier_Reference id;
			public C_Punctuation rightParen = new C_Punctuation(')');
		}

		public static class C_SubscriptedVariable extends TokenSequence
		{
			public C_Identifier_Reference id;
			public TokenList<C_Subscript> subscripts;
		}
	}

	public static class C_ExtendedIdentifier extends TokenChooser
	{
		public static class C_DotIdentifier extends TokenSequence
		{
			public @NOSPACE C_Punctuation dot = new C_Punctuation('.');
			public @NOSPACE C_Identifier_Reference id;
		}
		
		public static class C_ArrowIdentifier extends TokenSequence
		{
			public @NOSPACE C_Punctuation arrow = new C_Punctuation("->");
			public @NOSPACE C_Identifier_Reference id;
		}
		
		public static class C_ColonColonIdentifier extends TokenSequence
		{
			public @NOSPACE C_Punctuation colonColon = new C_Punctuation("::");
			public @NOSPACE C_Identifier_Reference id;
		}
	}
}

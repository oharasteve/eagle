// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_Variable extends TokenSequence
{
	public CSharp_VariableIdentifier firstId;
	public @OPT TokenList<CSharp_DotIdentifier> moreIds;
	public @OPT TokenList<CSharp_Subscript> subscript;
	
	public static class CSharp_VariableIdentifier extends TokenChooser
	{
		public CSharp_KeywordChoice builtIn = new CSharp_KeywordChoice("this", "base", "class");
		public CSharp_Identifier_Reference id;
		
		public static class CSharp_CastedVariable extends TokenSequence
		{
			public CSharp_Punctuation leftParen1 = new CSharp_Punctuation('(');
			public CSharp_Punctuation leftParen2 = new CSharp_Punctuation('(');
			public CSharp_Type cstype;
			public CSharp_Punctuation rightParen1 = new CSharp_Punctuation(')');
			public CSharp_Identifier_Reference id;
			public CSharp_Punctuation rightParen2 = new CSharp_Punctuation(')');
		}
	}

	public static class CSharp_DotIdentifier extends TokenSequence
	{
		public @NOSPACE CSharp_Punctuation dot = new CSharp_Punctuation('.');
		public @NOSPACE CSharp_VariableIdentifier id;
	}
}

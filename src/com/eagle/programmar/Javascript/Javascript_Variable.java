// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Javascript_Variable extends TokenSequence
{
	public Javascript_VariableIdentifier firstId;
	public @OPT TokenList<Javascript_DotIdentifier> moreIds;
	public @OPT TokenList<Javascript_Subscript> subscript;
	
	public static class Javascript_VariableIdentifier extends TokenChooser
	{
		public Javascript_Identifier_Reference id;
		public Javascript_KeywordChoice THIS = new Javascript_KeywordChoice("this", "class");
		public @LAST Javascript_PunctuationChoice dollar = new Javascript_PunctuationChoice("$", "_");
		
		public static class Javascript_CastedVariable extends TokenSequence
		{
			public Javascript_Punctuation leftParen1 = new Javascript_Punctuation('(');
			public Javascript_Punctuation leftParen2 = new Javascript_Punctuation('(');
			public Javascript_Type jtype;
			public Javascript_Punctuation rightParen1 = new Javascript_Punctuation(')');
			public Javascript_Identifier_Reference id;
			public Javascript_Punctuation rightParen2 = new Javascript_Punctuation(')');
		}
	}

	public static class Javascript_DotIdentifier extends TokenSequence
	{
		public @NOSPACE Javascript_Punctuation dot = new Javascript_Punctuation('.');
		public @NOSPACE Javascript_VariableIdentifier id;
	}
}

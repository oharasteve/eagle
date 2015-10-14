// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Arguments extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public @OPT Delphi_OneArgument firstArg;
	public @OPT TokenList<Delphi_MoreArguments> moreArgs;
	public PunctuationRightParen rightParen;
	
	public static class Delphi_OneArgument extends TokenSequence
	{
		public @OPT Delphi_Keyword VAR = new Delphi_Keyword("Var");
		public SeparatedList<Delphi_Variable,PunctuationComma> names;
		public PunctuationColon colon;
		public Delphi_Type type;
		public @OPT Delphi_InitialValue initialValue;
	}

	public static class Delphi_MoreArguments extends TokenSequence
	{
		public PunctuationSemicolon semicolon;
		public @OPT Delphi_OneArgument nextArg;
	}
	
	public static class Delphi_InitialValue extends TokenSequence
	{
		public PunctuationEquals equals;
		public Delphi_Expression expr;
	}
}

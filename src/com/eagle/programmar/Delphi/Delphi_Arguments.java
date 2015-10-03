// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Arguments extends TokenSequence
{
	public Delphi_Punctuation leftParen = new Delphi_Punctuation('(');
	public @OPT Delphi_OneArgument firstArg;
	public @OPT TokenList<Delphi_MoreArguments> moreArgs;
	public Delphi_Punctuation rightParen = new Delphi_Punctuation(')');
	
	public static class Delphi_OneArgument extends TokenSequence
	{
		public @OPT Delphi_Keyword VAR = new Delphi_Keyword("Var");
		public Delphi_Variable name;
		public @OPT TokenList<Delphi_MoreVariables> moreNames;
		public Delphi_Punctuation colon = new Delphi_Punctuation(':');
		public Delphi_Type type;
		public @OPT Delphi_InitialValue initialValue;
	}
	
	public static class Delphi_MoreVariables extends TokenSequence
	{
		public Delphi_Punctuation comma = new Delphi_Punctuation(',');
		public Delphi_Variable name;
	}

	public static class Delphi_MoreArguments extends TokenSequence
	{
		public Delphi_Punctuation semicolon = new Delphi_Punctuation(';');
		public @OPT Delphi_OneArgument nextArg;
	}
	
	public static class Delphi_InitialValue extends TokenSequence
	{
		public Delphi_Punctuation equals = new Delphi_Punctuation('=');
		public Delphi_Expression expr;
	}
}

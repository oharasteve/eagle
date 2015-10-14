// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Command.CMD_Statement;
import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Identifier;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Literal;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_PercentVariable;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_For_Statement extends TokenSequence
{
	public @DOC("for.mspx") CMD_Keyword FOR = new CMD_Keyword("for");
	public @OPT TokenList<CMD_For_Option> opts;
	public CMD_PercentVariable var;
	public CMD_Keyword IN = new CMD_Keyword("in");
	public PunctuationLeftParen leftParen;
	public CMD_Argument arg;
	public @OPT TokenList<CMD_For_More_Params> moreParams;
	public PunctuationRightParen rightParen;
	public CMD_Keyword DO = new CMD_Keyword("do");
	public @OPT CMD_Punctuation at = new CMD_Punctuation('@');
	public CMD_Statement stmt;
	
	public static class CMD_For_More_Params extends TokenSequence
	{
		public @OPT PunctuationComma comma;
		public CMD_Argument arg;
	}
	
	public static class CMD_For_Option extends TokenChooser
	{
		public static class CMD_For_Option_D extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword D = new CMD_Keyword("d");
		}

		public static class CMD_For_Option_F extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword F = new CMD_Keyword("f");
			public @OPT CMD_Literal opts;
		}
	
		public static class CMD_For_Option_L extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword L = new CMD_Keyword("l");
		}

		public static class CMD_For_Option_R extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword R = new CMD_Keyword("r");
			public @OPT CMD_For_R forR;
			
			public static class CMD_For_R extends TokenChooser
			{
				public CMD_Literal dir;
				
				public CMD_Identifier dirName;
				
				public static class CMD_For_R_pct extends TokenSequence
				{
					public CMD_Punctuation percent = new CMD_Punctuation('%');
					public CMD_Number number;
				}
			}
		}
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 9, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class CMD_Grep_Statement extends TokenSequence
{
	public CMD_Keyword GREP = new CMD_Keyword("grep");
	public TokenList<CMD_Grep_Parameter> params;
	
	public static class CMD_Grep_Parameter extends TokenChooser
	{
		public CMD_Argument srcFile;
		
		public static class CMD_Grep_Option_e extends TokenSequence
		{
			public CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public CMD_Keyword E = new CMD_Keyword("e");
			public CMD_Argument pattern;
		}

		public static class CMD_Grep_Option_H extends TokenSequence
		{
			public CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public CMD_Keyword H = new CMD_Keyword("H");
		}

		public static class CMD_Grep_Option_i extends TokenSequence
		{
			public CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public CMD_Keyword I = new CMD_Keyword("i");
		}

		public static class CMD_Grep_Option_l extends TokenSequence
		{
			public CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public CMD_Keyword L = new CMD_Keyword("l");
		}

		public static class CMD_Grep_Option_n extends TokenSequence
		{
			public CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public CMD_Keyword N = new CMD_Keyword("n");
		}

		public static class CMD_Grep_Option_v extends TokenSequence
		{
			public CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public CMD_Keyword V = new CMD_Keyword("v");
		}

		public static class CMD_Grep_Option_w extends TokenSequence
		{
			public PunctuationHyphen minus;
			public CMD_Keyword W = new CMD_Keyword("w");
		}
	}
}

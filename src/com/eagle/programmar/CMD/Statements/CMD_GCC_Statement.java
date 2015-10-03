// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMD_GCC_Statement extends TokenSequence
{
	public CMD_Keyword GCC = new CMD_Keyword("gcc");
	public TokenList<CMD_GCC_Parameter> params;
	
	public static class CMD_GCC_Parameter extends TokenChooser
	{
		public CMD_Argument srcFile;
		
		public static class CMD_GCC_Option_O extends TokenSequence
		{
			public CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public CMD_Keyword O = new CMD_Keyword("o");
			public CMD_Argument tgtFile;
		}
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD;

import com.eagle.programmar.CMD.Symbols.CMD_Identifier_Reference;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class CMD_Expression extends TokenSequence
{
	public CMD_Expr_Item item1;
	public CMD_Punctuation plus = new CMD_Punctuation('+');
	public CMD_Expr_Item item2;
	
	public static class CMD_Expr_Item extends TokenChooser
	{
		public CMD_Number number;
		public CMD_Identifier_Reference var;
	}
}

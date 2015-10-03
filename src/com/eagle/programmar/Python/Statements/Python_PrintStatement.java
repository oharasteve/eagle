// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_PrintStatement extends TokenSequence
{
	public Python_Keyword PRINT = new Python_Keyword("print");
	public @OPT Python_Punctuation greaterGreater = new Python_Punctuation(">>");
	public @OPT Python_Expression expr;
	public @OPT TokenList<Python_MoreExpressions> more;
	public @OPT @CURIOUS("Extra comma") Python_Punctuation comma = new Python_Punctuation(',');
	
	public static class Python_MoreExpressions extends TokenSequence
	{
		public Python_Punctuation comma = new Python_Punctuation(',');
		public Python_Expression expr;
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 7, 2014

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_ExecStatement extends TokenSequence
{
	public Python_Keyword EXEC = new Python_Keyword("exec");
	public Python_Expression expr;
	public @OPT TokenList<Python_ExecMoreArgs> more;
	
	public static class Python_ExecMoreArgs extends TokenSequence
	{
		public Python_Punctuation comma = new Python_Punctuation(',');
		public Python_Expression expr;
	}
}

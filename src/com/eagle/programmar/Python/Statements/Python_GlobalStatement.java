// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Symbols.Python_Variable_Definition;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_GlobalStatement extends TokenSequence
{
	public Python_Keyword GLOBAL = new Python_Keyword("global");
	public Python_Variable_Definition var;
	public @OPT TokenList<Python_MoreGlobals> more;
	
	public static class Python_MoreGlobals extends TokenSequence
	{
		public Python_Punctuation comma = new Python_Punctuation(',');
		public Python_Variable_Definition var;
	}
}

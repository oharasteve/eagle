// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Python_YieldStatement extends TokenSequence
{
	public Python_Keyword YIELD = new Python_Keyword("yield");
	public @OPT Python_YieldWhat yieldWhat;
	
	public static class Python_YieldWhat extends TokenChooser
	{
		public @CHOICE Python_ExpressionList expressionList;
		
		public @CHOICE static class Python_YieldFrom extends TokenSequence
		{
			public Python_Keyword FROM = new Python_Keyword("from");
			public Python_Expression condition;
		}
	}
}

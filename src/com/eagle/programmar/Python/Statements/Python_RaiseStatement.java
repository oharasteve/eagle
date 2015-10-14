// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_RaiseStatement extends TokenSequence
{
	public Python_Keyword RAISE = new Python_Keyword("raise");
	public @OPT SeparatedList<Python_RaiseCondition,PunctuationComma> raiseConditions;
	
	public static class Python_RaiseCondition extends TokenSequence
	{
		public Python_Expression condition;
		public @OPT Python_RaiseFrom raiseFrom;
		
		public static class Python_RaiseFrom extends TokenSequence
		{
			public Python_Keyword FROM = new Python_Keyword("from");
			public Python_Expression condition;
		}
	}
}

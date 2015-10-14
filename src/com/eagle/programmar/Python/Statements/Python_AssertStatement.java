// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 17, 2014

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_AssertStatement extends TokenSequence
{
	public Python_Keyword ASSERT = new Python_Keyword("assert");
	public Python_Expression condition;
	public @OPT Python_AssertMessage message;
	
	public static class Python_AssertMessage extends TokenSequence
	{
		public PunctuationComma comma;
		public Python_Expression message;
	}
}
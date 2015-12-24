// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Statement;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_WithStatement extends TokenSequence
{
	public Python_Keyword WITH = new Python_Keyword("with");
	public SeparatedList<Python_WithItem,PunctuationComma> withItems;
	public PunctuationColon colon;
	public Python_EndOfLine eoln;
	public TokenList<Python_Statement> statements;
	
	public static class Python_WithItem extends TokenSequence
	{
		public Python_Expression condition;
		public @OPT Python_WithItemAs withItemAs;
		
		public static class Python_WithItemAs extends TokenSequence
		{
			public Python_Keyword AS = new Python_Keyword("as");
			public Python_Expression expression;
		}
	}
}

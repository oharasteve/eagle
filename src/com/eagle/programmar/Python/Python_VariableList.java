// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2014

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_VariableList extends TokenSequence
{
	public SeparatedList<Python_Variable_or_List,PunctuationComma> vars;
	public @OPT PunctuationComma comma;

	public static class Python_Variable_or_List extends TokenChooser
	{
		public @CHOICE Python_Variable var;
		
		public @CHOICE static class Python_Var_List extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_VariableList more;
			public PunctuationRightParen rightParen;
		}
	}
}

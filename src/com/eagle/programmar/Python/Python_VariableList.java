// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2014

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_VariableList extends TokenSequence
{
	public Python_Variable_or_List var;
	public @OPT TokenList<Python_MoreVariableList> moreVariables;

	public static class Python_Variable_or_List extends TokenChooser
	{
		public Python_Variable var;
		
		public static class Python_Var_List extends TokenSequence
		{
			public Python_Punctuation leftParen = new Python_Punctuation('(');
			public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_VariableList more;
			public Python_Punctuation rightParen = new Python_Punctuation(')');
		}
	}
	
	public static class Python_MoreVariableList extends TokenSequence
	{
		public Python_Punctuation comma = new Python_Punctuation(',');
		public @OPT Python_Variable_or_List var;
	}
	
}

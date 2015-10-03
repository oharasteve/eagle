// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_Assignment extends TokenSequence
{
	public Python_VariableList varList;
	public Python_PunctuationChoice operator = new Python_PunctuationChoice(
			"=", "+=", "-=", "*=", "/=", "%=", "&=", "|=", "^=", "<<=", ">>=", "**=", "//=");
	public Python_Expression expr;
	public @OPT TokenList<Python_MoreAsgExpressions> moreExpressions;
	public @OPT Python_Comment comment;
	
	public static class Python_MoreAsgExpressions extends TokenSequence
	{
		public Python_Punctuation comma = new Python_Punctuation(',');
		public @OPT Python_Expression expr;
	}
}

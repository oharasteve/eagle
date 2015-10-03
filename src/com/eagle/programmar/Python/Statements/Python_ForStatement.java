// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Python_Statement.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.TokenSequence;

public class Python_ForStatement extends TokenSequence
{
	public Python_Keyword FOR = new Python_Keyword("for");
	public Python_VariableList varList;
	public Python_Keyword IN = new Python_Keyword("in");
	public Python_ExpressionList expressionList;
	public Python_Punctuation colon = new Python_Punctuation(':');
	public @OPT Python_Comment comment;
	public Python_SingleOrMultiLineStatement forType;
	public @OPT Python_ForElse forElseStatement;

	public static class Python_ForElse extends TokenSequence
	{
		public Python_StartOfLine soln = new Python_StartOfLine();
		public Python_Keyword ELSE = new Python_Keyword("else");
		public Python_Punctuation colon = new Python_Punctuation(':');
		public Python_SingleOrMultiLineStatement doWhat;
	}
}

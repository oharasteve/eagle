// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenSequence;

public class Python_ReturnStatement extends TokenSequence
{
	public Python_Keyword RETURN = new Python_Keyword("return");
	public @OPT Python_ExpressionList expressionList;
	public @OPT Python_Comment comment;
}

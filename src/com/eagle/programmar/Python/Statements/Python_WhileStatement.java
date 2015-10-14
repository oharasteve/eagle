// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_WhileStatement extends TokenSequence
{
	public Python_Keyword WHILE = new Python_Keyword("while");
	public Python_Expression condition;
	public PunctuationColon colon;
	public @OPT Python_Comment comment;
	public TokenList<Python_EndOfLine> eoln;
	public TokenList<Python_Statement> statements;
	public @OPT Python_WhileElse whileElse;
	
	public static class Python_WhileElse extends TokenSequence
	{
		public Python_StartOfLine soln = new Python_StartOfLine();
		public Python_Keyword ELSE = new Python_Keyword("else");
		public PunctuationColon colon;
		public Python_SingleOrMultiLineStatement doWhat;
	}
}

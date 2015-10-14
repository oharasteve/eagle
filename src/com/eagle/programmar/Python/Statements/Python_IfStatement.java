// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Statement.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_IfStatement extends TokenSequence
{
	public Python_Keyword IF = new Python_Keyword("if");
	public Python_Expression condition;
	public PunctuationColon colon;
	public Python_SingleOrMultiLineStatement ifType;
	public @OPT TokenList<Python_IfElif> ifElif;
	public @OPT Python_IfElse ifElse;
	public @OPT TokenList<Python_Comment> comments;
	
	public static class Python_IfElif extends TokenSequence
	{
		public Python_StartOfLine soln = new Python_StartOfLine();
		public Python_Keyword ELIF = new Python_Keyword("elif");
		public Python_Expression condition;
		public PunctuationColon colon;
		public Python_SingleOrMultiLineStatement elifStatement;
	}
		
	public static class Python_IfElse extends TokenSequence
	{
		public Python_StartOfLine soln = new Python_StartOfLine();
		public Python_Keyword ELSE = new Python_Keyword("else");
		public PunctuationColon colon;
		public Python_SingleOrMultiLineStatement ifElseStatement;
	}
}

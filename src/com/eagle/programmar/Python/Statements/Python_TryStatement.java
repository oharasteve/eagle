// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Statement.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_TryStatement extends TokenSequence
{
	public Python_StartOfLine soln;
	public Python_Keyword TRY = new Python_Keyword("try");
	public PunctuationColon colon;
	public @OPT Python_Comment comment;
	public Python_SingleOrMultiLineStatement tryType;
	public @OPT TokenList<Python_TryExcept> tryExcept;
	public @OPT Python_TryElse tryElse;
	public @OPT Python_TryFinally tryFinally;
	
	public static class Python_TryExcept extends TokenSequence
	{
		public Python_Keyword EXCEPT = new Python_Keyword("except");
		public @OPT SeparatedList<Python_TryExceptClause,PunctuationComma> tryExceptClauses;
		public PunctuationColon colon;
		public Python_SingleOrMultiLineStatement exceptWhat;
		
		public static class Python_TryExceptClause extends TokenSequence
		{
			public Python_Expression condition;
			public @OPT Python_TryExceptAs tryExceptAs;
			
			public static class Python_TryExceptAs extends TokenSequence
			{
				public Python_Keyword AS = new Python_Keyword("as");
				public Python_Variable var;
			}
		}
	}

	public static class Python_TryElse extends TokenSequence
	{
		public Python_StartOfLine soln = new Python_StartOfLine();
		public Python_Keyword ELSE = new Python_Keyword("else");
		public PunctuationColon colon;
		public Python_SingleOrMultiLineStatement elseWhat;
	}

	public static class Python_TryFinally extends TokenSequence
	{
		public Python_StartOfLine soln = new Python_StartOfLine();
		public Python_Keyword FINALLY = new Python_Keyword("finally");
		public PunctuationColon colon;
		public Python_SingleOrMultiLineStatement finallyWhat;
	}
}

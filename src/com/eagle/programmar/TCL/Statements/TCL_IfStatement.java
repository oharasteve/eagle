// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Statements;

import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Statement;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.tokens.TokenSequence;

public class TCL_IfStatement extends TokenSequence
{
	public TCL_Keyword IF = new TCL_Keyword("if");
	public TCL_Punctuation leftBrace = new TCL_Punctuation('{');
	public TCL_Expression condition;
	public TCL_Punctuation rightBrace = new TCL_Punctuation('}');
	public TCL_Statement stmt;
	public @OPT TCL_ElseClause elseClause;
	
	public static class TCL_ElseClause extends TokenSequence
	{
		public TCL_Keyword ELSE = new TCL_Keyword("else");
		public TCL_Statement stmt;
	}
}

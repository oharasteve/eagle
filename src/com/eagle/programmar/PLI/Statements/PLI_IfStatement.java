// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Statement;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.TokenList;

public class PLI_IfStatement extends TokenSequence
{
	public @OPT PLI_Label label;
	public @DOC("7.27") PLI_Keyword IF = new PLI_Keyword("IF");
	public PLI_Expression cond;
	public PLI_Keyword THEN = new PLI_Keyword("THEN");
	public @OPT TokenList<PLI_Comment> comment1;
	public PLI_Statement thenAction;
	public @OPT TokenList<PLI_Comment> comment2;
	public @OPT PLI_Else elseClause;
	public @OPT PLI_Keyword ENDIF = new PLI_Keyword("END-IF");
	
	public static class PLI_Else extends TokenSequence
	{
		public PLI_Keyword ELSE = new PLI_Keyword("ELSE");
		public @OPT TokenList<PLI_Comment> comment3;
		public PLI_Statement elseAction;
		public @OPT TokenList<PLI_Comment> comment4;
	}
}

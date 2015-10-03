// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_If_Statement extends TokenSequence
{
	public Delphi_Keyword IF = new Delphi_Keyword("If");
	public Delphi_Expression cond;
	public Delphi_Keyword THEN = new Delphi_Keyword("Then");
	public @OPT TokenList<Delphi_Comment> comments;
	public Delphi_Statement stmt;
	public @OPT Delphi_If_Else ifElse;
	
	public static class Delphi_If_Else extends TokenSequence
	{
		public @OPT TokenList<Delphi_Comment> comments;
		public Delphi_Keyword ELSE = new Delphi_Keyword("Else");
		public Delphi_Statement stmt;
	}
}

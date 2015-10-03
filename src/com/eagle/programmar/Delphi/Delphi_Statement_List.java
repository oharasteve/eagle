// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Statement_List extends TokenSequence
{
	public @OPT TokenList<Delphi_Comment> comments1;
	public Delphi_Statement stmt;
	public @OPT TokenList<Delphi_MoreStatements> stmts;
	public @OPT TokenList<Delphi_Comment> comments2;
	public @OPT Delphi_Punctuation semicolon = new Delphi_Punctuation(';');
	public @OPT TokenList<Delphi_Comment> comments3;
	
	public static class Delphi_MoreStatements extends TokenSequence
	{
		public Delphi_Punctuation semicolon = new Delphi_Punctuation(';');
		public @OPT TokenList<Delphi_Comment> comments4;
		public Delphi_Statement stmt;
		public @OPT TokenList<Delphi_Comment> comments5;
	}
}

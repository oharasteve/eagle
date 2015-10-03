// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement;
import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_For_Statement extends TokenSequence
{
	public Delphi_Keyword FOR = new Delphi_Keyword("For");
	public Delphi_Identifier_Reference var;
	public Delphi_Punctuation colonEquals = new Delphi_Punctuation(":=");
	public Delphi_Expression from;
	public Delphi_KeywordChoice TO_DOWNTO = new Delphi_KeywordChoice("To", "DownTo");
	public Delphi_Expression to;
	public Delphi_Keyword DO = new Delphi_Keyword("Do");
	public @OPT TokenList<Delphi_Comment> comments;
	public Delphi_Statement stmt;
}

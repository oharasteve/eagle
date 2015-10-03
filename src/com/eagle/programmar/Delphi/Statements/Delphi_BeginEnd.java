// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Statement_List;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_BeginEnd extends TokenSequence
{
	public Delphi_Keyword BEGIN = new Delphi_Keyword("Begin");
	public @OPT TokenList<Delphi_Comment> comments;
	public @OPT Delphi_Statement_List statements;
	public Delphi_Keyword END = new Delphi_Keyword("End");
}

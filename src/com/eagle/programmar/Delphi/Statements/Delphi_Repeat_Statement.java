// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement_List;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;

public class Delphi_Repeat_Statement extends TokenSequence
{
	public Delphi_Keyword REPEAT = new Delphi_Keyword("Repeat");
	public Delphi_Statement_List statements;
	public Delphi_Keyword UNTIL = new Delphi_Keyword("Until");
	public Delphi_Expression expr;
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;

public class Delphi_While_Statement extends TokenSequence
{
	public Delphi_Keyword WHILE = new Delphi_Keyword("While");
	public Delphi_Expression cond;
	public Delphi_Keyword DO = new Delphi_Keyword("Do");
	public Delphi_Statement stmt;
}

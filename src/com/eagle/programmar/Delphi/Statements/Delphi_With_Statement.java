// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;

public class Delphi_With_Statement extends TokenSequence
{
	public Delphi_Keyword WITH = new Delphi_Keyword("With");
	public Delphi_Expression expr;
	public Delphi_Keyword DO = new Delphi_Keyword("Do");
	public Delphi_Statement stmt;
}

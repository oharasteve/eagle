// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Parameter_List;
import com.eagle.programmar.Delphi.Delphi_Variable;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;

public class Delphi_Inherited_Statement extends TokenSequence
{
	public Delphi_Keyword INHERITED = new Delphi_Keyword("Inherited");
	public @OPT Delphi_Variable name;
	public @OPT Delphi_Parameter_List params;
}

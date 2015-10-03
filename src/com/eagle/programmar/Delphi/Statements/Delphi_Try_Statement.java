// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 16, 2013

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Statement_List;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;

public class Delphi_Try_Statement extends TokenSequence
{
	public Delphi_Keyword TRY = new Delphi_Keyword("Try");
	public Delphi_Statement_List statements1;
	public @OPT Delphi_Keyword EXCEPT = new Delphi_Keyword("Except");
	public @OPT Delphi_Statement_List statements2;
	public @OPT Delphi_Keyword FINALLY = new Delphi_Keyword("Finally");
	public @OPT Delphi_Statement_List statements3;
	public Delphi_Keyword END = new Delphi_Keyword("End");
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Natural_Label;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_GetStatement extends TokenSequence
{
	public @OPT Natural_Label label;
	public @DOC("sm/get.htm") Natural_Keyword GET = new Natural_Keyword("GET");
	public @OPT Natural_Keyword TRANSACTION = new Natural_Keyword("TRANSACTION");
	public @OPT Natural_Keyword DATA = new Natural_Keyword("DATA");
	public TokenList<Natural_Expression> exprs;
}

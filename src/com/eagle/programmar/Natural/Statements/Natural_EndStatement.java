// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_EndStatement extends TokenSequence
{
	public @DOC("sm/endtrans.htm") Natural_Keyword END = new Natural_Keyword("END");
	public @OPT Natural_Keyword OF = new Natural_Keyword("OF");
	public Natural_Keyword TRANSACTION = new Natural_Keyword("TRANSACTION");
	public @OPT TokenList<Natural_Variable> vars;
	public @OPT Natural_Literal literal;
}

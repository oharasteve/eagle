// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_CompressStatement extends TokenSequence
{
	public @DOC("sm/compress.htm") Natural_Keyword COMPRESS = new Natural_Keyword("COMPRESS");
	public TokenList<Natural_Expression> expr;
	public Natural_Keyword INTO = new Natural_Keyword("INTO");
	public Natural_Variable var;
}

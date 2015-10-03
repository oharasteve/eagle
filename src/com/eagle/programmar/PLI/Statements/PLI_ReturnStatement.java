// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenSequence;

public class PLI_ReturnStatement extends TokenSequence
{
	public @DOC("7.48") PLI_Keyword RETURN = new PLI_Keyword("RETURN");
	public @OPT PLI_Expression expr;
	public PLI_Punctuation semiColon = new PLI_Punctuation(';');
}

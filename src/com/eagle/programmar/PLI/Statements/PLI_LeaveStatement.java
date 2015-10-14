// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_LeaveStatement extends TokenSequence
{
	public @DOC("7.30") PLI_Keyword LEAVE = new PLI_Keyword("LEAVE");
	public @OPT PLI_Identifier_Reference label;
	public PunctuationSemicolon semicolon;
}

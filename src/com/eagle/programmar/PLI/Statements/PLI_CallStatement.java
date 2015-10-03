// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenSequence;

public class PLI_CallStatement extends TokenSequence
{
	public @DOC("7.6") PLI_Keyword CALL = new PLI_Keyword("CALL");
	public PLI_Expression params;
	public @OPT PLI_Comment comment;
	public PLI_Punctuation semiColon = new PLI_Punctuation(';');
}

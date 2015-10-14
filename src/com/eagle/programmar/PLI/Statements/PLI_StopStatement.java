// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2012

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_StopStatement extends TokenSequence
{
	public PLI_Keyword STOP = new PLI_Keyword("STOP");
	public PunctuationSemicolon semicolon;
}

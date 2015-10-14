// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2012

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_IterateStatement extends TokenSequence
{
	public PLI_Keyword ITERATE = new PLI_Keyword("ITERATE");
	public PLI_Identifier_Reference label;
	public PunctuationSemicolon semicolon;
}

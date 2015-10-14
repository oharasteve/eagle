// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_FreeStatement extends TokenSequence
{
	public @DOC("7.23") PLI_Keyword FREE = new PLI_Keyword("FREE");
	public SeparatedList<PLI_Identifier_Reference,PunctuationComma> ids;
	public PunctuationSemicolon semicolon;
}

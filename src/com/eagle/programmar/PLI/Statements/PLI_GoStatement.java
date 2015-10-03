// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenSequence;

public class PLI_GoStatement extends TokenSequence
{
	public @DOC("7.25") PLI_Keyword GO = new PLI_Keyword("GO");
	public PLI_Keyword TO = new PLI_Keyword("TO");
	public PLI_Identifier_Reference label;
	public PLI_Punctuation semicolon = new PLI_Punctuation(';');
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Javascript_BreakStatement extends TokenSequence
{
	public @NEWLINE @DOC("js_break.asp") Javascript_Keyword BREAK = new Javascript_Keyword("break");
	public @OPT Javascript_Identifier_Reference label;
	public @NOSPACE @OPT Javascript_Punctuation semicolon = new Javascript_Punctuation(';');
}

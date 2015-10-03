// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Javascript_DoStatement extends TokenSequence
{
	public @NEWLINE @DOC("js_loop_while.asp") Javascript_Keyword DO = new Javascript_Keyword("do");
	public Javascript_Statement doStatement;
	public @NEWLINE Javascript_Keyword WHILE = new Javascript_Keyword("while");
	public Javascript_Punctuation leftParen = new Javascript_Punctuation('(');
	public @NOSPACE Javascript_Expression condition;
	public @NOSPACE Javascript_Punctuation rightParen = new Javascript_Punctuation(')');
	public @NOSPACE @OPT Javascript_Punctuation semicolon = new Javascript_Punctuation(';');
}

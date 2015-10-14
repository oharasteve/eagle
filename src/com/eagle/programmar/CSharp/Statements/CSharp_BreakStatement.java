// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_BreakStatement extends TokenSequence
{
	public @NEWLINE @OPT CSharp_Keyword YIELD = new CSharp_Keyword("yield");
	public @DOC("statements.html#14.15") CSharp_Keyword BREAK = new CSharp_Keyword("break");
	public PunctuationSemicolon semicolon;
}

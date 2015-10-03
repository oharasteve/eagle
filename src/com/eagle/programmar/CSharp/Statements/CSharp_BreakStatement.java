// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CSharp_BreakStatement extends TokenSequence
{
	public @NEWLINE @OPT CSharp_Keyword YIELD = new CSharp_Keyword("yield");
	public @DOC("statements.html#14.15") CSharp_Keyword BREAK = new CSharp_Keyword("break");
	public CSharp_Punctuation semicolon = new CSharp_Punctuation(';');
}

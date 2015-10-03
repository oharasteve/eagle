// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CSharp_ContinueStatement extends TokenSequence
{
	public @DOC("statements.html#14.16") CSharp_Keyword CONTINUE = new CSharp_Keyword("continue");
	public CSharp_Punctuation semicolon = new CSharp_Punctuation(';');
}

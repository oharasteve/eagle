// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CSharp_ReturnStatement extends TokenSequence
{
	public @NEWLINE @OPT CSharp_Keyword YIELD = new CSharp_Keyword("yield");
	public @DOC("statements.html#14.17") CSharp_Keyword RETURN = new CSharp_Keyword("return");
	public @OPT CSharp_Expression expression;
	public CSharp_Punctuation semicolon = new CSharp_Punctuation(';');
}

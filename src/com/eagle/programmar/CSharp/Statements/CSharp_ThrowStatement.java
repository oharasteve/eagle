// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CSharp_ThrowStatement extends TokenSequence
{
	public @DOC("statements.html#14.18") CSharp_Keyword THROW = new CSharp_Keyword("throw");
	public @OPT CSharp_Expression expression;
	public CSharp_Punctuation semicolon = new CSharp_Punctuation(';');
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Expression.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CSharp_SuperStatement extends TokenSequence
{
	public CSharp_Keyword SUPER = new CSharp_Keyword("super");
	public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
	public @OPT CSharp_ArgumentList args;
	public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
	public CSharp_Punctuation semicolon = new CSharp_Punctuation(';');
}

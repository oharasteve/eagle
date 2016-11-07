// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_SuperStatement extends TokenSequence
{
	public CSharp_Keyword SUPER = new CSharp_Keyword("super");
	public PunctuationLeftParen leftParen;
	public @OPT CSharp_ArgumentList args;
	public PunctuationRightParen rightParen;
	public PunctuationSemicolon semicolon;
}

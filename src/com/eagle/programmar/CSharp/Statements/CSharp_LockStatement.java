// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2014

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_LockStatement extends TokenSequence
{
	public @NEWLINE CSharp_Keyword LOCK = new CSharp_Keyword("lock");
	public PunctuationLeftParen leftParen;
	public @NOSPACE CSharp_Expression expr;
	public @NOSPACE PunctuationRightParen rightParen;
	public CSharp_Statement statement;
}

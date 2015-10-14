// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_SynchronizedStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.19") CSharp_Keyword SYNCHRONIZED = new CSharp_Keyword("synchronized");
	public PunctuationLeftParen leftParen;
	public @NOSPACE CSharp_Expression expr;
	public @NOSPACE PunctuationRightParen rightParen;
	public CSharp_Statement syncStatement;
}

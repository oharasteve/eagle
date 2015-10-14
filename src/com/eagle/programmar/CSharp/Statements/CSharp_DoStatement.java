// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 31, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_DoStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.13") CSharp_Keyword DO = new CSharp_Keyword("do");
	public CSharp_Statement doStatement;
	public @NEWLINE CSharp_Keyword WHILE = new CSharp_Keyword("while");
	public PunctuationLeftParen leftParen;
	public CSharp_Expression condition;
	public PunctuationRightParen rightParen;
	public PunctuationSemicolon semicolon;
}

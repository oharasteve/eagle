// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 31, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CSharp_DoStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.13") CSharp_Keyword DO = new CSharp_Keyword("do");
	public CSharp_Statement doStatement;
	public @NEWLINE CSharp_Keyword WHILE = new CSharp_Keyword("while");
	public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
	public CSharp_Expression condition;
	public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
	public CSharp_Punctuation semicolon = new CSharp_Punctuation(';');
}

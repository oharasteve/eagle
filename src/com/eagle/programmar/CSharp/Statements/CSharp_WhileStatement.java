// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CSharp_WhileStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.12") CSharp_Keyword WHILE = new CSharp_Keyword("while");
	public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
	public @NOSPACE CSharp_Expression condition;
	public @NOSPACE CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
	public @OPT CSharp_Comment comment;
	public CSharp_Statement whileStatement;
}

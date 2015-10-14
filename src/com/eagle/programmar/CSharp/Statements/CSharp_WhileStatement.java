// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_WhileStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.12") CSharp_Keyword WHILE = new CSharp_Keyword("while");
	public PunctuationLeftParen leftParen;
	public @NOSPACE CSharp_Expression condition;
	public @NOSPACE PunctuationRightParen rightParen;
	public @OPT CSharp_Comment comment;
	public CSharp_Statement whileStatement;
}

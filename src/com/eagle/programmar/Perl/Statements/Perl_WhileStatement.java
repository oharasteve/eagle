// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_WhileStatement extends TokenSequence
{
	public @DOC("control-structures.while.php") Perl_Keyword WHILE = new Perl_Keyword("while");
	public PunctuationLeftParen leftParen;
	public Perl_Expression condition;
	public PunctuationRightParen rightParen;
	public @OPT TokenList<Perl_Comment> comments;
	public Perl_Statement stmt;
}

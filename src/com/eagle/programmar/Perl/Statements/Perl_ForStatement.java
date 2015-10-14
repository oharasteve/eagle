// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_ForStatement extends TokenSequence
{
	public @DOC("control-structures.for.php") Perl_Keyword FOR = new Perl_Keyword("for");
	public PunctuationLeftParen leftParen;
	public @OPT Perl_Expression initExpr;
	public @OPT Perl_ForClause testExpr;
	public @OPT Perl_ForClause incrExpr;
	public PunctuationRightParen rightParen;
	public Perl_Statement stmt;
	
	public static class Perl_ForClause extends TokenSequence
	{
		public @OPT PunctuationSemicolon semicolon;
		public Perl_Expression incrExpr;
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 27, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Statement.Perl_SimpleStatement.Perl_StatementOrComment;
import com.eagle.programmar.Perl.Symbols.Perl_Class_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Perl_TraitStatement extends TokenSequence
{
	public Perl_Keyword TRAIT = new Perl_Keyword("trait");
	public Perl_Class_Definition trait;
	public PunctuationLeftBrace leftBrace;
	public @OPT TokenList<Perl_StatementOrComment> stmts;
	public PunctuationRightBrace rightBrace;
}

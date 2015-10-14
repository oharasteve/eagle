// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 15, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Statement.Perl_StatementBlock;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_NamespaceStatement extends TokenSequence
{
	public Perl_Keyword NAMESPACE = new Perl_Keyword("namespace");
	public @OPT Perl_Identifier_Reference id;
	public @OPT TokenList<Perl_MoreNamespace> more;
	public Perl_NamespaceBody body;
	
	public static class Perl_NamespaceBody extends TokenChooser
	{
		public PunctuationSemicolon semicolon;
		public Perl_StatementBlock block;
	}
	
	public static class Perl_MoreNamespace extends TokenSequence
	{
		public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public Perl_Identifier_Reference id;
	}
}

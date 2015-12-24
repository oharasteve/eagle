// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 16, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.programmar.Perl.Perl_Program;
import com.eagle.programmar.Perl.Perl_Syntax;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMD_Perl_Statement extends TokenSequence
{
	public CMD_Keyword PERL = new CMD_Keyword("perl");
	public @OPT TokenList<CMD_Perl_Option> opts;
	public TokenList<CMD_Argument> args;
	public CMD_EndOfLine eoln1;
	public CMD_Goto_Statement gotoStatement;
	public CMD_EndOfLine eoln2;
	public @OPT CMD_Punctuation at = new CMD_Punctuation('@');
	public CMD_Rem_Statement remStatement;
	public CMD_EndOfLine eoln3;
	
	public @SYNTAX(Perl_Syntax.class) Perl_Program perlProgram;

	public @SYNTAX(Perl_Syntax.class) Perl_Keyword END = new Perl_Keyword("__END__");
	
	public static class CMD_Perl_Option extends TokenChooser
	{
		public static class CMD_Perl_Option_E extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('-');
			public CMD_Keyword E = new CMD_Keyword("e");
		}
	}
}

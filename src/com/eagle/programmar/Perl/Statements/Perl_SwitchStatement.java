// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement.Perl_SimpleStatement.Perl_StatementOrComment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Perl_SwitchStatement  extends TokenSequence
{
	public @DOC("control-structures.switch.php") Perl_Keyword SWITCH = new Perl_Keyword("switch");
	public Perl_Punctuation leftParen = new Perl_Punctuation('(');
	public Perl_Expression val;
	public Perl_Punctuation rightParen = new Perl_Punctuation(')');
	public Perl_Punctuation leftBrace = new Perl_Punctuation('{');
	public TokenList<Perl_CaseClause> caseClause;
	public @OPT Perl_DefaultClause elseClause;
	public Perl_Punctuation rightBrace = new Perl_Punctuation('}');
	
	public static class Perl_CaseClause extends TokenSequence
	{
		public Perl_Keyword CASE = new Perl_Keyword("case");
		public Perl_Expression expr;
		public Perl_Punctuation colon = new Perl_Punctuation(':');
		public @OPT TokenList<Perl_StatementOrComment> statements;
	}
	
	public static class Perl_DefaultClause extends TokenSequence
	{
		public Perl_Keyword DEFAULT = new Perl_Keyword("default");
		public Perl_Punctuation colon = new Perl_Punctuation(':');
		public TokenList<Perl_StatementOrComment> statements;
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement.Perl_SimpleStatement.Perl_StatementOrComment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_SwitchStatement  extends TokenSequence
{
	public @DOC("control-structures.switch.php") Perl_Keyword SWITCH = new Perl_Keyword("switch");
	public PunctuationLeftParen leftParen;
	public Perl_Expression val;
	public PunctuationRightParen rightParen;
	public PunctuationLeftBrace leftBrace;
	public TokenList<Perl_CaseClause> caseClause;
	public @OPT Perl_DefaultClause elseClause;
	public PunctuationRightBrace rightBrace;
	
	public static class Perl_CaseClause extends TokenSequence
	{
		public Perl_Keyword CASE = new Perl_Keyword("case");
		public Perl_Expression expr;
		public PunctuationColon colon;
		public @OPT TokenList<Perl_StatementOrComment> statements;
	}
	
	public static class Perl_DefaultClause extends TokenSequence
	{
		public Perl_Keyword DEFAULT = new Perl_Keyword("default");
		public PunctuationColon colon;
		public TokenList<Perl_StatementOrComment> statements;
	}
}

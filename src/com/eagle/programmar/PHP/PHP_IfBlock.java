// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 27, 2014

package com.eagle.programmar.PHP;

import com.eagle.programmar.PHP.PHP_Program.PHP_EndTag;
import com.eagle.programmar.PHP.PHP_Program.PHP_Entry;
import com.eagle.programmar.PHP.PHP_Program.PHP_StartTag;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Perl_Syntax;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

// <?php stmts; if(cond) { ?> xxx <?php } else { ?> xxx <?php } ?>

public class PHP_IfBlock extends TokenSequence
{
	public @SYNTAX(Perl_Syntax.class) PHP_IfCondition condition;
	public TokenList<PHP_Entry> ifPart;
	public @SYNTAX(Perl_Syntax.class) PHP_IfElse elseBlock;
	public TokenList<PHP_Entry> elsePart;
	public @SYNTAX(Perl_Syntax.class) PHP_EndIf endIf;
	
	public static class PHP_IfCondition extends TokenSequence
	{
		public PHP_StartTag startTag;
		public TokenList<Perl_Statement> statements;
		public Perl_Keyword IF = new Perl_Keyword("if");
		public PunctuationLeftParen leftParen;
		public Perl_Expression condition;
		public PunctuationRightParen rightParen;
		public PunctuationLeftBrace leftBrace;
		public PHP_EndTag endTag;
	}

	public static class PHP_IfElse extends TokenSequence
	{
		public PHP_StartTag startTag;
		public PunctuationRightBrace rightBrace;
		public Perl_Keyword ELSE = new Perl_Keyword("else");
		public PunctuationLeftBrace leftBrace;
		public PHP_EndTag endTag;
	}

	public static class PHP_EndIf extends TokenSequence
	{
		public PHP_StartTag startTag;
		public PunctuationRightBrace rightBrace;
		public PHP_EndTag endTag;
	}
}

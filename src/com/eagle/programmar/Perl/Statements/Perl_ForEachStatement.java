// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_ForEachStatement extends TokenChooser
{
	public @CHOICE static class Perl_ForEachAsStatement extends TokenSequence
	{
		public @DOC("control-structures.foreach.php") Perl_Keyword FOREACH = new Perl_Keyword("foreach");
		public PunctuationLeftParen leftParen;
		public Perl_Expression expr;
		public Perl_Keyword AS = new Perl_Keyword("as");
		public Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_Variable_Definition var;
		public @OPT Perl_ForEachArrow arrow;
		public PunctuationRightParen rightParen;
		public Perl_Statement stmt;
		
		public static class Perl_ForEachArrow extends TokenSequence
		{
			public Perl_Punctuation equalsGreater = new Perl_Punctuation("=>");
			public Perl_Variable var;
		}
	}
	
	public @CHOICE static class Perl_ForEachNoAsStatement extends TokenSequence
	{
		public @DOC("control-structures.foreach.php") Perl_Keyword FOREACH = new Perl_Keyword("foreach");
		public @OPT Perl_ForEachVariable var;
		public PunctuationLeftParen leftParen;
		public Perl_Expression expr;
		public PunctuationRightParen rightParen;
		public Perl_Statement stmt;
		
		public static class Perl_ForEachVariable extends TokenSequence
		{
			public Perl_Punctuation dollar = new Perl_Punctuation('$');
			public Perl_Variable_Definition var;
		}
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.AWK_Action;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_IfStatement extends TokenSequence
{
	public AWK_Keyword IF = new AWK_Keyword("if");
	public PunctuationLeftParen leftParen;
	public AWK_Expression condition;
	public PunctuationRightParen rightParen;
	public AWK_IfBlock block;
	public @OPT AWK_IfElse ifelse;
	
	public static class AWK_IfElse extends TokenSequence
	{
		public AWK_Keyword ELSE = new AWK_Keyword("else");
		public AWK_IfBlock block;
	}
	
	public static class AWK_IfBlock extends TokenChooser
	{
		public @CHOICE AWK_Statement stmt;
		public @CHOICE AWK_Action action;
	}
}

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2015

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.AWK_Action;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class AWK_ForStatement extends TokenSequence
{
	public AWK_Keyword FOR = new AWK_Keyword("for");
	public PunctuationLeftParen leftParen;
	public AWK_Expression initialize;
	public PunctuationSemicolon semicolon1;
	public AWK_Expression test;
	public PunctuationSemicolon semicolon2;
	public AWK_Expression increment;
	public PunctuationRightParen rightParen;
	public AWK_ForBlock block;
	
	public static class AWK_ForBlock extends TokenChooser
	{
		public AWK_Statement stmt;
		public AWK_Action actions;
	}
}

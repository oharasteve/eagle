// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 9, 2014

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_NoteStatement extends TokenSequence
{
	public PLI_Keyword NOTE = new PLI_Keyword("NOTE");
	public PunctuationLeftParen leftParen;
	public PLI_Expression expr1;
	public PunctuationComma comma;
	public PLI_Expression expr2;
	public PunctuationRightParen rightParen;
	public PunctuationSemicolon semicolon;
}

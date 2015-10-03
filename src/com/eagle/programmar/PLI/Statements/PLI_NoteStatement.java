// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 9, 2014

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenSequence;

public class PLI_NoteStatement extends TokenSequence
{
	public PLI_Keyword NOTE = new PLI_Keyword("NOTE");
	public PLI_Punctuation leftParen = new PLI_Punctuation('(');
	public PLI_Expression expr1;
	public PLI_Punctuation comma = new PLI_Punctuation(',');
	public PLI_Expression expr2;
	public PLI_Punctuation rightParen = new PLI_Punctuation(')');
	public PLI_Punctuation semiColon = new PLI_Punctuation(';');
}

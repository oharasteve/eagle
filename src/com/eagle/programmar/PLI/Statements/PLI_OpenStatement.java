// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 3, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_OpenStatement extends TokenSequence
{
	public @DOC("7.37") PLI_Keyword OPEN = new PLI_Keyword("OPEN");
	public PLI_Keyword FILE = new PLI_Keyword("FILE");
	public PunctuationLeftParen leftParen1;
	public PLI_Identifier_Reference fileName;
	public PunctuationRightParen rightParen1;
	public PLI_Keyword TITLE = new PLI_Keyword("TITLE");
	public PunctuationLeftParen leftParen2;
	public PLI_Literal title;
	public PunctuationRightParen rightParen2;
	public PunctuationSemicolon semicolon;
}

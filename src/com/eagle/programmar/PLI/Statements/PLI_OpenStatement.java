// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 3, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenSequence;

public class PLI_OpenStatement extends TokenSequence
{
	public @DOC("7.37") PLI_Keyword OPEN = new PLI_Keyword("OPEN");
	public PLI_Keyword FILE = new PLI_Keyword("FILE");
	public PLI_Punctuation leftParen1 = new PLI_Punctuation('(');
	public PLI_Identifier_Reference fileName;
	public PLI_Punctuation rightParen1 = new PLI_Punctuation(')');
	public PLI_Keyword TITLE = new PLI_Keyword("TITLE");
	public PLI_Punctuation leftParen2 = new PLI_Punctuation('(');
	public PLI_Literal title;
	public PLI_Punctuation rightParen2 = new PLI_Punctuation(')');
	public PLI_Punctuation semicolon = new PLI_Punctuation(';');
}

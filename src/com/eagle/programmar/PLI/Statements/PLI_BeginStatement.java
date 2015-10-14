// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_BeginStatement extends TokenSequence
{
	public @OPT PLI_Label label1;
	public @DOC("7.5") PLI_Keyword BEGIN = new PLI_Keyword("BEGIN");
	public PunctuationSemicolon semicolon1;
	public TokenList<PLI_StatementOrComment> statements;
	public PLI_Keyword END = new PLI_Keyword("END");
	public @OPT PLI_Identifier_Reference label2;
	public PunctuationSemicolon semicolon2;
}

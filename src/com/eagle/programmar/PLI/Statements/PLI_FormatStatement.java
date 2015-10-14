// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2012

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.Statements.PLI_PutStatement.PLI_PutFormat;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_FormatStatement extends TokenSequence
{
	public @OPT PLI_Label label;
	public PLI_Keyword FORMAT = new PLI_Keyword("FORMAT");
	public PLI_PutFormat format;
	public PunctuationSemicolon semicolon;
}

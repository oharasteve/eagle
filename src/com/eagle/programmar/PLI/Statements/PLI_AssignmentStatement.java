// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 18, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Subscript;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_AssignmentStatement extends TokenSequence
{
	public @OPT PLI_Label label;
	public PLI_Identifier_Reference var;
	public @OPT PLI_Subscript params;
	public PunctuationEquals equals;
	public PLI_Expression expr;
	public @OPT PLI_Comment comment;
	public PunctuationSemicolon semicolon;
}

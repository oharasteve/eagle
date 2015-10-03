// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class VB_ExitStatement extends TokenSequence
{
	public VB_Keyword EXIT = new VB_Keyword("exit");
	public VB_KeywordChoice FOR = new VB_KeywordChoice(
			"do", "for", "function", "sub");
}

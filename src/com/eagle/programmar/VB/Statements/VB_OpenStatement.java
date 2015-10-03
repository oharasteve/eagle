// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.programmar.VB.Terminals.VB_Number;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.TokenSequence;

public class VB_OpenStatement extends TokenSequence
{
	public VB_Keyword OPEN = new VB_Keyword("open");
	public VB_Expression fileName;
	public VB_Keyword FOR = new VB_Keyword("for");
	public VB_KeywordChoice inputOutput = new VB_KeywordChoice(
			"input", "output");
	public VB_Keyword AS = new VB_Keyword("as");
	public VB_Punctuation pound = new VB_Punctuation('#');
	public VB_Number channel;
}

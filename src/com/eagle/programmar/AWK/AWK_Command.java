// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class AWK_Command extends TokenSequence
{
	public @OPT SeparatedList<AWK_Condition,PunctuationComma> condition;
	public AWK_Action action;
}

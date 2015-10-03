// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.tokens.TokenSequence;

public class AWK_Command extends TokenSequence
{
	public @OPT AWK_Condition condition;
	public AWK_Action action;
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.AWK_Expression.AWK_SubscriptExpression;
import com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference;
import com.eagle.tokens.TokenSequence;

public class AWK_Variable extends TokenSequence
{
	public AWK_Identifier_Reference id;
	public @OPT AWK_SubscriptExpression subscript;
}

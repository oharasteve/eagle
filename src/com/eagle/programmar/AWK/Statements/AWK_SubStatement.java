// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.tokens.TokenSequence;

public class AWK_SubStatement extends TokenSequence
{
	public AWK_KeywordChoice SUB = new AWK_KeywordChoice("gsub", "sub");
	public AWK_Punctuation leftParen = new AWK_Punctuation('(');
	public AWK_ArgumentList argList;
	public AWK_Punctuation rightParen = new AWK_Punctuation(')');
}

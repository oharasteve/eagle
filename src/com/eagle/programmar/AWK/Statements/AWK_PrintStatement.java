// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class AWK_PrintStatement extends TokenSequence
{
	public AWK_KeywordChoice PRINT = new AWK_KeywordChoice("print", "printf");
	public AWK_PrintParameters param;
	
	public static class AWK_PrintParameters extends TokenChooser
	{
		public @FIRST static class AWK_Print_WithParens extends TokenSequence
		{
			public AWK_Punctuation leftParen = new AWK_Punctuation('(');
			public @OPT AWK_ArgumentList argList;
			public AWK_Punctuation rightParen = new AWK_Punctuation(')');
		}
		
		public static class AWK_Print_NoParens extends TokenSequence
		{
			public @OPT AWK_ArgumentList argList;
		}
	}
}

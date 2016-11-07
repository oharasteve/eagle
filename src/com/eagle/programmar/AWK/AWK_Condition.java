// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
import com.eagle.tokens.TokenChooser;

public class AWK_Condition extends TokenChooser
{
	public @CHOICE AWK_KeywordChoice BEGIN = new AWK_KeywordChoice("BEGIN", "END");
	public @LAST AWK_Expression expr;
}

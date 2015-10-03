// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
import com.eagle.tokens.TokenSequence;

public class IntelASM_ListDirective extends TokenSequence
{
	public IntelASM_Punctuation dot = new IntelASM_Punctuation('.');
	public IntelASM_KeywordChoice directive = new IntelASM_KeywordChoice(
			"list",
			"xlist"
	);
}
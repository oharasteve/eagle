// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class IntelASM_ListDirective extends TokenSequence
{
	public PunctuationPeriod dot;
	public IntelASM_KeywordChoice directive = new IntelASM_KeywordChoice(
			"list",
			"xlist"
	);
}
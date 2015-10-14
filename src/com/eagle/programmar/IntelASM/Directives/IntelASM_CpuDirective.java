// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IntelASM_CpuDirective extends TokenSequence
{
	public IntelASM_Keyword CPU = new IntelASM_Keyword("cpu");
	public SeparatedList<IntelASM_CPUValue,PunctuationComma> values;
	
	public static class IntelASM_CPUValue extends TokenSequence
	{
		public IntelASM_KeywordChoice cpu = new IntelASM_KeywordChoice(
				"MMX",
				"SSE",
				"SSE2",
				"SSE3",
				"SSSE3"
		);
	}
}

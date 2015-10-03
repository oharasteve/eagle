// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class IntelASM_CpuDirective extends TokenSequence
{
	public IntelASM_Keyword CPU = new IntelASM_Keyword("cpu");
	public IntelASM_CPUValue value;
	public @OPT TokenList<IntelASM_MoreCPUValue> more;
	
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
	
	public static class IntelASM_MoreCPUValue extends TokenSequence
	{
		public IntelASM_Punctuation comma = new IntelASM_Punctuation(',');
		public IntelASM_CPUValue value;
	}
}

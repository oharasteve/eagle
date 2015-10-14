// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Literal;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class IntelASM_IncludeDirective extends TokenSequence
{
	public @OPT IntelASM_Punctuation percent = new IntelASM_Punctuation('%');
	public IntelASM_Keyword INCLUDE = new IntelASM_Keyword("include");
	public IntelASM_Filename filename;

	public static class IntelASM_Filename extends TokenChooser
	{
		public IntelASM_Literal literal;

		public static class IntelASM_BareFilename extends TokenSequence
		{
			public SeparatedList<IntelASM_Identifier_Reference,PunctuationPeriod> name;
		}
	}
}
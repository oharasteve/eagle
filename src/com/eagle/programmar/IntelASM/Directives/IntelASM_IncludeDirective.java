// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Literal;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

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
			public IntelASM_Identifier_Reference name;
			public @OPT TokenList<IntelASM_MoreFilename> more;
			
			public static class IntelASM_MoreFilename extends TokenSequence
			{
				public IntelASM_Punctuation dot = new IntelASM_Punctuation('.');
				public IntelASM_Identifier_Reference name;
			}
		}
	}
}
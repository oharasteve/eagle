// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.tokens.TokenSequence;

public class IntelASM_SectionDirective extends TokenSequence
{
	public IntelASM_Keyword SECTION = new IntelASM_Keyword("SECTION_TEXT");
}

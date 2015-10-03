// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
import com.eagle.tokens.TokenSequence;

public class IntelASM_IfDefDirective extends TokenSequence
{
	public IntelASM_Punctuation percent = new IntelASM_Punctuation('%');
	public IntelASM_KeywordChoice IFDEF = new IntelASM_KeywordChoice("ifdef", "ifndef");
	public IntelASM_Identifier_Reference name;
}
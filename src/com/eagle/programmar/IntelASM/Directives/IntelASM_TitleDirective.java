// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Terminals.IntelASM_CommentToEndOfLine;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.tokens.TokenSequence;

public class IntelASM_TitleDirective extends TokenSequence
{
	public IntelASM_Keyword TITLE = new IntelASM_Keyword("TITLE");
	public IntelASM_CommentToEndOfLine title;
}

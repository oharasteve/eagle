// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.Django.Terminals.Django_Literal;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Django_ExtendsControl extends TokenSequence
{
	public HTML_Punctuation bracePercent = new HTML_Punctuation("{%");
	public Django_Keyword EXTENDS = new Django_Keyword("extends");
	public Django_Literal literal;
	public HTML_Punctuation percentBrace = new HTML_Punctuation("%}");
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.Django.Terminals.Django_KeywordChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Django_AutoEscapeControl extends TokenSequence
{
	public HTML_Punctuation bracePercent1 = new HTML_Punctuation("{%");
	public Django_KeywordChoice AUTOESCAPE = new Django_KeywordChoice("autoescape", "endautoescape");
	public @OPT Django_Keyword OFF = new Django_Keyword("off");
	public HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
}

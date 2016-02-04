// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 16, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Symbols.Django_Variable_Definition;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_LoadControl extends TokenSequence
{
	public HTML_Punctuation bracePercent1 = new HTML_Punctuation("{%");
	public @OPT PunctuationHyphen dash1;
	public Django_Keyword LOAD = new Django_Keyword("load");
	public TokenList<Django_Variable_Definition> variables;
	public @OPT PunctuationHyphen dash2;
	public HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Django_Element;
import com.eagle.programmar.Django.Symbols.Django_Variable_Definition;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_BlockControl extends TokenSequence
{
	public HTML_Punctuation bracePercent1 = new HTML_Punctuation("{%");
	public @OPT PunctuationHyphen dash1;
	public Django_Keyword BLOCK = new Django_Keyword("block");
	public Django_Variable_Definition variable;
	public HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
	
	public @OPT TokenList<Django_Element> html;

	public HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
	public @OPT PunctuationHyphen dash2;
	public Django_Keyword ENDBLOCK = new Django_Keyword("endblock");
	public HTML_Punctuation percentBrace2 = new HTML_Punctuation("%}");
}

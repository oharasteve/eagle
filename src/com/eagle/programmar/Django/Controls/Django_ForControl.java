// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Django_Element;
import com.eagle.programmar.Django.Django_Expression;
import com.eagle.programmar.Django.Django_Variable;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Django_ForControl extends TokenSequence
{
	public HTML_Punctuation bracePercent = new HTML_Punctuation("{%");
	public Django_Keyword FOR = new Django_Keyword("for");
	public Django_Variable var;
	public Django_Keyword IN = new Django_Keyword("in");
	public Django_Expression expr;
	public HTML_Punctuation percentBrace = new HTML_Punctuation("%}");
	
	public TokenList<Django_Element> html;

	public HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
	public Django_Keyword ENDFOR = new Django_Keyword("endfor");
	public HTML_Punctuation percentBrace2 = new HTML_Punctuation("%}");
}

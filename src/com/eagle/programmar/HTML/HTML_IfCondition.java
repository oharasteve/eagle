// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.Terminals.HTML_Identifier;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenSequence;

public class HTML_IfCondition extends TokenSequence
{
	public HTML_Punctuation startTag = new HTML_Punctuation("<!");
	public HTML_Punctuation leftBracket = new HTML_Punctuation('[');
	public HTML_Keyword IF = new HTML_Keyword("if");
	public HTML_Identifier tag;
	public HTML_Punctuation rightBracket = new HTML_Punctuation(']');
	public HTML_Punctuation endTag = new HTML_Punctuation('>');
}

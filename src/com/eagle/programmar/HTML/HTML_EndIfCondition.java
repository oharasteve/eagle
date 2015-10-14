// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class HTML_EndIfCondition extends TokenSequence
{
	public HTML_Punctuation startTag = new HTML_Punctuation("<!");
	public PunctuationLeftBracket leftBracket;
	public HTML_Keyword ENDIF = new HTML_Keyword("endif");
	public PunctuationRightBracket rightBracket;
	public HTML_Punctuation endTag = new HTML_Punctuation('>');
}

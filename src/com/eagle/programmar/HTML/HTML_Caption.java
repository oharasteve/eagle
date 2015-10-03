// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2014

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.HTML_Program.HTML_Element;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_Caption extends TokenSequence
{
	public @INDENT HTML_Punctuation startTag = new HTML_Punctuation('<');
	public @NOSPACE HTML_Keyword CAPTION = new HTML_Keyword("caption");
	public @OPT TokenList<HTML_Attribute> attributes; 
	public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	public @OPT TokenList<HTML_Element> elements;
	public @OUTDENT HTML_EndCaption endCaption;

	public static class HTML_EndCaption extends TokenSequence
	{
		public HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @NOSPACE HTML_Keyword CAPTION = new HTML_Keyword("caption");
		public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
}

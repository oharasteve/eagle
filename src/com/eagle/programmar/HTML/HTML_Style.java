// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.CSS.CSS_Program;
import com.eagle.programmar.CSS.CSS_Syntax;
import com.eagle.programmar.HTML.Terminals.HTML_CData;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Literal;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_Style extends TokenSequence
{
	public @INDENT HTML_StartStyle startStyle;
	public HTML_StyleBody body;
	public @OUTDENT HTML_EndStyle endStyle;
	
	public static class HTML_StyleBody extends TokenChooser
	{
		public @LAST @SYNTAX(CSS_Syntax.class) CSS_Program css;
		public @CHOICE HTML_CData cdata;
		
		public @CHOICE static class HTML_StyleInclude extends TokenSequence
		{
			public HTML_Punctuation leftBrace = new HTML_Punctuation("{%");
			public HTML_Keyword INCLUDE = new HTML_Keyword("include");
			public HTML_Literal fileName;
			public HTML_Punctuation percent2 = new HTML_Punctuation("%}");
		}
	}

	public static class HTML_StartStyle extends TokenSequence
	{
		public HTML_Punctuation startTag = new HTML_Punctuation("<");
		public @NOSPACE @DOC("html_styles.asp") HTML_Keyword STYLE = new HTML_Keyword("style");
		public @OPT TokenList<HTML_Attribute> attributes; 
		public @NOSPACE HTML_Punctuation endTag1 = new HTML_Punctuation('>');
	}

	public static class HTML_EndStyle extends TokenSequence
	{
		public HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @NOSPACE HTML_Keyword STYLE = new HTML_Keyword("style");
		public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.HTML_Attribute.HTML_Value;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.PHP.PHP_Program.PHP_Entry;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class HTML_Anchor extends TokenChooser
{
	public @CHOICE static class HTML_AnchorHref extends TokenSequence
	{
		public @INDENT HTML_StartAnchor startTagA;
		public @OPT TokenList<PHP_Entry> contents;
		public @OUTDENT HTML_EndAnchor endAnchor;

		public static class HTML_StartAnchor extends TokenSequence
		{
			public HTML_Punctuation startTag = new HTML_Punctuation("<");
			public @NOSPACE HTML_Keyword A = new HTML_Keyword("a");
			public @OPT TokenList<HTML_Attribute> attributes; 
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}
		
		public static class HTML_EndAnchor extends TokenSequence
		{
			public HTML_Punctuation startTag = new HTML_Punctuation("</");
			public @NOSPACE HTML_Keyword A = new HTML_Keyword("a");
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}
	}
	
	// This is an oddball case. An anchor with no closing </a> (in theory).
	public @FIRST static class HTML_AnchorName extends TokenSequence
	{
		public @INDENT HTML_Punctuation startTagA = new HTML_Punctuation('<');
		public @NOSPACE HTML_Keyword A = new HTML_Keyword("a");
		public HTML_Keyword NAME = new HTML_Keyword("name");
		public @NOSPACE PunctuationEquals equals;
		public @NOSPACE HTML_Value value;
		public @NOSPACE HTML_Punctuation endTagA = new HTML_Punctuation("/>");
	}
}

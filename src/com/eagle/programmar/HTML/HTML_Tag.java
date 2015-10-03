// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.Django.Django_Control;
import com.eagle.programmar.Django.Django_Insert;
import com.eagle.programmar.HTML.Terminals.HTML_Identifier;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_Tag extends TokenSequence
{
	public @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation('<');
	public @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
	public @NOSPACE HTML_Identifier tag;
	public @OPT TokenList<HTML_TagElement> attributes; 
	public @NOSPACE HTML_PunctuationChoice closer = new HTML_PunctuationChoice(">", "/>");
	
	public static class HTML_TagCloser extends TokenChooser
	{
		public HTML_Punctuation endTag1 = new HTML_Punctuation('>');
		public HTML_Punctuation endTag2 = new HTML_Punctuation("/>");
	}
	
	public static class HTML_TagElement extends TokenChooser
	{
		public HTML_Attribute attribute;
		public Django_Control control;
		public Django_Insert insert;
	}
	
	public static class HTML_Tag_Namespace extends TokenSequence
	{
		public HTML_Identifier ns;
		public HTML_Punctuation colon = new HTML_Punctuation(':');
	}
	
	public static class HTML_EndTag extends TokenSequence
	{
		public @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
		public @NOSPACE HTML_Identifier tag;
		public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
}

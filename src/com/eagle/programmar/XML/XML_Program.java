// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.XML;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.Django.Django_Control;
import com.eagle.programmar.Django.Django_Insert;
import com.eagle.programmar.Django.Django_Syntax;
import com.eagle.programmar.Django.Controls.Django_AutoEscapeControl;
import com.eagle.programmar.HTML.HTML_DocType;
import com.eagle.programmar.HTML.HTML_Tag.HTML_TagElement;
import com.eagle.programmar.HTML.HTML_Tag.HTML_Tag_Namespace;
import com.eagle.programmar.HTML.Terminals.HTML_CData;
import com.eagle.programmar.HTML.Terminals.HTML_Comment;
import com.eagle.programmar.HTML.Terminals.HTML_Identifier;
import com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_Text;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class XML_Program extends EagleLanguage
{
	public static final String NAME = "XML";
	
	public XML_Program()
	{
		super(NAME, new XML_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/xml/";
	}

	public TokenList<XML_Element> elements;
	
	public static class XML_Header extends TokenSequence
	{
		public @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation("<?");
		public @NOSPACE HTML_KeywordChoice XML = new HTML_KeywordChoice(
				"mso-application",
				"rfc",
				"test",
				"test-style",
				"xml",
				"xml-stylesheet");
		public @OPT TokenList<HTML_TagElement> attributes; 
		public @NOSPACE HTML_Punctuation question2 = new HTML_Punctuation("?>");
	}
	
	public static class XML_Element extends TokenChooser
	{
		public XML_Header header;
		
		public HTML_Comment comment;
		public HTML_Text text;
		public HTML_DocType docType;
		public HTML_CData cdata;

		public Django_AutoEscapeControl autoEscape;
		
		public @FIRST XML_CombinedTag tag;

		// Django
		public @LAST @SYNTAX(Django_Syntax.class) Django_Control dj_control;
		public @LAST @SYNTAX(Django_Syntax.class) Django_Insert dj_insert;
		
		public static class XML_TagElement extends TokenSequence
		{
			public XML_StartTag startTag;
			public @OPT TokenList<XML_Element> elements;
			public XML_EndTag endTag;
		}
	}
	
	public static class XML_CombinedTag extends TokenSequence
	{
		public @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation('<');
		public @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
		public @NOSPACE XML_Identifier tag;
		public @OPT TokenList<HTML_TagElement> attributes;
		public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation("/>");
	}

	public static class XML_StartTag extends TokenSequence
	{
		public @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation('<');
		public @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
		public @NOSPACE XML_Identifier tag;
		public @OPT TokenList<HTML_TagElement> attributes;
		public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}

	public static class XML_EndTag extends TokenSequence
	{
		public @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
		public @NOSPACE XML_Identifier tag;
		public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
	
	public static class XML_Identifier extends TokenSequence
	{
		public HTML_Identifier tag;
		public @OPT TokenList<XML_MoreIds> more;
		
		public static class XML_MoreIds extends TokenSequence
		{
			public HTML_Punctuation dot = new HTML_Punctuation('.');
			public HTML_Identifier tag;
		}
	}
}

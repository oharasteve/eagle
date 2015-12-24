// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.Django.Django_Control;
import com.eagle.programmar.Django.Django_Insert;
import com.eagle.programmar.Django.Django_Syntax;
import com.eagle.programmar.HTML.HTML_Tag.HTML_EndTag;
import com.eagle.programmar.HTML.Terminals.HTML_Comment;
import com.eagle.programmar.HTML.Terminals.HTML_Pre;
import com.eagle.programmar.HTML.Terminals.HTML_Text;
import com.eagle.programmar.XML.XML_Program.XML_Header;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_Program extends EagleLanguage
{
	public static final String NAME = "HTML";
	
	public HTML_Program()
	{
		super(NAME, new HTML_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/html/";
	}
	
	public TokenList<HTML_Element> elements;
	
	public static class HTML_Element extends TokenChooser
	{
		public HTML_DocType docType;

		public HTML_Tag tag;
		public HTML_EndTag endTag;
		public HTML_Comment comment;
		
		// Statements, sort of
		public HTML_IfCondition ifCondition;
		public HTML_EndIfCondition endIfCondition;
		
		// Custom tags that need to be processed separately
		public HTML_Pre pre;
		public HTML_Style style;
		public HTML_Script script;
		public HTML_Anchor anchor;
		public HTML_Table table;
		public HTML_Caption caption;
		
		// Django
		public @SYNTAX(Django_Syntax.class) Django_Control dj_control;
		public @SYNTAX(Django_Syntax.class) Django_Insert dj_insert;
		
		public @LAST XML_Header xmlHeader;
		
		public @LAST static class HTML_JustText extends TokenSequence
		{
			// In a separate class just to enable @NEWLINE
			public @NEWLINE HTML_Text text;
		}
	}
}

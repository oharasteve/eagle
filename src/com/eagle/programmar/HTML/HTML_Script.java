// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.Django.Django_Control;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.Javascript.Javascript_Program;
import com.eagle.programmar.Javascript.Javascript_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_Script extends TokenChooser
{
	public @CHOICE static class HTML_ScriptWithBody extends TokenSequence
	{
		public @INDENT HTML_StartScript startScript;
		public HTML_ScriptBody body;
		public @OUTDENT HTML_EndScript endScript;
		
		public static class HTML_StartScript extends TokenSequence
		{
			public HTML_Punctuation startTag = new HTML_Punctuation('<');
			public @NOSPACE @DOC("html_scripts.asp") HTML_Keyword script = new HTML_Keyword("script");
			public @OPT TokenList<HTML_Attribute> attributes;
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}
		
		public static class HTML_ScriptBody extends TokenChooser
		{
			public @CHOICE @NOSPACE @OPT Django_Control django;
			public @CHOICE @SYNTAX(Javascript_Syntax.class) @OPT Javascript_Program javascript;
		}

		public static class HTML_EndScript extends TokenSequence
		{
			public HTML_Punctuation startTag = new HTML_Punctuation("</");
			public @NOSPACE HTML_Keyword SCRIPT = new HTML_Keyword("script");
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}
	}
	
	public @CHOICE static class HTMLScriptNoBody extends TokenSequence
	{
		public HTML_Punctuation startTag = new HTML_Punctuation('<');
		public @NOSPACE @DOC("html_scripts.asp") HTML_Keyword script = new HTML_Keyword("script");
		public @OPT TokenList<HTML_Attribute> attributes;
		public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation("/>");
	}
}

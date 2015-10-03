// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django;

import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.Django.Terminals.Django_Literal;
import com.eagle.programmar.Django.Terminals.Django_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Django_Insert extends TokenSequence
{
	public HTML_Punctuation startBraceBrace = new HTML_Punctuation("{{");
	public Django_Variable variable;
	public @OPT Django_OrEscape orEscape;
	public @OPT Django_OrDate orDate;
	public HTML_Punctuation endBraceBrace = new HTML_Punctuation("}}");
	
	public static class Django_OrEscape extends TokenSequence
	{
		public Django_Punctuation verticalBar = new Django_Punctuation('|');
		public Django_Keyword ESCAPE = new Django_Keyword("escape");
	}
	
	public static class Django_OrDate extends TokenSequence
	{
		public Django_Punctuation verticalBar = new Django_Punctuation('|');
		public Django_Keyword DATE = new Django_Keyword("date");
		public Django_Punctuation colon = new Django_Punctuation(':');
		public Django_Literal literal;
	}
}

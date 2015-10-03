// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.Django;

import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Number;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Django_Expression extends TokenChooser
{
	public @LAST Django_Variable var;
	
	public static class Django_ExpressionRange extends TokenSequence
	{
		public Django_Keyword RANGE = new Django_Keyword("range");
		public HTML_Punctuation leftParen = new HTML_Punctuation('(');
		public HTML_Number number;
		public HTML_Punctuation rightParen = new HTML_Punctuation(')');
	}
}
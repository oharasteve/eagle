// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2014

package com.eagle.programmar.CSS.Directives;

import com.eagle.programmar.CSS.CSS_Program.CSS_Entry;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CSS_If_Directive extends TokenSequence
{
	public CSS_Punctuation lessThan = new CSS_Punctuation('<');
	public CSS_Keyword IF = new CSS_Keyword("if");
	public CSS_Keyword EXPR = new CSS_Keyword("expr");
	public PunctuationEquals equals;
	public CSS_Literal literal;
	public CSS_Punctuation greaterThan = new CSS_Punctuation('>');
	
	public TokenList<CSS_Entry> entries;
	
	public CSS_EndIf endIf;
	
	public static class CSS_EndIf extends TokenSequence
	{
		public CSS_Punctuation lessThan = new CSS_Punctuation('<');
		public PunctuationSlash slash;
		public CSS_Keyword IF = new CSS_Keyword("if");
		public CSS_Punctuation greaterThan = new CSS_Punctuation('>');
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Django_Element;
import com.eagle.programmar.Django.Django_Variable;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_IfControl extends TokenSequence
{
	public HTML_Punctuation bracePercent1 = new HTML_Punctuation("{%");
	public @OPT PunctuationHyphen dash1;
	public Django_Keyword IF = new Django_Keyword("if");
	public @OPT Django_Keyword NOT = new Django_Keyword("not");
	public Django_Variable variable;
	public @OPT PunctuationHyphen dash2;
	public HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
	
	public TokenList<Django_Element> html;

	public @OPT Django_IfElseControl ifElse;
	
	public HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
	public @OPT PunctuationHyphen dash3;
	public Django_Keyword ENDIF = new Django_Keyword("endif");
	public @OPT PunctuationHyphen dash4;
	public HTML_Punctuation percentBrace2 = new HTML_Punctuation("%}");
	
	public static class Django_IfElseControl extends TokenSequence
	{
		public HTML_Punctuation bracePercent = new HTML_Punctuation("{%");
		public @OPT PunctuationHyphen dash1;
		public Django_Keyword ELSE = new Django_Keyword("else");
		public @OPT PunctuationHyphen dash2;
		public HTML_Punctuation percentBrace = new HTML_Punctuation("%}");
		
		public TokenList<Django_Element> html;
	}
}

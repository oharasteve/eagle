// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2015

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Django_Element;
import com.eagle.programmar.Django.Django_Variable;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.Django.Terminals.Django_Literal;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_BlockTransControl extends TokenSequence
{
	public HTML_Punctuation bracePercent1 = new HTML_Punctuation("{%");
	public @OPT PunctuationHyphen dash1;
	public Django_Keyword BLOCK = new Django_Keyword("blocktrans");
	public @OPT Django_BlockControlWith with;
	public @OPT PunctuationHyphen dash2;
	public HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
	
	public @OPT TokenList<Django_Element> html;

	public HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
	public @OPT PunctuationHyphen dash3;
	public Django_Keyword ENDBLOCK = new Django_Keyword("endblocktrans");
	public @OPT PunctuationHyphen dash4;
	public HTML_Punctuation percentBrace2 = new HTML_Punctuation("%}");
	
	public static class Django_BlockControlWith extends TokenSequence
	{
		public Django_Keyword WITH = new Django_Keyword("with");
		public Django_Literal literal;
		public Django_Keyword AS = new Django_Keyword("as");
		public Django_Variable var;
	}
}

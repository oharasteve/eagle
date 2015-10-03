// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON;

import com.eagle.programmar.JSON.JSON_Program.JSON_Element;
import com.eagle.programmar.JSON.Terminals.JSON_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class JSON_Object extends TokenSequence
{
	public JSON_Punctuation leftBracket = new JSON_Punctuation('[');
	public @OPT JSON_Element element;
	public @OPT TokenList<JSON_MoreElements> more;
	public JSON_Punctuation rightBracket = new JSON_Punctuation(']');
	
	public static class JSON_MoreElements extends TokenSequence
	{
		public JSON_Punctuation comma = new JSON_Punctuation(',');
		public JSON_Element element;
	}
}

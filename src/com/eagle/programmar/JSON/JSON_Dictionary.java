// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON;

import com.eagle.programmar.JSON.JSON_Program.JSON_Element;
import com.eagle.programmar.JSON.Terminals.JSON_Literal;
import com.eagle.programmar.JSON.Terminals.JSON_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class JSON_Dictionary extends TokenSequence
{
	public JSON_Punctuation leftBrace = new JSON_Punctuation('{');
	public @OPT JSON_DictEntry entry;
	public @OPT TokenList<JSON_MoreFields> more;
	public JSON_Punctuation rightBrace = new JSON_Punctuation('}');
	
	public static class JSON_DictEntry extends TokenSequence
	{
		public JSON_Literal name;
		public JSON_Punctuation colon = new JSON_Punctuation(':');
		public JSON_Element value;
	}
	
	public static class JSON_MoreFields extends TokenSequence
	{
		public JSON_Punctuation comma = new JSON_Punctuation(',');
		public JSON_DictEntry entry;
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON;

import com.eagle.programmar.JSON.JSON_Program.JSON_Element;
import com.eagle.programmar.JSON.Terminals.JSON_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class JSON_Dictionary extends TokenSequence
{
	public PunctuationLeftBrace leftBrace;
	public @OPT SeparatedList<JSON_DictEntry,PunctuationComma> entries;
	public PunctuationRightBrace rightBrace;
	
	public static class JSON_DictEntry extends TokenSequence
	{
		public JSON_Literal name;
		public PunctuationColon colon;
		public JSON_Element value;
	}
}

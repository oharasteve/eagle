// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON;

import com.eagle.programmar.JSON.JSON_Program.JSON_Element;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class JSON_Object extends TokenSequence
{
	public PunctuationLeftBracket leftBracket;
	public @OPT SeparatedList<JSON_Element,PunctuationComma> elements;
	public PunctuationRightBracket rightBracket;
}

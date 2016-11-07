// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Natural_FunctionCall extends TokenSequence
{
	public Natural_KeywordChoice fnName = new Natural_KeywordChoice(
			Natural_Functions.builtinFunctions );
	public PunctuationLeftParen leftParen;
	public Natural_Expression expr;
	public PunctuationRightParen rightParen;
}

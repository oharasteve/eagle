// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Symbols.Gupta_Identifier_Reference;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Gupta_Function_Call extends TokenSequence
{
	public Gupta_Identifier_Reference fnName;
	public PunctuationLeftParen leftParen;
	public @OPT Gupta_Expression expr;
	public @OPT TokenList<Gupta_More_Arguments> moreArgs;
	public PunctuationRightParen rightParen;
	
	public static class Gupta_More_Arguments extends TokenSequence
	{
		public @OPT PunctuationComma comma;
		public Gupta_Expression expr;
	}
}

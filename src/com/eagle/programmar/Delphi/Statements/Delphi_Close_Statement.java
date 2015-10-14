// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 16, 2013

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Close_Statement extends TokenSequence
{
	public Delphi_Keyword CLOSE = new Delphi_Keyword("Close");
	public @OPT Delphi_CloseWhat what;
	
	public static class Delphi_CloseWhat extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public Delphi_Expression file;
		public PunctuationRightParen rightParen;
	}
}

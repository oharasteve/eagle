// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 16, 2013

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Delphi_Close_Statement extends TokenSequence
{
	public Delphi_Keyword CLOSE = new Delphi_Keyword("Close");
	public @OPT Delphi_CloseWhat what;
	
	public static class Delphi_CloseWhat extends TokenSequence
	{
		public Delphi_Punctuation leftParen = new Delphi_Punctuation('(');
		public Delphi_Expression file;
		public Delphi_Punctuation righttParen = new Delphi_Punctuation(')');
	}
}

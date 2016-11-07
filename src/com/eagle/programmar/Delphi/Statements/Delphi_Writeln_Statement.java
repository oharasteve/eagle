// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Writeln_Statement extends TokenSequence
{
	public Delphi_KeywordChoice WRITELN = new Delphi_KeywordChoice("Write", "WriteLn");
	public @OPT Delphi_WriteLn_Something something;
	
	public static class Delphi_WriteLn_Something extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public @OPT SeparatedList<Delphi_WriteLn_Piece,PunctuationComma> pieces;
		public PunctuationRightParen rightParen;
		
		public static class Delphi_WriteLn_Piece extends TokenSequence
		{
			public Delphi_Expression expr;
			public @OPT Delphi_Writeln_ColonWidth width;
			public @OPT Delphi_Writeln_ColonWidth precision;
			
			public static class Delphi_Writeln_ColonWidth extends TokenSequence
			{
				public PunctuationColon colon;
				public Delphi_Expression width;
			}
		}
	}
}

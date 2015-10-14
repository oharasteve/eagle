// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_EditMask;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Natural_MoveStatement extends TokenSequence
{
	public @DOC("sm/move.htm") Natural_Keyword MOVE = new Natural_Keyword("MOVE");
	public @OPT Natural_Keyword EDITED = new Natural_Keyword("EDITED");
	public Natural_Expression expr;
	public Natural_Keyword TO = new Natural_Keyword("TO");
	public TokenList<Natural_Variable> variables;
	public @OPT Natural_MoveMask mask;
	
	public static class Natural_MoveMask extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public Natural_Keyword EM = new Natural_Keyword("EM");
		public PunctuationEquals equals;
		public Natural_EditMask mask;
		public PunctuationRightParen rightParen;
	}
}

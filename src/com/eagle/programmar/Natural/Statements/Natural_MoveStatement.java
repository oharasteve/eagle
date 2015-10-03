// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_EditMask;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

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
		public Natural_Punctuation leftParen = new Natural_Punctuation('(');
		public Natural_Keyword EM = new Natural_Keyword("EM");
		public Natural_Punctuation equals = new Natural_Punctuation('=');
		public Natural_EditMask mask;
		public Natural_Punctuation rightParen = new Natural_Punctuation(')');
	}
}

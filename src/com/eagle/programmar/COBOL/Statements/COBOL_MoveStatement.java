// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_MoveStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsmove.htm") COBOL_Keyword MOVE = new COBOL_Keyword("MOVE");
	public @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
	public COBOL_Expression expr;
	public COBOL_Keyword TO = new COBOL_Keyword("TO");
	public @OPT COBOL_Identifier_Reference var;
	public @OPT COBOL_Subscript subscript;
	public @OPT TokenList<COBOL_MoveMore> more;
	public @OPT @CURIOUS("MOVE: Extra comma") COBOL_Punctuation comma = new COBOL_Punctuation(',');
	
	public static class COBOL_MoveMore extends TokenSequence
	{
		public @OPT COBOL_Punctuation comma = new COBOL_Punctuation(',');
		public @OPT TokenList<COBOL_Comment> comments;
		public COBOL_Identifier_Reference var;
		public @OPT COBOL_Subscript subscript;
	}
}

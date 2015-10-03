// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2014

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Symbols.CMD_Identifier_Reference;
import com.eagle.programmar.CMD.Terminals.CMD_Comment;
import com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
import com.eagle.programmar.CMD.Terminals.CMD_Literal;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.UnparsedElement;

public class CMD_Unparsed_Statement extends UnparsedElement
{
	static String[] PUNCTS = new String[] { "%", ".", ",", "=", "(", ")", "[", "]" };

	@Override
	public @SKIP TokenList<? extends AbstractToken> unparsedPieces()
	{
		return elements;
	}
	
	public TokenList<CMD_UnparsedElement> elements;
	public CMD_EndOfLine eoln;
	
	public static class CMD_UnparsedElement extends TokenChooser
	{
		public CMD_Identifier_Reference id;
		public CMD_PunctuationChoice punct = new CMD_PunctuationChoice(PUNCTS);
		public CMD_Literal literal;
		public CMD_Number number;
		public CMD_Comment comment;
	}
}

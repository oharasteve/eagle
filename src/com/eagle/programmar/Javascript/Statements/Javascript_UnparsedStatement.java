// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2014

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.Javascript.Javascript_Syntax;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_RegularExpression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.UnparsedElement;

public class Javascript_UnparsedStatement extends UnparsedElement
{
	private static EagleSyntax SYNTAX = new Javascript_Syntax();
	static String[] KEYWORDS = SYNTAX.allReservedWords();
	static String[] PUNCTS = new String[] {
			"+", "-", "*", "/", ".", ",", "?", ":", "=", ">", "(", ")", "[", "]", "&", "|" };

	@Override
	public @SKIP TokenList<? extends AbstractToken> unparsedPieces()
	{
		return elements;
	}
	
	public TokenList<Javascript_UnparsedElement> elements;
	
	public static class Javascript_UnparsedElement extends TokenChooser
	{
		public Javascript_Identifier_Reference id;
		public @LAST Javascript_PunctuationChoice punct = new Javascript_PunctuationChoice(PUNCTS);
		public Javascript_Literal literal;
		public Javascript_Number number;
		public Javascript_Comment comment;
		public Javascript_RegularExpression regex;
		public Javascript_KeywordChoice keyword = new Javascript_KeywordChoice(KEYWORDS);
	}
}

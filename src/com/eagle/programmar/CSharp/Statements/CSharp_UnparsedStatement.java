// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2014

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.CSharp.CSharp_Syntax;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_HexNumber;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.UnparsedElement;

public class CSharp_UnparsedStatement extends UnparsedElement
{
	private static EagleSyntax SYNTAX = new CSharp_Syntax();
	static String[] KEYWORDS = SYNTAX.allReservedWords();
	static String[] PUNCTS = new String[] {
			"+", "-", "*", "/", ".", ",", "?", ":", "<", "=", ">", "(", ")", "[", "]", "&", "|" };

	@Override
	public @SKIP TokenList<? extends AbstractToken> unparsedPieces()
	{
		return elements;
	}
	
	public TokenList<CSharp_UnparsedElement> elements;
	
	public static class CSharp_UnparsedElement extends TokenChooser
	{
		public @CHOICE CSharp_Identifier_Reference id;
		public @LAST CSharp_PunctuationChoice punct = new CSharp_PunctuationChoice(PUNCTS);
		public @CHOICE CSharp_Literal literal;
		public @CHOICE CSharp_Number number;
		public @CHOICE CSharp_HexNumber hex;
		public @CHOICE CSharp_Comment comment;
		public @CHOICE CSharp_KeywordChoice keyword = new CSharp_KeywordChoice(KEYWORDS);
	}
}

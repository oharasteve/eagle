// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

package com.eagle.programmar.Lisp;

import com.eagle.programmar.Lisp.Symbols.Lisp_Identifier_Reference;
import com.eagle.programmar.Lisp.Terminals.Lisp_Character;
import com.eagle.programmar.Lisp.Terminals.Lisp_Comment;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.programmar.Lisp.Terminals.Lisp_Literal;
import com.eagle.programmar.Lisp.Terminals.Lisp_Number;
import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Lisp_SExpr extends TokenChooser
{
	public @CHOICE Lisp_Keyword NIL = new Lisp_Keyword("nil");
	public @CHOICE Lisp_Identifier_Reference id;
	public @CHOICE Lisp_Number number;
	public @CHOICE Lisp_Literal literal;
	public @CHOICE Lisp_Character character;
	public @CHOICE Lisp_Comment comment;
	public @CHOICE Lisp_Function function;
	
	public @CHOICE Lisp_PunctuationChoice operator = new Lisp_PunctuationChoice(
			"+",
			"++",
			"+++",
			"-",
			"*",
			"**",
			"***",
			"/",
			"//",
			"///",
			"1+",
			"1-",
			".",
			"?",
			">",
			">=",
			"=",
			"/=",
			"<",
			"<=");
	
	public @CHOICE static class Lisp_Ampersand extends TokenSequence
	{
		public Lisp_Punctuation ampersand = new Lisp_Punctuation('&');
		public Lisp_SExpr expr;
	}
	
	public @CHOICE static class Lisp_Colon extends TokenSequence
	{
		public PunctuationColon colon;
		public Lisp_SExpr expr;
	}
	
	public @CHOICE static class Lisp_Comma extends TokenSequence
	{
		public PunctuationComma comma;
		public @OPT Lisp_Punctuation at = new Lisp_Punctuation('@');
		public Lisp_SExpr expr;
	}
	
	public @CHOICE static class Lisp_Hash extends TokenSequence
	{
		public Lisp_Punctuation hash = new Lisp_Punctuation('#');
		public Lisp_SExpr expr;
	}
	
	public @CHOICE static class Lisp_Quote extends TokenSequence
	{
		public Lisp_Punctuation quote = new Lisp_Punctuation('\'');
		public Lisp_SExpr expr;
	}
	
	public @CHOICE static class Lisp_Tick extends TokenSequence
	{
		public Lisp_Punctuation tick = new Lisp_Punctuation('`');
		public Lisp_SExpr expr;
	}
	
	public @CHOICE static class Lisp_List extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public @OPT TokenList<Lisp_SExpr> exprs;
		public PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class Lisp_CharString extends TokenSequence
	{
		public Lisp_KeywordChoice charString = new Lisp_KeywordChoice("char", "string");
		public @OPT Lisp_Punctuation not = new Lisp_Punctuation('/');
		public @OPT Lisp_Punctuation less = new Lisp_Punctuation('<');
		public @OPT Lisp_Punctuation greater = new Lisp_Punctuation('>');
		public @OPT PunctuationEquals equals;
	}
	
	public @CHOICE static class Lisp_doLetProg extends TokenSequence
	{
		public Lisp_KeywordChoice doLetProg = new Lisp_KeywordChoice("do", "let", "prog");
		public @OPT PunctuationStar star;
	}
}
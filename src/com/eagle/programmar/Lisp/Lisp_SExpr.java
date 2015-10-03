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
import com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice;
import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Lisp_SExpr extends TokenChooser
{
	public Lisp_Keyword NIL = new Lisp_Keyword("nil");
	public Lisp_Identifier_Reference id;
	public Lisp_Number number;
	public Lisp_Literal literal;
	public Lisp_Character character;
	public Lisp_Comment comment;
	public Lisp_Function function;
	
	public Lisp_PunctuationChoice operator = new Lisp_PunctuationChoice(
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
	
	public static class Lisp_Ampersand extends TokenSequence
	{
		public Lisp_Punctuation ampersand = new Lisp_Punctuation('&');
		public Lisp_SExpr expr;
	}
	
	public static class Lisp_Colon extends TokenSequence
	{
		public Lisp_Punctuation colon = new Lisp_Punctuation(':');
		public Lisp_SExpr expr;
	}
	
	public static class Lisp_Comma extends TokenSequence
	{
		public Lisp_Punctuation comma = new Lisp_Punctuation(',');
		public @OPT Lisp_Punctuation at = new Lisp_Punctuation('@');
		public Lisp_SExpr expr;
	}
	
	public static class Lisp_Hash extends TokenSequence
	{
		public Lisp_Punctuation hash = new Lisp_Punctuation('#');
		public Lisp_SExpr expr;
	}
	
	public static class Lisp_Quote extends TokenSequence
	{
		public Lisp_Punctuation quote = new Lisp_Punctuation('\'');
		public Lisp_SExpr expr;
	}
	
	public static class Lisp_Tick extends TokenSequence
	{
		public Lisp_Punctuation tick = new Lisp_Punctuation('`');
		public Lisp_SExpr expr;
	}
	
	public static class Lisp_List extends TokenSequence
	{
		public Lisp_Punctuation leftParen = new Lisp_Punctuation('(');
		public @OPT TokenList<Lisp_SExpr> exprs;
		public Lisp_Punctuation rightParen = new Lisp_Punctuation(')');
	}
	
	public static class Lisp_CharString extends TokenSequence
	{
		public Lisp_KeywordChoice charString = new Lisp_KeywordChoice("char", "string");
		public @OPT Lisp_Punctuation not = new Lisp_Punctuation('/');
		public @OPT Lisp_Punctuation less = new Lisp_Punctuation('<');
		public @OPT Lisp_Punctuation greater = new Lisp_Punctuation('>');
		public @OPT Lisp_Punctuation equals = new Lisp_Punctuation('=');
	}
	
	public static class Lisp_doLetProg extends TokenSequence
	{
		public Lisp_KeywordChoice doLetProg = new Lisp_KeywordChoice("do", "let", "prog");
		public @OPT Lisp_Punctuation star = new Lisp_Punctuation('*');
	}
}
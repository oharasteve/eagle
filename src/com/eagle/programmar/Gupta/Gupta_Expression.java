// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Symbols.Gupta_Identifier_Reference;
import com.eagle.programmar.Gupta.Terminals.Gupta_Literal;
import com.eagle.programmar.Gupta.Terminals.Gupta_Number;
import com.eagle.programmar.Gupta.Terminals.Gupta_Punctuation;
import com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Gupta_Expression extends PrecedenceChooser
{
	public Gupta_Expression()
	{
	}
	
	public Gupta_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
	
	@Override
	protected void establishChoices() 
	{
		// Order doesn't matter
		super.addTerm(Gupta_Parens.class);
		super.addTerm(Gupta_Number.class);
		super.addTerm(Gupta_Literal.class);
		super.addTerm(Gupta_Function_Call.class);
		super.addTerm(Gupta_Identifier_Reference.class);
		super.addTerm(Gupta_UnarySign.class);

		// Highest precedence first
		super.addOperator(Gupta_Multiplicative_Expression.class);
		super.addOperator(Gupta_Additive_Expression.class);
		super.addOperator(Gupta_StrCat_Expression.class);
	}		

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static class Gupta_Parens extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public Gupta_Expression expr;
		public PunctuationRightParen rightParen;		
	}
	
	public static class Gupta_Function_Call extends ExpressionTerm
	{
		public Gupta_Identifier_Reference fnName;
		public PunctuationLeftParen leftParen;
		public @OPT Gupta_Expression expr;
		public @OPT TokenList<Gupta_More_Arguments> moreArgs;
		public PunctuationRightParen rightParen;
		
		public static class Gupta_More_Arguments extends TokenSequence
		{
			public @OPT PunctuationComma comma;
			public Gupta_Expression expr;
		}
	}
	
	public static class Gupta_UnarySign extends ExpressionTerm
	{
		public Gupta_PunctuationChoice sign = new Gupta_PunctuationChoice("-", "+");
		public Gupta_Expression exp;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static class Gupta_Multiplicative_Expression extends PrecedenceOperator 
	{
		public Gupta_Expression left = new Gupta_Expression(this, AllowedPrecedence.ATLEAST);
		public Gupta_PunctuationChoice timesDivide = new Gupta_PunctuationChoice("*", "/");
		public Gupta_Expression right = new Gupta_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Gupta_Additive_Expression extends PrecedenceOperator 
	{
		public Gupta_Expression left = new Gupta_Expression(this, AllowedPrecedence.ATLEAST);
		public Gupta_PunctuationChoice timesDivide = new Gupta_PunctuationChoice("+", "-");
		public Gupta_Expression right = new Gupta_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Gupta_StrCat_Expression extends PrecedenceOperator 
	{
		public Gupta_Expression left = new Gupta_Expression(this, AllowedPrecedence.ATLEAST);
		public Gupta_Punctuation strCat = new Gupta_Punctuation("||");
		public Gupta_Expression right = new Gupta_Expression(this, AllowedPrecedence.HIGHER);
	}
}
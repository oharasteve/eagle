// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Symbols.Gupta_Identifier_Reference;
import com.eagle.programmar.Gupta.Terminals.Gupta_Literal;
import com.eagle.programmar.Gupta.Terminals.Gupta_Number;
import com.eagle.programmar.Gupta.Terminals.Gupta_Punctuation;
import com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Gupta_Expression extends PrecedenceChooser
{
	public Gupta_Expression()
	{
	}
	
	public Gupta_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
	
	@Override
	protected void establishChoices() 
	{
		// Order doesn't matter
		super.addUnaryOperator(Gupta_Parens.class);
		super.addUnaryOperator(Gupta_Number.class);
		super.addUnaryOperator(Gupta_Literal.class);
		super.addUnaryOperator(Gupta_Function_Call.class);
		super.addUnaryOperator(Gupta_Identifier_Reference.class);
		super.addUnaryOperator(Gupta_UnarySign.class);

		// Highest precedence first
		super.addBinaryOperator(Gupta_Multiplicative_Expression.class);
		super.addBinaryOperator(Gupta_Additive_Expression.class);
		super.addBinaryOperator(Gupta_StrCat_Expression.class);
	}		

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static class Gupta_Parens extends UnaryOperator
	{
		public Gupta_Punctuation leftParen = new Gupta_Punctuation('(');
		public Gupta_Expression expr;
		public Gupta_Punctuation rightParen = new Gupta_Punctuation(')');		
	}
	
	public static class Gupta_Function_Call extends UnaryOperator
	{
		public Gupta_Identifier_Reference fnName;
		public Gupta_Punctuation leftParen = new Gupta_Punctuation('(');
		public @OPT Gupta_Expression expr;
		public @OPT TokenList<Gupta_More_Arguments> moreArgs;
		public Gupta_Punctuation rightParen = new Gupta_Punctuation(')');
		
		public static class Gupta_More_Arguments extends TokenSequence
		{
			public @OPT Gupta_Punctuation comma = new Gupta_Punctuation(',');
			public Gupta_Expression expr;
		}
	}
	
	public static class Gupta_UnarySign extends UnaryOperator
	{
		public Gupta_PunctuationChoice sign = new Gupta_PunctuationChoice("-", "+");
		public Gupta_Expression exp;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static class Gupta_Multiplicative_Expression extends BinaryOperator 
	{
		public Gupta_Expression left = new Gupta_Expression(this, AllowedPrecedence.ATLEAST);
		public Gupta_PunctuationChoice timesDivide = new Gupta_PunctuationChoice("*", "/");
		public Gupta_Expression right = new Gupta_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Gupta_Additive_Expression extends BinaryOperator 
	{
		public Gupta_Expression left = new Gupta_Expression(this, AllowedPrecedence.ATLEAST);
		public Gupta_PunctuationChoice timesDivide = new Gupta_PunctuationChoice("+", "-");
		public Gupta_Expression right = new Gupta_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Gupta_StrCat_Expression extends BinaryOperator 
	{
		public Gupta_Expression left = new Gupta_Expression(this, AllowedPrecedence.ATLEAST);
		public Gupta_Punctuation strCat = new Gupta_Punctuation("||");
		public Gupta_Expression right = new Gupta_Expression(this, AllowedPrecedence.HIGHER);
	}
}
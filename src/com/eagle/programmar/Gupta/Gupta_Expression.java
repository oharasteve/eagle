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
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Gupta_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Gupta_Number number;
	public @P(20) Gupta_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Gupta_Expression()
	{
	    super(_operators);
	}

	public Gupta_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static @P(100) class Gupta_Parens extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public Gupta_Expression expr;
		public PunctuationRightParen rightParen;		
	}
	
	public static @P(110) class Gupta_FunctionCall extends PrimaryOperator
	{
		public Gupta_Function_Call fnCall;
	}
	
	public static @P(120) class Gupta_IdentifierExpression extends PrimaryOperator
	{
		public Gupta_Identifier_Reference identifier;
	}
	
	public static @P(130) class Gupta_UnarySign extends PrimaryOperator
	{
		public Gupta_PunctuationChoice sign = new Gupta_PunctuationChoice("-", "+");
		public Gupta_Expression exp;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(140) class Gupta_Multiplicative_Expression extends PrecedenceOperator 
	{
		public Gupta_Expression left = new Gupta_Expression(this, AllowedPrecedence.ATLEAST);
		public Gupta_PunctuationChoice timesDivide = new Gupta_PunctuationChoice("*", "/");
		public Gupta_Expression right = new Gupta_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(150) class Gupta_Additive_Expression extends PrecedenceOperator 
	{
		public Gupta_Expression left = new Gupta_Expression(this, AllowedPrecedence.ATLEAST);
		public Gupta_PunctuationChoice timesDivide = new Gupta_PunctuationChoice("+", "-");
		public Gupta_Expression right = new Gupta_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(160) class Gupta_StrCat_Expression extends PrecedenceOperator 
	{
		public Gupta_Expression left = new Gupta_Expression(this, AllowedPrecedence.ATLEAST);
		public Gupta_Punctuation strCat = new Gupta_Punctuation("||");
		public Gupta_Expression right = new Gupta_Expression(this, AllowedPrecedence.HIGHER);
	}
}

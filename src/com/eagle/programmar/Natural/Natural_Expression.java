// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Natural_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Natural_Number number;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Natural_Expression()
	{
	    super(_operators);
	    setOperators(_operators);
	}

	public Natural_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Natural_LiteralExpression extends PrimaryOperator
	{
		public Natural_Literal literal;
	}
	
	public static @P(110) class Natural_System_Variable extends PrimaryOperator
	{
		public Natural_SystemVariable systemVariable;
	}
	
	public static @P(120) class Natural_NegativeExpression extends PrimaryOperator
	{
		public Natural_PunctuationChoice plusMinus = new Natural_PunctuationChoice("+", "-");
		public Natural_Expression expr;
	}

	public static @P(130) class Natural_VariableExpression extends PrimaryOperator
	{
		public Natural_Variable variable;
	}
	
	public static @P(140) class Natural_Function_Call extends PrimaryOperator
	{
		public Natural_FunctionCall functionCall;
	}

	public static @P(150) class Natural_ParenthesizedExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public Natural_Expression expression;
		public PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(160) class Natural_MultiplicativeExpression extends PrecedenceOperator
	{
		public Natural_Expression left = new Natural_Expression(this, AllowedPrecedence.ATLEAST);
		public Natural_PunctuationChoice timesDivide = new Natural_PunctuationChoice("*", "/");
		public Natural_Expression right = new Natural_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(170) class Natural_AdditiveExpression extends PrecedenceOperator
	{
		public Natural_Expression left = new Natural_Expression(this, AllowedPrecedence.ATLEAST);
		public Natural_PunctuationChoice plusMinus = new Natural_PunctuationChoice("+", "-");
		public Natural_Expression right = new Natural_Expression(this, AllowedPrecedence.HIGHER);
	}
}

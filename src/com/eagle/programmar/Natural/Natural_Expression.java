// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Natural_Expression extends PrecedenceChooser
{
	public Natural_Expression()
	{
	}
	
	public Natural_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(Natural_Literal.class);
		super.addTerm(Natural_Number.class);
		super.addTerm(Natural_SystemVariable.class);
		super.addTerm(Natural_NegativeExpression.class);
		super.addTerm(Natural_Variable.class);
		super.addTerm(Natural_FunctionCall.class);
		super.addTerm(Natural_ParenthesizedExpression.class);
		
		// Order is critical ...
		super.addOperator(Natural_MultiplicativeExpression.class);
		super.addOperator(Natural_AdditiveExpression.class);
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static class Natural_SystemVariable extends ExpressionTerm
	{
		public PunctuationStar star;
		public Natural_Identifier_Reference id;
		public @OPT Natural_Subscript subscript;
	}

	public static class Natural_ParenthesizedExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public Natural_Expression expression;
		public PunctuationRightParen rightParen;
	}
	
	public static class Natural_NegativeExpression extends ExpressionTerm
	{
		public Natural_PunctuationChoice plusMinus = new Natural_PunctuationChoice("+", "-");
		public Natural_Expression expr;
	}

	public static class Natural_FunctionCall extends ExpressionTerm
	{
		public Natural_KeywordChoice fnName = new Natural_KeywordChoice(
				Natural_Functions.builtinFunctions );
		public PunctuationLeftParen leftParen;
		public Natural_Expression expr;
		public PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class Natural_AdditiveExpression extends PrecedenceOperator
	{
		public Natural_Expression left = new Natural_Expression(this, AllowedPrecedence.ATLEAST);
		public Natural_PunctuationChoice plusMinus = new Natural_PunctuationChoice("+", "-");
		public Natural_Expression right = new Natural_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Natural_MultiplicativeExpression extends PrecedenceOperator
	{
		public Natural_Expression left = new Natural_Expression(this, AllowedPrecedence.ATLEAST);
		public Natural_PunctuationChoice timesDivide = new Natural_PunctuationChoice("*", "/");
		public Natural_Expression right = new Natural_Expression(this, AllowedPrecedence.HIGHER);
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.programmar.Natural.Terminals.Natural_Punctuation;
import com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;

public class Natural_Expression extends PrecedenceChooser
{
	public Natural_Expression()
	{
	}
	
	public Natural_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(Natural_Literal.class);
		super.addUnaryOperator(Natural_Number.class);
		super.addUnaryOperator(Natural_SystemVariable.class);
		super.addUnaryOperator(Natural_NegativeExpression.class);
		super.addUnaryOperator(Natural_Variable.class);
		super.addUnaryOperator(Natural_FunctionCall.class);
		super.addUnaryOperator(Natural_ParenthesizedExpression.class);
		
		// Order is critical ...
		super.addBinaryOperator(Natural_MultiplicativeExpression.class);
		super.addBinaryOperator(Natural_AdditiveExpression.class);
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static class Natural_SystemVariable extends UnaryOperator
	{
		public Natural_Punctuation star = new Natural_Punctuation('*');
		public Natural_Identifier_Reference id;
		public @OPT Natural_Subscript subscript;
	}

	public static class Natural_ParenthesizedExpression extends UnaryOperator
	{
		public Natural_Punctuation leftParen = new Natural_Punctuation('(');
		public Natural_Expression expression;
		public Natural_Punctuation rightParen = new Natural_Punctuation(')');
	}
	
	public static class Natural_NegativeExpression extends UnaryOperator
	{
		public Natural_PunctuationChoice plusMinus = new Natural_PunctuationChoice("+", "-");
		public Natural_Expression expr;
	}

	public static class Natural_FunctionCall extends UnaryOperator
	{
		public Natural_KeywordChoice fnName = new Natural_KeywordChoice(
				Natural_Functions.builtinFunctions );
		public Natural_Punctuation leftParen = new Natural_Punctuation('(');
		public Natural_Expression expr;
		public Natural_Punctuation rightParen = new Natural_Punctuation(')');
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class Natural_AdditiveExpression extends BinaryOperator
	{
		public Natural_Expression left = new Natural_Expression(this, AllowedPrecedence.ATLEAST);
		public Natural_PunctuationChoice plusMinus = new Natural_PunctuationChoice("+", "-");
		public Natural_Expression right = new Natural_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Natural_MultiplicativeExpression extends BinaryOperator
	{
		public Natural_Expression left = new Natural_Expression(this, AllowedPrecedence.ATLEAST);
		public Natural_PunctuationChoice timesDivide = new Natural_PunctuationChoice("*", "/");
		public Natural_Expression right = new Natural_Expression(this, AllowedPrecedence.HIGHER);
	}
}

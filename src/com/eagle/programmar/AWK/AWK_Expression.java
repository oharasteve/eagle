// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference;
import com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
import com.eagle.programmar.AWK.Terminals.AWK_Literal;
import com.eagle.programmar.AWK.Terminals.AWK_Number;
import com.eagle.programmar.AWK.Terminals.AWK_Pattern;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class AWK_Expression extends PrecedenceChooser
{
	public AWK_Expression()
	{
	}
	
	public AWK_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(AWK_Number.class);
		super.addUnaryOperator(AWK_FunctionCall.class);
		super.addUnaryOperator(AWK_Pattern.class);
		super.addUnaryOperator(AWK_PreIncrementExpression.class);
		super.addUnaryOperator(AWK_PreDecrementExpression.class);
		super.addUnaryOperator(AWK_PostIncrementExpression.class);
		super.addUnaryOperator(AWK_PostDecrementExpression.class);
		super.addUnaryOperator(AWK_Strings.class);
		super.addUnaryOperator(AWK_NotExpression.class);
		super.addUnaryOperator(AWK_ParenthesizedExpression.class);
		
		// Order is critical ...
		super.addBinaryOperator(AWK_SubscriptExpression.class);
		super.addBinaryOperator(AWK_MultiplicativeExpression.class);
		super.addBinaryOperator(AWK_AdditiveExpression.class);
		super.addBinaryOperator(AWK_RelationalExpression.class);
		super.addBinaryOperator(AWK_AndExpression.class);
		super.addBinaryOperator(AWK_OrExpression.class);
		super.addBinaryOperator(AWK_Assignment.class);
	}
	
	///////////////////////////////////////////////
	// Primary expressions

	public static class AWK_Strings extends TokenSequence
	{
		public TokenList<AWK_StringPiece> pieces;
		
		public static class AWK_StringPiece extends TokenChooser
		{
			public AWK_Literal literal;
			public AWK_Identifier_Reference id;
		}
	}
	
	public static class AWK_PreIncrementExpression extends UnaryOperator
	{
		public AWK_Punctuation preIncrementOperator = new AWK_Punctuation("++");
		public AWK_Expression expr;
	}

	public static class AWK_PreDecrementExpression extends UnaryOperator
	{
		public AWK_Punctuation preDecrementOperator = new AWK_Punctuation("--");
		public AWK_Expression expr;
	}
	
	public static class AWK_PostIncrementExpression extends UnaryOperator
	{
		public AWK_Variable var;		// Cannot be just AWK_Expression -- infinite loop
		public AWK_Punctuation postIncrementOperator = new AWK_Punctuation("++");
	}

	public static class AWK_PostDecrementExpression extends UnaryOperator
	{
		public AWK_Variable var;		// Cannot be just AWK_Expression -- infinite loop
		public AWK_Punctuation postDecrementOperator = new AWK_Punctuation("--");
	}

	public static class AWK_FunctionCall extends UnaryOperator
	{
		public AWK_KeywordChoice functionName = new AWK_KeywordChoice(AWK_Syntax.FUNCTIONS);
		public AWK_Punctuation leftParen = new AWK_Punctuation('(');
		public @OPT AWK_ArgumentList argList;
		public AWK_Punctuation rightParen = new AWK_Punctuation(')');
	}

	public static class AWK_NotExpression extends UnaryOperator
	{
		public AWK_Punctuation notOperator = new AWK_Punctuation('!');
		public AWK_Expression expr;
	}

	public static class AWK_ParenthesizedExpression extends UnaryOperator
	{
		public AWK_Punctuation leftParen = new AWK_Punctuation('(');
		public AWK_Expression expression;
		public AWK_Punctuation rightParen = new AWK_Punctuation(')');
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class AWK_Assignment extends BinaryOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice equals = new AWK_PunctuationChoice("=", "+=");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class AWK_OrExpression extends BinaryOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Punctuation orOperator = new AWK_Punctuation("||");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class AWK_AndExpression extends BinaryOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Punctuation andOperator = new AWK_Punctuation("&&");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class AWK_RelationalExpression extends BinaryOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice operator = new AWK_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class AWK_AdditiveExpression extends BinaryOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice operator = new AWK_PunctuationChoice("+", "-");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class AWK_MultiplicativeExpression extends BinaryOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice operator = new AWK_PunctuationChoice("*", "/", "%");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class AWK_SubscriptExpression extends BinaryOperator
	{
		public AWK_Expression expr = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Punctuation leftBracket = new AWK_Punctuation('[');
		public AWK_Expression subscr = new AWK_Expression(this, AllowedPrecedence.HIGHER);
		public AWK_Punctuation rightBracket = new AWK_Punctuation(']');
	}
}
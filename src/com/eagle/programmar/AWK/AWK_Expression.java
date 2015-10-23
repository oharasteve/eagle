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
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_Expression extends PrecedenceChooser
{
	public AWK_Expression()
	{
	}
	
	public AWK_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(AWK_Number.class);
		super.addTerm(AWK_FunctionCall.class);
		super.addTerm(AWK_Pattern.class);
		super.addTerm(AWK_PreIncrementExpression.class);
		super.addTerm(AWK_PreDecrementExpression.class);
		super.addTerm(AWK_PostIncrementExpression.class);
		super.addTerm(AWK_PostDecrementExpression.class);
		super.addTerm(AWK_Strings.class);
		super.addTerm(AWK_NotExpression.class);
		super.addTerm(AWK_ParenthesizedExpression.class);
		super.addTerm(AWK_DollarParensExpression.class);
		
		// Order is critical ...
		super.addOperator(AWK_SubscriptExpression.class);
		super.addOperator(AWK_MultiplicativeExpression.class);
		super.addOperator(AWK_AdditiveExpression.class);
		super.addOperator(AWK_RelationalExpression.class);
		super.addOperator(AWK_RegularExpression.class);
		super.addOperator(AWK_AndExpression.class);
		super.addOperator(AWK_OrExpression.class);
		super.addOperator(AWK_Assignment.class);
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
	
	public static class AWK_PreIncrementExpression extends ExpressionTerm
	{
		public AWK_Punctuation preIncrementOperator = new AWK_Punctuation("++");
		public AWK_Expression expr;
	}

	public static class AWK_PreDecrementExpression extends ExpressionTerm
	{
		public AWK_Punctuation preDecrementOperator = new AWK_Punctuation("--");
		public AWK_Expression expr;
	}
	
	public static class AWK_PostIncrementExpression extends ExpressionTerm
	{
		public AWK_Variable var;		// Cannot be just AWK_Expression -- infinite loop
		public AWK_Punctuation postIncrementOperator = new AWK_Punctuation("++");
	}

	public static class AWK_PostDecrementExpression extends ExpressionTerm
	{
		public AWK_Variable var;		// Cannot be just AWK_Expression -- infinite loop
		public AWK_Punctuation postDecrementOperator = new AWK_Punctuation("--");
	}

	public static class AWK_FunctionCall extends ExpressionTerm
	{
		public AWK_KeywordChoice functionName = new AWK_KeywordChoice(AWK_Syntax.FUNCTIONS);
		public PunctuationLeftParen leftParen;
		public @OPT AWK_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}

	public static class AWK_NotExpression extends ExpressionTerm
	{
		public AWK_Punctuation notOperator = new AWK_Punctuation('!');
		public AWK_Expression expr;
	}

	public static class AWK_ParenthesizedExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public AWK_Expression expression;
		public PunctuationRightParen rightParen;
	}

	public static class AWK_DollarParensExpression extends ExpressionTerm
	{
		public AWK_Punctuation dollar = new AWK_Punctuation('$');
		public PunctuationLeftParen leftParen;
		public AWK_Expression expression;
		public PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class AWK_Assignment extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice equals = new AWK_PunctuationChoice("=", "+=");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class AWK_OrExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Punctuation orOperator = new AWK_Punctuation("||");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class AWK_AndExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Punctuation andOperator = new AWK_Punctuation("&&");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class AWK_RegularExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice operator = new AWK_PunctuationChoice("~", "!~");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class AWK_RelationalExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice operator = new AWK_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class AWK_AdditiveExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice operator = new AWK_PunctuationChoice("+", "-");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class AWK_MultiplicativeExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice operator = new AWK_PunctuationChoice("*", "/", "%");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class AWK_SubscriptExpression extends PrecedenceOperator
	{
		public AWK_Expression expr = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationLeftBracket leftBracket;
		public AWK_Expression subscr = new AWK_Expression();
		public PunctuationRightBracket rightBracket;
	}
}
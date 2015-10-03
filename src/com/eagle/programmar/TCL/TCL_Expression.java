// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.programmar.TCL.Terminals.TCL_Literal;
import com.eagle.programmar.TCL.Terminals.TCL_Number;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;

public class TCL_Expression extends PrecedenceChooser
{
	public TCL_Expression()
	{
	}
	
	public TCL_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(TCL_Number.class);			
		super.addUnaryOperator(TCL_Literal.class);
		super.addUnaryOperator(TCL_Variable.class);
		super.addUnaryOperator(TCL_SignedExpression.class);
		super.addUnaryOperator(TCL_NotExpression.class);
		super.addUnaryOperator(TCL_BangExpression.class);
		super.addUnaryOperator(TCL_ParenthesizedExpression.class);
		
		// Order is critical ...
		super.addBinaryOperator(TCL_MultiplicativeExpression.class);
		super.addBinaryOperator(TCL_AdditiveExpression.class);
		super.addBinaryOperator(TCL_RelationalExpression.class);
		super.addBinaryOperator(TCL_ConditionalAndExpression.class);
		super.addBinaryOperator(TCL_ConditionalOrExpression.class);
	}
	
	///////////////////////////////////////////////
	// Primary expressions

	public static class TCL_SignedExpression extends UnaryOperator
	{
		public TCL_PunctuationChoice signedOperator = new TCL_PunctuationChoice("+", "-");
		public TCL_Expression expr;
	}

	public static class TCL_NotExpression extends UnaryOperator
	{
		public TCL_Keyword NOT = new TCL_Keyword("not");
		public TCL_Expression expr;
	}
	
	public static class TCL_BangExpression extends UnaryOperator
	{
		public TCL_Punctuation bang = new TCL_Punctuation('!');
		public TCL_Expression expr;
	}
	
	public static class TCL_ParenthesizedExpression extends UnaryOperator
	{
		public TCL_Punctuation leftParen = new TCL_Punctuation('(');
		public TCL_Expression expression;
		public TCL_Punctuation rightParen = new TCL_Punctuation(')');
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class TCL_ConditionalOrExpression extends BinaryOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_Keyword OR = new TCL_Keyword("or");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class TCL_ConditionalAndExpression extends BinaryOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_Keyword AND = new TCL_Keyword("and");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}
		
	public static class TCL_RelationalExpression extends BinaryOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_PunctuationChoice operator = new TCL_PunctuationChoice(
				"<", ">", "<=", ">=", "==", "<>");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}
		
	public static class TCL_AdditiveExpression extends BinaryOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_PunctuationChoice operator = new TCL_PunctuationChoice("+", "-");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class TCL_MultiplicativeExpression extends BinaryOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_PunctuationChoice operator = new TCL_PunctuationChoice("*", "/");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}
}

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template;

import com.eagle.programmar.Template.Symbols.Template_Identifier_Reference;
import com.eagle.programmar.Template.Terminals.Template_Literal;
import com.eagle.programmar.Template.Terminals.Template_Number;
import com.eagle.programmar.Template.Terminals.Template_Punctuation;
import com.eagle.programmar.Template.Terminals.Template_PunctuationChoice;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Template_Expression extends PrecedenceChooser
{
	public Template_Expression()
	{
	}
	
	public Template_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
	
	@Override
	protected void establishChoices() 
	{
		// Order matters
		super.addTerm(Template_Parens.class);
		super.addTerm(Template_Number.class);
		super.addTerm(Template_Literal.class);
		super.addTerm(Template_Identifier_Reference.class);
		super.addTerm(Template_Negative.class);
		super.addTerm(Template_NotOp.class);

		// Highest precedence first
		super.addOperator(Template_Multiplicative_Expression.class);
		super.addOperator(Template_Additive_Expression.class);
		super.addOperator(Template_Relational_Expression.class);
	}		

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static class Template_Parens extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public Template_Expression expr;
		public PunctuationRightParen rightParen;		
	}
	
	public static class Template_Negative extends ExpressionTerm
	{
		public Template_Punctuation negative = new Template_Punctuation('-');
		public Template_Expression expr;
	}
	
	public static class Template_NotOp extends ExpressionTerm
	{
		public Template_Punctuation NOT = new Template_Punctuation('!');
		public Template_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static class Template_Multiplicative_Expression extends PrecedenceOperator
	{
		public Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
		public Template_PunctuationChoice operator = new Template_PunctuationChoice("*", "/");
		public Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Template_Additive_Expression extends PrecedenceOperator implements EagleRunnable
	{
		public Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
		public Template_PunctuationChoice operator = new Template_PunctuationChoice("+", "-");
		public Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			String oper = operator.toString();
			if (oper.equals("+"))
			{
				interpreter.pushInt(leftValue + rightValue);
			}
			else // Must be "-"
			{
				interpreter.pushInt(leftValue - rightValue);
			}
		}
	}
	
	public static class Template_Relational_Expression extends PrecedenceOperator
	{
		public Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
		public Template_PunctuationChoice operator = new Template_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
		public Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);
	}
}

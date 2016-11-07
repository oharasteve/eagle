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
	protected static OperatorList _operators = new OperatorList();

	public @P(10) Template_Number number;
	public @P(20) Template_Literal literal;
	public @P(30) Template_Identifier_Reference id;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Template_Expression()
	{
	    super(_operators);
	}

	public Template_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static @P(100) class Template_Parens extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public Template_Expression expr;
		public PunctuationRightParen rightParen;		
	}
	
	public static @P(110) class Template_Negative extends PrimaryOperator
	{
		public Template_Punctuation negative = new Template_Punctuation('-');
		public Template_Expression expr;
	}
	
	public static @P(120) class Template_NotOp extends PrimaryOperator
	{
		public Template_Punctuation NOT = new Template_Punctuation('!');
		public Template_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(130) class Template_Multiplicative_Expression extends PrecedenceOperator
	{
		public Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
		public Template_PunctuationChoice operator = new Template_PunctuationChoice("*", "/");
		public Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(140) class Template_Additive_Expression extends PrecedenceOperator implements EagleRunnable
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
	
	public static @P(150) class Template_Relational_Expression extends PrecedenceOperator
	{
		public Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
		public Template_PunctuationChoice operator = new Template_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
		public Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);
	}
}

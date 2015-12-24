// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

package com.eagle.programmar.CMacro;

import com.eagle.math.EagleValue;
import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.Terminals.C_Character_Literal;
import com.eagle.programmar.C.Terminals.C_HexNumber;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Expression extends PrecedenceChooser
{
	public CMacro_Expression()
	{
	}
	
	public CMacro_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(C_Number.class);
		super.addTerm(C_HexNumber.class);
		super.addTerm(C_Literal.class);
		super.addTerm(C_Character_Literal.class);
		super.addTerm(CMacro_FunctionCall.class);
		super.addTerm(CMacro_Identifier_Reference.class);
		super.addTerm(CMacro_NotExpression.class);
		super.addTerm(CMacro_ParenthesizedExpression.class);
		super.addTerm(CMacro_SymbolExpression.class);
		
		// Order is critical ...
		super.addOperator(CMacro_MultiplicativeExpression.class);
		super.addOperator(CMacro_AdditiveExpression.class);
		super.addOperator(CMacro_RelationalExpression.class);
		super.addOperator(CMacro_EqualityExpression.class);
		super.addOperator(CMacro_BitwiseAndExpression.class);
		super.addOperator(CMacro_ExclusiveOrExpression.class);
		super.addOperator(CMacro_BitwiseOrExpression.class);
		super.addOperator(CMacro_ConditionalAndExpression.class);
		super.addOperator(CMacro_ConditionalOrExpression.class);
		super.addOperator(CMacro_ConcatenateExpression.class);
	}
	
	///////////////////////////////////////////////
	// Primary expressions

	public static class CMacro_SymbolExpression extends ExpressionTerm
	{
		public C_Punctuation poundOperator = new C_Punctuation('#');
		public CMacro_Expression expr;
	}
	
	public static class CMacro_NotExpression extends ExpressionTerm implements EagleRunnable
	{
		public C_Punctuation notOperator = new C_Punctuation('!');
		public CMacro_Expression expr;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean val = interpreter.getBoolValue(expr);
			interpreter.pushBool(! val);
		}
	}
	
	public static class CMacro_ParenthesizedExpression extends ExpressionTerm implements EagleRunnable
	{
		public PunctuationLeftParen leftParen;
		public CMacro_Expression expression;
		public PunctuationRightParen rightParen;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expression);
			interpreter.pushEagleValue(val);
		}
	}

	public static class CMacro_FunctionCall extends ExpressionTerm implements EagleRunnable
	{
		public C_Keyword DEFINED = new C_Keyword("defined");
		public PunctuationLeftParen leftParen;
		public CMacro_Identifier_Reference variable;
		public PunctuationRightParen rightParen;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean val = interpreter._symbolTable.isDefined(variable.toString());
			interpreter.pushBool(val);
		}
	}
				
	///////////////////////////////////////////////
	// Binary expressions

	public static class CMacro_ConcatenateExpression extends PrecedenceOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation poundOperator = new C_Punctuation("##");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class CMacro_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation orOperator = new C_Punctuation("||");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean leftVal = interpreter.getBoolValue(left);
			if (leftVal)
			{
				// Short circuit a bit
				interpreter.pushBool(true);
			}
			else
			{
				boolean rightVal = interpreter.getBoolValue(right);
				interpreter.pushBool(rightVal);
			}
		}
	}
	
	public static class CMacro_ConditionalAndExpression extends PrecedenceOperator implements EagleRunnable
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation andOperator = new C_Punctuation("&&");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean leftVal = interpreter.getBoolValue(left);
			if (! leftVal)
			{
				// Short circuit a bit
				interpreter.pushBool(false);
			}
			else
			{
				boolean rightVal = interpreter.getBoolValue(right);
				interpreter.pushBool(rightVal);
			}
		}
	}
		
	public static class CMacro_BitwiseOrExpression extends PrecedenceOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseOrOperator = new C_Punctuation('|');
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CMacro_ExclusiveOrExpression extends PrecedenceOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseXOrOperator = new C_Punctuation('^');
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CMacro_BitwiseAndExpression extends PrecedenceOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseAndOperator = new C_Punctuation('&');
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CMacro_EqualityExpression extends PrecedenceOperator implements EagleRunnable
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("==", "!=");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftVal = interpreter.getIntValue(left);
			int rightVal = interpreter.getIntValue(right);
			String oper = operator.getValue();
			if (oper.equals("==")) interpreter.pushBool(leftVal == rightVal);
			else if (oper.equals("!=")) interpreter.pushBool(leftVal != rightVal);
			else throw new RuntimeException("Unexpected operator: " + oper);
		}
	}
	
	public static class CMacro_RelationalExpression extends PrecedenceOperator implements EagleRunnable
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("<", ">", "<=", ">=");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftVal = interpreter.getIntValue(left);
			int rightVal = interpreter.getIntValue(right);
			String oper = operator.getValue();
			if (oper.equals("<")) interpreter.pushBool(leftVal < rightVal);
			else if (oper.equals(">")) interpreter.pushBool(leftVal > rightVal);
			else if (oper.equals("<=")) interpreter.pushBool(leftVal >= rightVal);
			else if (oper.equals(">=")) interpreter.pushBool(leftVal >= rightVal);
			else throw new RuntimeException("Unexpected operator: " + oper);
		}
	}

	public static class CMacro_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("+", "-");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftVal = interpreter.getIntValue(left);
			int rightVal = interpreter.getIntValue(right);
			interpreter.pushInt(leftVal + rightVal);
		}
	}

	public static class CMacro_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("*", "/", "%");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftVal = interpreter.getIntValue(left);
			int rightVal = interpreter.getIntValue(right);
			interpreter.pushInt(leftVal * rightVal);
		}
	}
	
	//////////////////////////////////////////////////////////////
	// Evaluation routine, for macros
	
	public boolean getBooleanValue(CMacro_Preprocess preprocessor)
	{
		AbstractToken which = _whichToken;
		if (! (which instanceof EagleRunnable))
		{
			throw new RuntimeException("Need to implement EagleRunnable for " + which);
		}
		
		EagleRunnable runnable = (EagleRunnable) which;
		CMacro_Interpreter interpreter = new CMacro_Interpreter(preprocessor._symbolTable);
		runnable.interpret(interpreter);
		boolean val = interpreter.getBoolValue(this);
		return val;
	}
}

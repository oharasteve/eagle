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
	private static OperatorList _operators = new OperatorList();

	public @P(10) C_Number number;
	public @P(20) C_HexNumber hex;
	public @P(30) C_Literal literal;
	public @P(40) C_Character_Literal characters;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public CMacro_Expression()
	{
	    super(_operators);
	}

	public CMacro_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class CMacro_FunctionCall extends PrimaryOperator implements EagleRunnable
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
	
	public static @P(110) class CMacro_IdentifierExpression extends PrimaryOperator implements EagleRunnable
	{
		public CMacro_Identifier_Reference identifier;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(identifier);
		}
	}

	public static @P(120) class CMacro_NotExpression extends PrimaryOperator implements EagleRunnable
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
	
	public static @P(130) class CMacro_ParenthesizedExpression extends PrimaryOperator implements EagleRunnable
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

	public static @P(140) class CMacro_SymbolExpression extends PrimaryOperator
	{
		public C_Punctuation poundOperator = new C_Punctuation('#');
		public CMacro_Expression expr;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static @P(150) class CMacro_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
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
	
	public static @P(160) class CMacro_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
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

	public static @P(170) class CMacro_RelationalExpression extends PrecedenceOperator implements EagleRunnable
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

	public static @P(180) class CMacro_EqualityExpression extends PrecedenceOperator implements EagleRunnable
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
	
	public static @P(190) class CMacro_BitwiseAndExpression extends PrecedenceOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseAndOperator = new C_Punctuation('&');
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(200) class CMacro_ExclusiveOrExpression extends PrecedenceOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseXOrOperator = new C_Punctuation('^');
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(210) class CMacro_BitwiseOrExpression extends PrecedenceOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseOrOperator = new C_Punctuation('|');
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(220) class CMacro_ConditionalAndExpression extends PrecedenceOperator implements EagleRunnable
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
		
	public static @P(230) class CMacro_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
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
	
	public static @P(240) class CMacro_ConcatenateExpression extends PrecedenceOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation poundOperator = new C_Punctuation("##");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
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

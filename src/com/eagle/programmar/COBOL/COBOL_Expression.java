// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 9, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.COBOL_Expression.COBOL_RelationCondition.COBOL_RelationalOperator;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tests.EagleTestable;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) COBOL_Literal literal;
	public @P(20) COBOL_Number number;
	public @P(30) COBOL_HexNumber hex;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public COBOL_Expression()
	{
	    super(_operators);
	}

	public COBOL_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class COBOL_BuiltIn extends PrimaryOperator implements EagleTestable, EagleRunnable
	{
		public COBOL_KeywordChoice logicalConstant = new COBOL_KeywordChoice("FALSE", "TRUE", "ANY",
				"ZERO", "ZEROS", "LOW-VALUES", "HIGH-VALUES", "SPACE", "SPACES");

		@Override
		public void addTests()
		{
			COBOL_Tests.addTestExpr("TrueFalse1", "FALSE OR FALSE", "false");
			COBOL_Tests.addTestExpr("TrueFalse2", "FALSE OR TRUE", "true");
			COBOL_Tests.addTestExpr("TrueFalse3", "TRUE AND FALSE", "false");
			COBOL_Tests.addTestExpr("TrueFalse4", "FALSE AND TRUE", "false");
		}

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			String name = logicalConstant.toString();
			if (name.equals("FALSE"))
			{
				interpreter.pushBool(false);
			}
			else if (name.equals("TRUE"))
			{
				interpreter.pushBool(true);
			}
			else throw new RuntimeException("Can't handle BuiltIn's other than TRUE/FALSE: " + name);
		}
	}
	
	public static @P(110) class COBOL_ParenthesizedExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public COBOL_Expression expression;
		public PunctuationRightParen rightParen;
	}

	public static @P(120) class COBOL_ExpressionFunction extends PrimaryOperator
	{
		public COBOL_Keyword FUNCTION = new COBOL_Keyword("FUNCTION");
		public COBOL_Identifier_Reference func;
		public PunctuationLeftParen leftParen;
		public COBOL_Expression parameter;
		public PunctuationRightParen rightParen;
	}

	public static @P(130) class COBOL_ClassCondition extends PrimaryOperator
	{
		public COBOL_Variable var;
		public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public COBOL_KeywordChoice type = new COBOL_KeywordChoice(
				"POSITIVE", "NEGATIVE", "ZERO", "NUMERIC", 
				"ALPHABETIC", "ALPHABETIC-LOWER", "ALPHABETIC-UPPER");
	}
	
	public static @P(140) class COBOL_NotCondition extends PrimaryOperator
	{
		public COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public COBOL_Expression cond;
	}
	
	public static @P(150) class COBOL_VariableExpression extends PrimaryOperator
	{
		public COBOL_Variable variable;
	}

	///////////////////////////////////////////////
	// Binary expressions
	
	public static @P(160) class COBOL_ThroughExpression extends PrecedenceOperator
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_Keyword THRU = new COBOL_Keyword("THRU");
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(170) class COBOL_ConcatenateExpression extends PrecedenceOperator
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_Punctuation ampersand = new COBOL_Punctuation('&');
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(180) class COBOL_MultiplicativeExpression extends PrecedenceOperator
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_PunctuationChoice timesDivide = new COBOL_PunctuationChoice("*", "/");
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(190) class COBOL_AdditiveExpression extends PrecedenceOperator
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_PunctuationChoice plusMinus = new COBOL_PunctuationChoice("+", "-");
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(200) class COBOL_RelationCondition extends PrecedenceOperator
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public COBOL_RelationalOperator relationalOperator;
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);

		public static class COBOL_RelationalOperator extends TokenChooser
		{
			public @CHOICE COBOL_PunctuationChoice operator = new COBOL_PunctuationChoice("<=", "<", "=", ">=", ">");
			
			public @CHOICE static class COBOL_Greater extends TokenSequence
			{
				public COBOL_Keyword GREATER = new COBOL_Keyword("GREATER");
				public @OPT COBOL_Keyword THAN = new COBOL_Keyword("THAN");
				public @OPT COBOL_OrEqual orEqual;
			}
			
			public @CHOICE static class COBOL_Equal extends TokenSequence
			{
				public COBOL_Keyword EQUAL = new COBOL_Keyword("EQUAL");
				public @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
			}
			
			public @CHOICE static class COBOL_Less extends TokenSequence
			{
				public COBOL_Keyword LESS = new COBOL_Keyword("LESS");
				public @OPT COBOL_Keyword THAN = new COBOL_Keyword("THAN");
				public @OPT COBOL_OrEqual orEqual;
			}

			public @CHOICE static class COBOL_OrEqual extends TokenSequence
			{
				public COBOL_Keyword OR = new COBOL_Keyword("OR");
				public COBOL_Keyword EQUAL = new COBOL_Keyword("EQUAL");
				public @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
			}
		}
	}

	public static @P(210) class COBOL_AndCondition extends PrecedenceOperator implements EagleRunnable
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_Keyword AND = new COBOL_Keyword("AND");
		public @OPT COBOL_RelationalOperator relationalOperator;
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean leftValue = interpreter.getBoolValue(left);
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue && rightValue);
		}
	}

	public static @P(220) class COBOL_OrCondition extends PrecedenceOperator implements EagleRunnable
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_Keyword OR = new COBOL_Keyword("OR");
		public @OPT COBOL_RelationalOperator relationalOperator;
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean leftValue = interpreter.getBoolValue(left);
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue || rightValue);
		}
	}
}

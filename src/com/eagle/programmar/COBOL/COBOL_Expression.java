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
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class COBOL_Expression extends PrecedenceChooser
{
	public COBOL_Expression()
	{
	}
	
	public COBOL_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}

	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(COBOL_Literal.class);	
		super.addUnaryOperator(COBOL_Number.class);
		super.addUnaryOperator(COBOL_HexNumber.class);
		super.addUnaryOperator(COBOL_BuiltIn.class);
		super.addUnaryOperator(COBOL_ParenthesizedExpression.class);
		super.addUnaryOperator(COBOL_ExpressionFunction.class);
		super.addUnaryOperator(COBOL_ClassCondition.class);
		super.addUnaryOperator(COBOL_NotCondition.class);
		super.addUnaryOperator(COBOL_Variable.class);

		// Order is critical ...
		super.addBinaryOperator(COBOL_ThroughExpression.class);
		super.addBinaryOperator(COBOL_ConcatenateExpression.class);
		super.addBinaryOperator(COBOL_MultiplicativeExpression.class);
		super.addBinaryOperator(COBOL_AdditiveExpression.class);
		super.addBinaryOperator(COBOL_RelationCondition.class);
		super.addBinaryOperator(COBOL_AndCondition.class);
		super.addBinaryOperator(COBOL_OrCondition.class);
	}		
	
	///////////////////////////////////////////////
	// Primary expressions
	
	public static class COBOL_ParenthesizedExpression extends UnaryOperator
	{
		public COBOL_Punctuation leftParen = new COBOL_Punctuation('(');
		public COBOL_Expression expression;
		public COBOL_Punctuation rightParen = new COBOL_Punctuation(')');
	}

	public static class COBOL_BuiltIn extends UnaryOperator implements EagleTestable, EagleRunnable
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
	
	public static class COBOL_ExpressionFunction extends UnaryOperator
	{
		public COBOL_Keyword FUNCTION = new COBOL_Keyword("FUNCTION");
		public COBOL_Identifier_Reference func;
		public COBOL_Punctuation leftParen = new COBOL_Punctuation('(');
		public COBOL_Expression parameter;
		public COBOL_Punctuation rightParen = new COBOL_Punctuation(')');
	}
	
	public static class COBOL_NotCondition extends UnaryOperator
	{
		public COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public COBOL_Expression cond;
	}

	public static class COBOL_ClassCondition extends UnaryOperator
	{
		public COBOL_Variable var;
		public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public COBOL_KeywordChoice type = new COBOL_KeywordChoice(
				"POSITIVE", "NEGATIVE", "ZERO", "NUMERIC", 
				"ALPHABETIC", "ALPHABETIC-LOWER", "ALPHABETIC-UPPER");
	}

	///////////////////////////////////////////////
	// Binary expressions
	
	public static class COBOL_RelationCondition extends BinaryOperator
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public COBOL_RelationalOperator relationalOperator;
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);

		public static class COBOL_RelationalOperator extends TokenChooser
		{
			public COBOL_PunctuationChoice operator = new COBOL_PunctuationChoice("<=", "<", "=", ">=", ">");
			
			public static class COBOL_Greater extends TokenSequence
			{
				public COBOL_Keyword GREATER = new COBOL_Keyword("GREATER");
				public @OPT COBOL_Keyword THAN = new COBOL_Keyword("THAN");
				public @OPT COBOL_OrEqual orEqual;
			}
			
			public static class COBOL_Equal extends TokenSequence
			{
				public COBOL_Keyword EQUAL = new COBOL_Keyword("EQUAL");
				public @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
			}
			
			public static class COBOL_Less extends TokenSequence
			{
				public COBOL_Keyword LESS = new COBOL_Keyword("LESS");
				public @OPT COBOL_Keyword THAN = new COBOL_Keyword("THAN");
				public @OPT COBOL_OrEqual orEqual;
			}

			public static class COBOL_OrEqual extends TokenSequence
			{
				public COBOL_Keyword OR = new COBOL_Keyword("OR");
				public COBOL_Keyword EQUAL = new COBOL_Keyword("EQUAL");
				public @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
			}
		}
	}

	public static class COBOL_OrCondition extends BinaryOperator implements EagleRunnable
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

	public static class COBOL_AndCondition extends BinaryOperator implements EagleRunnable
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

	public static class COBOL_AdditiveExpression extends BinaryOperator
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_PunctuationChoice plusMinus = new COBOL_PunctuationChoice("+", "-");
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class COBOL_MultiplicativeExpression extends BinaryOperator
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_PunctuationChoice timesDivide = new COBOL_PunctuationChoice("*", "/");
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class COBOL_ConcatenateExpression extends BinaryOperator
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_Punctuation ampersand = new COBOL_Punctuation('&');
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class COBOL_ThroughExpression extends BinaryOperator
	{
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_Keyword THRU = new COBOL_Keyword("THRU");
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}
}
// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_Class.Java_ClassElement;
import com.eagle.programmar.Java.Java_Type.Java_GenericType;
import com.eagle.programmar.Java.Terminals.Java_Character_Literal;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_HexNumber;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tests.EagleTestable;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Java_HexNumber hex;
	public @P(20) Java_Number number;
	public @P(30) Java_Literal literal;
	public @P(40) Java_Character_Literal characters;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Java_Expression()
	{
	    super(_operators);
	}

	public Java_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Java_DotClass extends PrimaryOperator
	{
		public Java_Type jtype;
		public @NOSPACE PunctuationPeriod dot;
		public @NOSPACE Java_Keyword CLASS = new Java_Keyword("class");
	}
	
	public static @P(110) class Java_CastExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public Java_Type jtype;
		public PunctuationRightParen rightParen;
		public Java_Expression expr;
	}

	public static @P(120) class Java_ExpressionList extends PrimaryOperator
	{
		public PunctuationLeftBrace leftBrace;
		public @OPT TokenList<Java_Comment> comment;
		public @OPT Java_ArgumentList valueList;
		public PunctuationRightBrace rightBrace;
	}
	
	public static @P(130) class Java_InterfaceCreationWithMethod extends PrimaryOperator
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_KeywordChoice jinterface = new Java_KeywordChoice( 
				"Runnable", "ActionListener", "WindowAdapter");
		public PunctuationLeftParen leftParen;
		public PunctuationRightParen rightParen;
		public PunctuationLeftBrace leftBrace;
		public Java_Method method;
		public PunctuationRightBrace rightBrace;
	}

	public static @P(140) class Java_ClassCreationExpression extends PrimaryOperator
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_Type jtype;
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE @OPT TokenList<Java_Comment> comments;
		public @NOSPACE @OPT Java_ArgumentList argList;
		public @NOSPACE PunctuationRightParen rightParen;
		public @OPT Java_ClassOverride override;

		public static class Java_ClassOverride extends TokenSequence
		{
			public PunctuationLeftBrace leftBrace;
			public TokenList<Java_ClassElement> elementList;
			public PunctuationRightBrace rightBrace;
		}
	}
	
	public static @P(150) class Java_ClassCreationWithInitializers extends PrimaryOperator
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_Type jtype;
		public PunctuationLeftBrace leftBrace;
		public @OPT Java_ArgumentList valueList;
		public PunctuationRightBrace rightBrace;
	}
	
	public static @P(160) class Java_ClassCreationWithSubscript extends PrimaryOperator
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_Type jtype;
		public TokenList<Java_Subscript> subscripts;
	}
	
	public static @P(170) class Java_MethodInvocation extends PrimaryOperator implements EagleRunnable
	{
		public Java_Variable methodName;
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE @OPT Java_ArgumentList argList;
		public @NOSPACE PunctuationRightParen rightParen;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			// Assume System.out.println(exp);
			EagleValue result = interpreter.getEagleValue(argList.arg);
			System.out.println(result.toString());
		}
	}
	
	public static @P(180) class Java_PreIncrementExpression extends PrimaryOperator
	{
		public Java_Punctuation preIncrementOperator = new Java_Punctuation("++");
		public @NOSPACE Java_Variable var;
	}

	public static @P(190) class Java_PreDecrementExpression extends PrimaryOperator
	{
		public Java_Punctuation preDecrementOperator = new Java_Punctuation("--");
		public @NOSPACE Java_Variable var;
	}
	
	public static @P(200) class Java_PostIncrementExpression extends PrimaryOperator
	{
		public Java_Variable var;
		public @NOSPACE Java_Punctuation postIncrementOperator = new Java_Punctuation("++");
	}

	public static @P(210) class Java_PostDecrementExpression extends PrimaryOperator
	{
		public Java_Variable var;
		public @NOSPACE Java_Punctuation postDecrementOperator = new Java_Punctuation("--");
	}
	
	public static @P(220) class Java_NegativeExpression extends PrimaryOperator
	{
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("-", "+");
		public Java_Expression expr;
	}

	public static @P(230) class Java_LogicalNotExpression extends PrimaryOperator
	{
		public Java_Punctuation logicalNotOperator = new Java_Punctuation('~');
		public Java_Expression expr;
	}
	
	public static @P(240) class Java_NotExpression extends PrimaryOperator
	{
		public Java_Punctuation notOperator = new Java_Punctuation('!');
		public Java_Expression expr;
	}
	
	public static @P(250) class Java_BuiltIn extends PrimaryOperator implements EagleTestable, EagleRunnable
	{
		public Java_KeywordChoice builtinConstant = new Java_KeywordChoice("false", "true", "null", "this", "super");
		
		@Override
		public void addTests()
		{
			Java_Tests.addTestExpr("TrueFalse1", "false || false", "false");
			Java_Tests.addTestExpr("TrueFalse2", "false || true", "true");
			Java_Tests.addTestExpr("TrueFalse3", "true && false", "false");
			Java_Tests.addTestExpr("TrueFalse4", "false && true", "false");
		}

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			String name = builtinConstant.toString();
			if (name.equals("false"))
			{
				interpreter.pushBool(false);
			}
			else if (name.equals("true"))
			{
				interpreter.pushBool(true);
			}
			else throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + name);
		}
	}
	
	public static @P(260) class Java_VariableExpression extends PrimaryOperator implements EagleRunnable
	{
		public Java_Variable variable;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}
	}
	
	public static @P(270) class Java_ParenthesizedExpression extends PrimaryOperator implements EagleRunnable
	{
		public PunctuationLeftParen leftParen;
		public Java_Expression expression;
		public PunctuationRightParen rightParen;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(expression);
		}
	}
	
	public static @P(280) class Java_CommentExpression extends PrimaryOperator
	{
		public Java_Comment comment;
		public Java_Expression expr;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(290) class Java_SubscriptExpression extends PrecedenceOperator
	{
		public Java_Expression expr = new Java_Expression(this, AllowedPrecedence.HIGHER);
		public PunctuationLeftBracket leftBracket;
		public @OPT Java_Expression subscr;
		public PunctuationRightBracket rightBracket;
	}

	public static @P(300) class Java_Subfield extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @NOSPACE PunctuationPeriod dot;
		public @OPT @NOSPACE Java_GenericType genericType;
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(310) class Java_MultiplicativeExpression extends PrecedenceOperator implements EagleTestable, EagleRunnable
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("*", "/", "%");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		@Override
		public void addTests()
		{
			Java_Tests.addTestExpr("Mult1", "2 * 3 * 4", "24");
			Java_Tests.addTestExpr("Divide1", "24 / 6", "4");
			Java_Tests.addTestExpr("Mod1", "43 % 10", "3");
		}

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			if (operator.toString().equals("*"))
			{
				interpreter.pushInt(leftValue * rightValue);
			}
			else if (operator.toString().equals("/"))
			{
				interpreter.pushInt(leftValue / rightValue);
			}
			else
			{
				interpreter.pushInt(leftValue % rightValue);
			}
		}
	}

	public static @P(320) class Java_AdditiveExpression extends PrecedenceOperator implements EagleTestable, EagleRunnable
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("+", "-");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		@Override
		public void addTests()
		{
			Java_Tests.addTestExpr("Add1", "2 + 3 + 4", "9");
			Java_Tests.addTestExpr("Add2", "2 + 3 * 4", "14");
			Java_Tests.addTestExpr("Add3", "2 * 3 + 4", "10");
			Java_Tests.addTestExpr("Subtract1", "27-10-1", "16");
			Java_Tests.addTestExpr("Subtract2", "27-(10-1)", "18");
			Java_Tests.addTestExpr("FiveSix", "five + six", "11");
		}

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			if (operator.toString().equals("+"))
			{
				interpreter.pushInt(leftValue + rightValue);
			}
			else
			{
				interpreter.pushInt(leftValue - rightValue);
			}
		}
	}

	public static @P(330) class Java_ShiftExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_PunctuationChoice operator = new Java_PunctuationChoice(">>>", "<<", ">>");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(340) class Java_RelationalExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("<", ">", "<=", ">=");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(350) class Java_InstanceOfExpression extends PrecedenceOperator
	{
		public Java_Expression expr = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Keyword instanceOperator = new Java_Keyword("instanceof");
		public Java_Type type;
	}

	public static @P(360) class Java_EqualityExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("==", "!=");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(370) class Java_AndExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation bitwiseAndOperator = new Java_Punctuation('&');
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(380) class Java_ExclusiveOrExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation bitwiseXOrOperator = new Java_Punctuation('^');
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(390) class Java_InclusiveOrExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation bitwiseOrOperator = new Java_Punctuation('|');
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(400) class Java_ConditionalAndExpression extends PrecedenceOperator implements EagleRunnable
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation andOperator = new Java_Punctuation("&&");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean leftValue = interpreter.getBoolValue(left);
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue && rightValue);
		}
	}
	
	public static @P(410) class Java_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation orOperator = new Java_Punctuation("||");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean leftValue = interpreter.getBoolValue(left);
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue || rightValue);
		}
	}
	
	public static @P(420) class Java_AssignmentExpression extends PrecedenceOperator
	{
		public Java_Expression var = new Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_AssignmentOperator assignmentOperator;
		public Java_Expression expr;

		public static class Java_AssignmentOperator extends TokenChooser
		{
			public @CHOICE Java_PunctuationChoice equals = new Java_PunctuationChoice(
					"=",
					"*=",
					"/=",
					"%=",
					"+=",
					"-=",
					"<<=",
					">>=",
					">>>=",
					"&=",
					"^=",
					"|=");
		}
	}

	public static @P(430) class Java_TrueFalseExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_Punctuation questionMark = new Java_Punctuation('?');
		public Java_Expression middle = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationColon colon;
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_HexNumber;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_RegularExpression;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Javascript_HexNumber hex;
	public @P(20) Javascript_Number number;
	public @P(30) Javascript_Literal literal;
	public @P(40) Javascript_RegularExpression regEx;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Javascript_Expression()
	{
	    super(_operators);
	}

	public Javascript_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Javascript_DotClass extends PrimaryOperator
	{
		public Javascript_Type jtype;
		public PunctuationPeriod dot;
		public Javascript_Keyword CLASS = new Javascript_Keyword("class");
	}
	
	public static @P(110) class Javascript_CastExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public @NOSPACE Javascript_Type jtype;
		public @NOSPACE PunctuationRightParen rightParen;
		public Javascript_Expression expr;
	}
	
	public static @P(120) class Javascript_ExpressionList extends PrimaryOperator
	{
		public PunctuationLeftBrace leftBrace;
		public @OPT @NOSPACE TokenList<Javascript_Comment> comment;
		public @OPT @NOSPACE Javascript_ArgumentList valueList;
		public @NOSPACE PunctuationRightBrace rightBrace;
	}
	
	public static @P(130) class Javascript_StringFunction extends PrimaryOperator
	{
		public Javascript_Keyword STRING = new Javascript_Keyword("String");
		public PunctuationLeftParen leftParen;
		public Javascript_Expression expr;
		public PunctuationRightParen rightParen;
	}

	public static @P(140) class Javascript_DeleteExpression extends PrimaryOperator
	{
		public Javascript_Keyword DELETE = new Javascript_Keyword("delete");
		public Javascript_Expression expr;
	}

	public static @P(150) class Javascript_ClassCreationExpression extends PrimaryOperator
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
		public Javascript_ParenthesizedExpression arguments;
	}
	
	public static @P(160) class Javascript_ClassCreationWithInitializers extends PrimaryOperator
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
		public PunctuationLeftBrace leftBrace;
		public Javascript_ArgumentList valueList;
		public PunctuationRightBrace rightBrace;
	}
	
	public static @P(170) class Javascript_ClassCreationWithSubscript extends PrimaryOperator
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
		public TokenList<Javascript_Subscript> subscripts;
	}

	public static @P(180) class Javascript_NewNoArgsExpression extends PrimaryOperator
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
	}
	
	public static @P(190) class Javascript_MethodInvocation extends PrimaryOperator
	{
		public Javascript_Variable methodName;
		public @NOSPACE Javascript_ParenthesizedExpression arguments;
	}
	
	public static @P(200) class Javascript_PreIncrementExpression extends PrimaryOperator
	{
		public Javascript_Punctuation preIncrementOperator = new Javascript_Punctuation("++");
		public @NOSPACE Javascript_Variable var;
	}

	public static @P(210) class Javascript_PreDecrementExpression extends PrimaryOperator
	{
		public Javascript_Punctuation preDecrementOperator = new Javascript_Punctuation("--");
		public @NOSPACE Javascript_Variable var;
	}
	
	public static @P(220) class Javascript_PostIncrementExpression extends PrimaryOperator
	{
		public Javascript_Variable var;
		public @NOSPACE Javascript_Punctuation postIncrementOperator = new Javascript_Punctuation("++");
	}

	public static @P(230) class Javascript_PostDecrementExpression extends PrimaryOperator
	{
		public Javascript_Variable var;
		public @NOSPACE Javascript_Punctuation postDecrementOperator = new Javascript_Punctuation("--");
	}
	
	public static @P(240) class Javascript_NegativeExpression extends PrimaryOperator
	{
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("-", "+");
		public Javascript_Expression expr;
	}

	public static @P(250) class Javascript_LogicalNotExpression extends PrimaryOperator
	{
		public Javascript_Punctuation logicalNotOperator = new Javascript_Punctuation('~');
		public Javascript_Expression expr;
	}
	
	public static @P(260) class Javascript_NotExpression extends PrimaryOperator
	{
		public Javascript_Punctuation notOperator = new Javascript_Punctuation('!');
		public Javascript_Expression expr;
	}
	
	public static @P(270) class Javascript_BuiltIn extends PrimaryOperator
	{
		public Javascript_KeywordChoice logicalConstant = new Javascript_KeywordChoice(
				"false",
				"null",
				"String",
				"super",
				"this",
				"true"
			);
	}
	
	public static @P(280) class Javascript_VariableExpression extends PrimaryOperator
	{
		public Javascript_Variable variable;
	}
	
	public static @P(290) class Javascript_StrangeFunction extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public @NOSPACE Javascript_Number zero;
		public @NOSPACE PunctuationComma comma;
		public Javascript_Variable function;
		public @NOSPACE PunctuationRightParen rightParen;
		public @NEWLINE Javascript_ParenthesizedExpression arguments;
	}

	public static @P(300) class Javascript_ParenthesizedFunction extends PrimaryOperator
	{
		public @INDENT PunctuationLeftParen leftParen;
		public Javascript_Function function;
		public @OUTDENT PunctuationRightParen rightParen;
		public Javascript_ParenthesizedExpression arguments;
	}

	public static @P(310) class Javascript_Parenthesized_Expression extends PrimaryOperator
	{
		public Javascript_ParenthesizedExpression expr;
	}
	
	public static @P(320) class Javascript_SimpleArray extends PrimaryOperator
	{
		public PunctuationLeftBracket leftBracket;
		public @OPT Javascript_Expression expr;
		public @OPT TokenList<Javascript_MoreArray> more;
		public PunctuationRightBracket rightBracket;
		
		public static class Javascript_MoreArray extends TokenSequence
		{
			public PunctuationComma comma;
			public @OPT Javascript_Expression expr;
		}
	}
	
	public static @P(330) class Javascript_CommentExpression extends PrimaryOperator
	{
		public Javascript_Comment comment;
		public Javascript_Expression expr;
	}
	
	public static @P(340) class Javascript_Dictionary extends PrimaryOperator
	{
		// Don't use @INDENT here. Messes up 'return' statements that return a dictionary.
		public PunctuationLeftBrace leftBrace;
		public SeparatedList<Javascript_DictionaryItem,PunctuationComma> items;
		public @OPT PunctuationComma comma;
		public @OPT Javascript_Comment comment;
		public PunctuationRightBrace rightBrace;
		
		public static class Javascript_DictionaryItem extends TokenSequence
		{
			public Javascript_Expression field;
			public PunctuationColon colon;
			public Javascript_Expression expr;
		}
	}

	public static @P(350) class Javascript_FunctionExpr extends PrimaryOperator
	{
		public Javascript_Function function;
		public @OPT Javascript_ParenthesizedExpression args;
	}
	
	public static @P(360) class Javascript_TypeOfExpr extends PrimaryOperator
	{
		public Javascript_Keyword TYPEOF = new Javascript_Keyword("typeof");
		public Javascript_Expression expr;
	}
	
	public static @P(370) class Javascript_VoidExpr extends PrimaryOperator
	{
		public Javascript_Keyword VOID = new Javascript_Keyword("void");
		public Javascript_Number number;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static @P(380) class Javascript_SubscriptExpression extends PrecedenceOperator
	{
		public Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @NOSPACE PunctuationLeftBracket leftBracket;
		public @NOSPACE Javascript_Expression subscr = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public @NOSPACE PunctuationRightBracket rightBracket;
	}

	public static @P(390) class Javascript_Subfield extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @NOSPACE PunctuationPeriod dot;
		public @NOSPACE Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(400) class Javascript_MultiplicativeExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("*", "/", "%");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(410) class Javascript_AdditiveExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("+", "-");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(420) class Javascript_ShiftExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice(">>>", "<<", ">>");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(430) class Javascript_RelationalExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("<", ">", "<=", ">=");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(440) class Javascript_InstanceOfExpression extends PrecedenceOperator
	{
		public Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Keyword instanceOperator = new Javascript_Keyword("instanceof");
		public Javascript_Type type;
	}

	public static @P(450) class Javascript_InExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Keyword inOperator = new Javascript_Keyword("in");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(460) class Javascript_EqualityExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("!==", "===", "==", "!=");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public @OPT Javascript_Comment comment;
	}

	public static @P(470) class Javascript_AndExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation bitwiseAndOperator = new Javascript_Punctuation('&');
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(480) class Javascript_ExclusiveOrExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation bitwiseXOrOperator = new Javascript_Punctuation('^');
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(490) class Javascript_InclusiveOrExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation bitwiseOrOperator = new Javascript_Punctuation('|');
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(500) class Javascript_ConditionalAndExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation andOperator = new Javascript_Punctuation("&&");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(510) class Javascript_ConditionalOrExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation orOperator = new Javascript_Punctuation("||");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(520) class Javascript_TrueFalseExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public Javascript_Punctuation questionMark = new Javascript_Punctuation('?');
		public Javascript_Expression middle = new Javascript_Expression(this, AllowedPrecedence.ANY);
		public PunctuationColon colon;
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static @P(530) class Javascript_AssignmentExpression extends PrecedenceOperator
	{
		public Javascript_Expression var = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	}
}

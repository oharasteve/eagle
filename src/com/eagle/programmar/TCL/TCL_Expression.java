// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.programmar.TCL.Terminals.TCL_Literal;
import com.eagle.programmar.TCL.Terminals.TCL_Number;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class TCL_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) TCL_Number number;
	public @P(20) TCL_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public TCL_Expression()
	{
	    super(_operators);
	}

	public TCL_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class TCL_VariableExpression extends PrimaryOperator
	{
		public TCL_Variable variable;
	}
	
	public static @P(110) class TCL_SignedExpression extends PrimaryOperator
	{
		public TCL_PunctuationChoice signedOperator = new TCL_PunctuationChoice("+", "-");
		public TCL_Expression expr;
	}

	public static @P(120) class TCL_NotExpression extends PrimaryOperator
	{
		public TCL_Keyword NOT = new TCL_Keyword("not");
		public TCL_Expression expr;
	}
	
	public static @P(130) class TCL_BangExpression extends PrimaryOperator
	{
		public TCL_Punctuation bang = new TCL_Punctuation('!');
		public TCL_Expression expr;
	}
	
	public static @P(140) class TCL_ParenthesizedExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public TCL_Expression expression;
		public PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions
	
	public static @P(150) class TCL_MultiplicativeExpression extends PrecedenceOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_PunctuationChoice operator = new TCL_PunctuationChoice("*", "/");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(160) class TCL_AdditiveExpression extends PrecedenceOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_PunctuationChoice operator = new TCL_PunctuationChoice("+", "-");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(170) class TCL_RelationalExpression extends PrecedenceOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_PunctuationChoice operator = new TCL_PunctuationChoice(
				"<", ">", "<=", ">=", "==", "<>");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}
		
	public static @P(180) class TCL_ConditionalAndExpression extends PrecedenceOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_Keyword AND = new TCL_Keyword("and");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}
		
	public static @P(190) class TCL_ConditionalOrExpression extends PrecedenceOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_Keyword OR = new TCL_Keyword("or");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}
}

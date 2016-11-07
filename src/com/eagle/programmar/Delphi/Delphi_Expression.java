// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Character;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_HexNumber;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_Literal;
import com.eagle.programmar.Delphi.Terminals.Delphi_Number;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Delphi_Number number;
	public @P(20) Delphi_HexNumber hex;
	public @P(30) Delphi_Literal literal;
	public @P(40) Delphi_Character character;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Delphi_Expression()
	{
	    super(_operators);
	}

	public Delphi_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static @P(100) class Delphi_Builtins extends PrimaryOperator
	{
		public Java_KeywordChoice builtinConstant = new Java_KeywordChoice("False", "True", "Nil");
	}
	
	public static @P(110) class Delphi_Parens extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public SeparatedList<Delphi_Expression,PunctuationComma> exprList;
		public PunctuationRightParen rightParen;		
	}
	
	public static @P(120) class Delphi_Brackets extends PrimaryOperator
	{
		public PunctuationLeftBracket leftBracket;
		public SeparatedList<Delphi_Expression,PunctuationComma> exprs;
		public PunctuationRightBracket rightBracket;		
	}
	
	public static @P(130) class Delphi_Function_Call extends PrimaryOperator
	{
		public Delphi_Variable name;
		public Delphi_Parameter_List params;
	}
	
	public static @P(140) class Delphi_Cast extends PrimaryOperator
	{
		public Delphi_Type type;
		public PunctuationLeftParen leftParen;
		public Delphi_Expression expr;
		public PunctuationRightParen rightParen;		
	}
	
	public static @P(150) class Delphi_VariableExpression extends PrimaryOperator
	{
		public Delphi_Variable variable;
	}
	
	public static @P(160) class Delphi_UnarySign extends PrimaryOperator
	{
		public Delphi_PunctuationChoice sign = new Delphi_PunctuationChoice("-", "+");
		public Delphi_Expression expr;
	}
	
	public static @P(170) class Delphi_NotOp extends PrimaryOperator
	{
		public Delphi_Keyword NOT = new Delphi_Keyword("Not");
		public Delphi_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(180) class Delphi_Dot_Expression extends PrecedenceOperator 
	{
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(190) class Delphi_Multiplicative_Expression extends PrecedenceOperator 
	{
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public Delphi_Multiplicative_Operator multOp;
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Delphi_Multiplicative_Operator extends TokenChooser
		{
			public @CHOICE Delphi_PunctuationChoice operator = new Delphi_PunctuationChoice("*", "/");
			public @CHOICE Delphi_KeywordChoice word = new Delphi_KeywordChoice("Div", "Mod", "And", "Shl", "Shr", "As");
		}
	}

	public static @P(200) class Delphi_Additive_Expression extends PrecedenceOperator 
	{
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public Delphi_Additive_Operator addOp;
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Delphi_Additive_Operator extends TokenChooser
		{
			public @CHOICE Delphi_PunctuationChoice operator = new Delphi_PunctuationChoice("+", "-");
			public @CHOICE Delphi_KeywordChoice OR = new Delphi_KeywordChoice("Or", "Xor");
		}
	}
	
	public static @P(210) class Delphi_Relational_Expression extends PrecedenceOperator 
	{
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public Delphi_Relational_Operator relOp;
		public @OPT Delphi_Comment comment;
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);

		public static class Delphi_Relational_Operator extends TokenChooser
		{
			public @CHOICE Delphi_PunctuationChoice operator = new Delphi_PunctuationChoice("=", "<>", "<", ">", "<=", ">=");
			public @CHOICE Delphi_KeywordChoice IN = new Delphi_KeywordChoice("In", "Is");
		}
	}
	
	public static @P(220) class Delphi_DotDot_Expression extends PrecedenceOperator 
	{
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
		public Delphi_Punctuation dotDot = new Delphi_Punctuation("..");
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
	}
}

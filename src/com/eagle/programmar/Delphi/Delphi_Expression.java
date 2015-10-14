// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_HexNumber;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_Literal;
import com.eagle.programmar.Delphi.Terminals.Delphi_Number;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
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
	public Delphi_Expression()
	{
	}
	
	public Delphi_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
	
	@Override
	protected void establishChoices() 
	{
		// Order doesn't matter
		super.addTerm(Delphi_Parens.class);
		super.addTerm(Delphi_Brackets.class);
		super.addTerm(Delphi_Number.class);
		super.addTerm(Delphi_HexNumber.class);
		super.addTerm(Delphi_Literal.class);
		super.addTerm(Delphi_Function_Call.class);
		super.addTerm(Delphi_Cast.class);
		super.addTerm(Delphi_Variable.class);
		super.addTerm(Delphi_UnarySign.class);
		super.addTerm(Delphi_NotOp.class);

		// Highest precedence first
		super.addOperator(Delphi_Dot_Expression.class);
		super.addOperator(Delphi_Multiplicative_Expression.class);
		super.addOperator(Delphi_Additive_Expression.class);
		super.addOperator(Delphi_Relational_Expression.class);
	}		

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static class Delphi_Brackets extends ExpressionTerm
	{
		public PunctuationLeftBracket leftBracket;
		public SeparatedList<Delphi_Expression,PunctuationComma> exprs;
		public PunctuationRightBracket rightBracket;		
	}
	
	public static class Delphi_Parens extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public Delphi_Expression expr;
		public PunctuationRightParen rightParen;		
	}
	
	public static class Delphi_Function_Call extends ExpressionTerm
	{
		public Delphi_Variable name;
		public Delphi_Parameter_List params;
	}
	
	public static class Delphi_Cast extends ExpressionTerm
	{
		public Delphi_Type type;
		public PunctuationLeftParen leftParen;
		public Delphi_Expression expr;
		public PunctuationRightParen rightParen;		
	}
	
	public static class Delphi_UnarySign extends ExpressionTerm
	{
		public Delphi_PunctuationChoice sign = new Delphi_PunctuationChoice("-", "+");
		public Delphi_Expression expr;
	}
	
	public static class Delphi_NotOp extends ExpressionTerm
	{
		public Delphi_Keyword NOT = new Delphi_Keyword("Not");
		public Delphi_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static class Delphi_Dot_Expression extends PrecedenceOperator 
	{
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Delphi_Multiplicative_Expression extends PrecedenceOperator 
	{
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public Delphi_Multiplicative_Operator multOp;
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Delphi_Multiplicative_Operator extends TokenChooser
		{
			public Delphi_PunctuationChoice operator = new Delphi_PunctuationChoice("*", "/");
			public Delphi_KeywordChoice word = new Delphi_KeywordChoice("Div", "Mod", "And", "Shl", "Shr", "As");
		}
	}

	public static class Delphi_Additive_Expression extends PrecedenceOperator 
	{
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public Delphi_Additive_Operator addOp;
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Delphi_Additive_Operator extends TokenChooser
		{
			public Delphi_PunctuationChoice operator = new Delphi_PunctuationChoice("+", "-");
			public Delphi_KeywordChoice OR = new Delphi_KeywordChoice("Or", "Xor");
		}
	}
	
	public static class Delphi_Relational_Expression extends PrecedenceOperator 
	{
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public Delphi_Relational_Operator relOp;
		public @OPT Delphi_Comment comment;
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);

		public static class Delphi_Relational_Operator extends TokenChooser
		{
			public Delphi_PunctuationChoice operator = new Delphi_PunctuationChoice("=", "<>", "<", ">", "<=", ">=");
			public Delphi_KeywordChoice IN = new Delphi_KeywordChoice("In", "Is");
		}
	}
}

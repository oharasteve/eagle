// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_HexNumber;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_Literal;
import com.eagle.programmar.Delphi.Terminals.Delphi_Number;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Expression extends PrecedenceChooser
{
	public Delphi_Expression()
	{
	}
	
	public Delphi_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
	
	@Override
	protected void establishChoices() 
	{
		// Order doesn't matter
		super.addUnaryOperator(Delphi_Parens.class);
		super.addUnaryOperator(Delphi_Brackets.class);
		super.addUnaryOperator(Delphi_Number.class);
		super.addUnaryOperator(Delphi_HexNumber.class);
		super.addUnaryOperator(Delphi_Literal.class);
		super.addUnaryOperator(Delphi_Function_Call.class);
		super.addUnaryOperator(Delphi_Cast.class);
		super.addUnaryOperator(Delphi_Variable.class);
		super.addUnaryOperator(Delphi_UnarySign.class);
		super.addUnaryOperator(Delphi_NotOp.class);

		// Highest precedence first
		super.addBinaryOperator(Delphi_Dot_Expression.class);
		super.addBinaryOperator(Delphi_Multiplicative_Expression.class);
		super.addBinaryOperator(Delphi_Additive_Expression.class);
		super.addBinaryOperator(Delphi_Relational_Expression.class);
	}		

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static class Delphi_Brackets extends UnaryOperator
	{
		public Delphi_Punctuation leftBracket = new Delphi_Punctuation('[');
		public Delphi_Expression expr;
		public @OPT TokenList<Delphi_MoreBrackets> more;
		public Delphi_Punctuation rightBracket = new Delphi_Punctuation(']');		
		
		public static class Delphi_MoreBrackets extends TokenSequence
		{
			public Delphi_Punctuation comma = new Delphi_Punctuation(',');
			public Delphi_Expression expr;
		}
	}
	
	public static class Delphi_Parens extends UnaryOperator
	{
		public Delphi_Punctuation leftParen = new Delphi_Punctuation('(');
		public Delphi_Expression expr;
		public Delphi_Punctuation rightParen = new Delphi_Punctuation(')');		
	}
	
	public static class Delphi_Function_Call extends UnaryOperator
	{
		public Delphi_Variable name;
		public Delphi_Parameter_List params;
	}
	
	public static class Delphi_Cast extends UnaryOperator
	{
		public Delphi_Type type;
		public Delphi_Punctuation leftParen = new Delphi_Punctuation('(');
		public Delphi_Expression expr;
		public Delphi_Punctuation rightParen = new Delphi_Punctuation(')');		
	}
	
	public static class Delphi_UnarySign extends UnaryOperator
	{
		public Delphi_PunctuationChoice sign = new Delphi_PunctuationChoice("-", "+");
		public Delphi_Expression expr;
	}
	
	public static class Delphi_NotOp extends UnaryOperator
	{
		public Delphi_Keyword NOT = new Delphi_Keyword("Not");
		public Delphi_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static class Delphi_Dot_Expression extends BinaryOperator 
	{
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public Delphi_Punctuation dot = new Delphi_Punctuation('.');
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Delphi_Multiplicative_Expression extends BinaryOperator 
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

	public static class Delphi_Additive_Expression extends BinaryOperator 
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
	
	public static class Delphi_Relational_Expression extends BinaryOperator 
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

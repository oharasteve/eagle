// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Terminals.SQL_HexString;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;

public class SQL_Expression extends PrecedenceChooser
{
	public SQL_Expression()
	{
	}
	
	public SQL_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(SQL_Number.class);
		super.addUnaryOperator(SQL_Literal.class);
		super.addUnaryOperator(SQL_HexString.class);
		super.addUnaryOperator(SQL_Builtin.class);
		super.addUnaryOperator(SQL_Function.class);
		super.addUnaryOperator(SQL_DollarVariable.class);
		super.addUnaryOperator(SQL_Variable.class);
		super.addUnaryOperator(SQL_Star.class);

		// Order is critical ...
		super.addBinaryOperator(SQL_MultiplicativeExpression.class);
		super.addBinaryOperator(SQL_AdditiveExpression.class);
		super.addBinaryOperator(SQL_RelationalExpression.class);
		super.addBinaryOperator(SQL_AndExpression.class);
		super.addBinaryOperator(SQL_OrExpression.class);
	}
	
	///////////////////////////////////////////////
	// Primary expressions

	public static class SQL_Builtin extends TokenChooser
	{
		public SQL_KeywordChoice TIMESTAMP = new SQL_KeywordChoice("CURRENT_TIMESTAMP", "SYSTIMESTAMP");
	}

	public static class SQL_Star extends UnaryOperator
	{
		public SQL_Punctuation star = new SQL_Punctuation('*');
	}

	public static class SQL_DollarVariable extends UnaryOperator
	{
		public SQL_Punctuation dollar = new SQL_Punctuation('$');
		public SQL_Number number;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class SQL_OrExpression extends BinaryOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_Keyword OR = new SQL_Keyword("OR");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class SQL_AndExpression extends BinaryOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_Keyword AND = new SQL_Keyword("AND");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class SQL_RelationalExpression extends BinaryOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_RelationalOperator relationalOperator;
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);

		public static class SQL_RelationalOperator extends TokenChooser
		{
			public SQL_Keyword LIKE = new SQL_Keyword("LIKE");
			public SQL_PunctuationChoice operator = new SQL_PunctuationChoice("=", "!=", "<", ">", "<=", ">=");
		}
	}

	public static class SQL_AdditiveExpression extends BinaryOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_PunctuationChoice operator = new SQL_PunctuationChoice("+", "-");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class SQL_MultiplicativeExpression extends BinaryOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_PunctuationChoice operator = new SQL_PunctuationChoice("*", "/", "%");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}
}

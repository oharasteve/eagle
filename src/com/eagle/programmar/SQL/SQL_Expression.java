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
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationStar;

public class SQL_Expression extends PrecedenceChooser
{
	public SQL_Expression()
	{
	}
	
	public SQL_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(SQL_Number.class);
		super.addTerm(SQL_Literal.class);
		super.addTerm(SQL_HexString.class);
		super.addTerm(SQL_Builtin.class);
		super.addTerm(SQL_Function.class);
		super.addTerm(SQL_DollarVariable.class);
		super.addTerm(SQL_Variable.class);
		super.addTerm(SQL_Star.class);

		// Order is critical ...
		super.addOperator(SQL_MultiplicativeExpression.class);
		super.addOperator(SQL_AdditiveExpression.class);
		super.addOperator(SQL_RelationalExpression.class);
		super.addOperator(SQL_AndExpression.class);
		super.addOperator(SQL_OrExpression.class);
	}
	
	///////////////////////////////////////////////
	// Primary expressions

	public static class SQL_Builtin extends TokenChooser
	{
		public SQL_KeywordChoice TIMESTAMP = new SQL_KeywordChoice("CURRENT_TIMESTAMP", "SYSTIMESTAMP");
	}

	public static class SQL_Star extends ExpressionTerm
	{
		public PunctuationStar star;
	}

	public static class SQL_DollarVariable extends ExpressionTerm
	{
		public SQL_Punctuation dollar = new SQL_Punctuation('$');
		public SQL_Number number;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class SQL_OrExpression extends PrecedenceOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_Keyword OR = new SQL_Keyword("OR");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class SQL_AndExpression extends PrecedenceOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_Keyword AND = new SQL_Keyword("AND");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class SQL_RelationalExpression extends PrecedenceOperator
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

	public static class SQL_AdditiveExpression extends PrecedenceOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_PunctuationChoice operator = new SQL_PunctuationChoice("+", "-");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class SQL_MultiplicativeExpression extends PrecedenceOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_PunctuationChoice operator = new SQL_PunctuationChoice("*", "/", "%");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}
}

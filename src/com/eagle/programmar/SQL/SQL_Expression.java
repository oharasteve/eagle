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
	private static OperatorList _operators = new OperatorList();

	public @P(10) SQL_Number number;
	public @P(20) SQL_Literal literal;
	public @P(30) SQL_HexString hex;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public SQL_Expression()
	{
	    super(_operators);
	}

	public SQL_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class SQL_Builtin extends PrimaryOperator
	{
		public SQL_KeywordChoice TIMESTAMP = new SQL_KeywordChoice("CURRENT_TIMESTAMP", "SYSTIMESTAMP");
	}
	
	public static @P(110) class SQL_FunctionExpression extends PrimaryOperator
	{
		public SQL_Function function;
	}

	public static @P(120) class SQL_DollarVariable extends PrimaryOperator
	{
		public SQL_Punctuation dollar = new SQL_Punctuation('$');
		public SQL_Number number;
	}
	
	public static @P(130) class SQL_VariableExpression extends PrimaryOperator
	{
		public SQL_Variable variable;
	}

	public static @P(140) class SQL_Star extends PrimaryOperator
	{
		public PunctuationStar star;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(150) class SQL_MultiplicativeExpression extends PrecedenceOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_PunctuationChoice operator = new SQL_PunctuationChoice("*", "/", "%");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(160) class SQL_AdditiveExpression extends PrecedenceOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_PunctuationChoice operator = new SQL_PunctuationChoice("+", "-");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(170) class SQL_RelationalExpression extends PrecedenceOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_RelationalOperator relationalOperator;
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);

		public static class SQL_RelationalOperator extends TokenChooser
		{
			public @CHOICE SQL_Keyword LIKE = new SQL_Keyword("LIKE");
			public @CHOICE SQL_PunctuationChoice operator = new SQL_PunctuationChoice("=", "!=", "<", ">", "<=", ">=");
		}
	}

	public static @P(180) class SQL_AndExpression extends PrecedenceOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_Keyword AND = new SQL_Keyword("AND");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(190) class SQL_OrExpression extends PrecedenceOperator
	{
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_Keyword OR = new SQL_Keyword("OR");
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}
}

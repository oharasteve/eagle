// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Perl_FunctionDefinition.Perl_FunctionBlock;
import com.eagle.programmar.Perl.Perl_FunctionDefinition.Perl_Function_Parameters;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_HexNumber;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Literal;
import com.eagle.programmar.Perl.Terminals.Perl_Number;
import com.eagle.programmar.Perl.Terminals.Perl_OctalNumber;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
import com.eagle.programmar.Perl.Terminals.Perl_RegularExpression;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationHyphen;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Perl_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Perl_OctalNumber octal;
	public @P(20) Perl_HexNumber hex;
	public @P(30) Perl_Number number;
	public @P(40) Perl_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Perl_Expression()
	{
	    super(_operators);
	}

	public Perl_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Perl_ClassCastExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public Perl_Variable ptype;
		public PunctuationRightParen rightParen;
		public Perl_Expression expr;
	}

	public static @P(110) class Perl_Expression_List extends PrimaryOperator
	{
		public Perl_ExpressionList expressionList;
	}
	
	public static @P(120) class Perl_ClassCreationExpression extends PrimaryOperator
	{
		public Perl_Keyword NEW = new Perl_Keyword("new");
		public @OPT Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @OPT TokenList<Perl_MoreNamespace> namespace;
		public PunctuationLeftParen leftParen;
		public @OPT TokenList<Perl_Comment> comments;
		public @OPT Perl_ArgumentList argList;
		public PunctuationRightParen rightParen;

		public static class Perl_MoreNamespace extends TokenSequence
		{
			public @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
			public Perl_Identifier_Reference id;
		}
	}
	
	public static @P(130) class Perl_CloneExpression extends PrimaryOperator
	{
		public Perl_Keyword CLONE = new Perl_Keyword("clone");
		public Perl_Expression expr;
	}
	
	public static @P(140) class Perl_MethodInvocation extends PrimaryOperator
	{
		public Perl_Variable methodName;
		public PunctuationLeftParen leftParen;
		public @OPT Perl_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}
	
	public static @P(150) class Perl_DollarEnvExpression extends PrimaryOperator
	{
		public Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_Keyword ENV = new Perl_Keyword("ENV");
		public PunctuationLeftBrace leftBrace;
		public Perl_Literal literal;
		public PunctuationRightBrace rightBrace;
	}
	
	public static @P(160) class Perl_PreIncrementExpression extends PrimaryOperator
	{
		public Perl_Punctuation preIncrementOperator = new Perl_Punctuation("++");
		public Perl_Variable var;
	}

	public static @P(170) class Perl_PreDecrementExpression extends PrimaryOperator
	{
		public Perl_Punctuation preDecrementOperator = new Perl_Punctuation("--");
		public Perl_Variable var;
	}
	
	public static @P(180) class Perl_PostIncrementExpression extends PrimaryOperator
	{
		public Perl_Variable var;
		public Perl_Punctuation postIncrementOperator = new Perl_Punctuation("++");
	}

	public static @P(190) class Perl_PostDecrementExpression extends PrimaryOperator
	{
		public Perl_Variable var;
		public Perl_Punctuation postDecrementOperator = new Perl_Punctuation("--");
	}
	
	public static @P(200) class Perl_ExistsExpression extends PrimaryOperator
	{
		public PunctuationHyphen minus;
		public Perl_KeywordChoice XE = new Perl_KeywordChoice("e", "x");
		public Perl_Expression file;
	}

	public static @P(210) class Perl_NegativeExpression extends PrimaryOperator
	{
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("-", "+");
		public Perl_Expression expr;
	}

	public static @P(220) class Perl_LogicalNotExpression extends PrimaryOperator
	{
		public Perl_Punctuation logicalNotOperator = new Perl_Punctuation('~');
		public Perl_Expression expr;
	}
	
	public static @P(230) class Perl_NotExpression extends PrimaryOperator
	{
		public Perl_NotOperator oper;
		public Perl_Expression expr;
		
		public static class Perl_NotOperator extends TokenChooser
		{
			public @CHOICE Perl_Punctuation notOperator = new Perl_Punctuation('!');
			public @CHOICE Perl_Keyword NOT = new Perl_Keyword("not");
		}
	}
	
	public static @P(240) class Perl_StarExpression extends PrimaryOperator
	{
		public PunctuationStar starOperator;
		public Perl_Expression expr;
	}
	
	public static @P(250) class Perl_GrepExpression extends PrimaryOperator
	{
		public Perl_Keyword GREP = new Perl_Keyword("grep");
		public Perl_RegularExpression regex;
		public PunctuationComma comma;
		public Perl_Expression expr;
	}
	
	public static @P(260) class Perl_BuiltIn extends PrimaryOperator
	{
		public Perl_KeywordChoice builtIn = new Perl_KeywordChoice(
				"FALSE", "False", "false",
				"TRUE", "True", "true",
				"NULL", "null",
				"T_CLASS",
				"T_FUNCTION",
                "T_INCLUDE",
                "T_INCLUDE_ONCE",
                "T_REQUIRE",
                "T_REQUIRE_ONCE",
                "T_USE",
                "namespace",
                "shift");
	}
	
	public static @P(270) class Perl_RegExExpression extends PrimaryOperator
	{
		public Perl_RegularExpression regex;
	}
	
	public static @P(280) class Perl_FunctionCall extends PrimaryOperator
	{
		public Perl_Identifier_Reference fnName;
		public @OPT TokenList<Perl_MoreFunctionName> more;
		public @OPT TokenList<Perl_Method> perlMethods;
		public PunctuationLeftParen leftParen;
		public @OPT Perl_Punctuation at = new Perl_Punctuation('@');
		public @OPT Perl_Expression parameter;
		public @OPT TokenList<Perl_MoreParameters> moreExpr;
		public PunctuationRightParen rightParen;
		
		public static class Perl_MoreFunctionName extends TokenSequence
		{
			public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
			public Perl_Identifier_Reference fnName;
		}
		public static class Perl_Method extends TokenSequence
		{
			public Perl_Punctuation colonColon = new Perl_Punctuation("::");
			public Perl_Identifier_Reference fnName;
		}
		
		public static class Perl_MoreParameters extends TokenSequence
		{
			public PunctuationComma comma;
			public @OPT Perl_Comment comment;
			public @OPT Perl_Punctuation at = new Perl_Punctuation('@');
			public Perl_Expression parameter;
		}
	}
	
	public static @P(290) class Perl_VariableExpression extends PrimaryOperator
	{
		public Perl_Variable variable;
	}
	
	public static @P(300) class Perl_ParenthesizedExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public Perl_ArgumentList valueList;
		public PunctuationRightParen rightParen;
	}
	
	public static @P(310) class Perl_BracketedExpression extends PrimaryOperator
	{
		public PunctuationLeftBracket leftBracket;
		public @OPT TokenList<Perl_Comment> comment1;
		public @OPT Perl_ArgumentList valueList;
		public @OPT PunctuationComma comma;
		public @OPT TokenList<Perl_Comment> comment2;
		public PunctuationRightBracket rightBracket;
	}

	public static @P(320) class Perl_EachExpression extends PrimaryOperator
	{
		public Perl_Keyword EACH = new Perl_Keyword("each");
		public PunctuationLeftParen leftParen;
		public @NOSPACE Perl_Variable var;
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static @P(330) class Perl_DieExpression extends PrimaryOperator
	{
		public Perl_Keyword DIE = new Perl_Keyword("die");
		public Perl_Expression expr = new Perl_Expression();
	}

	public static @P(340) class Perl_AddressOfExpression extends PrimaryOperator
	{
		public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public Perl_Expression expr;
	}
	
	public static @P(350) class Perl_FunctionExpression extends PrimaryOperator
	{
		public Perl_Keyword FUNCTION = new Perl_Keyword("function");
		public Perl_Function_Parameters params;
		public @OPT Perl_FunctionUse use;
		public Perl_FunctionBlock block;
		
		public static class Perl_FunctionUse extends TokenSequence
		{	
			public Perl_Keyword USE = new Perl_Keyword("use");
			public PunctuationLeftParen leftParen;
			public Perl_Punctuation ampersand = new Perl_Punctuation('&');
			public @NOSPACE Perl_Variable var;
			public @NOSPACE PunctuationRightParen rightParen;
		}
	}
	
	public static @P(360) class Perl_FileIO extends PrimaryOperator
	{
		public Perl_Punctuation lessThan = new Perl_Punctuation('<');
		public @OPT Perl_Identifier_Reference channel;
		public Perl_Punctuation greaterThan = new Perl_Punctuation('>');
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(370) class Perl_SubscriptExpression extends PrecedenceOperator
	{
		public Perl_Expression expr = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationLeftBracket leftBracket;
		public @OPT Perl_Expression subscr = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public PunctuationRightBracket rightBracket;
	}
	
	public static @P(380) class Perl_DotExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(390) class Perl_ColonColonExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation colonColon = new Perl_Punctuation("::");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(400) class Perl_ArrowExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation arrow = new Perl_Punctuation("->");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(410) class Perl_MapExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation arrow = new Perl_Punctuation("=>");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(420) class Perl_MultiplicativeExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("*", "/", "%");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(430) class Perl_AdditiveExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("+", "-");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(440) class Perl_ShiftExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		// "<<" gets confused with <<SENTINEL for multi-line literals.
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice(">>", ">>>");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(450) class Perl_RelationalExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("<=", ">=", "<", ">");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(460) class Perl_RegExTest extends PrecedenceOperator
	{
		public Perl_Expression var = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("=~", "!~");
		public Perl_RegularExpression expr;
	}

	public static @P(470) class Perl_InstanceOfExpression extends PrecedenceOperator
	{
		public Perl_Expression expr = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Keyword instanceOperator = new Perl_Keyword("instanceof");
		public @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public Perl_Identifier_Reference type;
	}
	
	public static @P(480) class Perl_EqualityExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_EqualityOperator equalityOperator;
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);

		public static class Perl_EqualityOperator extends TokenChooser
		{
			public @CHOICE Perl_KeywordChoice EQ = new Perl_KeywordChoice("eq", "ne");
			public @CHOICE Perl_PunctuationChoice operator = new Perl_PunctuationChoice("===", "!==", "==", "!=");
		}
	}
	
	public static @P(490) class Perl_AndExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation bitwiseAndOperator = new Perl_Punctuation('&');
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(500) class Perl_ExclusiveOrExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation bitwiseXOrOperator = new Perl_Punctuation('^');
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(510) class Perl_InclusiveOrExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation bitwiseOrOperator = new Perl_Punctuation('|');
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(520) class Perl_ConditionalAndExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_AndOperator oper;
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Perl_AndOperator extends TokenChooser
		{
			public @CHOICE Perl_Punctuation andOperator = new Perl_Punctuation("&&");
			public @CHOICE Perl_Keyword AND = new Perl_Keyword("and");
		}
	}
	
	public static @P(530) class Perl_ConditionalOrExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_OrOperator oper;
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Perl_OrOperator extends TokenChooser
		{
			public @CHOICE Perl_Punctuation orOperator = new Perl_Punctuation("||");
			public @CHOICE Perl_Keyword OR = new Perl_Keyword("or");
		}
	}
	
	public static @P(540) class Perl_TrueFalseExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_Punctuation questionMark = new Perl_Punctuation('?');
		public Perl_Expression middle = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationColon colon;
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static @P(550) class Perl_AssignmentExpression extends PrecedenceOperator
	{
		public Perl_Expression var = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=", ".=");
		public Perl_Expression expr;
	}
}

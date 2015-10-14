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
	public Perl_Expression()
	{
	}
	
	public Perl_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(Perl_OctalNumber.class);
		super.addTerm(Perl_HexNumber.class);
		super.addTerm(Perl_Number.class);			
		super.addTerm(Perl_Literal.class);
		super.addTerm(Perl_ClassCastExpression.class);
		super.addTerm(Perl_ExpressionList.class);
		super.addTerm(Perl_ClassCreationExpression.class);
		super.addTerm(Perl_CloneExpression.class);
		super.addTerm(Perl_MethodInvocation.class);
		super.addTerm(Perl_DollarEnvExpression.class);
		super.addTerm(Perl_PreIncrementExpression.class);
		super.addTerm(Perl_PreDecrementExpression.class);
		super.addTerm(Perl_PostIncrementExpression.class);
		super.addTerm(Perl_PostDecrementExpression.class);
		super.addTerm(Perl_ExistsExpression.class);
		super.addTerm(Perl_NegativeExpression.class);
		super.addTerm(Perl_LogicalNotExpression.class);
		super.addTerm(Perl_NotExpression.class);
		super.addTerm(Perl_StarExpression.class);
		super.addTerm(Perl_GrepExpression.class);
		super.addTerm(Perl_BuiltIn.class);
		super.addTerm(Perl_FunctionCall.class);
		super.addTerm(Perl_Variable.class);
		super.addTerm(Perl_ParenthesizedExpression.class);
		super.addTerm(Perl_BracketedExpression.class);
		super.addTerm(Perl_EachExpression.class);
		super.addTerm(Perl_ExistsExpression.class);
		super.addTerm(Perl_DieExpression.class);
		super.addTerm(Perl_AddressOfExpression.class);
		super.addTerm(Perl_FunctionExpression.class);
		super.addTerm(Perl_RegularExpression.class);
		super.addTerm(Perl_FileIO.class);
		
		// Order is critical ...
		super.addOperator(Perl_SubscriptExpression.class);
		super.addOperator(Perl_DotExpression.class);
		super.addOperator(Perl_ArrowExpression.class);
		super.addOperator(Perl_MapExpression.class);
		super.addOperator(Perl_MultiplicativeExpression.class);
		super.addOperator(Perl_AdditiveExpression.class);
		super.addOperator(Perl_ShiftExpression.class);
		super.addOperator(Perl_RelationalExpression.class);
		super.addOperator(Perl_RegExTest.class);
		super.addOperator(Perl_InstanceOfExpression.class);
		super.addOperator(Perl_EqualityExpression.class);
		super.addOperator(Perl_AndExpression.class);
		super.addOperator(Perl_ExclusiveOrExpression.class);
		super.addOperator(Perl_InclusiveOrExpression.class);
		super.addOperator(Perl_ConditionalAndExpression.class);
		super.addOperator(Perl_ConditionalOrExpression.class);
		super.addOperator(Perl_TrueFalseExpression.class);
		super.addOperator(Perl_AssignmentExpression.class);
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static class Perl_BuiltIn extends TokenChooser
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
	
	public static class Perl_ParenthesizedExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public Perl_ArgumentList valueList;
		public PunctuationRightParen rightParen;
	}
	
	public static class Perl_BracketedExpression extends ExpressionTerm
	{
		public PunctuationLeftBracket leftBracket;
		public @OPT TokenList<Perl_Comment> comment1;
		public @OPT Perl_ArgumentList valueList;
		public @OPT PunctuationComma comma;
		public @OPT TokenList<Perl_Comment> comment2;
		public PunctuationRightBracket rightBracket;
	}

	public static class Perl_ClassCastExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public Perl_Variable ptype;
		public PunctuationRightParen rightParen;
		public Perl_Expression expr;
	}

	public static class Perl_ExpressionList extends ExpressionTerm
	{
		public PunctuationLeftBrace leftBrace;
		public @OPT TokenList<Perl_Comment> comment;
		public Perl_ArgumentList valueList;
		public PunctuationRightBrace rightBrace;
	}
	
	public static class Perl_CloneExpression extends ExpressionTerm
	{
		public Perl_Keyword CLONE = new Perl_Keyword("clone");
		public Perl_Expression expr;
	}
	
	public static class Perl_GrepExpression extends ExpressionTerm
	{
		public Perl_Keyword GREP = new Perl_Keyword("grep");
		public Perl_RegularExpression regex;
		public PunctuationComma comma;
		public Perl_Expression expr;
	}
	
	public static class Perl_ClassCreationExpression extends ExpressionTerm
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
	
	public static class Perl_MethodInvocation extends ExpressionTerm
	{
		public Perl_Variable methodName;
		public PunctuationLeftParen leftParen;
		public @OPT Perl_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}
	
	public static class Perl_DollarEnvExpression extends ExpressionTerm
	{
		public Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_Keyword ENV = new Perl_Keyword("ENV");
		public PunctuationLeftBrace leftBrace;
		public Perl_Literal literal;
		public PunctuationRightBrace rightBrace;
	}
	
	public static class Perl_ArgumentList extends ExpressionTerm
	{
		public Perl_Expression arg;
		public @OPT TokenList<Perl_Comment> comments1;
		public @OPT TokenList<Perl_MoreArguments> moreArgs;
		public @OPT PunctuationComma comma;
		public @OPT TokenList<Perl_Comment> comments2;
		
		public static class Perl_MoreArguments extends TokenSequence
		{
			public PunctuationComma comma;
			public @OPT TokenList<Perl_Comment> comments1;
			public Perl_Expression arg;
			public @OPT TokenList<Perl_Comment> comments2;
		}
	}

	public static class Perl_PreIncrementExpression extends ExpressionTerm
	{
		public Perl_Punctuation preIncrementOperator = new Perl_Punctuation("++");
		public Perl_Variable var;
	}

	public static class Perl_PreDecrementExpression extends ExpressionTerm
	{
		public Perl_Punctuation preDecrementOperator = new Perl_Punctuation("--");
		public Perl_Variable var;
	}
	
	public static class Perl_PostIncrementExpression extends ExpressionTerm
	{
		public Perl_Variable var;
		public Perl_Punctuation postIncrementOperator = new Perl_Punctuation("++");
	}

	public static class Perl_PostDecrementExpression extends ExpressionTerm
	{
		public Perl_Variable var;
		public Perl_Punctuation postDecrementOperator = new Perl_Punctuation("--");
	}
	
	public static class Perl_NegativeExpression extends ExpressionTerm
	{
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("-", "+");
		public Perl_Expression expr;
	}

	public static class Perl_LogicalNotExpression extends ExpressionTerm
	{
		public Perl_Punctuation logicalNotOperator = new Perl_Punctuation('~');
		public Perl_Expression expr;
	}
	
	public static class Perl_NotExpression extends ExpressionTerm
	{
		public Perl_NotOperator oper;
		public Perl_Expression expr;
		
		public static class Perl_NotOperator extends TokenChooser
		{
			public Perl_Punctuation notOperator = new Perl_Punctuation('!');
			public Perl_Keyword NOT = new Perl_Keyword("not");
		}
	}
	
	public static class Perl_StarExpression extends ExpressionTerm
	{
		public PunctuationStar starOperator;
		public Perl_Expression expr;
	}
	
	public static class Perl_ExistsExpression extends ExpressionTerm
	{
		public PunctuationHyphen minus;
		public Perl_KeywordChoice XE = new Perl_KeywordChoice("e", "x");
		public Perl_Expression file;
	}

	public static class Perl_DieExpression extends ExpressionTerm
	{
		public Perl_Keyword DIE = new Perl_Keyword("die");
		public Perl_Expression expr = new Perl_Expression();
	}

	public static class Perl_EachExpression extends ExpressionTerm
	{
		public Perl_Keyword EACH = new Perl_Keyword("each");
		public PunctuationLeftParen leftParen;
		public @NOSPACE Perl_Variable var;
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static class Perl_AddressOfExpression extends ExpressionTerm
	{
		public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public Perl_Expression expr;
	}
	
	public static class Perl_FunctionExpression extends ExpressionTerm
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
	
	public static class Perl_FileIO extends ExpressionTerm
	{
		public Perl_Punctuation lessThan = new Perl_Punctuation('<');
		public @OPT Perl_Identifier_Reference channel;
		public Perl_Punctuation greaterThan = new Perl_Punctuation('>');
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class Perl_AssignmentExpression extends PrecedenceOperator
	{
		public Perl_Expression var = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=", ".=");
		public Perl_Expression expr;
	}

	public static class Perl_TrueFalseExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_Punctuation questionMark = new Perl_Punctuation('?');
		public Perl_Expression middle = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationColon colon;
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static class Perl_ConditionalOrExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_OrOperator oper;
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Perl_OrOperator extends TokenChooser
		{
			public Perl_Punctuation orOperator = new Perl_Punctuation("||");
			public Perl_Keyword OR = new Perl_Keyword("or");
		}
	}
	
	public static class Perl_ConditionalAndExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_AndOperator oper;
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Perl_AndOperator extends TokenChooser
		{
			public Perl_Punctuation andOperator = new Perl_Punctuation("&&");
			public Perl_Keyword AND = new Perl_Keyword("and");
		}
	}
	
	public static class Perl_InclusiveOrExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation bitwiseOrOperator = new Perl_Punctuation('|');
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_ExclusiveOrExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation bitwiseXOrOperator = new Perl_Punctuation('^');
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_AndExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation bitwiseAndOperator = new Perl_Punctuation('&');
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_EqualityExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_EqualityOperator equalityOperator;
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);

		public static class Perl_EqualityOperator extends TokenChooser
		{
			public Perl_KeywordChoice EQ = new Perl_KeywordChoice("eq", "ne");
			public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("===", "!==", "==", "!=");
		}
	}
	
	public static class Perl_RegExTest extends PrecedenceOperator
	{
		public Perl_Expression var = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("=~", "!~");
		public Perl_RegularExpression expr;
	}

	public static class Perl_InstanceOfExpression extends PrecedenceOperator
	{
		public Perl_Expression expr = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Keyword instanceOperator = new Perl_Keyword("instanceof");
		public @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public Perl_Identifier_Reference type;
	}

	public static class Perl_RelationalExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("<=", ">=", "<", ">");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_ShiftExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		// "<<" gets confused with <<SENTINEL for multi-line literals.
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice(">>", ">>>");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_AdditiveExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("+", "-");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_MultiplicativeExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("*", "/", "%");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_MapExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation arrow = new Perl_Punctuation("=>");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_ArrowExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation arrow = new Perl_Punctuation("->");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_DotExpression extends PrecedenceOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_SubscriptExpression extends PrecedenceOperator
	{
		public Perl_Expression expr = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationLeftBracket leftBracket;
		public @OPT Perl_Expression subscr = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public PunctuationRightBracket rightBracket;
	}
}

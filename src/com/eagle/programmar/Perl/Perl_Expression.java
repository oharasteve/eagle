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
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Perl_Expression extends PrecedenceChooser
{
	public Perl_Expression()
	{
	}
	
	public Perl_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(Perl_OctalNumber.class);
		super.addUnaryOperator(Perl_HexNumber.class);
		super.addUnaryOperator(Perl_Number.class);			
		super.addUnaryOperator(Perl_Literal.class);
		super.addUnaryOperator(Perl_ClassCastExpression.class);
		super.addUnaryOperator(Perl_ExpressionList.class);
		super.addUnaryOperator(Perl_ClassCreationExpression.class);
		super.addUnaryOperator(Perl_CloneExpression.class);
		super.addUnaryOperator(Perl_MethodInvocation.class);
		super.addUnaryOperator(Perl_DollarEnvExpression.class);
		super.addUnaryOperator(Perl_PreIncrementExpression.class);
		super.addUnaryOperator(Perl_PreDecrementExpression.class);
		super.addUnaryOperator(Perl_PostIncrementExpression.class);
		super.addUnaryOperator(Perl_PostDecrementExpression.class);
		super.addUnaryOperator(Perl_ExistsExpression.class);
		super.addUnaryOperator(Perl_NegativeExpression.class);
		super.addUnaryOperator(Perl_LogicalNotExpression.class);
		super.addUnaryOperator(Perl_NotExpression.class);
		super.addUnaryOperator(Perl_StarExpression.class);
		super.addUnaryOperator(Perl_GrepExpression.class);
		super.addUnaryOperator(Perl_BuiltIn.class);
		super.addUnaryOperator(Perl_FunctionCall.class);
		super.addUnaryOperator(Perl_Variable.class);
		super.addUnaryOperator(Perl_ParenthesizedExpression.class);
		super.addUnaryOperator(Perl_BracketedExpression.class);
		super.addUnaryOperator(Perl_EachExpression.class);
		super.addUnaryOperator(Perl_ExistsExpression.class);
		super.addUnaryOperator(Perl_DieExpression.class);
		super.addUnaryOperator(Perl_AddressOfExpression.class);
		super.addUnaryOperator(Perl_FunctionExpression.class);
		super.addUnaryOperator(Perl_RegularExpression.class);
		super.addUnaryOperator(Perl_FileIO.class);
		
		// Order is critical ...
		super.addBinaryOperator(Perl_SubscriptExpression.class);
		super.addBinaryOperator(Perl_DotExpression.class);
		super.addBinaryOperator(Perl_ArrowExpression.class);
		super.addBinaryOperator(Perl_MapExpression.class);
		super.addBinaryOperator(Perl_MultiplicativeExpression.class);
		super.addBinaryOperator(Perl_AdditiveExpression.class);
		super.addBinaryOperator(Perl_ShiftExpression.class);
		super.addBinaryOperator(Perl_RelationalExpression.class);
		super.addBinaryOperator(Perl_RegExTest.class);
		super.addBinaryOperator(Perl_InstanceOfExpression.class);
		super.addBinaryOperator(Perl_EqualityExpression.class);
		super.addBinaryOperator(Perl_AndExpression.class);
		super.addBinaryOperator(Perl_ExclusiveOrExpression.class);
		super.addBinaryOperator(Perl_InclusiveOrExpression.class);
		super.addBinaryOperator(Perl_ConditionalAndExpression.class);
		super.addBinaryOperator(Perl_ConditionalOrExpression.class);
		super.addBinaryOperator(Perl_TrueFalseExpression.class);
		super.addBinaryOperator(Perl_AssignmentExpression.class);
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
	
	public static class Perl_ParenthesizedExpression extends UnaryOperator
	{
		public Perl_Punctuation leftParen = new Perl_Punctuation('(');
		public Perl_ArgumentList valueList;
		public Perl_Punctuation rightParen = new Perl_Punctuation(')');
	}
	
	public static class Perl_BracketedExpression extends UnaryOperator
	{
		public Perl_Punctuation leftBracket = new Perl_Punctuation('[');
		public @OPT TokenList<Perl_Comment> comment1;
		public @OPT Perl_ArgumentList valueList;
		public @OPT Perl_Punctuation comma = new Perl_Punctuation(',');
		public @OPT TokenList<Perl_Comment> comment2;
		public Perl_Punctuation rightBracket = new Perl_Punctuation(']');
	}

	public static class Perl_ClassCastExpression extends UnaryOperator
	{
		public Perl_Punctuation leftParen = new Perl_Punctuation('(');
		public Perl_Variable ptype;
		public Perl_Punctuation rightParen = new Perl_Punctuation(')');
		public Perl_Expression expr;
	}

	public static class Perl_ExpressionList extends UnaryOperator
	{
		public Perl_Punctuation leftBrace = new Perl_Punctuation('{');
		public @OPT TokenList<Perl_Comment> comment;
		public Perl_ArgumentList valueList;
		public Perl_Punctuation rightBrace = new Perl_Punctuation('}');
	}
	
	public static class Perl_CloneExpression extends UnaryOperator
	{
		public Perl_Keyword CLONE = new Perl_Keyword("clone");
		public Perl_Expression expr;
	}
	
	public static class Perl_GrepExpression extends UnaryOperator
	{
		public Perl_Keyword GREP = new Perl_Keyword("grep");
		public Perl_RegularExpression regex;
		public Perl_Punctuation comma = new Perl_Punctuation(',');
		public Perl_Expression expr;
	}
	
	public static class Perl_ClassCreationExpression extends UnaryOperator
	{
		public Perl_Keyword NEW = new Perl_Keyword("new");
		public @OPT Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @OPT TokenList<Perl_MoreNamespace> namespace;
		public Perl_Punctuation leftParen = new Perl_Punctuation('(');
		public @OPT TokenList<Perl_Comment> comments;
		public @OPT Perl_ArgumentList argList;
		public Perl_Punctuation rightParen = new Perl_Punctuation(')');

		public static class Perl_MoreNamespace extends TokenSequence
		{
			public @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
			public Perl_Identifier_Reference id;
		}
	}
	
	public static class Perl_MethodInvocation extends UnaryOperator
	{
		public Perl_Variable methodName;
		public Perl_Punctuation leftParen = new Perl_Punctuation('(');
		public @OPT Perl_ArgumentList argList;
		public Perl_Punctuation rightParen = new Perl_Punctuation(')');
	}
	
	public static class Perl_DollarEnvExpression extends UnaryOperator
	{
		public Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_Keyword ENV = new Perl_Keyword("ENV");
		public Perl_Punctuation leftBrace = new Perl_Punctuation('{');
		public Perl_Literal literal;
		public Perl_Punctuation rightBrace = new Perl_Punctuation('}');
	}
	
	public static class Perl_ArgumentList extends UnaryOperator
	{
		public Perl_Expression arg;
		public @OPT TokenList<Perl_Comment> comments1;
		public @OPT TokenList<Perl_MoreArguments> moreArgs;
		public @OPT Perl_Punctuation comma = new Perl_Punctuation(',');
		public @OPT TokenList<Perl_Comment> comments2;
		
		public static class Perl_MoreArguments extends TokenSequence
		{
			public Perl_Punctuation comma = new Perl_Punctuation(',');
			public @OPT TokenList<Perl_Comment> comments1;
			public Perl_Expression arg;
			public @OPT TokenList<Perl_Comment> comments2;
		}
	}

	public static class Perl_PreIncrementExpression extends UnaryOperator
	{
		public Perl_Punctuation preIncrementOperator = new Perl_Punctuation("++");
		public Perl_Variable var;
	}

	public static class Perl_PreDecrementExpression extends UnaryOperator
	{
		public Perl_Punctuation preDecrementOperator = new Perl_Punctuation("--");
		public Perl_Variable var;
	}
	
	public static class Perl_PostIncrementExpression extends UnaryOperator
	{
		public Perl_Variable var;
		public Perl_Punctuation postIncrementOperator = new Perl_Punctuation("++");
	}

	public static class Perl_PostDecrementExpression extends UnaryOperator
	{
		public Perl_Variable var;
		public Perl_Punctuation postDecrementOperator = new Perl_Punctuation("--");
	}
	
	public static class Perl_NegativeExpression extends UnaryOperator
	{
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("-", "+");
		public Perl_Expression expr;
	}

	public static class Perl_LogicalNotExpression extends UnaryOperator
	{
		public Perl_Punctuation logicalNotOperator = new Perl_Punctuation('~');
		public Perl_Expression expr;
	}
	
	public static class Perl_NotExpression extends UnaryOperator
	{
		public Perl_NotOperator oper;
		public Perl_Expression expr;
		
		public static class Perl_NotOperator extends TokenChooser
		{
			public Perl_Punctuation notOperator = new Perl_Punctuation('!');
			public Perl_Keyword NOT = new Perl_Keyword("not");
		}
	}
	
	public static class Perl_StarExpression extends UnaryOperator
	{
		public Perl_Punctuation starOperator = new Perl_Punctuation('*');
		public Perl_Expression expr;
	}
	
	public static class Perl_ExistsExpression extends UnaryOperator
	{
		public Perl_Punctuation minus = new Perl_Punctuation('-');
		public Perl_KeywordChoice XE = new Perl_KeywordChoice("e", "x");
		public Perl_Expression file;
	}

	public static class Perl_DieExpression extends UnaryOperator
	{
		public Perl_Keyword DIE = new Perl_Keyword("die");
		public Perl_Expression expr = new Perl_Expression();
	}

	public static class Perl_EachExpression extends UnaryOperator
	{
		public Perl_Keyword EACH = new Perl_Keyword("each");
		public Perl_Punctuation leftParen = new Perl_Punctuation('(');
		public @NOSPACE Perl_Variable var;
		public @NOSPACE Perl_Punctuation rightParen = new Perl_Punctuation(')');
	}
	
	public static class Perl_AddressOfExpression extends UnaryOperator
	{
		public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public Perl_Expression expr;
	}
	
	public static class Perl_FunctionExpression extends UnaryOperator
	{
		public Perl_Keyword FUNCTION = new Perl_Keyword("function");
		public Perl_Function_Parameters params;
		public @OPT Perl_FunctionUse use;
		public Perl_FunctionBlock block;
		
		public static class Perl_FunctionUse extends TokenSequence
		{	
			public Perl_Keyword USE = new Perl_Keyword("use");
			public Perl_Punctuation leftParen = new Perl_Punctuation('(');
			public Perl_Punctuation ampersand = new Perl_Punctuation('&');
			public @NOSPACE Perl_Variable var;
			public @NOSPACE Perl_Punctuation rightParen = new Perl_Punctuation(')');
		}
	}
	
	public static class Perl_FileIO extends UnaryOperator
	{
		public Perl_Punctuation lessThan = new Perl_Punctuation('<');
		public @OPT Perl_Identifier_Reference channel;
		public Perl_Punctuation greaterThan = new Perl_Punctuation('>');
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class Perl_AssignmentExpression extends BinaryOperator
	{
		public Perl_Expression var = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=", ".=");
		public Perl_Expression expr;
	}

	public static class Perl_TrueFalseExpression extends BinaryOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_Punctuation questionMark = new Perl_Punctuation('?');
		public Perl_Expression middle = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation colon = new Perl_Punctuation(':');
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static class Perl_ConditionalOrExpression extends BinaryOperator
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
	
	public static class Perl_ConditionalAndExpression extends BinaryOperator
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
	
	public static class Perl_InclusiveOrExpression extends BinaryOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation bitwiseOrOperator = new Perl_Punctuation('|');
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_ExclusiveOrExpression extends BinaryOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation bitwiseXOrOperator = new Perl_Punctuation('^');
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_AndExpression extends BinaryOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation bitwiseAndOperator = new Perl_Punctuation('&');
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_EqualityExpression extends BinaryOperator
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
	
	public static class Perl_RegExTest extends BinaryOperator
	{
		public Perl_Expression var = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("=~", "!~");
		public Perl_RegularExpression expr;
	}

	public static class Perl_InstanceOfExpression extends BinaryOperator
	{
		public Perl_Expression expr = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Keyword instanceOperator = new Perl_Keyword("instanceof");
		public @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public Perl_Identifier_Reference type;
	}

	public static class Perl_RelationalExpression extends BinaryOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("<=", ">=", "<", ">");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_ShiftExpression extends BinaryOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		// "<<" gets confused with <<SENTINEL for multi-line literals.
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice(">>", ">>>");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_AdditiveExpression extends BinaryOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("+", "-");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_MultiplicativeExpression extends BinaryOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_PunctuationChoice operator = new Perl_PunctuationChoice("*", "/", "%");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_MapExpression extends BinaryOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation arrow = new Perl_Punctuation("=>");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_ArrowExpression extends BinaryOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation arrow = new Perl_Punctuation("->");
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_DotExpression extends BinaryOperator
	{
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation dot = new Perl_Punctuation('.');
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Perl_SubscriptExpression extends BinaryOperator
	{
		public Perl_Expression expr = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Punctuation leftBracket = new Perl_Punctuation('[');
		public @OPT Perl_Expression subscr = new Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_Punctuation rightBracket = new Perl_Punctuation(']');
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_HexNumber;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_RegularExpression;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_Expression extends PrecedenceChooser
{
	private boolean _allowComma = true;
	
	public Javascript_Expression(boolean allowComma)
	{
		_allowComma = allowComma;
	}
	
	public Javascript_Expression()
	{
	}
	
	public Javascript_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(Javascript_HexNumber.class);
		super.addTerm(Javascript_Number.class);
		super.addTerm(Javascript_Literal.class);
		super.addTerm(Javascript_RegularExpression.class);
		super.addTerm(Javascript_DotClass.class);
		super.addTerm(Javascript_CastExpression.class);
		super.addTerm(Javascript_ExpressionList.class);
		super.addTerm(Javascript_StringFunction.class);
		super.addTerm(Javascript_DeleteExpression.class);
		super.addTerm(Javascript_ClassCreationExpression.class);
		super.addTerm(Javascript_ClassCreationWithInitializers.class);
		super.addTerm(Javascript_ClassCreationWithSubscript.class);
		super.addTerm(Javascript_NewNoArgsExpression.class);
		super.addTerm(Javascript_MethodInvocation.class);
		super.addTerm(Javascript_PreIncrementExpression.class);
		super.addTerm(Javascript_PreDecrementExpression.class);
		super.addTerm(Javascript_PostIncrementExpression.class);
		super.addTerm(Javascript_PostDecrementExpression.class);
		super.addTerm(Javascript_NegativeExpression.class);
		super.addTerm(Javascript_LogicalNotExpression.class);
		super.addTerm(Javascript_NotExpression.class);
		super.addTerm(Javascript_BuiltIn.class);
		super.addTerm(Javascript_Variable.class);
		super.addTerm(Javascript_StrangeFunction.class);
		super.addTerm(Javascript_ParenthesizedFunction.class);
		super.addTerm(Javascript_ParenthesizedExpression.class);
		super.addTerm(Javascript_SimpleArray.class);
		super.addTerm(Javascript_CommentExpression.class);
		super.addTerm(Javascript_Dictionary.class);
		super.addTerm(Javascript_FunctionExpr.class);
		super.addTerm(Javascript_TypeOfExpr.class);
		super.addTerm(Javascript_VoidExpr.class);
		
		// Order is critical ...
		super.addOperator(Javascript_SubscriptExpression.class);
		super.addOperator(Javascript_Subfield.class);
		super.addOperator(Javascript_MultiplicativeExpression.class);
		super.addOperator(Javascript_AdditiveExpression.class);
		super.addOperator(Javascript_ShiftExpression.class);
		super.addOperator(Javascript_RelationalExpression.class);
		super.addOperator(Javascript_InstanceOfExpression.class);
		super.addOperator(Javascript_InExpression.class);
		super.addOperator(Javascript_EqualityExpression.class);
		super.addOperator(Javascript_AndExpression.class);
		super.addOperator(Javascript_ExclusiveOrExpression.class);
		super.addOperator(Javascript_InclusiveOrExpression.class);
		super.addOperator(Javascript_ConditionalAndExpression.class);
		super.addOperator(Javascript_ConditionalOrExpression.class);
		super.addOperator(Javascript_AssignmentExpression.class);
		super.addOperator(Javascript_TrueFalseExpression.class);
		if (_allowComma)
		{
			super.addOperator(Javascript_CommaExpression.class);
		}
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static class Javascript_BuiltIn extends TokenChooser
	{
		public Javascript_KeywordChoice logicalConstant = new Javascript_KeywordChoice("false", "true", "null", "this", "super");
		public Javascript_Keyword STRING = new Javascript_Keyword("String");
	}
	
	public static class Javascript_ParenthesizedExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public @OPT @NOSPACE SeparatedList<Javascript_Expression,PunctuationComma> expressions;
		public @OPT PunctuationComma comma;
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static class Javascript_StrangeFunction extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public @NOSPACE Javascript_Number zero;
		public @NOSPACE PunctuationComma comma;
		public Javascript_Variable function;
		public @NOSPACE PunctuationRightParen rightParen;
		public @NEWLINE Javascript_ParenthesizedExpression arguments;
	}

	public static class Javascript_ParenthesizedFunction extends ExpressionTerm
	{
		public @INDENT PunctuationLeftParen leftParen;
		public Javascript_Function function;
		public @OUTDENT PunctuationRightParen rightParen;
		public Javascript_ParenthesizedExpression arguments;
	}

	public static class Javascript_SimpleArray extends ExpressionTerm
	{
		public PunctuationLeftBracket leftBracket;
		public @OPT Javascript_Expression expr;
		public @OPT TokenList<Javascript_MoreArray> more;
		public PunctuationRightBracket rightBracket;
		
		public static class Javascript_MoreArray extends TokenSequence
		{
			public PunctuationComma comma;
			public @OPT Javascript_Expression expr;
		}
	}
	
	public static class Javascript_CastExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public @NOSPACE Javascript_Type jtype;
		public @NOSPACE PunctuationRightParen rightParen;
		public Javascript_Expression expr;
	}
	
	public static class Javascript_DeleteExpression extends ExpressionTerm
	{
		public Javascript_Keyword DELETE = new Javascript_Keyword("delete");
		public Javascript_Expression expr;
	}

	public static class Javascript_ExpressionList extends ExpressionTerm
	{
		public PunctuationLeftBrace leftBrace;
		public @OPT @NOSPACE TokenList<Javascript_Comment> comment;
		public @OPT @NOSPACE Javascript_ArgumentList valueList;
		public @NOSPACE PunctuationRightBrace rightBrace;
	}
	
	public static class Javascript_NewNoArgsExpression extends ExpressionTerm
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
	}
	
	public static class Javascript_ClassCreationExpression extends ExpressionTerm
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
		public Javascript_ParenthesizedExpression arguments;
	}
	
	public static class Javascript_ClassCreationWithInitializers extends ExpressionTerm
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
		public PunctuationLeftBrace leftBrace;
		public Javascript_ArgumentList valueList;
		public PunctuationRightBrace rightBrace;
	}
	
	public static class Javascript_ClassCreationWithSubscript extends ExpressionTerm
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
		public TokenList<Javascript_Subscript> subscripts;
	}

	public static class Javascript_MethodInvocation extends ExpressionTerm
	{
		public Javascript_Variable methodName;
		public @NOSPACE Javascript_ParenthesizedExpression arguments;
	}
	
	public static class Javascript_DotClass extends ExpressionTerm
	{
		public Javascript_Type jtype;
		public PunctuationPeriod dot;
		public Javascript_Keyword CLASS = new Javascript_Keyword("class");
	}
	
	public static class Javascript_ArgumentList extends ExpressionTerm
	{
		public Javascript_Expression arg;
		public @OPT TokenList<Javascript_Comment> comment;
		public @OPT TokenList<Javascript_MoreArguments> moreArgs;
		public @OPT @CURIOUS("Extra comma") PunctuationComma comma;
		
		public static class Javascript_MoreArguments extends TokenSequence
		{
			public @NOSPACE PunctuationComma comma;
			public @OPT TokenList<Javascript_Comment> comment1;
			public Javascript_Expression arg;
			public @OPT TokenList<Javascript_Comment> comment2;
		}
	}

	public static class Javascript_PreIncrementExpression extends ExpressionTerm
	{
		public Javascript_Punctuation preIncrementOperator = new Javascript_Punctuation("++");
		public @NOSPACE Javascript_Variable var;
	}

	public static class Javascript_PreDecrementExpression extends ExpressionTerm
	{
		public Javascript_Punctuation preDecrementOperator = new Javascript_Punctuation("--");
		public @NOSPACE Javascript_Variable var;
	}
	
	public static class Javascript_PostIncrementExpression extends ExpressionTerm
	{
		public Javascript_Variable var;
		public @NOSPACE Javascript_Punctuation postIncrementOperator = new Javascript_Punctuation("++");
	}

	public static class Javascript_PostDecrementExpression extends ExpressionTerm
	{
		public Javascript_Variable var;
		public @NOSPACE Javascript_Punctuation postDecrementOperator = new Javascript_Punctuation("--");
	}
	
	public static class Javascript_NegativeExpression extends ExpressionTerm
	{
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("-", "+");
		public Javascript_Expression expr;
	}

	public static class Javascript_LogicalNotExpression extends ExpressionTerm
	{
		public Javascript_Punctuation logicalNotOperator = new Javascript_Punctuation('~');
		public Javascript_Expression expr;
	}
	
	public static class Javascript_NotExpression extends ExpressionTerm
	{
		public Javascript_Punctuation notOperator = new Javascript_Punctuation('!');
		public Javascript_Expression expr;
	}
	
	public static class Javascript_CommentExpression extends ExpressionTerm
	{
		public Javascript_Comment comment;
		public Javascript_Expression expr;
	}
	
	public static class Javascript_Dictionary extends ExpressionTerm
	{
		// Don't use @INDENT here. Messes up 'return' statements that return a dictionary.
		public PunctuationLeftBrace leftBrace;
		public SeparatedList<Javascript_DictionaryItem,PunctuationComma> items;
		public @OPT PunctuationComma comma;
		public @OPT Javascript_Comment comment;
		public PunctuationRightBrace rightBrace;
		
		public static class Javascript_DictionaryItem extends TokenSequence
		{
			public Javascript_Expression key;
			public PunctuationColon colon;
			public Javascript_Expression value = new Javascript_Expression(false);
		}
	}

	public static class Javascript_FunctionExpr extends ExpressionTerm
	{
		public Javascript_Function function;
		public @OPT Javascript_ParenthesizedExpression args;
	}
	
	public static class Javascript_TypeOfExpr extends ExpressionTerm
	{
		public Javascript_Keyword TYPEOF = new Javascript_Keyword("typeof");
		public Javascript_Expression expr;
	}
	
	public static class Javascript_VoidExpr extends ExpressionTerm
	{
		public Javascript_Keyword VOID = new Javascript_Keyword("void");
		public Javascript_Number number;
	}
	
	public static class Javascript_StringFunction extends ExpressionTerm
	{
		public Javascript_Keyword STRING = new Javascript_Keyword("String");
		public PunctuationLeftParen leftParen;
		public Javascript_Expression expr;
		public PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class Javascript_CommaExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @NOSPACE PunctuationComma comma;
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Javascript_AssignmentExpression extends PrecedenceOperator
	{
		public Javascript_Expression var = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	}

	public static class Javascript_TrueFalseExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public Javascript_Punctuation questionMark = new Javascript_Punctuation('?');
		public Javascript_Expression middle = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationColon colon;
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static class Javascript_ConditionalOrExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation orOperator = new Javascript_Punctuation("||");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Javascript_ConditionalAndExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation andOperator = new Javascript_Punctuation("&&");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Javascript_InclusiveOrExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation bitwiseOrOperator = new Javascript_Punctuation('|');
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_ExclusiveOrExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation bitwiseXOrOperator = new Javascript_Punctuation('^');
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_AndExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation bitwiseAndOperator = new Javascript_Punctuation('&');
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_EqualityExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("!==", "===", "==", "!=");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public @OPT Javascript_Comment comment;
	}
	
	public static class Javascript_RelationalExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("<", ">", "<=", ">=");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_InstanceOfExpression extends PrecedenceOperator
	{
		public Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Keyword instanceOperator = new Javascript_Keyword("instanceof");
		public Javascript_Type type;
	}

	public static class Javascript_InExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Keyword inOperator = new Javascript_Keyword("in");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_ShiftExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice(">>>", "<<", ">>");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_AdditiveExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("+", "-");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_MultiplicativeExpression extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("*", "/", "%");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_Subfield extends PrecedenceOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @NOSPACE PunctuationPeriod dot;
		public @NOSPACE Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_SubscriptExpression extends PrecedenceOperator
	{
		public Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @NOSPACE PunctuationLeftBracket leftBracket;
		public @NOSPACE Javascript_Expression subscr = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public @NOSPACE PunctuationRightBracket rightBracket;
	}
}

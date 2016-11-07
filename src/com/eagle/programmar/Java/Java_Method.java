// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Java_Statement.Java_StatementBlock;
import com.eagle.programmar.Java.Java_Type.Java_GenericType;
import com.eagle.programmar.Java.Symbols.Java_Current_Class_Reference;
import com.eagle.programmar.Java.Symbols.Java_Method_Definition;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Method extends TokenSequence implements EagleScopeInterface
{
	private EagleScope _scope = new EagleScope(Java_Syntax.isCaseSensitive);
	
	public @OPT @NEWLINE TokenList<Java_Comment> comments;
	public @OPT TokenList<Java_MethodModifiers> modifiers;
	public @OPT Java_GenericType genericType;
	public Java_Type jtype;
	public Java_Method_Definition methodName;
	public @NOSPACE PunctuationLeftParen leftParen;
	public @OPT @NOSPACE Java_MethodParameter param;
	public @OPT @NOSPACE TokenList<Java_MoreParameters> moreParams;
	public @NOSPACE PunctuationRightParen rightParen;
	public @OPT Java_MethodDefault methodDefault;
	public @OPT Java_MethodThrows jthrows;
	public @OPT Java_Comment comment;
	public Java_MethodBody body;
	
	public static class Java_MethodDefault extends TokenSequence
	{
		public Java_Keyword DEFAULT = new Java_Keyword("default");
		public @OPT Java_Expression expr;
	}
	
	public static class Java_MethodModifiers extends TokenChooser
	{
		public @FIRST Java_Comment comment;
		public @CHOICE Java_KeywordChoice modifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
		public @CHOICE Java_Annotation annotation;
	}
	
	public static class Java_MethodParameter extends TokenSequence
	{
		public @OPT TokenList<Java_MethodParameterPrefix> prefixes;
		public @NOSPACE Java_Type jtype;
		public @OPT Java_Punctuation elipsis = new Java_Punctuation("...");
		public Java_Variable_Definition id;
		public @OPT Java_Punctuation emptySubscript = new Java_Punctuation("[]");
		
		public static class Java_MethodParameterPrefix extends TokenChooser
		{
			public @CHOICE Java_Keyword FINAL = new Java_Keyword("final");

			public @CHOICE static class Java_MethodNullable extends TokenSequence
			{
				public Java_Punctuation atSign = new Java_Punctuation('@');
				public Java_Keyword NULLABLE = new Java_Keyword("Nullable");
			}

			public @CHOICE static class Java_MethodSuppress extends TokenSequence
			{
				public Java_Punctuation atSign = new Java_Punctuation('@');
				public @NOSPACE Java_Keyword SUPPRESS = new Java_Keyword("SuppressWarnings");
				public @NOSPACE PunctuationLeftParen leftParen;
				public @NOSPACE Java_Literal warning;
				public @NOSPACE PunctuationRightParen rightParen;
			}
		}
	}
		
	public static class Java_MoreParameters extends TokenSequence
	{
		public @NOSPACE PunctuationComma comma;
		public Java_MethodParameter param;
	}
	
	public static class Java_MethodThrows extends TokenSequence
	{
		public Java_Keyword jthrows = new Java_Keyword("throws");
		public SeparatedList<Java_Variable,PunctuationComma> jclass;
	}
	
	public static class Java_MethodBody extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon semicolon;
		
		public @CHOICE static class Java_MethodImplementation extends TokenSequence
		{
			public @OPT TokenList<Java_Comment> comment1;
			public Java_StatementBlock block;
			public @OPT TokenList<Java_Comment> comment2;
			public @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon2;
		}
	}
	
	public static class Java_Constructor extends TokenSequence
	{
		public @OPT @BLANKLINE TokenList<Java_Annotation> annotation;
		public @OPT TokenList<Java_MethodModifiers> modifiers;
		public Java_Current_Class_Reference constructorName;
		public @NOSPACE PunctuationLeftParen leftParen;
		public @OPT Java_MethodParameter param;
		public @OPT TokenList<Java_MoreParameters> moreParams;
		public @NOSPACE PunctuationRightParen rightParen;
		public @OPT Java_MethodThrows jthrows;
		public @OPT Java_Comment comment;
		public Java_MethodBody body;
	}
		
	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
//	@Override
//	public void setScope(EagleScope scope)
//	{
//		_scope = scope;
//	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Java_Statement.Java_StatementBlock.Java_StatementOrComment;
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
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Java_Method extends TokenSequence implements EagleScopeInterface
{
	private EagleScope _scope = new EagleScope(Java_Syntax.isCaseSensitive);
	
	public @OPT @NEWLINE TokenList<Java_Comment> comments;
	public @OPT TokenList<Java_MethodModifiers> modifiers;
	public @OPT Java_GenericType genericType;
	public Java_Type jtype;
	public Java_Method_Definition methodName;
	public @NOSPACE Java_Punctuation leftParen = new Java_Punctuation('(');
	public @OPT @NOSPACE Java_MethodParameter param;
	public @OPT @NOSPACE TokenList<Java_MoreParameters> moreParams;
	public @NOSPACE Java_Punctuation rightParen = new Java_Punctuation(')');
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
		public Java_KeywordChoice modifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
		public Java_Annotation annotation;
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
			public Java_Keyword FINAL = new Java_Keyword("final");

			public static class Java_MethodNullable extends TokenSequence
			{
				public Java_Punctuation atSign = new Java_Punctuation('@');
				public Java_Keyword NULLABLE = new Java_Keyword("Nullable");
			}

			public static class Java_MethodSuppress extends TokenSequence
			{
				public Java_Punctuation atSign = new Java_Punctuation('@');
				public @NOSPACE Java_Keyword SUPPRESS = new Java_Keyword("SuppressWarnings");
				public @NOSPACE Java_Punctuation leftParen = new Java_Punctuation('(');
				public @NOSPACE Java_Literal warning;
				public @NOSPACE Java_Punctuation rightParen = new Java_Punctuation(')');
			}
		}
	}
		
	public static class Java_MoreParameters extends TokenSequence
	{
		public @NOSPACE Java_Punctuation comma = new Java_Punctuation(',');
		public Java_MethodParameter param;
	}
	
	public static class Java_MethodThrows extends TokenSequence
	{
		public Java_Keyword jthrows = new Java_Keyword("throws");
		public Java_Variable jclass;
		public @OPT TokenList<Java_More_Exceptions> moreExceptions;
		
		public static class Java_More_Exceptions extends TokenSequence
		{
			public @NOSPACE Java_Punctuation comma = new Java_Punctuation(',');
			public Java_Variable jclass;
		}
	}
	
	public static class Java_MethodBody extends TokenChooser
	{
		public Java_Punctuation semicolon = new Java_Punctuation(';');
		
		public static class Java_MethodImplementation extends TokenSequence
		{
			public @OPT TokenList<Java_Comment> comment1;
			public @INDENT Java_Punctuation leftBrace = new Java_Punctuation('{');
			public @OPT TokenList<Java_StatementOrComment> elements;
			public @OPT @CURIOUS("Extra semicolon") Java_Punctuation semicolon1 = new Java_Punctuation(';');
			public @OUTDENT Java_Punctuation rightBrace = new Java_Punctuation('}');
			public @OPT TokenList<Java_Comment> comment2;
			public @OPT @CURIOUS("Extra semicolon") Java_Punctuation semicolon2 = new Java_Punctuation(';');
		}
	}
	
	public static class Java_Constructor extends TokenSequence
	{
		public @OPT @NEWLINE2 TokenList<Java_Annotation> annotation;
		public @OPT TokenList<Java_MethodModifiers> modifiers;
		public Java_Current_Class_Reference constructorName;
		public Java_Punctuation leftParen = new Java_Punctuation('(');
		public @OPT Java_MethodParameter param;
		public @OPT TokenList<Java_MoreParameters> moreParams;
		public Java_Punctuation rightParen = new Java_Punctuation(')');
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

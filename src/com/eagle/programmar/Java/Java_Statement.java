// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Java_Method.Java_MethodModifiers;
import com.eagle.programmar.Java.Statements.Java_AssertStatement;
import com.eagle.programmar.Java.Statements.Java_BreakStatement;
import com.eagle.programmar.Java.Statements.Java_ContinueStatement;
import com.eagle.programmar.Java.Statements.Java_DoStatement;
import com.eagle.programmar.Java.Statements.Java_ExpressionStatement;
import com.eagle.programmar.Java.Statements.Java_ForStatement;
import com.eagle.programmar.Java.Statements.Java_IfStatement;
import com.eagle.programmar.Java.Statements.Java_ReturnStatement;
import com.eagle.programmar.Java.Statements.Java_SuperStatement;
import com.eagle.programmar.Java.Statements.Java_SwitchStatement;
import com.eagle.programmar.Java.Statements.Java_SynchronizedStatement;
import com.eagle.programmar.Java.Statements.Java_ThrowStatement;
import com.eagle.programmar.Java.Statements.Java_TryStatement;
import com.eagle.programmar.Java.Statements.Java_WhileStatement;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Java_Statement extends TokenChooser
{
	public Java_Data jdata;
	public Java_Class jclass;
	public Java_Enum jenum;
	
	public @CURIOUS("Empty statement") Java_Punctuation emptyStatement = new Java_Punctuation(';');
	
	public static class Java_AnnotationDefinition extends TokenSequence
	{
		public @OPT Java_Annotation annotation;
		public TokenList<Java_MethodModifiers> modifiers;
		public Java_Punctuation atSign = new Java_Punctuation('@');
		public Java_Keyword INTERFACE = new Java_Keyword("interface");
		public Java_Identifier id;
		public Java_Punctuation leftBrace = new Java_Punctuation('{');
		public @OPT TokenList<Java_Comment> comments;
		public @OPT Java_AnnotationParameter parameter;
		public Java_Punctuation rightBrace = new Java_Punctuation('}');
		
		public static class Java_AnnotationParameter extends TokenSequence
		{
			public Java_Type type;
			public Java_Identifier id;
			public Java_Punctuation leftParen = new Java_Punctuation('(');
			public Java_Punctuation rightParen = new Java_Punctuation(')');
			public Java_Punctuation semicolon = new Java_Punctuation(';');
		}
	}

	public static class Java_StatementBlock extends TokenSequence implements EagleScopeInterface
	{
		private EagleScope _scope = new EagleScope(Java_Syntax.isCaseSensitive);
		
		public @INDENT Java_Punctuation leftBrace = new Java_Punctuation('{');
		public @OPT TokenList<Java_StatementOrComment> statements;
		public @OUTDENT Java_Punctuation rightBrace = new Java_Punctuation('}');
		
		public static class Java_StatementOrComment extends TokenChooser
		{
			public @FIRST @NEWLINE Java_Comment comment;
			public @NEWLINE Java_Statement statement;
		}
		
		@Override
		public EagleScope getScope()
		{
			return _scope;
		}
		
//		@Override
//		public void setScope(EagleScope scope)
//		{
//			_scope = scope;
//		}
	}

	public Java_AssertStatement assertStatement;
	public Java_BreakStatement breakStatement;
	public Java_ContinueStatement continueStatement;
	public Java_DoStatement doStatement;
	public Java_ForStatement forStatement;
	public Java_IfStatement ifStatement;
	public Java_ReturnStatement returnStatement;
	public Java_SuperStatement superStatement;
	public Java_SwitchStatement switchStatement;
	public Java_SynchronizedStatement synchronizedStatement;
	public Java_ThrowStatement throwStatement;
	public Java_TryStatement tryStatement;
	public Java_WhileStatement whileStatement;

	// Do this one last, just because it is so slow
	public @LAST Java_ExpressionStatement assignmentStatement;
	
	//public @LAST Java_UnparsedStatement unparsed;
}

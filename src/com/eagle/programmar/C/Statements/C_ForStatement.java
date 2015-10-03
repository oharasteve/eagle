// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Expression.C_AssignmentExpression;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class C_ForStatement extends TokenChooser
{
	public static class C_ForLoopStatement extends TokenSequence
	{
		public @DOC("#The-for-Statement") C_Keyword FOR = new C_Keyword("for");
		public C_Punctuation leftParen = new C_Punctuation('(');
		public @OPT C_ForLoopVariable initializer;
		public @OPT C_Comment comment1;
		public C_Punctuation semicolon1 = new C_Punctuation(';');
		public @OPT C_Expression terminateCondition;
		public @OPT C_Comment comment2;
		public C_Punctuation semicolon2 = new C_Punctuation(';');
		public @OPT C_Expression increment;
		public @OPT TokenList<C_MoreLoopIncrements> moreLoopIncrements;
		public @OPT C_Comment comment3;
		public C_Punctuation rightParen = new C_Punctuation(')');
		public @OPT C_Comment comment4;
		public C_Statement action;

		public static class C_ForLoopVariable extends TokenChooser
		{
			public static class C_ForLoopVariableWithType extends TokenSequence
			{
				public C_Type varType;
				public C_AssignmentExpression assignment;
			}

			public static class C_ForLoopVariableNoType extends TokenSequence
			{
				public C_AssignmentExpression assignment;
			}
		}
		
		public static class C_MoreLoopIncrements extends TokenSequence
		{
			public C_Punctuation comma = new C_Punctuation(',');
			public C_ForLoopVariable forVar;
		}
	}
	
	public static class C_ForCollectionStatement extends TokenSequence
	{
		public C_Keyword FOR = new C_Keyword("for");
		public C_Punctuation leftParen = new C_Punctuation('(');
		public C_Type varType;
		public C_Variable forVar;
		public C_Punctuation colon = new C_Punctuation(':');
		public C_Expression collection;
		public C_Punctuation rightParen = new C_Punctuation(')');
		public C_Statement action;
	}
}

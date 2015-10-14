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
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_ForStatement extends TokenChooser
{
	public static class C_ForLoopStatement extends TokenSequence
	{
		public @DOC("#The-for-Statement") C_Keyword FOR = new C_Keyword("for");
		public PunctuationLeftParen leftParen;
		public @OPT C_ForLoopVariable initializer;
		public @OPT C_Comment comment1;
		public PunctuationSemicolon semicolon1;
		public @OPT C_Expression terminateCondition;
		public @OPT C_Comment comment2;
		public PunctuationSemicolon semicolon2;
		public @OPT C_Expression increment;
		public @OPT TokenList<C_MoreLoopIncrements> moreLoopIncrements;
		public @OPT C_Comment comment3;
		public PunctuationRightParen rightParen;
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
			public PunctuationComma comma;
			public C_ForLoopVariable forVar;
		}
	}
	
	public static class C_ForCollectionStatement extends TokenSequence
	{
		public C_Keyword FOR = new C_Keyword("for");
		public PunctuationLeftParen leftParen;
		public C_Type varType;
		public C_Variable forVar;
		public PunctuationColon colon;
		public C_Expression collection;
		public PunctuationRightParen rightParen;
		public C_Statement action;
	}
}

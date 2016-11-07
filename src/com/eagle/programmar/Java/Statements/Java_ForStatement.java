// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_ForStatement extends TokenChooser implements EagleScopeInterface
{
	private EagleScope _scope = new EagleScope(Java_Syntax.isCaseSensitive);
	
	public @CHOICE static class Java_ForLoopStatement extends TokenSequence
	{
		public @OPT @NEWLINE Java_Label label;
		public @DOC("statements.html#14.14") Java_Keyword FOR = new Java_Keyword("for");
		public PunctuationLeftParen leftParen;
		public @OPT Java_ForInit init;
		public @NOSPACE PunctuationSemicolon semicolon1;
		public @OPT Java_Expression terminateCondition;
		public @NOSPACE PunctuationSemicolon semicolon2;
		public @OPT Java_Expression increment;
		public @NOSPACE PunctuationRightParen rightParen;
		public @OPT Java_Comment comment;
		public Java_Statement action;

		public static class Java_ForInit extends TokenSequence
		{
			public @OPT Java_Keyword FINAL = new Java_Keyword("final");
			public SeparatedList<Java_ForWhat,PunctuationComma> what;
			
			public static class Java_ForWhat extends TokenChooser
			{
				public @CHOICE Java_Expression expr;
				
				public @CHOICE static class Java_ForWithType extends TokenSequence
				{
					public Java_Type varType;
					public Java_Expression expr;
				}
			}
		}
	}
	
	public @CHOICE static class Java_ForCollectionStatement extends TokenSequence
	{
		public @OPT @NEWLINE Java_Label label;
		public Java_Keyword FOR = new Java_Keyword("for");
		public PunctuationLeftParen leftParen;
		public @OPT Java_Keyword FINAL = new Java_Keyword("final");
		public Java_Type varType;
		public Java_Variable forVar;
		public PunctuationColon colon;
		public Java_Expression collection;
		public PunctuationRightParen rightParen;
		public @OPT Java_Comment comment;
		public Java_Statement action;
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

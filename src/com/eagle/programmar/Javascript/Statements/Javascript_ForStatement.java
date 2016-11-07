// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Data.Javascript_More_Variables;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_ForStatement extends TokenChooser
{
	public @CHOICE static class Javascript_ForLoopStatement extends TokenSequence
	{
		public @NEWLINE @DOC("js_loop_for.asp") Javascript_Keyword FOR = new Javascript_Keyword("for");
		public PunctuationLeftParen leftParen;
		public @OPT Javascript_ForLoopVariable loopVar;
		public @OPT Javascript_PunctuationChoice equals = new Javascript_PunctuationChoice("=", "+=");
		public @OPT Javascript_Expression initialize;
		public @OPT TokenList<Javascript_More_Variables> moreVariables;
		public @NOSPACE PunctuationSemicolon semicolon1;
		public @OPT Javascript_Expression terminateCondition;
		public @NOSPACE PunctuationSemicolon semicolon2;
		public @OPT Javascript_Expression increment;
		public @OPT PunctuationComma comma;
		public @OPT Javascript_Expression extraIncrement;
		public @NOSPACE PunctuationRightParen rightParen;
		public Javascript_Statement action;

		public static class Javascript_ForLoopVariable extends TokenChooser
		{
			public @FIRST static class Javascript_ForLoopVariableWithType extends TokenSequence
			{
				public @NOSPACE Javascript_Type varType;
				public Javascript_Variable forVar;
			}

			public @CHOICE static class Javascript_ForLoopVariableNoType extends TokenSequence
			{
				public @NOSPACE Javascript_Variable forVar;
			}
		}
	}
	
	public @CHOICE static class Javascript_ForCollectionStatement extends TokenSequence
	{
		public @NEWLINE Javascript_Keyword FOR = new Javascript_Keyword("for");
		public PunctuationLeftParen leftParen;
		public Javascript_Type varType;
		public @OPT Javascript_Variable forVar;  // The Javascript_Type steals it ...
		public Javascript_InOrColon inOrColon;
		public Javascript_Expression collection;
		public PunctuationRightParen rightParen;
		public Javascript_Statement action;
		
		public static class Javascript_InOrColon extends TokenChooser
		{
			public @CHOICE PunctuationColon colon;
			public @CHOICE Javascript_Keyword IN = new Javascript_Keyword("in");
		}
	}
}

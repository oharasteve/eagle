// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Data.Javascript_More_Variables;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Javascript_ForStatement extends TokenChooser
{
	public static class Javascript_ForLoopStatement extends TokenSequence
	{
		public @NEWLINE @DOC("js_loop_for.asp") Javascript_Keyword FOR = new Javascript_Keyword("for");
		public Javascript_Punctuation leftParen = new Javascript_Punctuation('(');
		public @OPT Javascript_ForLoopVariable loopVar;
		public @OPT Javascript_Punctuation equals = new Javascript_Punctuation('=');
		public @OPT Javascript_Expression initialize;
		public @OPT TokenList<Javascript_More_Variables> moreVariables;
		public @NOSPACE Javascript_Punctuation semicolon1 = new Javascript_Punctuation(';');
		public @OPT Javascript_Expression terminateCondition;
		public @NOSPACE Javascript_Punctuation semicolon2 = new Javascript_Punctuation(';');
		public @OPT Javascript_Expression increment;
		public @OPT Javascript_Punctuation comma = new Javascript_Punctuation(',');
		public @OPT Javascript_Expression extraIncrement;
		public @NOSPACE Javascript_Punctuation rightParen = new Javascript_Punctuation(')');
		public Javascript_Statement action;

		public static class Javascript_ForLoopVariable extends TokenChooser
		{
			public @FIRST static class Javascript_ForLoopVariableWithType extends TokenSequence
			{
				public @NOSPACE Javascript_Type varType;
				public Javascript_Variable forVar;
			}

			public static class Javascript_ForLoopVariableNoType extends TokenSequence
			{
				public @NOSPACE Javascript_Variable forVar;
			}
		}
	}
	
	public static class Javascript_ForCollectionStatement extends TokenSequence
	{
		public @NEWLINE Javascript_Keyword FOR = new Javascript_Keyword("for");
		public Javascript_Punctuation leftParen = new Javascript_Punctuation('(');
		public Javascript_Type varType;
		public @OPT Javascript_Variable forVar;  // The Javascript_Type steals it ...
		public Javascript_InOrColon inOrColon;
		public Javascript_Expression collection;
		public Javascript_Punctuation rightParen = new Javascript_Punctuation(')');
		public Javascript_Statement action;
		
		public static class Javascript_InOrColon extends TokenChooser
		{
			public Javascript_Punctuation colon = new Javascript_Punctuation(':');
			public Javascript_Keyword IN = new Javascript_Keyword("in");
		}
	}
}

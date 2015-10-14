// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_ForStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.14") CSharp_Keyword FOR = new CSharp_Keyword("for");
	public PunctuationLeftParen leftParen;
	public CSharp_ForLoopVariable loopVar;
	public PunctuationEquals equals;
	public CSharp_Expression initialize;
	public PunctuationSemicolon semicolon1;
	public CSharp_Expression terminateCondition;
	public PunctuationSemicolon semicolon2;
	public CSharp_Expression increment;
	public PunctuationRightParen rightParen;
	public CSharp_Statement action;

	public static class CSharp_ForLoopVariable extends TokenChooser
	{
		public static class CSharp_ForLoopVariableWithType extends TokenSequence
		{
			public @NOSPACE CSharp_Type varType;
			public CSharp_Variable forVar;
		}

		public static class CSharp_ForLoopVariableNoType extends TokenSequence
		{
			public @NOSPACE CSharp_Variable forVar;
		}
	}
}

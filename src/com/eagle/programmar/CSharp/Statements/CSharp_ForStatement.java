// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class CSharp_ForStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.14") CSharp_Keyword FOR = new CSharp_Keyword("for");
	public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
	public CSharp_ForLoopVariable loopVar;
	public CSharp_Punctuation equals = new CSharp_Punctuation('=');
	public CSharp_Expression initialize;
	public CSharp_Punctuation semicolon1 = new CSharp_Punctuation(';');
	public CSharp_Expression terminateCondition;
	public CSharp_Punctuation semicolon2 = new CSharp_Punctuation(';');
	public CSharp_Expression increment;
	public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
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

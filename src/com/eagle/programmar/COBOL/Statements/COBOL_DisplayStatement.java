// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleInterpreter.EagleValue;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_DisplayStatement extends COBOL_AbstractStatement implements EagleRunnable
{
	public @DOC("rlpsdisp.htm") COBOL_Keyword DISPLAY = new COBOL_Keyword("DISPLAY");
	public @OPT COBOL_DisplayPosition position;
	public TokenList<COBOL_DisplayClause> clauses;
	
	public static class COBOL_DisplayPosition extends TokenSequence
	{
		public COBOL_Punctuation leftParen = new COBOL_Punctuation('(');
		public @OPT COBOL_Expression x;
		public COBOL_Punctuation comma = new COBOL_Punctuation(',');
		public COBOL_Expression y;
		public COBOL_Punctuation righttParen = new COBOL_Punctuation(')');
	}
	
	public static class COBOL_DisplayClause extends TokenSequence
	{
		public COBOL_DisplayWhat what;
		public @OPT TokenList<COBOL_DisplayOptions> options;
		
		public static class COBOL_DisplayOptions extends TokenChooser
		{
			public COBOL_DisplayUpon upon;
			public COBOL_DisplayAt at;
			public COBOL_DisplayWith with;
			public COBOL_DisplayWithControl withControl;
			public COBOL_DisplayWithNoAdvancing withNoAdv;
		}
	}
	
	public static class COBOL_DisplayWithNoAdvancing extends TokenSequence
	{
		public @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public COBOL_Keyword NO = new COBOL_Keyword("NO");
		public COBOL_Keyword ADVANCING = new COBOL_Keyword("ADVANCING");
	}
	
	public static class COBOL_DisplayWithControl extends TokenSequence
	{
		public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public COBOL_Keyword CONTROL = new COBOL_Keyword("CONTROL");
		public COBOL_Identifier_Reference control;
	}
	
	public static class COBOL_DisplayUpon extends TokenSequence
	{
		public COBOL_Keyword UPON = new COBOL_Keyword("UPON");
		public COBOL_Identifier_Reference upon;
	}
	
	public static class COBOL_DisplayWhat extends TokenSequence
	{
		public COBOL_Expression expr;
		public @OPT TokenList<COBOL_DisplayMore> more;
		
		public static class COBOL_DisplayMore extends TokenSequence
		{
			public COBOL_Punctuation comma = new COBOL_Punctuation(',');
			public COBOL_Expression expr;
		}
	}
	
	public static class COBOL_DisplayAt extends TokenSequence
	{
		public COBOL_Keyword AT = new COBOL_Keyword("AT");
		public @OPT COBOL_Keyword LINE = new COBOL_Keyword("LINE");
		public @OPT COBOL_Expression location;
		public @OPT COBOL_DisplayColumn column;
		
		public static class COBOL_DisplayColumn extends TokenSequence
		{
			public COBOL_Keyword COLUMN = new COBOL_Keyword("COLUMN");
			public COBOL_Expression column;
		}
	}
	
	public static class COBOL_DisplayWith extends TokenSequence
	{
		public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public TokenList<COBOL_DisplayColor> colors;
		
		public static class COBOL_DisplayColor extends TokenSequence
		{
			public COBOL_KeywordChoice color = new COBOL_KeywordChoice(
					"FOREGROUND-COLOR", "BACKGROUND-COLOR", "HBCKGROUND-COLOR", "HIGHLIGHT", "REVERSE-VIDEO");
			public @OPT COBOL_Number fg;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_DisplayClause clause : clauses._elements)
		{
			clause.what.expr.tryToInterpret(interpreter);
			EagleValue result = interpreter.popValue();
			System.out.println(result.toString());
		}
	}
}

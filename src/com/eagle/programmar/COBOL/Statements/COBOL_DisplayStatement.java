// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement.COBOL_DisplayClause.COBOL_DisplayOptions.COBOL_DisplayAt.COBOL_DisplayColumn;
import com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement.COBOL_DisplayClause.COBOL_DisplayOptions.COBOL_DisplayAt.COBOL_DisplayLine;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_DisplayStatement extends COBOL_AbstractStatement implements EagleRunnable
{
	public @DOC("rlpsdisp.htm") COBOL_Keyword DISPLAY = new COBOL_Keyword("DISPLAY");
	public @OPT COBOL_DisplayPosition position;
	public TokenList<COBOL_DisplayClause> clauses;
	
	public static class COBOL_DisplayPosition extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public @OPT COBOL_Expression x;
		public PunctuationComma comma;
		public COBOL_Expression y;
		public PunctuationRightParen rightParen;
	}
	
	public static class COBOL_DisplayClause extends TokenSequence
	{
		public COBOL_DisplayWhat what;
		public @OPT TokenList<COBOL_DisplayOptions> options;
		
		public static class COBOL_DisplayOptions extends TokenChooser
		{
			public @CHOICE COBOL_DisplayLine line;
			public @CHOICE COBOL_DisplayColumn column;
			public @CHOICE PunctuationComma comma;

			public @CHOICE static class COBOL_DisplayUpon extends TokenSequence
			{
				public COBOL_Keyword UPON = new COBOL_Keyword("UPON");
				public COBOL_Identifier_Reference upon;
			}
			
			public @FIRST static class COBOL_DisplayAt extends TokenSequence
			{
				public COBOL_Keyword AT = new COBOL_Keyword("AT");
				public @OPT COBOL_DisplayLine line;
				public @OPT COBOL_DisplayColumn column;
				
				public static class COBOL_DisplayLine extends TokenSequence
				{
					public COBOL_Keyword LINE = new COBOL_Keyword("LINE");
					public COBOL_Expression line;
				}
				
				public static class COBOL_DisplayColumn extends TokenSequence
				{
					public COBOL_Keyword COLUMN = new COBOL_Keyword("COLUMN");
					public COBOL_Expression column;
				}
			}

			public @CHOICE static class COBOL_DisplayWith extends TokenSequence
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

			public @CHOICE static class COBOL_DisplayWithNoAdvancing extends TokenSequence
			{
				public @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
				public COBOL_Keyword NO = new COBOL_Keyword("NO");
				public COBOL_Keyword ADVANCING = new COBOL_Keyword("ADVANCING");
			}
			
			public @CHOICE static class COBOL_DisplayWithControl extends TokenSequence
			{
				public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
				public COBOL_Keyword CONTROL = new COBOL_Keyword("CONTROL");
				public COBOL_Identifier_Reference control;
			}
			
			public @CHOICE static class COBOL_DisplayLines extends TokenSequence
			{
				public COBOL_Keyword LINES = new COBOL_Keyword("LINES");
				public COBOL_Expression lines;
			}
			
			public @CHOICE static class COBOL_DisplaySize extends TokenSequence
			{
				public COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
				public COBOL_Expression size;
			}
		}
	}
	
	public static class COBOL_DisplayWhat extends TokenSequence implements EagleRunnable
	{
		public SeparatedList<COBOL_Expression,PunctuationComma> exprs;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			for (AbstractToken token : exprs._elements)
			{
				if (token instanceof COBOL_Expression)
				{
					COBOL_Expression expr = (COBOL_Expression) token;
					EagleValue result = interpreter.getEagleValue(expr);
					System.out.println(result.toString());
				}
			}
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_DisplayClause clause : clauses._elements)
		{
			interpreter.tryToInterpret(clause.what);
		}
	}
}

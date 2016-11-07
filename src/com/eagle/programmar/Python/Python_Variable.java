// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_Variable extends TokenSequence
{
	public Python_SelfOrVariable var;
	public @OPT TokenList<Python_Subscript> subscript1;
	public @OPT TokenList<Python_DotVariable> moreFields1;
	public @OPT TokenList<Python_Subscript> subscript2;
	public @OPT TokenList<Python_DotVariable> moreFields2;
	
	public static class Python_SelfOrVariable extends TokenChooser
	{
		public @CHOICE Python_Keyword SELF = new Python_Keyword("self");
		public @CHOICE Python_Identifier_Reference id;
		public @CHOICE Python_DotVariable dotVariable;
		public @CHOICE Python_PunctuationChoice dotDot = new Python_PunctuationChoice("..", ".", "_1", "_2", "__", "_$", "_");
	}
	
	public static class Python_DotVariable extends TokenSequence
	{
		public PunctuationPeriod dot1;
		public @OPT PunctuationPeriod dot2;
		public Python_Identifier_Reference fld;
	}
	
	public static class Python_Subscript extends TokenSequence
	{
		public PunctuationLeftBracket leftBracket;
		public @SYNTAX(Python_Multiline_Syntax.class) Python_Subscript_Body body;
		public PunctuationRightBracket rightBracket;

		public static class Python_Subscript_Body extends TokenSequence
		{
			public @OPT Python_EndOfLine eoln;
			public SeparatedList<Python_Subscript_Dimension,PunctuationComma> dimensions;
		}

		public static class Python_Subscript_Dimension extends TokenSequence
		{
			public @OPT Python_Expression subscr;
			public @OPT Python_ColonSubscr colonStop;
			public @OPT Python_ColonSubscr colonIncrement;

			public static class Python_ColonSubscr extends TokenSequence
			{
				public PunctuationColon colon;
				public @OPT Python_EndOfLine eoln;
				public @OPT Python_Expression subscr;
			}
		}
	}
}

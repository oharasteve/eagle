// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_Variable extends TokenSequence
{
	public Python_SelfOrVariable var;
	public @OPT TokenList<Python_Subscript> subscript1;
	public @OPT TokenList<Python_DotVariable> moreFields;
	public @OPT TokenList<Python_Subscript> subscript2;
	
	public static class Python_SelfOrVariable extends TokenChooser
	{
		public Python_Keyword SELF = new Python_Keyword("self");
		public Python_Identifier_Reference id;
		public Python_DotVariable dotVariable;
		public Python_PunctuationChoice dotDot = new Python_PunctuationChoice("..", ".", "_", "__", "_$");
	}
	
	public static class Python_DotVariable extends TokenSequence
	{
		public Python_Punctuation dot = new Python_Punctuation('.');
		public @OPT Python_Punctuation dot2 = new Python_Punctuation('.');
		public Python_Identifier_Reference fld;
	}
	
	public static class Python_Subscript extends TokenSequence
	{
		public Python_Punctuation leftBracket = new Python_Punctuation('[');
		public @SYNTAX(Python_Multiline_Syntax.class) Python_Subscript_Body body;
		public Python_Punctuation rightBracket = new Python_Punctuation(']');

		public static class Python_Subscript_Body extends TokenSequence
		{
			public @OPT Python_EndOfLine eoln;
			public Python_Subscript_Dimension dimension;
			public @OPT TokenList<Python_More_Dimensions> more;
		}

		public static class Python_Subscript_Dimension extends TokenSequence
		{
			public @OPT Python_Expression subscr;
			public @OPT Python_ColonSubscript colonStop;
			public @OPT Python_ColonSubscript colonIncrement;

			public static class Python_ColonSubscript extends TokenSequence
			{
				public Python_Punctuation colon = new Python_Punctuation(':');
				public @OPT Python_EndOfLine eoln;
				public @OPT Python_Expression subscr;
			}
		}
		
		public static class Python_More_Dimensions extends TokenSequence
		{
			public Python_Punctuation comma = new Python_Punctuation(',');
			public Python_Subscript_Dimension dimension;
		}
	}
}

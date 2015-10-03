// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Parameter_List;
import com.eagle.programmar.Python.Python_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_Statement_List;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Symbols.Python_Function_Definition;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_FunctionDefinition extends TokenSequence
{
	public @OPT TokenList<Python_Decorator> decorator;
	public @OPT TokenList<Python_EndOfLine> eoln;
	public Python_Keyword DEF = new Python_Keyword("def");
	public Python_FunctionName fnName;
	public Python_Parameter_List params;
	public Python_Punctuation colon = new Python_Punctuation(':');
	public @OPT TokenList<Python_Comment> comment;
	public Python_DefType defType;
	
	public static class Python_DefType extends TokenChooser
	{
		public Python_Statement_List statement;
		
		public static class Python_DefMultiLine extends TokenSequence
		{
			public Python_EndOfLine eoln;
			public @OPT Python_Literal comment;
			public @OPT TokenList<Python_EndOfLine> commentEoln;
			public TokenList<Python_Statement> statements;
		}
	}
	
	public static class Python_Decorator extends TokenSequence
	{
		public Python_Punctuation atSign = new Python_Punctuation('@');
		public Python_Variable id;
		public @OPT Python_Parameter_List params;
		public @OPT Python_Comment comment;
		public Python_EndOfLine newLine;
	}
	
	public static class Python_FunctionName extends TokenChooser
	{
		public Python_Function_Definition name;
		public Python_Keyword INIT = new Python_Keyword("__init__");
	}
}

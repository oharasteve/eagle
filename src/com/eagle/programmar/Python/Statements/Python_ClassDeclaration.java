// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_Statement_List;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Python_Type;
import com.eagle.programmar.Python.Statements.Python_FunctionDefinition.Python_Decorator;
import com.eagle.programmar.Python.Symbols.Python_Class_Definition;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_ClassDeclaration extends TokenSequence
{
	public @OPT TokenList<Python_Decorator> decorator;
	public @OPT TokenList<Python_EndOfLine> eoln;
	public Python_Keyword CLASS = new Python_Keyword("class");
	public Python_Class_Definition name;
	public @OPT Python_ClassSuper superClass;
	public PunctuationColon colon;
	public Python_ClassType classType;
	
	public static class Python_ClassType extends TokenChooser
	{
		public static class Python_ClassSingleLine extends TokenSequence
		{
			public Python_Statement_List statement;
		}
		
		public static class Python_ClassMultiLine extends TokenSequence
		{
			public TokenList<Python_EndOfLine> eoln;
			public TokenList<Python_Statement> entries;
		}
	}
	
	public static class Python_ClassSuper extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public @OPT Python_EndOfLine eoln;
		public @OPT Python_Type type;
		public @OPT @SYNTAX(Python_Multiline_Syntax.class) TokenList<Python_MoreTypes> moreTypes; 
		public PunctuationRightParen rightParen;

		public static class Python_MoreTypes extends TokenSequence
		{
			public PunctuationComma comma;
			public Python_Type type;
		}
	}
}

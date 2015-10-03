// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Declarations;

import com.eagle.programmar.Gupta.Gupta_Declaration;
import com.eagle.programmar.Gupta.Gupta_Statement;
import com.eagle.programmar.Gupta.Gupta_Type;
import com.eagle.programmar.Gupta.Gupta_Variable_Declaration;
import com.eagle.programmar.Gupta.Symbols.Gupta_Function_Definition;
import com.eagle.programmar.Gupta.Terminals.Gupta_CommentToEndOfLine;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.programmar.Gupta.Terminals.Gupta_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.TokenList;

public class Gupta_Function extends Gupta_Declaration
{
	public Gupta_Keyword Function = new Gupta_Keyword("Function");
	public Gupta_Punctuation colon = new Gupta_Punctuation(':');
	public Gupta_Function_Definition functionName;
	
	public @INDENT Gupta_Function_Description description;
	public Gupta_Function_Returns returns;
	public Gupta_Function_Parameters parameters;
	public Gupta_Function_Static_Variables staticVariables;
	public Gupta_Function_Local_Variables localVariables;
	public Gupta_Function_Actions actions;
	
	public static class Gupta_Function_Description extends Gupta_Declaration
	{
		public Gupta_Keyword Description = new Gupta_Keyword("Description");
		public Gupta_CommentToEndOfLine description;
	}
	
	public static class Gupta_Function_Returns extends Gupta_Declaration
	{
		public Gupta_Keyword Returns = new Gupta_Keyword("Returns");
		public @OPT Gupta_Function_Return_Type returnType;
		
		public static class Gupta_Function_Return_Type extends TokenSequence
		{
			public @INDENT Gupta_Type returnType;
			public Gupta_Punctuation colon = new Gupta_Punctuation(':');
		}
	}
	
	public static class Gupta_Function_Parameters extends Gupta_Declaration
	{
		public Gupta_Keyword Parameters = new Gupta_Keyword("Parameters");
		public @OPT @INDENT TokenList<Gupta_Variable_Declaration> variables;
	}
	
	public static class Gupta_Function_Static_Variables extends Gupta_Declaration
	{
		public Gupta_Keyword Static = new Gupta_Keyword("Static");
		public Gupta_Keyword Variables = new Gupta_Keyword("Variables");
		public @OPT @INDENT TokenList<Gupta_Variable_Declaration> variables;
	}
	
	public static class Gupta_Function_Local_Variables extends Gupta_Declaration
	{
		public Gupta_Keyword Local = new Gupta_Keyword("Local");
		public Gupta_Keyword Variables = new Gupta_Keyword("Variables");
		public @OPT @INDENT TokenList<Gupta_Variable_Declaration> variables;
	}
	
	public static class Gupta_Function_Actions extends Gupta_Declaration
	{
		public Gupta_Keyword Actions = new Gupta_Keyword("Actions");
		public @INDENT TokenList<Gupta_Statement> statements;
	}
}

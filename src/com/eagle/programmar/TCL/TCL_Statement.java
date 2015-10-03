// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Statements.TCL_IfStatement;
import com.eagle.programmar.TCL.Statements.TCL_NamespaceStatement;
import com.eagle.programmar.TCL.Statements.TCL_SetStatement;
import com.eagle.programmar.TCL.Statements.TCL_VariableStatement;
import com.eagle.programmar.TCL.Terminals.TCL_Comment;
import com.eagle.programmar.TCL.Terminals.TCL_EndOfLine;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class TCL_Statement extends TokenSequence
{
	public TCL_Compound_Statement compoundStatement;
	public @OPT TCL_Comment comment;
	public TokenList<TCL_EndOfLine> eoln;
	
	public static class TCL_Compound_Statement extends TokenSequence
	{
		public TCL_BaseStatement statement;
		public @OPT TokenList<TCL_MoreStatements> moreStatements;
		
		public static class TCL_MoreStatements extends TokenSequence
		{
			public TCL_Punctuation semicolon = new TCL_Punctuation(';');
			public TCL_BaseStatement statement;
		}
	}
	
	public static class TCL_BaseStatement extends TokenChooser
	{
		public TCL_Comment comment;
		public TCL_BlockStatement blockStatement;
		
		public TCL_IfStatement ifStatement;
		public TCL_NamespaceStatement namespaceStatement;
		public TCL_SetStatement setStatement;
		public TCL_VariableStatement variableStatement;
	}
	
	public static class TCL_BlockStatement extends TokenSequence
	{
		public TCL_Punctuation leftBrace = new TCL_Punctuation('{');
		public @OPT TokenList<TCL_Statement> statements;
		public TCL_Punctuation rightBrace = new TCL_Punctuation('}');
	}
}

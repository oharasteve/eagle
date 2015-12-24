// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Statements.TCL_IfStatement;
import com.eagle.programmar.TCL.Statements.TCL_NamespaceStatement;
import com.eagle.programmar.TCL.Statements.TCL_SetStatement;
import com.eagle.programmar.TCL.Statements.TCL_VariableStatement;
import com.eagle.programmar.TCL.Terminals.TCL_Comment;
import com.eagle.programmar.TCL.Terminals.TCL_EndOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class TCL_Statement extends TokenSequence
{
	public TCL_Compound_Statement compoundStatement;
	public @OPT TCL_Comment comment;
	public TCL_EndOfLine eoln;
	
	public static class TCL_Compound_Statement extends TokenSequence
	{
		public SeparatedList<TCL_BaseStatement,PunctuationSemicolon> statements;
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
		public PunctuationLeftBrace leftBrace;
		public @OPT TokenList<TCL_Statement> statements;
		public PunctuationRightBrace rightBrace;
	}
}

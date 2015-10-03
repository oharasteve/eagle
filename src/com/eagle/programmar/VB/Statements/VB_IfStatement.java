// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.VB_Statement.VB_BaseStatement;
import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class VB_IfStatement extends TokenSequence
{
	public @DOC("752y8abs.aspx") VB_Keyword IF1 = new VB_Keyword("if");
	public VB_Expression condition;
	public VB_Keyword THEN = new VB_Keyword("then");
	public VB_IfType ifType;
	
	public static class VB_IfType extends TokenChooser
	{
		public static class VB_IfOneLiner extends TokenSequence
		{
			public VB_BaseStatement thenStatement;
		}
		
		public static class VB_IfMultiLiner extends TokenSequence
		{
			public TokenList<VB_EndOfLine> eoln;
			public TokenList<VB_Statement> thenStatement;
			public @OPT TokenList<VB_IfElseIfClause> elseIfClause;
			public @OPT VB_IfElseClause elseClause;
			public VB_Keyword END = new VB_Keyword("end");
			public VB_Keyword IF2 = new VB_Keyword("if");
		}
	}

	public static class VB_IfElseIfClause extends TokenSequence
	{
		public @OPT TokenList<VB_Comment> comments;
		public VB_Keyword ELSEIF = new VB_Keyword("elseif");
		public VB_Expression condition;
		public VB_Keyword THEN = new VB_Keyword("then");
		public TokenList<VB_EndOfLine> eoln;
		public TokenList<VB_Statement> elseIfStatement;
	}
	
	public static class VB_IfElseClause extends TokenSequence
	{
		public @OPT TokenList<VB_Comment> comments;
		public VB_Keyword ELSE = new VB_Keyword("else");
		public TokenList<VB_EndOfLine> eoln;
		public TokenList<VB_Statement> elseStatement;
	}
}

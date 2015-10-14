// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.FindIncludeFile;
import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.C_Syntax;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.CMacro_Statement;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_If_Statement extends CMacro_Statement
{
	public @DOC("If.html") C_Keyword IF = new C_Keyword("if");
	public CMacro_Expression expr;
	public @OPT C_Comment comment;
	public CMacro_EndOfLine eoln1;
	public @OPT TokenList<CMacro_IfElement> elements;
	public @OPT TokenList<CMacro_IfElif> ifElif;
	public @OPT CMacro_IfElse ifElse;
	public @OPT CMacro_EndOfLine eoln2;
	public C_Punctuation pound = new C_Punctuation('#'); 
	public C_Keyword ENDIF = new C_Keyword("endif");

	public static class CMacro_IfElif extends TokenSequence
	{
		public C_Punctuation pound1 = new C_Punctuation('#');
		public C_Keyword ELIF = new C_Keyword("elif");
		public CMacro_Expression expr;
		public @OPT TokenList<CMacro_IfElement> elements;
	}
	
	public static class CMacro_IfElse extends TokenSequence
	{
		public C_Punctuation pound1 = new C_Punctuation('#');
		public C_Keyword ELSE = new C_Keyword("else");
		public @OPT @SYNTAX(C_Syntax.class) TokenList<CMacro_IfElement> elements;
	}
	
	public static class CMacro_IfElement extends TokenSequence
	{
		public @SYNTAX(C_Syntax.class) C_StatementOrComment elements;
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor, FindIncludeFile findInclude)
	{
		boolean isTrue = expr.getBooleanValue(preprocessor);
		if (isTrue)
		{
			// Use the "then" part
			for (AbstractToken element : elements._elements)
			{
				preprocessor.preprocessElement(element, findInclude);
			}
		}
		else
		{
			// Use the "else" part, if any
			if (ifElse._present)
			{
				for (AbstractToken element : ifElse.elements._elements)
				{
					preprocessor.preprocessElement(element, findInclude);
				}
			}
		}
		
		return true;	// Changed the file
	}
}

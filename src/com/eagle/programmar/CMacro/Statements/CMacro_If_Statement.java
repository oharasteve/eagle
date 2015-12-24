// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.C_Syntax;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_If_Statement extends TokenSequence implements CMacro_Processable
{
	public C_Punctuation pound1 = new C_Punctuation('#'); 
	public @DOC("If.html") C_Keyword IF = new C_Keyword("if");
	public CMacro_Expression expr;
	public @OPT C_Comment comment1;
	public CMacro_EndOfLine eoln1;
	public @OPT TokenList<CMacro_IfElement> elements;
	public @OPT TokenList<CMacro_IfElif> ifElif;
	public @OPT CMacro_IfElse ifElse;
	public @OPT CMacro_EndOfLine eoln2;
	public C_Punctuation pound2 = new C_Punctuation('#'); 
	public C_Keyword ENDIF = new C_Keyword("endif");
	public @OPT C_Comment comment2;

	public static class CMacro_IfElif extends TokenSequence
	{
		public C_Punctuation pound1 = new C_Punctuation('#');
		public C_Keyword ELIF = new C_Keyword("elif");
		public CMacro_Expression expr;
		public @OPT @SYNTAX(C_Syntax.class) C_Comment comment;
		public @OPT CMacro_EndOfLine eoln;
		public @OPT TokenList<CMacro_IfElement> elements;
	}
	
	public static class CMacro_IfElse extends TokenSequence
	{
		public C_Punctuation pound1 = new C_Punctuation('#');
		public C_Keyword ELSE = new C_Keyword("else");
		public @OPT @SYNTAX(C_Syntax.class) C_Comment comment;
		public @OPT CMacro_EndOfLine eoln;
		public @OPT TokenList<CMacro_IfElement> elements;
	}
	
	// Need this for switching languages from CMacro to C
	public static class CMacro_IfElement extends TokenSequence
	{
		public @SYNTAX(C_Syntax.class) C_StatementOrComment element;
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		boolean isTrue = expr.getBooleanValue(preprocessor);
		TokenList<CMacro_IfElement> whichElements = null;
		if (isTrue)
		{
			whichElements = elements;	// Use the "then" clause
		}
		else
		{
			// Check for #elif clauses
			for (CMacro_IfElif elif : ifElif._elements)
			{
				if (elif.expr.getBooleanValue(preprocessor))
				{
					whichElements = elif.elements;
					break;
				}
			}
		}
		
		// Well, maybe there is a #else clause
		if (whichElements == null && ifElse._present)
		{
			whichElements = ifElse.elements;
		}
		
		// Dang, nothing matches, and no "else" clause
		if (whichElements == null) return true;
		
		for (AbstractToken token : whichElements._elements)
		{
			if (token instanceof CMacro_IfElement)	// Normal case for C_Progam
			{
				CMacro_IfElement element = (CMacro_IfElement) token;
				preprocessor.preprocessCStatement(element.element);
			}
			else if (token instanceof CMacro_Element)	// Class override in CMacro_Program
			{
				CMacro_Element element = (CMacro_Element) token;
				preprocessor.preprocessCMacroElement(element);
			}
			else throw new RuntimeException("Didn't expect " + token + " here");
		}
		return true;	// Always change the file
	}
}

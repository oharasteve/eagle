// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.FindIncludeFile;
import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.C_Syntax;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CMacro.CMacro_Statement;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class CMacro_IfDef_Statement extends CMacro_Statement
{
	private static final String IFDEF = "ifdef";
	private static final String IFNDEF = "ifndef";
	
	public @DOC("Ifdef.html") C_KeywordChoice IFDEFNDEF = new C_KeywordChoice(IFDEF, IFNDEF);
	public CMacro_Identifier_Reference ref;
	public @OPT C_Comment comment1;
	public CMacro_EndOfLine eoln1;
	public @OPT TokenList<CMacro_IfDefElement> elements;
	public @OPT CMacro_IfDefElse ifElse;
	public @OPT CMacro_EndOfLine eoln2;
	public C_Punctuation pound2 = new C_Punctuation('#'); 
	public C_Keyword ENDIF = new C_Keyword("endif");

	public static class CMacro_IfDefElse extends TokenSequence
	{
		public @OPT CMacro_EndOfLine eoln;
		public C_Punctuation pound1 = new C_Punctuation('#'); 
		public C_Keyword ELSE = new C_Keyword("else");
		public @OPT TokenList<CMacro_IfDefElement> elements;
	}
	
	public static class CMacro_IfDefCPlusPlus extends TokenSequence
	{
		public @DOC("Ifdef.html") C_Keyword ifdef1 = new C_Keyword("ifdef");
		public C_Keyword CPLUSPLUS1 = new C_Keyword("__cplusplus");
		public CMacro_EndOfLine eoln1;
		public C_Keyword EXTERN = new C_Keyword("extern");
		public C_Literal C;
		public PunctuationLeftBrace leftBrace;
		public CMacro_EndOfLine eoln2;
		public C_Punctuation pound2 = new C_Punctuation('#'); 
		public C_Keyword ENDIF1 = new C_Keyword("endif");
		public CMacro_EndOfLine eoln3;
		
		public @OPT TokenList<CMacro_IfDefElement> element;

		public C_Punctuation pound3 = new C_Punctuation('#'); 
		public @DOC("Ifdef.html") C_Keyword ifdef2 = new C_Keyword("ifdef");
		public C_Keyword CPLUSPLUS2 = new C_Keyword("__cplusplus");
		public CMacro_EndOfLine eoln4;
		public PunctuationRightBrace rightBrace;
		public @OPT C_Comment comment;
		public CMacro_EndOfLine eoln5;
		public C_Punctuation pound4 = new C_Punctuation('#'); 
		public C_Keyword ENDIF2 = new C_Keyword("endif");
	}
	
	public static class CMacro_IfDefElement extends TokenSequence
	{
		public @SYNTAX(C_Syntax.class) C_StatementOrComment element;
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor, FindIncludeFile findInclude)
	{
		String macroName = ref.getValue();
		System.out.println("#" + IFDEFNDEF + " " + macroName + " ...");
		boolean isIfDef = IFDEFNDEF.toString().equals(IFDEF);
		AbstractToken macro = preprocessor.findSymbol(macroName);
		if (macro != null ^ isIfDef)	// XOR
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
		else
		{
			// Use the "then" part
			for (AbstractToken element : elements._elements)
			{
				preprocessor.preprocessElement(element, findInclude);
			}
		}
		
		return true;	// Changed the file
	}
}

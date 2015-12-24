// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.math.EagleValue;
import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.C_Syntax;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class CMacro_IfDef_Statement extends TokenSequence implements CMacro_Processable
{
	private static final String IFDEF = "ifdef";
	private static final String IFNDEF = "ifndef";
	
	public C_Punctuation pound = new C_Punctuation('#'); 
	public @DOC("Ifdef.html") C_KeywordChoice IFDEFNDEF = new C_KeywordChoice(IFDEF, IFNDEF);
	public CMacro_Identifier_Reference ref;
	public @OPT C_Comment comment1;
	public CMacro_EndOfLine eoln1;
	public @OPT TokenList<CMacro_IfDefElement> elements;
	public @OPT TokenList<CMacro_IfDefElif> ifElif;
	public @OPT CMacro_IfDefElse ifElse;
	public @OPT CMacro_EndOfLine eoln2;
	public C_Punctuation pound2 = new C_Punctuation('#'); 
	public C_Keyword ENDIF = new C_Keyword("endif");
	
	public static class CMacro_IfDefElif extends TokenSequence
	{
		public C_Punctuation pound1 = new C_Punctuation('#');
		public C_Keyword ELIF = new C_Keyword("elif");
		public CMacro_Expression expr;
		public @OPT @SYNTAX(C_Syntax.class) C_Comment comment;
		public @OPT CMacro_EndOfLine eoln;
		public @OPT TokenList<CMacro_IfDefElement> elements;
	}

	public static class CMacro_IfDefElse extends TokenSequence
	{
		public @OPT CMacro_EndOfLine eoln1;
		public C_Punctuation pound1 = new C_Punctuation('#'); 
		public C_Keyword ELSE = new C_Keyword("else");
		public @OPT C_Comment comment;
		public CMacro_EndOfLine eoln2;
		public @OPT TokenList<CMacro_IfDefElement> elements;
	}
	
	public static class CMacro_IfDefCPlusPlus extends TokenSequence
	{
		public C_Punctuation pound = new C_Punctuation('#'); 
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
		
		public @OPT TokenList<CMacro_IfDefElement> elements;

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
	
	// Need this for switching languages from CMacro to C
	public static class CMacro_IfDefElement extends TokenSequence
	{
		public @SYNTAX(C_Syntax.class) C_StatementOrComment element;
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		String macroName = ref.getValue();
		//System.out.println("#" + IFDEFNDEF + " " + macroName + " ...");
		boolean isIfDef = IFDEFNDEF.toString().equals(IFDEF);
		EagleValue value = preprocessor._symbolTable.findSymbol(macroName);
		
		TokenList<CMacro_IfDefElement> whichElements;
		if (value == null ^ isIfDef)	// XOR
		{
			whichElements = elements;
		}
		else if (ifElse._present)
		{
			whichElements = ifElse.elements;
		}
		else return true;	// No "#else" given.
		
		for (AbstractToken token : whichElements._elements)
		{
			if (token instanceof CMacro_IfDefElement)
			{
				CMacro_IfDefElement element = (CMacro_IfDefElement) token;
				preprocessor.preprocessCStatement(element.element);
			}
			else	// Class override for CMacro_Program
			{
				CMacro_Element element = (CMacro_Element) token;
				preprocessor.preprocessCMacroElement(element);
			}
		}

		return true;	// Always change the file
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 25, 2013

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public abstract class COBOL_Program_Complete extends COBOL_Program
{
	public COBOL_Program_Complete(String name, COBOL_Syntax syntax)
	{
		super(name, syntax);
	}
	
	// Components of a complete COBOL Program
	public @OPT TokenList<COBOL_Comment> comments1;
	public @OPT TokenList<COBOL_Directive> directives;
	public @OPT TokenList<COBOL_Comment> comments2;
	public @OPT COBOL_SpecialNames specialNames;
	public @OPT COBOL_IdentificationDivision identificationDiv;
	public @OPT COBOL_EnvironmentDivision environmentDiv;
	public @OPT TokenList<COBOL_Comment> comments3;
	public @OPT COBOL_DataDivision dataDiv;
	public COBOL_ProcedureDivision procedureDiv;
	
	public @OPT TokenList<COBOL_Program_Free_Format> nestedPrograms;

	public @OPT COBOL_EndProgram endProgram;
	
	public static class COBOL_EndProgram extends TokenSequence
	{
		public COBOL_Keyword END = new COBOL_Keyword("END");
		public COBOL_Keyword PROGRAM = new COBOL_Keyword("PROGRAM");
		public COBOL_Identifier_Reference programId;
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
	}
}

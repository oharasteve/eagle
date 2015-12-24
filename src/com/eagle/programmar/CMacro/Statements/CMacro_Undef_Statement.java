// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.tokens.TokenSequence;

public class CMacro_Undef_Statement extends TokenSequence implements CMacro_Processable
{
	public C_Punctuation pound = new C_Punctuation('#'); 
	public @DOC("Undefining-and-Redefining-Macros.html") C_Keyword UNDEF = new C_Keyword("undef");
	public CMacro_Identifier_Reference var;
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		String id = var.getValue();
		//System.out.println("#undef " + id + " ...");
		preprocessor._symbolTable.removeSymbol(id);
		return true;
	}
}

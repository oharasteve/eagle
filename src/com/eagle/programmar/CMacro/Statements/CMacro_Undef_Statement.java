// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.FindIncludeFile;
import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.CMacro.CMacro_Statement;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;

public class CMacro_Undef_Statement extends CMacro_Statement
{
	public @DOC("Undefining-and-Redefining-Macros.html") C_Keyword UNDEF = new C_Keyword("undef");
	public CMacro_Identifier_Reference var;
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor, FindIncludeFile findInclude)
	{
		String id = var.getValue();
		System.out.println("#undef " + id + " ...");
		preprocessor.removeSymbol(id);
		return true;
	}
}

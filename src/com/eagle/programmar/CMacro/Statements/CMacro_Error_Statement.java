// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 10, 2014

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.FindIncludeFile;
import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.CMacro.CMacro_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement.CMacro_DefineItem;
import com.eagle.tokens.TokenList;

public class CMacro_Error_Statement extends CMacro_Statement
{
	public C_Keyword ERROR = new C_Keyword("error");
	public @OPT TokenList<CMacro_DefineItem> item;

	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor, FindIncludeFile findInclude)
	{
		// Nothing to do
		return false;	// false means we didn't change anything
	}
}

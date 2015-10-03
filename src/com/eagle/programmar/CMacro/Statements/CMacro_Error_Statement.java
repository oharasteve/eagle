// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 10, 2014

package com.eagle.programmar.CMacro.Statements;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement.CMacro_DefineItem;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_Error_Statement extends TokenSequence
{
	public C_Keyword ERROR = new C_Keyword("error");
	public @OPT TokenList<CMacro_DefineItem> item;
}

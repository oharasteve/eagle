// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.tokens.TokenSequence;

public class CMacro_Undef_Statement extends TokenSequence
{
	public @DOC("Undefining-and-Redefining-Macros.html") C_Keyword UNDEF = new C_Keyword("undef");
	public CMacro_Identifier_Reference var;
}

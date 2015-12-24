// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 10, 2014

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.Terminals.CMacro_RestOfLine;
import com.eagle.tokens.TokenSequence;

public class CMacro_Error_Statement extends TokenSequence implements CMacro_Processable
{
	public C_Punctuation pound = new C_Punctuation('#'); 
	public C_Keyword ERROR = new C_Keyword("error");
	public @OPT CMacro_RestOfLine message;		// Just keep it as a String

	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		// Nothing to do
		return false;	// false means we didn't change anything
	}
}

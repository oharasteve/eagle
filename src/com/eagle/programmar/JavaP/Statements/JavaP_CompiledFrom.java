// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Literal;
import com.eagle.tokens.TokenSequence;

public class JavaP_CompiledFrom extends TokenSequence
{
	public JavaP_Keyword COMPILED = new JavaP_Keyword("Compiled");
	public JavaP_Keyword FROM = new JavaP_Keyword("from");
	public JavaP_Literal fileName;
	public JavaP_EndOfLine eoln;
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class COBOL_ComputeStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpscomp.htm") COBOL_Keyword COMPUTE = new COBOL_Keyword("COMPUTE");
	public COBOL_Identifier_Reference var;
	public @OPT COBOL_Subscript subscript;
	public @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");
	public PunctuationEquals equals;
	public COBOL_Expression expr;
}

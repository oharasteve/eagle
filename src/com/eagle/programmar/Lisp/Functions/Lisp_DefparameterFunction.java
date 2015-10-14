// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.Lisp.Functions;

import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Symbols.Lisp_Parameter_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_DefparameterFunction extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public @DOC("m_defpar.htm") Lisp_Keyword DEFPARAMETER = new Lisp_Keyword("defparameter");
	public Lisp_Parameter_Definition name;
	public Lisp_SExpr value;
	public PunctuationRightParen rightParen;
}

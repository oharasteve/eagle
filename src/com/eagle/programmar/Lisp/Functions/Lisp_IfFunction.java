// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.Lisp.Functions;

import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_IfFunction extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public @DOC("s_if.htm") Lisp_Keyword IF = new Lisp_Keyword("if");
	public Lisp_SExpr condition;
	public Lisp_SExpr ifTrue;
	public @OPT Lisp_SExpr ifFalse;
	public PunctuationRightParen rightParen;
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.Lisp.Functions;

import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Lisp_IfFunction extends TokenSequence
{
	public Lisp_Punctuation leftParen = new Lisp_Punctuation('(');
	public @DOC("s_if.htm") Lisp_Keyword IF = new Lisp_Keyword("if");
	public Lisp_SExpr condition;
	public Lisp_SExpr ifTrue;
	public @OPT Lisp_SExpr ifFalse;
	public Lisp_Punctuation rightParen = new Lisp_Punctuation(')');
}
